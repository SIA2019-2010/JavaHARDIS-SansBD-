<%-- 
    Document   : ModifierMDPPage
    Created on : 23 mars 2020, 14:41:57
    Author     : alexisbaillieu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Modification MDP</title>
</head>
    <body>
        <h1>Changement de mot de passe</h1>
        <p>
            <%
                String attribut = (String) request.getAttribute("message");
                out.println(attribut);
            %>
        </p>
        <form method="post" action="Page">
            <fieldset>
                <legend>Nouveau mot de passe : </legend>
                <label for="NvMDP">Mot de passe<span class="requis">*</span></label>
                <input type="text" name="NvMDP" value="" size="20" maxlength="20"/><br/>
                <input type="hidden" name="action" value="ChangementMDP"/>
            </fieldset>
            <input type="submit" value="Valider"/>
            <input type="reset" value="Remettre à zéro"/>
            <input type="button" value="Revenir au Menu" onclick="location.href='Menu?action=vide'"/>
            <%--input type="button" value="test" onclick="location.href='Page?action=AffilieAuthen&LoginAffilie=login&MDPAffilie=mdp'"/--%>
        </form>
    </body>
</html>

