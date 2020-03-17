/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entitee.Beneficiaire;
import entitee.Contrat;
import entitee.Devis;
import entitee.Genre;
import entitee.PersonnePhysique;
import entitee.Population;
import entitee.Produit;
import entitee.Remboursement;
import entitee.StatutBeneficiaire;
import facade.ActeFacadeLocal;
import facade.ContratFacadeLocal;
import facade.DevisFacadeLocal;
import facade.PersonnePhysiqueFacadeLocal;
import facade.ProduitFacadeLocal;
import facade.StatutBeneficiaireFacadeLocal;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
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
public class AffilieSession implements AffilieSessionLocal {

    @EJB
    private ProduitFacadeLocal produitFacade;

    @EJB
    private ActeFacadeLocal acteFacade;

    @EJB
    private ContratFacadeLocal contratFacade;

    @EJB
    private DevisFacadeLocal devisFacade;

    @EJB
    private PersonnePhysiqueFacadeLocal personnePhysiqueFacade;
    
    @EJB
    private StatutBeneficiaireFacadeLocal statutBeneficiaireFacade;
    
    
    
    
    
    
 
    

    @Override
    public List<Object> authentificationAffilie(String login, String mdp, HttpServletRequest request) {
        System.out.println("authenRespon"+login+"   "+mdp);
        List<Object> Response=new ArrayList();
        if(login.trim().isEmpty()||mdp.trim().isEmpty()){
            Response.add("Il manque de champs");
            Response.add("/Connexion.jsp");
            System.out.println("champs null");
            request.setAttribute("typeConnexion","AffilieConnexion");
        }
        else{
            PersonnePhysique sessionpersonnePhysique=personnePhysiqueFacade.authentificationAffilie(login, mdp);
            if(sessionpersonnePhysique==null){
                Response.add("Erreur :login ou mdp");
                Response.add("/Connexion.jsp");
                System.out.println("erreur mdp");
                request.setAttribute("typeConnexion","AffilieConnexion");
            }
            else{
                Response.add("Connexion réussie");
                Response.add("/AgentMenu.jsp");
                System.out.println("reussie");
                HttpSession session = request.getSession(true);
                session.setAttribute("sessionpersonnePhysique",sessionpersonnePhysique);
                request.setAttribute("typeConnexion","AffilieConnexion");
            }
        }
        return Response;
    }
    
    @Override
    public PersonnePhysique modifierMDP(String nvMDP, PersonnePhysique pers) {
        return personnePhysiqueFacade.modifierMdp(nvMDP, pers);
    }

    @Override
    public PersonnePhysique modifierMail(String nvMail, PersonnePhysique pers) {
        return personnePhysiqueFacade.modifierMail(pers, nvMail);
    }

    @Override
    public PersonnePhysique modifierAdresse(String nvAdresse, PersonnePhysique pers) {
        return personnePhysiqueFacade.modifierAdresse(pers, nvAdresse);
    }

    @Override
    public Devis creerDevis(PersonnePhysique pers,double prix,Produit prod,Date dateDevis,List<PersonnePhysique>listeAyantdroits) {
        return devisFacade.creerDevis(pers, prod, prix, dateDevis,listeAyantdroits);
    }
   

    @Override
    public PersonnePhysique creerPersonnePhysiqueDevis(String nom, String prenom, String mail, String numeroSS, Date datenaiss, Population laPopulation) {
        return personnePhysiqueFacade.creerPersonnePhysiqueDevis(nom, prenom, mail,numeroSS,datenaiss, laPopulation);
    }

    @Override
    public StatutBeneficiaire creerStatutBeneficiaireDevis(Date datedeb, Beneficiaire statut,PersonnePhysique pers) {
        return statutBeneficiaireFacade.creerStatutBeneficiaireDevis(datedeb, statut, pers);
    }

    @Override
    public PersonnePhysique renseignerInfos(PersonnePhysique pers,String adresse, Genre genre) {
        return personnePhysiqueFacade.renseignerInfos(pers, adresse, genre);
    }

    @Override
    public Contrat modifierDateFinContrat(Contrat contrat, Date datef) {
        return contratFacade.modifierDateFin(datef, contrat);
    }

    @Override
    public List<Contrat> rechercheContrats(PersonnePhysique pers) {
        return statutBeneficiaireFacade.rechercheContrats(pers);
    }

    @Override
    public List<Remboursement> rechercheRemboursements(PersonnePhysique pers) {
        return acteFacade.rechercheRemboursementsPersonne(pers);
    }
    
    
    
    @Override
    public List<Object> calculPacksAffilie(PersonnePhysique pers,List<Object[]>listeinfos) {
       List<Object> Response=new ArrayList();
       //la personne qui crée le devis est envoyé depuis attribut de session
       //Tout les champs doivent etre non modifiable car pas de test null!=nonnull
       
       //ayants droits  : List Object listeinfos
       //1 Nom ; 2Prenom ; 3datenaiss ; 4numeroSS ; 5 statut (beneficiaire)
       
       //controle sur les ayants droits
        for(Object infos: listeinfos){
            String nom=(String)Array.get(infos, 0);
            String prenom=(String)Array.get(infos, 1);
            Date datenaiss=(Date)Array.get(infos, 2);
            String numeroSS = (String)Array.get(infos,3);
            
            
            if(nom==null||prenom==null||datenaiss==null||numeroSS==null){
             Response.add("Il manque des champs");//1
             Response.add("/CreationDevis.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
             Response.add(pers);//3 la personne qui crée le devis
             Response.add(listeinfos);//4 les ayant drois (nom, prenom, datenaiss, population, statut)
             Response.add(null); //5 pas de devis
             
            return Response; //tout ce qu'on a donné
            }
            
        }
        
         // si tout les champs sont bien remplis : Algo pour prix+produit dans un objet 
         
        List<Object> lesPacks=new ArrayList();
        Date dateDevis = new Date();
        
        // prix +produit = objet 
        //lesPacks ==== algo CLAIRE
        
        
        Response.add("Packs calculés"); // 1
        Response.add("/AfficherPacks.jsp"); // 2 Jsp pour afficher les devis avec une liste de DEVIS
        Response.add(pers);//3 la pers
        Response.add(listeinfos); // 4 les ayant droits
        Response.add(lesPacks); // 5 les "devis" (avec produit) : objet avec prix 1 et 2 produit
    
        return Response;
    }
    
    
    @Override
    public List<Object> creerDevisCompletAffilie(PersonnePhysique pers,Object[] pack,List<Object[]>listeinfos) {
        List<Object> Response=new ArrayList();
        List<PersonnePhysique> LesAyantdroit=new ArrayList();
 
       // 1 devis, 1 personne qui crée, 1 liste personne (Ayants droits)
       //Object pack :  en 1 produit , en 2 prix       
       //la personne est fixe : AttributSession affilie
       
        // ---> creation du devis avec prix, date (newdate)
                              
           //contrôle sur la liste d'ayantdroit
           for(Object infos: listeinfos){
            String nom=(String)Array.get(infos, 0);
            String prenom=(String)Array.get(infos, 1);
            Date datenaiss =(Date)Array.get(infos, 2);
            String numeroSS=(String)Array.get(infos, 3);
            
            //on recupère les infos des ayant droit pour les créée ou non
            PersonnePhysique ayantdroitencours;
            ayantdroitencours=personnePhysiqueFacade.recherchePersNumeroSS(numeroSS);
            
                //si la personne n'existe pas
                if (ayantdroitencours==null){
                
                   ayantdroitencours=personnePhysiqueFacade.creerAyantsDroits(nom, prenom, datenaiss,numeroSS); 
                }
                    
              LesAyantdroit.add(ayantdroitencours);                                            
        }       
           
         //creation du devis ; pers,produit,prix,datedujour,listayantdroits       
         Devis devcree; 
         Date dateDevis = new Date();
           
        devcree=devisFacade.creerDevis(pers,(Produit)Array.get(pack, 0),(Double)Array.get(pack, 1),dateDevis,LesAyantdroit);  
        
        
        Response.add("Devis créée "); // 1
        Response.add("/MenuAffilie.jsp"); // 2 Jsp pour afficher 
        Response.add(devcree);//3 la pers
        
        
        return Response;
    }
    
    
    
    
    

    
    
    
    
    
    
    
    
    
}
