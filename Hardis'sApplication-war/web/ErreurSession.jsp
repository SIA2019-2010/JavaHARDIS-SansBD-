<%-- 
    Document   : ErreurSession
    Created on : 11 janv. 2020, 12:06:46
    Author     : lixin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="message" scope="request" class="String"></jsp:useBean>        
        <jsp:useBean id="typeConnexion" scope="request" class="String"></jsp:useBean>  
        <%session.setAttribute("titre", "Ooooops");%>
        <%@ include file="Style.jsp"%>
    
    <body>
        <%@ include file="Header.jsp"%>
        <%%>
        <%if(message.equals("Erreur : Illégale action réalisée<br/>"
                    + "Contactez avec nous afin de résoudre le problème.<br/>"
                    + "Adresse de Mail : exemple@pfe.fr")){
            session.setAttribute("t1", "Erreur de fonctionnement");
            session.setAttribute("t2", "Connectez avec nous afin de résoudre le problème.");
        }else if(message.equals("Erreur de session ! Veuillez vous reconnecter !")){
            session.setAttribute("t1", "Erreur de droits");
            session.setAttribute("t2", "Vous n'avez pas de droits à réaliser cet action.");
        }else if(message.equals("Erreur de session ! Veuillez vous reconnecter !")){
            session.setAttribute("t1", "Erreur de droits");
            session.setAttribute("t2", "Vous n'avez pas de droits à réaliser cet action.");
        }else{ 
            session.setAttribute("t1", "Erreur ID Devis");
            session.setAttribute("t2", "Impossible de trouver le devis que vous cherchez.");
        }%>
        <%@ include file="Header1.jsp"%>
        <form method="post" action="Page">
        <%if("ResponsableConnexion".equals(typeConnexion)||"AffilieConnexion".equals(typeConnexion)||"GestionnaireConnexion".equals(typeConnexion)){%>
            <div class="btn-form">
                <input type="button" value="Reconnecter" onclick="location.href='Page?action=<%=typeConnexion%>&typeConnexion=<%=typeConnexion%>'" style="margin:50px auto">
            </div>
        <%}else{%>
            <div class="btn-form">
                <input type="button" value="Reconnecter" onclick="location.href='Page?action=vide'" style="margin:50px auto">
            </div>
        <%}%>
        </form>
        <%@ include file="Footer.jsp"%>
    </body>
</html>
