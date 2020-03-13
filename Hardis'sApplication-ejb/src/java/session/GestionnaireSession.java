/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entitee.Gestionnaire;
import facade.GestionnaireFacadeLocal;
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
public class GestionnaireSession implements GestionnaireSessionLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private GestionnaireFacadeLocal gestionnaireFacade;
    
    @Override
    public List<Object> authentificationGestionnaire(String login, String mdp, HttpServletRequest request) {
        System.out.println("authenRespon"+login+"   "+mdp);
        List<Object> Response=new ArrayList();
        if(login.trim().isEmpty()||mdp.trim().isEmpty()){
            Response.add("Il manque de champs");
            Response.add("/Connexion.jsp");
            System.out.println("champs null");
            request.setAttribute("action","GestionnaireConnexion");
        }
        else{
            Gestionnaire sessiongestionnaire=gestionnaireFacade.authentificationGestionnaire(login, mdp);
            if(sessiongestionnaire==null){
                Response.add("Erreur :login ou mdp");
                Response.add("/Connexion.jsp");
                System.out.println("erreur mdp");
                request.setAttribute("action","GestionnaireConnexion");
            }
            else{
                Response.add("Connexion réussie");
                Response.add("/GestionnaireMenu.jsp");
                System.out.println("reussie");
                HttpSession session = request.getSession(true);
                session.setAttribute("sessiongestionnaire",sessiongestionnaire);
                request.setAttribute("typeConnexion","GestionnaireConnexion");
            }
        }
        return Response;
    }
    
    @Override
    public Gestionnaire modifiermdp(Gestionnaire resp, String mdp){
        return gestionnaireFacade.modifierMdp(resp, mdp);
    }
    
}
