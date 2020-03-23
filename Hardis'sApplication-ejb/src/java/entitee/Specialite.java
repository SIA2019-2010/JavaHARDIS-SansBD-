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
import javax.persistence.OneToMany;

/**
 *
 * @author 6170137
 */
@Entity
public class Specialite implements Serializable {

    @OneToMany(mappedBy = "laSpecialite")
    private List<Praticien> lesPraticiens;
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String LibelleSpecialite;

    public String getLibelleSpecialite() {
        return LibelleSpecialite;
    }

    public void setLibelleSpecialite(String LibelleSpecialite) {
        this.LibelleSpecialite = LibelleSpecialite;
    }

    public List<Praticien> getLesPraticiens() {
        return lesPraticiens;
    }

    public void setLesPraticiens(List<Praticien> lesPraticiens) {
        this.lesPraticiens = lesPraticiens;
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
        if (!(object instanceof Specialite)) {
            return false;
        }
        Specialite other = (Specialite) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitee.Specialite[ id=" + id + " ]";
    }
    
}
