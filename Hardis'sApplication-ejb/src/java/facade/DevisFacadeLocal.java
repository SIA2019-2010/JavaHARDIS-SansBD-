/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.Devis;
import entitee.PersonnePhysique;
import entitee.Produit;
import java.util.List;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author lixin
 */
@Local
public interface DevisFacadeLocal {

    void create(Devis devis);

    void edit(Devis devis);

    void remove(Devis devis);

    Devis find(Object id);

    List<Devis> findAll();

    List<Devis> findRange(int[] range);

    int count();

    Devis creerDevis(List<PersonnePhysique> listpers,Produit prod,double prix,Date dateDevis);

    List<Devis> rechercherDevisPersonne(PersonnePhysique pers);

    List<Devis> rechercherDevisDate(Date dateDevis);
    
}
