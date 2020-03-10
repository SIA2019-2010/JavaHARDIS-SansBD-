/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitee;

import com.sun.istack.Nullable;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author alexisbaillieu
 */
@Entity
public class PersonnePhysique implements Serializable {

    @OneToMany(mappedBy = "laPersonnePhysique")
    private ArrayList<Devis> lesDevis;

    @OneToMany(mappedBy = "laPersonnePhysique")
    private ArrayList<StatutBeneficiaire> lesStatutsBeneficiaire;

    @OneToMany(mappedBy = "laPersonnePhysique")
    private ArrayList<Acte> lesActes;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    private String Nom;

    private String Prenom;

    private Date DateNaiss;

    private String NumeroSS;

    private String Adresse;

    private String Mail;

    private Genre Genre;

    private boolean AdherentCAS;
    
    @ManyToOne
    private Population laPopulation;
    
    private String Login;
    
    private String Mdp;
    

    public String getMdp() {
        return Mdp;
    }

    public void setMdp(String Mdp) {
        this.Mdp = Mdp;
    }


    public String getLogin() {
        return Login;
    }

    public void setLogin(String Login) {
        this.Login = Login;
    }


   

    public Population getLaPopulation() {
        return laPopulation;
    }

    public void setLaPopulation(Population laPopulation) {
        this.laPopulation = laPopulation;
    }

    public ArrayList<Devis> getLesDevis() {
        return lesDevis;
    }

    public void setLesDevis(ArrayList<Devis> lesDevis) {
        this.lesDevis = lesDevis;
    }

    public ArrayList<StatutBeneficiaire> getLesStatutsBeneficiaire() {
        return lesStatutsBeneficiaire;
    }

    public void setLesStatutsBeneficiaire(ArrayList<StatutBeneficiaire> lesStatutsBeneficiaire) {
        this.lesStatutsBeneficiaire = lesStatutsBeneficiaire;
    }

    public ArrayList<Acte> getLesActes() {
        return lesActes;
    }

    public void setLesActes(ArrayList<Acte> lesActes) {
        this.lesActes = lesActes;
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

    public String getMail() {
        return Mail;
    }

    public void setMail(String Mail) {
        this.Mail = Mail;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public String getNumeroSS() {
        return NumeroSS;
    }

    public void setNumeroSS(String NumeroSS) {
        this.NumeroSS = NumeroSS;
    }

    public Date getDateNaiss() {
        return DateNaiss;
    }

    public void setDateNaiss(Date DateNaiss) {
        this.DateNaiss = DateNaiss;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
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
