/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.Praticien;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author lixin
 */
@Local
public interface PraticienFacadeLocal {

    void create(Praticien praticien);

    void edit(Praticien praticien);

    void remove(Praticien praticien);

    Praticien find(Object id);

    List<Praticien> findAll();

    List<Praticien> findRange(int[] range);

    int count();
    
}
