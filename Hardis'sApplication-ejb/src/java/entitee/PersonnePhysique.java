/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitee;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

/**
 *
 * @author alexisbaillieu
 */
@Entity
@Inheritance
(strategy = InheritanceType.TABLE_PER_CLASS)
public class PersonnePhysique implements Serializable {

    @OneToMany(mappedBy = "laPersonnePhysique")
    private List<StatutBeneficiaire> lesStatutsBeneficiaire;

    @OneToMany(mappedBy = "laPersonnePhysique")
    private List<Acte> lesActes;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    private Long id;
    
    private char Nom;

    private char Prenom;
    
    private Date DateNaiss;
    
    private char NumeroSS;
        
    private char Adresse;
        
    private char Mail;
    
    private Genre Genre;
    
    private boolean AdherentCAS;
    
    private Population Population;
    
    

    public Population getPopulation() {
        return Population;
    }

    public void setPopulation(Population Population) {
        this.Population = Population;
    }


    public boolean isAdherentCAS() {
        return AdherentCAS;
    }

    public void setAdherentCAS(boolean AdherentCAS) {
        this.AdherentCAS = AdherentCAS;
    }


    public Genre getGenre() {
        return Genre;
    }

    public void setGenre(Genre Genre) {
        this.Genre = Genre;
    }


    public char getMail() {
        return Mail;
    }

    public void setMail(char Mail) {
        this.Mail = Mail;
    }


    public char getAdresse() {
        return Adresse;
    }

    public void setAdresse(char Adresse) {
        this.Adresse = Adresse;
    }
    

    public char getNumeroSS() {
        return NumeroSS;
    }

    public void setNumeroSS(char NumeroSS) {
        this.NumeroSS = NumeroSS;
    }


    public Date getDateNaiss() {
        return DateNaiss;
    }

    public void setDateNaiss(Date DateNaiss) {
        this.DateNaiss = DateNaiss;
    }


    public char getPrenom() {
        return Prenom;
    }

    public void setPrenom(char Prenom) {
        this.Prenom = Prenom;
    }

    public char getNom() {
        return Nom;
    }

    public void setNom(char Nom) {
        this.Nom = Nom;
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
        if (!(object instanceof PersonnePhysique)) {
            return false;
        }
        PersonnePhysique other = (PersonnePhysique) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitee.PersonnePhysique[ id=" + id + " ]";
    }
    
}
