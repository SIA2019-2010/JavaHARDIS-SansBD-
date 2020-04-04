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
        <jsp:useBean id="ReSS" scope="session" class="String"></jsp:useBean>
        <jsp:useBean id="listeacte" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="Npage" scope="request" class="java.lang.Integer"></jsp:useBean>
        <jsp:useBean id="total" scope="request" class="java.lang.Integer"></jsp:useBean>
        <%
            System.out.println("debut");
            List<Acte> liste=listeacte;
            DateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
        %>
    </head>
    <body>
        <h1><%=message%></h1>
        <h1>Liste actes</h1>
        <form name="formcherche" method="post" action="Page">
            <%if(ReSS.equals("")||ReSS.equals("%")){%><input id="a" type="text" name="ReSS" value="" placeholder="Numéro SS à rechercher"/>
            <%}else{%><input id="a" type="text" name="ReSS" value="<%=ReSS%>"/><%}%>
            <input type="hidden" name="action" value="GestionnaireActesNonRembourse"/>
            <button type="submit" value="Valider">Rechercher</button>
            <input type="button" value="Supprimer la filtré" onclick="location.href='Page?action=GestionnaireActesNonRembourse&ReSS='">
        </form>
        <table>
            <tr>
                <td>id acte</td>
                <td>Date début</td>
                <td>Date fin</td>
                <td>Dépense</td>
                <td>Valider</td>
            </tr>
            <%for(Acte acte : liste){%>
                <tr>
                    <form name="formvalider" method="post" action="Page">
                        <td><%=acte.getId()%></td>
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
            <input type="hidden" name="action" value="GestionnaireActesNonRembourse"/>
            <button type="submit" value="Valider">GO</button>
        </form>
    </body>
</html>
