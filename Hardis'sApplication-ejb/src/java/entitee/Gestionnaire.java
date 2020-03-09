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
    
    private char Nom;
    
    private char Prenom;
    
    private char Adresse;
    
    private char Telephone;
    
    private char Login;
    
    private char Mdp;
    
    @ManyToOne
    private Domaine leDomaine;
    
    

    public Domaine getLeDomaine() {
        return leDomaine;
    }

    public void setLeDomaine(Domaine leDomaine) {
        this.leDomaine = leDomaine;
    }

    
    public char getMdp(){
        return Mdp;
    }
    
 public void setMdp(char Mdp) {
        this.Mdp = Mdp;
    }
    
    public char getLogin(){
        return Login;
    }
    
 public void setLogin(char Login) {
        this.Login = Login;
    }
 
    public char getTelephone(){
        return Telephone;
    }
    
 public void setTelephone(char Telephone) {
        this.Telephone = Telephone;
    }
 
    public char getAdresse() {
        return Adresse;
    }

    public void setAdresse(char Adresse) {
        this.Adresse = Adresse;
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
