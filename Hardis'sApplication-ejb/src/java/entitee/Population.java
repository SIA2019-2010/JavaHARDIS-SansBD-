/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitee;

import java.io.Serializable;
import java.util.ArrayList;
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
public class Population implements Serializable {

    @OneToMany(mappedBy = "laPopulation")
    private ArrayList<PersonnePhysique> lesPersonnePhysiques;

    @ManyToMany(mappedBy = "lesPopulations")
    private ArrayList<Produit> lesProduits;
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String LibellePopulation;

    public String getLibellePopulation() {
        return LibellePopulation;
    }

    public void setLibellePopulation(String LibellePopulation) {
        this.LibellePopulation = LibellePopulation;
    }

    public ArrayList<PersonnePhysique> getLesPersonnePhysiques() {
        return lesPersonnePhysiques;
    }

    public void setLesPersonnePhysiques (ArrayList<PersonnePhysique> lesPersonnePhysiques) {
        this.lesPersonnePhysiques = lesPersonnePhysiques;
    }

    public ArrayList<Produit> getLesProduits() {
        return lesProduits;
    }

    public void setLesProduits(ArrayList<Produit> lesProduits) {
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
        if (!(object instanceof Population)) {
            return false;
        }
        Population other = (Population) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitee.Population[ id=" + id + " ]";
    }
    
}
