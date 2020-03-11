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
import java.util.ArrayList;
import java.util.EnumSet;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    public Produit creerProduit(String nom, EnumSet lesBeneficiaires, ArrayList<AssietteCotisation> lesAssiettes, ArrayList<TypeGarantie> lesTypesGaranties, TypeProduit leTypeProduit, ArrayList<Fiscalite> lesFiscalites,ArrayList<Population> lesPopulations, PersonneMorale laPersonneMorale) {
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
    
}
