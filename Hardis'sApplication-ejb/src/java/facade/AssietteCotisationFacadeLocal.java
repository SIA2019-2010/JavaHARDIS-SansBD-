/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.AssietteCotisation;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author lixin
 */
@Local
public interface AssietteCotisationFacadeLocal {

    void create(AssietteCotisation assietteCotisation);

    void edit(AssietteCotisation assietteCotisation);

    void remove(AssietteCotisation assietteCotisation);

    AssietteCotisation find(Object id);

    List<AssietteCotisation> findAll();

    List<AssietteCotisation> findRange(int[] range);

    int count();
    
}
