/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entitee.Responsable;
import facade.ResponsableFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author lixin
 */
@Stateless
public class ResponsableSession implements ResponsableSessionLocal {

    @EJB
    private ResponsableFacadeLocal responsableFacade;
    
    @Override
    public Responsable authentificationResponsable(String login, String mdp) {
        return responsableFacade.authentificationResponsable(login, mdp);
    }
    
    @Override
    public Responsable modifiermdp(Responsable resp, String mdp){
        return responsableFacade.modifierMdp(resp, mdp);
    }
    
}
