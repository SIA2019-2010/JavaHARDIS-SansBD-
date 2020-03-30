<%-- 
    Document   : GestionnaireAfficherAffilie
    Created on : 30 mars 2020, 01:13:36
    Author     : lixin
--%>

<%@page import="entitee.PersonnePhysique"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:useBean id="message" scope="request" class="String"></jsp:useBean>
        <jsp:useBean id="listepers" scope="request" class="java.util.List"></jsp:useBean>
        <%
            List<PersonnePhysique>liste=listepers;
        %>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%=liste.size()%>
        <%=message%>
        <%for(PersonnePhysique p:liste){%>
            <form method="post" action="Page">
                <tr>
                    <td><%=p.getNom()%></td>
                    <td><%=p.getPrenom()%></td>
                    <input type="hidden" name="idp" value="<%=p.getId().toString()%>"/>
                    <input type="hidden" name="action" value="ContratListePersonnes"/>
                    <td><input type ="submit" value="Afficher" /></td>
                </tr>
            </form>
        <%}%>
    </body>
</html>
