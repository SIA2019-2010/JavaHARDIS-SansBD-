<%-- 
    Document   : PageCreationDevis.jsp
    Created on : 13 mars 2020, 15:27:07
    Author     : lixin
--%>
<%@page import="java.util.List"%>
<%@page import="entitee.Population"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Renseigner informations</title>
        <jsp:useBean id="listepopulation" scope="request" class="java.util.List"></jsp:useBean>
        <script type="text/javascript" src="js.js"></script>
        <script>
            function add(){
                ayantdrois = document.getElementById("b");
                copieligne=ligne.cloneNode(true);
                ayantdrois.appendChild(copieligne);
                console.log("copy");
            }
            $(document).ready(function(){        
                createur = document.getElementById("a");
                ligne = createur.cloneNode(true);
                console.log("ready");
            });
        </script>
    </head>
    <body>
        <%List<Population>lesPops=listepopulation;%>
        <h1>Renseigner informations</h1>
        <h2>Personne créateur</h2>
            <table>
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
                        <label for="Population">Population<span class="requis">*</span></label>
                    </td>
                </tr>
                <tr id="a">
                    <td>
                        <input type="text" name="Nom"/>
                    </td>
                    <td>
                        <input type="text" name="Prenom"/>
                    </td>
                    <td>
                        <input type="text" name="DateN" id="datepicker" placeholder="Date de naissance">
                    </td>
                    <td>
                        <input type="text" name="NumeroSS"/>
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
            </table>
        <h2>Ayants droits</h2>
        <table id="b">
            <tr>
                <td>
                    <label for="Nom">Nom<span class="requis">*</span></label>
                </td>
                <td>
                    <label for="Prenom">Prénom<span class="requis">*</span></label>
                </td>
                <td>
                    <label for="DateNaissance">Date de naissance<span class="requis">*</span></label>
                </td>
                <td>
                    <label for="NumeroSS">Numéro SS<span class="requis">*</span></label>
                </td>
                <td>
                    <label for="Population">Population<span class="requis">*</span></label>
                </td>
            </tr>
        </table>
        
        <input type="button" value="add" onclick="add()"/>
        <ul id="ul"><li><input type="text"/></li></ul>
        </html>
    </body>
</html>
