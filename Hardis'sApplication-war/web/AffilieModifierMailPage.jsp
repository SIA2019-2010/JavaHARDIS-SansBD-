<%-- 
    Document   : AffilieModifierAdressePage
    Created on : 23 mars 2020, 15:05:10
    Author     : alexisbaillieu
--%>

<%@page import="entitee.PersonnePhysique"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%session.setAttribute("titre", "Modifier Mail");%>
        <%@ include file="Style.jsp"%>        
    <body>
        <%@ include file="Header.jsp"%>
        <%session.setAttribute("t1", "Changement du mail");%>
        <%session.setAttribute("t2", "Changez votre mail.");%>
        <%@ include file="Header1.jsp"%>
        
        <form method="post" action="Page">
            <fieldset>
                <blockquote class="generic-blockquote">
                    <div class="section-top-border">
                        <div class="rec-xin" id="b">
                            <div class="row">
                                <div class="col-xl-4" style="margin-right: 60px">
                                </div>
                                <h3 class="mb-30">Changer votre mail</h3>
                            </div>
                            <div class="row">
                                <div class="col-xl-3">
                                </div>
                                <div class="col-xl-3" style="padding-left: 100px; padding-top: 15px;">
                                    <label for="mail">Votre mail actuel</label>
                                </div>
                                <div class="col-xl-2">
                                    <input type="text" class="input-xin" STYLE="background-color:#CCCCCC;" name="mail" value="<%=((PersonnePhysique)(session.getAttribute("sessionaffilie"))).getMail()%>" readonly/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xl-3">
                                </div>
                                <div class="col-xl-3" style="padding-left: 100px; padding-top: 15px;">
                                    <label for="NvMailAffilie">Votre nouveau mail<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-2">
                                    <input type="test" class="input-xin" name="NvMailAffilie" placeholder="Nouvelle mail"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xl-4" style="margin-right: 35px;">
                                </div>
                                <div class="col-xl-4">
                                    <input type="submit" class="info-xin" value="Valider"/>
                                    <input type="reset" class="info-xin" value="Effacer"/>
                                    <input type="button" class="info-xin" value="Menu" onclick="location.href='Page?action=AffilieConnexion'"/>   
                                </div>
                            </div>
                        </div>
                    </div>
                </blockquote>
            </fieldset>
<!--            <input type="submit" value="Valider"/>
            <input type="reset" value="Remettre à zéro"/>
            <input type="button" value="Revenir au Menu" onclick="location.href='Menu?action=vide'"/>-->
            <%--input type="button" value="test" onclick="location.href='Page?action=AffilieAuthen&LoginAffilie=login&MDPAffilie=mdp'"/--%>
            <input type="hidden" name="action" value="AffilieModifierMail"/>
        </form>
        <%@ include file="Footer.jsp"%>
    </body>
</html>