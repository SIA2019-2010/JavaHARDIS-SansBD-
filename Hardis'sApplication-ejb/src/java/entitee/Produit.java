/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitee;

import java.io.Serializable;
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
    
    private char NomProduit;
    
    private TypeProduit TypeProduit;
   
    @ManyToMany
    private List<Fiscalite> lesFiscalites;

    @ManyToMany
    private List<TypeGarantie> lesTypesGarantie;

    @ManyToMany
    private List<AssietteCotisation> lesAssiettesCotisation;
    
    @ManyToMany
    private List<StatutBeneficiaire> lesStatutsBeneficiaire;
    
    private List<Beneficiaire> Beneficiaires;
    
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
        return TypeProduit;
    }

    public void setTypeProduit(TypeProduit TypeProduit) {
        this.TypeProduit = TypeProduit;
    }


    public char getNomProduit() {
        return NomProduit;
    }

    public void setNomProduit(char NomProduit) {
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
