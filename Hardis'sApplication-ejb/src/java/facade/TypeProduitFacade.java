/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.TypeProduit;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lixin
 */
@Stateless
public class TypeProduitFacade extends AbstractFacade<TypeProduit> implements TypeProduitFacadeLocal {

    @PersistenceContext(unitName = "Hardis_sApplication-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypeProduitFacade() {
        super(TypeProduit.class);
    }
    
    @Override
    public TypeProduit creerTypeProduit(String libelle) {
        TypeProduit typeproduit=new TypeProduit();
        typeproduit.setLibelleTypeProduit(libelle);
        em.persist(typeproduit);
        return typeproduit;
    }
    
    @Override
    public TypeProduit modifierLibelleTypeProduit(TypeProduit typeproduit, String libelle) {
        typeproduit.setLibelleTypeProduit(libelle);
        em.merge(typeproduit);
        return typeproduit;
    }
    
}
