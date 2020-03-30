<%-- 
    Document   : GestionnaireValiderContrat
    Created on : 19 mars 2020, 14:51:22
    Author     : lixin
--%>

<%@page import="java.util.List"%>
<%@page import="entitee.Contrat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:useBean id="message" scope="request" class="String"></jsp:useBean>
        <jsp:useBean id="listecontrat" scope="request" class="java.util.List"></jsp:useBean>
        <%
            List<Contrat>liste=listecontrat;
        %>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p><%=message%></p>
        <table>
            <tr>
                <td>Nom Produit</td>
                <td>Prix Mensuel</td>
                <td>Valider</td>
            </tr>
        <%for(Contrat cont : liste){%>
            <tr>
                <form method="post" action="Page">
                    <td><%=cont.getLeProduit().getNomProduit()%></td>
                    <td><%=cont.getPrixMensuel()%></td>
                    <input type="hidden" name="idc" value="<%=cont.getId().toString()%>"/>
                    <input type="hidden" name="action" value="ValiderContrat"/>
                    <td><input type ="submit" value="Valider" /></td>
                </form>
            </tr>
        <%}%>
    </body>
</html>
