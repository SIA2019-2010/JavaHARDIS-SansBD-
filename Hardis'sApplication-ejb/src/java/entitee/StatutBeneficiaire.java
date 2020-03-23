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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author alexisbaillieu
 */
@Entity
public class StatutBeneficiaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDebutValidite;
       
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFinValidite;
    
    @ManyToOne
    private Beneficiaire laBeneficiaire;

    public Beneficiaire getLaBeneficiaire() {
        return laBeneficiaire;
    }

    public void setLaBeneficiaire(Beneficiaire laBeneficiaire) {
        this.laBeneficiaire = laBeneficiaire;
    }
    
    @ManyToOne
   private PersonnePhysique laPersonnePhysique;
    
    @ManyToOne
   private Contrat leContrat;

    public Date getDateFinValidite() {
        return dateFinValidite;
    }

    public void setDateFinValidite(Date dateFinValidite) {
        this.dateFinValidite = dateFinValidite;
    }


    public Date getDateDebutValidite() {
        return dateDebutValidite;
    }

    public void setDateDebutValidite(Date dateDebutValidite) {
        this.dateDebutValidite = dateDebutValidite;
    }



    public Contrat getLeContrat() {
        return leContrat;
    }

    public void setLeContrat(Contrat leContrat) {
        this.leContrat = leContrat;
    }


    public PersonnePhysique getLaPersonnePhysique() {
        return laPersonnePhysique;
    }

    public void setLaPersonnePhysique(PersonnePhysique laPersonnePhysique) {
        this.laPersonnePhysique = laPersonnePhysique;
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
        if (!(object instanceof StatutBeneficiaire)) {
            return false;
        }
        StatutBeneficiaire other = (StatutBeneficiaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitee.StatutBeneficiare[ id=" + id + " ]";
    }
    
}
