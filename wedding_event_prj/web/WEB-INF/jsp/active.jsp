<%-- 
    Document   : activeAccount
    Created on : Sep 1, 2015, 9:42:23 PM
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
        <h1>Hello World!</h1>
        <form action="/wedding_event_prj/account/active" method="POST">
            <label>Chúng tôi vừa gửi key active vào mail của bạn, hãy kiếm tra mail kể trong mục spam!!!Nhập key vào đây</label>
            <input type="text" name="activeKey"/>
            <br/>
            <input type="submit" value="Kích hoạt"/>
            <input type="button" value="Gửi lại mail cho tôi"/>
        </form>
        
        
    </body>
</html>
