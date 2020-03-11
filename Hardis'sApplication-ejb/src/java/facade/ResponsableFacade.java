/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.PersonneMorale;
import entitee.Responsable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lixin
 */
@Stateless
public class ResponsableFacade extends AbstractFacade<Responsable> implements ResponsableFacadeLocal {

    @PersistenceContext(unitName = "Hardis_sApplication-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ResponsableFacade() {
        super(Responsable.class);
    }
    
    @Override
    public Responsable creerResponsable(String nom, String prenom, String mail, String telephone, String login, String mdp, PersonneMorale personne) {
        Responsable resp = new Responsable();
        resp.setNom(nom);
        resp.setPrenom(prenom);
        resp.setMail(mail);
        resp.setTelephone(telephone);
        resp.setLogin(login);
        resp.setMdp(mdp);
        resp.setLaPersonneMorale(personne);
        em.persist(resp);
        return resp;
    }
    
    @Override
    public Responsable modifierMail(Responsable resp, String mail) {
        resp.setMail(mail);
        em.merge(resp);
        return resp;
    }
    
    @Override
    public Responsable modifierTelephone(Responsable resp, String telephone) {
        resp.setTelephone(telephone);
        em.merge(resp);
        return resp;
    }
    
    @Override
    public Responsable modifierMdp(Responsable resp, String mdp) {
        resp.setMdp(mdp);
        em.merge(resp);
        return resp;
    }
    
}
