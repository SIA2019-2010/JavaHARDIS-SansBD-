<%-- 
    Document   : ResponsableAfficherListePersonnePhique
    Created on : 20 mars 2020, 00:14:26
    Author     : lixin
--%>

<%@page import="entitee.Contrat"%>
<%@page import="entitee.Produit"%>
<%@page import="entitee.StatutBeneficiaire"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:useBean id="listestatut" scope="request" class="java.util.List"></jsp:useBean>
        <title>JSP Page</title>
        <%
            List<StatutBeneficiaire>liste=listestatut;
            Produit p0=null;
            Contrat c0=null;
        %>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p><%=listestatut.size()%><p>
        <%for(StatutBeneficiaire statut : liste){
            Contrat c1=statut.getLeContrat();
            Produit p1=c1.getLeProduit();
            if(!p1.equals(p0)){
                p0=p1;%>
                <p><%=p0.getNomProduit()%></p>
            <%}
            if(!c1.equals(c0)){
                c0=c1;%>
                <p><%=c0.getId()%></p>
            <%}%>
            <p><%=statut.toString()%></p>
        <%}%>
    </body>
</html>
