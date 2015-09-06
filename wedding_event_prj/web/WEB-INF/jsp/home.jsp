<%-- 
    Document   : home
    Created on : Sep 2, 2015, 2:53:35 PM
    Author     : SON
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../bundle/bootstrap.jsp"/>
<jsp:include page="../bundle/jstl.jsp"/>
<jsp:useBean id="userDAO" class="com.matrimony.database.UserDAO"/>
<c:set var="user" value="${sessionScope.user}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Matrimony</title>
    </head>
    <body>
        <h1>Trang chủ chính thức</h1>
        <a href="/wedding_event_prj/${user.username}">${user.name}</a>
        
        <form action="/wedding_event_prj/logout" method="POST">
            <input type="submit" value="Đăng xuất"/>
        </form>
    </body>
</html>
