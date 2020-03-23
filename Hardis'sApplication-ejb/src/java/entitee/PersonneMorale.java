/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitee;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class PersonneMorale implements Serializable {

    @OneToMany(mappedBy = "laPersonneMorale")
    private List<Produit> lesProduits;

    @OneToMany(mappedBy = "laPersonneMorale")
    private List<Responsable> lesResponsables;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String SIRET;

    private String RaisonSociale;

    private String Adresse;

    @ManyToOne
    private Activite laActivite;

    public Activite getLaActivite() {
        return laActivite;
    }

    public void setLaActivite(Activite laActivite) {
        this.laActivite = laActivite;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public String getRaisonSociale() {
        return RaisonSociale;
    }

    public void setRaisonSociale(String RaisonSociale) {
        this.RaisonSociale = RaisonSociale;
    }

    public String getSIRET() {
        return SIRET;
    }

    public void setSIRET(String SIRET) {
        this.SIRET = SIRET;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Produit> getLesProduits() {
        return lesProduits;
    }

    public void setLesProduits(List<Produit> lesProduits) {
        this.lesProduits = lesProduits;
    }

    public List<Responsable> getLesResponsables() {
        return lesResponsables;
    }

    public void setLesResponsables(List<Responsable> lesResponsables) {
        this.lesResponsables = lesResponsables;
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
        if (!(object instanceof PersonneMorale)) {
            return false;
        }
        PersonneMorale other = (PersonneMorale) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitee.PersonneMorale[ id=" + id + " ]";
    }

}
