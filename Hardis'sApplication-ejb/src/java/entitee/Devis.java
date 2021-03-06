/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitee;

import java.io.Serializable;
import java.util.List;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author alexisbaillieu
 */
@Entity
public class Devis implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PersonnePhysique laPersonne;

    @ManyToMany
    private List<PersonnePhysique> lesAyantsDroit;

    @ManyToOne
    private Produit leProduit;

    private double prix;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDevis;
    
    
    public PersonnePhysique getLaPersonne() {
        return laPersonne;
    }

    public void setLaPersonne(PersonnePhysique laPersonne) {
        this.laPersonne = laPersonne;
    }

    public Date getDateDevis() {
        return dateDevis;
    }

    public void setDateDevis(Date dateDevis) {
        this.dateDevis = dateDevis;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Produit getLeProduit() {
        return leProduit;
    }

    public void setLeProduit(Produit leProduit) {
        this.leProduit = leProduit;
    }

    public List<PersonnePhysique> getLesAyantsDroit() {
        return lesAyantsDroit;
    }

    public void setLesAyantsDroit(List<PersonnePhysique> lesAyantsDroit) {
        this.lesAyantsDroit = lesAyantsDroit;
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
        if (!(object instanceof Devis)) {
            return false;
        }
        Devis other = (Devis) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitee.Devis[ id=" + id + " ]";
    }

}
