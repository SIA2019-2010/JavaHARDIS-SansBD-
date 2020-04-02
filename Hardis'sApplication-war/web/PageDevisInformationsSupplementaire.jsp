<%-- 
    Document   : RenseignementInformationsSupplementaire.jsp
    Created on : 26 mars 2020, 12:04:27
    Author     : lixin
--%>

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
        <jsp:useBean id="devis" scope="request" class="Devis"></jsp:useBean>
        <jsp:useBean id="listben" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listpop" scope="request" class="java.util.List"></jsp:useBean>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            List<Beneficiaire>lesb=listben;
            List<Population>lesp=listpop;
            PersonnePhysique pers=devis.getLaPersonne();
            List<PersonnePhysique> listepers=devis.getLesAyantsDroit();
            DateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
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
                        <td>
                            <input type="text" name="Nom" value="<%=pers.getNom()%>" STYLE="background-color:#CCCCCC;" disabled/>
                        </td>
                        <td>
                            <input type="text" name="Prenom" value="<%=pers.getPrenom()%>" STYLE="background-color:#CCCCCC;" disabled/>
                        </td>
                        <td>
                            <input type="text" name="DateN" value="<%=formatter.format(pers.getDateNaiss())%>" STYLE="background-color:#CCCCCC;" disabled>
                        </td>
                        <td>
                            <input type="text" name="NumeroSS" value="<%=pers.getNumeroSS()%>" STYLE="background-color:#CCCCCC;" disabled/>
                        </td>
                        <td>
                            <input type="text" name="Mail" value="<%=pers.getMail()%>" STYLE="background-color:#CCCCCC;" disabled/>
                        </td>
                        <td>
                            <select name="idpop" style="width: 100%" STYLE="background-color:#CCCCCC;" disabled>
                                <option value ="<%=pers.getLaPopulation().getLibellePopulation()%>" selected="true" STYLE="background-color:#CCCCCC;">
                                    <%=pers.getLaPopulation().getLibellePopulation()%>
                                </option>
                            </select>
                        </td>
                        <td>
                            <select name="idben" style="width: 100%" STYLE="background-color:#CCCCCC;" disabled>
                                <option value ="Affilié" selected="true" STYLE="background-color:#CCCCCC;">
                                    Affilié
                                </option>
                            </select>
                        </td>
                        <td>
                            <input type="text" name="Adresse" />
                        </td>
                        <td>
                            <select name="idgen" style="width: 100%">
                                <%for(Genre g : Genre.values()){%>
                                    <option value ="<%=g.name()%>" ><%=g.name()%></option>
                                <%}%>
                            </select>
                        </td>
                        <td>
                            <input type="text" name="Login" />
                        </td>
                        <td>
                            <input type="text" name="MDP" />
                        </td>
                        <td>
                            <input type="text" name="RIB" />
                        </td>
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
                    <%for(PersonnePhysique p : listepers){%>
                        <tr>
                            <td>
                                <input type="text" name="NomAD" value="<%=p.getNom()%>"STYLE="background-color:#CCCCCC;" disabled/>
                            </td>
                            <td>
                                <input type="text" name="PrenomAD" value="<%=p.getPrenom()%>"STYLE="background-color:#CCCCCC;" disabled/>
                            </td>
                            <td>
                                <input type="text" name="DateNaiAD" value="<%=formatter.format(p.getDateNaiss())%>" STYLE="background-color:#CCCCCC;" disabled/>
                            </td>
                            <td>
                                <input type="text" name="NumeroSSAD" value="<%=p.getNumeroSS()%>" STYLE="background-color:#CCCCCC;" disabled/>
                            </td>
                            <td>
                                <select name="idpopAD" style="width: 100%">
                                    <% for (Population pop : lesp){%>
                                        <option value ="<%=pop.getId()%>">
                                            <%=pop.getLibellePopulation()%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                            <td>
                                <select name="idbenAD" style="width: 100%">
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
                                    <%for(Genre g : Genre.values()){%>
                                        <option value ="<%=g.name()%>" ><%=g.name()%></option>
                                    <%}%>
                                </select>
                            </td>
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
