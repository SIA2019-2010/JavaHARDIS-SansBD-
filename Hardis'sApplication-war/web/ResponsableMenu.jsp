<%-- 
    Document   : Choix
    Created on : 5 nov. 2019, 11:16:31
    Author     : lixin
--%>

<%@page import="entitee.Responsable"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="zxx">

    <head>
        <%session.setAttribute("titre", "Menu Responsable");%>
        <jsp:useBean id="sessionresponsable" scope="session" class="Responsable"></jsp:useBean>
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
                            <p>Bienvenue <span style="color: #FFFFFF; font-weight: bold"><%out.println(sessionresponsable.getPrenom()+" "+sessionresponsable.getNom());%></span> sur votre espace personnel</p>
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
                            <input type="button" value="Changer le mot de passe" onclick="location.href='Page?action=ResponsablePageModifierMdp'">
                            <input type="button" value="ResponsableAfficherListePersonnePhique" onclick="location.href='Page?action=ResponsableAfficherListePersonnePhique'">
                            <input type="button" value="Déconnexion" onclick="location.href='Page?action=Deconnexion&typeConnexion=ResponsableConnexion'">
                        --%>
                        <!-- Single Fretutes Start -->
                        <div class="col-lg-4" onclick="location.href='Page?action=ResponsableAfficherListePersonnePhique'">
                            <div class="about-feature-item" style="width:100%; height: 100%">
                                <i class="fa fa-medkit"></i>
                                <h3>AFFICHER LES PERSONNES</h3>
                                <p>Vous permet d'afficher la liste de nos affilés et prospects.</p>
                            </div>
                        </div>

                        <!-- Single Fretutes End -->

                        <!-- Single Fretutes Start -->
                        <div class="col-lg-4" onclick="location.href='Page?action=ResponsablePageModifierMdp'">
                            <div class="about-feature-item" style="width:100%; height: 100%">
                                <i class="fa fa-user-md"></i>
                                <h3>MODIFIER MOT DE PASSE</h3>
                                <p>Vous permet de modifier le mot de passe d'un responsable.</p>
                            </div>
                        </div>
                        <!-- Single Fretutes End -->

                        <!-- Single Fretutes Start -->
                        <div class="col-lg-4" onclick="location.href='Page?action=Deconnexion&typeConnexion=ResponsableConnexion'">
                            <div class="about-feature-item" style="width:100%; height: 100%">
                                <i class="fa fa-heartbeat"></i>
                                <h3>DECONNEXION</h3>
                                <p>Merci de votre visite, A bientôt.</p>
                            </div>
                        </div>
                        <!-- Single Fretutes End -->
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
