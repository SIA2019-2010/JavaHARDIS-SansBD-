/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entitee.Beneficiaire;
import entitee.Devis;
import entitee.Genre;
import entitee.Gestionnaire;
import entitee.PersonnePhysique;
import entitee.Population;
import entitee.Produit;
import entitee.Responsable;
import entitee.StatutBeneficiaire;
import facade.DevisFacadeLocal;
import facade.PersonnePhysiqueFacadeLocal;
import facade.StatutBeneficiaireFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lixin
 */
@Stateless
public class PubliqueSession implements PubliqueSessionLocal {

    @EJB
    private StatutBeneficiaireFacadeLocal statutBeneficiaireFacade;

    @EJB
    private DevisFacadeLocal devisFacade;

    @EJB
    private PersonnePhysiqueFacadeLocal personnePhysiqueFacade;
    
    
    
    
        @Override
    public Devis creerDevis(double prix, Date dateDevis, List<PersonnePhysique> listpers, Produit prod) {
        return devisFacade.creerDevis(listpers, prod, prix, dateDevis);
    }

    @Override
    public PersonnePhysique creerPersonnePhysiqueDevis(String nom, String prenom, String mail, Population pop) {
        return personnePhysiqueFacade.creerPersonnePhysiqueDevis(nom, prenom, mail, pop);
    }

    @Override
    public StatutBeneficiaire creerStatutBeneficiaireDevis(Date datedeb, Beneficiaire statut,PersonnePhysique pers) {
        return statutBeneficiaireFacade.creerStatutBeneficiaireDevis(datedeb, statut, pers);
    }

    @Override
    public PersonnePhysique renseignerInfos(PersonnePhysique pers, String numeroSS, String adresse, Genre genre, boolean adherent) {
        return personnePhysiqueFacade.renseignerInfos(pers, numeroSS, adresse, genre, adherent);
    }
    
    @Override
    public List<Object> rechercherConnexion(HttpSession session, Gestionnaire sessiongestionnaire, PersonnePhysique sessionaffilie, Responsable sessionresponsable, boolean sessionpublique){
        List<Object> Response=new ArrayList();
        if (sessiongestionnaire!=null){
            Response.add("/GestionnaireMenu.jsp");
            Response.add("Connecté à "+new Date(session.getCreationTime()).toLocaleString());
        }
        else if(sessionaffilie!=null){
            Response.add("/AffilieMenu.jsp");
            Response.add("Connecté à "+new Date(session.getCreationTime()).toLocaleString());
        }
        else if(sessionresponsable!=null){
            Response.add("/ResponsableMenu.jsp");
            Response.add("Connecté à "+new Date(session.getCreationTime()).toLocaleString());
        }
        else if(sessionpublique){
            Response.add("/PubliqueMenu.jsp");
            Response.add("Connecté à "+new Date(session.getCreationTime()).toLocaleString());
        }
        else{
            Response.add("/Connexion.jsp");
            Response.add("Affichage page connexion");
        }
        return Response;
    }
    
    
    
    
    
    
    
    
    
}
