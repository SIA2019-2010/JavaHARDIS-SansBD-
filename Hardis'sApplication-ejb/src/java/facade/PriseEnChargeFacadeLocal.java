/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.PriseEnCharge;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author lixin
 */
@Local
public interface PriseEnChargeFacadeLocal {

    void create(PriseEnCharge priseEnCharge);

    void edit(PriseEnCharge priseEnCharge);

    void remove(PriseEnCharge priseEnCharge);

    PriseEnCharge find(Object id);

    List<PriseEnCharge> findAll();

    List<PriseEnCharge> findRange(int[] range);

    int count();
    
}
