<%-- 
    Document   : AfficherFournisseur
    Created on : 11 nov. 2019, 18:16:33
    Author     : lixin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="typeConnexion" scope="request" class="String"></jsp:useBean>
        <%--<meta http-equiv="x-ua-compatible" content="ie=edge">--%>
        <%--meta http-equiv="Content-Type" content="text/html; charset=UTF-8"--%>
        <script type="text/javascript" src="js.js"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                $('#AffilieConnexion').prop("checked", true);
                $('#<%=typeConnexion%>').prop("checked", true);
            });
        </script>
        <%session.setAttribute("titre", "Connexion");%>
        <%@ include file="Style.jsp"%>
    
    <body>
        <%@ include file="Header.jsp"%>
        <%session.setAttribute("t1", "Connexion");%>
        <%session.setAttribute("t2", "Connectez vous pour accéder à votre espace personnel.");%>
        <%@ include file="Header1.jsp"%>

        <section id="lgoin-page-wrap" class="section-padding">
            <div class="container">
                <div class="row">
                    <div class="col-lg-4 col-md-8 m-auto">
                        <div class="login-page-content">
                            <h3 id="login-form_h3">Bon retour parmi nous!</h3>
                            <div class="login-form" style="padding-top: 10px">
                                
                                
                                <%--// target="_blank"
                                                <input name="action" type="radio" id="GestionnaireConnexion" value="GestionnaireAuthen"/>
                                                <label for="action">Gestionnaire Connexion</label>
                                            </div>
                                            <div class="radioButtonStyle">
                                                <input name="action" type="radio" id="AffilieConnexion" value="AffilieAuthen"/>
                                                <label for="action">Affilie Connexion</label>
                                            </div>
                                            <div class="radioButtonStyle">
                                                <input name="action" type="radio" id="ResponsableConnexion" value="ResponsableAuthen"/>
                                                <label for="action">Responsable Connexion</label><br/>
                                            </div>
                                        </div>
                                        <input type="text" name="Login"/>
                                        <input type="password" name="MDP"/>
                                        <button type="submit" value="Valider">Connexion</button>
                                        <input type="button" value="Revenir à l'accueil" onclick="location.href='Page?action=vide'"/>




                                    </fieldset>
                                </form>
                                --%>
                                
                                <form method="post" action="Page">
                                    <fieldset>
                                        <div class="ensembleBooutonsRadio">
                                            <div class = "radioButtonStyle">
                                                <input class ="RadioButtonStyle" name="action" type="radio" id="GestionnaireConnexion" value="GestionnaireAuthen" />
                                                <label for="action"><span style="color: #FFFFFF; font-weight: bold">Gestionnaire Connexion</span></label>
                                            </div>
                                            <div class="radioButtonStyle">
                                                <input class ="RadioButtonStyle" name="action" type="radio" id="AffilieConnexion" value="AffilieAuthen"/>
                                                <label for="action"><span style="color: #FFFFFF; font-weight: bold">Affilie Connexion</span></label>
                                            </div>
                                            <div class="radioButtonStyle">
                                                <input class ="RadioButtonStyle" name="action" type="radio" id="ResponsableConnexion" value="ResponsableAuthen"/>
                                                <label for="action"><span style="color: #FFFFFF; font-weight: bold">Responsable Connexion</span></label>
                                            </div>
                                        </div>
                                        <div class="username">
                                            <input type="text" name="Login" placeholder="Login">
                                        </div>
                                        <div class="password">
                                            <input type="password" name="MDP" placeholder="Mot de passe">
                                        </div>
                                        <div class="log-btn">
                                            <button type="submit" value="Valider"><i class="fa fa-sign-in"></i>Connexion</button>
                                        </div>
                                        <div class="log-btn">
                                            <input type="button" value="Revenir à l'accueil" onclick="location.href='Page?action=vide'"/>
                                        </div>
                                    </fieldset>
                                </form>
                            </div>
                                
                            <div class="login-menu">
                                    <a href="about.html">A propos</a>
                                    <span>|</span>
                                    <a href="contact.html">Contact</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--== Login Page Content End ==-->
        
        <%@ include file="Footer.jsp"%>
        
        
    </body>
</html>
