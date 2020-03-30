<%-- 
    Document   : CreationMorale
    Created on : 24 mars 2020, 10:06:16
    Author     : alexisbaillieu
--%>
<%@page import="entitee.Activite"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Creation d'une personne Morale</title>
        <jsp:useBean id="listeact" scope="request" class="java.util.List"></jsp:useBean>
    </head>
    <body>
        <h1>Rentrer les informations </h1>
    </body>
        <form method="post" action="Page">
            <%List<Activite>lesActi=listeact;%>
            <fieldset>
                <legend>Informations nouvelle Entreprise</legend>
                <label for="idact">Activité de l'entreprise (Code NAF)<span class="requis">*</span></label>
                <select name="idact">
                    <% for (Activite ac: lesActi){%>
                        <option value ="<%=ac.getCodeNAF()%>"><%=ac.getId()%><%=ac.getDescription()%></option>
                    <%}%>
                </select>
                <br/>
                <label for ="Siret">Siret<span class ="requis">*</span></label>
                <input type="text" name ="Siret" value ="" size="20" maxlength ="20"/>
                <br/>
                <br/>
                <label for ="RaisonSociale">Raison Sociale<span class ="requis">*</span></label>
                <input type="text" name ="RaisonSociale" value ="" size="20" maxlength ="20"/>
                <br/>
                <br/>
                <label for ="Adresse">Adresse<span class ="requis">*</span></label>
                <input type="text" name ="Adresse" value ="" size="20" maxlength ="20"/>
                <br/>
            </fieldset>
            
            <input type="hidden" name="action" value="InsererMorale"/>
            <input type ="submit" value="Valider" />
            <input type ="reset" value="Remettre à zero" /> <br/>
        </form>
        <td Width=25%><A href="ServMenu?action=retourAgent"> Retour au menu</a></td>              
 </body>
    
</html>
