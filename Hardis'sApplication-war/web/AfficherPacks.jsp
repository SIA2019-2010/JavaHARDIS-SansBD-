<%-- 
    Document   : AfficherPacks
    Created on : 19 mars 2020, 01:35:23
    Author     : lixin
--%>

<%@page import="java.util.List"%>
<%@page import="entitee.Population"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:useBean id="lesPacks" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listepopulation" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listeinfos" scope="request" class="java.util.List"></jsp:useBean>
        <title>JSP Page</title>
        <%
            List<Object[]>listepro=lesPacks;
            List<Population>lesPops=listepopulation;
            List<Object[]>listep=listeinfos;
            Object[] pers=(Object[])request.getAttribute("pers");
        %>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p><%=listepro.size()%></p>
    </body>
</html>
