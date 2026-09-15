<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Download albums music</title>
    </head>
    <body>
        <h1>List of albums</h1>
        <c:forEach var="p" items="${products}">
            <p>
                <a href="download?action=checkUser&amp;productCode=${p.code}">
                    ${p.description}
                </a>
            </p>
        </c:forEach>
    </body>
</html>