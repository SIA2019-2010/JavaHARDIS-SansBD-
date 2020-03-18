/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.Domaine;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author lixin
 */
@Local
public interface DomaineFacadeLocal {

    void create(Domaine domaine);

    void edit(Domaine domaine);

    void remove(Domaine domaine);

    Domaine find(Object id);

    List<Domaine> findAll();

    List<Domaine> findRange(int[] range);

    int count();
    
    Domaine creerDomaine(String libelleDomaine);
    
    Domaine modifierLibelleDomaine(Domaine domaine, String libelleDomaine);

    Domaine rechercheExistantID(Long iddo);
    
}
