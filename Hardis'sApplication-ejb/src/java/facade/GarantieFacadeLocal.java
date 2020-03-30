/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.BaseRemboursementSecu;
import entitee.Garantie;
import entitee.TypeGarantie;
import java.util.List;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author lixin
 */
@Local
public interface GarantieFacadeLocal {

    void create(Garantie garantie);

    void edit(Garantie garantie);

    void remove(Garantie garantie);

    Garantie find(Object id);

    List<Garantie> findAll();

    List<Garantie> findRange(int[] range);

    int count();
    
    Garantie creerGarantie(String libellegarantie, BaseRemboursementSecu labase, List<TypeGarantie> lestypes);
    
    Garantie modifierLibelleGarantie(Garantie garantie, String libellegarantie);

    Garantie recupererGarantieID(Long idgar);
    
}
