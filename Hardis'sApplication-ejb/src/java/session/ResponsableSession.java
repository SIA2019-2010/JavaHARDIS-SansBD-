/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entitee.PersonneMorale;
import entitee.Responsable;
import entitee.StatutBeneficiaire;
import facade.ResponsableFacadeLocal;
import facade.StatutBeneficiaireFacadeLocal;
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
    
    @EJB
    private StatutBeneficiaireFacadeLocal statutBeneficiaireFacade;
    
    @Override
    public List<Object> authentificationResponsable(String login, String mdp, HttpServletRequest request) {
        System.out.println("authenRespon"+login+"   "+mdp);
        HttpSession session = request.getSession(false);
        session.invalidate();
        System.out.println("0000");
        List<Object> Response=new ArrayList();
        System.out.println("1111");
        if(login.trim().isEmpty()||mdp.trim().isEmpty()){
            System.out.println("aaaa");
            Response.add("Il manque de champs");
            Response.add("/Connexion.jsp");
            System.out.println("champs null");
            request.setAttribute("typeConnexion","ResponsableConnexion");
        }
        else{
            Responsable sessionresponsable=responsableFacade.authentificationResponsable(login, mdp);
            if(sessionresponsable==null){
                System.out.println("bbbb");
                Response.add("Erreur :login ou mdp");
                Response.add("/Connexion.jsp");
                System.out.println("erreur mdp");
                request.setAttribute("typeConnexion","ResponsableConnexion");
            }
            else{
                System.out.println("cccc");
                Response.add("Connexion réussie");
                Response.add("/ResponsableMenu.jsp");
                System.out.println("reussie");
                session = request.getSession(true);
                session.setAttribute("sessionresponsable",sessionresponsable);
                request.setAttribute("typeConnexion","ResponsableConnexion");
            }
        }
        return Response;
    }
    
    @Override
    public List<Object> modifiermdp(Responsable resp, String OMDP, String NMDP, String RMDP){
        List<Object> Response=new ArrayList();
        if(resp==null||OMDP==null||NMDP==null||RMDP==null){
            Response.add("Erreur Session");
            Response.add("/ErreurSession.jsp");
        }
        else if(OMDP.equals("")||NMDP.equals("")||RMDP.equals("")){
            Response.add("Remplisez tous les champs");
            Response.add("/PageModifierMdp.jsp");
        }
        else if(!NMDP.equals(RMDP)){
            Response.add("répéter mot de pas incorrecte");
            Response.add("/PageModifierMdp.jsp");
        }
        else if(!resp.getMdp().equals(OMDP)){
            Response.add("Ancien mot de passe incorrecte");
            Response.add("/PageModifierMdp.jsp");
        }
        else if(OMDP.equals(NMDP)){
            Response.add("faur choisir un mot de pas différent");
            Response.add("/PageModifierMdp.jsp");
        }
        else{
            responsableFacade.modifierMdp(resp, RMDP);
            Response.add("Mot de passe modifié");
            Response.add("/PageModifierMdp.jsp");
        }
        
        return Response;
    }
    
    @Override
    public List<StatutBeneficiaire> rechercherStatutBeneficiaire(PersonneMorale persmo){
        return statutBeneficiaireFacade.rechercherStatutBeneficiaire(persmo);
    }
    
}
