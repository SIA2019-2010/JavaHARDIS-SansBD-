package servlet;
import entitee.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.GestionnaireSessionLocal;
import session.PubliqueSessionLocal;
import session.ResponsableSessionLocal;
import session.AffilieSessionLocal;
/**
 *
 * @author lixin
 */
@WebServlet(name = "Page", urlPatterns = {"/Page"})
public class Page extends HttpServlet {

    @EJB
    private ResponsableSessionLocal responsableSession;
    
    @EJB
    private AffilieSessionLocal affilieSession;
    
    @EJB
    private PubliqueSessionLocal publiqueSession;
    
    @EJB
    private GestionnaireSessionLocal gestionnaireSession;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String jspClient = null;
        String message = null;
        String act=request.getParameter("action");
        HttpSession session=request.getSession(false);
        Gestionnaire sessiongestionnaire=null;
        PersonnePhysique sessionaffilie=null;
        Responsable sessionresponsable=null;
        List<Object> Response;
        if(session!=null){
            sessiongestionnaire=(Gestionnaire)session.getAttribute("sessionresponsable");
            sessionaffilie=(PersonnePhysique)session.getAttribute("sessionphysique");
            sessionresponsable=(Responsable)session.getAttribute("sessionresponsable");
        }
        
        if(agentSession.RechercherAgence("AdresseAgence1")==null){
            agentSession.CreerAgence("AdresseAgence1");
        }
        if(agentSession.RechercherAgent("login")==null){
            agentSession.CreerAgent("NomAgent1", "PreomAgenT1", "login", "mdp", agentSession.RechercherAgence("AdresseAgence1"));
        }
        if(agentSession.RechercherTypeVehicule("SUV")==null){
            agentSession.CreerTypeVehicule("SUV");
        }
        if(agentSession.RechercherVehicule("TestVehicul")==null){
            agentSession.CreerVehicule("TestVehicul", "MarqueTestVehicule", "ModeleTestVehicule", "CouleurTestVehicule", 2000, 40.5, agentSession.RechercherTypeVehicule("SUV"));
        }
        Vehicule vtest=agentSession.RechercherVehicule("TestVehicul");
        if(vtest.getRevisions().isEmpty()){
            agentSession.CreerRevision(vtest, new Date(System.currentTimeMillis() - (21 * 24 * 60 * 60 * 1000)));
            agentSession.ModifierRevision(vtest);
        }
        //agentSession.CreerAgence("Adresse1");
        //long idAg=(long)1;
        //Agence ag = agentSession.RechercherAgence(idAg);
        //agentSession.CreerAgent("Nom", "Prenom", "login", "mdp", ag);
        //clientSession.creerClient("Nom1", "Prenom1");
        //long idCl=(long)3;
        if(clientSession.RechercherCompte("login")==null){
            clientSession.CreerCompte("login", "mdp", "NomClient1", "PrenomClient1", "AdresseClient1", "0612345678");
        }
        
        if((sessioncompte!=null&&sessionagent!=null)||(sessioncompte==null&&sessionagent==null&&act!=null&&!act.equals("")&&!act.equals("AgentConnexion")&&!act.equals("ClientConnexion")&&!act.equals("AgentAuthen")&&!act.equals("ClientAuthen")&&!act.equals("Deconnexion")&&!act.equals("AfficherCreerCompte")&&!act.equals("CreerCompteClient"))){
            jspClient="/ErreurSession.jsp";
            message="Erreur de session ! Veuillez vous reconnecter !";
            if(act.substring(0, 5).equals("Agent")) request.setAttribute("typeConnexion","AgentConnexion");
            else request.setAttribute("typeConnexion","ClientConnexion");
        }
        else if(null==act){
            jspClient="/SIA.jsp";
            message="Bienvenue";
        }
        else switch (act) {  
            
            case "vide":
                jspClient="/SIA.jsp";
                message="Bienvenue";
                break;
                
            case "AgentConnexion" :
            case "ClientConnexion" :
                if (sessioncompte!=null){
                    jspClient="/ClientMenu.jsp";
                    message="";
                }
                else if(sessionagent!=null){
                    jspClient="/AgentMenu.jsp";
                    message="";
                }
                else{
                    jspClient="/Connexion.jsp";
                    message="Affichage page connexion";
                    request.setAttribute("typeConnexion",act);
                }
                break;
            
            case "Deconnexion" :
                session = request.getSession(false);
                session.invalidate();
                request.setAttribute("typeConnexion",request.getParameter("typeConnexion"));
                jspClient="/Connexion.jsp";
                message="Vous êtes déconnecté";
                break;
                
            case "AgentAuthen" :
                String Agentlogin=request.getParameter("Login");
                String Agentmdp=request.getParameter("MDP");
                if(Agentlogin.trim().isEmpty()||Agentmdp.trim().isEmpty()){
                    message="Il manque de champs";
                    request.setAttribute("typeConnexion","AgentConnexion");
                    jspClient="/Connexion.jsp";
                }
                else{
                    sessionagent=agentSession.RechercherAgent(Agentlogin, Agentmdp);
                    if(sessionagent==null){
                        request.setAttribute("typeConnexion","AgentConnexion");
                        message="Erreur :login ou mdp";
                        jspClient="/Connexion.jsp";
                    }
                    else{
                        jspClient="/AgentMenu.jsp";
                        message="Connexion réussie";
                        session = request.getSession(true);
                        session.setAttribute("sessionagent",sessionagent);
                    }
                }
                break;
                
            case "ClientAuthen" :
                String Clientlogin=request.getParameter("Login");
                String Clientmdp=request.getParameter("MDP");
                if(Clientlogin.trim().isEmpty()||Clientmdp.trim().isEmpty()){
                    message="Vous n'avez pas rempli tous les champs";
                    request.setAttribute("typeConnexion","ClientConnexion");
                    jspClient="/Connexion.jsp";
                }
                else{
                    sessioncompte=clientSession.RechercherCompte(Clientlogin, Clientmdp);
                    if(sessioncompte==null){
                        request.setAttribute("typeConnexion","ClientConnexion");
                        message=" Le login ou le mot de passe est incorrect ";
                        jspClient="/Connexion.jsp";
                    }
                    else{
                        jspClient="/ClientMenu.jsp";
                        message="Connexion réussie";
                        session = request.getSession(true);
                        session.setAttribute("sessioncompte",sessioncompte);
                    }
                }
                break;
                
            default:
                jspClient="/"+act+".jsp";
                message="";
        }
            
            
        RequestDispatcher Rd;
        Rd=getServletContext().getRequestDispatcher(jspClient);
        request.setAttribute("message",message);
        Rd.forward(request,response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
