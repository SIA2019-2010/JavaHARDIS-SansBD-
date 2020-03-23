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
 * @author 6170137
 */
@Entity
public class Beneficiaire implements Serializable {

    @OneToMany(mappedBy = "laBeneficiaire")
    private List<StatutBeneficiaire> lesStatutBeneficiaire;
    
    @OneToMany(mappedBy = "laBeneficiaire")
    private List<Produit> lesProduitsAss;

    @ManyToMany(mappedBy = "lesBeneficiaires")
    private List<Produit> lesProduitsBene;
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String LibelleBeneficiaire;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<StatutBeneficiaire> getLesStatutBeneficiaire() {
        return lesStatutBeneficiaire;
    }

    public void setLesStatutBeneficiaire(List<StatutBeneficiaire> lesStatutBeneficiaire) {
        this.lesStatutBeneficiaire = lesStatutBeneficiaire;
    }

    public List<Produit> getLesProduitsAss() {
        return lesProduitsAss;
    }

    public void setLesProduitsAss(List<Produit> lesProduitsAss) {
        this.lesProduitsAss = lesProduitsAss;
    }

    public List<Produit> getLesProduitsBene() {
        return lesProduitsBene;
    }

    public void setLesProduitsBene(List<Produit> lesProduitsBene) {
        this.lesProduitsBene = lesProduitsBene;
    }

    public String getLibelleBeneficiaire() {
        return LibelleBeneficiaire;
    }

    public void setLibelleBeneficiaire(String LibelleBeneficiaire) {
        this.LibelleBeneficiaire = LibelleBeneficiaire;
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
        if (!(object instanceof Beneficiaire)) {
            return false;
        }
        Beneficiaire other = (Beneficiaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitee.Beneficiaire[ id=" + id + " ]";
    }
    
}
