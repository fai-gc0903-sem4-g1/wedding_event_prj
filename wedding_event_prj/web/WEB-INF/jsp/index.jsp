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
   
    </head>
    <body>
        <c:choose>
            <c:when test="${empty sessionScope.currentAccount}">
                <jsp:include page="join.jsp"/>
            </c:when>
            <c:when test="${sessionScope.currentAccount.activated==true}">
               
            </c:when>
        </c:choose>



    </body>
</html>
