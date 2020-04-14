<%-- 
    Document   : PageCreationProduit
    Created on : 31 mars 2020, 03:00:24
    Author     : lixin
--%>

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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:useBean id="message" scope="request" class="String"></jsp:useBean>
        <jsp:useBean id="listben" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listepersmo" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listpop" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listdo" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listtp" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listf" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listtg" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listg" scope="request" class="java.util.List"></jsp:useBean>
        
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
    </head>
    <body>
        <%--<h1>Hello World!</h1>
        
        <h2>Les Bénéficiaires</h2>
        <%for(Beneficiaire b:listeb){%>
            <input type="checkbox" name="idbs" value="<%=b.getId()%>"/>
            <%=b.getLibelleBeneficiaire()%>
        <%}%>
        <br/>
        
        <h2>Les populations</h2>
        <%for(Population pop:listepop){%>
            <input type="checkbox" name="idpops" value="<%=pop.getId()%>"/>
            <%=pop.getId()%><%=pop.getLibellePopulation()%>
        <%}%>
        <br/>
        
        
        <h2>Les TypeGaranties</h2>
        <%for(TypeGarantie tg:listetg){%>
            <input type="checkbox" name="idtgs" value="<%=tg.getId()%>"/>
            <%=tg.getId()%><%=tg.getTypeGarantie()%>
        <%}%>
        <br/>
        
        <h2>La Bénéficiaire</h2>
        <select name="idb">
            <%for(Beneficiaire b:listeb){%>
                <option value="">
                    À choisir
                </option>
                <option value ="<%=b.getId()%>">
                    <%=b.getId()%><%=b.getLibelleBeneficiaire()%>
                </option>
            <%}%>
        </select>
        <br/>
        
        <h2>La Personne</h2>
        <select name="idp">
            <option value ="">
                À choisir
            </option>
            <option value ="0">
                0"ContratIndividuel"
            </option>
            <%for(PersonneMorale p:listep){%>
                <option value ="<%=p.getId()%>">
                    <%=p.getId()%><%=p.getRaisonSociale()%>
                </option>
            <%}%>
        </select><br/>

        <h2>Le Domaine</h2>
        <select name="idd">
            <%for(Domaine d:listed){%>
                <option value ="<%=d.getId()%>">
                    <%=d.getLibelleDomaine()%>
                </option>
            <%}%>
        </select>
        <br/>
        
        <h2>La fiscalité</h2>
        <%for(Fiscalite f:listef){%>
            <select name="idf">
                <option value="">
                    À choisir
                </option>
                <option value="<%=f.getId()%>">
                    <%=f.getTaxe()%>
                </option>
            </select>
        <%}%>
        <br/>
        
        <h2>La TypeProduit</h2>
        <%for(TypeProduit tp:listetp){%>
            <select name="idtp">
                <option value="">
                    À choisir
                </option>
                <option value="<%=tp.getId()%>">
                    <%=tp.getLibelleTypeProduit()%>
                </option>
            </select>
        <%}%><br/>
        
        <%for(Garantie g : listeg ){%>
            <p><%=g.getLibelleGarantie()%></p>
            <select name="idtp">
                <option value="">À choisir</option>
                <option value="0">BF</option>
                <option value="1">TM</option>
                <option value="2">FR</option>
                <option value="3">BR-RSS</option>
                <option value="4">PMSS</option>
                <option value="5">Chiffre</option>
            </select>
            <input type="number" step="0.01" name="oui"/>
            <input type="number" step="0.01" name="non"/>
        <%}%><%--
            
        
        <input type="hidden" name="action" value="GestionnaireCreationProduitCollectif"/>
        <input type ="submit" value="Valider" />
        <input type ="reset" value="Remettre à zero" /> <br/>
    </body>
</html>
