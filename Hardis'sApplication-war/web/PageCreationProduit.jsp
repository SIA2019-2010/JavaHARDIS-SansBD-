<%-- 
    Document   : PageCreationProduit
    Created on : 31 mars 2020, 03:00:24
    Author     : lixin
--%>

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
        <%
            List<Beneficiaire> listeb=listben;
            List<PersonneMorale> listep=listepersmo;
            List<Population> listepop=listpop;
            List<Domaine> listed=listdo;
            List<TypeProduit> listetp=listtp;
            List<Fiscalite> listef=listf;
            List<TypeGarantie> listetg=listtg;
            //DateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
        %>
    </head>
    <body>
        <h1>Hello World!</h1>
        <select name="idb">
            <%for(Beneficiaire b:listeb){%>
                <option value ="<%=b.getId()%>"><%=b.getId()%><%=b.getLibelleBeneficiaire()%></option>
            <%}%>
        </select><br/>
        <select name="idp">
            <option value ="0">0"ContratIndividuel"</option>
            <%for(PersonneMorale p:listep){%>
                <option value ="<%=p.getId()%>"><%=p.getId()%><%=p.getRaisonSociale()%></option>
            <%}%>
        </select><br/>
        <select name="idd">
            <%for(Domaine d:listed){%>
                <option value ="<%=d.getId()%>"><%=d.getId()%><%=d.getLibelleDomaine()%></option>
            <%}%>
        </select><br/>
        <%for(Beneficiaire b:listeb){%>
            <input type="checkbox" name="idbs" value="<%=b.getId()%>"/><%=b.getId()%><%=b.getLibelleBeneficiaire()%>
        <%}%><br/>
        
        <%for(Population pop:listepop){%>
            <input type="checkbox" name="idpops" value="<%=pop.getId()%>"/><%=pop.getId()%><%=pop.getLibellePopulation()%>
        <%}%><br/>
        
        <%for(Fiscalite f:listef){%>
            <input type="checkbox" name="idfs" value="<%=f.getId()%>"/><%=f.getId()%><%=f.getTaxe()%>
        <%}%><br/>
        
        <%for(TypeGarantie tg:listetg){%>
            <input type="checkbox" name="idtgs" value="<%=tg.getId()%>"/><%=tg.getId()%><%=tg.getTypeGarantie()%>
        <%}%><br/>
        
        <%for(TypeProduit tp:listetp){%>
            <input type="checkbox" name="idtps" value="<%=tp.getId()%>"/><%=tp.getId()%><%=tp.getLibelleTypeProduit()%>
        <%}%><br/>
        
        <input type="hidden" name="action" value="GestionnaireCreationProduit"/>
        <input type ="submit" value="Valider" />
        <input type ="reset" value="Remettre à zero" /> <br/>
    </body>
</html>
