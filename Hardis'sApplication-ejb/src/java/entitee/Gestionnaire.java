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

/**
 *
 * @author alexisbaillieu
 */
@Entity
public class Gestionnaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    private Long id;
    
    private String Nom;
    
    private String Prenom;
    
    private String Adresse;
    
    private String Telephone;
    
    private String Login;
    
    private String Mdp;
    
    @ManyToOne
    private Domaine leDomaine;
    
    

    public Domaine getLeDomaine() {
        return leDomaine;
    }

    public void setLeDomaine(Domaine leDomaine) {
        this.leDomaine = leDomaine;
    }

    
    public String getMdp(){
        return Mdp;
    }
    
 public void setMdp(String Mdp) {
        this.Mdp = Mdp;
    }
    
    public String getLogin(){
        return Login;
    }
    
 public void setLogin(String Login) {
        this.Login = Login;
    }
 
    public String getTelephone(){
        return Telephone;
    }
    
 public void setTelephone(String Telephone) {
        this.Telephone = Telephone;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gestionnaire)) {
            return false;
        }
        Gestionnaire other = (Gestionnaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitee.Gestionnaire[ id=" + id + " ]";
    }
    
}
