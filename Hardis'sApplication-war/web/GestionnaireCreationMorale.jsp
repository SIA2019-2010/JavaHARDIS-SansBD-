<%-- 
    Document   : CreationMorale
    Created on : 24 mars 2020, 10:06:16
    Author     : alexisbaillieu
--%>
<%@page import="java.util.Collections"%>
<%@page import="entitee.Activite"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="listeact" scope="request" class="java.util.List"></jsp:useBean>
        <%List<Activite>lesActi=listeact;%>
        <%session.setAttribute("titre", "Renseigner Personne Morale");%>
        <%@ include file="Style.jsp"%>
    
    <body>
        <%@ include file="Header.jsp"%>
        <%session.setAttribute("t1", "Renseigner informations");%>
        <%session.setAttribute("t2", "Créez vous une personne morale");%>
        <%@ include file="Header1.jsp"%>
        
        <form method="post" action="Page">
            <fieldset>
                <blockquote class="generic-blockquote">
                    <div class="section-top-border">
                        <div class="rec-xin" id="b">
                            <div class="row">
                                <div class="col-xl-2">
                                </div>
                                <h3 class="mb-30">Informations nouvelle Entreprise</h3>
                            </div>
                            <div class="row">
                                <div class="col-xl-2">
                                </div>
                                <div class="col-xl-9">
                                    <label for="idact">Activité de l'entreprise (Code NAF)<span class="requis">*</span></label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xl-2">
                                </div>
                                <div class="col-xl-9">
                                    <select name="idact" class="input-xin-select" style="width: 100%;">
                                        <option value ="" >
                                            À choisir
                                        </option>
                                        <% for (Activite ac: lesActi){%>
                                            <option value ="<%=ac.getId()%>">
                                                <%=ac.getCodeNAF()+String.join("", Collections.nCopies(10-ac.getCodeNAF().length(), "_"))+ac.getDescription()%>
                                            </option>
                                        <%}%>
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xl-2">
                                </div>
                                <div class="col-xl-3">
                                    <label for="Siret">Siret<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-3">
                                    <label for="RaisonSociale">Raison Sociale<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-3">
                                    <label for="Adresse">Adresse<span class="requis">*</span></label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xl-2">
                                </div>
                                <div class="col-xl-3">
                                    <input type="text" class="input-xin" name="Siret" placeholder="Siret"/>
                                </div>
                                <div class="col-xl-3">
                                    <input type="text" class="input-xin" name="RaisonSociale" placeholder="Raison Sociale"/>
                                </div>
                                <div class="col-xl-3">
                                    <input type="text" class="input-xin" name="Adresse" placeholder="Adresse"/>
                                </div>
                            </div>
                            <div class="row"  id="0">
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
            
            <input type="hidden" name="action" value="InsererMorale"/>
            
        </form>
        <%@ include file="Footer.jsp"%>
    </body>
    
</html>
