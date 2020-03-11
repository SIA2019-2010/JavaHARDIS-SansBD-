/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitee;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author alexisbaillieu
 */
@Entity
public class Produit implements Serializable {

    @OneToMany(mappedBy = "leProduit")
    private ArrayList<Devis> lesDevis;

    @OneToMany(mappedBy = "leProduit")
    private ArrayList<Contrat> lesContrats;

    @OneToMany(mappedBy = "leProduit")
    private ArrayList<PriseEnCharge> lesPriseEnCharges;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String NomProduit;
    
    @ManyToOne
    private TypeProduit leTypeProduit;

    @ManyToMany
    private ArrayList<Fiscalite> lesFiscalites;

    @ManyToMany
    private ArrayList<TypeGarantie> lesTypesGarantie;

    @ManyToMany
    private ArrayList<AssietteCotisation> lesAssiettesCotisation;

    @ManyToMany
    private ArrayList<Population> lesPopulations;

    private EnumSet<Beneficiaire> Beneficiaires;

    public ArrayList<Population> getLesPopulations() {
        return lesPopulations;
    }

    public void setLesPopulations(ArrayList<Population> lesPopulations) {
        this.lesPopulations = lesPopulations;
    }

    public EnumSet<Beneficiaire> getBeneficiaires() {
        return Beneficiaires;
    }

    public void setBeneficiaires(EnumSet<Beneficiaire> Beneficiaires) {
        this.Beneficiaires = Beneficiaires;
    }

    public ArrayList<AssietteCotisation> getLesAssiettesCotisation() {
        return lesAssiettesCotisation;
    }

    public void setLesAssiettesCotisation(ArrayList<AssietteCotisation> lesAssiettesCotisation) {
        this.lesAssiettesCotisation = lesAssiettesCotisation;
    }

    public ArrayList<TypeGarantie> getLesTypesGarantie() {
        return lesTypesGarantie;
    }

    public void setLesTypesGarantie(ArrayList<TypeGarantie> lesTypesGarantie) {
        this.lesTypesGarantie = lesTypesGarantie;
    }

    public ArrayList<Fiscalite> getLesFiscalites() {
        return lesFiscalites;
    }

    public void setLesFiscalites(ArrayList<Fiscalite> lesFiscalites) {
        this.lesFiscalites = lesFiscalites;
    }

    public TypeProduit getLeTypeProduit() {
        return leTypeProduit;
    }

    public void setLeTypeProduit(TypeProduit leTypeProduit) {
        this.leTypeProduit = leTypeProduit;
    }

    public String getNomProduit() {
        return NomProduit;
    }

    public void setNomProduit(String NomProduit) {
        this.NomProduit = NomProduit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArrayList<Devis> getLesDevis() {
        return lesDevis;
    }

    public void setLesDevis(ArrayList<Devis> lesDevis) {
        this.lesDevis = lesDevis;
    }

    public ArrayList<Contrat> getLesContrats() {
        return lesContrats;
    }

    public void setLesContrats(ArrayList<Contrat> lesContrats) {
        this.lesContrats = lesContrats;
    }

    public ArrayList<PriseEnCharge> getLesPriseEnCharges() {
        return lesPriseEnCharges;
    }

    public void setLesPriseEnCharges(ArrayList<PriseEnCharge> lesPriseEnCharges) {
        this.lesPriseEnCharges = lesPriseEnCharges;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produit)) {
            return false;
        }
        Produit other = (Produit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitee.Produit[ id=" + id + " ]";
    }

}
