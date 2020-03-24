<%-- 
    Document   : Choix
    Created on : 5 nov. 2019, 11:16:31
    Author     : lixin
--%>

<%@page import="entitee.PersonnePhysique"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="zxx">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Espace Agent</title>
    
    <jsp:useBean id="sessionaffilie" scope="session" class="PersonnePhysique"></jsp:useBean>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Menu Affilie</title>
</head>

    <body>
        <header >
        </header>
        <p class="message-attribut">
            <%String attribut=(String)request.getAttribute("message");
            boolean b = attribut.toLowerCase().contains("erreur");
            if (b==true){%>
                <span class="message_erreur">
                    <%out.println(attribut);%>
                </span>
            <%}else{%>
                <span class="message_normal">
                    <%out.println(attribut);%>
                </span>
            <%}%>
                    
        </p>
        <p>
            Bienvenue <%out.println(sessionaffilie.getPrenom()+" "+sessionaffilie.getNom());%> sur votre espace personnel</p>
        
        <input type="button" value="AffilieAfficherRempoursementPers" onclick="location.href='Page?action=AffilieAfficherRempoursementPers'">
        <input type="button" value="AffilieAfficherContrat" onclick="location.href='Page?action=AffilieAfficherContrat'">
        <input type="button" value="Changer le mot de passe" onclick="location.href='Page?action=AffiliePageModifierMdp'">
        <input type="button" value="Changer le mail" onclick="location.href='Page?action=AffilieModifierMailPage'">
        <input type="button" value="Changer l'adresse" onclick="location.href='Page?action=AffilieModifierAdressePage'">
        <input type="button" value="Déconnexion" onclick="location.href='Page?action=Deconnexion&typeConnexion=AffilieConnexion'">
    </body>
</html>
