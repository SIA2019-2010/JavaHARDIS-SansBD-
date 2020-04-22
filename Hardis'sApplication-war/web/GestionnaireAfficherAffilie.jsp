<%-- 
    Document   : GestionnaireAfficherAffilie
    Created on : 30 mars 2020, 01:13:36
    Author     : lixin
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.Format"%>
<%@page import="entitee.PersonnePhysique"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:useBean id="ReSS" scope="session" class="String"></jsp:useBean>
        <jsp:useBean id="message" scope="request" class="String"></jsp:useBean>
        <jsp:useBean id="listepers" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="Npage" scope="request" class="java.lang.Integer"></jsp:useBean>
        <jsp:useBean id="total" scope="request" class="java.lang.Integer"></jsp:useBean>
        <%
            List<PersonnePhysique>liste=listepers;
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        %>
        <%session.setAttribute("titre", "Liste Affiliés");%>
        <%@ include file="Style.jsp"%>
    <body>
        <%@ include file="Header.jsp"%>
        <%session.setAttribute("t1", "Liste des contrats");%>
        <%session.setAttribute("t2", "Choisisez un contrat à afficher");%>
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
                                        <input id="a" type="text" class="input-xin" name="ReSS" placeholder="Numéro SS à rechercher" value="<%=("".equals(ReSS)||"%".equals(ReSS)?"":(String)ReSS)%>"/>
                                    </div>
                                    <div class="col-6">    
                                        <input type="hidden" name="action" value="GestionnaireAfficherAffilie"/>
                                        <button class="info-xin" type="submit" style="width:100%;" value="Valider">Rechercher</button>
                                    </div>
                                    <div class="col-6">   
                                        <input class="info-xin" type="button" style="width:100%;" value="Supprimer le filtre" onclick="location.href='Page?action=GestionnaireAfficherAffilie&ReSS='">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="about-feature-area">
                        <div class='titreTableau'>
                            <h3 class="mb-30">Résultat à afficher</h3>
                        </div>
                        <table class="tableauListe">
                            <thead>
                                <tr>
                                    <th Width="25%">
                                        Nom
                                    </th>
                                    <th Width="10%">
                                        Prénom
                                    </th>
                                    <th Width="15%">
                                        Numéro Sécurité
                                    </th>
                                    <th>
                                        Date de naissance
                                    </th>
                                    <th>
                                        Genre
                                    </th>
                                    <th Width="10%">
                                        info
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <%for(PersonnePhysique p:liste){%>
                                    <form method="post" action="Page">
                                        <tr>
                                            <td><%=p.getNom()%></td>
                                            <td><%=p.getPrenom()%></td>
                                            <td><%=p.getNumeroSS()%></td>
                                            <td><%=(p.getDateNaiss()==null?"":formatter.format(p.getDateNaiss()))%></td>
                                            <td><%=(p.getGenre()==null?"":p.getGenre().toString())%></td>
                                            <input type="hidden" name="idp" value="<%=p.getNumeroSS()%>"/>
                                            <input type="hidden" name="action" value="ContratListePersonnes"/>
                                            <td><input type ="submit" class='info-xin' value="Afficher" /></td>
                                        </tr>
                                    </form>
                                <%}%>
                            </tbody>
                        </table>
                    </div>
                            
                    <div class="about-feature-area" >
                        <div class="row" id="b"  style="margin-left: 40%; margin-bottom: 5%;">
                            <form name="formgo" method="post" action="Page">
                                <div class="row">
                                    <div class="col-cl-4">
                                        <select class="input-xin-select" name="SPage">                     
                                            <%if(total>0)for(int i=1;i<=total;i++){
                                                if(Npage==i){%>
                                                    <option  value="<%=i%>" selected="true">
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
                                    </div>
                                    <input type="hidden" name="action" value="GestionnaireAfficherAffilie"/>
                                    <div class="col-cl-4">
                                        <button class="info-xin" style="height: 100%;" type="submit" value="Valider">GO</button>
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
