/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entitee.Gestionnaire;
import java.util.List;
import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author lixin
 */
@Local
public interface GestionnaireSessionLocal {
    
    List<Object> authentificationGestionnaire(String login, String mdp, HttpServletRequest request);
    
    Gestionnaire modifiermdp(Gestionnaire resp, String mdp);
    
}
