<%-- 
    Document   : PageCreationDevis.jsp
    Created on : 13 mars 2020, 15:27:07
    Author     : lixin
--%>
<%@page import="entitee.Genre"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="entitee.Population"%>
<%@page import="entitee.Beneficiaire"%>
<%@page import="java.lang.reflect.Array"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="message" scope="request" class="String"></jsp:useBean>
        <jsp:useBean id="idprod" scope="request" class="String"></jsp:useBean>
        <jsp:useBean id="listinfos" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listben" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listpop" scope="request" class="java.util.List"></jsp:useBean>
        <%
            List<Beneficiaire>lesb=listben;
            List<Population>lesp=listpop;
            //DateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
            List<Object[]>listep=listinfos;
            Object[] persa=(Object[])request.getAttribute("persa");
        %>
        <%session.setAttribute("titre", "Compléter Produit Collectif");%>
        <%@ include file="Style.jsp"%>
        
    <body>
        <%@ include file="Header.jsp"%>
        <%session.setAttribute("t1", "Renseigner informations");%>
        <%session.setAttribute("t2", "Complétez les informations");%>
        <%@ include file="Header1.jsp"%>
        
        
        <form method="post" action="Page">
            <fieldset>
                <blockquote class="generic-blockquote">    
                    <div class="section-top-border">
                        <div class="row">
                            <div class="col-xl-1"></div>
                            <div class="col-xl-12" id="b">
                                <div class="row">
                                    <h3 class="mb-30">Affilié principal</h3>
                                    <p>18 ans minimum</p>
                                </div>
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
                                    <div class="col-xl-1">
                                        <label for="Mail">Mail<span class="requis">*</span></label>
                                    </div>
                                </div>
                                <div class="row">
                                    <%if(persa==null){%>
                                        <div class="col-xl-1">
                                            <input type="text" class="input-xin" name="Nom" placeholder="Nom" />
                                        </div>
                                        <div class="col-xl-1">
                                            <input type="text" class="input-xin" name="Prenom" placeholder="Prénom" />
                                        </div>
                                        <div class="col-xl-1">
                                            <input id="o0" class="datepickerxin" name="DateN" placeholder="Date naissance" readonly="true"/>
                                        </div>
                                        <div class="col-xl-1">
                                            <input type="text" class="input-xin" name="NumeroSS" placeholder="Numéro SS" />
                                        </div>
                                        <div class="col-xl-1">
                                            <select name="idpop" class="input-xin-select" style="width: 100%">
                                                <option value ="" >
                                                    À choisir
                                                </option>
                                                <%for(Population pop : lesp){%>
                                                    <option value ="<%=pop.getId()%>" >
                                                        <%=pop.getLibellePopulation()%>
                                                    </option>
                                                <%}%>
                                            </select>
                                        </div>
                                        <div class="col-xl-1">
                                            <select class="input-xin-select-disabled" style="width: 100%;" disabled>
                                                <option value ="Affilié" selected="true" >
                                                    Affilié
                                                </option>
                                            </select>
                                            <input name="idben" type="hidden" value="Affilié"/>
                                        </div>
                                        <div class="col-xl-1">
                                            <input type="text" class="input-xin" name="Adresse" placeholder="Adresse" />
                                        </div>
                                        <div class="col-xl-1">
                                            <select name="idgen" class="input-xin-select" style="width: 100%">
                                                <option value ="" >
                                                    À choisir
                                                </option>
                                                <%for(Genre g : Genre.values()){%>
                                                    <option value ="<%=g.name()%>" >
                                                        <%=g.name()%>
                                                    </option>
                                                <%}%>
                                            </select>
                                        </div>
                                        <div class="col-xl-1">
                                            <input type="text" class="input-xin" name="Mail" placeholder="Mail" />
                                        </div>

                                    <%}else{%>
                                        <div class="col-xl-1">
                                            <input type="text" class="input-xin" name="Nom" placeholder="Nom" value="<%=Array.get(persa,1)%>" />
                                        </div>
                                        <div class="col-xl-1">
                                            <input type="text" class="input-xin" name="Prenom" placeholder="Prénom" value="<%=Array.get(persa,2)%>" />
                                        </div>
                                        <%if(Array.get(persa,3).equals("")){%>
                                            <div class="col-xl-1">
                                                <input id="o0" class="datepickerxin" name="DateN" placeholder="Date naissance" readonly="true">
                                            </div>
                                        <%}else{%>
                                            <div class="col-xl-1">
                                                <input id="o0" class="datepickerxin" name="DateN" placeholder="Date naissance"  value="<%=Array.get(persa,3)%>" readonly="true">
                                            </div>
                                        <%}%>
                                        <div class="col-xl-1">
                                            <input type="text" class="input-xin" name="NumeroSS" placeholder="Numéro SS" value="<%=Array.get(persa,4)%>" />
                                        </div>
                                        <div class="col-xl-1">
                                            <select name="idpop" class="input-xin-select" style="width: 100%">
                                                <option value ="" >
                                                    À choisir
                                                </option>
                                                <%for(Population pop : lesp){
                                                    if(pop.getId().toString().equals((String)(Array.get(persa,7)))){%>
                                                        <option value ="<%=pop.getId()%>" selected="true">
                                                            <%=pop.getLibellePopulation()%>
                                                        </option>
                                                    <%}else{%>
                                                        <option value ="<%=pop.getId()%>" >
                                                            <%=pop.getLibellePopulation()%>
                                                        </option>
                                                    <%}%>
                                                <%}%>
                                            </select>
                                        </div>
                                        <div class="col-xl-1">
                                            <select name="idben"class="input-xin-select" style="width: 100%" disabled>
                                                <option value ="Affilié" selected="true" >
                                                    Affilié
                                                </option>
                                            </select>
                                            <input name="idben" type="hidden" value="Affilié"/>
                                        </div>
                                        <div class="col-xl-1">
                                            <input type="text" class="input-xin" name="Adresse" placeholder="Adresse" value="<%=Array.get(persa,5)%>" />
                                        </div>
                                        <div class="col-xl-1">
                                            <select name="idgen" class="input-xin-select" style="width: 100%">
                                                <option value ="" >
                                                    À choisir
                                                </option>
                                                <%for(Genre g : Genre.values()){
                                                    if(g.name().equals((String)(Array.get(persa,0)))){%>
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
                                            <input type="text" class="input-xin" name="Mail" placeholder="Mail" value="<%=Array.get(persa,6)%>" />
                                        </div>

                                    <%}%>
                                </div>

                                <h3 class="mb-30">Ayants droits</h3>
                                <div class="row" id="0">
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
                                        <label for="idpopAD">Population<span class="requis">*</span></label>
                                    </div>
                                    <div class="col-xl-1">
                                        <label for="idbenAD">Beneficiaire<span class="requis">*</span></label>
                                    </div>
                                    <div class="col-xl-1">
                                        <label for="AdresseAD">Adresse<span class="requis">*</span></label>
                                    </div>
                                    <div class="col-xl-1">
                                        <label for="idgenAD">Genre<span class="requis">*</span></label>
                                    </div>
                                    <div class="col-xl-1">
                                        <label for="Supprimer">Supprimer</label>
                                    </div>
                                    <div class="col-xl-3">
                                        <input type="button" class="info-xin" value="Ajouter" onclick="add()"/>
                                        <input type="submit" class="info-xin" value="Envoyer"/>
                                        <input type="button" class="info-xin" value="Menu" onclick="location.href='Page?action=GestionnaireConnexion'"/>
                                    </div>
                                </div>
                                <%for(int j=0;j<listep.size();j++){
                                    Object[] infos=listep.get(j);%>
                                    <div class="row" id="<%=j+1%>">
                                        <div class="col-xl-1">
                                            <input type="text" class="input-xin" name="NomAD" value="<%=Array.get(infos,1)%>" placeholder="Nom"/>
                                        </div>
                                        <div class="col-xl-1">
                                            <input type="text" class="input-xin" name="PrenomAD" value="<%=Array.get(infos,2)%>" placeholder="Prénom"/>
                                        </div>
                                        <%if(Array.get(infos,3).equals("")){%>
                                            <div class="col-xl-1">
                                                <input id="o<%=j+1%>" class="datepickerxin" name="DateNaiAD" class="datepicker" placeholder="Date naissance" readonly="true">
                                            </div>
                                        <%}else{%>
                                            <div class="col-xl-1">
                                                <input id="o<%=j+1%>" class="datepickerxin" name="DateNaiAD" class="datepicker"  value="<%=Array.get(infos,3)%>" placeholder="Date naissance" readonly="true">
                                            </div>
                                        <%}%>
                                        <div class="col-xl-1">
                                            <input type="text" class="input-xin" name="NumeroSSAD" value="<%=Array.get(infos,4)%>" placeholder="Numéro SS"/>
                                        </div>
                                        <div class="col-xl-1">
                                            <select name="idpopAD"class="input-xin-select" style="width: 100%">
                                                <option value ="" >
                                                    À choisir
                                                </option>
                                                <% for (Population pop : lesp){
                                                    if(pop.getId().toString().equals(Array.get(listep.get(j),6))){%>
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
                                            <select name="idbenAD"class="input-xin-select" style="width: 100%">
                                                <option value ="" >
                                                    À choisir
                                                </option>
                                                <% for (Beneficiaire ben : lesb) if(!ben.getLibelleBeneficiaire().equalsIgnoreCase("Affilie")){
                                                    if(ben.getId().toString().equals(Array.get(listep.get(j),7))){%>
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
                                            <input type="text" class="input-xin" name="AdresseAD" placeholder="Adresse" value="<%=Array.get(listep.get(j),5)%>"/>
                                        </div>
                                        <div class="col-xl-1">
                                            <select name="idgenAD" class="input-xin-select" style="width: 100%">
                                                <option value ="" >
                                                    À choisir
                                                </option>
                                                <%for(Genre g : Genre.values()){
                                                    if(g.name().equals((String)(Array.get(listep.get(j),0)))){%>
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
                                        <div class="col-xl-1" style="padding-top: 10px">
                                            <input type="button" class="danger-xin" value="Supprimer" onclick="sup(<%=j+1%>)"/>
                                        </div>
                                    </div>
                                <%}%>
                            </div>
                        </div>
                    </div>
                </blockquote>
            </fieldset>
            <input type="hidden" name="idprod" value="<%=idprod%>"/>
            <input type="hidden" name="action" value="GestionnaireCreerContratCollectif"/>
            
            
            <script>
                ayantdrois = document.getElementById("b");
                if(ayantdrois!==null){
                    n=parseInt(ayantdrois.lastElementChild.id);
                    console.log(n);
                }
                else{
                    n=0;
                    console.log("no n");
                }
                function add(){
                    n++;
                    copieligne = document.createElement("DIV");
                    copieligne.innerHTML = `<div class="col-xl-1">
                                                <input type="text" class="input-xin" name="NomAD" placeholder="Nom"/>
                                            </div>
                                            <div class="col-xl-1">
                                                <input type="text" class="input-xin" name="PrenomAD" placeholder="Prénom"/>
                                            </div>
                                            <div class="col-xl-1">
                                                <input id="n`+n+`" class="datepickerxin" name="DateNaiAD" placeholder="Date naissance" readonly>
                                            </div>
                                            <div class="col-xl-1">
                                                <input type="text" class="input-xin" name="NumeroSSAD" placeholder="Numéro SS"/>
                                            </div>
                                            <div class="col-xl-1">
                                                <select id="aa`+n+`" name="idpopAD" class="input-xin-select" style="width: 100%; display: none;">
                                                    <option value ="" >
                                                        À choisir
                                                    </option>
                                                    <% for (Population pop : lesp){%>
                                                        <option value ="<%=pop.getId()%>">
                                                            <%=pop.getLibellePopulation()%>
                                                        </option>
                                                    <%}%>
                                                </select>
                                                <div class="nice-select input-xin-select" tabindex="0">
                                                    <span class="current">
                                                        À choisir
                                                    </span>
                                                    <ul class="list">
                                                        <li data-value="" class="option selected">
                                                            À choisir
                                                        </li>
                                                        <% for (Population pop : lesp){%>
                                                            <li data-value="<%=pop.getId()%>" class="option">
                                                                <%=pop.getLibellePopulation()%>
                                                            </li>
                                                        <%}%>
                                                    </ul>
                                                </div>
                                            </div>
                                            <div class="col-xl-1">
                                                <select id="bb`+n+`" name="idbenAD" class="input-xin-select" style="width: 100%; display: none;">
                                                    <option value ="" >
                                                        À choisir
                                                    </option>
                                                    <% for (Beneficiaire ben : lesb) 
                                                        if(!ben.getLibelleBeneficiaire().equalsIgnoreCase("Affilie")){%>
                                                        <option value ="<%=ben.getId()%>">
                                                            <%=ben.getLibelleBeneficiaire()%>
                                                        </option>
                                                    <%}%>
                                                </select>
                                                <div class="nice-select input-xin-select" tabindex="0">
                                                    <span class="current">
                                                        À choisir
                                                    </span>
                                                    <ul class="list">
                                                        <li data-value="" class="option selected">
                                                            À choisir
                                                        </li>
                                                        <% for (Beneficiaire ben : lesb) 
                                                            if(!ben.getLibelleBeneficiaire().equalsIgnoreCase("Affilie")){%>
                                                            <li data-value="<%=ben.getId()%>" class="option">
                                                                <%=ben.getLibelleBeneficiaire()%>
                                                            </li>
                                                        <%}%>
                                                    </ul>
                                                </div>
                                            </div>
                                            <div class="col-xl-1">
                                                <input type="text" class="input-xin" name="AdresseAD" placeholder="Adresse"/>
                                            </div>
                                            <div class="col-xl-1">
                                                <select id="cc`+n+`" name="idgenAD" class="input-xin-select" style="width: 100%; display: none;">
                                                    <option value ="" >
                                                        À choisir
                                                    </option>
                                                    <%for(Genre g : Genre.values()){%>
                                                        <option value ="<%=g.name()%>" >
                                                            <%=g.name()%>
                                                        </option>
                                                    <%}%>
                                                </select>
                                                <div class="nice-select input-xin-select" tabindex="0">
                                                    <span class="current">
                                                        À choisir
                                                    </span>
                                                    <ul class="list">
                                                        <li data-value="" class="option selected">
                                                            À choisir
                                                        </li>
                                                        <%for(Genre g : Genre.values()){%>
                                                            <li data-value="<%=g.name()%>" class="option">
                                                                <%=g.name()%>
                                                            </li>
                                                        <%}%>
                                                    </ul>
                                                </div>
                                            </div>
                                            <div class="col-xl-1" style="padding-top: 10px">
                                                <input type="button" class="danger-xin" value="Supprimer" onclick="sup(`+n+`)"/>
                                            </div>`
                    copieligne.className="row";
                    copieligne.id=n;
//                    $('#a'+n)
                    ayantdrois.appendChild(copieligne);
                    
                    
                    
                    today = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate());
                    $('#n'+n).datepicker({
                        dateFormat: 'yy-mm-dd',
                        maxDate: today,
                        changeMonth: true,
                        changeYear: true,
                        showRightIcon: false
                    });
                }
            </script>
        </form>
        <%@ include file="Footer.jsp"%>
    </body>
</html>
