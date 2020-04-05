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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:useBean id="message" scope="request" class="String"></jsp:useBean>
        <jsp:useBean id="listinfos" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listben" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listpop" scope="request" class="java.util.List"></jsp:useBean>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            List<Beneficiaire>lesb=listben;
            List<Population>lesp=listpop;
            DateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
            List<Object[]>listep=listinfos;
            Object[] persa=(Object[])request.getAttribute("persa");
        %>
        <form method="post" action="Page">
            <p><%=message%></p>
            <h1>Renseigner informations</h1>
            
            <fieldset>
                <h2>Personne créateur</h2>
                <table width="100%">
                    <tr>
                        <td width="9%">
                            <label for="Nom">Nom<span class="requis">*</span></label>
                        </td>
                        <td width="9%">
                            <label for="Prenom">Prénom<span class="requis">*</span></label>
                        </td>
                        <td width="9%">
                            <label for="DateNaissance">Date de naissance<span class="requis">*</span></label>
                        </td>
                        <td width="9%">
                            <label for="NumeroSS">Numéro SS<span class="requis">*</span></label>
                        </td>
                        <td width="9%">
                            <label for="Mail">Mail<span class="requis">*</span></label>
                        </td>
                        <td width="7%">
                            <label for="Population">Population<span class="requis">*</span></label>
                        </td>
                        <td width="7%">
                            <label for="Beneficiaire">Beneficiaire<span class="requis">*</span></label>
                        </td>
                        <td width="9%">
                            <label for="Adresse">Adresse<span class="requis">*</span></label>
                        </td>
                        <td width="5%">
                            <label for="Genre">Genre<span class="requis">*</span></label>
                        </td>
                        <td width="9%">
                            <label for="Login">Login<span class="requis">*</span></label>
                        </td>
                        <td width="9%">
                            <label for="MDP">Mot de passe<span class="requis">*</span></label>
                        </td>
                        <td width="9%">
                            <label for="RIB">RIB<span class="requis">*</span></label>
                        </td>
                    </tr>
                    <tr>
                        <%if(persa==null){%>
                            <td>
                                <input type="text" name="Nom" />
                            </td>
                            <td>
                                <input type="text" name="Prenom" />
                            </td>
                            <td>
                                <input type="text" name="DateN" />
                            </td>
                            <td>
                                <input type="text" name="NumeroSS" />
                            </td>
                            <td>
                                <input type="text" name="Mail" />
                            </td>
                            <td>
                                <select name="idpop" style="width: 100%">
                                    <option value ="" >
                                        À choisir
                                    </option>
                                    <%for(Population pop : lesp){%>
                                        <option value ="<%=pop.getLibellePopulation()%>" >
                                            <%=pop.getLibellePopulation()%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                            <td>
                                <select style="width: 100%" disabled>
                                    <option value ="Affilié" selected="true" >
                                        Affilié
                                    </option>
                                </select>
                                <input name="idben" type="hidden" value="Affilié"/>
                            </td>
                            <td>
                                <input type="text" name="Adresse" />
                            </td>
                            <td>
                                <select name="idgen" style="width: 100%">
                                    <option value ="" >
                                        À choisir
                                    </option>
                                    <%for(Genre g : Genre.values()){%>
                                        <option value ="<%=g.name()%>" ><%=g.name()%></option>
                                    <%}%>
                                </select>
                            </td>
                        <%}else{%>
                            <td>
                                <input type="text" name="Nom" value="<%=Array.get(persa,1)%>" />
                            </td>
                            <td>
                                <input type="text" name="Prenom" value="<%=Array.get(persa,2)%>" />
                            </td>
                            <td>
                                <input type="text" name="DateN" value="<%=formatter.format(Array.get(persa,3)%>" >
                            </td>
                            <td>
                                <input type="text" name="NumeroSS" value="<%=Array.get(persa,4)%>" />
                            </td>
                            <td>
                                <input type="text" name="Mail" value="<%=Array.get(persa,6)%>" />
                            </td>
                            <td>
                                <select name="idpop" style="width: 100%">
                                    <option value ="" >
                                        À choisir
                                    </option>
                                    <%for(Population pop : lesp){
                                        if(pop.getLibellePopulation().equals((String)(Array.get(persa,7)))){%>
                                            <option value ="<%=pop.getLibellePopulation()%>" selected="true">
                                                <%=pop.getLibellePopulation()%>
                                            </option>
                                        <%}else{%>
                                            <option value ="<%=pop.getLibellePopulation()%>" >
                                                <%=pop.getLibellePopulation()%>
                                            </option>
                                        <%}%>
                                    <%}%>
                                </select>
                            </td>
                            <td>
                                <select name="idben" style="width: 100%" disabled>
                                    <option value ="Affilié" selected="true" >
                                        Affilié
                                    </option>
                                </select>
                                <input name="idben" type="hidden" value="Affilié"/>
                            </td>
                            <td>
                                <input type="text" name="Adresse" value="<%=Array.get(persa,5)%>" />
                            </td>
                            <td>
                                <select name="idgen" style="width: 100%">
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
                            </td>
                        <%}%>
                    </tr>                        
                </table>
                <h2>Ayants droits</h2>
                <table width="80%" id="b">
                    <tr>
                        <td width="9%">
                            <label for="Nom">Nom<span class="requis">*</span></label>
                        </td>
                        <td width="9%">
                            <label for="Prenom">Prénom<span class="requis">*</span></label>
                        </td>
                        <td width="9%">
                            <label for="DateNaissance">Date de naissance<span class="requis">*</span></label>
                        </td>
                        <td width="9%">
                            <label for="NumeroSS">Numéro SS<span class="requis">*</span></label>
                        </td>
                        <td width="9%">
                            <label for="Population">Population<span class="requis">*</span></label>
                        </td>
                        <td width="9%">
                            <label for="Beneficiaire">Beneficiaire<span class="requis">*</span></label>
                        </td>
                        <td width="9%">
                            <label for="Adresse">Adresse<span class="requis">*</span></label>
                        </td>
                        <td width="9%">
                            <label for="Genre">Genre<span class="requis">*</span></label>
                        </td>
                    </tr>
                    <%for(Object[] infos : listep){
                        <tr>
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                            <%if(listep.size()==0){%>
                                <td>
                                    <select name="idpopAD" style="width: 100%">
                                        <option value ="" >
                                            À choisir
                                        </option>
                                        <% for (Population pop : lesp){%>
                                            <option value ="<%=pop.getId()%>">
                                                <%=pop.getLibellePopulation()%>
                                            </option>
                                        <%}%>
                                    </select>
                                </td>
                                <td>
                                    <select name="idbenAD" style="width: 100%">
                                        <option value ="" >
                                            À choisir
                                        </option>
                                        <% for (Beneficiaire ben : lesb) if(!ben.getLibelleBeneficiaire().equalsIgnoreCase("Affilie")){%>
                                            <option value ="<%=ben.getId()%>">
                                                <%=ben.getLibelleBeneficiaire()%>
                                            </option>
                                        <%}%>
                                    </select>
                                </td>
                                <td>
                                    <input type="text" name="AdresseAD" />
                                </td>
                                <td>
                                    <select name="idgenAD" style="width: 100%">
                                        <option value ="" >
                                            À choisir
                                        </option>
                                        <%for(Genre g : Genre.values()){%>
                                            <option value ="<%=g.name()%>" ><%=g.name()%></option>
                                        <%}%>
                                    </select>
                                </td>
                            <%}else{%>
                            <td>
                                <input type="text" name="NomAD" value="<%=p.getNom()%>"/>
                            </td>
                            <td>
                                <input type="text" name="PrenomAD" value="<%=p.getPrenom()%>"/>
                            </td>
                            <td>
                                <input type="text" name="DateNaiAD" value="<%=formatter.format(p.getDateNaiss())%>" />
                            </td>
                            <td>
                                <input type="text" name="NumeroSSAD" value="<%=p.getNumeroSS()%>" />
                            </td>
                                <td>
                                    <select name="idpopAD" style="width: 100%">
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
                                </td>
                                <td>
                                    <select name="idbenAD" style="width: 100%">
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
                                </td>
                                <td>
                                    <input type="text" name="AdresseAD" value="<%=Array.get(listep.get(i),3)%>"/>
                                </td>
                                <td>
                                    <select name="idgenAD" style="width: 100%">
                                        <option value ="" >
                                            À choisir
                                        </option>
                                        <%for(Genre g : Genre.values()){
                                            if(g.name().equals((String)(Array.get(listep.get(i),4)))){%>
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
                                </td>
                            <%}%>
                        </tr>
                    <%}%>
                </table>
            </fieldset>
            <input type="hidden" name="iddevis" value="<%=devis.getId().toString()%>"/>
            <input type="hidden" name="action" value="CompleterInformations"/>
            <button type="submit" value="Valider">Envoyer</button>
            <input type="button" value="Revenir à l'accueil" onclick="location.href='Page?action=vide'"/>
        </form>
    </body>
</html>
