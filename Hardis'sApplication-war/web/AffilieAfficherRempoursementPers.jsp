<%-- 
    Document   : AffilieAfficherRempoursementPers
    Created on : 20 mars 2020, 01:32:12
    Author     : lixin
--%>

<%@page import="entitee.PersonnePhysique"%>
<%@page import="entitee.Remboursement"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="listeremboursement" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="sessionaffilie" scope="session" class="PersonnePhysique"></jsp:useBean>
        <%
            List<Remboursement>liste=listeremboursement;
        %>
        <%session.setAttribute("titre", "Actes Remboursés");%>
        <%@ include file="Style.jsp"%>
    <body>
        <%@ include file="Header.jsp"%>
        <%session.setAttribute("t1", "Liste des remboursements");%>
        <%session.setAttribute("t2", "Informations des remboursements réalisés");%>
        <%@ include file="Header1.jsp"%>
        <fieldset>
            <section id="about-area" class="section-padding-menu">
                <div class="container">
                    <div class="about-feature-area" >
                        <div class="titreTableau" id="b">
                            <div class='titreTableau'>
                                <h3 class="mb-30">Liste remboursements réalisés : <%=sessionaffilie.getNumeroSS()%></h3>
                            </div>
                            
                            <table class="tableauListe">
                                <thead>
                                    <tr>
                                        <th Width="25%">
                                            ID Acte
                                        </th>
                                        <th Width="15%">
                                            Date Début
                                        </th>
                                        <th Width="15%">
                                            Date Fin
                                        </th>
                                        <th Width="25%">
                                            Dépense
                                        </th>
                                        <th Width="10%">
                                            Nom Praticien
                                        </th>
                                        <th Width="10%">
                                            Remboursé
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%for(Remboursement r:liste){%>
                                        <tr>
                                            <td><%=r.getLeActe().getId()%></td>
                                            <td><%=r.getLeActe().getDateDebut()%></td>
                                            <td><%=r.getLeActe().getDateFin()%></td>
                                            <td><%=r.getLeActe().getDepense()%></td>
                                            <td><%=r.getLeActe().getLePraticien().getNom()%></td>
                                            <td><%=r.getRemboursementEffectif()%></td>
                                        </tr>
                                    <%}%>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-cl-4">
                            <input type="button" class="info-xin" style="height: 100%; margin-left: 45%; margin-bottom: 10%" value="Menu" onclick="location.href='Page?action=AffilieConnexion'"/>
                        </div>
                    </div>
                </div>
            </section>
        </fieldset>
        <%@ include file="Footer.jsp"%>
    </body>
</html>
