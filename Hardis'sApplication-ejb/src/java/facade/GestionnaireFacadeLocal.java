/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.Domaine;
import entitee.Gestionnaire;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author lixin
 */
@Local
public interface GestionnaireFacadeLocal {

    void create(Gestionnaire gestionnaire);

    void edit(Gestionnaire gestionnaire);

    void remove(Gestionnaire gestionnaire);

    Gestionnaire find(Object id);

    List<Gestionnaire> findAll();

    List<Gestionnaire> findRange(int[] range);

    int count();

    Gestionnaire creerGestionnaire(String nom, String prenom, String adresse, String telephone, String login, String mdp, Domaine domaine);
    
    Gestionnaire modifierAdresse(Gestionnaire gest, String adresse);
    
    Gestionnaire modifierTelephone(Gestionnaire gest, String telephone);
    
    Gestionnaire modifierMdp(Gestionnaire gest, String mdp);
    
    Gestionnaire modifierDomaine(Gestionnaire gest, Domaine domaine);
    
}
