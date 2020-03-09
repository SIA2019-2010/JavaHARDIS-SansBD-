/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitee;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author alexisbaillieu
 */
@Entity
public class Praticien implements Serializable {

    @OneToMany(mappedBy = "lePraticien")
    private ArrayList<Acte> lesActes;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String Nom;

    private String Prenom;

    private String Adresse;

    private Secteur Secteur;
    
    @ManyToOne
    private Specialite laSpecialite;

    public Specialite getLaSpecialite() {
        return laSpecialite;
    }

    public void setLaSpecialite(Specialite laSpecialite) {
        this.laSpecialite = laSpecialite;
    }

    public Secteur getSecteur() {
        return Secteur;
    }

    public void setSecteur(Secteur Secteur) {
        this.Secteur = Secteur;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
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

    public ArrayList<Acte> getLesActes() {
        return lesActes;
    }

    public void setLesActes(ArrayList<Acte> lesActes) {
        this.lesActes = lesActes;
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
        if (!(object instanceof Praticien)) {
            return false;
        }
        Praticien other = (Praticien) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitee.Practitien[ id=" + id + " ]";
    }

}
