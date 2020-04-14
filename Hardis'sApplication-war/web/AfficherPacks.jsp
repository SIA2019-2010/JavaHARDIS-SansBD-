<%-- 
    Document   : AfficherPacks
    Created on : 19 mars 2020, 01:35:23
    Author     : lixin
--%>

<%@page import="entitee.Unite"%>
<%@page import="entitee.PriseEnCharge"%>
<%@page import="entitee.Garantie"%>
<%@page import="entitee.TypeGarantie"%>
<%@page import="entitee.Produit"%>
<%@page import="java.lang.reflect.Array"%>
<%@page import="java.util.List"%>
<%@page import="entitee.Population"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="typeConnexion" scope="request" class="String"></jsp:useBean>
        <jsp:useBean id="message" scope="request" class="String"></jsp:useBean>
        <jsp:useBean id="lesPacks" scope="session" class="java.util.List"></jsp:useBean>
        <%--<jsp:useBean id="listeinfos" scope="request" class="java.util.List"></jsp:useBean>--%>
        <%
            List<Object[]>listepacks=lesPacks;
            //List<Object[]>listep=listeinfos;
            //Object[] pers=(Object[])request.getAttribute("pers");
            //session.setAttribute("pers", pers);
            //session.setAttribute("listeinfos", listeinfos);
            //session.setAttribute("lesPacks", lesPacks);
        %>
        <%session.setAttribute("titre", "Choix de produits");%>
        <%@ include file="Style.jsp"%>
    <body>
        <%@ include file="Header.jsp"%>
        <%session.setAttribute("t1", "Choix de produits");%>
        <%session.setAttribute("t2", "Choisisez vous le produit.");%>
        <%@ include file="Header1.jsp"%>
        <script>
            function clickpopup(idform1){
                window.location.href='Page?action=AfficherPacks';
                document.getElementById(idform1).submit();
            }
        </script>
        <fieldset>
            <section id="about-area" class="section-padding-menu">
                <div class="container">
                    <div class="about-feature-area">
                        <div class="row" id="b">
                            <%int npp1=0;
                            int npp2=0;
                            for(Object[] pack : listepacks){
                                npp1++;%>
                                <div class="col-lg-4 popup-with-form" style="margin-bottom: 30px; text-align: center;" href="#TGG<%=npp1%>">
                                    <div class="about-feature-item" style="width:100%; height: 100%">
                                        <%Produit p=(Produit)(Array.get(pack,0));
                                        double px=(java.lang.Double)(Array.get(pack,1));%>
                                        <h1 class="mb-20"><%=p.getNomProduit()%></h1>
                                        <h3>Prix : <%=px%> €</h3>

<!--                                        <div class="book_btn d-none d-lg-block">
                                            <a class="popup-with-form" href="#TGG<%=npp1%>">Infos</a>
                                        </div>-->
<!--                                        -->
                                        <%for(TypeGarantie tg:p.getLesTypesGarantie()){%>
                                            <%=tg.getTypeGarantie()%><br/>
                                        <%}%>
                                    </div>
                                </div>
                            <%}%>
                        </div>
                    </div>
                </div>
            </section>
            <div class="row">
                <div class="col-xl-5">
                </div>
                <div class="col-xl-3">
                    <input type="button" class="info-xin" style="width: 32%;" value="Modifier" onclick="location.href='Page?action=CreationDevisInformations'"/>
                    <input type="button" class="info-xin" style="width: 32%;" value="  Menu  " onclick="location.href='Page?action=<%=("GestionnaireConnexion".equals(typeConnexion)?"GestionnaireConnexion":"vide")%>'"/>
                </div>
            </div>
            <div class="row">
                <div class="col-xl-12"></div>>
            </div>
        </fieldset> 
        <%for(Object[] pack1 : listepacks){
            Produit p=(Produit)(Array.get(pack1,0));
            double px=(java.lang.Double)(Array.get(pack1,1));
            npp2++;%>
            <form id="TGG<%=npp2%>" class="white-popup-block xinproduit mfp-hide" target="_blank">
                <input type="hidden" value="<%=listepacks.indexOf(pack1)%>" name="numpack"/>
                <input type="hidden" value="ChoixPack" name="action"/>
                <div class="popup_box_xin ">
                    <div class="popup_inner">
                        <div class="section-top-border">
                            <h1 class="mb-30">Nom : <%=p.getNomProduit()%></h1>
                            <h2 class="mb-30">Prix : <%=px%></h2>
                            <h3>
                                <input class="info-xin" type="submit" value="Valider" onclick="clickpopup('TGG<%=npp2%>')"/>
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
                                <input class="info-xin" type="submit" value="Valider" onclick="clickpopup('TGG<%=npp2%>')"/>
                            </h3>
                        </div>
                    </div>
                </div>
            </form>
        <%}%> 
        <%@ include file="Footer.jsp"%>
    </body>
</html>
