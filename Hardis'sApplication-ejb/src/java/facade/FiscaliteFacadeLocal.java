/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.Fiscalite;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author lixin
 */
@Local
public interface FiscaliteFacadeLocal {

    void create(Fiscalite fiscalite);

    void edit(Fiscalite fiscalite);

    void remove(Fiscalite fiscalite);

    Fiscalite find(Object id);

    List<Fiscalite> findAll();

    List<Fiscalite> findRange(int[] range);

    int count();
    
}
