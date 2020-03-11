/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.Produit;
import entitee.AssietteCotisation;
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
    public Produit creerProduit(String nom, EnumSet lesBeneficiaires, List<AssietteCotisation> lesAssiettes, List<TypeGarantie> lesTypesGaranties, TypeProduit leTypeProduit, List<Fiscalite> lesFiscalites,List<Population> lesPopulations, PersonneMorale laPersonneMorale) {
        Produit prod = new Produit();
        
        prod.setNomProduit(nom);
        prod.setBeneficiaires(lesBeneficiaires);
        prod.setLeTypeProduit(leTypeProduit);
        prod.setLesAssiettesCotisation(lesAssiettes);
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
    
}
