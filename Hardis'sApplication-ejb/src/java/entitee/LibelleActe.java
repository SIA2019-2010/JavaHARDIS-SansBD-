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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author alexisbaillieu
 */
@Entity
public class LibelleActe implements Serializable {

    @OneToOne(mappedBy = "leLibelleActe")
    private Acte leActe;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String DesignationActe;
    
    @ManyToOne
    private Garantie laGarantie;

    public Garantie getLaGarantie() {
        return laGarantie;
    }

    public void setLaGarantie(Garantie laGarantie) {
        this.laGarantie = laGarantie;
    }

    
    

    public String getDesignationActe() {
        return DesignationActe;
    }

    public void setDesignationActe(String DesignationActe) {
        this.DesignationActe = DesignationActe;
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
        if (!(object instanceof LibelleActe)) {
            return false;
        }
        LibelleActe other = (LibelleActe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitee.LibelleActe[ id=" + id + " ]";
    }
    
}
