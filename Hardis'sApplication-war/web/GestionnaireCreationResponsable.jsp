<%-- 
    Document   : CreationMorale
    Created on : 24 mars 2020, 10:06:16
    Author     : alexisbaillieu
--%>
<%@page import="entitee.PersonneMorale"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="listepersmo" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="message" scope="request" class="String"></jsp:useBean>
        <%session.setAttribute("titre", "Renseigner Rresponsable");%>
        <%@ include file="Style.jsp"%>
    
    <body>
        <%@ include file="Header.jsp"%>
        <%session.setAttribute("t1", "Rentrer les informations");%>
        <%session.setAttribute("t2", "Créez vous un responsable");%>
        <%@ include file="Header1.jsp"%>
        <form method="post" action="Page">
            <%List<PersonneMorale>lesPersmo=listepersmo;%>
            <fieldset>
                <blockquote class="generic-blockquote">
                    <div class="section-top-border">
                        <div class="rec-xin" id="b">
                            <div class="row">
                                <div class="col-xl-2">
                                </div>
                                <h3 class="mb-30">Informations nouveau Responsable</h3>
                            </div>
                            <div class="row">
                                <div class="col-xl-2">
                                </div>
                                <div class="col-xl-8">
                                    <label for="idpers">Entreprise à laquelle ratacher le responsable<span class="requis">*</span></label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xl-2">
                                </div>
                                <div class="col-xl-8">
                                    <select name="idpers"  class="input-xin-select" style="width: 100%">
                                        <option value ="" >
                                            À choisir
                                        </option>
                                        <%for(PersonneMorale ac: lesPersmo){%>
                                            <option value="<%=ac.getId()%>">
                                                <%=ac.getId()%><%=ac.getRaisonSociale()%>
                                            </option>
                                        <%}%>
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xl-2">
                                </div>
                                <div class="col-xl-2">
                                    <label for="Nom">Nom<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-2">
                                    <label for="Prenom">Prenom<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-2">
                                    <label for="Mail">Mail<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-2">
                                    <label for="Telephone">Telephone<span class="requis">*</span></label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xl-2">
                                </div>
                                <div class="col-xl-2">
                                    <input type="text" class="input-xin" name="Nom" placeholder="Nom"/>
                                </div>
                                <div class="col-xl-2">
                                    <input type="text" class="input-xin" name="Prenom" placeholder="Prenom"/>
                                </div>
                                <div class="col-xl-2">
                                    <input type="text" class="input-xin" name="Mail" placeholder="Mail"/>
                                </div>
                                <div class="col-xl-2">
                                    <input type="text" class="input-xin" name="Telephone" placeholder="Telephone"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xl-4">
                                </div>
                                <div class="col-xl-2">
                                    <input type ="submit" class="info-xin" value="Valider" />
                                </div>
                                <div class="col-xl-2">
                                    <input type="button" class="info-xin" value="Menu" onclick="location.href='Page?action=GestionnaireConnexion'"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </blockquote>             
            </fieldset>
            <input type="hidden" name="action" value="InsererResponsable"/>
        </form>
        <%@ include file="Footer.jsp"%>
    </body>
</html>
