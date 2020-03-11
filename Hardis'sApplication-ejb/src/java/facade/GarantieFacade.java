/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.BaseRemboursementSecu;
import entitee.Garantie;
import entitee.TypeGarantie;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lixin
 */
@Stateless
public class GarantieFacade extends AbstractFacade<Garantie> implements GarantieFacadeLocal {

    @PersistenceContext(unitName = "Hardis_sApplication-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GarantieFacade() {
        super(Garantie.class);
    }
    
    @Override
    public Garantie creerGarantie(String libellegarantie, BaseRemboursementSecu labase, ArrayList<TypeGarantie> lestypes) {
        Garantie garantie=new Garantie();
        garantie.setLibelleGarantie(libellegarantie);
        em.persist(garantie);
        return garantie;
    }
    
    @Override
    public Garantie modifierLibelleGarantie(Garantie garantie, String libellegarantie) {
        garantie.setLibelleGarantie(libellegarantie);
        em.persist(garantie);
        return garantie;
    }
    
}
