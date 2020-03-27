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
        <jsp:useBean id="lesPacks" scope="session" class="java.util.List"></jsp:useBean>
        <%--<jsp:useBean id="listeinfos" scope="request" class="java.util.List"></jsp:useBean>--%>
        <title>JSP Page</title>
        <%
            List<Object[]>listepacks=lesPacks;
            //List<Object[]>listep=listeinfos;
            //Object[] pers=(Object[])request.getAttribute("pers");
            //session.setAttribute("pers", pers);
            //session.setAttribute("listeinfos", listeinfos);
            //session.setAttribute("lesPacks", lesPacks);
        %>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p><%=message%></p>
        <table>
            <%for(Object[] pack : listepacks){%>
                <tr>
                    <form method="post" action="Page">
                        <td><%=((Produit)(Array.get(pack,0))).getNomProduit()%></td>
                        <td><%=((Array.get(pack,1)))%></td>
                        <input type="hidden" value="<%=listepacks.indexOf(pack)%>" name="numpack"/>
                        <input type="hidden" value="ChoixPack" name="action"/>
                        <td><input type="submit" value="Valider"/></td>
                    </form>
                </tr>
            <%}%>
        </table>
    </body>
</html>
