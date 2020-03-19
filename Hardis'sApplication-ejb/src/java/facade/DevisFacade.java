/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.Devis;
import entitee.PersonnePhysique;
import entitee.Produit;
import java.util.List;
import java.util.Collection;
import java.util.Date;
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
public class DevisFacade extends AbstractFacade<Devis> implements DevisFacadeLocal {

    @PersistenceContext(unitName = "Hardis_sApplication-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DevisFacade() {
        super(Devis.class);
    }

    @Override
    public Devis creerDevis(PersonnePhysique pers,Produit prod,double prix,Date dateDevis,List<PersonnePhysique> listeAyantdroits) {
        Devis dev=new Devis();
        dev.setLaPersonne(pers);
        dev.setLeProduit(prod);
        dev.setDateDevis(dateDevis);
        dev.setPrix(prix);
        dev.setLesAyantsDroit(listeAyantdroits);
        
        em.persist(dev);
        return dev;
    }

    @Override
    public List<Devis> rechercherDevisPersonne(PersonnePhysique personne) {
        List<Devis> listdev; 
        String tx = "SELECT dev FROM Devis AS dev where dev.laPersonnePhysique=:pers"; 
        Query req = getEntityManager().createQuery(tx); 
        req.setParameter("pers", personne); 
        listdev= req.getResultList (); 
        return listdev;
         
    }

    @Override
    public List<Devis> rechercherDevisDate(Date dateDevis) {
        List<Devis> listdev; 
        String tx = "SELECT dev FROM Devis AS dev where dev.dateDevis=:date"; 
        Query req = getEntityManager().createQuery(tx); 
        req.setParameter("date", dateDevis); 
        listdev= req.getResultList (); 
        return listdev;
    }

    @Override
    public List<PersonnePhysique> rechercherAyantsDroitPersonne(PersonnePhysique personne) {
       List<PersonnePhysique> listAyantsdroit; 
        String tx = "SELECT dev.lesAyantsDroit FROM Devis AS dev where dev.laPersonnePhysique=:pers"; 
        Query req = getEntityManager().createQuery(tx); 
        req.setParameter("pers", personne); 
        listAyantsdroit= req.getResultList (); 
        return listAyantsdroit;
    }

    @Override
    public Devis rechercheExistantID(Long iddev) {
        Devis dev=null;
        String txt = "SELECT d FROM Devis AS d WHERE d.id=:ii";
        Query req = getEntityManager().createQuery(txt); 
        req = req.setParameter("ii",iddev);
        List<Devis> result = req.getResultList();
        if(result.size()==1){
            dev = (Devis)result.get(0);
        }
        return dev;
    }
    
    
    
  
    
    
    
  
    
    
    
    
    
    
}
