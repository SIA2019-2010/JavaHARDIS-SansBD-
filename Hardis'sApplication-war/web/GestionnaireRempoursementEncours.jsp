<%-- 
    Document   : GestionnaireRempoursementEncours
    Created on : 31 mars 2020, 00:34:01
    Author     : lixin
--%>

<%@page import="entitee.Remboursement"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="message" scope="request" class="String"></jsp:useBean>
        <jsp:useBean id="lister" scope="request" class="java.util.List"></jsp:useBean>
        <%
            List<Remboursement> liste=lister;
            //DateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
        %>
        <%session.setAttribute("titre", "Traiter Remboursements");%>
        <%@ include file="Style.jsp"%>


    <body>
        <%@ include file="Header.jsp"%>
        <%session.setAttribute("t1", "Liste des remboursements");%>
        <%session.setAttribute("t2", "Choisisez un remboursement à traiter");%>
        <%@ include file="Header1.jsp"%>
        <fieldset>
            <section id="about-area" class="section-padding-menu">
                <div class="container">
                    <div class="about-feature-area" >
                        <div class="titreTableau" id="b">
                            <div class='titreTableau'>
                                <h3 class="mb-30">Liste demandes remboursements</h3>
                            </div>
                            
                            <table class="tableauListe">
                                <thead>
                                    <tr>
                                        <th Width="25%">
                                            ID Acte
                                        </th>
                                        <th Width="15%">
                                            Nom
                                        </th>
                                        <th Width="15%">
                                            Prénom
                                        </th>
                                        <th Width="25%">
                                            Dépense
                                        </th>
                                        <th Width="10%">
                                            Valider
                                        </th>
                                        <th Width="10%">
                                            Refuser
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%for(Remboursement r:liste){%>
                                        <tr>
                                            <td><%=r.getLeActe().getId()%></td>
                                            <td><%=r.getLeActe().getLaPersonnePhysique().getNom()%></td>
                                            <td><%=r.getLeActe().getLaPersonnePhysique().getPrenom()%></td>
                                            <td><%=r.getLeActe().getDepense()%></td>
                                            <td>
                                                <form method="post" action="Page">
                                                    <input type="hidden" value="<%=r.getId().toString()%>" name="idrv"/>
                                                    <input type="hidden" value="GestionnaireValiderRemboursement" name="action"/>
                                                    <input type="submit" class='info-xin' value="Valider"/>
                                                </form>
                                            </td>
                                            <td>
                                                <form method="post" action="Page">
                                                    <input type="hidden" value="<%=r.getId().toString()%>" name="idrr"/>
                                                    <input type="hidden" value="GestionnaireRefuserRemboursement" name="action"/>
                                                    <input type="submit" class="danger-xin" value="Refuser"/>
                                                </form>
                                            </td>
                                        </tr>
                                    <%}%>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-cl-4">
                            <input type="button" class="info-xin" style="height: 100%; margin-left: 45%; margin-bottom: 10%" value="Menu" onclick="location.href='Page?action=GestionnaireConnexion'"/>
                        </div>
                    </div>
                </div>
            </section>
        </fieldset>
        <%@ include file="Footer.jsp"%>
    </body>
</html>
