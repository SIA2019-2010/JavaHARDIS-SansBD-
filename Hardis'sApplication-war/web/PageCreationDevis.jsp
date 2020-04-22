<%-- 
    Document   : PageCreationDevis.jsp
    Created on : 13 mars 2020, 15:27:07
    Author     : lixin
--%>
<%@page import="entitee.Population"%>
<%@page import="entitee.Beneficiaire"%>
<%@page import="java.lang.reflect.Array"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="typeConnexion" scope="request" class="String"></jsp:useBean>
        <jsp:useBean id="listeinfos" scope="session" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listepopulation" scope="request" class="java.util.List"></jsp:useBean>
        <%
            List<Population>lesPops=listepopulation;
            List<Object[]>listep=listeinfos;
            Object[] pers=(Object[])session.getAttribute("pers");
        %>
        
          
        
        <%--<script>
            $(function() { 
                $(".datepickerxin").datepicker({
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
            }--%>
        <%session.setAttribute("titre", "Renseigner Devis");%>
        <%@ include file="Style.jsp"%>
    <body>
        <%@ include file="Header.jsp"%>
        <%session.setAttribute("t1", "Renseigner informations");%>
        <%session.setAttribute("t2", "Connectez vous pour accéder à votre espace personnel.");%>
        <%@ include file="Header1.jsp"%>
        
                        
                            
                            
        <form method="post" action="Page">
            <fieldset>

                <blockquote class="generic-blockquote">

                    <div class="section-top-border">
                        <div class="rec-xin" id="b">
                            <div class="row">
                                <h3 class="mb-30">Affilié principal </h3>
                                <p>18 ans minimum</p>
                            </div>
                            <div class="row">
                                <div class="col-xl-2">
                                    <label for="Nom">Nom<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-2">
                                    <label for="Prenom">Prénom<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-2">
                                    <label for="DateNaissance">Date naissance<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-2">
                                    <label for="NumeroSS">Numéro SS<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-2">
                                    <label for="Mail">Mail<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-2">
                                    <label for="Population">Population<span class="requis">*</span></label>
                                </div>
                            </div>
                            <%if(pers==null){%>
                                <div class="row">
                                    <div class="col-xl-2">
                                        <input type="text" class="input-xin" placeholder="Nom" name="Nom"/>
                                    </div>
                                    <div class="col-xl-2">
                                        <input type="text" class="input-xin" placeholder="Prénom" name="Prenom"/>
                                    </div>
                                    <div class="col-xl-2">
                                        <input id="o0" class="datepickerxin" name="DateN" placeholder="Date de naissance" readonly>
                                    </div>
                                    <div class="col-xl-2">
                                        <input type="text" class="input-xin" placeholder="Numéro SS" name="NumeroSS"/>
                                    </div>
                                    <div class="col-xl-2">
                                        <input type="text" class="input-xin" placeholder="Mail" name="Mail"/>
                                    </div>
                                    <div class="col-xl-2">
                                        <select name="idpop" class="input-xin-select" style="width: 100%">
                                            <option value ="" >
                                                À choisir
                                            </option>
                                            <% for (Population pop:lesPops){%>
                                                <option value ="<%=pop.getId()%>">
                                                    <%=pop.getLibellePopulation()%>
                                                </option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                            <%}else{%>
                                <div class="row">
                                    <div class="col-xl-2">
                                        <input type="text" class="input-xin" name="Nom" placeholder="Nom" value="<%=Array.get(pers,0)%>"/>
                                    </div>
                                    <div class="col-xl-2">
                                        <input type="text" class="input-xin" name="Prenom" placeholder="Prénom" value="<%=Array.get(pers,1)%>"/>
                                    </div>
                                    <div class="col-xl-2">
                                        <input id="o0" class="datepickerxin" name="DateN" placeholder="Date de naissance" value="<%=Array.get(pers,2)%>" readonly>
            <!--                                <input type="text" class="input-xin" name="DateN" class="datepickerxin"  value="<%=Array.get(pers,2)%>" readonly="true">-->
                                    </div>
                                    <div class="col-xl-2">
                                        <input type="text" class="input-xin" name="NumeroSS" placeholder="Numéro SS" value="<%=Array.get(pers,3)%>"/>
                                    </div>
                                    <div class="col-xl-2">
                                        <input type="text" class="input-xin" name="Mail" placeholder="Mail" value="<%=Array.get(pers,4)%>"/>
                                    </div>
                                    <div class="col-xl-2">
                                        <select name="idpop" class="input-xin-select" style="width: 100%">
                                            <option value ="" >
                                                À choisir
                                            </option>
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
                                    </div>
                                </div>
                            <%}%>   

                            <h3 class="mb-30">Ayants droits</h3>
                            <div class="row" id="0" style="border-bottom: 10px">
                                <div class="col-xl-2">
                                    <label for="Nom">Nom<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-2">
                                    <label for="Prenom">Prénom<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-2">
                                    <label for="DateNaissance">Date naissance<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-2">
                                    <label for="NumeroSS">Numéro SS<span class="requis">*</span></label>
                                </div>
                                <div class="col-xl-4">
                                    <input type="button" class="info-xin" value="Ajouter" onclick="add()"/>
                                    <input type="submit" class="info-xin" value="Envoyer"/>
                                    <input type="button" class="info-xin" value="Menu" onclick="location.href='Page?action=<%=("GestionnaireConnexion".equals(typeConnexion)?"GestionnaireConnexion":"vide")%>'"/>
                                </div>
                            </div>
                            <% for(int j=0;j<listep.size();j++){
                                Object[] infos=listep.get(j); %>
                                <div class="row" id="<%=j+1%>">
                                    <div class="col-xl-2">
                                        <input type="text" class="input-xin" name="NomAD" placeholder="Nom" value="<%=Array.get(infos,0)%>"/>
                                    </div>
                                    <div class="col-xl-2">
                                        <input type="text" class="input-xin" name="PrenomAD" placeholder="Préom" value="<%=Array.get(infos,1)%>"/>
                                    </div>
                                    <div class="col-xl-2">
                                        <input id="o<%=j+1%>" class="datepickerxin" name="DateNaiAD" placeholder="Date de naissance" value="<%=Array.get(infos,2)%>" readonly>
                                    </div>
                                    <div class="col-xl-2">
                                        <input type="text" class="input-xin" name="NumeroSSAD" placeholder="Numéro SS" value="<%=Array.get(infos,3)%>"/>
                                    </div>
                                    <div class="col-xl-2" style="padding-top: 10px">
                                        <input type="button" class="danger-xin" value="Supprimer" onclick="sup(<%=j+1%>)"/>
                                    </div>
                                </div>
                            <%}%>
                        </div>
                    </div>
                </blockquote>
            </fieldset>
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
                    console.log(n);
                    copieligne = document.createElement("DIV");
                    copieligne.innerHTML = `<div class="col-xl-2">
                                                <input type="text" class="input-xin" name="NomAD" placeholder="Nom"/>
                                            </div>
                                            <div class="col-xl-2">
                                                <input type="text" class="input-xin" name="PrenomAD" placeholder="Prénom"/>
                                            </div>
                                            <div class="col-xl-2">
                                                <input id="n`+n+`" class="datepickerxin" name="DateNaiAD" placeholder="Date de naissance" readonly>
                                            </div>
                                            <div class="col-xl-2">
                                                <input type="text" class="input-xin" name="NumeroSSAD" placeholder="Numéro SS"/>
                                            </div>
                                            <div class="col-xl-2" style="padding-top: 10px">
                                                <input type="button" class="danger-xin" value="Supprimer" onclick="sup(`+n+`)"/>
                                            </div>`
                    copieligne.className="row";
                    copieligne.id=n;

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
            <input type="hidden" name="action" value="CalculPrixDevis"/>
<!--            <button type="submit" value="Valider">Envoyer</button>
            <input type="button" value="Revenir à l'accueil" onclick="location.href='Page?action=vide'"/>-->
        </form>
        <%@ include file="Footer.jsp"%>
    </body>
</html>
