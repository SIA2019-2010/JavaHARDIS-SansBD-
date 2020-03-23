<%-- 
    Document   : AffilieModifierAdressePage
    Created on : 23 mars 2020, 15:05:10
    Author     : alexisbaillieu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP ModificationAdresse</title>
   </head>
    <body>
        <h1>Changement d'adresse</h1>
        <p>
            <%
                String attribut = (String) request.getAttribute("message");
                out.println(attribut);
            %>
        </p>
        <form method="post" action="Page">
            <fieldset>
                <legend>Nouvelle adresse : </legend>
                <label for="NvAdresseAffilie">Adresse<span class="requis">*</span></label>
                <input type="text" name="NvAdresseAffilie" value="" size="20" maxlength="20"/><br/>
                <input type="hidden" name="action" value="ChangementAdresse"/>
            </fieldset>
            <input type="submit" value="Valider"/>
            <input type="reset" value="Remettre à zéro"/>
            <input type="button" value="Revenir au Menu" onclick="location.href='Menu?action=vide'"/>
            <%--input type="button" value="test" onclick="location.href='Page?action=AffilieAuthen&LoginAffilie=login&MDPAffilie=mdp'"/--%>
        </form>
    </body>
</html>