<%-- 
    Document   : profile
    Created on : Sep 3, 2015, 3:47:23 PM
    Author     : SON
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Đây là trang cá nhân của ${requestScope.account.firstName} ${requestScope.account.lastName}</h1>
    </body>
</html>
