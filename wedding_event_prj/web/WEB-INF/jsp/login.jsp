<%-- 
    Document   : login
    Created on : Aug 29, 2015, 11:20:59 PM
    Author     : SON
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="/wedding_event_prj/account/qlogin" method="POST">
            <label>username</label>
            <input type="text" name="username"/><br/>
            <label>password</label>
            <input type="password" name="passwordHash"/></br>           
            <input type="submit" value="Login"/>
         </form>
    </body>
</html>
