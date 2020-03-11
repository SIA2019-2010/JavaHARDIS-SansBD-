/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.Garantie;
import entitee.PriseEnCharge;
import entitee.Produit;
import entitee.Unite;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lixin
 */
@Stateless
public class PriseEnChargeFacade extends AbstractFacade<PriseEnCharge> implements PriseEnChargeFacadeLocal {

    @PersistenceContext(unitName = "Hardis_sApplication-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PriseEnChargeFacade() {
        super(PriseEnCharge.class);
    }
    
    @Override
    public PriseEnCharge creerPriseEnCharge(double tauxremboursement, String baseremboursement, Unite unite, Produit produit, Garantie garantie) {
        PriseEnCharge prisnenchareg=new PriseEnCharge();
        prisnenchareg.setTauxRempoursement(tauxremboursement);
        prisnenchareg.setBaseRemboursement(baseremboursement);
        prisnenchareg.setLeProduit(produit);
        prisnenchareg.setLaGarantie(garantie);
        prisnenchareg.setUnite(unite);
        em.persist(prisnenchareg);
        return prisnenchareg;
    }
    
    @Override
    public PriseEnCharge modifierTauxRempoursement(PriseEnCharge prisnenchareg, double tauxremboursement) {
        prisnenchareg.setTauxRempoursement(tauxremboursement);
        em.merge(prisnenchareg);
        return prisnenchareg;
    }
    
}
