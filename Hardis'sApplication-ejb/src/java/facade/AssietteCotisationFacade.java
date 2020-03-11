/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.AssietteCotisation;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lixin
 */
@Stateless
public class AssietteCotisationFacade extends AbstractFacade<AssietteCotisation> implements AssietteCotisationFacadeLocal {

    @PersistenceContext(unitName = "Hardis_sApplication-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AssietteCotisationFacade() {
        super(AssietteCotisation.class);
    }
    
    @Override
    public AssietteCotisation creerAssietteCotisation(String libelleassiete, double assiette) {
        AssietteCotisation assiettecotisation=new AssietteCotisation();
        assiettecotisation.setLibelleAssiette(libelleassiete);
        assiettecotisation.setAssiette(assiette);
        em.persist(assiettecotisation);
        return assiettecotisation;
    }
    
    @Override
    public AssietteCotisation modifierLibelleAssiette(AssietteCotisation assiettecotisation, String libelleassiete) {
        assiettecotisation.setLibelleAssiette(libelleassiete);
        em.merge(assiettecotisation);
        return assiettecotisation;
    }
    
    @Override
    public AssietteCotisation modifierAssiette(AssietteCotisation assiettecotisation, double assiette) {
        assiettecotisation.setAssiette(assiette);
        em.merge(assiettecotisation);
        return assiettecotisation;
    }
    
}
