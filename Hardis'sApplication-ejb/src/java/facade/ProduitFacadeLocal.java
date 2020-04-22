/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.Beneficiaire;
import entitee.Domaine;
import entitee.Fiscalite;
import entitee.PersonneMorale;
import entitee.Produit;
import entitee.TypeGarantie;
import entitee.TypeProduit;
import entitee.Population;
import entitee.PriseEnCharge;
import java.util.List;
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

    Produit creerProduit(String nom, double prix, List<Beneficiaire> lesBeneficiaires, Beneficiaire assiette, List<TypeGarantie> lesTypesGaranties, TypeProduit leTypeProduit, Fiscalite laFiscalite,List<Population> lesPopulations, PersonneMorale laPersonneMorale,Domaine leDomaine);
    
    boolean rechererProduitNom(String Nom);
    
    List<Produit> afficherPersonneMoraleProduit(PersonneMorale personne);

    Produit rechercheProduitCollectifID(long parameter);
    
    List<Produit> afficherProduit();

    List<Produit> afficherProduitCollectif(int page, String RePr);
    
    long CompterProduitCollectif(String RePr);
    
    List<Produit> afficherProduitIndividuel();
    
}
