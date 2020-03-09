/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author alexisbaillieu
 */
@Entity
public class Produit implements Serializable {

    @OneToMany(mappedBy = "leProduit")
    private List<Devis> lesDevis;

    @OneToMany(mappedBy = "leProduit")
    private List<Contrat> lesContrats;

    @OneToMany(mappedBy = "leProduit")
    private List<PriseEnCharge> lesPriseEnCharges;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String NomProduit;

    private TypeProduit LeTypeProduit;

    @ManyToMany
    private List<Fiscalite> lesFiscalites;

    @ManyToMany
    private List<TypeGarantie> lesTypesGarantie;

    @ManyToMany
    private List<AssietteCotisation> lesAssiettesCotisation;

    @ManyToMany
    private List<StatutBeneficiaire> lesStatutsBeneficiaire;

    /*private List<Beneficiaire> Beneficiaires;
    
    private List<Population> Populations;

    public List<Population> getPopulations() {
        return Populations;
    }

    public void setPopulations(List<Population> Populations) {
        this.Populations = Populations;
    }


    public List<Beneficiaire> getBeneficiaires() {
        return Beneficiaires;
    }

    public void setBeneficiaires(List<Beneficiaire> Beneficiaires) {
        this.Beneficiaires = Beneficiaires;
    }*/
    @OneToMany
    private ArrayList<Population> Populations;

    public ArrayList<Population> getPopulations() {
        return Populations;
    }

    public void setPopulations(ArrayList<Population> Populations) {
        this.Populations = Populations;
    }

    private EnumSet<Beneficiaire> Beneficiaires;

    public EnumSet<Beneficiaire> getBeneficiaires() {
        return Beneficiaires;
    }

    public void setBeneficiaires(EnumSet<Beneficiaire> Beneficiaires) {
        this.Beneficiaires = Beneficiaires;
    }

    public List<StatutBeneficiaire> getLesStatutsBeneficiaire() {
        return lesStatutsBeneficiaire;
    }

    public void setLesStatutsBeneficiaire(List<StatutBeneficiaire> lesStatutsBeneficiaire) {
        this.lesStatutsBeneficiaire = lesStatutsBeneficiaire;
    }

    public List<AssietteCotisation> getLesAssiettesCotisation() {
        return lesAssiettesCotisation;
    }

    public void setLesAssiettesCotisation(List<AssietteCotisation> lesAssiettesCotisation) {
        this.lesAssiettesCotisation = lesAssiettesCotisation;
    }

    public List<TypeGarantie> getLesTypesGarantie() {
        return lesTypesGarantie;
    }

    public void setLesTypesGarantie(List<TypeGarantie> lesTypesGarantie) {
        this.lesTypesGarantie = lesTypesGarantie;
    }

    public List<Fiscalite> getLesFiscalites() {
        return lesFiscalites;
    }

    public void setLesFiscalites(List<Fiscalite> lesFiscalites) {
        this.lesFiscalites = lesFiscalites;
    }

    public TypeProduit getTypeProduit() {
        return LeTypeProduit;
    }

    public void setTypeProduit(TypeProduit LeTypeProduit) {
        this.LeTypeProduit = LeTypeProduit;
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
