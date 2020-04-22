<%-- 
    Document   : PageCreationProduit
    Created on : 31 mars 2020, 03:00:24
    Author     : lixin
--%>

<%@page import="java.util.Arrays"%>
<%@page import="entitee.Garantie"%>
<%@page import="entitee.PersonneMorale"%>
<%@page import="entitee.TypeGarantie"%>
<%@page import="entitee.Fiscalite"%>
<%@page import="entitee.TypeProduit"%>
<%@page import="entitee.Domaine"%>
<%@page import="entitee.Population"%>
<%@page import="entitee.Beneficiaire"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="v" scope="request" class="Boolean"></jsp:useBean>
        <jsp:useBean id="message" scope="request" class="String"></jsp:useBean>
        <jsp:useBean id="listben" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listepersmo" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listpop" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listdo" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listtp" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listf" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listtg" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listg" scope="request" class="java.util.List"></jsp:useBean>
        <%System.out.println("ok");%>
        
        <jsp:useBean id="NomProduit" scope="request" class="String"></jsp:useBean>
        <jsp:useBean id="PrixProduit" scope="request" class="String"></jsp:useBean>
        <%System.out.println("ok");%>
        
        <jsp:useBean id="idbs" scope="request" class="String[]"></jsp:useBean>
        <jsp:useBean id="idpops" scope="request" class="String[]"></jsp:useBean>
        <jsp:useBean id="idtgs" scope="request" class="String[]"></jsp:useBean>
        <%System.out.println("ok");%>
        <jsp:useBean id="idbrs" scope="request" class="String[]"></jsp:useBean>
        <jsp:useBean id="p1" scope="request" class="String[]"></jsp:useBean>
        <jsp:useBean id="p2" scope="request" class="String[]"></jsp:useBean>
        
        <jsp:useBean id="idp" scope="request" class="String"></jsp:useBean>
        <jsp:useBean id="idd" scope="request" class="String"></jsp:useBean>
        <jsp:useBean id="idb" scope="request" class="String"></jsp:useBean>
        <jsp:useBean id="idf" scope="request" class="String"></jsp:useBean>
        <jsp:useBean id="idtp" scope="request" class="String"></jsp:useBean>
        
        <%
            List<TypeProduit> listetp=listtp;
            List<TypeGarantie> listetg=listtg;
            List<Population> listepop=listpop;
            List<PersonneMorale> listep=listepersmo;
            List<Beneficiaire> listeb=listben;
            List<Domaine> listed=listdo;
            List<Fiscalite> listef=listf;
            List<Garantie> listeg=listg;
            //DateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
        %>
        <%session.setAttribute("titre", "Créer Produit");%>
        <%@ include file="Style.jsp"%>
        
    <body>
        <%@ include file="Header.jsp"%>
        <%session.setAttribute("t1", "Renseigner informations");%>
        <%session.setAttribute("t2", "Renseigner des informations du produit");%>
        <%@ include file="Header1.jsp"%>
        <form method="post" action="Page">
            <fieldset>
                <blockquote class="generic-blockquote">
                    <div class="section-top-border">
                        <div class="col-xl-8" style="left:20%;" id="b">
                            <div class="row">
                                <h3 class="mb-30">Information</h3>
                            </div>
                            <div class="row">
                                <div class="col-md-2" >
                                    <div>
                                        <label for="NomProduit" <%=(NomProduit.equals("")&&v?"style=\"color:#f00\"":"")%>>Nom Produit<span class="requis">*</span></label>
                                    </div>
                                    <div>
                                        <input type="text" class="input-xin" name="NomProduit" placeholder="Produit" value="<%=NomProduit%>"/>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div>
                                        <label for="PrixProduit" <%=(PrixProduit.equals("")&&v?"style=\"color:#f00\"":"")%>>Prix Produit<span class="requis">*</span></label>
                                    </div>
                                    <div>
                                        <input type="number" step="0.01" class="input-xin" name="PrixProduit" placeholder="Prix" value="<%=PrixProduit%>"/>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div>
                                        <label for="idd" <%=(idd.equals("")&&v?"style=\"color:#f00\"":"")%>>Domaine<span class="requis">*</span></label>
                                    </div>
                                    <div>
                                        <select name="idd" class="input-xin-select">
                                            <option value="">
                                                À choisir
                                            </option>
                                                <option value ="1" <%=("1".equals(idd)?"selected":"")%>>
                                                    Santé
                                                </option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div> 
                                        <label for="idtp" <%=(idtp.equals("")&&v?"style=\"color:#f00\"":"")%><span class="requis">Type Produit<span class="requis">*</span></label>
                                    </div>
                                    <div>
                                        <select name="idtp" class="input-xin-select">
                                            <option value="">
                                                À choisir
                                            </option>
                                            <%for(TypeProduit tp:listetp){%>
                                                <option value="<%=tp.getId()%>" <%=(tp.getId().toString().equals(idtp)?"selected":"")%> >
                                                    <%=tp.getLibelleTypeProduit()%>
                                                </option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div>
                                        <label for="idf" <%=(idf.equals("")&&v?"style=\"color:#f00\"":"")%> >Fiscalité<span class="requis">*</span></label>
                                    </div>
                                    <div>
                                        <select name="idf" class="input-xin-select">
                                            <option value="">
                                                À choisir
                                            </option>
                                            <%for(Fiscalite f:listef){%>
                                                    <option value="<%=f.getId()%>" <%=(f.getId().toString().equals(idf)?"selected":"")%> >
                                                        <%=f.getTaxe()%>
                                                    </option>
                                            <%}%>
                                        </select>
                                    </div>    
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-8" style="margin-right: 50px;">
                                    <div>
                                        <label for="idp" <%=(idp.equals("")&&v?"style=\"color:#f00\"":"")%>>Entreprise/Individuel<span class="requis">*</span></label>
                                    </div>
                                    <div>
                                        <select name="idp" class="input-xin-select">
                                            <option value ="">
                                                À choisir
                                            </option>
                                            <option value ="0" <%=("0".equals(idp)?"selected":"")%> >
                                                -------------"Contrat Individuel"-------------
                                            </option>
                                            <%for(PersonneMorale p:listep){%>
                                                <option value ="<%=p.getId()%>" <%=(p.getId().toString().equals(idp)?"selected":"")%> >
                                                    <%=p.getRaisonSociale()%>
                                                </option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>  
                                <div class="col-md-2" style="padding-bottom: 30px">   
                                    <div>
                                        <label for="idb" <%=(idb.equals("")&&v?"style=\"color:#f00\"":"")%> >Assiettes Cotisation<span class="requis">*</span></label>
                                    </div>
                                    <div>   
                                        <select name="idb" class="input-xin-select">
                                            <option value="">
                                                À choisir
                                            </option>
                                            <%for(Beneficiaire be:listeb){%>
                                                <option value ="<%=be.getId()%>" <%=(be.getId().toString().equals(idb)?"selected":"")%> >
                                                    <%=be.getId()%><%=be.getLibelleBeneficiaire()%>
                                                </option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                            <div class="row">          
                                <div class="col-md-4">
                                    <div>
                                        <label for="idpops" <%=(idpops.length==0&&v?"style=\"color:#f00\"":"")%>>Populations<span class="requis">*</span></label>
                                    </div>
                                    <div>
                                        <%for(Population pop:listepop){%>
                                            <div>
                                                <input class="check-circle"type="checkbox" name="idpops" value="<%=pop.getId()%>" <%=(Arrays.asList(idpops).contains(pop.getId().toString())?"checked":"")%>/>
                                                <%=pop.getId()%><%=pop.getLibellePopulation()%>
                                            </div>
                                        <%}%>
                                    </div>
                                </div>
                                <div class="col-md-3"  style="margin-right: 50px;">
                                    <div>
                                        <label for="idtgs" <%=(idtgs.length==0&&v?"style=\"color:#f00\"":"")%>>Type Garanties<span class="requis">*</span></label>
                                    </div>
                                    <div>
                                        <%for(TypeGarantie tg:listetg){%>
                                            <div>
                                                <input type="checkbox" name="idtgs" value="<%=tg.getId()%>" <%=(Arrays.asList(idtgs).contains(tg.getId().toString())?"checked":"")%>/>
                                                <%=tg.getId()%><%=tg.getTypeGarantie()%>
                                            </div>
                                        <%}%> 
                                    </div>
                                </div>  
                                <div class="col-md-4" style="padding-right: -10px">
                                    <div>
                                        <label for="idbs"<%=(idbs.length==0&&v?"style=\"color:#f00\"":"")%>>Bénéficiaires<span class="requis">*</span></label>
                                    </div>
                                    <div>
                                        <%for(Beneficiaire be:listeb){%>
                                            <div>
                                                <input type="checkbox" name="idbs" value="<%=be.getId()%>" <%=(Arrays.asList(idbs).contains(be.getId().toString())?"checked":"")%>/>
                                                <%=be.getLibelleBeneficiaire()%>
                                            </div>
                                        <%}%>
                                    </div>
                                </div> 
                            </div>
                            <div class="row">
                                <h3 class="mb-30">Prise en charge (AdéhentCas doit être plus grand que Non AdéhentCas)</h3>
                            </div>
                            <div class="row">
                                <%for(int i=0;i<listeg.size();i++){
                                    Garantie g =listeg.get(i);%>
                                    <div class="col-md-4">
                                        <div class="col-md-auto">
                                            <p ><%=g.getLibelleGarantie()%></p>
                                        </div>
                                            <input name="idg" type="hidden" value="<%=g.getId()%>"/>
                                            <div class="row">
                                            <div class="col-md-5">
                                                <select name="idbrs"  class="input-xin-select">
                                                    <option value="" >À choisir</option>
                                                    <option value="0" <%=("0".equals((idbrs.length==listeg.size()?idbrs[listeg.indexOf(g)]:""))?"selected":"")%>>BR</option>
                                                    <option value="1" <%=("1".equals((idbrs.length==listeg.size()?idbrs[listeg.indexOf(g)]:""))?"selected":"")%>>TM</option>
                                                    <option value="2" <%=("2".equals((idbrs.length==listeg.size()?idbrs[listeg.indexOf(g)]:""))?"selected":"")%>>FR</option>
                                                    <option value="3" <%=("3".equals((idbrs.length==listeg.size()?idbrs[listeg.indexOf(g)]:""))?"selected":"")%>>BR-RSS</option>
                                                    <option value="4" <%=("4".equals((idbrs.length==listeg.size()?idbrs[listeg.indexOf(g)]:""))?"selected":"")%>>PMSS</option>
                                                    <option value="5" <%=("5".equals((idbrs.length==listeg.size()?idbrs[listeg.indexOf(g)]:""))?"selected":"")%>>Chiffre</option>
                                                </select>
                                            </div>
                                            <div class="col-md-6">    
                                                <input name="p1" class="input-xin" style="height:20px" type="number" step="0.01" placeholder="AdéhentCAS" value="<%=(p1.length==listeg.size()?p1[listeg.indexOf(g)]:"")%>"/>
                                                <input name="p2" class="input-xin" style="height:20px" type="number" step="0.01" placeholder="Non AdéhentCAS" value="<%=(p2.length==listeg.size()?p2[listeg.indexOf(g)]:"")%>"/>
                                            </div>
                                        </div>
                                    </div>  
                                <%}%>
                            </div>
                                                  
                            <div class="col-xl-4" style="margin-left:35%;">
                                <input type="hidden" value="CreerProduit" name="action">
                                <input type="submit" class="info-xin" value="Valider"/>
                                <input type="button" class="info-xin" value="Menu" onclick="location.href='Page?action=GestionnaireConnexion'"/>   
                            </div>  
                        </div>
                    </div>
                </blockquote>
            </fieldset>
        </form>
        <%@ include file="Footer.jsp"%>
    </body>
</html>
