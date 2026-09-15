<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Download Page</title>
    </head>
    <body>
        <main>
            <h1>Downloads</h1>
            
            <%-- Hiển thị tên Album động theo đối tượng product truyền từ Servlet --%>
            <h2>${product.description}</h2>
            
            <table>
                <thead>
                    <tr>
                        <th>Song title</th>
                        <th>Audio format</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>You Are a Star</td>
                        <td>
                            <%-- Đường dẫn mp3 sẽ tự động đổi theo mã product.code --%>
                            <a href="${pageContext.request.contextPath}/sound/${product.code}/star.mp3">MP3</a>
                        </td>
                    </tr>
                    <tr>
                        <td>Don't Make No Difference</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/sound/${product.code}/no_difference.mp3">MP3</a>
                        </td>
                    </tr>
                </tbody>
            </table>
            
            <p><a href="download?action=viewAlbums">View list of albums</a></p>
        </main>
    </body>
</html>