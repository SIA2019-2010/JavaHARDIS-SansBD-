<%-- 
    Document   : GestionnaireRempoursementEncours
    Created on : 31 mars 2020, 00:34:01
    Author     : lixin
--%>

<%@page import="entitee.Remboursement"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:useBean id="message" scope="request" class="String"></jsp:useBean>
        <jsp:useBean id="lister" scope="request" class="java.util.List"></jsp:useBean>
        <%
            List<Remboursement> liste=lister;
            //DateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
        %>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p><%=message%></p>
        <table>
            <%for(Remboursement r:liste){%>
                <tr>
                    <td><%=r.getLeActe().getId()%></td>
                    <td><%=r.getLeActe().getLaPersonnePhysique().getNom()%></td>
                    <td><%=r.getLeActe().getLaPersonnePhysique().getPrenom()%></td>
                    <td>
                        <form method="post" action="Page">
                            <input type="hidden" value="<%=r.getId().toString()%>" name="idrv"/>
                            <input type="hidden" value="GestionnaireValiderRemboursement" name="action"/>
                            <td><input type="submit" value="Valider"/></td>
                        </form>
                    </td>
                    <td>
                        <form method="post" action="Page">
                            <input type="hidden" value="<%=r.getId().toString()%>" name="idrr"/>
                            <input type="hidden" value="GestionnaireRefuserRemboursement" name="action"/>
                            <td><input type="submit" value="Refuser"/></td>
                        </form>
                    </td>
                </tr>
            <%}%>
        </table>
    </body>
</html>
