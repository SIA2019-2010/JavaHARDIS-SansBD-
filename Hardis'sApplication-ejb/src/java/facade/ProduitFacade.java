/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.Produit;
import entitee.Beneficiaire;
import entitee.Fiscalite;
import entitee.PersonneMorale;
import entitee.TypeGarantie;
import entitee.TypeProduit;
import entitee.Population;
import java.util.List;
import java.util.EnumSet;
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
    public Produit creerProduit(String nom, EnumSet<Beneficiaire> lesBeneficiaires, EnumSet<Beneficiaire> assiettes, List<TypeGarantie> lesTypesGaranties, TypeProduit leTypeProduit, List<Fiscalite> lesFiscalites,List<Population> lesPopulations, PersonneMorale laPersonneMorale) {
        Produit prod = new Produit();
        
        prod.setNomProduit(nom);
        prod.setBeneficiaires(lesBeneficiaires);
        prod.setLeTypeProduit(leTypeProduit);
        prod.setAssiettesCotisation(assiettes);
        prod.setLesFiscalites(lesFiscalites);
        prod.setLesPopulations(lesPopulations);
        prod.setLesTypesGarantie(lesTypesGaranties);        
        prod.setLaPersonneMorale(laPersonneMorale);

        
        em.persist(prod);
        return prod;
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
    public Produit rechercheProduitID(long idp) {
        Produit prod;
        String txt="SELECT pr FROM Produit AS pr WHERE pr.Id=:id";
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
    
    
    
    
    
}
