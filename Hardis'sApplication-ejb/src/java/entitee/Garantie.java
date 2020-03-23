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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author alexisbaillieu
 */
@Entity
public class Garantie implements Serializable {

    @OneToMany(mappedBy = "laGarantie")
    private List<LibelleActe> lesLibelleActes;

    @OneToMany(mappedBy = "laGarantie")
    private List<PriseEnCharge> lesPriseEnCharges;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String LibelleGarantie;

    @ManyToOne
    private BaseRemboursementSecu laBaseRemboursementSeco;

    @ManyToMany
    private List<TypeGarantie> lesTypesGarantie;

    public List<TypeGarantie> getLesTypesGarantie() {
        return lesTypesGarantie;
    }

    public void setLesTypesGarantie(List<TypeGarantie> lesTypesGarantie) {
        this.lesTypesGarantie = lesTypesGarantie;
    }

    public BaseRemboursementSecu getLaBaseRemboursementSeco() {
        return laBaseRemboursementSeco;
    }

    public void setLaBaseRemboursementSeco(BaseRemboursementSecu laBaseRemboursementSeco) {
        this.laBaseRemboursementSeco = laBaseRemboursementSeco;
    }

    public String getLibelleGarantie() {
        return LibelleGarantie;
    }

    public void setLibelleGarantie(String LibelleGarantie) {
        this.LibelleGarantie = LibelleGarantie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<LibelleActe> getLesLibelleActes() {
        return lesLibelleActes;
    }

    public void setLesLibelleActes(List<LibelleActe> lesLibelleActes) {
        this.lesLibelleActes = lesLibelleActes;
    }

    public List<PriseEnCharge> getLesPriseEnCharges() {
        return lesPriseEnCharges;
    }

    public void setLesPriseEnCharges(List<PriseEnCharge> lesPriseEnCharges) {
        this.lesPriseEnCharges = lesPriseEnCharges;
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
        if (!(object instanceof Garantie)) {
            return false;
        }
        Garantie other = (Garantie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitee.Garantie[ id=" + id + " ]";
    }

}
