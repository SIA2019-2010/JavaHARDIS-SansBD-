/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entitee.Activite;
import entitee.Beneficiaire;
import entitee.Fiscalite;
import entitee.Gestionnaire;
import entitee.PersonneMorale;
import entitee.PersonnePhysique;
import entitee.Population;
import entitee.Produit;
import entitee.Responsable;
import entitee.TypeGarantie;
import entitee.TypeProduit;
import facade.ActiviteFacadeLocal;
import facade.FiscaliteFacadeLocal;
import facade.GarantieFacadeLocal;
import facade.GestionnaireFacadeLocal;
import facade.PersonneMoraleFacadeLocal;
import facade.PopulationFacadeLocal;
import facade.ProduitFacadeLocal;
import facade.ResponsableFacadeLocal;
import facade.TypeGarantieFacadeLocal;
import facade.TypeProduitFacadeLocal;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
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
    
    
    
    @Override
    public List<Object> authentificationGestionnaire(String login, String mdp, HttpServletRequest request) {
        System.out.println("authenRespon"+login+"   "+mdp);
        List<Object> Response=new ArrayList();
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
             Response.add(pers);//3 les infos deja données
             
            return Response; //manque des champs donc renvoi de toutes les informations
       }
       //controle sur l'activite
       Long idact=Long.valueOf(pers.get(3));
       Activite act = activiteFacade.rechercheActiviteExistantID(idact);
       if(act==null){
             Response.add("Probleme sur la population");//1
             Response.add("/CreationDevis.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
             Response.add(pers);//3 les infos deja données
             
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
       //Objet pers : 1 nom, 2 prenom, 3 mail, 4 tel,5 Login,6 Mdp,7 PersonneMorale (String de ID)
        
        if(pers.get(0).isEmpty()||pers.get(1).isEmpty()||pers.get(2).isEmpty()||pers.get(3).isEmpty()||pers.get(4).isEmpty()||pers.get(5).isEmpty()||pers.get(6).isEmpty()){
             Response.add("Merci de remplir la totalité des champs");//1
             Response.add("/CreationResponsable.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
             Response.add(pers);//3 les infos deja données
             
            return Response; //manque des champs donc renvoi de toutes les informations
       }
        
        //controle sur la personne morale
       Long idpersmo=Long.valueOf(pers.get(6));
       PersonneMorale persmo = personneMoraleFacade.rechercheExistantID(idpersmo);
       if(persmo==null){
             Response.add("Probleme sur la personneMorale");//1
             Response.add("/CreationResponsable.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
             Response.add(pers);//3 les infos deja données
             
            return Response; //PersonneMorale introuvable
       }
       
       
       Responsable resp;
       resp=creerResponsable(pers.get(0),pers.get(1),pers.get(2),pers.get(3),pers.get(4),pers.get(5),persmo);
       
        Response.add("Personne Morale créée");//1
        Response.add("/MenuGestionnaire.jsp"); //2 
        Response.add(persmo);//3 la personne morale créée
        
        return Response;
    }

    @Override
    public Produit creerProduit(String nom, EnumSet<Beneficiaire> lesBeneficiaires, EnumSet<Beneficiaire> lesAssiettes, List<TypeGarantie> lesTypesGaranties, TypeProduit leTypeProduit, List<Fiscalite> lesFiscalites,List<Population> lesPopulations, PersonneMorale laPersonneMorale) {
        return produitFacade.creerProduit(nom, lesBeneficiaires, lesAssiettes, lesTypesGaranties, leTypeProduit, lesFiscalites, lesPopulations, laPersonneMorale);
    }

    @Override
    public List<Object> creerProduitComplet(List<String> infos,List<String> lesbenefs,List<String> lesassiettes,List<Long> lestypes,List<Long> lesfiscas,List<Long> lespops) {
        List<Object> Response=new ArrayList();
        //infos : 1 String nom, 2 ID leTypeProduit, 3 ID laPersonneMorale
        //liste :  ENUMSET : lesBeneficiaires, 3 ENUMSET : lesAssiettes, 4 LIST lesTypesGaranties,
        //liste :  6 lesFiscalites, 7 lesPopulations, 
        EnumSet<Beneficiaire> lesBeneficiaires = null;
        EnumSet<Beneficiaire> lesAssiettes = null;
        List<TypeGarantie> lesTypes=new ArrayList();
        List<Fiscalite> lesFiscalites=new ArrayList();
        List<Population> lesPopulations=new ArrayList();
        
        
        //Controle sur les 3 champs individuel : nom, ID Typeprod, ID Personnemorale
        if(infos.get(0).isEmpty()||infos.get(1).isEmpty()||infos.get(2).isEmpty()){
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
             Response.add("/CreationResponsable.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
             
            return Response; //PersonneMorale introuvable
       }
       
          //controle sur le type produit
       Long idprod=Long.valueOf((String)Array.get(infos, 2));
       TypeProduit typep = typeProduitFacade.rechercheExistantID(idprod);
       if(typep==null){
             Response.add("Probleme sur le Type de produit");//1
             Response.add("/CreationResponsable.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
             
            return Response; //TypeProduit introuvable
       }
       
       
       //Transformation des listes en liste d'objet appropriée:
       
       //EnumSet beneficiaire
        for(String beneficiaire: lesbenefs){
            
            lesBeneficiaires.add(Beneficiaire.valueOf(beneficiaire));
                   
        }
       //enumSet Assiette
        for(String assiette: lesassiettes){
           
            lesAssiettes.add(Beneficiaire.valueOf(assiette));
                   
        }
        
       //liste type
        for(Long type: lestypes){
          TypeGarantie typegar;
           typegar= typeGarantieFacade.rechercheExistantID(type);
            
          lesTypes.add(typegar);
          
        }
        
         //liste fisca
        for(Long fisca: lesfiscas){
          Fiscalite fiscalite;
           fiscalite=fiscaliteFacade.rechercheExistantID(fisca);
     
           lesFiscalites.add(fiscalite);
          
        }
           
         //liste fisca
        for(Long popu: lespops){
          Population population;
           population=populationFacade.rechercheExistantPopulationID(popu);
     
           lesPopulations.add(population);
          
        }
           
        //si tout c'est bien passé : (ajouter les controles)
        
        Produit prod;
        prod=produitFacade.creerProduit(infos.get(0), lesBeneficiaires, lesAssiettes, lesTypes, typep, lesFiscalites, lesPopulations, persmo);
        
        Response.add("Produit créée");//1
        Response.add("/MenuGestionnaire.jsp"); //2 
        Response.add(prod);//3 le produit créé
        
        return Response;
        
    }
    
    
    
    
    
    
    
    
    
}
