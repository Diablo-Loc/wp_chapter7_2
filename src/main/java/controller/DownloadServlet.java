package controller;

import data.AlbumsDB;
import model.User;
import util.CookieUtil;
import util.UserIO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import model.Product;

@WebServlet(urlPatterns = {"/download"}, loadOnStartup = 1)
public class DownloadServlet extends HttpServlet {
    
    @Override
    public void init() throws ServletException {
        ServletContext sc = getServletContext();
        sc.setAttribute("products", AlbumsDB.getAllProducts());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        if (action == null) {
            action = "viewAlbums";
        }
        
        String url = "/home.jsp";
        if (action.equals("viewAlbums")) {
            url = "/home.jsp";
        } else if (action.equals("checkUser")) {
            url = checkUser(request, response);
        }
        
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        String url = "/home.jsp";
        
        if (action != null && action.equals("registerUser")) {
            url = registerUser(request, response);
        }
        
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
    
    private String checkUser(HttpServletRequest request, HttpServletResponse response) {
        String productCode = request.getParameter("productCode");
        HttpSession session = request.getSession();
        session.setAttribute("productCode", productCode);

        // Lấy thông tin Album từ DB và lưu vào request
        Product product = AlbumsDB.getProduct(productCode);
        request.setAttribute("product", product);

        User user = (User) session.getAttribute("user");
        String url;

        if (user == null) {
            Cookie[] cookies = request.getCookies();
            String emailAddress = CookieUtil.getCookieValue(cookies, "emailCookie");

            if (emailAddress == null || emailAddress.trim().isEmpty()) {
                url = "/register.jsp";
            } else {
                ServletContext sc = getServletContext();
                String path = sc.getRealPath("/WEB-INF/EmailList.txt");
                user = UserIO.getUser(emailAddress, path);

                if (user != null) {
                    session.setAttribute("user", user);
                    url = "/download.jsp";
                } else {
                    url = "/register.jsp";
                }
            }
        } else {
            url = "/download.jsp";
        }
        return url;
    }

    private String registerUser(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        // Ưu tiên lấy productCode từ Form của Tab hiện tại gửi lên
        String productCode = request.getParameter("productCode");
        HttpSession session = request.getSession();

        // Nếu Form không gửi (fallback), mới lấy từ Session
        if (productCode == null || productCode.trim().isEmpty()) {
            productCode = (String) session.getAttribute("productCode");
        }

        User user = new User(firstName, lastName, email);

        ServletContext sc = getServletContext();
        String path = sc.getRealPath("/WEB-INF/EmailList.txt");
        UserIO.add(user, path);

        session.setAttribute("user", user);

        Cookie c = new Cookie("emailCookie", email);
        c.setMaxAge(60 * 60 * 24 * 365 * 2);
        c.setPath("/");
        response.addCookie(c);

        if (productCode == null || productCode.trim().isEmpty()) {
            return "/download?action=viewAlbums";
        }

        Product product = AlbumsDB.getProduct(productCode);
        request.setAttribute("product", product);

        return "/download.jsp";
    }
}