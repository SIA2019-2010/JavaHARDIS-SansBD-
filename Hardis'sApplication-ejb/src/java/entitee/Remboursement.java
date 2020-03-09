/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitee;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author alexisbaillieu
 */
@Entity
public class Remboursement implements Serializable {

    @OneToOne(mappedBy = "leRemboursement")
    private Acte leActe;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private double BREffective;
    
    private double RemboursementEffectif;
    
    private EtatRemboursement EtatRemboursement;

    public EtatRemboursement getEtatRemboursement() {
        return EtatRemboursement;
    }

    public void setEtatRemboursement(EtatRemboursement EtatRemboursement) {
        this.EtatRemboursement = EtatRemboursement;
    }

    public double getRemboursementEffectif() {
        return RemboursementEffectif;
    }

    public void setRemboursementEffectif(double RemboursementEffectif) {
        this.RemboursementEffectif = RemboursementEffectif;
    }


    public double getBREffective() {
        return BREffective;
    }

    public void setBREffective(double BREffective) {
        this.BREffective = BREffective;
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
        if (!(object instanceof Remboursement)) {
            return false;
        }
        Remboursement other = (Remboursement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitee.Remboursement[ id=" + id + " ]";
    }
    
}
