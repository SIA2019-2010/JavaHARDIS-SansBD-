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
import java.lang.reflect.Array;
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


    @Override
    public List<Object> calculPacks(Object[] pers,List<Object>listeinfos,Date datedeb) {
       List<Object> Response=new ArrayList();
   
       //la personne qui crée le devis n'est pas stocké dans la liste d'objet car il n'y a pas les meme informations demandées
       // simplement faire une liste de personne ? 
       
       if(((String)Array.get(pers, 0)).equals("")||pers.getPrenom()==null||pers.getDateNaiss()==null||pers.getMail()==null){ // ajouter numero SS ?
             Response.add("Il manque de champs");//1
             Response.add("/CreationDevis.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
             Response.add(pers);//3 la personne qui crée le devis
             Response.add(listeinfos);//4 les ayant drois (nom, prenom, datenaiss, population, statut)
             Response.add(null); //5 pas de devis
             
            return Response; //tout ce qu'on a donné
       }
       
       //possibilité de changer ce qui est demandé pour les ayants droits
       
        for(Object infos: listeinfos){
            String nom=(String)Array.get(infos, 0);
            String prenom=(String)Array.get(infos, 1);
            Date datenaiss=(Date)Array.get(infos, 2);
            String numeroSS = (String)Array.get(infos,3);
            Beneficiaire statut=(Beneficiaire)Array.get(infos, 4);
            
            
            if(nom==null||prenom==null||datenaiss==null||statut==null){
             Response.add("Il manque de champs");//1
             Response.add("/CreationDevis.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
             Response.add(pers);//3 la personne qui crée le devis
             Response.add(listeinfos);//4 les ayant drois (nom, prenom, datenaiss, population, statut)
             Response.add(null); //5 pas de devis
             
            return Response; //tout ce qu'on a donné
            }
            
        }
        
         // si tout les champs sont bien remplis :  
         
        List<Devis> lesPacks=new ArrayList(); //a mettre en liste d'objetss
        Date dateDevis = new Date();
        
        // prix = // algo Claire
        //produit = //algo CLaire
        //lesPacks = algo Claire
        
        
        Response.add("Packs calculés"); // 1
        Response.add("/AfficherPacks.jsp"); // 2 Jsp pour afficher les devis avec une liste de DEVIS
        Response.add(pers);//3 la pers
        Response.add(listeinfos); // 4 les ayant droits
        Response.add(lesPacks); // 5 les devis (avec produit)

        
        
        
       
        
    
        return Response;
    }
    
    
        @Override
    public List<Object> creerDevisComplet(double prix,PersonnePhysique pers,List<Object>listeinfos) {
        List<Object> Response=new ArrayList();
        
 
       // 1 devis, 1 personne qui crée, 1 liste personne (Ayants droits)
            //---> creation de la personne si existe pas (test sur l'adresse mail)
                                 // ---> creation du devis avec prix, date (newdate)
      
          
                             //on créée la personne si elle n'existe pas    
            PersonnePhysique persencours=personnePhysiqueFacade.recherchePersNumeroSS(pers.getNumeroSS());
            if(persencours==null){
                persencours=personnePhysiqueFacade.creerPersonnePhysiqueDevis(pers.getNom(),pers.getPrenom(), pers.getMail(), pers.getNumeroSS(),pers.getDateNaiss(),pers.getLaPopulation());
                
            }
                                 
      
           for(Object infos: listeinfos){
            String nom=(String)Array.get(infos, 0);
            String prenom=(String)Array.get(infos, 1);
            Date datenaiss =(Date)Array.get(infos, 2);
            String numeroSS=(String)Array.get(infos, 3);
            Beneficiaire statut=(Beneficiaire)Array.get(infos, 4); 
            
            //on recupère les infos des ayant droit pour les créée ou non
            
            
                if (personnePhysiqueFacade.recherchePersNumeroSS(numeroSS)){
                    personnePhysiqueFacade.creerAyantsDroits(nom, prenom, datenaiss,numeroSS);
                    
                    
                }
            
            
            
            
            
            
                                 
            }                           
                                 
                                 
        
        return Response;
    }
    
    
    
    
    
    
}
