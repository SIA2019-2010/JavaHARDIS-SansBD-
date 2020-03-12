<%-- 
    Document   : AfficherFournisseur
    Created on : 11 nov. 2019, 18:16:33
    Author     : lixin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="action" scope="request" class="String"></jsp:useBean>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="js.js"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                $('input[type="radio"]').click(function(){
                    $(".tout").hide();
                    $("."+$(this).attr("value")).show();
                });
                $(".tout").hide();
                $("."+"<%=action%>").show();
                $('#<%=action%>').prop("checked", true);
            });
        </script>
    </head>
    <body>
        <h1>CreerArticle</h1>
        <form method="post" action="Page">
            <fieldset>
                <legend>Informations Fournisseur</legend>
                <input name="action" type="radio" id="GestionnaireConnexion" value="GestionnaireConnexion">AgentConnexion
                <input name="action" type="radio" id="AffilieConnexion" value="AffilieConnexion">ClientConnexion
                <input name="action" type="radio" id="ResponsableConnexion" value="ResponsableConnexion">Publique<br/>
                
                <div class="GestionnaireConnexion tout">
                    <jsp:include page="GestionnaireConnexion.jsp"/>
                </div>
                <div class="AffilieConnexion tout">
                    <jsp:include page="AffilieConnexion.jsp"/>
                </div>
                <div class="ResponsableConnexion tout">
                    <jsp:include page="ResponsableConnexion.jsp"/>
                </div>
            </fieldset>
        </form>
    </body>
</html>
