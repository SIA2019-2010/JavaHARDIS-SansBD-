/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.TypeGarantie;
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
public class TypeGarantieFacade extends AbstractFacade<TypeGarantie> implements TypeGarantieFacadeLocal {

    @PersistenceContext(unitName = "Hardis_sApplication-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypeGarantieFacade() {
        super(TypeGarantie.class);
    }
    
    @Override
    public TypeGarantie creerTypeGarantie(String libelle) {
        TypeGarantie typeproduit=new TypeGarantie();
        typeproduit.setTypeGarantie(libelle);
        em.persist(typeproduit);
        return typeproduit;
    }
    
    @Override
    public TypeGarantie modifierTypeGarantie(TypeGarantie typeproduit, String libelle) {
        typeproduit.setTypeGarantie(libelle);
        em.merge(typeproduit);
        return typeproduit;
    }

    @Override
    public TypeGarantie rechercheExistantID(Long idga) {
        TypeGarantie typegar;
        String txt="SELECT typep FROM TypeProduit AS typep WHERE typep.id=:ii";
        Query req=getEntityManager().createQuery(txt);
        req=req.setParameter("ii",idga);
        typegar=null;
        List <TypeGarantie> result = req.getResultList();
        if (result.size()==1)
            {typegar=(TypeGarantie)result.get(0);};
        return typegar;
    }
    
    
    
    
}
