/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.Population;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author lixin
 */
@Local
public interface PopulationFacadeLocal {

    void create(Population population);

    void edit(Population population);

    void remove(Population population);

    Population find(Object id);

    List<Population> findAll();

    List<Population> findRange(int[] range);

    int count();
    
    Population creerPopulation(String libelle);
    
    Population modifierLibellePopulation(Population popu, String libelle);

    Population rechercheExistantPopulationID(long id);
    
}
