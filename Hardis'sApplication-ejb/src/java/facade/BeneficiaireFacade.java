/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.Beneficiaire;
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
public class BeneficiaireFacade extends AbstractFacade<Beneficiaire> implements BeneficiaireFacadeLocal {

    @PersistenceContext(unitName = "Hardis_sApplication-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BeneficiaireFacade() {
        super(Beneficiaire.class);
    }
    
    @Override
    public Beneficiaire creerBeneficiaire(String libelle) {
        em.flush();
        Beneficiaire ben=new Beneficiaire();
        ben.setLibelleBeneficiaire(libelle);
        em.persist(ben);
        return ben;
    }
    
    @Override
    public Beneficiaire modifierLibelleBeneficiaire(Beneficiaire ben, String libelle) {
        ben.setLibelleBeneficiaire(libelle);
        em.merge(ben);
        return ben;
    }

    @Override
    public Beneficiaire rechercheExistantBeneficiaireID(Long idd) {
            Beneficiaire ben;
        String txt="SELECT ben FROM Beneficiaire AS ben WHERE ben.id=:ii";
        Query req=getEntityManager().createQuery(txt);
        req=req.setParameter("ii",idd);
        ben=null;
        List <Beneficiaire> result = req.getResultList();
        if (result.size()==1)
            {ben=(Beneficiaire)result.get(0);};
        return ben;
    }

    @Override
    public Beneficiaire rechercheExistantBeneficiaireLibelle(String libelle) {
        Beneficiaire ben;
        String txt="SELECT ben FROM Beneficiaire AS ben WHERE ben.LibelleBeneficiaire=:libelle";
        Query req=getEntityManager().createQuery(txt);
        req=req.setParameter("libelle",libelle);
        ben=null;
        List <Beneficiaire> result = req.getResultList();
        if (result.size()==1)
            {ben=(Beneficiaire)result.get(0);};
        return ben;
    }

    @Override
    public List<Beneficiaire> rechercheBeneficiaires() {
        List<Beneficiaire> result=null;
        String txt="SELECT b FROM Beneficiaire AS b";
        Query req=getEntityManager().createQuery(txt);
        result = req.getResultList();
        return result;
    }
    
}
