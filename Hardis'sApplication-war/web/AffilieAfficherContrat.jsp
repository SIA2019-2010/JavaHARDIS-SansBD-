<%-- 
    Document   : AffilieAfficherContrat
    Created on : 20 mars 2020, 01:35:46
    Author     : lixin
--%>

<%@page import="entitee.Unite"%>
<%@page import="entitee.PriseEnCharge"%>
<%@page import="entitee.Garantie"%>
<%@page import="entitee.TypeGarantie"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="entitee.Produit"%>
<%@page import="entitee.Contrat"%>
<%@page import="java.util.List"%>
<%@page import="entitee.StatutBeneficiaire"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="listecontrat" scope="request" class="java.util.List"></jsp:useBean>
        <%
            List<StatutBeneficiaire>liste=listecontrat;
            int ct1=0;
            Contrat c0=null;
            DateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
        %>
        <%session.setAttribute("titre", "Infos Contrats");%>
        <%@ include file="Style.jsp"%>
        
    <body>
        <%@ include file="Header.jsp"%>
        <%session.setAttribute("t1", "Liste de votre ayant-droits");%>
        <%session.setAttribute("t2", "Affichage des infos de votres contrats");%>
        <%@ include file="Header1.jsp"%>
        
        <fieldset>
            <section id="about-area" class="section-padding-menu">
                <div class="container">
                    <div class="about-feature-area">
                        <div class="row" id="b">
                            <%for(StatutBeneficiaire statut : liste){
                                Contrat c1=statut.getLeContrat();
                                Produit p1=c1.getLeProduit();%>
                                <%if(!c1.equals(c0)){
                                    ct1++;
                                    c0=c1;
                                    Produit p=c0.getLeProduit();%>
                                    <form id="CT<%=ct1%>" class="white-popup-block xinproduit mfp-hide">
                                        <div class="popup_box_xin ">
                                            <div class="popup_inner">
                                                <div class="section-top-border">
                                                    <h1 class="mb-30">Nom : <%=p.getNomProduit()%></h1>
                                                    <h2 class="mb-30">Prix : <%=c0.getPrixMensuel()%></h2>
                                                    <%for(TypeGarantie tg:p.getLesTypesGarantie()){%>
                                                        <div class='titreTableau'>
                                                            <h3 class="mb-30"><%=tg.getTypeGarantie()%></h3>
                                                        </div>
                                                        <table class="tableauListe">
                                                            <thead>
                                                                <tr>
                                                                    <th Width="55%">
                                                                        Garantie
                                                                    </th>
                                                                    <th Width="15%">
                                                                        Façon
                                                                    </th>
                                                                    <th Width="15%">
                                                                        Adhérent
                                                                    </th>
                                                                    <th Width="15%">
                                                                        Valeur
                                                                    </th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <%for(Garantie g:tg.getLesGaranties()){%>
                                                                    <%String infoPEC="";%>
                                                                    <%for(PriseEnCharge pec:p.getLesPriseEnCharges()){%>
                                                                        <%if(pec.getLaGarantie().equals(g)){%>
                                                                            <tr>
                                                                                <%if(pec.getUnite()==Unite.euro){
                                                                                    infoPEC=pec.getTauxRempoursement()+" €" ;
                                                                                }
                                                                                else{
                                                                                    infoPEC=pec.getTauxRempoursement()+"%" ;
                                                                                }
                                                                                System.out.println("asdasdf000000");
                                                                                %>
                                                                                <%boolean choix=true;
                                                                                    boolean adh=true;
                                                                                    try{
                                                                                        adh=pec.isAdherentCAS();
                                                                                    }catch(Exception e){
                                                                                        choix=false;
                                                                                    }
                                                                                %>
                                                                                <td>
                                                                                    <%=g.getLibelleGarantie()%>
                                                                                </td>
                                                                                <td>
                                                                                    <%=(pec.getBaseRemboursement()==null?"Euro":pec.getBaseRemboursement())%>
                                                                                </td>
                                                                                <td>
                                                                                    <%=(choix?(adh?"Oui":"Non"):"Pareil")%>
                                                                                </td>
                                                                                <td>
                                                                                    <%=infoPEC%>
                                                                                </td>
                                                                            </tr>
                                                                        <%}%>
                                                                    <%}%>
                                                                <%}%>
                                                            </tbody>
                                                        </table>
                                                        <h3 class="mb-30"><br/></h3>
                                                    <%}%>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                    <div class="col-lg-12 popup-with-form" style="margin-bottom: 30px; text-align: center;" href="#CT<%=ct1%>">
                                        <div class="about-feature-item" style="width:100%; height: 100%">
                                            <div class='titreTableau'>
                                                <h1 class="mb-30"><%=c0.getLeProduit().getNomProduit()%></h1>
                                            </div>
                                            <table class="tableauListe">
                                                <thead>
                                                    <tr>
                                                        <th Width="15%">
                                                            Nom
                                                        </th>
                                                        <th Width="15%">
                                                            Prénom
                                                        </th>
                                                        <th Width="15%">
                                                            Date Naissance
                                                        </th>
                                                        <th Width="40%">
                                                            Numéro SS
                                                        </th>
                                                        <th Width="15%">
                                                            Relation
                                                        </th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                <%}%>
                                                    <tr>
                                                        <td><%=statut.getLaPersonnePhysique().getNom()%></td>
                                                        <td><%=statut.getLaPersonnePhysique().getPrenom()%></td>
                                                        <td><%=formatter.format(statut.getLaPersonnePhysique().getDateNaiss())%></td>
                                                        <td><%=statut.getLaPersonnePhysique().getNumeroSS()%></td>
                                                        <td><%=statut.getLaBeneficiaire().getLibelleBeneficiaire()%></td>
                                                    </tr>
                            <%}%>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
            </section>
            <div class="row">
                <div class="col-xl-4" style="margin-left:45%">
                    <input type="button" class="info-xin" value="Menu" onclick="location.href='Page?action=AffilieConnexion'"/>   
                </div>
            </div>
            <div class="row">
                <div class="col-xl-12">
                </div>
            </div>
        </fieldset>
        <%@ include file="Footer.jsp"%>
    </body>
</html>
