/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitee;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author alexisbaillieu
 */
@Entity
public class Acte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date DateDebut;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date DateFin;

    private double Depense;

    private String Lieu;
    
    

    @ManyToOne
    private Praticien lePraticien;

    @ManyToOne
    private LibelleActe leLibelleActe;

    @OneToOne(mappedBy = "leActe")
    private Remboursement leRemboursement;

    @ManyToOne
    private PersonnePhysique laPersonnePhysique;
    
    @ManyToOne
    private PlafondMensuelSecuSociale lePlafond;

    public PlafondMensuelSecuSociale getLePlafond() {
        return lePlafond;
    }

    public void setLePlafond(PlafondMensuelSecuSociale lePlafond) {
        this.lePlafond = lePlafond;
    }

    

    public PersonnePhysique getLaPersonnePhysique() {
        return laPersonnePhysique;
    }

    public void setLaPersonnePhysique(PersonnePhysique laPersonnePhysique) {
        this.laPersonnePhysique = laPersonnePhysique;
    }

    public Remboursement getLeRemboursement() {
        return leRemboursement;
    }

    public void setLeRemboursement(Remboursement leRemboursement) {
        this.leRemboursement = leRemboursement;
    }

    public LibelleActe getLeLibelleActe() {
        return leLibelleActe;
    }

    public void setLeLibelleActe(LibelleActe leLibelleActe) {
        this.leLibelleActe = leLibelleActe;
    }

    public Praticien getLePraticien() {
        return lePraticien;
    }

    public void setLePraticien(Praticien lePraticien) {
        this.lePraticien = lePraticien;
    }

    public String getLieu() {
        return Lieu;
    }

    public void setLieu(String Lieu) {
        this.Lieu = Lieu;
    }

    public double getDepense() {
        return Depense;
    }

    public void setDepense(double Depense) {
        this.Depense = Depense;
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
        if (!(object instanceof Acte)) {
            return false;
        }
        Acte other = (Acte) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitee.Acte[ id=" + id + " ]";
    }

}
