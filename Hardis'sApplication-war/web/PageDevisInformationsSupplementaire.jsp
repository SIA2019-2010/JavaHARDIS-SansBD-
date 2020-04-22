<%-- 
    Document   : RenseignementInformationsSupplementaire.jsp
    Created on : 26 mars 2020, 12:04:27
    Author     : lixin
--%>

<%@page import="java.lang.reflect.Array"%>
<%@page import="entitee.Population"%>
<%@page import="entitee.Beneficiaire"%>
<%@page import="entitee.Genre"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.List"%>
<%@page import="entitee.PersonnePhysique"%>
<%@page import="entitee.Devis"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="typeConnexion" scope="request" class="String"></jsp:useBean>
        <jsp:useBean id="message" scope="request" class="String"></jsp:useBean>
        <jsp:useBean id="devis" scope="request" class="Devis"></jsp:useBean>
        <jsp:useBean id="listinfos" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listben" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listpop" scope="request" class="java.util.List"></jsp:useBean>
        <%session.setAttribute("titre", "Compléter Devis");%>
        <%@ include file="Style.jsp"%>
    <body>
        <%@ include file="Header.jsp"%>
        <%session.setAttribute("t1", "Renseigner informations");%>
        <%session.setAttribute("t2", "Complétez les informations supplémentaires");%>
        <%@ include file="Header1.jsp"%>
        <%
            List<Beneficiaire>lesb=listben;
            List<Population>lesp=listpop;
            session.setAttribute("flistben", listben);
            session.setAttribute("flistpop", listpop);
            PersonnePhysique pers=devis.getLaPersonne();
            List<PersonnePhysique> listepers=devis.getLesAyantsDroit();
            DateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
            List<Object[]>listep=listinfos;
            Object[] persa=(Object[])request.getAttribute("persa");
            System.out.println("entree jsp");
        %>
        <form method="post" action="Page">
            <fieldset>
                <blockquote class="generic-blockquote">    
                    <div class="section-top-border">
                        <div class="col-xl-12" id="b">
                            <h3 class="mb-30">Affilié principal</h3>
                            <div class="row">
                                <div class="col-xl-1">
                                    <label for="Nom">Nom<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-1">
                                    <label for="Prenom">Prénom<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-1">
                                    <label for="DateNaissance">Date naissance<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-1">
                                    <label for="NumeroSS">Numéro SS<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-1">
                                    <label for="Mail">Mail<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-1">
                                    <label for="Population">Population<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-1">
                                    <label for="Beneficiaire">Beneficiaire<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-1">
                                    <label for="Adresse">Adresse<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-1">
                                    <label for="Genre">Genre<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-1">
                                    <label for="Login">Login<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-1">
                                    <label for="MDP">Mot de passe<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-1">
                                    <label for="RIB">RIB<span class="requis">*</span></label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xl-1">
                                    <input type="text" class="input-xin" name="Nom" placeholder="Nom" value="<%=pers.getNom()%>" STYLE="background-color:#CCCCCC;" readonly/>
                                </div>
                                <div class="col-xl-1">
                                    <input type="text" class="input-xin" name="Prenom" placeholder="Prénom" value="<%=pers.getPrenom()%>" STYLE="background-color:#CCCCCC;" readonly/>
                                </div>
                                <div class="col-xl-1">
                                    <input type="text" class="input-xin" name="DateN" placeholder="Date naissance" value="<%=formatter.format(pers.getDateNaiss())%>" STYLE="background-color:#CCCCCC;" readonly>
                                </div>
                                <div class="col-xl-1">
                                    <input type="text" class="input-xin" name="NumeroSS" placeholder="Numéro SS" value="<%=pers.getNumeroSS()%>" STYLE="background-color:#CCCCCC;" readonly/>
                                </div>
                                <div class="col-xl-1">
                                    <input type="text" class="input-xin" name="Mail" placeholder="Mail" value="<%=pers.getMail()%>" STYLE="background-color:#CCCCCC;" readonly/>
                                </div>
                                <div class="col-xl-1">
                                    <select name="idpop" class="input-xin-select-disabled" style="width: 100%" disabled>
                                        <option value ="<%=pers.getLaPopulation().getLibellePopulation()%>" >
                                            <%=pers.getLaPopulation().getLibellePopulation()%>
                                        </option>
                                    </select>
                                    <input type="hidden" name="idpop" value="<%=pers.getLaPopulation().getLibellePopulation()%>"/>
                                </div>
                                <div class="col-xl-1">
                                    <select name="idben" class="input-xin-select-disabled" style="width: 100%" disabled>
                                        <option value ="Affilié" selected="true" >
                                            Affilié
                                        </option>
                                    </select>
                                    <input type="hidden" name="idben" value="Affilié"/>
                                </div>
                                <%if(persa==null){%>
                                    <div class="col-xl-1">
                                        <input type="text" class="input-xin" placeholder="Adresse" name="Adresse" />
                                    </div>
                                    <div class="col-xl-1">
                                        <select name="idgen" class="input-xin-select" style="width: 100%">
                                            <option value ="" >
                                                À choisir
                                            </option>
                                            <%for(Genre g : Genre.values()){%>
                                                <option value ="<%=g.name()%>" ><%=g.name()%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                    <div class="col-xl-1">
                                        <input type="text" class="input-xin" placeholder="Login" name="Login" />
                                    </div>
                                    <div class="col-xl-1">
                                        <input type="text" class="input-xin" placeholder="Mot de passe" name="MDP" />
                                    </div>
                                    <div class="col-xl-1">
                                        <input type="text" class="input-xin" placeholder="RIB" name="RIB" />
                                    </div>
                                <%}else{%>
                                    <div class="col-xl-1">
                                        <input type="text" class="input-xin" placeholder="Adresse" name="Adresse" value="<%=Array.get(persa,3)%>" />
                                    </div>
                                    <div class="col-xl-1">
                                        <select name="idgen" class="input-xin-select" style="width: 100%">
                                            <option value ="" >
                                                À choisir
                                            </option>
                                            <%for(Genre g : Genre.values()){
                                                if(g.name().equals((String)(Array.get(persa,4)))){%>
                                                    <option value ="<%=g.name()%>" selected="true">
                                                        <%=g.name()%>
                                                    </option>
                                                <%}else{%>
                                                    <option value ="<%=g.name()%>" >
                                                        <%=g.name()%>
                                                    </option>
                                                <%}%>
                                            <%}%>
                                        </select>
                                    </div>
                                    <div class="col-xl-1">
                                        <input type="text" class="input-xin" placeholder="Login" name="Login" value="<%=Array.get(persa,5)%>" />
                                    </div>
                                    <div class="col-xl-1">
                                        <input type="text" class="input-xin" placeholder="Mot de passe" name="MDP" value="<%=Array.get(persa,6)%>" />
                                    </div>
                                    <div class="col-xl-1">
                                        <input type="text" class="input-xin" placeholder="RIB" name="RIB" value="<%=Array.get(persa,7)%>" />
                                    </div>
                                <%}%>
                            </div>
                            <h3 class="mb-30">Ayants droits</h3>
                            <div class="row">
                                <div class="col-xl-1">
                                    <label for="Nom">Nom<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-1">
                                    <label for="Prenom">Prénom<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-1">
                                    <label for="DateNaissance">Date naissance<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-1">
                                    <label for="NumeroSS">Numéro SS<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-1">
                                    <label for="Population">Population<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-1">
                                    <label for="Beneficiaire">Beneficiaire<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-1">
                                    <label for="Adresse">Adresse<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-1">
                                    <label for="Genre">Genre<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-4">
                                    <input type="submit" class="info-xin" value="Envoyer"/>
                                    <input type="button" class="info-xin" value="Menu" onclick="location.href='Page?action=<%=("GestionnaireConnexion".equals(typeConnexion)?"GestionnaireConnexion":"vide")%>'"/>
                                </div>
                            </div>
                            <%for(int i=0;i<listepers.size();i++){
                                PersonnePhysique p=listepers.get(i);%>
                                <div class="row">
                                    <div class="col-xl-1">
                                        <input type="text" class="input-xin" placeholder="Nom" name="NomAD" value="<%=p.getNom()%>"STYLE="background-color:#CCCCCC;" readonly/>
                                    </div>
                                    <div class="col-xl-1">
                                        <input type="text" class="input-xin" placeholder="Nom" name="PrenomAD" value="<%=p.getPrenom()%>"STYLE="background-color:#CCCCCC;" readonly/>
                                    </div>
                                    <div class="col-xl-1">
                                        <input type="text" class="input-xin" placeholder="Nom" name="DateNaiAD" value="<%=formatter.format(p.getDateNaiss())%>" STYLE="background-color:#CCCCCC;" readonly/>
                                    </div>
                                    <div class="col-xl-1">
                                        <input type="text" class="input-xin" placeholder="Nom" name="NumeroSSAD" value="<%=p.getNumeroSS()%>" STYLE="background-color:#CCCCCC;" readonly/>
                                    </div>
                                    <%if(listep.size()==0){%>
                                        <div class="col-xl-1">
                                            <select name="idpopAD" class="input-xin-select" style="width: 100%">
                                                <option value ="" >
                                                    À choisir
                                                </option>
                                                <% for (Population pop : lesp){%>
                                                    <option value ="<%=pop.getId()%>">
                                                        <%=pop.getLibellePopulation()%>
                                                    </option>
                                                <%}%>
                                            </select>
                                        </div>
                                        <div class="col-xl-1">
                                            <select name="idbenAD" class="input-xin-select" style="width: 100%">
                                                <option value ="" >
                                                    À choisir
                                                </option>
                                                <% for (Beneficiaire ben : lesb) if(!ben.getLibelleBeneficiaire().equalsIgnoreCase("Affilie")){%>
                                                    <option value ="<%=ben.getId()%>">
                                                        <%=ben.getLibelleBeneficiaire()%>
                                                    </option>
                                                <%}%>
                                            </select>
                                        </div>
                                        <div class="col-xl-1">
                                            <input type="text" class="input-xin" placeholder="Adresse" name="AdresseAD" />
                                        </div>
                                        <div class="col-xl-1">
                                            <select name="idgenAD" class="input-xin-select" style="width: 100%">
                                                <option value ="" >
                                                    À choisir
                                                </option>
                                                <%for(Genre g : Genre.values()){%>
                                                    <option value ="<%=g.name()%>" ><%=g.name()%></option>
                                                <%}%>
                                            </select>
                                        </div>
                                    <%}else{%>
                                        <div class="col-xl-1">
                                            <select name="idpopAD" class="input-xin-select" style="width: 100%">
                                                <option value ="" >
                                                    À choisir
                                                </option>
                                                <% for (Population pop : lesp){
                                                    if(pop.getId().toString().equals(Array.get(listep.get(i),5))){%>
                                                        <option value ="<%=pop.getId()%>" selected="true">
                                                            <%=pop.getLibellePopulation()%>
                                                        </option>
                                                    <%}else{%>
                                                        <option value ="<%=pop.getId()%>">
                                                            <%=pop.getLibellePopulation()%>
                                                        </option>
                                                    <%}%>
                                                <%}%>
                                            </select>
                                        </div>
                                        <div class="col-xl-1">
                                            <select name="idbenAD" class="input-xin-select" style="width: 100%">
                                                <option value ="" >
                                                    À choisir
                                                </option>
                                                <% for (Beneficiaire ben : lesb) if(!ben.getLibelleBeneficiaire().equalsIgnoreCase("Affilie")){
                                                    if(ben.getId().toString().equals(Array.get(listep.get(i),6))){%>
                                                        <option value ="<%=ben.getId()%>" selected="true">
                                                            <%=ben.getLibelleBeneficiaire()%>
                                                        </option>
                                                    <%}else{%>
                                                        <option value ="<%=ben.getId()%>">
                                                            <%=ben.getLibelleBeneficiaire()%>
                                                        </option>
                                                    <%}%>
                                                <%}%>
                                            </select>
                                        </div>
                                        <div class="col-xl-1">
                                            <input type="text" class="input-xin" placeholder="Adresse" name="AdresseAD" value="<%=Array.get(listep.get(i),3)%>"/>
                                        </div>
                                        <div class="col-xl-1">
                                            <select name="idgenAD" class="input-xin-select" style="width: 100%">
                                                <option value ="" >
                                                    À choisir
                                                </option>
                                                <%for(Genre g : Genre.values()){
                                                    if(g.name().equals((String)(Array.get(listep.get(i),4)))){%>
                                                        <option value ="<%=g.name()%>" class="input-xin-select" selected="true">
                                                            <%=g.name()%>
                                                        </option>
                                                    <%}else{%>
                                                        <option value ="<%=g.name()%>" >
                                                            <%=g.name()%>
                                                        </option>
                                                    <%}%>
                                                <%}%>
                                            </select>
                                        </div>
                                    <%}%>
                                </div>
                            <%}%>
                        </div>
                    </div>
                </blockquote>
            </fieldset>
            <input type="hidden" name="iddevis" value="<%=devis.getId().toString()%>"/>
            <input type="hidden" name="action" value="CompleterInformations"/>
            <button type="submit" value="Valider">Envoyer</button>
            <input type="button" value="Revenir à l'accueil" onclick="location.href='Page?action=vide'"/>
        </form>
        <%@ include file="Footer.jsp"%>
    </body>
</html>
