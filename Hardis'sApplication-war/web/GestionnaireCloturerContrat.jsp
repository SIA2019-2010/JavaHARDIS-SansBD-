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
        <p><%=liste.size()%></p>
        <table>
            <tr>
                <td>Nom Produit</td>
                <td>Prix Mensuel</td>
                <td>Cloturer</td>
            </tr>
            <%for(Contrat cont : liste){%>
                <tr>
                    <td><%=cont.getLeProduit().getNomProduit()%></td>
                    <td><%=cont.getPrixMensuel()%></td>
                    <td><input type="button" value="Cloturer" onclick="location.href='Page?action=GestionnaireCloturerContrat'"></td>
                </tr>
            <%}%>
        </table>
    </body>
</html>
