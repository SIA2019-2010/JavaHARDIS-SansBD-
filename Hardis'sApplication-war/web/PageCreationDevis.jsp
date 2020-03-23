<%-- 
    Document   : PageCreationDevis.jsp
    Created on : 13 mars 2020, 15:27:07
    Author     : lixin
--%>
<%@page import="java.lang.reflect.Array"%>
<%@page import="java.util.List"%>
<%@page import="entitee.Population"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Renseigner listepopulation</title>
        <jsp:useBean id="listepopulation" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listeinfos" scope="request" class="java.util.List"></jsp:useBean>
        <%
            List<Population>lesPops=listepopulation;
            List<Object[]>listep=listeinfos;
            Object[] pers=(Object[])request.getAttribute("pers");
        %>
        <link rel="stylesheet" href="jqueryui/jqueryui.css">
        <script type="text/javascript" src="jqueryui/external/jquery/jquery.js"></script>
        <script type="text/javascript" src="jqueryui/jqueryui.js"></script>
        <script>
            $(function() { 
                $(".datepicker").datepicker({
                    dateFormat: 'yy-mm-dd',
                    maxDate: 0
                });
            });
            function add(){
                /*ayantdrois = document.getElementById("b");
                copieligne=ligne.cloneNode(true);
                ayantdrois.appendChild(copieligne);
                console.log("copy");*/
                ayantdrois = document.getElementById("b")
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
                                        </tr>`
    
                ayantdrois.tBodies[0].appendChild(copieligne);
                $(".datepicker").datepicker({
                    dateFormat: 'yy-mm-dd',
                    maxDate: 0
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
                <table width="80%">
                    <tr>
                        <td width="15%">
                            <label for="Nom">Nom<span class="requis">*</span></label>
                        </td>
                        <td width="15%">
                            <label for="Prenom">Prénom<span class="requis">*</span></label>
                        </td>
                        <td width="15%">
                            <label for="DateNaissance">Date de naissance<span class="requis">*</span></label>
                        </td>
                        <td width="15%">
                            <label for="NumeroSS">Numéro SS<span class="requis">*</span></label>
                        </td>
                        <td width="15%">
                            <label for="Mail">Mail<span class="requis">*</span></label>
                        </td>
                        <td width="15%">
                            <label for="Population">Population<span class="requis">*</span></label>
                        </td>
                    </tr>
                    <%if(pers==null){%>
                        <tr>
                            <td>
                                <input type="text" name="Nom"/>
                            </td>
                            <td>
                                <input type="text" name="Prenom"/>
                            </td>
                            <td>
                                <input type="text" name="DateN" class="datepicker" placeholder="Date de naissance" readonly="true">
                            </td>
                            <td>
                                <input type="text" name="NumeroSS"/>
                            </td>
                            <td>
                                <input type="text" name="Mail"/>
                            </td>
                            <td>
                                <select name="idpop" style="width: 100%">
                                    <% for (Population pop:lesPops){%>
                                        <option value ="<%=pop.getId()%>">
                                            <%=pop.getLibellePopulation()%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                        </tr>
                    <%}else{%>
                        <tr>
                            <td>
                                <input type="text" name="Nom" value="<%=Array.get(pers,0)%>"/>
                            </td>
                            <td>
                                <input type="text" name="Prenom" value="<%=Array.get(pers,1)%>"/>
                            </td>
                            <%if(Array.get(pers,2).equals("")){%>
                                <td>
                                    <input type="text" name="DateN" class="datepicker" placeholder="Date de naissance" readonly="true">
                                </td>
                            <%}else{%>
                                <td>
                                    <input type="text" name="DateN" class="datepicker"  value="<%=Array.get(pers,2)%>" readonly="true">
                                </td>
                            <%}%>
                            <td>
                                <input type="text" name="NumeroSS" value="<%=Array.get(pers,3)%>"/>
                            </td>
                            <td>
                                <input type="text" name="Mail" value="<%=Array.get(pers,4)%>"/>
                            </td>
                            <td>
                                <select name="idpop" style="width: 100%">
                                    <% for (Population pop:lesPops){
                                        if(pop.getId().toString().equals(Array.get(pers,5))){%>
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
                        </tr>
                    <%}%>
                        
                </table>
                <h2>Ayants droits</h2>
                <table width="80%" id="b">
                    <tr>
                        <td width="15%">
                            <label for="Nom">Nom<span class="requis">*</span></label>
                        </td>
                        <td width="15%">
                            <label for="Prenom">Prénom<span class="requis">*</span></label>
                        </td>
                        <td width="15%">
                            <label for="DateNaissance">Date de naissance<span class="requis">*</span></label>
                        </td>
                        <td width="15%">
                            <label for="NumeroSS">Numéro SS<span class="requis">*</span></label>
                        </td>
                        <td width="15%">
                            <label for="NumeroSS">Mail<span class="requis">*</span></label>
                        </td>
                        <td width="15%">
                            <label for="Population">Population<span class="requis">*</span></label>
                        </td>
                    </tr>
                    <%for(Object[] infos:listep){%>
                        <tr>
                            <td>
                                <input type="text" name="NomAD" value="<%=Array.get(infos,0)%>"/>
                            </td>
                            <td>
                                <input type="text" name="PrenomAD" value="<%=Array.get(infos,1)%>"/>
                            </td>
                            <%if(Array.get(infos,2).equals("")){%>
                                <td>
                                    <input type="text" name="DateNaiAD" class="datepicker" placeholder="Date de naissance" readonly="true">
                                </td>
                            <%}else{%>
                                <td>
                                    <input type="text" name="DateNaiAD" class="datepicker"  value="<%=Array.get(infos,2)%>" readonly="true">
                                </td>
                            <%}%>
                            <td>
                                <input type="text" name="NumeroSSAD" value="<%=Array.get(infos,3)%>"/>
                            </td>
                        </tr>
                    <%}%>
                </table>

                <input type="button" value="add" onclick="add()"/>
                <input type="hidden" name="action" value="CalculPrixDevis"/>
                <button type="submit" value="Valider">Envoyer</button>
                <input type="button" value="Revenir à l'accueil" onclick="location.href='Page?action=vide'"/>
            </fieldset>
        </form>
    </body>
</html>
