/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.AssietteCotisation;
import entitee.Fiscalite;
import entitee.Produit;
import entitee.TypeGarantie;
import entitee.TypeProduit;
import entitee.Population;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author lixin
 */
@Local
public interface ProduitFacadeLocal {

    void create(Produit produit);

    void edit(Produit produit);

    void remove(Produit produit);

    Produit find(Object id);

    List<Produit> findAll();

    List<Produit> findRange(int[] range);

    int count();

    Produit creerProduit(String nom, EnumSet lesBeneficiaires, ArrayList<AssietteCotisation> lesAssiettes, ArrayList<TypeGarantie> lesTypesGaranties, TypeProduit leTypeProduit, ArrayList<Fiscalite> lesFiscalites,ArrayList<Population> lesPopulations);
    
}
