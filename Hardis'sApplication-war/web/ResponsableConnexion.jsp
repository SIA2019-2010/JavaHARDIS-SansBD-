<%-- 
    Document   : AfficherFournisseur
    Created on : 11 nov. 2019, 18:16:33
    Author     : lixin
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>111</title>
    </head>
    <body>
        <h1>Connexion du responsable</h1>
        <p>
            <%
                String attribut = (String) request.getAttribute("message");
                out.println(attribut);
            %>
        </p>
        <form method="post" action="Page">
            <fieldset>
                <legend>Informations Vetement</legend>
                <label for="LoginResponsable">Login<span class="requis">*</span></label>
                <input type="text" name="LoginResponsable" value="" size="20" maxlength="20"/><br/>
                <label for="MDPResponsable">Mot de passe<span class="requis">*</span></label>
                <input type="text" name="MDPResponsable" value="" size="20" maxlength="20"/><br/>
                <input type="hidden" name="action" value="ResponsableAuthen"/>
            </fieldset>
            <input type="submit" value="Valider"/>
            <input type="reset" value="Remettre à zéro"/>
            <input type="button" value="Revenir au Menu" onclick="location.href='Menu?action=vide'"/>
            <%--input type="button" value="test" onclick="location.href='Page?action=ResponsableAuthen&LoginResponsable=login&MDPResponsable=mdp'"/--%>
        </form>
    </body>
</html>
