/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitee;

import com.sun.istack.Nullable;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author alexisbaillieu
 */
@Entity
public class PersonnePhysique implements Serializable {

    @OneToMany(mappedBy = "laPersonne")
    private List<Devis> lesDevis;

    @ManyToMany(mappedBy = "lesAyantsDroit")
    private List<Devis> lesDevisAyantDroit;

    @OneToMany(mappedBy = "laPersonnePhysique")
    private List<StatutBeneficiaire> lesStatutsBeneficiaire;

    @OneToMany(mappedBy = "laPersonnePhysique")
    private List<Acte> lesActes;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String Nom;

    private String Prenom;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date DateNaiss;

    private String NumeroSS;

    private String Adresse;

    private String Mail; //doit etre UNIQUE

    private Genre Genre;
    
    @ManyToOne
    private Population laPopulation;
    
    private String Login;
    
    private String Mdp;
    
    private int RIB;

    public int getRIB() {
        return RIB;
    }

    public void setRIB(int RIB) {
        this.RIB = RIB;
    }


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
    

    public void setLesDevisAyantDroit(List<Devis> lesDevisAyantDroit) {
        this.lesDevisAyantDroit = lesDevisAyantDroit;
    }
    
    public List<Devis> getLesDevisAyantDroit() {
        return lesDevisAyantDroit;
    }
    
     public List<Devis> getLesDevis() {
        return lesDevis;
    }

    public void setLesDevis(List<Devis> lesDevis) {
        this.lesDevis = lesDevis;
    }

    public List<StatutBeneficiaire> getLesStatutsBeneficiaire() {
        return lesStatutsBeneficiaire;
    }

    public void setLesStatutsBeneficiaire(List<StatutBeneficiaire> lesStatutsBeneficiaire) {
        this.lesStatutsBeneficiaire = lesStatutsBeneficiaire;
    }

    public List<Acte> getLesActes() {
        return lesActes;
    }

    public void setLesActes(List<Acte> lesActes) {
        this.lesActes = lesActes;
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
