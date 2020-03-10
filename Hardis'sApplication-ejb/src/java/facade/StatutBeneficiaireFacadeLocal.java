/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.StatutBeneficiaire;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author lixin
 */
@Local
public interface StatutBeneficiaireFacadeLocal {

    void create(StatutBeneficiaire statutBeneficiaire);

    void edit(StatutBeneficiaire statutBeneficiaire);

    void remove(StatutBeneficiaire statutBeneficiaire);

    StatutBeneficiaire find(Object id);

    List<StatutBeneficiaire> findAll();

    List<StatutBeneficiaire> findRange(int[] range);

    int count();
    
}
