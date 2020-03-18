/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.Contrat;
import entitee.Devis;
import entitee.Domaine;
import entitee.PersonneMorale;
import entitee.Produit;
import entitee.TypeGarantie;
import java.util.List;
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
public class ContratFacade extends AbstractFacade<Contrat> implements ContratFacadeLocal {

    @PersistenceContext(unitName = "Hardis_sApplication-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContratFacade() {
        super(Contrat.class);
    }

    @Override
    public Contrat creerContrat(Date dateCrea, double prixMensuel, Produit leProduit) {
        
        Contrat con = new Contrat();
        con.setDateCreation(dateCrea);
        con.setLeProduit(leProduit);
        con.setPrixMensuel(prixMensuel);
        
        em.persist(con);
        return con;
    }

    @Override
    public List<Contrat> rechercheContratDateFin(Date datef) {
        List<Contrat> listcontrats; 
        String tx = "SELECT cnt FROM Contrat AS cnt where cnt.dateFin=:date"; 
        Query req = getEntityManager().createQuery(tx); 
        req.setParameter("date", datef); 
        listcontrats= req.getResultList (); 
        return listcontrats;
    }

    @Override
    public Contrat modifierDateFin(Date datef, Contrat cont) {
        
        cont.setDateFin(datef);
        em.merge(cont);
        
        return cont;
    }
    
    @Override
    public List<Contrat> AfficherContrat(Domaine dom) {
        List<Contrat> listcontrats; 
        String tx = "SELECT cnt FROM Contrat AS cnt where cnt.leDomaine=:dom"; 
        Query req = getEntityManager().createQuery(tx); 
        req.setParameter("dom", dom); 
        listcontrats= req.getResultList (); 
        return listcontrats;
    }
    
    @Override
    public Contrat ValiderContrat(Contrat cnt) {
        cnt.setDateDebut(new Date());
        em.merge(cnt);
        return cnt;
    }
    
    
    
}
