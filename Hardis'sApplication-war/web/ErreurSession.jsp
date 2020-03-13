<%-- 
    Document   : ErreurSession
    Created on : 11 janv. 2020, 12:06:46
    Author     : lixin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="message" scope="request" class="String"></jsp:useBean>        
        <jsp:useBean id="typeConnexion" scope="request" class="String"></jsp:useBean>  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Erreur Session</h1>
        <form method="post" action="Page">
            <p><%=message%></p>
            <input type="button" value="Reconnecter" onclick="location.href='Page?action=<%=typeConnexion%>&typeConnexion=<%=typeConnexion%>'"/>
        </form>
    </body>
</html>
