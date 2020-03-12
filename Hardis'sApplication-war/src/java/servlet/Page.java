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
        boolean sessionpublique=false;
        List<Object> Response;
        int count=0;
        if(session!=null){
            sessiongestionnaire=(Gestionnaire)session.getAttribute("sessionresponsable");
            sessionaffilie=(PersonnePhysique)session.getAttribute("sessionphysique");
            sessionresponsable=(Responsable)session.getAttribute("sessionresponsable");
            count=(sessiongestionnaire==null?0:1)+(sessionaffilie==null?0:1)+(sessionresponsable==null?0:1);
        }
        
        if(count>1||(count==0&&act!=null&&!act.equals("")&&!act.equals("AgentConnexion")&&!act.equals("ClientConnexion")&&!act.equals("AgentAuthen")&&!act.equals("ClientAuthen")&&!act.equals("Deconnexion")&&!act.equals("AfficherCreerCompte")&&!act.equals("CreerCompteClient"))){
            jspClient="/ErreurSession.jsp";
            message="Erreur de session ! Veuillez vous reconnecter !";
            if(act.substring(0, 5).equals("Agent")) request.setAttribute("typeConnexion","AgentConnexion");
            else request.setAttribute("typeConnexion","ClientConnexion");
        }
        else if(null==act){
            jspClient="/Connexion.jsp";
            request.setAttribute("typeConnexion","GestionnaireConnexion");
            message="Bienvenue";
        System.out.println("testttt"+act);
        }
        else switch (act) {  
            
            case "vide":
                jspClient="/Connexion.jsp";
                request.setAttribute("typeConnexion","GestionnaireConnexion");
                message="Bienvenue";
                break;
                
            case "GestionnaireConnexion" :
            case "AffilieConnexion" :
            case "ResponsableConnexion" :
            case "PubliqueConnexion" :
                Response=publiqueSession.rechercherConnexion(session, sessiongestionnaire, sessionaffilie, sessionresponsable, sessionpublique);
                message=(String)Response.get(0);
                jspClient=(String)Response.get(1);
                request.setAttribute("typeConnexion",act);
                break;
            
            case "Deconnexion" :
                session = request.getSession(false);
                session.invalidate();
                request.setAttribute("typeConnexion",request.getParameter("typeConnexion"));
                jspClient="/Connexion.jsp";
                message="Vous êtes déconnecté";
                break;
                
            case "ResponsableAuthen" :
                String ResponsableLogin=request.getParameter("Login");
                String ResponsableMdp=request.getParameter("MDP");
                Response=responsableSession.authentificationResponsable(ResponsableLogin, ResponsableMdp, request);
                message=(String)Response.get(0);
                jspClient=(String)Response.get(1);
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
