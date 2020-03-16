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
import entitee.Population;
import entitee.Produit;
import entitee.Responsable;
import entitee.TypeGarantie;
import entitee.TypeProduit;
import facade.ActiviteFacadeLocal;
import facade.GestionnaireFacadeLocal;
import facade.PersonneMoraleFacadeLocal;
import facade.ProduitFacadeLocal;
import facade.ResponsableFacadeLocal;
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
    public List<Object> creerMoraleComplet(Object[] pers) {
        List<Object> Response=new ArrayList();
       //Objet pers : 1 SIRET, 2 raisonSociale, 3 adresse, 4 activite (String de ID)
   
       
       if(((String)Array.get(pers, 0)).equals("")||((String)Array.get(pers, 1)).equals("")||((String)Array.get(pers, 2)).equals("")||((String)Array.get(pers, 3)).equals("")){
             Response.add("Merci de remplir la totalité des champs");//1
             Response.add("/CreationMorale.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
             Response.add(pers);//3 les infos deja données
             
            return Response; //manque des champs donc renvoi de toutes les informations
       }
       //controle sur l'activite
       Long idact=Long.valueOf((String)Array.get(pers, 3));
       Activite act = activiteFacade.rechercheActiviteExistantID(idact);
       if(act==null){
             Response.add("Probleme sur la population");//1
             Response.add("/CreationDevis.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
             Response.add(pers);//3 les infos deja données
             
            return Response; //Population introuvable
       }
        
        //si tout est bien rempli : 
        
        PersonneMorale persmo;
        persmo=creerMorale(((String)Array.get(pers, 0)),((String)Array.get(pers, 1)),((String)Array.get(pers, 2)),act);
        
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
    public List<Object> creerResponsableComplet(Object[] pers) {
        List<Object> Response=new ArrayList();
       //Objet pers : 1 nom, 2 prenom, 3 mail, 4 tel,5 Login,6 Mdp,7 PersonneMorale (String de ID)
        
        if(((String)Array.get(pers, 0)).equals("")||((String)Array.get(pers, 1)).equals("")||((String)Array.get(pers, 2)).equals("")||((String)Array.get(pers, 3)).equals("")||((String)Array.get(pers, 4)).equals("")||((String)Array.get(pers, 5)).equals("")||((String)Array.get(pers, 6)).equals("")){
             Response.add("Merci de remplir la totalité des champs");//1
             Response.add("/CreationResponsable.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
             Response.add(pers);//3 les infos deja données
             
            return Response; //manque des champs donc renvoi de toutes les informations
       }
        
        //controle sur la personne morale
       Long idpersmo=Long.valueOf((String)Array.get(pers, 6));
       PersonneMorale persmo = personneMoraleFacade.rechercheExistantID(idpersmo);
       if(persmo==null){
             Response.add("Probleme sur la personneMorale");//1
             Response.add("/CreationResponsable.jsp"); //2 JSP creation de devis avec liste object + infos personne (nom, prenom, mail, population)
             Response.add(pers);//3 les infos deja données
             
            return Response; //PersonneMorale introuvable
       }
       
       
       Responsable resp;
       resp=creerResponsable(((String)Array.get(pers, 0)),((String)Array.get(pers, 1)),((String)Array.get(pers, 2)),((String)Array.get(pers, 3)),((String)Array.get(pers, 4)),((String)Array.get(pers, 5)),persmo);
       
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
    public List<Object> creerProduitComplet(String nom, EnumSet<Beneficiaire> lesbenef, EnumSet<Beneficiaire> lesassiettes, List<TypeGarantie> lestypesgarantie, TypeProduit letype, List<Fiscalite> lesfisca,PersonneMorale lapers) {
        
        
        return null;
    }
    
    
    
    
    
    
    
    
    
}
