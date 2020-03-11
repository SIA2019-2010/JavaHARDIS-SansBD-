/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.Domaine;
import entitee.Gestionnaire;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lixin
 */
@Stateless
public class GestionnaireFacade extends AbstractFacade<Gestionnaire> implements GestionnaireFacadeLocal {

    @PersistenceContext(unitName = "Hardis_sApplication-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GestionnaireFacade() {
        super(Gestionnaire.class);
    }

    @Override
    public Gestionnaire creerGestionnaire(String nom, String prenom, String adresse, String telephone, String login, String mdp, Domaine domaine) {
        Gestionnaire gest=new Gestionnaire();
        gest.setNom(nom);
        gest.setPrenom(prenom);
        gest.setAdresse(adresse);
        gest.setTelephone(telephone);
        gest.setLogin(login);
        gest.setMdp(mdp);
        gest.setLeDomaine(domaine);
        em.persist(gest);
        return null;
    }
    
    @Override
    public Gestionnaire modifierAdresse(Gestionnaire gest, String adresse) {
        gest.setAdresse(adresse);
        em.persist(gest);
        return gest;
    }
    
    @Override
    public Gestionnaire modifierTelephone(Gestionnaire gest, String telephone) {
        gest.setTelephone(telephone);
        em.persist(gest);
        return gest;
    }
    
    @Override
    public Gestionnaire modifierMdp(Gestionnaire gest, String mdp) {
        gest.setMdp(mdp);
        em.persist(gest);
        return gest;
    }
    
    @Override
    public Gestionnaire modifierDomaine(Gestionnaire gest, Domaine domaine) {
        gest.setLeDomaine(domaine);
        em.persist(gest);
        return gest;
    }
    
}
