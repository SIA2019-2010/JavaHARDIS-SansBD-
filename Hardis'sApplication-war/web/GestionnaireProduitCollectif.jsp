<%-- 
    Document   : GestionnaireCreationRemboursement
    Created on : 26 mars 2020, 23:25:47
    Author     : lixin
--%>

<%@page import="entitee.Unite"%>
<%@page import="entitee.TypeGarantie"%>
<%@page import="entitee.PriseEnCharge"%>
<%@page import="entitee.Garantie"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="entitee.Produit"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:useBean id="message" scope="request" class="String"></jsp:useBean>
        <jsp:useBean id="RePr" scope="session" class="String"></jsp:useBean>
        <jsp:useBean id="listepro" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="Npage" scope="request" class="java.lang.Integer"></jsp:useBean>
        <jsp:useBean id="total" scope="request" class="java.lang.Integer"></jsp:useBean>
        <%
            List<Produit> liste=listepro;
            DateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
        %>
        <%session.setAttribute("titre", "Choix Produits");%>
        <%@ include file="Style.jsp"%>
    <body>
        <%@ include file="Header.jsp"%>
        <%session.setAttribute("t1", "Liste des produits");%>
        <%session.setAttribute("t2", "Choisisez un produit collectif");%>
        <%@ include file="Header1.jsp"%>
        <fieldset>
            <section id="about-area" class="section-padding-menu">
                <div class="container">
                    <div class="about-feature-area" >
                        <div class="row" id="b" style="margin-left: 14%;">
                            <form name="formcherche" method="post" action="Page">
                                <div class="row">
                                    <div class="col-xl-6">
                                        <h4>Nom du produit à choisir<br/>(complet ou une parite)</h4>
                                    </div>
                                    <div class="col-xl-6">
                                        <input id="a" type="text" class="input-xin" name="RePr" placeholder="Nom de produit" value="<%=(RePr.equals("")||RePr.equals("%")?"":(String)RePr)%>"/>
                                    </div>
                                    <div class="col-6">    
                                        <input type="hidden" name="action" value="GestionnaireProduitCollectif"/>
                                        <button class="info-xin" type="submit" style="width:100%;" value="Valider">Rechercher</button>
                                    </div>
                                    <div class="col-6">   
                                        <input class="info-xin" type="button" style="width:100%;" value="Supprimer la filtré" onclick="location.href='Page?action=GestionnaireProduitCollectif&RePr='">
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
                                    <th Width="23%">
                                        Nom Produit
                                    </th>
                                    <th Width="7%">
                                        Prix Mensuel
                                    </th>
                                    <th Width="50%">
                                        Entreprise
                                    </th>
                                    <th Width="10%">
                                        SIRET
                                    </th>
                                    <th Width="10%">
                                        info
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <%for(Produit pro : liste){%>
                                    <tr>
                                        <td><%=pro.getNomProduit()%></td>
                                        <td><%=(java.lang.Double)pro.getPrixBase()%></td>
                                        <td><%=pro.getLaPersonneMorale().getRaisonSociale()%></td>
                                        <td><%=pro.getLaPersonneMorale().getSIRET()%></td>
                                        <td><input type="button" class="popup-with-form info-xin" value="info" href='#PR<%=pro.getId()%>'/></td>
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
                                        <select name="SPage">                     
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
                                    <input type="hidden" name="action" value="GestionnaireProduitCollectif"/>
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
        
        <%for(Produit p : liste){%>
            <form id="PR<%=p.getId() %>" method="post" action="Page" class="white-popup-block xinproduit mfp-hide">
                <input type="hidden" name="idprod" value="<%=p.getId().toString()%>"/>
                <input type="hidden" name="action" value="GestionnaireChoixProduitCollectif"/>
                <div class="popup_box_xin ">
                    <div class="popup_inner">
                        <div class="section-top-border">
                            <h1 class="mb-30">Nom : <%=p.getNomProduit()%></h1>
                            <h2 class="mb-30">Prix : <%=p.getPrixBase()%></h2>
                            <h3>
                                <input class="info-xin" type="submit" value="Choisir"/>
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
                                                        }%>
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
                                <input class="info-xin" type="submit" value="Choisir"/>
                            </h3>
                        </div>
                    </div>
                </div>
            </form>
        <%}%>
        <%@ include file="Footer.jsp"%>
    </body>
</html>
