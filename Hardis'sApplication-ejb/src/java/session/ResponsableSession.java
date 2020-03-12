/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entitee.Responsable;
import facade.ResponsableFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lixin
 */
@Stateless
public class ResponsableSession implements ResponsableSessionLocal {

    @EJB
    private ResponsableFacadeLocal responsableFacade;
    
    @Override
    public List<Object> authentificationResponsable(String login, String mdp, HttpServletRequest request) {
        List<Object> Response=new ArrayList();
        if(login.trim().isEmpty()||mdp.trim().isEmpty()){
            Response.add("Il manque de champs");
            Response.add("/Connexion.jsp");
            request.setAttribute("typeConnexion","ResponsableConnexion");
        }
        else{
            Responsable sessionresponsable=responsableFacade.authentificationResponsable(login, mdp);
            if(sessionresponsable==null){
                Response.add("Erreur :login ou mdp");
                Response.add("/Connexion.jsp");
                request.setAttribute("typeConnexion","ResponsableConnexion");
            }
            else{
                Response.add("Connexion réussie");
                Response.add("/AgentMenu.jsp");
                request.setAttribute("typeConnexion","ResponsableConnexion");
                HttpSession session = request.getSession(true);
                session.setAttribute("sessionresponsable",sessionresponsable);
            }
        }
        return Response;
    }
    
    @Override
    public Responsable modifiermdp(Responsable resp, String mdp){
        return responsableFacade.modifierMdp(resp, mdp);
    }
    
}
