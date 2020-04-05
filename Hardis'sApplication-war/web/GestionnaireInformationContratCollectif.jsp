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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        <link rel="stylesheet" href="jqueryui/jqueryui.css">
        <script type="text/javascript" src="jqueryui/external/jquery/jquery.js"></script>
        <script type="text/javascript" src="jqueryui/jqueryui.js"></script>
        <script>
            $(function() { 
                $(".datepicker").datepicker({
                    dateFormat: 'yy-mm-dd',
                    maxDate: 0,
                    changeMonth: true,
                    changeYear: true,
                    yearRange:'c-100:c'
                });
            });
            function sup(m){
                //console.log(m);
                supprligne = document.getElementById(m);
                console.log(supprligne);
                if(supprligne.parentNode){
                    supprligne.parentNode.removeChild(supprligne);
                }
            }
            function add(){
                /*ayantdrois = document.getElementById("b");
                copieligne=ligne.cloneNode(true);
                ayantdrois.appendChild(copieligne);
                console.log("copy");*/
                ayantdrois = document.getElementById("b");
                n=parseInt(ayantdrois.tBodies[0].lastElementChild.id);
                n=n+1;
                copieligne = document.createElement("TR");
                copieligne.innerHTML = `<tr>
                                            <td>
                                                <input type="text" name="NomAD"/>
                                            </td>
                                            <td>
                                                <input type="text" name="PrenomAD"/>
                                            </td>
                                            <td>
                                                <input type="text" name="DateNaiAD" class="datepicker" placeholder="Date de naissance" readonly>
                                            </td>
                                            <td>
                                                <input type="text" name="NumeroSSAD"/>
                                            </td>
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
                                                    <% for (Beneficiaire ben : lesb) 
                                                        if(!ben.getLibelleBeneficiaire().equalsIgnoreCase("Affilie")){%>
                                                        <option value ="<%=ben.getId()%>">
                                                            <%=ben.getLibelleBeneficiaire()%>
                                                        </option>
                                                    <%}%>
                                                </select>
                                            </td>
                                            <td>
                                                <input type="text" name="AdresseAD"/>
                                            </td>
                                            <td>
                                                <select name="idgenAD" style="width: 100%">
                                                    <option value ="" >
                                                        À choisir
                                                    </option>
                                                    <%for(Genre g : Genre.values()){%>
                                                        <option value ="<%=g.name()%>" >
                                                            <%=g.name()%>
                                                        </option>
                                                    <%}%>
                                                </select>
                                            </td>
                                            <td>
                                                <input style="width: 100%" type="button" value="sup" onclick="sup(`+n+`)"/>
                                            </td>
                                        </tr>`
                copieligne.id=n;
    
                ayantdrois.tBodies[0].appendChild(copieligne);
                $(".datepicker").datepicker({
                    dateFormat: 'yy-mm-dd',
                    maxDate: 0,
                    changeMonth: true,
                    changeYear: true,
                    yearRange:'c-100:c'
                });
            }
            /*$(document).ready(function(){        
                createur = document.getElementById("a");
                ligne = createur.cloneNode(true);
                console.log("ready");
            });*/
            
            
                
        </script>
    </head>
    <body>
        <form method="post" action="Page">
            <fieldset>
                <h1>Renseigner informations</h1>
                <h2>Personne créateur</h2>
                <p><%=message%></p>
                <p><%=idprod%></p>
                <p><%=(String)(Array.get(persa,7))%></p>
                <table width="80%">
                    <tr>
                        <td width="10%">
                            <label for="Nom">Nom<span class="requis">*</span></label>
                        </td>
                        <td width="10%">
                            <label for="Prenom">Prénom<span class="requis">*</span></label>
                        </td>
                        <td width="10%">
                            <label for="DateNaissance">Date de naissance<span class="requis">*</span></label>
                        </td>
                        <td width="10%">
                            <label for="NumeroSS">Numéro SS<span class="requis">*</span></label>
                        </td>
                        <td width="10%">
                            <label for="Population">Population<span class="requis">*</span></label>
                        </td>
                        <td width="10%">
                            <label for="Beneficiaire">Beneficiaire<span class="requis">*</span></label>
                        </td>
                        <td width="10%">
                            <label for="Adresse">Adresse<span class="requis">*</span></label>
                        </td>
                        <td width="10%">
                            <label for="Genre">Genre<span class="requis">*</span></label>
                        </td>
                        <td width="10%">
                            <label for="Mail">Mail<span class="requis">*</span></label>
                        </td>
                    </tr>
                        <%if(persa==null){%>
                                <td>
                                    <input type="text" name="Nom" />
                                </td>
                                <td>
                                    <input type="text" name="Prenom" />
                                </td>
                                <td>
                                    <input type="text" name="DateN" class="datepicker" placeholder="Date de naissance" readonly="true"/>
                                </td>
                                <td>
                                    <input type="text" name="NumeroSS" />
                                </td>
                                <td>
                                    <select name="idpop" style="width: 100%">
                                        <option value ="" >
                                            À choisir
                                        </option>
                                        <%for(Population pop : lesp){%>
                                            <option value ="<%=pop.getId()%>" >
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
                                <td>
                                    <input type="text" name="Mail" />
                                </td>
                                
                            <%}else{%>
                                <td>
                                    <input type="text" name="Nom" value="<%=Array.get(persa,1)%>" />
                                </td>
                                <td>
                                    <input type="text" name="Prenom" value="<%=Array.get(persa,2)%>" />
                                </td>
                                <%if(Array.get(persa,3).equals("")){%>
                                    <td>
                                        <input type="text" name="DateN" class="datepicker" placeholder="Date de naissance" readonly="true">
                                    </td>
                                <%}else{%>
                                    <td>
                                        <input type="text" name="DateN" class="datepicker"  value="<%=Array.get(persa,3)%>" readonly="true">
                                    </td>
                                <%}%>
                                <td>
                                    <input type="text" name="NumeroSS" value="<%=Array.get(persa,4)%>" />
                                </td>
                                <td>
                                    <select name="idpop" style="width: 100%">
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
                                <td>
                                    <input type="text" name="Mail" value="<%=Array.get(persa,6)%>" />
                                </td>
                                
                            <%}%>
                        </tr>
                        
                </table>
                <h2>Ayants droits</h2>
                <table width="80%" id="b">
                    <tr id="0">
                        <td width="10%">
                            <label for="Nom">Nom<span class="requis">*</span></label>
                        </td>
                        <td width="10%">
                            <label for="Prenom">Prénom<span class="requis">*</span></label>
                        </td>
                        <td width="10%">
                            <label for="DateNaissance">Date de naissance<span class="requis">*</span></label>
                        </td>
                        <td width="10%">
                            <label for="NumeroSS">Numéro SS<span class="requis">*</span></label>
                        </td>
                        <td width="10%">
                            <label for="idpopAD">Population<span class="requis">*</span></label>
                        </td>
                        <td width="10%">
                            <label for="idbenAD">Beneficiaire<span class="requis">*</span></label>
                        </td>
                        <td width="10%">
                            <label for="AdresseAD">Adresse<span class="requis">*</span></label>
                        </td>
                        <td width="10%">
                            <label for="idgenAD">Genre<span class="requis">*</span></label>
                        </td>
                        <td width="10%">
                            <label for="Supprimer">Supprimer</label>
                        </td>
                    </tr>
                    <%for(int j=0;j<listep.size();j++){
                        Object[] infos=listep.get(j);%>
                        <tr id="<%=j+1%>">
                            <td>
                                <input type="text" name="NomAD" value="<%=Array.get(infos,1)%>"/>
                            </td>
                            <td>
                                <input type="text" name="PrenomAD" value="<%=Array.get(infos,2)%>"/>
                            </td>
                            <%if(Array.get(infos,3).equals("")){%>
                                <td>
                                    <input type="text" name="DateNaiAD" class="datepicker" placeholder="Date de naissance" readonly="true">
                                </td>
                            <%}else{%>
                                <td>
                                    <input type="text" name="DateNaiAD" class="datepicker"  value="<%=Array.get(infos,3)%>" readonly="true">
                                </td>
                            <%}%>
                            <td>
                                <input type="text" name="NumeroSSAD" value="<%=Array.get(infos,4)%>"/>
                            </td>
                            <td>
                                <select name="idpopAD" style="width: 100%">
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
                            </td>
                            <td>
                                <select name="idbenAD" style="width: 100%">
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
                            </td>
                            <td>
                                <input type="text" name="AdresseAD" value="<%=Array.get(listep.get(j),5)%>"/>
                            </td>
                            <td>
                                <select name="idgenAD" style="width: 100%">
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
                            </td>
                            <td>
                                <input style="width: 100%" type="button" value="sup" onclick="sup(<%=j+1%>)"/>
                            </td>
                        </tr>
                    <%}%>
                </table>
            </fieldset>
            <input type="button" value="add" onclick="add()"/>
            <input type="hidden" name="idprod" value="<%=idprod%>"/>
            <input type="hidden" name="action" value="GestionnaireCreerContratCollectif"/>
            <button type="submit" value="Valider">Envoyer</button>
            <input type="button" value="Revenir à l'accueil" onclick="location.href='Page?action=vide'"/>
        </form>
    </body>
</html>
