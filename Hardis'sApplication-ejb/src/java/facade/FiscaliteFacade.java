/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.Fiscalite;
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
public class FiscaliteFacade extends AbstractFacade<Fiscalite> implements FiscaliteFacadeLocal {

    @PersistenceContext(unitName = "Hardis_sApplication-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FiscaliteFacade() {
        super(Fiscalite.class);
    }

    @Override
    public Fiscalite creerFiscalite(double taxe, double CMU, double TCA) {
        em.flush();
        Fiscalite fisca = new Fiscalite();
        fisca.setCMU(CMU);
        fisca.setTCA(TCA);
        fisca.setTaxe(taxe);
       
        em.persist(fisca);
        return fisca;
    }

    @Override
    public Fiscalite rechercheExistantID(Long idfi) {
        Fiscalite fisca;
        String txt="SELECT fis FROM Fiscalite AS fis WHERE fis.id=:ii";
        Query req=getEntityManager().createQuery(txt);
        req=req.setParameter("ii",idfi);
        fisca=null;
        List <Fiscalite> result = req.getResultList();
        if (result.size()==1)
            {fisca=(Fiscalite)result.get(0);};
        return fisca;
    }
    
    
    
    
    
}
