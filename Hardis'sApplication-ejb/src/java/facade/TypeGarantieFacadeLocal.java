/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.TypeGarantie;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author lixin
 */
@Local
public interface TypeGarantieFacadeLocal {

    void create(TypeGarantie typeGarantie);

    void edit(TypeGarantie typeGarantie);

    void remove(TypeGarantie typeGarantie);

    TypeGarantie find(Object id);

    List<TypeGarantie> findAll();

    List<TypeGarantie> findRange(int[] range);

    int count();
    
}
