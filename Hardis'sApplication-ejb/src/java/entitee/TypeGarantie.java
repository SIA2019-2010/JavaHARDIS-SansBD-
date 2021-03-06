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

/**
 *
 * @author alexisbaillieu
 */
@Entity
public class TypeGarantie implements Serializable {

    @ManyToMany(mappedBy = "lesTypesGarantie")
    private List<Produit> lesProduits;

    @ManyToMany(mappedBy = "lesTypesGarantie")
    private List<Garantie> lesGaranties;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String TypeGarantie;

    public String getTypeGarantie() {
        return TypeGarantie;
    }

    public void setTypeGarantie(String TypeGarantie) {
        this.TypeGarantie = TypeGarantie;
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

    public List<Garantie> getLesGaranties() {
        return lesGaranties;
    }

    public void setLesGaranties(List<Garantie> lesGaranties) {
        this.lesGaranties = lesGaranties;
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
        if (!(object instanceof TypeGarantie)) {
            return false;
        }
        TypeGarantie other = (TypeGarantie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitee.TypeGarantie[ id=" + id + " ]";
    }
    
}
