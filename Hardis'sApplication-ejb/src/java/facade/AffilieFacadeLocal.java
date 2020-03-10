/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.Affilie;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author lixin
 */
@Local
public interface AffilieFacadeLocal {

    void create(Affilie affilie);

    void edit(Affilie affilie);

    void remove(Affilie affilie);

    Affilie find(Object id);

    List<Affilie> findAll();

    List<Affilie> findRange(int[] range);

    int count();
    
}
