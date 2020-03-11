/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitee;

import java.io.Serializable;
import java.util.List;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author alexisbaillieu
 */
@Entity
public class AssietteCotisation implements Serializable {

    @ManyToMany(mappedBy = "lesAssiettesCotisation")
    private List<Produit> lesProduits;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String LibelleAssiette;

    private double Assiette;

    public double getAssiette() {
        return Assiette;
    }

    public void setAssiette(double Assiette) {
        this.Assiette = Assiette;
    }

    public String getLibelleAssiette() {
        return LibelleAssiette;
    }

    public void setLibelleAssiette(String LibelleAssiette) {
        this.LibelleAssiette = LibelleAssiette;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Produit> getLesProduits() {
        return lesProduits;
    }

    public void setLesProduits(List<Produit> lesProduits) {
        this.lesProduits = lesProduits;
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
        if (!(object instanceof AssietteCotisation)) {
            return false;
        }
        AssietteCotisation other = (AssietteCotisation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitee.AssietteCotisation[ id=" + id + " ]";
    }

}
