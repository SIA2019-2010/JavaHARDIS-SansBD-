<%-- 
    Document   : Choix
    Created on : 5 nov. 2019, 11:16:31
    Author     : lixin
--%>

<%@page import="entitee.Gestionnaire"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="zxx">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Espace Agent</title>
    
    <jsp:useBean id="sessiongestionnaire" scope="session" class="Gestionnaire"></jsp:useBean>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Menu Gestionnaire</title>
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
            Bienvenue <%out.println(sessiongestionnaire.getPrenom()+" "+sessiongestionnaire.getNom());%> sur votre espace personnel</p>
        
        <input type="button" value="Creer une nouvelle personne Morale (Entreprise)" onclick="location.href='Page?action=CreationMoraleInformations'">
        <input type="button" value="Creer une nouvelle personne Morale (Entreprise)" onclick="location.href='Page?action=CreationResponsableInformations'">
        <input type="button" value="Changer le mot de passe" onclick="location.href='Page?action=PageModifierMdp'">
        
        <input type="button" value="GestionnaireCreationRemboursement" onclick="location.href='Page?action=GestionnaireActesNonRembourse'">
        <input type="button" value="GestionnaireValiderContrat" onclick="location.href='Page?action=GestionnaireValiderContrat'">
        <input type="button" value="GestionnaireCloturerContrat" onclick="location.href='Page?action=GestionnaireCloturerContrat'">
        <input type="button" value="Changer le mot de passe" onclick="location.href='Page?action=GestionnairePageModifierMdp'">
        <input type="button" value="Déconnexion" onclick="location.href='Page?action=Deconnexion&typeConnexion=GestionnaireConnexion'">
    </body>
</html>
