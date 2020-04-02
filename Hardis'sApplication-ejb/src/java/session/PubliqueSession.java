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
import entitee.Gestionnaire;
import entitee.PersonnePhysique;
import entitee.Population;
import entitee.Produit;
import entitee.Responsable;
import entitee.StatutBeneficiaire;
import facade.BeneficiaireFacadeLocal;
import facade.ContratFacadeLocal;
import facade.DevisFacadeLocal;
import facade.PersonnePhysiqueFacadeLocal;
import facade.PopulationFacadeLocal;
import facade.ProduitFacadeLocal;
import facade.StatutBeneficiaireFacadeLocal;
import static java.lang.Math.round;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Random;
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
    private ContratFacadeLocal contratFacade;

    @EJB
    private ProduitFacadeLocal produitFacade;

    @EJB
    private PopulationFacadeLocal populationFacade;

    @EJB
    private StatutBeneficiaireFacadeLocal statutBeneficiaireFacade;

    @EJB
    private DevisFacadeLocal devisFacade;

    @EJB
    private PersonnePhysiqueFacadeLocal personnePhysiqueFacade;
    
    @EJB
    private BeneficiaireFacadeLocal beneficiaireFacade;
    
    
    
    
     
    
    
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
    public PersonnePhysique renseignerInfos(PersonnePhysique pers, String adresse, Genre genre,String RIB) {
        return personnePhysiqueFacade.renseignerInfos(pers, adresse, genre,RIB);
    }
           
    @Override
    public List<Object> rechercherConnexion(HttpSession session, Gestionnaire sessiongestionnaire, PersonnePhysique sessionaffilie, Responsable sessionresponsable){
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
        else{
            Response.add("Affichage page connexion");
            Response.add("/Connexion.jsp");
            Response.add("GestionnaireConnexion");
        }
        return Response;
    }


    @Override
    public List<Object> calculPacks(Object[] pers,List<Object[]>listeinfos) {
        List<Object> Response=new ArrayList();
       
        //la personne qui crée le devis n'est pas stocké dans la liste d'objet mais dans un object a part
       //Objet pers : 1 nom, 2 prenom, 3 datenaiss, 4 numero SS ,5 mail,6 Population (String de ID)
       
       //ayants droits  : List Object listeinfos
       //1 Nom ; 2Prenom ; 3datenaiss ; 4numeroSS ;
       
       if(((String)Array.get(pers, 0)).equals("")||((String)Array.get(pers, 1)).equals("")||((String)Array.get(pers, 2)).equals("")||((String)Array.get(pers, 3)).equals("")||((String)Array.get(pers, 4)).equals("")||((String)Array.get(pers, 5)).equals("")){
            System.out.println("null champs");
            Response.add("Il manque des champs");//1
             Response.add("/PageCreationDevis.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
             Response.add(null); //5 pas de devis
             
            return Response; //manque des champs donc renvoi de toutes les informations
       }else {
            PersonnePhysique persencours= personnePhysiqueFacade.recherchePersNumeroSS((String)Array.get(pers, 3));
            if (persencours!=null){
                List<StatutBeneficiaire> statutbenefs=persencours.getLesStatutsBeneficiaire();
                
                for (StatutBeneficiaire statut : statutbenefs){
                    if (statut.getLaBeneficiaire().getLibelleBeneficiaire().equalsIgnoreCase("Affilie")){
                        Response.add("Vous avez deja un contrat merci de contacter votre gestionnaire");//1
                        Response.add("/PageCreationDevis.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
                        Response.add(null); //5 pas de devis
             
                        return Response; //Population introuvable 
                    }                  
                }  
            }
        }
        //Date de naissance
        Date DateN=null;
        try{
            DateN=java.sql.Date.valueOf((String)Array.get(pers, 2));
        }catch(ArrayIndexOutOfBoundsException | IllegalArgumentException e){}
        double coef;
        if(DateN==null||DateN.after(new Date())){
            Response.add("Effeur Date");//1
            Response.add("/PageCreationDevis.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
            Response.add(null); //5 pas de devis
            return Response; //manque des champs donc renvoi de toutes les informations
        }
        else{
            coef=DateToCoef(DateN);
        }
        //controle sur la population
        Long idpop=Long.valueOf((String)Array.get(pers, 5));
        Population pop = populationFacade.rechercheExistantPopulationID(idpop);
        if(pop==null){
             Response.add("Probleme sur la population");//1
             Response.add("/PageCreationDevis.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
             Response.add(null); //5 pas de devis
             
             return Response; //Population introuvable
        }
        //controle sur les ayants droits
        for(Object[] infos: listeinfos){
            String nom=(String)Array.get(infos, 0);
            String prenom=(String)Array.get(infos, 1);
            String datenaiss=(String)Array.get(infos, 2);
            String numeroSS = (String)Array.get(infos,3);
            
            
            if(nom.equals("")||prenom.equals("")||datenaiss.equals("")||numeroSS.equals("")){
                Response.add("Il manque des champs");//1
                Response.add("/PageCreationDevis.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
                Response.add(null); //5 pas de packs (produit+prix)
             
                return Response; //tout ce qu'on a donné
            }
            
            DateN=java.sql.Date.valueOf(datenaiss);
            if(DateN==null||DateN.after(new Date())){
                 Response.add("Effeur Date");//1
                 Response.add("/PageCreationDevis.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
                 Response.add(null); //5 pas de devis
                 return Response; //manque des champs donc renvoi de toutes les informations
            }
            else{
                coef+=DateToCoef(DateN);
            }
            
            
        }
        
         // si tout les champs sont bien remplis : Algo pour prix+produit dans un objet 
         
        
        List<Produit> listproduit=produitFacade.afficherProduitIndividuel();
        List<Object[]> lesPacks=new ArrayList();
        for(Produit pr:listproduit){
            Object[] packproduit={pr,(double)(round(pr.getPrixBase()*coef*100)/100)};
            lesPacks.add(packproduit);
        }
        // prix +produit = objet 
        //lesPacks ==== algo CLAIRE
        
        
        Response.add("Packs calculés"); // 1
        Response.add("/AfficherPacks.jsp"); // 2 Jsp pour afficher les devis avec une liste de DEVIS
        Response.add(lesPacks); // 5 les "pack" (avec produit) : objet avec prix 1 et 2 produit
    
        return Response;
    }
    
    
    @Override
    public List<Object> creerDevisComplet(Object[] pers,Object[] pack,List<Object[]>listeinfos) {
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
                Date datenais=java.sql.Date.valueOf((String)Array.get(pers, 2));
                persencours=personnePhysiqueFacade.creerPersonnePhysiqueDevis((String)Array.get(pers, 0),(String)Array.get(pers, 1), (String)Array.get(pers, 4), (String)Array.get(pers, 3),datenais,pop);
                System.out.println(idpop+"idpop");
                
            }
            System.out.println("nanananana");
            if(persencours==null)System.out.println("personne null");
            else System.out.println(persencours);
            System.out.println(listeinfos.size());
            // Ayant droit : juste nom prenom date numeroSS
           for(Object[] infos: listeinfos){
           
            String nom=(String)Array.get(infos, 0);
            String prenom=(String)Array.get(infos, 1);
            Date datenaiss =java.sql.Date.valueOf((String)Array.get(infos, 2));
            String numeroSS=(String)Array.get(infos, 3);
            
            
            //on recupère les infos des ayant droit pour les créée ou non
            PersonnePhysique ayantdroitencours;
            ayantdroitencours=personnePhysiqueFacade.recherchePersNumeroSS(numeroSS);
            
            //si la personne n'existe pas
            if (ayantdroitencours==null){
                System.out.println("avant création");
                ayantdroitencours=personnePhysiqueFacade.creerAyantsDroits(nom, prenom, datenaiss,numeroSS);
                System.out.println("après création id "+ayantdroitencours.getId());
            }
            else{
                System.out.println("personne trouvée");
            }
            
            
                    
            LesAyantdroit.add(ayantdroitencours);                                            
        }       
        System.out.println("complet"); 
           
         //creation du devis ; pers,produit,prix,datedujour,listayantdroits       
         Devis devcree; 
         Date dateDevis = new Date();
           
        devcree=devisFacade.creerDevis(persencours,(Produit)Array.get(pack, 0),(Double)Array.get(pack, 1),dateDevis,LesAyantdroit);  
        
        
        Response.add("Devis créée "); // 1
        Response.add("/PageCreationDevis.jsp"); // 2 Jsp pour afficher 
        Response.add(devcree);//3 le devis
        
        
        return Response;
    }

    @Override
    public List<Population> recherchePopulations() {
        return populationFacade.recherchePopulations();
    }
    
    @Override
    public List<Beneficiaire> rechercheBeneficiaires() {
        return beneficiaireFacade.rechercheBeneficiaires();
    }
    
    public double DateToCoef(Date DateN){
        Date today=new Date();
        int age=today.getYear()-DateN.getYear();
        if(today.getMonth()<DateN.getMonth()) age--;
        else if(today.getDay()<DateN.getDay()&&today.getMonth()==DateN.getMonth()) age--;
        
        if(age<10) return 0;
        else if(age<16) return 1;
        else if(age<18) return 1.05;
        else if(age<25) return 1.15;
        else if(age<40) return 1.6;
        else if(age<55) return 1.9;
        else return 2.5;
    }

    @Override
    public PersonnePhysique renseignerLoginMDP(PersonnePhysique pers, String login, String mdp) {
        return personnePhysiqueFacade.renseignerLoginMdp(pers, login, mdp);
    }

    @Override
    public List<Object> validerDevis(String iddev, Object[] pers,List<Object[]>listeinfos) {
        List<Object> Response=new ArrayList(); 
        
        long iddevis;
        try{
            iddevis=(long)Integer.parseInt(iddev);
        }catch(NumberFormatException e){
            Response.add("Probleme sur le devis, contacter une agence");//1
            Response.add("/Acceuil.jsp");
            Response.add(null);
            Response.add(null);
            Response.add(null);
            //Response.add(null); 
             
            return Response;
        }
        //dans cette methode : on recupere : 
                        // pers : nom,prenom,numero SS,adresse,genre,login,mdp,RIB
                        // pour chaque ayant droit : nom,prenom,numeross,adresse,genre,population, STRING (Conjoint, Concubin, Enfant à charge)
                        //potentiellement des fichiers ?
          
         //controle sur le devis               
         Devis dev=devisFacade.rechercheExistantID(iddevis); 
         if (dev==null){
             Response.add("Probleme sur le devis, contacter une agence");//1
             Response.add("/Acceuil.jsp");
             Response.add(null);
             Response.add(null);
             Response.add(dev);
             //Response.add(null); 
             
            return Response; //probleme transition ID DEVIS
         }
         
         //controle sur les champs remplis : personnephy               
        if(((String)Array.get(pers, 0)).equals("")||((String)Array.get(pers, 1)).equals("")||((String)Array.get(pers, 2)).equals("")||((String)Array.get(pers, 3)).equals("")||((String)Array.get(pers, 4)).equals("")||((String)Array.get(pers, 5)).equals("")||((String)Array.get(pers, 6)).equals("")||((String)Array.get(pers,7)).equals("")){
             Response.add("Merci de remplir les champs obligatoires");//1
             Response.add("/RenseignementInformationsSupplementaire.jsp"); //2 JSP renseignements complementaire
             Response.add(pers);//3 la personne qui crée le devis (nom,prenom,numero SS,adresse,genre,login,mdp)
             Response.add(listeinfos);//4 les ayant droits (nom,prenom,numeross,adresse,genre,population, STRING (Conjoint, Concubin, Enfant à charge), 
             Response.add(dev);//Response.add(null); //5 effectuer la connexion avec login et mdp ?
             
            return Response; //manque des champs donc renvoi de toutes les informations
       }
        
        //controle les champs remplis : ayants droits
        for(Object[] infos: listeinfos){
            String nom=(String)Array.get(infos, 0);
            String prenom=(String)Array.get(infos, 1);
            String numeroSS = (String)Array.get(infos,2);
            String adresse = (String)Array.get(infos,3);
            String genre = (String)Array.get(infos,4);
            String idpopst = (String)Array.get(infos,5);
            String statutayt=(String)Array.get(infos,6);
 
            
            
            if(nom==null||prenom==null||adresse==null||numeroSS==null||genre==null||idpopst==null||statutayt==null){
                 Response.add("Merci de remplir les champs obligatoires");//1
                 Response.add("/RenseignementInformationsSupplementaire.jsp"); //2 JSP renseignements complementaire
                 Response.add(pers);//3 la personne qui crée le devis (nom,prenom,numero SS,adresse,genre,login,mdp)
                 Response.add(listeinfos);//4 les ayant droits (nom,prenom,numeross,adresse,genre,population,(mail mais peut etre null)) ne pas re remplir les RADIO (ID POP)
                 Response.add(dev);
                //Response.add(null); //5 effectuer la connexion avec login et mdp ?
             
                return Response; //manque des champs donc renvoi de toutes les informations
            
            } else{ // on rempli les renseignements
                Genre genreayt=null;
                try{
                    genreayt=Genre.valueOf(genre);
                }catch(Exception e){
                    Response.add("Erreur du champs genre");
                    Response.add("/RenseignementInformationsSupplementaire.jsp"); 
                    Response.add(pers);
                    Response.add(listeinfos);
                    Response.add(dev);
                    return Response;
                }
                
            /*if(genre.equalsIgnoreCase("Homme")){
                genreayt=Genre.Homme;
            }else if(genre.equalsIgnoreCase("Femme")){
                genreayt=Genre.Femme;
            }else if(genre.equalsIgnoreCase("Autre")){
                genreayt=Genre.Autre;
            }*/
            
            PersonnePhysique ayantdroitencours;
            ayantdroitencours=personnePhysiqueFacade.recherchePersNumeroSS(numeroSS);
                  
            Long idpop=Long.valueOf(idpopst);
            Population pop = populationFacade.rechercheExistantPopulationID(idpop);  
            
            ayantdroitencours=personnePhysiqueFacade.renseignerInfosAyantsDroit(ayantdroitencours, adresse, genreayt, pop);
           
           
            
            }
        }    
          
                      
                        
        PersonnePhysique persencours=personnePhysiqueFacade.recherchePersNumeroSS((String)Array.get(pers, 2));
        if (persencours==null){
             Response.add("Probleme sur le devis, contacter une agence");//1
             Response.add("/homepage.jsp"); 
             Response.add(null);
             Response.add(null);
             Response.add(dev);
             //Response.add(null); 
             
            return Response; //probleme numeroSS de la personne
         }else{ //on renseigne les infos car pas de probleme
            Genre genrepers=null;
            String genrestr=(String)Array.get(pers, 4);
            
            try{
                genrepers=Genre.valueOf(genrestr);
            }catch(Exception e){
                Response.add("Erreur du champs genre");
                Response.add("/RenseignementInformationsSupplementaire.jsp"); 
                Response.add(pers);
                Response.add(listeinfos);
                Response.add(dev);
                return Response;
            }
            /*if(genrestr.equalsIgnoreCase("Homme")){
                genrepers=Genre.Homme;
            }else if(genrestr.equalsIgnoreCase("Femme")){
                genrepers=Genre.Femme;
            }else if(genrestr.equalsIgnoreCase("Autre")){
                genrepers=Genre.Autre;
            }*/
            persencours=personnePhysiqueFacade.renseignerInfos(persencours,(String)Array.get(pers, 3), genrepers,(String)Array.get(pers,6));
            persencours=personnePhysiqueFacade.renseignerLoginMdp(persencours, (String)Array.get(pers, 5), (String)Array.get(pers, 6));
        }
         ///////login
        //on crée le contrat sans les beneficiaires
        Contrat ct=contratFacade.creerContrat(null,dev.getPrix() ,dev.getLeProduit());
        List<StatutBeneficiaire> liststatutct=new ArrayList();
        
        //on creer le statutBeneficiaire pour toutes les personnes concernées, ici afiilie
        StatutBeneficiaire statutaffi=null;
        statutaffi=statutBeneficiaireFacade.creerStatutBeneficiaire(new Date(), beneficiaireFacade.rechercheExistantBeneficiaireLibelle("Affilie"), ct, persencours);
        
        //on rassemble tout les statuts dans une liste
        liststatutct.add(statutaffi);
        
        
        for(Object[] infos: listeinfos){
          
             String numeroSS = (String)Array.get(infos,2);
             
            PersonnePhysique ayantdroitencours;
            ayantdroitencours=personnePhysiqueFacade.recherchePersNumeroSS(numeroSS);  
            
            String statutayd=(String)Array.get(infos,6);
            Beneficiaire benefayd=null;
            if(statutayd.equalsIgnoreCase("Concubin")){
                benefayd=beneficiaireFacade.rechercheExistantBeneficiaireLibelle("Concubin");
            }else if(statutayd.equalsIgnoreCase("Conjoint")){
                benefayd=beneficiaireFacade.rechercheExistantBeneficiaireLibelle("Conjoint");
            }else if(statutayd.equalsIgnoreCase("EnfantACharge")){
                benefayd=beneficiaireFacade.rechercheExistantBeneficiaireLibelle("EnfantACharge");
            }
            
             StatutBeneficiaire statutAyant=null;
             statutAyant=statutBeneficiaireFacade.creerStatutBeneficiaire(new Date(), benefayd, ct, ayantdroitencours);
             
             liststatutct.add(statutAyant);
             
        }
       
        //on enregistre toute les personnes au contrat (set la liste)
       ct.SetLesStatutsBeneficiaires(liststatutct);
        
        
        
        
        Response.add("Contrat créé");//1
        Response.add("/homepage.jsp"); 
        Response.add(null);
        Response.add(null);
        Response.add(dev);
        //Response.add(ct);       On envoit le contrat ?
        
        return Response;
    }
        

    @Override
    public List<Object> recupererInfosDevis(Long iddev) {        
        List<Object> Response=new ArrayList();
        
         Devis dev=devisFacade.rechercheExistantID(iddev);
         
        if(dev==null){
             
        Response.add("Devis introuvable "); // 1
        Response.add("/Homepage.jsp"); // 2 Jsp pour afficher 
        //Response.add(devcree);//3 le devis   
        
        return Response;
        }
        
        
        List<PersonnePhysique>AyantsDroits=dev.getLesAyantsDroit();
        PersonnePhysique personne=dev.getLaPersonne();
        
        
        //on va envoyer des informations sur les personnes (nom prenom numeroSS) pour ajouter des infos sur les bonnes personnesPhysique
        Object[] pers = null;
        List<Object[]>listeinfos = new ArrayList();
        
        //object pers composé de : nom prenom numeroSS (pour ajouter adresse,genre,login et mdp)
        Array.set(pers, 0, personne.getNom());
        Array.set(pers, 1, personne.getPrenom());
        Array.set(pers, 3, personne.getNumeroSS());
        
        
          for (PersonnePhysique ayt : AyantsDroits) {
             Object[]Ayantdroitobj=null;
             Array.set(Ayantdroitobj, 0, ayt.getNom());
             Array.set(Ayantdroitobj, 1, ayt.getPrenom());
             Array.set(Ayantdroitobj, 0, ayt.getNumeroSS());
           
             listeinfos.add(Ayantdroitobj);
        }       
        
        Response.add("Recuperation des infos OK");//1
        Response.add("/RenseignementInformationsSupplementaire.jsp"); //2 JSP creation de devis avec liste object Ayants droits + infos personne (nom, prenom, mail,numero SS population)
        Response.add(pers);//3 la personne qui crée le devis
        Response.add(listeinfos);//4 les ayant drois (nom, prenom, numeroSS)
          
        return Response;
    }
    
    
    @Override
    public List<Object> VerifierDevisID(String iddevis){
        List<Object> Response=new ArrayList();
        long idd;
        try{
            idd=Integer.parseInt(iddevis);
        }catch(NumberFormatException e){
            Response.add("Erreur ID Devis");
            Response.add("/Message.jsp");
            Response.add(null);
            return Response;
        }
        Devis d=devisFacade.rechercheExistantID(idd);
        if(d==null){
            Response.add("Erreur ID Devis");
            Response.add("/Message.jsp");
            Response.add(null);
            return Response;
        }
        else{
            Response.add("PageDevisInformationsSupplementaire");
            Response.add("/PageDevisInformationsSupplementaire.jsp");
            Response.add(d);
            return Response;
        }
    }
    
    @Override
    public void RIB(){
        List<PersonnePhysique> listep=personnePhysiqueFacade.findAll();
        for(PersonnePhysique p : listep){
            String RIB=String.valueOf(1+(new Random()).nextInt(9));
            for(int i=0;i<22;i++){
                RIB=RIB+String.valueOf((new Random()).nextInt(10));
            }
            personnePhysiqueFacade.SetRIB(p, RIB);
        }
    }
    
    
    
    
    
}
