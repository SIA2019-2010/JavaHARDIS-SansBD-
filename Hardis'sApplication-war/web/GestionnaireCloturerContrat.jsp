<%-- 
    Document   : GestionnaireValiderContrat
    Created on : 19 mars 2020, 14:51:22
    Author     : lixin
--%>

<%@page import="entitee.Unite"%>
<%@page import="entitee.Produit"%>
<%@page import="entitee.PriseEnCharge"%>
<%@page import="entitee.Garantie"%>
<%@page import="entitee.TypeGarantie"%>
<%@page import="entitee.PersonnePhysique"%>
<%@page import="entitee.StatutBeneficiaire"%>
<%@page import="java.util.List"%>
<%@page import="entitee.Contrat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="message" scope="request" class="String"></jsp:useBean>
        <jsp:useBean id="ReSS" scope="session" class="String"></jsp:useBean>
        <jsp:useBean id="listecontrat" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="Npage" scope="request" class="java.lang.Integer"></jsp:useBean>
        <jsp:useBean id="total" scope="request" class="java.lang.Integer"></jsp:useBean>
        <%
            List<Contrat>liste=listecontrat;
            PersonnePhysique crea;
            Produit p;
        %>
        <%session.setAttribute("titre", "Cloturer Contrats");%>
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
                                        <input type="hidden" name="action" value="GestionnaireCloturerContrat"/>
                                        <button class="info-xin" type="submit" style="width:100%;" value="Valider">Rechercher</button>
                                    </div>
                                    <div class="col-6">   
                                        <input class="info-xin" type="button" style="width:100%;" value="Supprimer la filtré" onclick="location.href='Page?action=GestionnaireCloturerContrat&ReSS='">
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
                                        Nom Produit
                                    </th>
                                    <th Width="10%">
                                        Prix Mensuel
                                    </th>
                                    <th Width="15%">
                                        Nom
                                    </th>
                                    <th Width="15%">
                                        Prénom
                                    </th>
                                    <th Width="25%">
                                        Numéro Sécurité
                                    </th>
                                    <th Width="10%">
                                        info
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <%for(Contrat cont : liste){
                                    crea=null;
                                    p=cont.getLeProduit();
                                    for(StatutBeneficiaire sb : cont.getLesStatutsBeneficiaire()){
                                        if("Affilie".equals(sb.getLaBeneficiaire().getLibelleBeneficiaire()))
                                            crea=sb.getLaPersonnePhysique();
                                    }%>
                                    <tr>
                                        <td><%=cont.getLeProduit().getNomProduit()%></td>
                                        <td><%=(java.lang.Double)cont.getPrixMensuel()%></td>
                                        <td><%=crea.getNom()%></td>
                                        <td><%=crea.getPrenom()%></td>
                                        <td><%=crea.getNumeroSS()%></td>

                                        <td><input type="button" class="popup-with-form info-xin" value="info" href='#PR<%=cont.getId()%>'/></td>
                                    </tr>

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
                                                    <option class="input-xin-select" value="<%=i%>" selected="true">
                                                        Page <%=i%> sur <%=total%>
                                                    </option>
                                                <%}else{%>
                                                    <option class="input-xin-select" value="<%=i%>">
                                                        Page <%=i%> sur <%=total%>
                                                    </option>
                                                <%}%>
                                            <%}else{%>
                                                <option class="input-xin-select" value="1" selected="true">
                                                    Page 1 sur 1
                                                </option>
                                            <%}%>
                                        </select>
                                    </div>
                                    <input type="hidden" name="action" value="GestionnaireCloturerContrat"/>
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
        
        <%for(Contrat cont : liste){
            p=cont.getLeProduit();%>
            <form id="PR<%=cont.getId()%>" class="white-popup-block xinproduit mfp-hide">
                <input type="hidden" name="idc" value="<%=cont.getId().toString()%>"/>
                <input type="hidden" name="action" value="CloturerContrat"/>
                <div class="popup_box_xin ">
                    <div class="popup_inner">
                        <div class="section-top-border">
                            <h1 class="mb-30">Nom : <%=p.getNomProduit()%></h1>
                            <h2 class="mb-30">Prix : <%=cont.getPrixMensuel()%></h2>
                            <h3>
                                <input class="info-xin" type="submit" value="Cloturer"/>
                            </h3>
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
                            <h3>
                                <input class="info-xin" type="submit" value="Cloturer"/>
                            </h3>
                        </div>
                    </div>
                </div>
            </form>
        <%}%>
        <%@ include file="Footer.jsp"%>
    </body>
</html>
