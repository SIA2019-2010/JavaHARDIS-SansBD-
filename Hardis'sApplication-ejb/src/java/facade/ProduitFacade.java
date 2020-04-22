/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.Produit;
import entitee.Beneficiaire;
import entitee.Domaine;
import entitee.Fiscalite;
import entitee.PersonneMorale;
import entitee.TypeGarantie;
import entitee.TypeProduit;
import entitee.Population;
import entitee.PriseEnCharge;
import java.util.ArrayList;
import java.util.List;
import java.util.EnumSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author lixin
 */
@Stateless
public class ProduitFacade extends AbstractFacade<Produit> implements ProduitFacadeLocal {

    @PersistenceContext(unitName = "Hardis_sApplication-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProduitFacade() {
        super(Produit.class);
    }
    
    @Override
    public Produit creerProduit(String nom, double prix, List<Beneficiaire> lesBeneficiaires, Beneficiaire assiette, List<TypeGarantie> lesTypesGaranties, TypeProduit leTypeProduit, Fiscalite laFiscalite,List<Population> lesPopulations, PersonneMorale laPersonneMorale,Domaine leDomaine) {
        em.flush();
        Produit prod = new Produit();

        prod.setNomProduit(nom);
        prod.setLeTypeProduit(leTypeProduit);
        prod.setLaBeneficiaire(assiette);
        prod.setLaFiscalite(laFiscalite);
        prod.setLaPersonneMorale(laPersonneMorale);
        prod.setLeDomaine(leDomaine);
        System.out.println((prod==null) +"null prod");
        System.out.println((prod.getId()==null) +"null prod");
//        prod.setLesBeneficiaires(new ArrayList());
        
        prod.setLesPopulations(new ArrayList());
        for(Population p:lesPopulations){
            prod.getLesPopulations().add(p);
        }
        prod.setLesTypesGarantie(new ArrayList());
        for(TypeGarantie tg:lesTypesGaranties){
            prod.getLesTypesGarantie().add(tg);
        }
        em.persist(prod);
        System.out.println((prod.getId()==null) +"null iddddd");
        for(Beneficiaire b:lesBeneficiaires){
//            prod.getLesBeneficiaires().add(b);
            System.out.println("add");
            
        }
        return prod;
    }
    
    @Override
    public boolean rechererProduitNom(String Nom){
        List<Produit> listesProduits;
        String tx = "SELECT p FROM Produit AS p where p.NomProduit=:nom"; 
        Query req = getEntityManager().createQuery(tx); 
        req.setParameter("nom", Nom); 
        listesProduits= req.getResultList ();
        return !listesProduits.isEmpty();
    }
    
    @Override
    public List<Produit> afficherPersonneMoraleProduit(PersonneMorale personne){
        List<Produit> listesProduits;
        String tx = "SELECT p FROM Produit AS p where p.laPersonneMorale=:pers"; 
        Query req = getEntityManager().createQuery(tx); 
        req.setParameter("pers", personne); 
        listesProduits= req.getResultList ();
        return listesProduits;
    }

    @Override
    public Produit rechercheProduitCollectifID(long idp) {
        Produit prod;
        String txt="SELECT pr FROM Produit AS pr "
                + "WHERE pr.id=:id "
                + "and pr.laPersonneMorale IS NOT NULL ";
        Query req=getEntityManager().createQuery(txt);
        req=req.setParameter("id",idp);
        prod=null;
        List <Produit> result = req.getResultList();
        if (result.size()==1)
            {prod=(Produit)result.get(0);};
        return prod;
    }
    
    @Override
    public List<Produit> afficherProduit(){
        Produit prod;
        String txt="SELECT pr FROM Produit AS pr";
        Query req=getEntityManager().createQuery(txt);
        //req=req.setParameter("id",idp);
        prod=null;
        List <Produit> result = req.getResultList();
        return result;
    }

    @Override
    public List<Produit> afficherProduitCollectif(int page, String RePr) {
        RePr="%"+RePr+"%";
        String txt="SELECT pr FROM Produit AS pr "
                + "where pr.laPersonneMorale IS NOT NULL "
                + "and pr.NomProduit like :RePr ";
        Query req=getEntityManager().createQuery(txt);
        req.setParameter("RePr", RePr); 
        req.setFirstResult(20*(page-1));
        req.setMaxResults(20);
        List <Produit> result = req.getResultList();
        return result;
    }
    
    @Override
    public long CompterProduitCollectif(String RePr) {
        long taille;
        RePr="%"+RePr+"%";
        String txt="SELECT count(pr) FROM Produit AS pr "
                + "where pr.laPersonneMorale IS NOT NULL "
                + "and pr.NomProduit like :RePr ";
        Query req=getEntityManager().createQuery(txt);
        req.setParameter("RePr", RePr); 
        taille= (long)req.getResultList().get(0);
        return taille;
    }
    
    @Override
    public List<Produit> afficherProduitIndividuel() {
        Produit prod;
        String txt="SELECT pr FROM Produit AS pr where pr.laPersonneMorale IS NULL";
        Query req=getEntityManager().createQuery(txt);
        prod=null;
        List <Produit> result = req.getResultList();
        return result;
    }
    
    
    
}
