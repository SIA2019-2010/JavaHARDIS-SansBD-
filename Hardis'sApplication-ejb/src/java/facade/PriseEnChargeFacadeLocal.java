/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.Garantie;
import entitee.PriseEnCharge;
import entitee.Produit;
import entitee.Unite;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author lixin
 */
@Local
public interface PriseEnChargeFacadeLocal {

    void create(PriseEnCharge priseEnCharge);

    void edit(PriseEnCharge priseEnCharge);

    void remove(PriseEnCharge priseEnCharge);

    PriseEnCharge find(Object id);

    List<PriseEnCharge> findAll();

    List<PriseEnCharge> findRange(int[] range);

    int count();
    
    void Enregistrer(PriseEnCharge p);
    
    PriseEnCharge creerPriseEnCharge(double tauxremboursement, String baseremboursement, Unite unite, Produit produit, Garantie garantie);
    
    PriseEnCharge modifierTauxRempoursement(PriseEnCharge prisnenchareg, double tauxremboursement);
    
}
