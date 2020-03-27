/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.Acte;
import entitee.PersonnePhysique;
import entitee.Remboursement;
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
public class ActeFacade extends AbstractFacade<Acte> implements ActeFacadeLocal {

    @PersistenceContext(unitName = "Hardis_sApplication-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActeFacade() {
        super(Acte.class);
    }

    @Override
    public List<Acte> rechercheActesPersonne(PersonnePhysique personnePhy) {
        List<Acte> listactes; 
        String tx = "SELECT act FROM Acte AS act where act.laPersonnePhysique=:pers"; 
        Query req = getEntityManager().createQuery(tx); 
        req.setParameter("pers", personnePhy); 
        listactes= req.getResultList (); 
        
        return listactes;

    }

    @Override
    public List<Remboursement> rechercheRemboursementsPersonne(PersonnePhysique persphy) {
        List<Remboursement> listremboursement; 
        String tx = "SELECT act.leRemboursement FROM Acte AS act where act.laPersonnePhysique=:pers"; 
        Query req = getEntityManager().createQuery(tx); 
        req.setParameter("pers", persphy); 
        listremboursement= req.getResultList (); 
        return listremboursement;
    }

    @Override
    public List<Acte> rechercheActePersNonRemb(PersonnePhysique pers) {
        List<Acte> listact; 
        String tx = "SELECT act FROM Acte AS act where act.leRemboursement IS NULL and act.laPersonnePhysique=:pers"; //==null 
        Query req = getEntityManager().createQuery(tx); 
        req.setParameter("vide", null); 
        listact= req.getResultList (); 
        return listact;
        
  
    }

    @Override
    public Acte rechercheActeID(Long idact) {
        Acte act;
       String txt="SELECT actee FROM Acte AS actee WHERE actee.id=:ii";
        Query req=getEntityManager().createQuery(txt);
        req=req.setParameter("ii",idact);
        act=null;
        List <Acte> result = req.getResultList();
        if (result.size()==1)
            {act=(Acte)result.get(0);};
        return act;
    }

    @Override
    public List<Acte> rechercheActesNonRembourse() {
        List<Acte> listact; 
        String tx = "SELECT act FROM Acte AS act where act.leRemboursement =:vide"; //==null 
        Query req = getEntityManager().createQuery(tx); 
        req.setParameter("vide", null); 
        listact= req.getResultList (); 
        return listact;
    }
    
    
    
    
    
}
