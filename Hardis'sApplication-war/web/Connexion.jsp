<%-- 
    Document   : AfficherFournisseur
    Created on : 11 nov. 2019, 18:16:33
    Author     : lixin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="typeConnexion" scope="request" class="String"></jsp:useBean>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="js.js"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                $('#<%=typeConnexion%>').prop("checked", true);
            });
        </script>
    </head>
    <body>
        <h1>Connexion</h1>
        <form method="post" action="Page">
            <fieldset>
                <legend>Informations </legend>
                <div class="ensembleBooutonsRadio">
                    <div class = "radioButtonStyle">
                        <input name="action" type="radio" id="GestionnaireConnexion" value="GestionnaireAuthen"/>
                        <label for="action">Gestionnaire Connexion</label>
                    </div>
                    <div class="radioButtonStyle">
                        <input name="action" type="radio" id="AffilieConnexion" value="AffilieAuthen"/>
                        <label for="action">Affilie Connexion</label>
                    </div>
                    <div class="radioButtonStyle">
                        <input name="action" type="radio" id="ResponsableConnexion" value="ResponsableAuthen"/>
                        <label for="action">Responsable Connexion</label><br/>
                    </div>
                </div>
                <input type="text" name="Login"/>
                <input type="password" name="MDP"/>
                <button type="submit" value="Valider">Connexion</button>
                <input type="button" value="Revenir à l'accueil" onclick="location.href='Page?action=vide'"/>
                
                
        
                                                    
            </fieldset>
        </form>
    </body>
</html>
