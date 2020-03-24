<%-- 
    Document   : AffilieAfficherContrat
    Created on : 20 mars 2020, 01:35:46
    Author     : lixin
--%>

<%@page import="java.util.List"%>
<%@page import="entitee.StatutBeneficiaire"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:useBean id="listecontrat" scope="request" class="java.util.List"></jsp:useBean>
        <title>JSP Page</title>
        <%
            List<StatutBeneficiaire>liste=listecontrat;
        %>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p><%=liste.size()%></p>
    </body>
</html>
