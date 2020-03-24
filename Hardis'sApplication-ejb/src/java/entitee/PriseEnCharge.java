/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitee;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author alexisbaillieu
 */
@Entity
public class PriseEnCharge implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double TauxRempoursement;

    private String BaseRemboursement;

    private Unite Unite;

    @ManyToOne
    private Produit leProduit;

    @ManyToOne
    private Garantie laGarantie;
    
    private Boolean AdherentCAS;

    public boolean isAdherentCAS() {
        return AdherentCAS;
    }

    public void setAdherentCAS(boolean AdherentCAS) {
        this.AdherentCAS = AdherentCAS;
    }


    public Produit getLeProduit() {
        return leProduit;
    }

    public void setLeProduit(Produit leProduit) {
        this.leProduit = leProduit;
    }

    public Garantie getLaGarantie() {
        return laGarantie;
    }

    public void setLaGarantie(Garantie laGarantie) {
        this.laGarantie = laGarantie;
    }

    public Unite getUnite() {
        return Unite;
    }

    public void setUnite(Unite Unite) {
        this.Unite = Unite;
    }

    public String getBaseRemboursement() {
        return BaseRemboursement;
    }

    public void setBaseRemboursement(String BaseRemboursement) {
        this.BaseRemboursement = BaseRemboursement;
    }

    public double getTauxRempoursement() {
        return TauxRempoursement;
    }

    public void setTauxRempoursement(double TauxRempoursement) {
        this.TauxRempoursement = TauxRempoursement;
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
        if (!(object instanceof PriseEnCharge)) {
            return false;
        }
        PriseEnCharge other = (PriseEnCharge) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitee.PriseEnCharge[ id=" + id + " ]";
    }

}
