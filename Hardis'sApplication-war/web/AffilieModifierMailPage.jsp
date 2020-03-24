<%-- 
    Document   : AffilieModifierMailPage
    Created on : 23 mars 2020, 15:05:23
    Author     : alexisbaillieu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP ModificationMail</title>
   </head>
    <body>
        <h1>Changement de Mail</h1>
        <p>
            <%
                String attribut = (String) request.getAttribute("message");
                out.println(attribut);
            %>
        </p>
        <form method="post" action="Page">
            <fieldset>
                <legend>Nouvelle adresse mail  : </legend>
                <label for="NvMailAffilie">eMail<span class="requis">*</span></label>
                <input type="text" name="NvMailAffilie" value="" size="20" maxlength="20"/><br/>
                <input type="hidden" name="action" value="ChangementMail"/>
            </fieldset>
            <input type="submit" value="Valider"/>
            <input type="reset" value="Remettre à zéro"/>
            <input type="button" value="Revenir au Menu" onclick="location.href='Menu?action=vide'"/>
            <%--input type="button" value="test" onclick="location.href='Page?action=AffilieAuthen&LoginAffilie=login&MDPAffilie=mdp'"/--%>
        </form>
    </body>
</html>
