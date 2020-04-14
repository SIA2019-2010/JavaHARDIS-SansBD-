<%-- 
    Document   : GestionnaireCreationRemboursement
    Created on : 26 mars 2020, 23:25:47
    Author     : lixin
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="entitee.Acte"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="message" scope="request" class="String"></jsp:useBean>
        <jsp:useBean id="ReSS" scope="session" class="String"></jsp:useBean>
        <jsp:useBean id="listeacte" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="Npage" scope="request" class="java.lang.Integer"></jsp:useBean>
        <jsp:useBean id="total" scope="request" class="java.lang.Integer"></jsp:useBean>
        <%
            System.out.println("debut");
            List<Acte> liste=listeacte;
            DateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
        %>
        <%session.setAttribute("titre", "Créer remboursement");%>
        <%@ include file="Style.jsp"%>
    <body>
        <%@ include file="Header.jsp"%>
        <%session.setAttribute("t1", "Liste des contrats");%>
        <%session.setAttribute("t2", "Choisisez un contrat à cloturer");%>
        <%@ include file="Header1.jsp"%>
        <fieldset>
            <section id="about-area" class="section-padding-menu">
                <div class="container">
                    <div class="about-feature-area" >
                        <div class="row" id="b" style="margin-left: 14%;">
                            <form name="formcherche" method="post" action="Page">
                                <div class="row">
                                    <div class="col-xl-6">
                                        <h4>Numéro Sécurité Social à rechercher<br/>(complet ou une parite)</h4>
                                    </div>
                                    <div class="col-xl-6">
                                        <input id="a" type="text" class="input-xin" name="ReSS" placeholder="Numéro SS à rechercher" value="<%=(ReSS.equals("")||ReSS.equals("%")?"":(String)ReSS)%>"/>
                                    </div>
                                    <div class="col-6">    
                                        <input type="hidden" name="action" value="GestionnaireActesNonRembourse"/>
                                        <button class="info-xin" type="submit" style="width:100%;" value="Valider">Rechercher</button>
                                    </div>
                                    <div class="col-6">   
                                        <input class="info-xin" type="button" style="width:100%;" value="Supprimer la filtré" onclick="location.href='Page?action=GestionnaireActesNonRembourse&ReSS='">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="about-feature-area" >
                        <div class='titreTableau'>
                            <h3 class="mb-30">Résultat à afficher</h3>
                        </div>
                        <table class="tableauListe">
                            <thead>
                                <tr>
                                    <th Width="25%">
                                        ID Acte
                                    </th>
                                    <th Width="25%">
                                        Nom
                                    </th>
                                    <th Width="10%">
                                        Prénom
                                    </th>
                                    <th Width="15%">
                                        Numéro Sécurité
                                    </th>
                                    <th Width="15%">
                                        Date Début
                                    </th>
                                    <th Width="25%">
                                        Date Fin
                                    </th>
                                    <th Width="25%">
                                        Dépense
                                    </th>
                                    <th Width="10%">
                                        Valider
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <%for(Acte acte : liste){%>
                                    <tr>
                                        <form name="formvalider" method="post" action="Page">
                                            <td><%=acte.getId()%></td>
                                            <td><%=acte.getLaPersonnePhysique().getNom()%></td>
                                            <td><%=acte.getLaPersonnePhysique().getPrenom()%></td>
                                            <td><%=acte.getLaPersonnePhysique().getNumeroSS()%></td>
                                            <td><%=formatter.format(acte.getDateDebut())%></td>
                                            <td><%=formatter.format(acte.getDateFin())%></td>
                                            <td><%=acte.getDepense()%></td>
                                            <input type="hidden" value="<%=acte.getId().toString()%>" name="idacte"/>
                                            <input type="hidden" value="GestionnaireCreerRemboursement" name="action"/>
                                            <td><input type="submit" class='info-xin' value="Valider"/></td>
                                        </form>
                                    </tr>
                                <%}%>
                            </tbody>
                        </table>
                    </div>
                    <div class="about-feature-area" >
                        <div class="row" id="b"  style="margin-left: 40%; margin-bottom: 5%;">
                            <form name="formgo" method="post" action="Page">
                                <div class="row">
                                    <select class="input-xin-select" name="SPage">                     
                                        <%if(total>0)for(int i=1;i<=total;i++){
                                            if(Npage==i){%>
                                                <option value="<%=i%>" selected="true">
                                                    Page <%=i%> sur <%=total%>
                                                </option>
                                            <%}else{%>
                                                <option value="<%=i%>">
                                                    Page <%=i%> sur <%=total%>
                                                </option>
                                            <%}%>
                                        <%}else{%>
                                            <option value="1" selected="true">
                                                Page 1 sur 1
                                            </option>
                                        <%}%>
                                    </select>
                                    <input type="hidden" name="action" value="GestionnaireActesNonRembourse"/>
                                    <div class="col-cl-2">
                                        <button class="info-xin" style="height: 100%;" type="submit" value="Valider">GO</button>
                                    </div>
                                    <div class="col-cl-2">
                                        <input type="button" class="info-xin" style="height: 100%;" value="Menu" onclick="location.href='Page?action=GestionnaireConnexion'"/>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
        </fieldset>
        <%@ include file="Footer.jsp"%>
    </body>
</html>
