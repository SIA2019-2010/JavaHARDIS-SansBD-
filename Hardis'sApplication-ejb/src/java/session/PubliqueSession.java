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
import facade.PopulationFacadeLocal;
import facade.ProduitFacadeLocal;
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
    private ProduitFacadeLocal produitFacade;

    @EJB
    private PopulationFacadeLocal populationFacade1;

    @EJB
    private PopulationFacadeLocal populationFacade;

    @EJB
    private StatutBeneficiaireFacadeLocal statutBeneficiaireFacade;

    @EJB
    private DevisFacadeLocal devisFacade;

    @EJB
    private PersonnePhysiqueFacadeLocal personnePhysiqueFacade;
    
    
            
            
    
    
    
    
    
    
    @Override
    public Devis creerDevis(PersonnePhysique pers,Produit prod,double prix, Date dateDevis,List<PersonnePhysique> listeayantdroit ) {
        return devisFacade.creerDevis(pers, prod, prix, dateDevis,listeayantdroit);
    }

    @Override
    public PersonnePhysique creerPersonnePhysiqueDevis(String nom, String prenom, String mail,String numeroSS,Date datenaiss, Population pop) {
        return personnePhysiqueFacade.creerPersonnePhysiqueDevis(nom, prenom, mail,numeroSS,datenaiss, pop);
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
            Response.add("Connecté à "+new Date(session.getCreationTime()).toLocaleString());
            Response.add("/GestionnaireMenu.jsp");
            Response.add("GestionnaireConnexion");
        }
        else if(sessionaffilie!=null){
            Response.add("Connecté à "+new Date(session.getCreationTime()).toLocaleString());
            Response.add("/AffilieMenu.jsp");
            Response.add("AffilieConnexion");
        }
        else if(sessionresponsable!=null){
            Response.add("Connecté à "+new Date(session.getCreationTime()).toLocaleString());
            Response.add("/ResponsableMenu.jsp");
            Response.add("ResponsableConnexion");
        }
        else if(sessionpublique){
            Response.add("Connecté à "+new Date(session.getCreationTime()).toLocaleString());
            Response.add("/PubliqueMenu.jsp");
            Response.add("PubliqueConnexion");
        }
        else{
            Response.add("Affichage page connexion");
            Response.add("/Connexion.jsp");
            Response.add("GestionnaireConnexion");
        }
        return Response;
    }


    @Override
    public List<Object> calculPacks(Object[] pers,List<Object>listeinfos) {
       List<Object> Response=new ArrayList();
       //la personne qui crée le devis n'est pas stocké dans la liste d'objet mais dans un object a part
       //Objet pers : 1 nom, 2 prenom, 3 datenaiss, 4 numero SS ,5 mail,6 Population (String de ID)
       
       //ayants droits  : List Object listeinfos
       //1 Nom ; 2Prenom ; 3datenaiss ; 4numeroSS ; 5 statut (beneficiaire)
       
       if(((String)Array.get(pers, 0)).equals("")||((String)Array.get(pers, 1)).equals("")||((Date)Array.get(pers, 2)).equals("")||((String)Array.get(pers, 3)).equals("")||((String)Array.get(pers, 4)).equals("")||((String)Array.get(pers, 5)).equals("")){
             Response.add("Il manque des champs");//1
             Response.add("/CreationDevis.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
             Response.add(pers);//3 la personne qui crée le devis
             Response.add(listeinfos);//4 les ayant drois (nom, prenom, datenaiss, population, statut)
             Response.add(null); //5 pas de devis
             
            return Response; //manque des champs donc renvoi de toutes les informations
       }
       //controle sur la population
       Long idpop=Long.valueOf((String)Array.get(pers, 5));
       Population pop = populationFacade.rechercheExistantPopulationID(idpop);
       if(pop==null){
            Response.add("Probleme sur la population");//1
             Response.add("/CreationDevis.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
             Response.add(pers);//3 la personne qui crée le devis
             Response.add(listeinfos);//4 les ayant drois (nom, prenom, datenaiss, population, statut)
             Response.add(null); //5 pas de devis
             
            return Response; //Population introuvable
       }
       
       //controle sur les ayants droits
        for(Object infos: listeinfos){
            String nom=(String)Array.get(infos, 0);
            String prenom=(String)Array.get(infos, 1);
            Date datenaiss=(Date)Array.get(infos, 2);
            String numeroSS = (String)Array.get(infos,3);
            String statut=(String)Array.get(infos, 4); 
            
            
            if(nom==null||prenom==null||datenaiss==null||numeroSS==null||statut==null){
             Response.add("Il manque des champs");//1
             Response.add("/CreationDevis.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
             Response.add(pers);//3 la personne qui crée le devis
             Response.add(listeinfos);//4 les ayant drois (nom, prenom, datenaiss, population, statut)
             Response.add(null); //5 pas de packs (produit+prix)
             
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
        Response.add(lesPacks); // 5 les "pack" (avec produit) : objet avec prix 1 et 2 produit
    
        return Response;
    }
    
    
    @Override
    public List<Object> creerDevisComplet(Object[] pers,Object[] pack,List<Object>listeinfos) {
        List<Object> Response=new ArrayList();
        List<PersonnePhysique> LesAyantdroit=new ArrayList();
 
       // 1 devis, 1 personne qui crée, 1 liste personne (Ayants droits)
            //---> creation de la personne si existe pas (test sur l'adresse mail)
                                 // ---> creation du devis avec prix, date (newdate)
        //Object pack : 1 produit , 2 prix                         
      
          
            //on créée la personne si elle n'existe pas                  
            PersonnePhysique persencours=personnePhysiqueFacade.recherchePersNumeroSS((String)Array.get(pers, 3));
            if(persencours==null){
                //1 nom, 2 prenom, 3 datenaiss, 4 numero SS ,5 mail, 6 population
                
                 Long idpop=Long.valueOf((String)Array.get(pers, 5));
                Population pop = populationFacade.rechercheExistantPopulationID(idpop);
                
                persencours=personnePhysiqueFacade.creerPersonnePhysiqueDevis((String)Array.get(pers, 0),(String)Array.get(pers, 1), (String)Array.get(pers, 4), (String)Array.get(pers, 3),(Date)Array.get(pers, 2),pop);
                
            }
                                 
      
           for(Object infos: listeinfos){
            String nom=(String)Array.get(infos, 0);
            String prenom=(String)Array.get(infos, 1);
            Date datenaiss =(Date)Array.get(infos, 2);
            String numeroSS=(String)Array.get(infos, 3);
            Beneficiaire statut=(Beneficiaire)Array.get(infos, 4); 
            
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
           
        devcree=devisFacade.creerDevis(persencours,(Produit)Array.get(pack, 0),(Double)Array.get(pack, 1),dateDevis,LesAyantdroit);  
        
        
        Response.add("Devis créée "); // 1
        Response.add("/Homepage.jsp"); // 2 Jsp pour afficher 
        Response.add(devcree);//3 la pers
        
        
        return Response;
    }
    
    
    
    
    
}
