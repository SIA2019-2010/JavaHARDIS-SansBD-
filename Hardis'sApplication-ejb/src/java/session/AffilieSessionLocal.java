/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entitee.Devis;
import entitee.PersonnePhysique;
import entitee.Produit;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author lixin
 */
@Local
public interface AffilieSessionLocal {

    PersonnePhysique authentificationAffilie(String login, String mdp);

    PersonnePhysique modifierMDP(String nvMDP,PersonnePhysique pers);

    PersonnePhysique modifierMail(String nvMail, PersonnePhysique pers);

    PersonnePhysique modifierAdresse(String nvAdresse, PersonnePhysique pers);

    Devis creerDevis(double prix, Date dateDevis, ArrayList<PersonnePhysique> pers, Produit prod);
    
}
