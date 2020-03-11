/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entitee.Responsable;
import javax.ejb.Local;

/**
 *
 * @author lixin
 */
@Local
public interface ResponsableSessionLocal {
    
    Responsable authentificationResponsable(String login, String mdp);
    
    Responsable modifiermdp(Responsable resp, String mdp);
    
}
