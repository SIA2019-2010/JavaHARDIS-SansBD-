<%-- 
    Document   : AfficherPacks
    Created on : 19 mars 2020, 01:35:23
    Author     : lixin
--%>

<%@page import="entitee.Produit"%>
<%@page import="java.lang.reflect.Array"%>
<%@page import="java.util.List"%>
<%@page import="entitee.Population"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:useBean id="message" scope="request" class="String"></jsp:useBean>
        <jsp:useBean id="lesPacks" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listepopulation" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listeinfos" scope="request" class="java.util.List"></jsp:useBean>
        <title>JSP Page</title>
        <%
            List<Object[]>listepacks=lesPacks;
            List<Population>lesPops=listepopulation;
            List<Object[]>listep=listeinfos;
            Object[] pers=(Object[])request.getAttribute("pers");
        %>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p><%=message%></p>
        <p><%=listepacks.size()%></p>
        <%for(Object[] pack : listepacks){%>
            <p><%=((Produit)(Array.get(pack,0))).getNomProduit()%></p>
            <p><%=((Array.get(pack,1)))%></p>
        <%}%>
    </body>
</html>
