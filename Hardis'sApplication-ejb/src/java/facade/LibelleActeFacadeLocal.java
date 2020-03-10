/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.LibelleActe;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author lixin
 */
@Local
public interface LibelleActeFacadeLocal {

    void create(LibelleActe libelleActe);

    void edit(LibelleActe libelleActe);

    void remove(LibelleActe libelleActe);

    LibelleActe find(Object id);

    List<LibelleActe> findAll();

    List<LibelleActe> findRange(int[] range);

    int count();
    
}
