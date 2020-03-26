<%-- 
    Document   : Message.jsp
    Created on : 26 mars 2020, 11:38:48
    Author     : lixin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:useBean id="message" scope="request" class="String"></jsp:useBean>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Message</h1>
        <p><%=message%></p>
    </body>
</html>
