/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.Genre;
import entitee.PersonnePhysique;
import entitee.Population;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author lixin
 */
@Local
public interface PersonnePhysiqueFacadeLocal {

    void create(PersonnePhysique personnePhysique);

    void edit(PersonnePhysique personnePhysique);

    void remove(PersonnePhysique personnePhysique);

    PersonnePhysique find(Object id);

    List<PersonnePhysique> findAll();

    List<PersonnePhysique> findRange(int[] range);

    int count();

    PersonnePhysique creerPersonnePhysique(String nom, String prenom, Date dateNaiss, String numeroSS, String adresse, String mail, Genre genre,Population laPopulation);

    PersonnePhysique creerPersonnePhysiqueDevis(String nom, String prenom, String mail,String numeroSS,Date datenaiss, Population laPopulation);
            
    PersonnePhysique recherchePersonnePhysique(String nom, String prenom);

    PersonnePhysique modifierAdresse(PersonnePhysique pers, String adresse);

    PersonnePhysique modifierMail(PersonnePhysique pers, String mail);

    PersonnePhysique authentificationAffilie(String login, String mdp);

    PersonnePhysique modifierMdp(PersonnePhysique pers,String nvMDP);

    PersonnePhysique enregistrerCompte(PersonnePhysique pers, String login, String mdp);

    boolean rechercheDispoLogin(String logintest);

    PersonnePhysique renseignerInfos(PersonnePhysique pers, String adresse, Genre genre,String RIB);

    boolean rechercheBooleanEmail(String email);

    PersonnePhysique recherchePersMail(String mail);

    PersonnePhysique recherchePersNumeroSS(String numSS);

    PersonnePhysique creerAyantsDroits(String nom, String prenom, Date datenaiss, String numeroSS);

    PersonnePhysique recherchePersonneID(Long idper);

    PersonnePhysique renseignerLoginMdp(PersonnePhysique pers, String login, String mdp);

    PersonnePhysique renseignerInfosAyantsDroit(PersonnePhysique pers, String adresse, Genre genre, Population population);

    PersonnePhysique creerPersonneComplete(String nom, String prenom, Genre genre, Date datenaiss, String numeroSS, String mail, Population popu, String adresse);
    
    void SetRIB(PersonnePhysique p, String RIB);
    
}
