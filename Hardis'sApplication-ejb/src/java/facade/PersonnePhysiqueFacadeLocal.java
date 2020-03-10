/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.PersonnePhysique;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author lixin
 */
@Local
public interface PersonnePhysiqueFacadeLocal {

    void create(PersonnePhysique personnePhysique);

    void edit(PersonnePhysique personnePhysique);

    void remove(PersonnePhysique personnePhysique);

    PersonnePhysique find(Object id);

    List<PersonnePhysique> findAll();

    List<PersonnePhysique> findRange(int[] range);

    int count();
    
}
