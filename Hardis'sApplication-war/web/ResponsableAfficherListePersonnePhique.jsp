<%-- 
    Document   : ResponsableAfficherListePersonnePhique
    Created on : 20 mars 2020, 00:14:26
    Author     : lixin
--%>

<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="entitee.Contrat"%>
<%@page import="entitee.Produit"%>
<%@page import="entitee.StatutBeneficiaire"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:useBean id="listestatut" scope="request" class="java.util.List"></jsp:useBean>
        <%
            List<StatutBeneficiaire>liste=listestatut;
            Contrat c0=liste.get(0).getLeContrat();
            Produit p0=c0.getLeProduit();
            DateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
        %>
        <%session.setAttribute("titre", "Liste Personne");%>
        <%@ include file="Style.jsp"%>
    <body>
        <p><%=listestatut.size()%><p>
         <%@ include file="Header.jsp"%>
        <%session.setAttribute("t1", "Liste des remboursements");%>
        <%session.setAttribute("t2", "Choisisez un remboursement à traiter");%>
        <%@ include file="Header1.jsp"%>
        <fieldset>
            <section id="about-area" class="section-padding-menu">
                <div class="container">
                    <div class="about-feature-area" >
                        <div class="titreTableau" id="b">
                            <div class='titreTableau'>
                                <h1 class="mb-30"><%=p0.getNomProduit()%></h1>
                            </div>
                            <div class='titreTableau'>
                                <h3 class="mb-30"><%="Contrat ID. "+c0.getId()%></h3>
                            </div>
                            <table class="tableauListe" style="margin-bottom: 30px;">
                                <thead>
                                    <tr>
                                        <th Width="25%">
                                            Nom
                                        </th>
                                        <th Width="15%">
                                            Prénom
                                        </th>
                                        <th Width="15%">
                                            Numéro Sécurité
                                        </th>
                                        <th Width="25%">
                                            Date de Naissance
                                        </th>
                                        <th Width="10%">
                                            Bénéficiaire
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>   
                                    <%for(StatutBeneficiaire statut : liste){
                                        Contrat c1=statut.getLeContrat();
                                        Produit p1=c1.getLeProduit();
                                        if(!p1.equals(p0)){
                                            p0=p1;
                                            c0=c1;%>
                                            </tbody>
                                        </table>
                                            <div class='titreTableau'>
                                                <h1 class="mb-30"><%=p0.getNomProduit()%></h1>
                                            </div>
                                            <div class='titreTableau'>
                                                <h3 class="mb-30"><%="Contrat ID. "+c0.getId()%></h3>
                                            </div>
                                            <table class="tableauListe" style="margin-bottom: 30px;">
                                                <thead>
                                                    <tr>
                                                        <th Width="25%">
                                                            Nom
                                                        </th>
                                                        <th Width="15%">
                                                            Prénom
                                                        </th>
                                                        <th Width="15%">
                                                            Numéro Sécurité
                                                        </th>
                                                        <th Width="25%">
                                                            Date de Naissance
                                                        </th>
                                                        <th Width="10%">
                                                            Bénéficiaire
                                                        </th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                            <%}else if(!c1.equals(c0)){
                                                c0=c1;%>
                                                </tbody>
                                            </table>
                                            <div class='titreTableau'>
                                                <h3 class="mb-30"><%="Contrat ID. "+c0.getId()%></h3>
                                            </div>
                                            <table class="tableauListe" style="margin-bottom: 30px;">
                                                <thead>
                                                    <tr>
                                                        <th Width="15%">
                                                            Nom
                                                        </th>
                                                        <th Width="15%">
                                                            Prénom
                                                        </th>
                                                        <th Width="40%">
                                                            Numéro Sécurité
                                                        </th>
                                                        <th Width="15%">
                                                            Date de Naissance
                                                        </th>
                                                        <th Width="15%">
                                                            Bénéficiaire
                                                        </th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                        <%}%>
                                        <tr>
                                            <td><%=statut.getLaPersonnePhysique().getNom()%></td>
                                            <td><%=statut.getLaPersonnePhysique().getPrenom()%></td>
                                            <td><%=statut.getLaPersonnePhysique().getNumeroSS()%></td>
                                            <td><%=formatter.format(statut.getLaPersonnePhysique().getDateNaiss())%></td>
                                            <td><%=statut.getLaBeneficiaire().getLibelleBeneficiaire()%></td>
                                        </tr>   
                                    <%}%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="col-cl-4">
                        <input type="button" class="info-xin" style="height: 100%; margin-left: 45%; margin-bottom: 10%" value="Menu" onclick="location.href='Page?action=ResponsableConnexion'"/>
                    </div>
                </div>
            </section>
        </fieldset>
                                            
    </body>
</html>
