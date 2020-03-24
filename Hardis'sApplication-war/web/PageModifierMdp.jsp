<%-- 
    Document   : PageModifierMdp
    Created on : 23 mars 2020, 23:08:49
    Author     : lixin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:useBean id="typeConnexion" scope="request" class="String"></jsp:useBean>
        <jsp:useBean id="message" scope="request" class="String"></jsp:useBean>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Changement de mot de passe</h1>
        <h1><%=message%><h1>
        <form method="post" action="Page">
            <fieldset>
                <legend>Informations Fournisseur</legend>
                <%
                    if(typeConnexion.equals("GestionnaireConnexion")){
                %>
                        <p>GestionnaireConnexion</p>
                        <input type="hidden" name="action" value="GestionnaireModifierMdp"/>
                <%
                    }else if(typeConnexion.equals("AffilieConnexion")){
                %>
                        <p>AffilieConnexion</p>
                        <input type="hidden" name="action" value="AffilieModifierMdp"/>
                <%
                    }else if(typeConnexion.equals("ResponsableConnexion")){
                %>
                        <p>ResponsableConnexion</p>
                        <input type="hidden" name="action" value="ResponsableModifierMdp"/>
                <%
                    }
                %>
                <label for="OMDP">Ancien mot de passe<span class="requis">*</span></label>
                <input type="password" name="OMDP"/>
                <label for="NMDP">Nouveau mot de passe<span class="requis">*</span></label>
                <input type="password" name="NMDP"/>
                <label for="RMDP">Répétition mot de passe<span class="requis">*</span></label>
                <input type="password" name="RMDP"/>
            </fieldset>
            <input type="submit" value="Valider"/>
            <input type="reset" value="Remettre à zéro"/>
            <input type="button" value="Revenir à l'accueil" onclick="location.href='Page?action=vide'"/>                         
        </form>
    </body>
</html>
