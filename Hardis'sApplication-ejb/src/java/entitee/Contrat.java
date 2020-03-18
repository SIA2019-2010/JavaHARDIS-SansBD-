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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author alexisbaillieu
 */
@Entity
public class Contrat implements Serializable {

    @OneToMany(mappedBy = "leContrat")
    private List<StatutBeneficiaire> lesStatutsBeneficiaires;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date DateDebut;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date DateFin;

    private double prixMensuel;

    @ManyToOne
    private Produit leProduit;

    public double getPrixMensuel() {
        return prixMensuel;
    }

    public void setPrixMensuel(double prixMensuel) {
        this.prixMensuel = prixMensuel;
    }

    public Produit getLeProduit() {
        return leProduit;
    }

    public void setLeProduit(Produit leProduit) {
        this.leProduit = leProduit;
    }

    public Date getDateFin() {
        return DateFin;
    }

    public void setDateFin(Date DateFin) {
        this.DateFin = DateFin;
    }

    public Date getDateDebut() {
        return DateDebut;
    }

    public void setDateDebut(Date DateDebut) {
        this.DateDebut = DateDebut;
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
        if (!(object instanceof Contrat)) {
            return false;
        }
        Contrat other = (Contrat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitee.Contrat[ id=" + id + " ]";
    }

}
