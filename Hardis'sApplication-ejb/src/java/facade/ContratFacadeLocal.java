/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.Contrat;
import entitee.Domaine;
import entitee.PersonneMorale;
import entitee.Produit;
import entitee.TypeGarantie;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author lixin
 */
@Local
public interface ContratFacadeLocal {

    void create(Contrat contrat);

    void edit(Contrat contrat);

    void remove(Contrat contrat);

    Contrat find(Object id);

    List<Contrat> findAll();

    List<Contrat> findRange(int[] range);

    int count();

    Contrat creerContrat(Date dateDeb, Date dateFin, double prixMensuel, Domaine leDomaine, ArrayList<TypeGarantie> lesGaranties, Produit leProduit);

    List<Contrat> rechercheContratPersonneMorale(PersonneMorale persmo);

    List<Contrat> rechercheContratDateFin(Date datef);

    Contrat modifierDateFin(Date datef, Contrat cont);
    
}
