/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.Beneficiaire;
import entitee.Contrat;
import entitee.Domaine;
import entitee.PersonneMorale;
import entitee.PersonnePhysique;
import entitee.StatutBeneficiaire;
import java.util.Date;
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
    
    StatutBeneficiaire creerStatutBeneficiaire(Date datedubut, Beneficiaire statut, Contrat lecontrat, PersonnePhysique lapersonne);
    
    StatutBeneficiaire creerStatutBeneficiaireDevis(Date datedubut, Beneficiaire statut, PersonnePhysique lapersonne);
    
    StatutBeneficiaire modifierDateFinValide(StatutBeneficiaire statutbeneficiaire, Date datefin);
    
    StatutBeneficiaire rechercherStatutBeneficiaire(long id);

    List<Contrat> rechercheContrats(PersonnePhysique pers);

    List<Contrat> rechercheContratsAffilie(PersonnePhysique pers);
    
    List<StatutBeneficiaire> rechercherStatutBeneficiaire(PersonneMorale persmo);
    
    List<StatutBeneficiaire> rechercherStatutBeneficiaire(PersonnePhysique persph);

    StatutBeneficiaire rechercheAffilieDomaine(PersonnePhysique pers, Domaine domaine);
    
    List<Contrat> AfficherContratGestionnaire(Domaine dom, String ReSS, int page);
    
    List<Contrat> AfficherContratCree(Domaine dom, String ReSS, int page);
    
    List<Contrat> AfficherContratValide(Domaine dom, String ReSS, int page);
    
    long CompterContratGestionnaire(Domaine dom, String ReSS);
    
    long CompterContratValide(Domaine dom, String ReSS);
    
    long CompterContratCree(Domaine dom, String ReSS);
    
    long CompterPersonnesPhysiques(String ReSS);
    
    List<PersonnePhysique> AfficherPersonnesPhysiques(String ReSS, int page);

    
    
}
