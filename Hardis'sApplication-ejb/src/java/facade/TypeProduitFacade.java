/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.TypeProduit;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

    @Override
    public TypeProduit rechercheExistantID(Long idtyp) {
       TypeProduit typeprod;
        String txt="SELECT typep FROM TypeProduit AS typep WHERE typep.id=:ii";
        Query req=getEntityManager().createQuery(txt);
        req=req.setParameter("ii",idtyp);
        typeprod=null;
        List <TypeProduit> result = req.getResultList();
        if (result.size()==1)
            {typeprod=(TypeProduit)result.get(0);};
        return typeprod;
    }
   
    
    
    
    
}
