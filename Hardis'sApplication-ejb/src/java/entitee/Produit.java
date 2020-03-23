/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitee;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.List;
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
    private List<Devis> lesDevis;

    @OneToMany(mappedBy = "leProduit")
    private List<Contrat> lesContrats;

    @OneToMany(mappedBy = "leProduit")
    private List<PriseEnCharge> lesPriseEnCharges;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String NomProduit;
    
    private Double PrixBase;

    public Double getPrixBase() {
        return PrixBase;
    }

    public void setPrixBase(Double PrixBase) {
        this.PrixBase = PrixBase;
    }

    
    @ManyToOne
    private TypeProduit leTypeProduit;

    @ManyToMany
    private List<TypeGarantie> lesTypesGarantie;

    @ManyToMany
    private List<Population> lesPopulations;

    @ManyToOne
    private PersonneMorale laPersonneMorale;
    
    @ManyToMany
    private List<Beneficiaire> lesBeneficiaires;
    
    @ManyToOne
    private Beneficiaire laBeneficiaire;
    
    @ManyToOne
    private Domaine leDomaine;
    
    @ManyToOne
    private Fiscalite laFiscalite;

    public Fiscalite getLaFiscalite() {
        return laFiscalite;
    }

    public void setLaFiscalite(Fiscalite laFiscalite) {
        this.laFiscalite = laFiscalite;
    }


    public Domaine getLeDomaine() {
        return leDomaine;
    }

    public void setLeDomaine(Domaine leDomaine) {
        this.leDomaine = leDomaine;
    }


    public List<Population> getLesPopulations() {
        return lesPopulations;
    }

    public void setLesPopulations(List<Population> lesPopulations) {
        this.lesPopulations = lesPopulations;
    }

    public List<Beneficiaire> getLesBeneficiaires() {
        return lesBeneficiaires;
    }

    public void setLesBeneficiaires(List<Beneficiaire> lesBeneficiaires) {
        this.lesBeneficiaires = lesBeneficiaires;
    }

    public Beneficiaire getLaBeneficiaire() {
        return laBeneficiaire;
    }

    public void setLaBeneficiaire(Beneficiaire laBeneficiaire) {
        this.laBeneficiaire = laBeneficiaire;
    }
    
    

    public List<TypeGarantie> getLesTypesGarantie() {
        return lesTypesGarantie;
    }

    public void setLesTypesGarantie(List<TypeGarantie> lesTypesGarantie) {
        this.lesTypesGarantie = lesTypesGarantie;
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

    public List<Devis> getLesDevis() {
        return lesDevis;
    }

    public void setLesDevis(List<Devis> lesDevis) {
        this.lesDevis = lesDevis;
    }

    public List<Contrat> getLesContrats() {
        return lesContrats;
    }

    public void setLesContrats(List<Contrat> lesContrats) {
        this.lesContrats = lesContrats;
    }

    public List<PriseEnCharge> getLesPriseEnCharges() {
        return lesPriseEnCharges;
    }

    public void setLesPriseEnCharges(List<PriseEnCharge> lesPriseEnCharges) {
        this.lesPriseEnCharges = lesPriseEnCharges;
    }

    public PersonneMorale getLaPersonneMorale() {
        return laPersonneMorale;
    }

    public void setLaPersonneMorale(PersonneMorale laPersonneMorale) {
        this.laPersonneMorale = laPersonneMorale;
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
