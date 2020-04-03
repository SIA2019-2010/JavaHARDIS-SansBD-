/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.Activite;
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
public class ActiviteFacade extends AbstractFacade<Activite> implements ActiviteFacadeLocal {

    @PersistenceContext(unitName = "Hardis_sApplication-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActiviteFacade() {
        super(Activite.class);
    }

    @Override
    public Activite rechercheActiviteExistantID(long idct) {
            Activite acti;
        String txt="SELECT act FROM Activite AS act WHERE act.id=:ii";
        Query req=getEntityManager().createQuery(txt);
        req=req.setParameter("ii",idct);
        acti=null;
        List <Activite> result = req.getResultList();
        if (result.size()==1)
            {acti=(Activite)result.get(0);};
        return acti;
    }
    
    @Override
    public void creerActivite(String n){
        em.flush();
        Activite a=new Activite();
        a.setCodeNAF(n);
        a.setDescription(n);
        em.persist(a);
    }

    @Override
    public List<Activite> rechercheActivite() {
        List<Activite> result=null;
        String txt="SELECT a FROM Activite AS a";
        Query req=getEntityManager().createQuery(txt);
        result = req.getResultList();
        return result;
    }

    
    
}
