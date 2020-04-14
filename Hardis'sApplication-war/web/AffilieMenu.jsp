<%-- 
    Document   : Choix
    Created on : 5 nov. 2019, 11:16:31
    Author     : lixin
--%>

<%@page import="entitee.PersonnePhysique"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="zxx">
    <%session.setAttribute("titre", "Menu Affilié");%>
    <head>
        <jsp:useBean id="sessionaffilie" scope="session" class="PersonnePhysique"></jsp:useBean>
        <%@ include file="Style.jsp"%>

    <body>
        <%@ include file="Header.jsp"%>
        <section id="page-title-area" class="section-padding overlay">
            <div class="container">
                <div class="row">
                    <!-- Page Title Start -->
                    <div class="col-lg-12">
                        <div class="section-title  text-center">
                            <h2>Votre espace Affilié</h2>
                            <span class="title-line"><i class="fa fa-heartbeat"></i></span>
                            <p>Bienvenue <span style="color: #FFFFFF; font-weight: bold"><%out.println(sessionaffilie.getPrenom()+" "+sessionaffilie.getNom());%></span> sur votre espace personnel</p>
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
                            <input type="button" value="AffilieAfficherRempoursementPers" onclick="location.href='Page?action=AffilieAfficherRempoursementPers'">
                            <input type="button" value="AffilieAfficherContrat" onclick="location.href='Page?action=AffilieAfficherContrat'">
                            <input type="button" value="Changer le mail" onclick="location.href='Page?action=AffilieModifierMailPage'">
                        --%>
                        <!-- Single Fretutes Start -->
                        <div class="col-lg-4" onclick="location.href='Page?action=AffilieAfficherRempoursementPers'">
                            <div class="about-feature-item" style="width:100%; height: 100%">
                                <i class="fa fa-medkit"></i>
                                <h3>AFFICHER REMBOURSEMENTS</h3>
                                <p>Vous permet de consulter les remboursements des affiliés en temps réel.</p>
                            </div>
                        </div>

                        <!-- Single Fretutes End -->

                        <!-- Single Fretutes Start -->
                        <div class="col-lg-4" onclick="location.href='Page?action=AffilieAfficherContrat'">
                            <div class="about-feature-item" style="width:100%; height: 100%">
                                <i class="fa fa-user-md"></i>
                                <h3>AFFICHER CONTRATS</h3>
                                <p>Vous permet de consulter les contrats en cours et passés de nos affiliés.</p>
                            </div>
                        </div>
                        <!-- Single Fretutes End -->

                        <!-- Single Fretutes Start -->
                        <div class="col-lg-4" onclick="location.href='Page?action=AffilieModifierMailPage'">
                            <div class="about-feature-item" style="width:100%; height: 100%">
                                <i class="fa fa-heartbeat"></i>
                                <h3>MODIFIER MAIL</h3>
                                <p>Vous permet de modifier le mail d'un affilié.</p>
                            </div>
                        </div>
                        <!-- Single Fretutes End -->
                    </div>

                    <div class="row" style="margin-bottom: 50px">
                        <%--
                            <input type="button" value="Changer l'adresse" onclick="location.href='Page?action=AffilieModifierAdressePage'">
                            <input type="button" value="Changer le mot de passe" onclick="location.href='Page?action=AffiliePageModifierMdp'">
                            <input type="button" value="Déconnexion" onclick="location.href='Page?action=Deconnexion&typeConnexion=AffilieConnexion'">
                        --%>
                        <!-- Single Fretutes Start -->
                        <div class="col-lg-4" onclick="location.href='Page?action=AffilieModifierAdressePage'">
                            <div class="about-feature-item" style="width:100%; height: 100%">
                                <i class="fa fa-medkit"></i>
                                <h3>MODIFIER ADRESSE</h3>
                                <p>Vous permet de modifier l'adresse d'un affilié.</p>
                            </div>
                        </div>
                        <!-- Single Fretutes End -->
                        <!-- Single Fretutes Start -->
                        <div class="col-lg-4" onclick="location.href='Page?action=AffiliePageModifierMdp'">
                            <div class="about-feature-item" style="width:100%; height: 100%">
                                <i class="fa fa-user-md"></i>
                                <h3>MODIFIER MOT DE PASSE</h3>
                                <p>Vous permet de modifier le mot de passe d'un affilié.</p>
                            </div>
                        </div>
                        <!-- Single Fretutes End -->

                        <!-- Single Fretutes End -->
                        <div class="col-lg-4" onclick="location.href='Page?action=Deconnexion&typeConnexion=AffilieConnexion'">
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
