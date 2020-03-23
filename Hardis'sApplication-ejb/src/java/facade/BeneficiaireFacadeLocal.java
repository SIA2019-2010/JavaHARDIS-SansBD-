/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.Beneficiaire;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author lixin
 */
@Local
public interface BeneficiaireFacadeLocal {

    void create(Beneficiaire beneficiaire);

    void edit(Beneficiaire beneficiaire);

    void remove(Beneficiaire beneficiaire);

    Beneficiaire find(Object id);

    List<Beneficiaire> findAll();

    List<Beneficiaire> findRange(int[] range);

    int count();
    
    Beneficiaire creerBeneficiaire(String libelle);
    
    Beneficiaire modifierLibelleBeneficiaire(Beneficiaire ben, String libelle);

    Beneficiaire rechercheExistantBeneficiaireID(Long id);

    List<Beneficiaire> rechercheBeneficiaires();
    
    Beneficiaire rechercheExistantBeneficiaireLibelle(String libelle);
    
}
