<%-- 
    Document   : Choix
    Created on : 5 nov. 2019, 11:16:31
    Author     : lixin
--%>

<%@page import="entitee.Gestionnaire"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="zxx">
    <%session.setAttribute("titre", "Menu Gestionnaire");%>
    <head>
        <jsp:useBean id="sessiongestionnaire" scope="session" class="Gestionnaire"></jsp:useBean>
        <%@ include file="Style.jsp"%>        

    <body>
        <%@ include file="Header.jsp"%>
        <section id="page-title-area" class="section-padding overlay">
            <div class="container">
                <div class="row">
                    <!-- Page Title Start -->
                    <div class="col-lg-12">
                        <div class="section-title  text-center">
                            <h2>Votre espace Gestionnaire</h2>
                            <span class="title-line"><i class="fa fa-heartbeat"></i></span>
                            <p>Bienvenue <span style="color: #FFFFFF; font-weight: bold"><%out.println(sessiongestionnaire.getPrenom()+" "+sessiongestionnaire.getNom());%></span> sur votre espace personnel</p>
                        </div>
                    </div>
                    <!-- Page Title End -->
                </div>
            </div>
        </section>
        <!--== Page Title Area End ==-->

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
        
        <!--== About Page Content Start ==-->
        <section id="about-area" class="section-padding-menu">
        <div class="container">
            <!-- About Fretutes Start -->
            <div class="about-feature-area">
                <div class="row">
                    <%--
                        <input type="button" value="CreationResponsableInformations" onclick="location.href='Page?action=CreationResponsableInformations'">
                        <input type="button" value="CreationMoraleInformations" onclick="location.href='Page?action=CreationMoraleInformations'">
                        <input type="button" value="GestionnaireAfficherAffilie" onclick="location.href='Page?action=GestionnaireAfficherAffilie'">
                    --%>
                    <!-- Single Fretutes Start -->
                    <div class="col-lg-4" onclick="location.href='Page?action=CreationMoraleInformations'">
                        <div class="about-feature-item" style="width:100%; height: 100%">
                            <i class="fa fa-medkit"></i>
                            <h3>CREER PERSONNE MORALE</h3>
                            <p>Vous permet d'enregistrer une personne morale.</p>
                        </div>
                    </div>
                    
                    <!-- Single Fretutes End -->

                    <!-- Single Fretutes Start -->
                    <div class="col-lg-4" onclick="location.href='Page?action=CreationResponsableInformations'">
                        <div class="about-feature-item" style="width:100%; height: 100%">
                            <i class="fa fa-user-md"></i>
                            <h3>CREER RESPONSABLE</h3>
                            <p>Vous permet d'enregistrer un responsable.</p>
                        </div>
                    </div>
                    <!-- Single Fretutes End -->
                    
                    <!-- Single Fretutes Start -->
                    <div class="col-lg-4" onclick="location.href='Page?action=GestionnaireAfficherAffilie'">
                        <div class="about-feature-item" style="width:100%; height: 100%">
                            <i class="fa fa-heartbeat"></i>
                            <h3>AFFICHER LES AFFILIES</h3>
                            <p>Vous permet d'afficher tous les affiliés.</p>
                        </div>
                    </div>
                    <!-- Single Fretutes End -->
                </div>
                <div class="row">
                    <%--
                        <input type="button" value="GestionnaireCreationRemboursement" onclick="location.href='Page?action=GestionnaireActesNonRembourse'">
                        <input type="button" value="GestionnaireValiderContrat" onclick="location.href='Page?action=GestionnaireValiderContrat'">
                        <input type="button" value="Créer un devis" onclick="location.href='Page?action=CreationDevisInformations'"/>
                    --%>
                    <!-- Single Fretutes End -->
                    <div class="col-lg-4" onclick="location.href='Page?action=GestionnaireActesNonRembourse'">
                        <div class="about-feature-item" style="width:100%; height: 100%">
                            <i class="fa fa-medkit"></i>
                            <h3>CREER REMBOURSEMENT</h3>
                            <p>Vous permet de créer un remboursement.</p>
                        </div>    
                    </div>
                    <!-- Single Fretutes End -->
                    <div class="col-lg-4" onclick="location.href='Page?action=GestionnaireValiderContrat'">
                        <div class="about-feature-item" style="width:100%; height: 100%">
                            <i class="fa fa-user-md"></i>
                            <h3>VALIDER CONTRATS</h3>
                            <p>Vous permet de valider les demandes de contrats.</p>
                        </div>    
                    </div>
                    <!-- Single Fretutes End -->
                    
                    <!-- Single Fretutes Start -->
                    <div class="col-lg-4" onclick="location.href='Page?action=CreationDevisInformations'">
                        <div class="about-feature-item" style="width:100%; height: 100%">
                            <i class="fa fa-heartbeat"></i>
                            <h3>CREER NOUVEAU DEVIS</h3>
                            <p>Vous permet de créer un nouveau devis.</p>
                        </div>
                    </div>
                    <!-- Single Fretutes End -->
                </div>
                <div class="row">
                    <%--
                        <input type="button" value="GestionnaireRempoursementEncours" onclick="location.href='Page?action=GestionnaireRempoursementEncours'">
                        <input type="button" value="GestionnaireCloturerContrat" onclick="location.href='Page?action=GestionnaireCloturerContrat'">
                        <form method="post" action="Page">
                            <input type="hidden" name="action" value="RechercherAffilieDevis"/>
                            <input type="text" name="NumeroSS"/>
                            <button type="submit" value="Rechercher">Connexion</button>
                        </form>
                    --%>
                    <!-- Single Fretutes Start -->
                    <div class="col-lg-4" onclick="location.href='Page?action=GestionnaireRempoursementEncours'">
                        <div class="about-feature-item" style="width:100%; height: 100%">
                            <i class="fa fa-medkit"></i>
                            <h3>TRAITER UN REMBOURSEMENT</h3>
                            <p>Vous permet de valider ou refuser un remboursement.</p>
                        </div>
                    </div>
                    <!-- Single Fretutes End -->
                    <!-- Single Fretutes Start -->
                    <div class="col-lg-4" onclick="location.href='Page?action=GestionnaireCloturerContrat'">
                        <div class="about-feature-item" style="width:100%; height: 100%">
                            <i class="fa fa-user-md"></i>
                            <h3>CLOTURER CONTRATS</h3>
                            <p>Vous permet de clôturer un contrat en cours.</p>
                        </div>
                    </div>
                    <!-- Single Fretutes End -->
                    
                    <!-- Single Fretutes End -->
                    <div class="col-lg-4" onclick="location.href='Page?action=PageCreationProduit'">
                        <div class="about-feature-item" style="width:100%; height: 100%">
                            <i class="fa fa-heartbeat"></i>
                            <h3>CREER UN PRODUIT</h3>
                            <p>Vous permet de créer un produit.</p>
                        </div>
                    </div>
                    <!-- Single Fretutes End -->
                </div>
                
                <div class="row" style="margin-bottom: 50px">
                    <%--
                        <input type="button" value="GestionnaireProduitCollectif" onclick="location.href='Page?action=GestionnaireProduitCollectif'">
                        <input type="button" value="Changer le mot de passe" onclick="location.href='Page?action=GestionnairePageModifierMdp'">
                        <input type="button" value="Deconnexion" onclick="location.href='Page?action=Deconnexion'">
                    --%>
                    <!-- Single Fretutes Start -->
                    <div class="col-lg-4" onclick="location.href='Page?action=GestionnaireProduitCollectif'">
                        <div class="about-feature-item" style="width:100%; height: 100%">
                            <i class="fa fa-medkit"></i>
                            <h3>CREER CONTRAT COLLECTIF</h3>
                            <p>Vous permet de créer un contrat collectif.</p>
                        </div>
                    </div>
                    <!-- Single Fretutes End -->
                    <!-- Single Fretutes Start -->
                    <div class="col-lg-4" onclick="location.href='Page?action=GestionnairePageModifierMdp'">
                        <div class="about-feature-item" style="width:100%; height: 100%">
                            <i class="fa fa-user-md"></i>
                            <h3>MODIFIER MOT DE PASSE</h3>
                            <p>Vous permet de modifier le mot de passe d'un gestionnaire.</p>
                        </div>
                    </div>
                    <!-- Single Fretutes End -->
                    
                    <!-- Single Fretutes End -->
                    <div class="col-lg-4" onclick="location.href='Page?action=Deconnexion&typeConnexion=GestionnaireConnexion'">
                        <div class="about-feature-item" style="width:100%; height: 100%">
                            <i class="fa fa-heartbeat"></i>
                            <h3>DECONNEXION</h3>
                            <p style="margin-bottom: 25px">Merci de votre visite, A bientôt.</p>
                        </div>
                    </div>
                    <!-- Single Fretutes End -->
                </div>
            </div>
            <!-- About Fretutes End -->
        </div>
    </section>
            
    <%@ include file="Footer.jsp"%>
        
    </body>
</html>
