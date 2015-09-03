<%-- 
    Document   : home
    Created on : Sep 2, 2015, 2:53:35 PM
    Author     : SON
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../bundle/bootstrap.jsp"/>
<jsp:include page="../bundle/jstl.jsp"/>
<jsp:useBean id="accountDAO" class="com.matrimony.database.AccountDAO"/>
<c:set var="account" value="${sessionScope.account}"/>
<c:set var="userProfile" value="${account.userProfiles.iterator().next()}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Matrimony</title>
    </head>
    <body>
        <h1>Trang chủ chính thức</h1>
        
        <a href="${account.username}">${userProfile.lastName} ${userProfile.middleName} ${userProfile.firstName}</a>
    </body>
</html>
