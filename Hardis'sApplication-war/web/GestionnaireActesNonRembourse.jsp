<%-- 
    Document   : GestionnaireCreationRemboursement
    Created on : 26 mars 2020, 23:25:47
    Author     : lixin
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="entitee.Acte"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:useBean id="message" scope="request" class="String"></jsp:useBean>
        <jsp:useBean id="listeacte" scope="request" class="java.util.List"></jsp:useBean>
        <%
            List<Acte> liste=listeacte;
            DateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
        %>
    </head>
    <body>
        <h1><%=message%></h1>
        <h1>Liste actes</h1>
        <table>
            <tr>
                <td>Date début</td>
                <td>Date fin</td>
                <td>Dépense</td>
                <td>Valider</td>
            </tr>
            <%for(Acte acte : liste){%>
                <tr>
                    <form method="post" action="Page">
                        <td><%=formatter.format(acte.getDateDebut())%></td>
                        <td><%=formatter.format(acte.getDateFin())%></td>
                        <td><%=acte.getDepense()%></td>
                        <input type="hidden" value="<%=acte.getId().toString()%>" name="idacte"/>
                        <input type="hidden" value="GestionnaireCreerRemboursement" name="action"/>
                        <td><input type="submit" value="Valider"/></td>
                    </form>
                </tr>
            <%}%>
        </table>
    </body>
</html>
