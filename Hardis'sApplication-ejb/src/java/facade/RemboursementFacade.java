/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.Acte;
import entitee.EtatRemboursement;
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
public class RemboursementFacade extends AbstractFacade<Remboursement> implements RemboursementFacadeLocal {

    @PersistenceContext(unitName = "Hardis_sApplication-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RemboursementFacade() {
        super(Remboursement.class);
    }

    @Override
    public Remboursement creerRemboursement(double brEffective, double remboursementEffectif, EtatRemboursement etatRemboursement,Acte leActe) {
        Remboursement rembour = new Remboursement();
        rembour.setBREffective(brEffective);
        rembour.setEtatRemboursement(etatRemboursement);
        rembour.setLeActe(leActe);
        rembour.setRemboursementEffectif(remboursementEffectif);
        
        em.persist(rembour);
        return rembour;
    }
    
    @Override
    public List<Remboursement> afficherRempoursement(PersonnePhysique perso) {        
        String txt="SELECT r FROM Remboursement AS r WHERE r.leActe.laPersonnePhysique:=:persp";
        Query req=getEntityManager().createQuery(txt);
        req=req.setParameter("persp",perso);
        List <Remboursement> result = req.getResultList();
        return result;
    }
    
    
}
