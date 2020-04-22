<%-- 
    Document   : Acceuil
    Created on : 12 mars 2020, 19:11:49
    Author     : lixin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="zxx">
    <%session.setAttribute("titre", "Acceuil");%>
    <head>
        <%@ include file="Style.jsp"%>
    <body>
        <%@ include file="Header.jsp"%>
        <%--<h1>Hello World!</h1>
        <input type="button" value="Connexion" onclick="location.href='Page?action=AffilieConnexion'"/>
        <input type="button" value="Créer un devis" onclick="location.href='Page?action=CreationDevisInformations'"/>
        <input type="button" value="Créer un devis" onclick="window.open('Page?action=CreationDevisInformations')"/>--%>
        
        <!-- slider_area_start -->
        <div class="slider_area">
            <div class="slider_active owl-carousel">
                <%--<div class="single_slider  d-flex align-items-center slider_bg_2">
                    <div class="container">
                        <div class="row">
                            <div class="col-xl-12">
                                <div class="slider_text ">
                                    <h3> <span>Health care</span> <br>
                                        For Hole Family </h3>
                                    <p>In healthcare sector, service excellence is the facility of <br> the hospital as
                                        healthcare service provider to consistently.</p>
                                    <a href="#" class="boxed-btn3">Check Our Services</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>--%>
                <div class="single_slider  d-flex align-items-center slider_bg_1">
                    <div class="container">
                        <div class="row">
                            <div class="col-xl-12">
                                <div class="slider_text ">
                                    <h3> <span>Health care</span> <br>
                                        For Hole Family </h3>
                                    <p>In healthcare sector, service excellence is the facility of <br> the hospital as
                                        healthcare service provider to consistently.</p>
                                    <a href="Page?action=CreationDevisInformations" class="xin-blue-white">Créer un devis</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="single_slider  d-flex align-items-center slider_bg_2">
                    <div class="container">
                        <div class="row">
                            <div class="col-xl-12">
                                <div class="slider_text ">
                                    <h3> <span>Health care</span> <br>
                                        For Hole Family </h3>
                                    <p>In healthcare sector, service excellence is the facility of <br> the hospital as
                                        healthcare service provider to consistently.</p>
                                    <a href="Page?action=CreationDevisInformations" class="xin-blue-white">Créer un devis</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- slider_area_end -->

        <!-- service_area_start -->
        <div class="service_area">
            <div class="container p-0">
                <div class="row no-gutters">
                    <div class="col-xl-4 col-md-4">
                        <div class="single_service">
                            <div class="icon">
                                <i class="flaticon-first-aid-kit"></i>
                            </div>
                            <br/><h3>Gestionnaire</h3>
                            <br/><br/>
                            <a href="Page?action=GestionnaireConnexion" class="boxed-btn3-white">Connexion</a>
                        </div>
                    </div>
                    <div class="col-xl-4 col-md-4">
                        <div class="single_service">
                            <div class="icon">
                                <i class="flaticon-emergency-call"></i>
                            </div>
                            <br/><h3>Affilié</h3>
                            <br/><br/>
                            <a href="Page?action=AffilieConnexion" class="boxed-btn3-white">Connexion</a>
                        </div>
                    </div>
                    <div class="col-xl-4 col-md-4">
                        <div class="single_service">
                            <div class="icon">
                                <i class="flaticon-electrocardiogram"></i>
                            </div>
                            <br/><h3>Responsable</h3>
                            <br/><br/>
                            <a href="Page?action=ResponsableConnexion" class="boxed-btn3-white">Connexion</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- service_area_end -->

        <!-- welcome_docmed_area_start -->
        <div class="welcome_docmed_area">
            <div class="container">
                <div class="row">
                    <div class="col-xl-6 col-lg-6">
                        <div class="welcome_thumb">
                            <div class="thumb_1">
                                <img src="img/about/1.png" alt="">
                            </div>
                            <div class="thumb_2">
                                <img src="img/about/2.png" alt="">
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-6 col-lg-6">
                        <div class="welcome_docmed_info">
                            <h2>Welcomeeu to Docmed</h2>
                            <h3>Best Care For Your <br>
                                    Good Health</h3>
                            <p>Esteem spirit temper too say adieus who direct esteem.
                                    It esteems luckily or picture placing drawing. Apartments frequently or motionless on reasonable projecting expression.</p>
                            <ul>
                                <li> <i class="flaticon-right"></i> Apartments frequently or motionless. </li>
                                <li> <i class="flaticon-right"></i> Duis aute irure dolor in reprehenderit in voluptate.</li>
                                <li> <i class="flaticon-right"></i> Voluptatem quia voluptas sit aspernatur. </li>
                            </ul>
                            <a href="#" class="boxed-btn3-white-2">Learn More</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- welcome_docmed_area_end -->

        <!-- offers_area_start -->
        <div class="our_department_area">
            <div class="container">
                <div class="row">
                    <div class="col-xl-12">
                        <div class="section_title text-center mb-55">
                            <h3>Our Departments</h3>
                            <p>Esteem spirit temper too say adieus who direct esteem. <br>
                                It esteems luckily or picture placing drawing. </p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xl-4 col-md-6 col-lg-4">
                        <div class="single_department">
                            <div class="department_thumb">
                                <img src="img/department/1.png" alt="">
                            </div>
                            <div class="department_content">
                                <h3><a href="#">Eye Care</a></h3>
                                <p>Esteem spirit temper too say adieus who direct esteem.</p>
                                <a href="#" class="learn_more">Learn More</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-4 col-md-6 col-lg-4">
                        <div class="single_department">
                            <div class="department_thumb">
                                <img src="img/department/2.png" alt="">
                            </div>
                            <div class="department_content">
                                <h3><a href="#">Physical Therapy</a></h3>
                                <p>Esteem spirit temper too say adieus who direct esteem.</p>
                                <a href="#" class="learn_more">Learn More</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-4 col-md-6 col-lg-4">
                        <div class="single_department">
                            <div class="department_thumb">
                                <img src="img/department/3.png" alt="">
                            </div>
                            <div class="department_content">
                                <h3><a href="#">Dental Care</a></h3>
                                <p>Esteem spirit temper too say adieus who direct esteem.</p>
                                <a href="#" class="learn_more">Learn More</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-4 col-md-6 col-lg-4">
                        <div class="single_department">
                            <div class="department_thumb">
                                <img src="img/department/4.png" alt="">
                            </div>
                            <div class="department_content">
                                <h3><a href="#">Diagnostic Test</a></h3>
                                <p>Esteem spirit temper too say adieus who direct esteem.</p>
                                <a href="#" class="learn_more">Learn More</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-4 col-md-6 col-lg-4">
                        <div class="single_department">
                            <div class="department_thumb">
                                <img src="img/department/5.png" alt="">
                            </div>
                            <div class="department_content">
                                <h3><a href="#">Skin Surgery</a></h3>
                                <p>Esteem spirit temper too say adieus who direct esteem.</p>
                                <a href="#" class="learn_more">Learn More</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-4 col-md-6 col-lg-4">
                        <div class="single_department">
                            <div class="department_thumb">
                                <img src="img/department/6.png" alt="">
                            </div>
                            <div class="department_content">
                                <h3><a href="#">Surgery Service</a></h3>
                                <p>Esteem spirit temper too say adieus who direct esteem.</p>
                                <a href="#" class="learn_more">Learn More</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- offers_area_end -->

        <!-- testmonial_area_start -->
        <div class="testmonial_area">
            <div class="testmonial_active owl-carousel">
                <div class="single-testmonial testmonial_bg_1 overlay2">
                    <div class="container">
                        <div class="row">
                            <div class="col-xl-10 offset-xl-1">
                                <div class="testmonial_info text-center">
                                    <div class="quote">
                                        <i class="flaticon-straight-quotes"></i>
                                    </div>
                                    <p>Donec imperdiet congue orci consequat mattis. Donec rutrum porttitor <br>
                                        sollicitudin. Pellentesque id dolor tempor sapien feugiat ultrices nec sed neque.
                                        <br>
                                        Fusce ac mattis nulla. Morbi eget ornare dui. </p>
                                    <div class="testmonial_author">
                                        <h4>Asana Korim</h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="single-testmonial testmonial_bg_2 overlay2">
                    <div class="container">
                        <div class="row">
                            <div class="col-xl-10 offset-xl-1">
                                <div class="testmonial_info text-center">
                                    <div class="quote">
                                        <i class="flaticon-straight-quotes"></i>
                                    </div>
                                    <p>Donec imperdiet congue orci consequat mattis. Donec rutrum porttitor <br>
                                        sollicitudin. Pellentesque id dolor tempor sapien feugiat ultrices nec sed neque.
                                        <br>
                                        Fusce ac mattis nulla. Morbi eget ornare dui. </p>
                                    <div class="testmonial_author">
                                        <h4>Asana Korim</h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- testmonial_area_end -->

        <!-- business_expert_area_start  -->
        <div class="business_expert_area">
            <div class="business_tabs_area">
                <div class="container">
                    <div class="row">
                        <div class="col-xl-12">
                            <ul class="nav" id="myTab" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home"
                                aria-selected="true">Excellent Services</a>
                                </li>

                                <li class="nav-item">
                                    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile"
                                aria-selected="false">Qualified Doctors</a>
                                </li>


                                <li class="nav-item">
                                    <a class="nav-link" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact"
                                aria-selected="false">Emergency Departments</a>
                                </li>
                            </ul>
                        </div>
                    </div>

                </div>
            </div>
            <div class="container">
                <div class="border_bottom">
                        <div class="tab-content" id="myTabContent">
                                <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">

                                        <div class="row align-items-center">
                                                <div class="col-xl-6 col-md-6">
                                                    <div class="business_info">
                                                        <div class="icon">
                                                            <i class="flaticon-first-aid-kit"></i>
                                                        </div>
                                                        <h3>Leading edge care for Your family</h3>
                                                        <p>Esteem spirit temper too say adieus who direct esteem.
                                                            It esteems luckily picture placing drawing. Apartments frequently or motionless on
                                                            reasonable projecting expression.
                                                        </p>
                                                    </div>
                                                </div>
                                                <div class="col-xl-6 col-md-6">
                                                    <div class="business_thumb">
                                                        <img src="img/about/business.png" alt="">
                                                    </div>
                                                </div>
                                            </div>
                                </div>
                                <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                                        <div class="row align-items-center">
                                                <div class="col-xl-6 col-md-6">
                                                    <div class="business_info">
                                                        <div class="icon">
                                                            <i class="flaticon-first-aid-kit"></i>
                                                        </div>
                                                        <h3>Leading edge care for Your family</h3>
                                                        <p>Esteem spirit temper too say adieus who direct esteem.
                                                            It esteems luckily picture placing drawing. Apartments frequently or motionless on
                                                            reasonable projecting expression.
                                                        </p>
                                                    </div>
                                                </div>
                                                <div class="col-xl-6 col-md-6">
                                                    <div class="business_thumb">
                                                        <img src="img/about/business.png" alt="">
                                                    </div>
                                                </div>
                                            </div>
                                </div>
                                <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">
                                        <div class="row align-items-center">
                                                <div class="col-xl-6 col-md-6">
                                                    <div class="business_info">
                                                        <div class="icon">
                                                            <i class="flaticon-first-aid-kit"></i>
                                                        </div>
                                                        <h3>Leading edge care for Your family</h3>
                                                        <p>Esteem spirit temper too say adieus who direct esteem.
                                                            It esteems luckily picture placing drawing. Apartments frequently or motionless on
                                                            reasonable projecting expression.
                                                        </p>
                                                    </div>
                                                </div>
                                                <div class="col-xl-6 col-md-6">
                                                    <div class="business_thumb">
                                                        <img src="img/about/business.png" alt="">
                                                    </div>
                                                </div>
                                            </div>
                                </div>
                              </div>
                </div>
            </div>
        </div>
        <!-- business_expert_area_end  -->


        <!-- expert_doctors_area_start -->
        <div class="expert_doctors_area">
            <div class="container">
                <div class="row">
                    <div class="col-xl-12">
                        <div class="doctors_title mb-55">
                            <h3>Experts Techniques</h3>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xl-12">
                        <div class="expert_active owl-carousel">
                            <div class="single_expert">
                                <div class="expert_thumb">
                                    <img src="img/experts/1.jpg" alt="">
                                </div>
                                <div class="experts_name text-center">
                                    <h3>Loubna Tahri</h3>
                                    <span>Directrice</span>
                                </div>
                            </div>
                            <div class="single_expert">
                                <div class="expert_thumb">
                                    <img src="img/experts/2.jpg" alt="">
                                </div>
                                <div class="experts_name text-center">
                                    <h3>Suzanna Chelian</h3>
                                    <span>Fonctionnel</span>
                                </div>
                            </div>
                            <div class="single_expert">
                                <div class="expert_thumb">
                                    <img src="img/experts/3.jpg" alt="">
                                </div>
                                <div class="experts_name text-center">
                                    <h3>Alexis Baillieu</h3>
                                    <span>Back</span>
                                </div>
                            </div>
                            <div class="single_expert">
                                <div class="expert_thumb">
                                    <img src="img/experts/4.jpg" alt="">
                                </div>
                                <div class="experts_name text-center">
                                    <h3>Claire Lawniczak</h3>
                                    <span>BI</span>
                                </div>
                            </div>
                            <div class="single_expert">
                                <div class="expert_thumb">
                                    <img src="img/experts/5.jpg" alt="">
                                </div>
                                <div class="experts_name text-center">
                                    <h3>Xin Li</h3>
                                    <span>Front</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- expert_doctors_area_end -->

        <%@ include file="Footer.jsp"%>
    </body>
</html>
