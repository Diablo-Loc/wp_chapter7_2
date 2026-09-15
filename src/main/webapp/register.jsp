<%-- 
    Document   : register
    Created on : Sep 10, 2026, 2:06:50 PM
    Author     : DIABLO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <h1>Download registration</h1>
        <p>To join our email list, enter your name and email address below.</p>
        
        <form action="download" method="post">
            <input type="hidden" name="action" value="registerUser">
            <input type="hidden" name="productCode" value="${sessionScope.productCode}">

            <label>Email:</label>
            <input type="email" name="email" required><br>

            <label>First Name :</label>
            <input type="text" name="firstName" required><br>

            <label>Last Name :</label>
            <input type="text" name="lastName" required><br>

            <label>&nbsp;</label>
            <input type="submit" value="Register" id="submit" class="margin_left">
        </form>
    </body>
</html>
