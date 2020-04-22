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
import entitee.Unite;
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
import facade.PriseEnChargeFacadeLocal;
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
    private PriseEnChargeFacadeLocal priseEnChargeFacade;

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
        HttpSession session = request.getSession(true);
        session.invalidate();
        if(login.trim().isEmpty()||mdp.trim().isEmpty()){
            Response.add("Erreur : Il manque de champs");
            Response.add("/Connexion.jsp");
            System.out.println("champs null");
            request.setAttribute("typeConnexion ","GestionnaireConnexion");
        }
        else{
            Gestionnaire sessiongestionnaire=gestionnaireFacade.authentificationGestionnaire(login, mdp);
            if(sessiongestionnaire==null){
                Response.add("Erreur : login ou mdp incorrect");
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
            Response.add("Erreur : Erreur Session");
            Response.add("/ErreurSession.jsp");
        }
        else if(OMDP.equals("")||NMDP.equals("")||RMDP.equals("")){
            Response.add("Erreur : Remplisez tous les champs");
            Response.add("/PageModifierMdp.jsp");
        }
        else if(!NMDP.equals(RMDP)){
            Response.add("Erreur : Répétition mot de pas incorrecte");
            Response.add("/PageModifierMdp.jsp");
        }
        else if(!gest.getMdp().equals(OMDP)){
            Response.add("Erreur : Ancien mot de passe incorrecte");
            Response.add("/PageModifierMdp.jsp");
        }
        else if(OMDP.equals(NMDP)){
            Response.add("Erreur : Il faut choisir un mot de pas différent");
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
   
       
       if(pers.get(0).equals("")||pers.get(1).equals("")||pers.get(2).equals("")||pers.get(3).equals("")){
             Response.add("Erreur : Merci de remplir la totalité des champs");//1
             Response.add("/GestionnaireCreationMorale.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
             Response.add(null);//3 les infos deja données
             
            return Response; //manque des champs donc renvoi de toutes les informations
       }
       for(int i=0;i<3;i++)System.out.println(pers.get(i)+"  No "+i);
       //controle sur l'activite
       Long idact=Long.valueOf(pers.get(3));
       Activite act = activiteFacade.rechercheActiviteExistantID(idact);
       if(act==null){
             Response.add("Erreur : Probleme sur l'activite");//1
             Response.add("/GestionnaireCreationMorale.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
             Response.add(null);//3 les infos deja données
             
            return Response; //Population introuvable
       }
        
        //si tout est bien rempli : 
        
        PersonneMorale persmo;
        persmo=creerMorale(pers.get(0),pers.get(1),pers.get(2),act);
        
        Response.add("Erreur : Personne Morale créée");//1
        Response.add("/GestionnaireCreationMorale.jsp"); //2 
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
        
        if(pers.get(0).equals("")||pers.get(1).equals("")||pers.get(2).equals("")||pers.get(3).equals("")||pers.get(4).equals("")){
            System.out.println("vide");
            Response.add("Erreur : Merci de remplir la totalité des champs");//1
             Response.add("/GestionnaireCreationResponsable.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
            // Response.add(pers);//3 les infos deja données
             
            return Response; //manque des champs donc renvoi de toutes les informations
       }
        
        //controle sur la personne morale
       Long idpersmo=Long.valueOf(pers.get(4));
       PersonneMorale persmo = personneMoraleFacade.rechercheExistantID(idpersmo);
       if(persmo==null){
             Response.add("Erreur : Probleme sur la personneMorale");//1
             Response.add("/GestionnaireCreationResponsable.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
             //Response.add(pers);//3 les infos deja données
             
            return Response; //PersonneMorale introuvable
       }
       
       //generation login + mdp
                //login = Nom+2lettres du prenom, si pas disponible ajout d'un chiffre en 1 et 9 aléatoire et check a nouveau la dispo
                boolean dispologin=false;
                String login;
                if((pers.get(1)).length()==1) login=(pers.get(0))+((pers.get(1)).substring(0, 1))+"*";
                else login=(pers.get(0))+((pers.get(1)).substring(0, 2));
                
                
                while(dispologin==false){
               
                  if(responsableFacade.rechercheDispoLogin(login)==false){
                      Random rand = new Random(); int nombreAleatoire = rand.nextInt(9 - 1 + 1) + 1;
                    login=login+String.valueOf(nombreAleatoire);                   
                   } else {
                      dispologin=true;
                  }
                }    
                    
                //hash nom+prenom     
                String mdp=(String.valueOf(pers.get(0).hashCode())+String.valueOf((pers.get(1).hashCode())));
       
                
       Responsable resp;
        System.out.println(pers.get(0));
        System.out.println(pers.get(1));
        System.out.println(pers.get(2));
        System.out.println(pers.get(3));
        System.out.println(pers.get(4));
       resp=creerResponsable(pers.get(0),pers.get(1),login,mdp,pers.get(2),pers.get(3),persmo);
       //String nom, String prenom, String login, String mdp, String mail, String tel, PersonneMorale personneMorale
       //1 nom, 2 prenom, 3 mail, 4 tel,5 PersonneMorale (String de ID)
        Response.add("Responsable créé");//1
        Response.add("/GestionnaireCreationResponsable.jsp"); //2 
        Response.add(resp);//3 le gestionnaire créé
        
        return Response;
        
        //envoyer login+mdp
    }

//    @Override
//    public Produit creerProduit(String nom, List<Beneficiaire> lesBeneficiaires, Beneficiaire lesAssiette, List<TypeGarantie> lesTypesGaranties, TypeProduit leTypeProduit, Fiscalite laFiscalite,List<Population> lesPopulations, PersonneMorale laPersonneMorale,Domaine leDomaine) {
//        return produitFacade.creerProduit(nom, lesBeneficiaires, lesAssiette, lesTypesGaranties, leTypeProduit, laFiscalite, lesPopulations, laPersonneMorale,leDomaine);
//    }

    @Override
    public List<Object> creerProduitComplet(String NomProduit, String PrixProduit, String[] idbs, String[] idpops, String[] idtgs, List<String[]> listepec, String idp, String idd, String idb, String idf, String idtp) {
        List<Object> Response=new ArrayList();
        //infos : 1 String nom, 2 ID leTypeProduit, 3 ID laPersonneMorale,4 ID leDomaine,5 ID laFiscalite
        //liste :  ENUMSET : lesBeneficiaires, 3 ENUMSET : lesAssiettes, 4 LIST lesTypesGaranties,
        //liste :  6 lesFiscalites, 7 lesPopulations, 
        Beneficiaire leAssiette;
        List<Beneficiaire> lesBeneficiaires = new ArrayList();
        List<TypeGarantie> lesTypes=new ArrayList();
        List<Population> lesPopulations=new ArrayList();
        List<PriseEnCharge> lesPEC=new ArrayList();
        TypeProduit typep;
        Fiscalite fisca;
        PersonneMorale persmo;
        Domaine dom;
        Double prix;
        
        //Controle sur les 4 champs individuel : nom, ID Typeprod, ID Personnemorale, ID fisca
        if(NomProduit.equals("")||PrixProduit.equals("")||idbs==null||idpops==null||idtgs==null||idp.equals("")||idd.equals("")||idb.equals("")||idf.equals("")||idtp.equals("")){
            Response.add("Erreur : Merci de remplir la totalité des champs");//1
            
            //Trop d'info pour renvoyer toute les listes
             
            return Response; //manque des champs, pas de renvoi d'informations car trop d'object
        }
        prix=Double.valueOf(PrixProduit);
        if(produitFacade.rechererProduitNom(NomProduit)){
            Response.add("Erreur : Produuit avec même nom existe déja");//1
            return Response; 
        }
        String ee="Avant varification";
        try{
            ee="Probleme de idpersmo";
            //controle sur la personne morale
            if(!idp.equals("0")){
                Long idpersmo=Long.valueOf(idp);
                persmo = personneMoraleFacade.rechercheExistantID(idpersmo);
                if(persmo==null){
                    Response.add("Erreur : Probleme sur la personne Morale");//1

                    return Response; //PersonneMorale introuvable, pas de renvoi d'informations
                }
            }
            else{
                persmo=null;
            }
            
            ee="Probleme de idtpprodu";
             //controle sur le type produit
            Long idtpprod=Long.valueOf(idtp);
            typep = typeProduitFacade.rechercheExistantID(idtpprod);
            if(typep==null){
                Response.add("Erreur : Probleme sur le Type de produit");//1

                return Response; //TypeProduit introuvable,pas de renvoi d'informations
            }else if(typep.getLibelleTypeProduit().equals("Individuel")&&persmo!=null){
                Response.add("Erreur : Produit Individuel ne peut pas avoir une entrepries");
                return Response;
            }else if(!typep.getLibelleTypeProduit().equals("Individuel")&&persmo==null){
                Response.add("Erreur : Produit "+ typep.getLibelleTypeProduit() +" doit forément avoir une entrepries");
                return Response;
            }

                 //controle sur le domaine
            ee="Probleme de iddom";
            Long iddom=Long.valueOf(idd);
            dom = domaineFacade.rechercheExistantID(iddom);
            if(dom==null){
                Response.add("Erreur : Probleme sur le Domaine");//1

                return Response; //Domaine introuvable,pas de renvoi d'informations
            }

            ee="Probleme de idfisca";
                //controle sur la fisca
           Long idficsa=Long.valueOf(idf);
           fisca=fiscaliteFacade.rechercheExistantID(idficsa);


           ee="Probleme de idb";
           //Transformation des listes en liste d'objet appropriée:
           //EnumSet beneficiaire
           for (int i=0;i<idbs.length;i++) {
               String idb1=idbs[1];
               Beneficiaire b=beneficiaireFacade.rechercheExistantBeneficiaireID(Long.valueOf(idb1));
               if(b==null){
                   Response.add("Erreur : Probleme sur la beneficiaire");//1

                   return Response; //Domaine introuvable,pas de renvoi d'informations
               }
               lesBeneficiaires.add(b);
           }
           //enumSet Assiette

           leAssiette=beneficiaireFacade.rechercheExistantBeneficiaireID(Long.valueOf(idb));
           if(leAssiette==null){
               Response.add("Erreur : Probleme sur l'assiete");//1

               return Response; //Domaine introuvable,pas de renvoi d'informations
           }
           ee="Probleme de idtgs";

          //liste type
           for(int i=0;i<idtgs.length;i++){
               String idtgs1=idtgs[i];
               TypeGarantie typegar;
               
               typegar= typeGarantieFacade.find(Long.valueOf(idtgs1));
               if(typegar==null){
                   Response.add("Erreur : Probleme sur la type garantie");//1

                   return Response; //PersonneMorale introuvable, pas de renvoi d'informations
               }

               lesTypes.add(typegar);

           }

           ee="Probleme de idpops";
            //liste popo
           for(int i=0;i<idpops.length;i++){
               String idpops1=idpops[i];
               Population population;
               population=populationFacade.rechercheExistantPopulationID(Long.valueOf(idpops1));
               if(population==null){
                   Response.add("Erreur : Probleme sur la population");//1

                   return Response; //Domaine introuvable,pas de renvoi d'informations
               }
               lesPopulations.add(population);
           }
           
           
//            ee="Probleme de listepec";
//            for(String[] pec:listepec){
//                if(pec[1].equals("")||pec[2].equals("")||pec[3].equals("")){
//                    Response.add("Erreur : Merci de remplir la totalité des champs");//1
//                    return Response; //manque des champs, pas de renvoi d'informations car trop d'object
//                }
//                Garantie g=garantieFacade.find(Long.valueOf(pec[0]));
//                if(g==null){
//                   Response.add("Erreur : Probleme sur la garantie id "+pec[0]);//1
//
//                   return Response; //Domaine introuvable,pas de renvoi d'informations
//               }
//                double taux1=Double.valueOf(pec[2]);
//                double taux2=Double.valueOf(pec[3]);
//                if(taux2<0){
//                    Response.add("Erreur : Probleme sur la garantie");//1
//                    return Response;
//                }
//                if(taux1<taux2){
//                    Response.add("Erreur : Taux Rempousement ne peut pas être moins grand quand il est AdherentCAS<br/>"
//                            +"Garantie : "+ pec[0]+"  "+Double.valueOf(pec[2]).toString()+" "+Double.valueOf(pec[3]).toString());//1
//                    return Response;
//                }
//                
//                PriseEnCharge PEC=new PriseEnCharge();
//                PEC.setLaGarantie(g);
//                PEC.setUnite(Unite.pourcent);
//                switch (pec[1]){
//                    case "0":{
//                        PEC.setBaseRemboursement("BR");
//                        taux1/=100;
//                        taux2/=100;
//                        break;
//                    }
//                    case "1":{
//                        PEC.setBaseRemboursement("TM");
//                        taux1/=100;
//                        taux2/=100;
//                        break;
//                    }
//                    case "2":{
//                        PEC.setBaseRemboursement("FR");
//                        taux1/=100;
//                        taux2/=100;
//                        break;
//                    }
//                    case "3":{
//                        PEC.setBaseRemboursement("BR-RSS");
//                        taux1/=100;
//                        taux2/=100;
//                        break;
//                    }
//                    case "4":{
//                        PEC.setBaseRemboursement("PMSS");
//                        taux1/=100;
//                        taux2/=100;
//                        break;
//                    }
//                    case "5":{
//                        PEC.setBaseRemboursement(null);
//                        PEC.setUnite(Unite.euro);
//                        break;
//                    }
//                    default :{
//                        Response.add("Erreur : Probleme sur le type base remboursement");//1
//                        return Response; //Domaine introuvable,pas de renvoi d'informations
//                    }
//                }
//                System.out.println(taux1+"   "+taux2);
//                if((taux1>1000||taux2>1000)&&pec[1].equals("5")){
//                    Response.add("Erreur : Le taux remboursement de garantie "+g.getLibelleGarantie()+" est trop grand, max : 1000€");//1
//                    return Response; //Domaine introuvable,pas de renvoi d'informations
//                }else if((taux1>10||taux2>10)&&!pec[1].equals("5")){
//                    Response.add("Erreur : Le taux remboursement de garantie "+g.getLibelleGarantie()+" est trop grand, max : 1000%");//1
//                    return Response; //Domaine introuvable,pas de renvoi d'informations
//                }
//                if(taux1==taux2){
//                    PEC.setTauxRempoursement(taux1);
//                    lesPEC.add(PEC);
//                }
//                else{
//                    PEC.setAdherentCAS(true);
//                    PEC.setTauxRempoursement(taux1);
//                    PriseEnCharge PEC2=new PriseEnCharge();
//                    PEC2.setAdherentCAS(false);
//                    PEC2.setBaseRemboursement(PEC.getBaseRemboursement());
//                    PEC2.setUnite(PEC.getUnite());
//                    PEC2.setTauxRempoursement(taux2);
//                    PEC2.setLaGarantie(g);
//                    lesPEC.add(PEC);
//                    lesPEC.add(PEC2);
//                }
//                System.out.println(PEC.getLaGarantie().getId());
//            }
        }catch(Exception e){
            Response.add("Erreur : Probleme sur les ids de la page.<br/>"
                    + "Veuillez contacter notre technicien : tec@PFE.fr "+ee);//1
            return Response;
        }
        //si tout c'est bien passé : (ajouter les controles)
        //Vefification jusqu'à ici
        
        
        
        
        Produit prod=produitFacade.creerProduit(NomProduit, prix, lesBeneficiaires, leAssiette, lesTypes, typep, fisca, lesPopulations, persmo,dom);
        System.out.println("ici");
//        for(PriseEnCharge Pec : lesPEC){
//            Pec.setLeProduit(prod);
//            System.out.println("prodsorti");
//            System.out.println(Pec.getLeProduit().getNomProduit());
////            System.out.println(Pec.getLeProduit().getId().toString()+" .  "+Pec.getLaGarantie().getId().toString());
//            //priseEnChargeFacade.Enregistrer(Pec);
//            System.out.println("good");
//        }
        Response.add("Produit créée");//1
        Response.add("/MenuGestionnaire.jsp"); //2 
//        Response.add(prod);//3 le produit créé
        
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
             Response.add("Erreur : Il manque des champs");//1
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
            Response.add("Erreur : Erreur Date");//1
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
            Response.add("Erreur : Probleme sur la population");//1
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
                Response.add("Erreur : Il manque des champs");//1
                Response.add("/CreationDevis.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
                Response.add(pers);//3 la personne qui crée le devis
                Response.add(listeinfos);//4 les ayant drois (nom, prenom, datenaiss, population)
                Response.add(null); //5 pas de packs (produit+prix)
             
                return Response; //tout ce qu'on a donné
            }
            
            DateN=java.sql.Date.valueOf((String)(Array.get(infos, 2)));
            if(DateN==null||DateN.after(new Date())){
                 Response.add("Erreur : Erreur Date");//1
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
        
        
        Response.add(""); // 1
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
    public List<Object> DevisAvecRecherchePersonne(Gestionnaire gest,String SS) {
        List<Object> Response=new ArrayList();
        /*long idpers;
        try{
            idpers=(long)Integer.parseInt(idp);
        }catch(NumberFormatException e){
            Response.add("Erreur de personne choisie");//1
            Response.add("/GestionnaireAfficherAffilie.jsp"); //2 JSP creation de devis avec liste object Ayants droits + infos personne (nom, prenom, mail,numero SS population)
            Response.add(null);
            Response.add(new ArrayList());
            return Response;
        }*/
        
        //Object[] pers = null;
        List<Object[]>listeinfos = new ArrayList();
        //cette methode consiste a creer les object pour envoyer dans la JSP creation de devis
        
        //on recuperer la personne
        PersonnePhysique personne=personnePhysiqueFacade.recherchePersNumeroSS(SS);
        System.out.println(personne==null);
        List<PersonnePhysique>Ayantsdroit=new ArrayList();
        Format formatter = new SimpleDateFormat("yyyy-MM-dd"); //j'ai trouver que ca pour Date To String
        Object[] pers={personne.getNom(), personne.getPrenom(), formatter.format(personne.getDateNaiss()), personne.getNumeroSS(), personne.getMail(), personne.getLaPopulation().getId()};
        Domaine dom = gest.getLeDomaine();
        
        //on recupere les ayants droits de son contrat du domaine concerné
        StatutBeneficiaire lestatutaffilie = statutBeneficiaireFacade.rechercheAffilieDomaine(personne, dom);
    
        List<StatutBeneficiaire>lespers = lestatutaffilie.getLeContrat().getLesStatutsBeneficiaire();
        System.out.println("mark");
        
        /*List<StatutBeneficiaire>statutsayants=new ArrayList();
        
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
        }*/
        
        for (StatutBeneficiaire stpers : lespers){
            if(stpers.getLaBeneficiaire().getLibelleBeneficiaire().equals("Affilie")){
                PersonnePhysique ayt=stpers.getLaPersonnePhysique();
                Object[] a={ayt.getNom(),ayt.getPrenom(),formatter.format(ayt.getDateNaiss()),ayt.getNumeroSS()};
                listeinfos.add(a);
            }
        }
        
        //Objet pers : 1 nom, 2 prenom, 3 datenaiss, 4 numero SS ,5 mail,6 Population (String de ID)
        /*Array.set(pers, 0, personne.getNom());
        Array.set(pers, 1, personne.getPrenom());
        
        String datenaiss  = formatter.format(personne.getDateNaiss());

        Array.set(pers, 2, datenaiss); 
        Array.set(pers, 3, personne.getNumeroSS());
        Array.set(pers, 4, personne.getMail());
        Array.set(pers, 4, personne.getLaPopulation().getId());*/
        
       //ayants droits  : List Object listeinfos
       //1 Nom ; 2Prenom ; 3datenaiss ; 4numeroSS ;
       
        /*for (PersonnePhysique ayt : Ayantsdroit) {
             Object[]Ayantdroitobj=null;
             Array.set(Ayantdroitobj, 0, ayt.getNom());
             Array.set(Ayantdroitobj, 1, ayt.getPrenom());
             Array.set(Ayantdroitobj, 2, ayt.getDateNaiss());
             Array.set(Ayantdroitobj, 0, ayt.getNumeroSS());
           
             listeinfos.add(Ayantdroitobj);
        }  */     
       
        Response.add("Recherche terminée");//1
        Response.add("/PageCreationDevis.jsp"); //2 JSP creation de devis avec liste object Ayants droits + infos personne (nom, prenom, mail,numero SS population)
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
    public List<Acte> rechercheListeActesNonRembourse(int Page, String ReSS) {
        List<Acte> listact = acteFacade.rechercheActesNonRembourse(Page, ReSS);
         
        return listact;
    }
    
    
    
    
        @Override
    public List<Object> creerRemboursement(String ida) {
        List<Object> Response=new ArrayList();
        long idact;
        try{
            idact=(long)Integer.parseInt(ida);
        }catch(NumberFormatException e){
            Response.add("Erreur : Echeque à créer un remboursement !!!2");
            return Response;
        }
        Acte act=acteFacade.rechercheActeID(idact);
        if(act==null){
            Response.add("Erreur : Echeque à créer un remboursement !!!3");
            return Response;
        }
        Boolean practiCAS =act.getLePraticien().isAdherentCAS();
        System.out.println(act.getId()+"practiCAS "+practiCAS);
        PlafondMensuelSecuSociale pmss=act.getLePlafond();
        LibelleActe l = act.getLeLibelleActe();
        Garantie g = l.getLaGarantie();
        PersonnePhysique pp = act.getLaPersonnePhysique();
        
        
       List<TypeGarantie> typegli=g.getLesTypesGarantie(); //--> les modules concerné par l'acte en question
       
       //on recupere le contrat
       List<Contrat> contrats=statutBeneficiaireFacade.rechercheContratsAffilie(pp);
       //filtre les contrats sur Domaine de la sante
       List<Contrat>contratssante=new ArrayList();
       System.out.println("nombre contrats contres"+contrats.size());
      for (Contrat ct : contrats){
          if(ct.getLeProduit().getLeDomaine().getLibelleDomaine().equalsIgnoreCase("Santé")){
              contratssante.add(ct);
          }
          
      }
      //on recuperer contrat individuel ou contrat collectif, si presence de complementaire on recuperer complementaire
      Contrat lecontrat=null;
        System.out.println("nombre contratssante contres"+contratssante.size());

      if(contratssante.size()==1){
        lecontrat=contratssante.get(0);
      }
      else{
          for (Contrat ct : contratssante){
          Produit leprod = ct.getLeProduit();
          TypeProduit letype=leprod.getLeTypeProduit();
           System.out.println(letype.getLibelleTypeProduit()+"nanana");
            if(letype.getLibelleTypeProduit().equalsIgnoreCase("Surcomplémentaire")){
                lecontrat=ct;
            }    
          }             
       }
        if(lecontrat==null){
            Response.add("Erreur : Pas possible de créer remboursement");
            return Response;
        }
        System.out.println("contrat id "+lecontrat.getId());
       //on compare les garanties couvertes par le produit, et dans quel module se trouve l'acte pour
       // savoir si on rembourse ou non 
        Produit prod=lecontrat.getLeProduit();
        List<TypeGarantie>typeglicouverte=prod.getLesTypesGarantie();
        //typegli list typegarantie = les garanties couverte par le produit
      
        boolean couvert=false;
        String lenom;
        for(TypeGarantie typ : typegli){
            System.out.println("search");
            lenom=typ.getTypeGarantie();
            if(typeglicouverte.contains(typ)){
                couvert=true;
                System.out.println("good");
                break;
            }
            /*for (TypeGarantie typacheck:typeglicouverte){
                if(lenom==typacheck.getTypeGarantie()){
                    couvert=true;
                    break;
                }
                  
            }   */
              
        }
    
      
        if (couvert==false){ //garantie pas couverte dans le produit
            Remboursement rembour=remboursementFacade.creerRemboursement(0,EtatRemboursement.NonRembourse,act);
            //creerRemboursement avec 0
        }
        List<PriseEnCharge> lesprisesench=lecontrat.getLeProduit().getLesPriseEnCharges();
        
        List<PriseEnCharge> lesprisesenchgar=new ArrayList();
        
        for(PriseEnCharge prise : lesprisesench){
        if(prise.getLaGarantie()==g){
            lesprisesenchgar.add(prise);
        }
         }
      
        //si le practitien est adherentCAS on recupere la prise en charge adhérent et inversement(qui contient les pourcentages de remboursement)
        PriseEnCharge p=null;
        System.out.println(lesprisesenchgar.size());
        if(lesprisesenchgar.size()==1) p=lesprisesenchgar.get(0);
        else for (PriseEnCharge priseen : lesprisesenchgar){
            System.out.println("pec id "+ priseen.getId());
            boolean pb;
            try{
                pb=priseen.isAdherentCAS();
                //System.out.println("pb"+pb);
            }catch(Exception e){
                //System.out.println("pbnull");
                continue;
            }
            if (pb==practiCAS){
                System.out.println("practiCAS"+practiCAS);
                System.out.println("ok"+priseen.getId());
                p=priseen;  
            }
            else{
                System.out.println("practiCAS"+practiCAS);
                System.out.println("xx"+priseen.getId());
            }
        }
        if(p==null){
            Response.add("Erreur : Echeque à créer un remboursement !!!1"); // 1
            return Response;
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
        Response.add(rembour);//3 le remboursement créé

        return Response;
    }

    @Override
    public List<Object> validerRemboursement(String idrv) {
        Long idremb;
        List<Object> Response=new ArrayList();
        try{
            idremb=(long)Integer.parseInt(idrv);
        }catch(Exception e){
            Response.add("Erreur : Remboursement non trouvé");
            return Response;
        }
        
        Remboursement remb = remboursementFacade.rechercheExistantID(idremb);
        
        remb = remboursementFacade.validerRemboursement(remb);
        
        Response.add("Remboursement validé "); // 1
        Response.add(remb);//le remboursement validé
        
        return Response;
    }

    @Override
    public List<Object> refuserRemboursement(String idrr) {
        Long idremb;
        List<Object> Response=new ArrayList();
        try{
            idremb=(long)Integer.parseInt(idrr);
        }catch(Exception e){
            Response.add("Erreur : Remboursement non trouvé");
            return Response;
        }
        
        Remboursement remb = remboursementFacade.rechercheExistantID(idremb);
        
        remb = remboursementFacade.refuserRemboursement(remb);
        
        Response.add("Remboursement refusé "); // 1
        Response.add(remb);//le remboursement refusé
        
        return Response;
    }

    @Override
    public List<Object> cloturerContrat(String idc) {
        List<Object> Response=new ArrayList();
        long idcontrat;
        try{
            idcontrat=(long)Integer.parseInt(idc);
        }catch(NumberFormatException e){
            Response.add("Erreur : Prodblème de contrat choisi");
            Response.add(null);
            return Response;
        }
        Contrat ct = contratFacade.rechercheExistantID(idcontrat);
        
        ct=contratFacade.cloturerContrat(ct);
        
        
        Response.add("Contrat clos "); // 1
        Response.add("/MenuGestionnaire.jsp"); // 2 Jsp pour afficher 
        Response.add(ct);
        
        return Response;
    }

    
    @Override
    public List<Object> validerContrat(String idc) {
        
        List<Object> Response=new ArrayList();
        long idcontrat;
        try{
            idcontrat=(long)Integer.parseInt(idc);
        }catch(NumberFormatException e){
            Response.add("Erreur : Prodblème de contrat choisi");
            Response.add(null);
            return Response;
        }
        Contrat ct = contratFacade.rechercheExistantID(idcontrat);
        
        ct = contratFacade.ValiderContrat(ct);
        
        
        Response.add("Contrat validé "); // 1
        Response.add(ct);
        
        return Response;
    }

    @Override
    public List<Object> refuserContrat(String idc) {
        List<Object> Response=new ArrayList();
        long idcontrat;
        try{
            idcontrat=(long)Integer.parseInt(idc);
        }catch(NumberFormatException e){
            Response.add("Erreur : Prodblème de contrat choisi");
            Response.add(null);
            return Response;
        }
        Contrat ct = contratFacade.rechercheExistantID(idcontrat);
        
        ct=contratFacade.RefuserContrat(ct);
        
        
        Response.add("Contrat refusé "); // 1
        Response.add(ct);
        
        return Response;
    }
    
    
    @Override
    public List<Remboursement> afficherRempoursementEncours() {
        return remboursementFacade.afficherRempoursementEncours();
    }
      
    
    
    
    
    
    @Override
    public void creerActivite(String n){
        System.out.println("creation session");
        activiteFacade.creerActivite(n);
        System.out.println("creation session sort");
    }
    
    
    
    //Suite de methode pour ajouter des personnes a un produit et ainsi creer son contrat
    
    
    @Override
    public List<Produit> rechercheProduitsCollectif(int page, String RePr) {
        return produitFacade.afficherProduitCollectif(page,RePr);
    }
    
    @Override
    public List<Object> rechercheProduitsCollectifID(String idprod){
        List<Object> Response=new ArrayList();
        long idp;
        System.out.println(idprod+"idprodddd");
        try{idp=(long)Integer.parseInt(idprod);}catch(NumberFormatException e){
            Response.add("Erreur : id produit erreur");
            Response.add("/GestionnaireProduitCollectif.jsp");
            Response.add("");
            return Response;
        }
        Produit prod=produitFacade.rechercheProduitCollectifID(idp);
        if(prod==null){
            Response.add("Erreur : Produit n'existe pas");
            Response.add("/GestionnaireProduitCollectif.jsp");
            Response.add("");
            return Response;
        }else{
            Response.add("Remplir les informations");
            Response.add("/GestionnaireInformationContratCollectif.jsp");
            Response.add(idprod);
            return Response;
        }
    }
    
    @Override
    public long CompterProduitCollectif(String RePr){
        return produitFacade.CompterProduitCollectif(RePr);
    }
    
    @Override
    public List<Object> ajouterPersonneProduitCollectif(String idp,Object[] pers,List<Object[]>listeinfos) {
        List<Object> Response=new ArrayList();
        
        //dans cette methode : on recupere : 
        //l'id du produit (collectif) auquel on veux ajouter une personne et creer un contrat
        //Objet pers : 0genre (String) 1 nom, 2 prenom, 3 datenaiss, 4 numero SS , 5adresse, 6 mail,7 Population (String de ID)
                        //login,mdp generés ?
                        
                        // pour chaque ayant droit : 0 genre,1 nom,2 prenom,3 datenaiss,4 numeross,5 adresse
                        //7 population, 8 ID (Conjoint, Concubin, Enfant à charge)
        long idproduit;
        try{
            idproduit=(long)Integer.parseInt(idp);
        }catch(Exception e){
            Response.add("Erreur : Probleme sur la creation (id du produit)");//1
            Response.add("/menuGestionnaire.jsp"); 
            Response.add(null);
            Response.add(null);
             
            return Response;
        }
        Produit prod=produitFacade.rechercheProduitCollectifID(idproduit);
        
         if (prod==null){
             Response.add("Erreur : Probleme sur la creation (choix du produit)");//1
             Response.add("/GestionnaireInformationContratCollectif.jsp"); 
             Response.add(null);
             Response.add(null);
             
            return Response; //probleme transition ID Produit
         }
        
        //controle sur les champs remplis : personnephy    
       // 0 genre (String) 1 nom, 2 prenom, 3 datenaiss, 4 numero SS , 5adresse, 6 mail,7 Population (String de ID)
        if(((String)Array.get(pers, 0)).equals("")||((String)Array.get(pers, 1)).equals("")||((String)Array.get(pers, 2)).equals("")||((String)Array.get(pers, 3)).equals("")||((String)Array.get(pers, 4)).equals("")||((String)Array.get(pers, 5)).equals("")||((String)Array.get(pers, 6)).equals("")||((String)Array.get(pers, 7)).equals("")){
             Response.add("Erreur : Merci de remplir les champs obligatoires");//1
             Response.add("/GestionnaireInformationContratCollectif.jsp"); //2 JSP AjouterPersonneProduit
             Response.add(pers);//3 la personne qui crée le devis (0genre (String) 1 nom, 2 prenom, 3 datenaiss, 4 numero SS , 5adresse, 6 mail,7 Population (String de ID))
             Response.add(listeinfos);//4 les ayant droits
             
            return Response; //manque des champs donc renvoi de toutes les informations
       }
        
         //controle les champs remplis : ayants droits
         // pour chaque ayant droit : 0 genre,1 nom,2 prenom,3 datenaiss,4 numeross,5 adresse
          //6 population, 7 STRING (Conjoint, Concubin, Enfant à charge)
        
          
        List<String> verifi=new ArrayList();
        verifi.add(((String)Array.get(pers, 4)).toUpperCase());
        List<PersonnePhysique> LesAyantdroit=new ArrayList();
        
           
        for(Object[] infos: listeinfos){
            String genre=(String)Array.get(infos, 0);
            String nom=(String)Array.get(infos, 1);
            String prenom=(String)Array.get(infos, 2);
            //String 
            String datenaiss=(String)Array.get(infos, 3);
            String numeroSS =(String)Array.get(infos,4);
            String adresse = (String)Array.get(infos,5);
            String idpopst = (String)Array.get(infos,6);
            String statutayt=(String)Array.get(infos,7);
            
            if(genre.trim().equals("")||nom.trim().equals("")||prenom.trim().equals("")||adresse.trim().equals("")||numeroSS.trim().equals("")||datenaiss.trim().equals("")||idpopst.trim().equals("")||statutayt.trim().equals("")){
                 Response.add("Erreur : Merci de remplir les champs obligatoires");//1
                 Response.add("/GestionnaireInformationContratCollectif.jsp"); //2 JSP renseignements complementaire
                 Response.add(pers);
                 Response.add(listeinfos);
             
                return Response; //manque des champs donc renvoi de toutes les informations
        
           } else{
                if(numeroSS.equalsIgnoreCase((String)Array.get(pers, 4))||verifi.contains(numeroSS.toUpperCase())){
                    Response.add("Erreur : Numéro SS répété");//1
                    Response.add("/GestionnaireInformationContratCollectif.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
                    Response.add(null); //5 pas de packs (produit+prix)

                    return Response; //tout ce qu'on a donné
                }else{
                    verifi.add(numeroSS.toUpperCase());
                }//on créer les ayants droits si ils existent
                Date daten;
                try{
                    daten=java.sql.Date.valueOf((String)Array.get(infos, 3));
                }catch(ArrayIndexOutOfBoundsException | IllegalArgumentException e){
                    Response.add("Erreur : Date Naissance Erreur");//1
                    Response.add("/GestionnaireInformationContratCollectif.jsp"); //2 JSP renseignements complementaire
                    Response.add(pers);
                    Response.add(listeinfos);
                }
                PersonnePhysique ayantdroitencours;
                ayantdroitencours=personnePhysiqueFacade.recherchePersNumeroSS(numeroSS);
                if (ayantdroitencours==null){
                        Long idpop=Long.valueOf(idpopst);
                        Population pop = populationFacade.rechercheExistantPopulationID(idpop);
                        Genre genreayt=Genre.valueOf(genre);
                        ayantdroitencours=personnePhysiqueFacade.creerPersonneComplete(nom, prenom, genreayt, java.sql.Date.valueOf((String)Array.get(infos, 3)), numeroSS, null, pop, adresse); 
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
                
                Genre genrepers=Genre.valueOf((String)Array.get(pers, 0));

                persencours=personnePhysiqueFacade.creerPersonneComplete(((String)Array.get(pers, 1)), ((String)Array.get(pers, 2)), genrepers,datenaisspers , ((String)Array.get(pers, 4)), ((String)Array.get(pers, 6)), pop, ((String)Array.get(pers, 5)));
                
                //generation login + mdp
                //login = Nom+2lettres du prenom, si pas disponible ajout d'un chiffre en 1 et 9 aléatoire et check a nouveau la dispo
                boolean dispologin=false;
                String login;
                if(((String)Array.get(pers, 1)).length()<2)login=((String)Array.get(pers, 1))+((String)Array.get(pers, 1))+"*";
                else login=((String)Array.get(pers, 1))+(((String)Array.get(pers, 1)).substring(0, 2));
                

                while(dispologin==false){
               
                  if(personnePhysiqueFacade.rechercheDispoLogin(login)==false){
                      Random rand = new Random(); int nombreAleatoire = rand.nextInt(9 - 1 + 1) + 1;
                    login=login+String.valueOf(nombreAleatoire);                   
                   } else {
                      dispologin=true;
                  }
                }    
                    
                String code;
                if(((String)Array.get(pers, 4)).length()<12)code=((String)Array.get(pers, 4)+"************").substring(5, 7);
                else code=((String)Array.get(pers, 4)).substring(5, 7);
                String mdp=(String.valueOf(datenaisspers.hashCode())+String.valueOf(code.hashCode()));
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
                System.out.println("fin"+numeroSS);
            ayantdroitencours=personnePhysiqueFacade.recherchePersNumeroSS(numeroSS);  
                System.out.println("fin");
            
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
        contratFacade.Enregistrer(ct);
   
        Response.add("Personne ajoutée, contrat créé");//1
        Response.add("/GestionnaireInformationContratCollectif.jsp"); //2 JSP 
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
    public List<Contrat> AfficherContratGestionnaire(Domaine dom, String ReSS, int page){
        return statutBeneficiaireFacade.AfficherContratGestionnaire(dom, ReSS, page);
    }
    
    @Override
    public List<Contrat> AfficherContratCree(Domaine dom, String ReSS, int page){
        return statutBeneficiaireFacade.AfficherContratCree(dom, ReSS, page);
    }
    
    @Override
    public List<Contrat> AfficherContratValide(Domaine dom, String ReSS, int page){
        return statutBeneficiaireFacade.AfficherContratValide(dom, ReSS, page);
    }
    
    @Override
    public long CompterContratGestionnaire(Domaine dom, String ReSS){
        return statutBeneficiaireFacade.CompterContratGestionnaire(dom, ReSS);
    }
    
    @Override
    public long CompterContratCree(Domaine dom, String Ress){
        return statutBeneficiaireFacade.CompterContratCree(dom, Ress);
    }
    
    @Override
    public long CompterContratValide(Domaine dom, String ReSS){
        return statutBeneficiaireFacade.CompterContratValide(dom, ReSS);
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
    @Override
    public List<PersonnePhysique> AfficherPersonnesPhysiques(String SS, int page){
        return statutBeneficiaireFacade.AfficherPersonnesPhysiques(SS, page);
    }
    
    @Override
    public long CompterPersonnesPhysiques(String ReSS){
        return statutBeneficiaireFacade.CompterPersonnesPhysiques(ReSS);
    }
    
    @Override
    public List<Domaine> AfficherDomaine(){
        return domaineFacade.findAll();
    }
    
    @Override
    public List<TypeProduit> AfficherTypeProduit(){
        return typeProduitFacade.findAll();
    }
    
    @Override
    public List<Fiscalite> AfficherFiscalite(){
        return fiscaliteFacade.findAll();
    }
    
    @Override
    public List<TypeGarantie> AfficherTypeGarantie(){
        return typeGarantieFacade.findAll();
    }
    
    @Override
    public long CompterActesNonRembourse(String ReSS){
        return acteFacade.CompterActesNonRembourse(ReSS);
    }
    
    @Override
    public List<Garantie> AfficherGarantie(){
        return garantieFacade.findAll();
    }
    
}