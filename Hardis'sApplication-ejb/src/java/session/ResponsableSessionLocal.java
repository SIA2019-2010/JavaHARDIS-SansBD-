/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entitee.PersonneMorale;
import entitee.Responsable;
import entitee.StatutBeneficiaire;
import java.util.List;
import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author lixin
 */
@Local
public interface ResponsableSessionLocal {
    
    List<Object> authentificationResponsable(String login, String mdp, HttpServletRequest request);
    
    List<Object> modifiermdp(Responsable resp, String OMDP, String NMDP, String RMDP);
    
    List<StatutBeneficiaire> rechercherStatutBeneficiaire(PersonneMorale persmo);
    
}
