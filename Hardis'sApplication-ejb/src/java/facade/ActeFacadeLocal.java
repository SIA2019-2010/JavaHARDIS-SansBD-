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
import javax.ejb.Local;

/**
 *
 * @author lixin
 */
@Local
public interface ActeFacadeLocal {

    void create(Acte acte);

    void edit(Acte acte);

    void remove(Acte acte);

    Acte find(Object id);

    List<Acte> findAll();

    List<Acte> findRange(int[] range);

    int count();

    List<Acte> rechercheActesPersonne(PersonnePhysique personnePhy);

    List<Remboursement> rechercheRemboursementsPersonne(PersonnePhysique pers);
    
}
