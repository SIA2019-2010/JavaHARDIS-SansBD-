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
import java.util.List;
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

    Contrat creerContrat(Date dateCrea, double prixMensuel, Produit leProduit);

    List<Contrat> rechercheContratDateFin(Date datef);

    Contrat modifierDateFin(Date datef, Contrat cont);
    
    List<Contrat> AfficherContratGestionnaire(Domaine dom);
    
    Contrat ValiderContrat(Contrat cnt);

    Contrat rechercheExistantID(Long idcnt);

    Contrat cloturerContrat(Contrat cnt);
    
}
