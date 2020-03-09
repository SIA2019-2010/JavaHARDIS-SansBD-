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

/**
 *
 * @author alexisbaillieu
 */
@Entity
public class Acte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date DateDebut;
    
    private Date DateFin;
    
    private char LibelleActe;
        
    private double Depense;
    
    private char Lieu;
    
    
    @ManyToOne
    private Praticien lePraticien;
    
    @OneToOne
    private LibelleActe leLibelleActe;
    
    @OneToOne
    private Remboursement leRemboursement;
    
    @ManyToOne
    private PersonnePhysique laPersonnePhysique;

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

    

    public char getLieu() {
        return Lieu;
    }

    public void setLieu(char Lieu) {
        this.Lieu = Lieu;
    }

    public double getDepense() {
        return Depense;
    }

    public void setDepense(double Depense) {
        this.Depense = Depense;
    }


    public char getLibelleActe() {
        return LibelleActe;
    }

    public void setLibelleActe(char LibelleActe) {
        this.LibelleActe = LibelleActe;
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
