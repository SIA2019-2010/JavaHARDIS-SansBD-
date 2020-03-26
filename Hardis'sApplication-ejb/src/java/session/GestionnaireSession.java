/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entitee.Acte;
import entitee.Activite;
import entitee.Beneficiaire;
import entitee.Contrat;
import entitee.Devis;
import entitee.Domaine;
import entitee.EtatRemboursement;
import entitee.Fiscalite;
import entitee.Garantie;
import entitee.Genre;
import entitee.Gestionnaire;
import entitee.LibelleActe;
import entitee.PersonneMorale;
import entitee.PersonnePhysique;
import entitee.PlafondMensuelSecuSociale;
import entitee.Population;
import entitee.PriseEnCharge;
import entitee.Produit;
import entitee.Remboursement;
import entitee.Responsable;
import entitee.StatutBeneficiaire;
import entitee.TypeGarantie;
import entitee.TypeProduit;
import facade.ActeFacadeLocal;
import facade.ActiviteFacadeLocal;
import facade.BeneficiaireFacadeLocal;
import facade.ContratFacadeLocal;
import facade.DevisFacadeLocal;
import facade.DomaineFacadeLocal;
import facade.FiscaliteFacadeLocal;
import facade.GarantieFacadeLocal;
import facade.GestionnaireFacadeLocal;
import facade.PersonneMoraleFacadeLocal;
import facade.PersonnePhysiqueFacadeLocal;
import facade.PopulationFacadeLocal;
import facade.ProduitFacadeLocal;
import facade.RemboursementFacadeLocal;
import facade.ResponsableFacadeLocal;
import facade.StatutBeneficiaireFacadeLocal;
import facade.TypeGarantieFacadeLocal;
import facade.TypeProduitFacadeLocal;
import java.lang.reflect.Array;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 *
 * @author lixin
 */
@Stateless
public class GestionnaireSession implements GestionnaireSessionLocal {

    @EJB
    private ContratFacadeLocal contratFacade;

    @EJB
    private DomaineFacadeLocal domaineFacade;
    
    @EJB
    private RemboursementFacadeLocal remboursementFacade;

    @EJB
    private StatutBeneficiaireFacadeLocal statutBeneficiaireFacade; 

    @EJB
    private ActeFacadeLocal acteFacade;

    @EJB
    private DevisFacadeLocal devisFacade;

    @EJB
    private PersonnePhysiqueFacadeLocal personnePhysiqueFacade;

    @EJB
    private PopulationFacadeLocal populationFacade;

    @EJB
    private GarantieFacadeLocal garantieFacade;

    @EJB
    private FiscaliteFacadeLocal fiscaliteFacade;

    @EJB
    private TypeGarantieFacadeLocal typeGarantieFacade;

    @EJB
    private TypeProduitFacadeLocal typeProduitFacade;

    @EJB
    private ProduitFacadeLocal produitFacade;

    @EJB
    private ResponsableFacadeLocal responsableFacade;

    @EJB
    private ActiviteFacadeLocal activiteFacade;

    @EJB
    private PersonneMoraleFacadeLocal personneMoraleFacade;

    @EJB
    private GestionnaireFacadeLocal gestionnaireFacade;
    
    @EJB
    private BeneficiaireFacadeLocal beneficiaireFacade;
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public List<Object> authentificationGestionnaire(String login, String mdp, HttpServletRequest request) {
        System.out.println("authenRespon"+login+"   "+mdp);
        List<Object> Response=new ArrayList();
        HttpSession session = request.getSession(false);
        session.invalidate();
        if(login.trim().isEmpty()||mdp.trim().isEmpty()){
            Response.add("Il manque de champs");
            Response.add("/Connexion.jsp");
            System.out.println("champs null");
            request.setAttribute("typeConnexion ","GestionnaireConnexion");
        }
        else{
            Gestionnaire sessiongestionnaire=gestionnaireFacade.authentificationGestionnaire(login, mdp);
            if(sessiongestionnaire==null){
                Response.add("Erreur :login ou mdp");
                Response.add("/Connexion.jsp");
                System.out.println("erreur mdp");
                request.setAttribute("typeConnexion","GestionnaireConnexion");
            }
            else{
                Response.add("Connexion réussie");
                Response.add("/GestionnaireMenu.jsp");
                System.out.println("reussie");
                session = request.getSession(true);
                session.setAttribute("sessiongestionnaire",sessiongestionnaire);
                request.setAttribute("typeConnexion","GestionnaireConnexion");
            }
        }
        return Response;
    }
    
    @Override
    public List<Object> modifiermdp(Gestionnaire gest, String OMDP, String NMDP, String RMDP){
        List<Object> Response=new ArrayList();
        if(gest==null||OMDP==null||NMDP==null||RMDP==null){
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
        else if(!gest.getMdp().equals(OMDP)){
            Response.add("Ancien mot de passe incorrecte");
            Response.add("/PageModifierMdp.jsp");
        }
        else if(OMDP.equals(NMDP)){
            Response.add("faur choisir un mot de pas différent");
            Response.add("/PageModifierMdp.jsp");
        }
        else{
            gestionnaireFacade.modifierMdp(gest, RMDP);
            Response.add("Mot de passe modifié");
            Response.add("/PageModifierMdp.jsp");
        }
        
        return Response;
    }

    @Override
    public PersonneMorale creerMorale(String siret,String raisonSo,String adresse,Activite acti) {
        return personneMoraleFacade.creerPersonneMorale(siret, raisonSo, adresse, acti);
    }

    @Override
    public List<Object> creerMoraleComplet(List<String> pers) {
        List<Object> Response=new ArrayList();
       //Objet pers : 1 SIRET, 2 raisonSociale, 3 adresse, 4 activite (String de ID)
   
       
       if(pers.get(0).isEmpty()||pers.get(1).isEmpty()||pers.get(2).isEmpty()||pers.get(3).isEmpty()){
             Response.add("Merci de remplir la totalité des champs");//1
             Response.add("/CreationMorale.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
             //Response.add(pers);//3 les infos deja données
             
            return Response; //manque des champs donc renvoi de toutes les informations
       }
       //controle sur l'activite
       Long idact=Long.valueOf(pers.get(3));
       Activite act = activiteFacade.rechercheActiviteExistantID(idact);
       if(act==null){
             Response.add("Probleme sur l'activite");//1
             Response.add("/CreationMorale.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
             //Response.add(pers);//3 les infos deja données
             
            return Response; //Population introuvable
       }
        
        //si tout est bien rempli : 
        
        PersonneMorale persmo;
        persmo=creerMorale(pers.get(0),pers.get(1),pers.get(2),act);
        
        Response.add("Personne Morale créée");//1
        Response.add("/MenuGestionnaire.jsp"); //2 
        Response.add(persmo);//3 la personne morale créée
       
        
        return Response;
    }

    @Override
    public Responsable creerResponsable(String nom, String prenom, String login, String mdp, String mail, String tel, PersonneMorale personneMorale) {
        return responsableFacade.creerResponsable(nom, prenom, mail, tel, login, mdp, personneMorale);
    }

    @Override
    public List<Object> creerResponsableComplet(List<String> pers) {
        List<Object> Response=new ArrayList();
       //Objet pers : 1 nom, 2 prenom, 3 mail, 4 tel,5 PersonneMorale (String de ID)
        
        if(pers.get(0).isEmpty()||pers.get(1).isEmpty()||pers.get(2).isEmpty()||pers.get(3).isEmpty()||pers.get(4).isEmpty()){
             Response.add("Merci de remplir la totalité des champs");//1
             Response.add("/CreationResponsable.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
            // Response.add(pers);//3 les infos deja données
             
            return Response; //manque des champs donc renvoi de toutes les informations
       }
        
        //controle sur la personne morale
       Long idpersmo=Long.valueOf(pers.get(4));
       PersonneMorale persmo = personneMoraleFacade.rechercheExistantID(idpersmo);
       if(persmo==null){
             Response.add("Probleme sur la personneMorale");//1
             Response.add("/CreationResponsable.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
             //Response.add(pers);//3 les infos deja données
             
            return Response; //PersonneMorale introuvable
       }
       
       //generation login + mdp
                //login = Nom+2lettres du prenom, si pas disponible ajout d'un chiffre en 1 et 9 aléatoire et check a nouveau la dispo
                boolean dispologin=false;
                String login=((String)Array.get(pers, 0))+(((String)Array.get(pers, 1)).substring(0, 2));
                
                
                while(dispologin==false){
               
                  if(personneMoraleFacade.rechercheDispoLogin(login)==false){
                      Random rand = new Random(); int nombreAleatoire = rand.nextInt(9 - 1 + 1) + 1;
                    login=login+String.valueOf(nombreAleatoire);                   
                   } else {
                      dispologin=true;
                  }
                }    
                    
                //hash nom+prenom     
                String mdp=(String.valueOf(pers.get(0).hashCode())+String.valueOf((pers.get(1).hashCode())));
       
                
       Responsable resp;
       resp=creerResponsable(pers.get(0),pers.get(1),pers.get(2),pers.get(3),login,mdp,persmo);
       
        Response.add("Personne Morale créée");//1
        Response.add("/MenuGestionnaire.jsp"); //2 
        Response.add(resp);//3 le gestionnaire créé
        
        return Response;
        
        //envoyer login+mdp
    }

    @Override
    public Produit creerProduit(String nom, List<Beneficiaire> lesBeneficiaires, Beneficiaire lesAssiette, List<TypeGarantie> lesTypesGaranties, TypeProduit leTypeProduit, Fiscalite laFiscalite,List<Population> lesPopulations, PersonneMorale laPersonneMorale,Domaine leDomaine) {
        return produitFacade.creerProduit(nom, lesBeneficiaires, lesAssiette, lesTypesGaranties, leTypeProduit, laFiscalite, lesPopulations, laPersonneMorale,leDomaine);
    }

    @Override
    public List<Object> creerProduitComplet(List<String> infos,List<String> lesbenefs,String leassiette,List<Long> lestypes,List<Long> lespops) {
        List<Object> Response=new ArrayList();
        //infos : 1 String nom, 2 ID leTypeProduit, 3 ID laPersonneMorale,4 ID leDomaine,5 ID laFiscalite
        //liste :  ENUMSET : lesBeneficiaires, 3 ENUMSET : lesAssiettes, 4 LIST lesTypesGaranties,
        //liste :  6 lesFiscalites, 7 lesPopulations, 
        List<Beneficiaire> lesBeneficiaires = new ArrayList();
        Beneficiaire leAssiette;
        List<TypeGarantie> lesTypes=new ArrayList();
        List<Population> lesPopulations=new ArrayList();
        
        
        //Controle sur les 4 champs individuel : nom, ID Typeprod, ID Personnemorale, ID fisca
        if(infos.get(0).isEmpty()||infos.get(1).isEmpty()||infos.get(2).isEmpty()||infos.get(3).isEmpty()||infos.get(4).isEmpty()){
             Response.add("Merci de remplir la totalité des champs");//1
             Response.add("/CreationProduit.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
            
            //Trop d'info pour renvoyer toute les listes
             
            return Response; //manque des champs, pas de renvoi d'informations car trop d'object
       }
        
        
         //controle sur la personne morale
       Long idpersmo=Long.valueOf((String)Array.get(infos, 1));
       PersonneMorale persmo = personneMoraleFacade.rechercheExistantID(idpersmo);
       if(persmo==null){
             Response.add("Probleme sur la personne Morale");//1
             Response.add("/CreationResponsable.jsp"); //2 JSP creation 
             
            return Response; //PersonneMorale introuvable, pas de renvoi d'informations
       }
       
          //controle sur le type produit
       Long idprod=Long.valueOf((String)Array.get(infos, 2));
       TypeProduit typep = typeProduitFacade.rechercheExistantID(idprod);
       if(typep==null){
             Response.add("Probleme sur le Type de produit");//1
             Response.add("/CreationResponsable.jsp"); //2 JSP creation
             
            return Response; //TypeProduit introuvable,pas de renvoi d'informations
       }
       
              //controle sur le domaine
       Long iddom=Long.valueOf((String)Array.get(infos, 3));
       Domaine dom = domaineFacade.rechercheExistantID(iddom);
       if(dom==null){
             Response.add("Probleme sur le Domaine");//1
             Response.add("/CreationResponsable.jsp"); //2 JSP creation 
             
            return Response; //Domaine introuvable,pas de renvoi d'informations
       }
       
       
             //controle sur la fisca
         Long idficsa=Long.valueOf((String)Array.get(infos, 4));
          Fiscalite fisca=fiscaliteFacade.rechercheExistantID(idficsa);
          
        
       
       
       //Transformation des listes en liste d'objet appropriée:
       
       //EnumSet beneficiaire
        for(String beneficiaire: lesbenefs){
            
            lesBeneficiaires.add(beneficiaireFacade.rechercheExistantBeneficiaireLibelle(beneficiaire));
                   
        }
       //enumSet Assiette
        //for(String assiette: lesassiettes){
           
            leAssiette=beneficiaireFacade.rechercheExistantBeneficiaireLibelle(leassiette);
                   
        //}
        
       //liste type
        for(Long type: lestypes){
          TypeGarantie typegar;
           typegar= typeGarantieFacade.rechercheExistantID(type);
            
          lesTypes.add(typegar);
          
        }
           
         //liste popo
        for(Long popu: lespops){
          Population population;
           population=populationFacade.rechercheExistantPopulationID(popu);
     
           lesPopulations.add(population);
          
        }
           
        //si tout c'est bien passé : (ajouter les controles)
        
        Produit prod;
        prod=produitFacade.creerProduit(infos.get(0), lesBeneficiaires, leAssiette, lesTypes, typep, fisca, lesPopulations, persmo,dom);
        
        Response.add("Produit créée");//1
        Response.add("/MenuGestionnaire.jsp"); //2 
        Response.add(prod);//3 le produit créé
        
        return Response;
        
    }
    
    
    
    
    
    
    //suite de methode pour creation de devis , avec ou sans recherche 
    
    @Override
    public List<Object> calculPacksGestionnaire(Object[] pers,List<Object[]>listeinfos) {
      List<Object> Response=new ArrayList();
       
        //la personne qui crée le devis n'est pas stocké dans la liste d'objet mais dans un object a part
       //Objet pers : 1 nom, 2 prenom, 3 datenaiss, 4 numero SS ,5 mail,6 Population (String de ID)
       
       //ayants droits  : List Object listeinfos
       //1 Nom ; 2Prenom ; 3datenaiss ; 4numeroSS ;
       
       if(((String)Array.get(pers, 0)).equals("")||((String)Array.get(pers, 1)).equals("")||((Date)Array.get(pers, 2))==null||((String)Array.get(pers, 3)).equals("")||((String)Array.get(pers, 4)).equals("")||((String)Array.get(pers, 5)).equals("")){
             Response.add("Il manque des champs");//1
             Response.add("/CreationDevis.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
             Response.add(pers);//3 la personne qui crée le devis
             Response.add(listeinfos);//4 les ayant drois (nom, prenom, datenaiss, population)
             Response.add(null); //5 pas de devis
             
            return Response; //manque des champs donc renvoi de toutes les informations
       }
       //Date de naissance
       Date DateN=java.sql.Date.valueOf((String)Array.get(pers, 2));
       double coef;
       if(DateN==null||DateN.after(new Date())){
            Response.add("Effeur Date");//1
            Response.add("/CreationDevis.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
            Response.add(pers);//3 la personne qui crée le devis
            Response.add(listeinfos);//4 les ayant drois (nom, prenom, datenaiss, population)
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
            Response.add("/CreationDevis.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
            Response.add(pers);//3 la personne qui crée le devis
            Response.add(listeinfos);//4 les ayant drois (nom, prenom, datenaiss, population)
            Response.add(null); //5 pas de devis
             
            return Response; //Population introuvable
       }
        //controle sur les ayants droits
        for(Object[] infos: listeinfos){
            String nom=(String)Array.get(infos, 0);
            String prenom=(String)Array.get(infos, 1);
            Date datenaiss=java.sql.Date.valueOf((String)Array.get(infos, 2));
            String numeroSS = (String)Array.get(infos,3);
            
            
            if(nom==null||prenom==null||datenaiss==null||numeroSS==null){
                Response.add("Il manque des champs");//1
                Response.add("/CreationDevis.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
                Response.add(pers);//3 la personne qui crée le devis
                Response.add(listeinfos);//4 les ayant drois (nom, prenom, datenaiss, population)
                Response.add(null); //5 pas de packs (produit+prix)
             
                return Response; //tout ce qu'on a donné
            }
            
            DateN=java.sql.Date.valueOf((String)(Array.get(infos, 2)));
            if(DateN==null||DateN.after(new Date())){
                 Response.add("Effeur Date");//1
                 Response.add("/CreationDevis.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
                 Response.add(pers);//3 la personne qui crée le devis
                 Response.add(listeinfos);//4 les ayant drois (nom, prenom, datenaiss, population)
                 Response.add(null); //5 pas de devis
                 return Response; //manque des champs donc renvoi de toutes les informations
            }
            else{
                coef+=DateToCoef(DateN);
            }
            
            
        }
        
         // si tout les champs sont bien remplis : Algo pour prix+produit dans un objet 
         
        
        Date dateDevis = new Date();
        List<Produit> listproduit=produitFacade.afficherProduit();
        List<Object[]> lesPacks=new ArrayList();
        for(Produit pr:listproduit){
            Object[] packproduit={pr,pr.getPrixBase()*coef};
            lesPacks.add(packproduit);
        }
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
    public List<Object> creerDevisCompletGestionnaire(Object[] pers,Object[] pack,List<Object[]>listeinfos) {
        List<Object> Response=new ArrayList();
        List<PersonnePhysique> LesAyantdroit=new ArrayList();
 
       // 1 devis, 1 personne qui crée, 1 liste personne (Ayants droits)
            //---> creation de la personne si existe pas (test sur ID)
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
            Date datenaiss =java.sql.Date.valueOf((String)Array.get(infos, 2));
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
           
        devcree=devisFacade.creerDevis(persencours,(Produit)Array.get(pack, 0),(Double)Array.get(pack, 1),dateDevis,LesAyantdroit);  
        
        
        Response.add("Devis créée "); // 1
        Response.add("/MenuGestionnaire.jsp"); // 2 Jsp pour afficher 
        Response.add(devcree);//3 le devis
        
        
        return Response;
    }

    
    
       @Override
    public List<Object> DevisAvecRecherchePersonne(Gestionnaire gest,Long idpers) {
        List<Object> Response=new ArrayList();
        Object[] pers = null;
        List<Object[]>listeinfos = new ArrayList();
        //cette methode consiste a creer les object pour envoyer dans la JSP creation de devis
        
        //on recuperer la personne
        PersonnePhysique personne=personnePhysiqueFacade.recherchePersonneID(idpers);
        List<PersonnePhysique>Ayantsdroit=new ArrayList();
        
        Domaine dom = gest.getLeDomaine();
        
        //on recupere les ayants droits de son contrat du domaine concerné
        StatutBeneficiaire lestatutaffilie = statutBeneficiaireFacade.rechercheAffilieDomaine(personne, dom);
    
        List<StatutBeneficiaire>lespers = lestatutaffilie.getLeContrat().getLesStatutsBeneficiaire();
        
        List<StatutBeneficiaire>statutsayants=new ArrayList();
        
        for (StatutBeneficiaire st : lespers){
            if(st.getLaBeneficiaire().getLibelleBeneficiaire().equals("Concubin")){
                statutsayants.add(st);
            }
            if(st.getLaBeneficiaire().getLibelleBeneficiaire().equals("Conjoint")){
                statutsayants.add(st);
            }
            if(st.getLaBeneficiaire().getLibelleBeneficiaire().equals("EnfantACharge")){
                statutsayants.add(st);
            }
            
        }
        
        for (StatutBeneficiaire stpers : statutsayants){
            Ayantsdroit.add(stpers.getLaPersonnePhysique());
        }
        
        //Objet pers : 1 nom, 2 prenom, 3 datenaiss, 4 numero SS ,5 mail,6 Population (String de ID)
        Array.set(pers, 0, personne.getNom());
        Array.set(pers, 1, personne.getPrenom());
        
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //j'ai trouver que ca pour Date To String
        String datenaiss  = formatter.format(personne.getDateNaiss());

        Array.set(pers, 2, datenaiss); 
        Array.set(pers, 3, personne.getNumeroSS());
        Array.set(pers, 4, personne.getMail());
        Array.set(pers, 4, personne.getLaPopulation().getId());
        
       //ayants droits  : List Object listeinfos
       //1 Nom ; 2Prenom ; 3datenaiss ; 4numeroSS ;
       
        for (PersonnePhysique ayt : Ayantsdroit) {
             Object[]Ayantdroitobj=null;
             Array.set(Ayantdroitobj, 0, ayt.getNom());
             Array.set(Ayantdroitobj, 1, ayt.getPrenom());
             Array.set(Ayantdroitobj, 2, ayt.getDateNaiss());
             Array.set(Ayantdroitobj, 0, ayt.getNumeroSS());
           
             listeinfos.add(Ayantdroitobj);
        }       
       
        Response.add("Recherche terminée");//1
        Response.add("/CreationDevis.jsp"); //2 JSP creation de devis avec liste object Ayants droits + infos personne (nom, prenom, mail,numero SS population)
        Response.add(pers);//3 la personne qui crée le devis
        Response.add(listeinfos);//4 les ayant drois (nom, prenom, datenaiss, population)
        
        
        
        return Response;
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
    
    
    
    
    
    

    //Suite de methode pour creer un remboursement :
    //1 affichage de tout les actes non remboursé(rechercheListeActesNonRembourse) dans une jsp, choix du gestionnaire sur un acte en particulier,
    //appel de la methode creerRemboursement avec l'ID de l'acte choisi
    //3 jsp 3 affichage du remboursement
    
                //methode utile que sur une recherche de personne 
    @Override
    public List<Acte> rechercheActesNonRemboursePersonne(Long idpers) {
        
         //on recupere la personne choisie
         PersonnePhysique pers = personnePhysiqueFacade.recherchePersonneID(idpers);
         
         List<Acte> listact = acteFacade.rechercheActePersNonRemb(pers);
        
        return listact;
    }
    
    
                    //on affiche la liste des actes non remboursé et le gestionnaire en choisi un
       @Override
    public List<Acte> rechercheListeActesNonRembourse() {
        List<Acte> listact = acteFacade.rechercheActesNonRembourse();
         
        return listact;
    }
    
    
    
    
        @Override
    public List<Object> creerRemboursement(Long idact) {
         List<Object> Response=new ArrayList();
        
        Acte act=acteFacade.rechercheActeID(idact);
         
        Boolean practiCAS =act.getLePraticien().isAdherentCAS();
        
        PlafondMensuelSecuSociale pmss=act.getLePlafond();
        LibelleActe l = act.getLeLibelleActe();
        Garantie g = l.getLaGarantie();
        PersonnePhysique pp = act.getLaPersonnePhysique();
        
        
       List<TypeGarantie> typegli=g.getLesTypesGarantie(); //--> les modules concerné par l'acte en question
       
       //on recupere le contrat
       List<Contrat> contrats=statutBeneficiaireFacade.rechercheContratsAffilie(pp);
       
       //filtre les contrats sur Domaine de la sante
       List<Contrat>contratssante=new ArrayList();
       
      for (Contrat ct : contrats){
          if(ct.getLeProduit().getLeDomaine().getLibelleDomaine().equalsIgnoreCase("Sante")){
              contratssante.add(ct);
          }
          
      }
      //on recuperer contrat individuel ou contrat collectif, si presence de complementaire on recuperer complementaire
      Contrat lecontrat=null;
      
      if(contratssante.size()==1){
        lecontrat=contratssante.get(0);
      }
      else{
          for (Contrat ct : contratssante){
          Produit leprod = ct.getLeProduit();
          TypeProduit letype=leprod.getLeTypeProduit();
           
            if(letype.getLibelleTypeProduit().equalsIgnoreCase("Surcomplementaire")){
                lecontrat=ct;
            }    
          }             
       }
      
           
       //on compare les garanties couvertes par le produit, et dans quel module se trouve l'acte pour
       // savoir si on rembourse ou non 
       
      List<TypeGarantie>typeglicouverte=lecontrat.getLeProduit().getLesTypesGarantie();
      //typegli list typegarantie = les garanties couverte par le produit
      
       boolean couvert=false;
       String lenom;
 
      for(TypeGarantie typ : typegli){
         
              lenom=typ.getTypeGarantie();
              
              for (TypeGarantie typacheck:typeglicouverte){
                  if(lenom==typacheck.getTypeGarantie()){
                      couvert=true;
                  }
                  
              }   
              
         }
    
      
      if (couvert==false){ //garantie pas couverte dans le produit
                Remboursement rembour=remboursementFacade.creerRemboursement(0,EtatRemboursement.NonRembourse,act);
                //creerRemboursement avec 0
      }
       List<PriseEnCharge> lesprisesench=lecontrat.getLeProduit().getLesPriseEnCharges();
      
       //si le practitien est adherentCAS on recupere la prise en charge adhérent et inversement(qui contient les pourcentages de remboursement)
       PriseEnCharge p=null;
       for (PriseEnCharge prise : lesprisesench){
           if (prise.isAdherentCAS()==practiCAS){
              p=prise;  
           }
       }
       
       String TypeBaseRemboursement = p.getBaseRemboursement();//TM,FR,BR,BR-RSS,PMSS
        double TauxRemboursement = p.getTauxRempoursement();
        double depense = act.getDepense();//dépense effective pour l'acte
        double TauxRemboursementSecu = g.getLaBaseRemboursementSeco().getTauxRemboursementSecu();
       
        double BaseRemboursementSecu = g.getLaBaseRemboursementSeco().getBaseRemboursementSecu();
        double RemboursementEffectif = 0;
        double MontantPMSS = pmss.getPlafond();

        if (TypeBaseRemboursement=="BR")
    {RemboursementEffectif=TauxRemboursement*BaseRemboursementSecu;}
        else if (TypeBaseRemboursement=="TM")
	{RemboursementEffectif=TauxRemboursement*(TauxRemboursementSecu*BaseRemboursementSecu);}
        else if (TypeBaseRemboursement=="FR")
	{RemboursementEffectif=TauxRemboursement*depense;}
        else if (TypeBaseRemboursement=="BR-RSS")
	{RemboursementEffectif=TauxRemboursement*(BaseRemboursementSecu-(TauxRemboursementSecu*BaseRemboursementSecu));}
        else if (TypeBaseRemboursement=="PMSS")
	{RemboursementEffectif=TauxRemboursement*MontantPMSS;}
        else if (TypeBaseRemboursement==null)
	{RemboursementEffectif=TauxRemboursement;}

        Remboursement rembour=remboursementFacade.creerRemboursement(RemboursementEffectif, EtatRemboursement.EnCours, act);
                //CreationRemboursement;
         
        Response.add("Remboursement créé "); // 1
        Response.add("/MenuGestionnaire.jsp"); // 2 Jsp pour afficher 
        Response.add(rembour);//3 le remboursement créé

        return Response;
    }

    @Override
    public List<Object> validerRemboursement(Long idremb) {
        List<Object> Response=new ArrayList();
        
        Remboursement remb = remboursementFacade.rechercheExistantID(idremb);
        
        remb = remboursementFacade.validerRemboursement(remb);
        
        Response.add("Remboursement créé "); // 1
        Response.add("/MenuGestionnaire.jsp"); // 2 Jsp pour afficher 
        Response.add(remb);//le remboursement validé
        
        return Response;
    }

    @Override
    public List<Object> refuserRemboursement(Long idremb) {
        List<Object> Response=new ArrayList();
        
        Remboursement remb = remboursementFacade.rechercheExistantID(idremb);
        
        remb = remboursementFacade.refuserRemboursement(remb);
        
        Response.add("Remboursement créé "); // 1
        Response.add("/MenuGestionnaire.jsp"); // 2 Jsp pour afficher 
        Response.add(remb);//le remboursement refusé
        
        return Response;
    }

    @Override
    public List<Object> cloturerContrat(Long idcontrat) {
        List<Object> Response=new ArrayList();
        
        Contrat ct = contratFacade.rechercheExistantID(idcontrat);
        
        ct=contratFacade.cloturerContrat(ct);
        
        
        Response.add("Contrat clos "); // 1
        Response.add("/MenuGestionnaire.jsp"); // 2 Jsp pour afficher 
        Response.add(ct);
        
        return Response;
    }

    
    @Override
    public List<Object> validerContrat(Long idcontrat) {
        List<Object> Response=new ArrayList();
        
        Contrat ct = contratFacade.rechercheExistantID(idcontrat);
        
        ct = contratFacade.ValiderContrat(ct);
        
        
        Response.add("Contrat validé "); // 1
        Response.add("/MenuGestionnaire.jsp"); // 2 Jsp pour afficher 
        Response.add(ct);
        
        return Response;
    }

    @Override
    public List<Object> refuserContrat(Long idcontrat) {
        List<Object> Response=new ArrayList();
        
        Contrat ct = contratFacade.rechercheExistantID(idcontrat);
        
        ct=contratFacade.RefuserContrat(ct);
        
        
        Response.add("Contrat refusé "); // 1
        Response.add("/MenuGestionnaire.jsp"); // 2 Jsp pour afficher 
        Response.add(ct);
        
        return Response;
    }
    
    
    
      
    
    
    
    
    
    @Override
    public void creerActivite(String n){
        System.out.println("creation session");
        activiteFacade.creerActivite(n);
        System.out.println("creation session sort");
    }
    
    
    
    //Suite de methode pour ajouter des personnes a un produit et ainsi creer son contrat
    
    
        @Override
    public List<Produit> rechercheProduitsCollectif() {
        return produitFacade.afficherProduitCollectif();
    }
    
    
    
    @Override
    public List<Object> ajouterPersonneProduitCollectif(Long idproduit,Object[] pers,List<Object[]>listeinfos) {
        List<Object> Response=new ArrayList();
        
        //dans cette methode : on recupere : 
        //l'id du produit (collectif) auquel on veux ajouter une personne et creer un contrat
        //Objet pers : 0genre (String) 1 nom, 2 prenom, 3 datenaiss, 4 numero SS , 5adresse, 6 mail,7 Population (String de ID)
                        //login,mdp generés ?
                        
                        // pour chaque ayant droit : 0 genre,1 nom,2 prenom,3 datenaiss,4 numeross,5 adresse
                        //7 population, 8 ID (Conjoint, Concubin, Enfant à charge)
        
        Produit prod=produitFacade.rechercheProduitID(idproduit);
        
         if (prod==null){
             Response.add("Probleme sur la creation (choix du produit)");//1
             Response.add("/menuGestionnaire.jsp"); 
             Response.add(null);
             Response.add(null);
             
            return Response; //probleme transition ID Produit
         }
        
        //controle sur les champs remplis : personnephy    
       // 0 genre (String) 1 nom, 2 prenom, 3 datenaiss, 4 numero SS , 5adresse, 6 mail,7 Population (String de ID)
        if(((String)Array.get(pers, 0)).equals("")||((String)Array.get(pers, 1)).equals("")||((String)Array.get(pers, 2)).equals("")||((String)Array.get(pers, 3)).equals("")||((String)Array.get(pers, 4)).equals("")||((String)Array.get(pers, 5)).equals("")||((String)Array.get(pers, 6)).equals("")||((String)Array.get(pers, 7)).equals("")){
             Response.add("Merci de remplir les champs obligatoires");//1
             Response.add("/AjouterPersonneProduit.jsp"); //2 JSP AjouterPersonneProduit
             Response.add(pers);//3 la personne qui crée le devis (0genre (String) 1 nom, 2 prenom, 3 datenaiss, 4 numero SS , 5adresse, 6 mail,7 Population (String de ID))
             Response.add(listeinfos);//4 les ayant droits
             
            return Response; //manque des champs donc renvoi de toutes les informations
       }
       
        
         //controle les champs remplis : ayants droits
         // pour chaque ayant droit : 0 genre,1 nom,2 prenom,3 datenaiss,4 numeross,5 adresse
          //6 population, 7 STRING (Conjoint, Concubin, Enfant à charge)
           List<PersonnePhysique> LesAyantdroit=new ArrayList();
        for(Object[] infos: listeinfos){
            String genre=(String)Array.get(infos, 0);
            String nom=(String)Array.get(infos, 1);
            String prenom=(String)Array.get(infos, 2);
            Date datenaiss=java.sql.Date.valueOf((String)Array.get(infos, 3));
            String numeroSS = (String)Array.get(infos,4);
            String adresse = (String)Array.get(infos,5);
            String idpopst = (String)Array.get(infos,6);
            String statutayt=(String)Array.get(infos,7);
            
            if(genre==null||nom==null||prenom==null||adresse==null||numeroSS==null||datenaiss==null||idpopst==null||statutayt==null){
                 Response.add("Merci de remplir les champs obligatoires");//1
                 Response.add("/RenseignementInformationsSupplementaire.jsp"); //2 JSP renseignements complementaire
                 Response.add(pers);
                 Response.add(listeinfos);
             
            return Response; //manque des champs donc renvoi de toutes les informations
        
           } else{ //on créer les ayants droits si ils existent
                
                     PersonnePhysique ayantdroitencours;
                     ayantdroitencours=personnePhysiqueFacade.recherchePersNumeroSS(numeroSS);

                    if (ayantdroitencours==null){
                            Long idpop=Long.valueOf(idpopst);
                            Population pop = populationFacade.rechercheExistantPopulationID(idpop);  
                        
                            Genre genreayt=null;
                             if(genre.equalsIgnoreCase("Homme")){
                             genreayt=Genre.Homme;
                             }else if(genre.equalsIgnoreCase("Femme")){
                            genreayt=Genre.Femme;
                             }else if(genre.equalsIgnoreCase("Autre")){
                            genreayt=Genre.Autre;
                              }
                            ayantdroitencours=personnePhysiqueFacade.creerPersonneComplete(nom, prenom, genreayt, java.sql.Date.valueOf((String)Array.get(pers, 3)), numeroSS, null, pop, adresse); 
                       }
                    
        
            LesAyantdroit.add(ayantdroitencours);  
                
            }
        }


 
        //on créer la personne
      // 0 genre (String) 1 nom, 2 prenom, 3 datenaiss, 4 numero SS , 5adresse, 6 mail,7 Population (String de ID)
        
        PersonnePhysique persencours=personnePhysiqueFacade.recherchePersNumeroSS((String)Array.get(pers, 4));
            if(persencours==null){
                
                Long idpop=Long.valueOf((String)Array.get(pers, 7));
                Population pop = populationFacade.rechercheExistantPopulationID(idpop);
                Date datenaisspers=java.sql.Date.valueOf((String)Array.get(pers, 3));
                
                 Genre genrepers=null;
                 if(((String)Array.get(pers, 0)).equalsIgnoreCase("Homme")){
                 genrepers=Genre.Homme;
                 }else if(((String)Array.get(pers, 0)).equalsIgnoreCase("Femme")){
                 genrepers=Genre.Femme;
                 }else if(((String)Array.get(pers, 0)).equalsIgnoreCase("Autre")){
                 genrepers=Genre.Autre;
                 }
                
                persencours=personnePhysiqueFacade.creerPersonneComplete(((String)Array.get(pers, 1)), ((String)Array.get(pers, 2)), genrepers,datenaisspers , ((String)Array.get(pers, 4)), ((String)Array.get(pers, 6)), pop, ((String)Array.get(pers, 5)));
                
                //generation login + mdp
                //login = Nom+2lettres du prenom, si pas disponible ajout d'un chiffre en 1 et 9 aléatoire et check a nouveau la dispo
                boolean dispologin=false;
                String login=((String)Array.get(pers, 1))+(((String)Array.get(pers, 1)).substring(0, 2));
                
                
                while(dispologin==false){
               
                  if(personnePhysiqueFacade.rechercheDispoLogin(login)==false){
                      Random rand = new Random(); int nombreAleatoire = rand.nextInt(9 - 1 + 1) + 1;
                    login=login+String.valueOf(nombreAleatoire);                   
                   } else {
                      dispologin=true;
                  }
                }    
                    
                    
                String mdp=(String.valueOf(datenaisspers.hashCode())+String.valueOf((((String)Array.get(pers, 4)).substring(5, 7)).hashCode()));
                persencours=personnePhysiqueFacade.enregistrerCompte(persencours, login, mdp);
                
            }
        
        
        //on crée le contrat sans les beneficiaires
        Contrat ct=contratFacade.creerContrat(null,prod.getPrixBase() ,prod);
        List<StatutBeneficiaire> liststatutct=new ArrayList();
        
        //on creer le statutBeneficiaire pour toutes les personnes concernées, ici afiilie
        StatutBeneficiaire statutaffi=null;
        String strbenef="Affilie";
        Beneficiaire benef=beneficiaireFacade.rechercheExistantBeneficiaireLibelle(strbenef);
        statutaffi=statutBeneficiaireFacade.creerStatutBeneficiaire(new Date(),benef, ct, persencours);
        
        //on rassemble tout les statuts dans une liste
        liststatutct.add(statutaffi);
        
        
        for(Object[] infos: listeinfos){
          
            String numeroSS = (String)Array.get(infos,4);
             
            PersonnePhysique ayantdroitencours;
            ayantdroitencours=personnePhysiqueFacade.recherchePersNumeroSS(numeroSS);  
            
            String statutayd=(String)Array.get(infos,7);
            Long idbenef = Long.valueOf(statutayd);
            
            Beneficiaire benefayd=beneficiaireFacade.rechercheExistantBeneficiaireID(idbenef);
            
             StatutBeneficiaire statutAyant=null;
             statutAyant=statutBeneficiaireFacade.creerStatutBeneficiaire(new Date(), benefayd, ct, ayantdroitencours);
             
             liststatutct.add(statutAyant);
             
        }
        
        //on a toutes les personnes et tout les statuts
       //on enregistre toute les personnes au contrat (set la liste)
       ct.SetLesStatutsBeneficiaires(liststatutct);
        
   
     Response.add("Personne ajoutée,contrat créé");//1
     Response.add("MenuGestionnaire.jsp"); //2 JSP 
     return Response;
    
    //envoyé login mdp au mail
    }

    
    //suite de methode pour listeJSP

    @Override
    public List<Activite> recupererActivites() {
        return activiteFacade.rechercheActivite();
    }

    @Override
    public List<PersonneMorale> recupererPersonneMorale() {
        return personneMoraleFacade.recherchePersmo();
    }
    
    @Override
    public List<Contrat> AfficherContratGestionnaire(Domaine dom){
        return contratFacade.AfficherContratGestionnaire(dom);
    }
    
    @Override
    public List<Contrat> AfficherContratCree(Domaine dom){
        return contratFacade.AfficherContratCree(dom);
    }
    
    @Override
    public List<Contrat> AfficherContratValide(Domaine dom){
        return contratFacade.AfficherContratCree(dom);
    }
    
    
    public static void sendMessage(/*String subject, String text, String destinataire, String copyDest*/) {
    // 1 -> Création de la session
    String destinataireee="alexis.baillieu@icloud.com";
    
    Properties properties = new Properties();
    properties.setProperty("mail.transport.protocol", "smtp");
    properties.setProperty("mail.smtp.host", "gmail.com");
    properties.setProperty("mail.smtp.user", "raxathmallyx");
    properties.setProperty("mail.from", "raxathmallyx@gmail.com");
    //Session session = Session.getInstance(properties);
    Session session = Session.getInstance(properties);
MimeMessage message = new MimeMessage(session);
    try {
        message.setText("Test d'envoie de mail");
        message.setSubject("Test d'envoie de mail");
        message.addRecipients(Message.RecipientType.TO, destinataireee);
        //message.addRecipients(Message.RecipientType.CC, copyDest);
    } catch (MessagingException e) {
        e.printStackTrace();
    }

Transport transport = null;
    try {
        transport = session.getTransport("smtp");
        transport.connect("raxathmallyx", "testnetbeans2020");
        transport.sendMessage(message, new Address[] { new InternetAddress(destinataireee),
                                                        /*new InternetAddress(copyDest)*/ });
    } catch (MessagingException e) {
        e.printStackTrace();
    } finally {
        try {
            if (transport != null) {
                transport.close();
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}    
    
}