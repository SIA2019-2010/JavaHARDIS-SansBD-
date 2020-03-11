/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.PersonneMorale;
import entitee.Responsable;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author lixin
 */
@Local
public interface ResponsableFacadeLocal {

    void create(Responsable responsable);

    void edit(Responsable responsable);

    void remove(Responsable responsable);

    Responsable find(Object id);

    List<Responsable> findAll();

    List<Responsable> findRange(int[] range);

    int count();
    
    Responsable creerResponsable(String nom, String prenom, String mail, String telephone, String login, String mdp, PersonneMorale personne);
    
    Responsable modifierMail(Responsable resp, String mail);
    
    Responsable modifierTelephone(Responsable resp, String telephone);
    
    Responsable modifierMdp(Responsable resp, String mdp);
    
    Responsable authentificationResponsable(String login, String mdp);
    
}
