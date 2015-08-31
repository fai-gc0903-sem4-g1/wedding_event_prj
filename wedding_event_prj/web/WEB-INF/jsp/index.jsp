<%-- 
    Document   : index
    Created on : Aug 29, 2015, 7:00:15 PM
    Author     : SON
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${empty sessionScope.currentUser}">
                <jsp:include page="join.jsp"/>
            </c:when>
            <c:otherwise>
                <h1>It work!</h1>
                <a href="/wedding_event_prj/account/login">Login</a><br/>
                <a href="/wedding_event_prj/account/register">Register</a><br/>
                <a href="/wedding_event_prj/account/login">Login</a><br/>
            </c:otherwise>
        </c:choose>



    </body>
</html>
