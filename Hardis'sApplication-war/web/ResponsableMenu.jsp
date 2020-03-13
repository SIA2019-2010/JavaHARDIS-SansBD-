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
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--=== Favicon ===-->
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />

    <title>Espace Agent</title>

    <!--=== Bootstrap CSS ===-->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <!--=== Slicknav CSS ===-->
    <link href="assets/css/plugins/slicknav.min.css" rel="stylesheet">
    <!--=== Magnific Popup CSS ===-->
    <link href="assets/css/plugins/magnific-popup.css" rel="stylesheet">
    <!--=== Owl Carousel CSS ===-->
    <link href="assets/css/plugins/owl.carousel.min.css" rel="stylesheet">
    <!--=== Gijgo CSS ===-->
    <link href="assets/css/plugins/gijgo.css" rel="stylesheet">
    <!--=== FontAwesome CSS ===-->
    <link href="assets/css/font-awesome.css" rel="stylesheet">
    <!--=== Theme Reset CSS ===-->
    <link href="assets/css/reset.css" rel="stylesheet">
    <!--=== Main Style CSS ===-->
    <link href="style.css" rel="stylesheet">
    <!--=== Responsive CSS ===-->
    <link href="assets/css/responsive.css" rel="stylesheet">


    <!--[if lt IE 9]>
        <script src="//oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="//oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    
        <jsp:useBean id="sessionresponsable" scope="session" class="Responsable"></jsp:useBean>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu Agent</title>
</head>

<body class="loader-active">

    <!--== Preloader Area Start ==-->
    <div class="preloader">
        <div class="preloader-spinner">
            <div class="loader-content">
                <img src="assets/img/preloader.gif" alt="JSOFT">
            </div>
        </div>
    </div>
    <!--== Preloader Area End ==-->

    <!--== Header Area Start ==-->
    <header id="header-area" class="fixed-top">
        <!--== Header Top Start ==-->
        <div id="header-top" class="d-none d-xl-block">
            <div class="container">
                <div class="row">
                    <!--== Single HeaderTop Start ==-->
                    <div class="col-lg-3 text-left">
                        <i class="fa fa-map-marker"></i> Université Jean Moulin Lyon 3
                        </div>
                        <!--== Single HeaderTop End ==-->

                        <!--== Single HeaderTop Start ==-->
                        <div class="col-lg-3 text-center">
                            <i class="fa fa-mobile"></i> +33 4 XX XX XX XX
                        </div>
                        <!--== Single HeaderTop End ==-->

                        <!--== Single HeaderTop Start ==-->
                        <div class="col-lg-3 text-center">
                            <i class="fa fa-clock-o"></i> Lun-Ven 09.00 - 17.00
                    </div>
                    <!--== Single HeaderTop End ==-->

                    <!--== Social Icons Start ==-->
                    <div class="col-lg-3 text-right">
                        <div class="header-social-icons">
                            <a href="#"><i class="fa fa-behance"></i></a>
                            <a href="#"><i class="fa fa-pinterest"></i></a>
                            <a href="#"><i class="fa fa-facebook"></i></a>
                            <a href="#"><i class="fa fa-linkedin"></i></a>
                        </div>
                    </div>
                    <!--== Social Icons End ==-->
                </div>
            </div>
        </div>
        <!--== Header Top End ==-->

        <!--== Header Bottom Start ==-->
        <div id="header-bottom">
            <div class="container">
                <div class="row">
                    <!--== Logo Start ==-->
                    <div class="col-lg-4">
                        <a onclick="location.href='Page?action=vide'" class="logo">
                            <img src="assets/img/logo.png" alt="JSOFT">
                        </a>
                    </div>
                    <!--== Logo End ==-->

                    <!--== Main Menu Start ==-->
                    <div class="col-lg-8 d-none d-xl-block">
                        <nav class="mainmenu alignright">
                            <ul>
                                <li><a class ="ensembleLiens" onclick="location.href='Page'">Accueil</a></li>
                                <li class="active"><a onclick="location.href='Page?action=AgentConnexion'"> Mes actions</a>
                                    <ul>
                                        <li><a class ="ensembleLiens" onclick="location.href='Page?action=AgentCreerVehiculePage'">Enregistrer véhicule</a></li>
                                        <li><a class ="ensembleLiens" onclick="location.href='Page?action=AgentCreerTypeVehiculePage'">Créer type véhicule</a></li>
                                        <li><a class ="ensembleLiens" onclick="location.href='Page?action=AgentCreerRevisionVehicule'">Révisions</a></li>
                                        <li><a class ="ensembleLiens" onclick="location.href='Page?action=AgentAfficherVehiculePourReparation'">Mettre en Réparation</a></li>
                                        <li><a class ="ensembleLiens" onclick="location.href='Page?action=AgentAfficherVehiculeEnReparation'">En Réparation</a></li>
                                        <li><a class ="ensembleLiens" onclick="location.href='Page?action=AgentRelancerLesClients&nb=0'">Relancer les clients </a></li>
                                        <li><a class ="ensembleLiens" onclick="location.href='Page?action=AgentDeclarerLesVehiculesVole&nb=0'">Véhicules volés </a></li>
                                        <li><a class ="ensembleLiens" onclick="location.href='Page?action=AgentListerLesReservationFuture'">Réservations à venir </a></li>
                                        <li><a class ="ensembleLiens" onclick="location.href='Page?action=AgentSaisieDateCA'">Chiffre d'Affaire </a></li>
                                        <li><a class ="ensembleLiens" onclick="location.href='Page?action=AgentSaisieRechercheClientLocation'">Débuter Location</a></li>
                                    </ul>
                                </li>
                                <li><a>Mon espace</a>
                                    <ul>
                                        <li><a class ="ensembleLiens" onclick="location.href='Page?action=AgentModifierMDPPage'">Modifier Mot de passe</a></li>
                                        <li><a class ="ensembleLiens" onclick="location.href='Page?action=Deconnexion&typeConnexion=AgentConnexion'">Déconnexion</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </nav>
                    </div>
                    <!--== Main Menu End ==-->
                </div>
            </div>
        </div>
        <!--== Header Bottom End ==-->
    </header>
    <!--== Header Area End ==-->

    <!--== Page Title Area Start ==-->
    <section id="page-title-area" class="section-padding overlay">
        <div class="container">
            <div class="row">
                <!-- Page Title Start -->
                <div class="col-lg-12">
                    <div class="section-title  text-center">
                        <h2>Votre espace Agent</h2>
                        <span class="title-line"><i class="fa fa-car"></i></span>
                        <p>Bienvenue <span style="color: #ffd000; font-weight: bold"><%out.println(sessionresponsable.getPrenom()+" "+sessionresponsable.getNom());%></span> sur votre espace personnel</p>
                    </div>
                </div>
                <!-- Page Title End -->
            </div>
        </div>
    </section>
    <!--== Page Title Area End ==-->
            
    <p class="message-attribut">
        <%  
            String attribut=(String)request.getAttribute("message");
            boolean b = attribut.toLowerCase().contains("erreur");
            if (b==true){%>
                <span class="message_erreur">
                    <%out.println(attribut);%>
                </span>
            <% } else {%>
                <span class="message_normal">
                    <%out.println(attribut);%>
                </span>
            <% }
        %>
    </p>
    <!--== About Page Content Start ==-->
    <section id="about-area" class="section-padding-menu">
        <div class="container">
            <!-- About Fretutes Start -->
            <div class="about-feature-area">
                <div class="row">
                    <!-- Single Fretutes Start -->
                    <div class="col-lg-4" onclick="location.href='Page?action=AgentCreerVehiculePage'">
                        <div class="about-feature-item">
                            <i class="fa fa-car"></i>
                            <h3>ENREGISTRER UN VEHICULE</h3>
                            <p>Vous permet d'enregistrer un nouveau véhicule dans la base de données.</p>
                        </div>
                    </div>
                    
                    <!-- Single Fretutes End -->

                    <!-- Single Fretutes Start -->
                    <div class="col-lg-4" onclick="location.href='Page?action=AgentCreerTypeVehiculePage'">
                        <div class="about-feature-item">
                            <i class="fa fa-car"></i>
                            <h3>CREER UN TYPE DE VEHICULE</h3>
                            <p>Vous permet d'enregistrer un nouveau type de véhicule dans la base de données.</p>
                        </div>
                    </div>
                    <!-- Single Fretutes End -->
                    
                    <!-- Single Fretutes Start -->
                    <div class="col-lg-4" onclick="location.href='Page?action=AgentCreerRevisionVehicule'">
                        <div class="about-feature-item">
                            <i class="fa fa-car"></i>
                            <h3>RÉVISIONS À VENIR</h3>
                            <p>Lister toutes les révisions des véhicules qui sont à venir</p>
                        </div>
                    </div>
                    <!-- Single Fretutes End -->
                </div>
                <div class="row">
                    <!-- Single Fretutes End -->
                    <div class="col-lg-4" onclick="location.href='Page?action=AgentRelancerLesClients&nb=0'">
                        <div class="about-feature-item">
                            <i class="fa fa-car"></i>
                            <h3>RELANCER LES CLIENTS</h3>
                            <p>Vous permet de relancer les clients.</p>
                        </div>    
                    </div>
                    <!-- Single Fretutes End -->
                    <div class="col-lg-4" onclick="location.href='Page?action=AgentRechercheClientLocation'">
                        <div class="about-feature-item">
                            <i class="fa fa-car"></i>
                            <h3>DEBUTER UNE LOCATION</h3>
                            <p>Vous permet de chercher les informations d'un client pour débuter une location</p>
                        </div>    
                    </div>
                    <!-- Single Fretutes End -->
                    
                    <!-- Single Fretutes Start -->
                    <div class="col-lg-4" onclick="location.href='Page?action=AgentAfficherVehiculePourReparation'">
                        <div class="about-feature-item">
                            <i class="fa fa-car"></i>
                            <h3>ENVOYER DES VÉHICULES EN RÉPARATION</h3>
                            <p>Envoyer les véhicules en panne en réparation</p>
                        </div>
                    </div>
                    <!-- Single Fretutes End -->
                </div>
                <div class="row">
                    <!-- Single Fretutes Start -->
                    <div class="col-lg-4" onclick="location.href='Page?action=AgentAfficherVehiculeEnReparation'">
                        <div class="about-feature-item">
                            <i class="fa fa-car"></i>
                            <h3>VEHICULE EN RÉPARATION</h3>
                            <p>Vous permet de voir la liste des véhicules en réparation.</p>
                        </div>
                    </div>
                    <!-- Single Fretutes End -->
                    <!-- Single Fretutes Start -->
                    <div class="col-lg-4" onclick="location.href='Page?action=AgentDeclarerLesVehiculesVole&nb=0'">
                        <div class="about-feature-item">
                            <i class="fa fa-car"></i>
                            <h3>DECLARER VÉHICULE VOLÉ</h3>
                            <p>Vous permet de déclarer un véhicule comme étant volé.</p>
                        </div>
                    </div>
                    <!-- Single Fretutes End -->
                    
                    <!-- Single Fretutes End -->
                    <div class="col-lg-4" onclick="location.href='Page?action=AgentListerLesReservationFuture'">
                        <div class="about-feature-item">
                            <i class="fa fa-car"></i>
                            <h3>RÉSERVATION À VENIR</h3>
                            <p>Vous permet de voir la liste des réservations à venir.</p>
                        </div>
                    </div>
                    <!-- Single Fretutes End -->
                </div>
                
                <div class="row" style="margin-bottom: 50px">
                    <!-- Single Fretutes Start -->
                    <div class="col-lg-4" onclick="location.href='Page?action=AgentSaisieDateCA'">
                        <div class="about-feature-item">
                            <i class="fa fa-car"></i>
                            <h3>CHIFFRE D'AFFAIRE</h3>
                            <p>Vous permet de voir le chiffre d'affaire réalisé sur une période.</p>
                        </div>
                    </div>
                    <!-- Single Fretutes End -->
                    <!-- Single Fretutes Start -->
                    <div class="col-lg-4" onclick="location.href='Page?action=AgentModifierMDPPage'">
                        <div class="about-feature-item">
                            <i class="fa fa-car"></i>
                            <h3>MODIFIER MOT DE PASSE</h3>
                            <p>Vous permet de modifier votre mot de passe.</p>
                        </div>
                    </div>
                    <!-- Single Fretutes End -->
                    
                    <!-- Single Fretutes End -->
                    <div class="col-lg-4" onclick="location.href='Page?action=Deconnexion&typeConnexion=AgentConnexion'">
                        <div class="about-feature-item">
                            <i class="fa fa-car"></i>
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
    <!--== About Page Content End ==-->
    
    

            

        

    <!--== Footer Area Start ==-->
    <section id="footer-area">
        <!-- Footer Bottom Start -->
        <div class="footer-bottom-area">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer Bottom End -->
    </section>
    <!--== Footer Area End ==-->

    <!--== Scroll Top Area Start ==-->
    <div class="scroll-top">
        <img src="assets/img/scroll-top.png" alt="JSOFT">
    </div>
    <!--== Scroll Top Area End ==-->

    <!--=======================Javascript============================-->
    <!--=== Jquery Min Js ===-->
    <script src="assets/js/jquery-3.2.1.min.js"></script>
    <!--=== Jquery Migrate Min Js ===-->
    <script src="assets/js/jquery-migrate.min.js"></script>
    <!--=== Popper Min Js ===-->
    <script src="assets/js/popper.min.js"></script>
    <!--=== Bootstrap Min Js ===-->
    <script src="assets/js/bootstrap.min.js"></script>
    <!--=== Gijgo Min Js ===-->
    <script src="assets/js/plugins/gijgo.js"></script>
    <!--=== Vegas Min Js ===-->
    <script src="assets/js/plugins/vegas.min.js"></script>
    <!--=== Isotope Min Js ===-->
    <script src="assets/js/plugins/isotope.min.js"></script>
    <!--=== Owl Caousel Min Js ===-->
    <script src="assets/js/plugins/owl.carousel.min.js"></script>
    <!--=== Waypoint Min Js ===-->
    <script src="assets/js/plugins/waypoints.min.js"></script>
    <!--=== CounTotop Min Js ===-->
    <script src="assets/js/plugins/counterup.min.js"></script>
    <!--=== YtPlayer Min Js ===-->
    <script src="assets/js/plugins/mb.YTPlayer.js"></script>
    <!--=== Magnific Popup Min Js ===-->
    <script src="assets/js/plugins/magnific-popup.min.js"></script>
    <!--=== Slicknav Min Js ===-->
    <script src="assets/js/plugins/slicknav.min.js"></script>

    <!--=== Mian Js ===-->
    <script src="assets/js/main.js"></script>
    </body>
</html>
