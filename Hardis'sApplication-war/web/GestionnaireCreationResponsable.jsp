<%-- 
    Document   : CreationMorale
    Created on : 24 mars 2020, 10:06:16
    Author     : alexisbaillieu
--%>
<%@page import="entitee.PersonneMorale"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Creation d'un responsable </title>
        <jsp:useBean id="listepersmo" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="message" scope="request" class="String"></jsp:useBean>
    </head>
    <body>
        <h1>Rentrer les informations </h1>
        <p><%=message%></p>
    </body>
     <form method="post" action="Page">
        <%List<PersonneMorale>lesPersmo=listepersmo;%>
                <fieldset>
                    <legend>Informations nouveau Responsable</legend>
                    <label for="idpers">Entreprise à laquelle ratacher le responsable<span class="requis">*</span></label>
                    <select name="idpers">
                        <%for(PersonneMorale ac: lesPersmo){%>
                            <option value="<%=ac.getId()%>"><%=ac.getId()%><%=ac.getRaisonSociale()%></option>
                        <%}%>
                    </select>
                    <br/>
                   <label for ="Nom">Nom<span class ="requis">*</span></label>
                <input type="text" name ="Nom" value ="" size="20" maxlength ="20"/>
                <br/>
                <br/>
                   <label for ="Prenom">Prenom<span class ="requis">*</span></label>
                <input type="text" name ="Prenom" value ="" size="20" maxlength ="20"/>
                <br/>
                 <br/>
                   <label for ="Mail">Mail<span class ="requis">*</span></label>
                <input type="text" name ="Mail" value ="" size="20" maxlength ="20"/>
                <br/>
                 <br/>
                   <label for ="Telephone">Telephone<span class ="requis">*</span></label>
                <input type="text" name ="Telephone" value ="" size="20" maxlength ="20"/>
                <br/>
                                    
                </fieldset>

            <input type="hidden" name="action" value="InsererResponsable"/>
            <input type ="submit" value="Valider" />
            <input type ="reset" value="Remettre à zero" /> <br/>
        </form>
        
        </div>
      <td Width=25%><A href="ServMenu?action=retourAgent"> Retour au menu</a></td>              
 </body>
    
</html>
