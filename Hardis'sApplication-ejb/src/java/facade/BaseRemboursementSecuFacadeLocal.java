/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.BaseRemboursementSecu;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author lixin
 */
@Local
public interface BaseRemboursementSecuFacadeLocal {

    void create(BaseRemboursementSecu baseRemboursementSecu);

    void edit(BaseRemboursementSecu baseRemboursementSecu);

    void remove(BaseRemboursementSecu baseRemboursementSecu);

    BaseRemboursementSecu find(Object id);

    List<BaseRemboursementSecu> findAll();

    List<BaseRemboursementSecu> findRange(int[] range);

    int count();
    
}
