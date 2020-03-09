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
public class Fiscalite implements Serializable {

    @ManyToMany(mappedBy = "lesFiscalites")
    private List<Produit> lesProduits;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private double Taxe;
    
    private double CMU;
    
    private double TCA;

    public double getTCA() {
        return TCA;
    }

    public void setTCA(double TCA) {
        this.TCA = TCA;
    }


    public double getCMU() {
        return CMU;
    }

    public void setCMU(double CMU) {
        this.CMU = CMU;
    }


    public double getTaxe() {
        return Taxe;
    }

    public void setTaxe(double Taxe) {
        this.Taxe = Taxe;
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
        if (!(object instanceof Fiscalite)) {
            return false;
        }
        Fiscalite other = (Fiscalite) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitee.Fiscalite[ id=" + id + " ]";
    }
    
}
