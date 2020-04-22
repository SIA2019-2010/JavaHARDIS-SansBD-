<%-- 
    Document   : PageModifierMdp
    Created on : 23 mars 2020, 23:08:49
    Author     : lixin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="typeConnexion" scope="request" class="String"></jsp:useBean>
        <jsp:useBean id="message" scope="request" class="String"></jsp:useBean>
        <%session.setAttribute("titre", "Modifier Mot de Passe");%>
        <%@ include file="Style.jsp"%>
    <body>
        <%@ include file="Header.jsp"%>
        <%session.setAttribute("t1", "Changement de mot de passe");%>
        <%session.setAttribute("t2", "Renseigner les informations");%>
        <%@ include file="Header1.jsp"%>
        
        
        
        <form method="post" action="Page">
            <fieldset>
                <blockquote class="generic-blockquote">
                    <div class="section-top-border">
                        <div class="rec-xin" id="b">
                            <div class="row">
                                <div class="col-xl-4">
                                </div>
                                <h3 class="mb-30">Changer votre mot de passe</h3>
                            </div>
                            <div class="row">
                                <div class="col-xl-3">
                                </div>
                                <div class="col-xl-3" style="padding-left: 100px; padding-top: 15px;">
                                    <label for="OMDP">Ancien mot de passe<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-2">
                                    <input type="password" class="input-xin" name="OMDP" placeholder="OMDP"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xl-3">
                                </div>
                                <div class="col-xl-3" style="padding-left: 100px; padding-top: 15px;">
                                    <label for="NMDP">Nouveau mot de passe<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-2">
                                    <input type="password" class="input-xin" name="NMDP" placeholder="NMDP"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xl-3">
                                </div>
                                <div class="col-xl-3" style="padding-left: 100px; padding-top: 15px;">
                                    <label for="RMDP">Répétition mot de passe<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-2">
                                    <input type="password" class="input-xin" name="RMDP" placeholder="RMDP"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xl-4" style="margin-right: 25px;">
                                </div>
                                <div class="col-xl-4">
                                    <input type="submit" class="info-xin" value="Valider"/>
                                    <input type="reset" class="info-xin" value="Effacer"/>
                                    <%String n=(typeConnexion.equals("AffilieConnexion")||typeConnexion.equals("ResponsableConnexion")||typeConnexion.equals("GestionnaireConnexion")?typeConnexion:"vide");%>
                                    <input type="button" class="info-xin" value="Menu" onclick="location.href='Page?action=<%=(n)%>'"/>   
                                </div>
                            </div>
                        </div>
                    </div>
                </blockquote>
            </fieldset>
<!--                <label for="OMDP">Ancien mot de passe<span class="requis">*</span></label>
                <input type="password" name="OMDP"/>
                <label for="NMDP">Nouveau mot de passe<span class="requis">*</span></label>
                <input type="password" name="NMDP"/>
                <label for="RMDP">Répétition mot de passe<span class="requis">*</span></label>
                <input type="password" name="RMDP"/>-->
                                
                                
            <%
                if(typeConnexion.equals("GestionnaireConnexion")){
            %>
                    <input type="hidden" name="action" value="GestionnaireModifierMdp"/>
            <%
                }else if(typeConnexion.equals("AffilieConnexion")){
            %>
                    <input type="hidden" name="action" value="AffilieModifierMdp"/>
            <%
                }else if(typeConnexion.equals("ResponsableConnexion")){
            %>
                    <input type="hidden" name="action" value="ResponsableModifierMdp"/>
            <%
                }
            %>                      
        </form>
        <%@ include file="Footer.jsp"%>
    </body>
</html>
