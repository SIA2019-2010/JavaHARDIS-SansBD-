<%-- 
    Document   : GestionnaireCreationRemboursement
    Created on : 26 mars 2020, 23:25:47
    Author     : lixin
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="entitee.Produit"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:useBean id="message" scope="request" class="String"></jsp:useBean>
        <jsp:useBean id="RePr" scope="session" class="String"></jsp:useBean>
        <jsp:useBean id="listepro" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="Npage" scope="request" class="java.lang.Integer"></jsp:useBean>
        <jsp:useBean id="total" scope="request" class="java.lang.Integer"></jsp:useBean>
        <%
            List<Produit> liste=listepro;
            DateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
        %>
    </head>
    <body>
        <h1><%=message%></h1>
        <h1>Liste actes</h1>
        <form name="formcherche" method="post" action="Page">
            <%if(RePr.equals("")||RePr.equals("%")){%><input id="a" type="text" name="RePr" value="" placeholder="Nom de produit"/>
            <%}else{%><input id="a" type="text" name="RePr" value="<%=RePr%>"/><%}%>
            <input type="hidden" name="action" value="GestionnaireProduitCollectif"/>
            <button type="submit" value="Valider">Rechercher</button>
            <input type="button" value="Supprimer la filtré" onclick="location.href='Page?action=GestionnaireProduitCollectif&RePr='">
        </form>
        <table>
            <tr>
                <td>id acte</td>
                <td>Date début</td>
                <td>Date fin</td>
                <td>Dépense</td>
                <td>Valider</td>
            </tr>
            <%for(Produit prod : liste){%>
                <tr>
                    <form name="formvalider" method="post" action="Page">
                        <td><%=prod.getId()%></td>
                        <td><%=prod.getNomProduit()%></td>
                        <td><%=prod.getPrixBase()%></td>
                        <input type="hidden" value="<%=prod.getId().toString()%>" name="idprod"/>
                        <input type="hidden" value="GestionnaireChoixProduitCollectif" name="action"/>
                        <td><input type="submit" value="Valider"/></td>
                    </form>
                </tr>
            <%}%>
        </table>
        <form name="formgo" method="post" action="Page">
            <select name="SPage">                     
                <%if(total>0)for(int i=1;i<=total;i++){
                    if(Npage==i){%>
                        <option value="<%=i%>" selected="true">
                            Page <%=i%> sur <%=total%>
                        </option>
                    <%}else{%>
                        <option value="<%=i%>">
                            Page <%=i%> sur <%=total%>
                        </option>
                    <%}%>
                <%}else{%>
                    <option value="1" selected="true">
                        Page 1 sur 1
                    </option>
                <%}%>
            </select>
            <input type="hidden" name="action" value="GestionnaireProduitCollectif"/>
            <button type="submit" value="Valider">GO</button>
        </form>
    </body>
</html>
