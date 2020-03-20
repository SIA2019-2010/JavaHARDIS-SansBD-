package servlet;
import entitee.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
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
        
        String jspClient;
        String message="";
        String act=request.getParameter("action");
        System.out.print(act==null);
        System.out.println(new Date().toLocaleString()+"  "+act+"========");//Supp
        List<Object> Response;
        List<Object[]> listeinfos;
        
        //Session
        List<Object> ResultatSession=TraiterSession(request,act);
        HttpSession session=(HttpSession)ResultatSession.get(0);
        Gestionnaire sessiongestionnaire=(Gestionnaire)ResultatSession.get(1);
        PersonnePhysique sessionaffilie=(PersonnePhysique)ResultatSession.get(2);;
        Responsable sessionresponsable=(Responsable)ResultatSession.get(3);
        
        if(!(boolean)ResultatSession.get(4)){
            System.out.println("Erreur Session");
            jspClient="/ErreurSession.jsp";
            message="Erreur de session ! Veuillez vous reconnecter !";
        }
        else if(null==act){
            System.out.println("new act null");
            jspClient="/Acceuil.jsp";
            message="Bienvenue";
        }
        else {System.out.println("new act "+act);
            switch (act) {  
            case "" :
            case "vide" :
                jspClient="/Acceuil.jsp";
                message="Bienvenue";
                break;
                
            case "GestionnaireConnexion" :
            case "AffilieConnexion" :
            case "ResponsableConnexion" :
                System.out.println("before");
                Response=publiqueSession.rechercherConnexion(session, sessiongestionnaire, sessionaffilie, sessionresponsable);
                System.out.println("after");
                message=(String)Response.get(0);
                System.out.println(message);
                jspClient=(String)Response.get(1);
                System.out.println(jspClient);
                request.setAttribute("typeConnexion",(String)Response.get(2));
                break;
            
            case "Deconnexion" :
                session = request.getSession(false);
                session.invalidate();
                request.setAttribute("typeConnexion",request.getParameter("typeConnexion"));
                jspClient="/Connexion.jsp";
                message="Vous êtes déconnecté";
                break;
                
            case "ResponsableAuthen" :
                String Login=request.getParameter("Login");
                String MDP=request.getParameter("MDP");
                Response=responsableSession.authentificationResponsable(Login, MDP, request);
                message=(String)Response.get(0);
                jspClient=(String)Response.get(1);
                break;
                
            case "AffilieAuthen" :
                Login=request.getParameter("Login");
                MDP=request.getParameter("MDP");
                Response=affilieSession.authentificationAffilie(Login, MDP, request);
                message=(String)Response.get(0);
                jspClient=(String)Response.get(1);
                break;
                
            case "GestionnaireAuthen" :
                Login=request.getParameter("Login");
                MDP=request.getParameter("MDP");
                Response=gestionnaireSession.authentificationGestionnaire(Login, MDP, request);
                message=(String)Response.get(0);
                jspClient=(String)Response.get(1);
                break;
                
            case "CreationDevisInformations" :
                List<Population> listpop = publiqueSession.recherchePopulations(); //je vais faire la methode
                listeinfos=(List<Object[]>)request.getAttribute("listeinfos");
                if(listeinfos==null) listeinfos=new ArrayList();
                request.setAttribute("listepopulation",listpop);
                request.setAttribute("pers",(Object)request.getAttribute("pers"));
                request.setAttribute("listeinfos",listeinfos);
                jspClient="/PageCreationDevis.jsp";
                break;
                
            case "CalculPrixDevis" :
                listeinfos=new ArrayList();
                String NomCreateur=request.getParameter("Nom");
                String PrenomCreateur=request.getParameter("Prenom");
                String DateNaiCreateur=request.getParameter("DateN");
                String SSCreateur=request.getParameter("NumeroSS");
                String MailCreateur=request.getParameter("Mail");
                String Papulation=request.getParameter("idpop");
                Object[] pers={NomCreateur,PrenomCreateur,DateNaiCreateur,SSCreateur,MailCreateur,Papulation};
                String[] NomAD=request.getParameterValues("NomAD");
                String[] PrenomAD=request.getParameterValues("PrenomAD");
                String[] DateNaiAD=request.getParameterValues("DateNaiAD");
                String[] NumeroSSAD=request.getParameterValues("NumeroSSAD");
                String[] MailAD=request.getParameterValues("MailAD");
                String[] idpopAD=request.getParameterValues("idpopAD");
                if(NomAD!=null)
                    for(int i=0; i<NomAD.length; i++){
                        Object[] infos={NomAD[i],PrenomAD[i],DateNaiAD[i],NumeroSSAD[i],MailAD[i],idpopAD[i]};
                        listeinfos.add(infos);
                    }

                
                
                Response=publiqueSession.calculPacks(pers, listeinfos);
                
                request.setAttribute("pers",pers);//supri
                request.setAttribute("listeinfos",listeinfos);//supri
                listpop = publiqueSession.recherchePopulations(); //je vais faire la methode
                request.setAttribute("listepopulation",listpop);
                request.setAttribute("lesPacks",(List<Object[]>)Response.get(4));
                jspClient=(String)Response.get(1);
                message=(String)Response.get(0);
                break;  
            
            case "ResponsableAfficherListePersonnePhique" :
                System.out.println(sessionresponsable==null);
                System.out.println(sessionresponsable.toString());
                request.setAttribute("listestatut",responsableSession.rechercherStatutBeneficiaire(sessionresponsable.getLaPersonneMorale()));
                jspClient="/ResponsableAfficherListePersonnePhique.jsp";
                message="liste de personnes physiques";
                break;
                
            case "AffilieAfficherRempoursementPers" :
                request.setAttribute("listeremboursement",affilieSession.afficherRempoursementPers(sessionaffilie));
                jspClient="/AffilieAfficherRempoursementPers.jsp";
                message="liste de remboursements";
                break;
                
            case "AffilieAfficherContrat" :
                request.setAttribute("listecontrat",affilieSession.rechercherStatutBeneficiaire(sessionaffilie));
                jspClient="/AffilieAfficherContrat.jsp";
                message="liste de contrats";
                break;    
                
            default:
                jspClient="/"+act+".jsp";
                message="";
        }
        }
            
            
        RequestDispatcher Rd;
        Rd=getServletContext().getRequestDispatcher(jspClient);
        request.setAttribute("message",message);
        Rd.forward(request,response);
    }
    
    protected List<Object> TraiterSession(HttpServletRequest request, String act)
            throws ServletException, IOException {
        List<Object> Response=new ArrayList();
        if(act==null) act="";
        HttpSession session=request.getSession(false);        
        boolean valide=true;
        String[] MenuGestionnaire={
        };
        String[] MenuAffilier={
            "AffilieAfficherRempoursementPers",
            "AffilieAfficherContrat"
        };
        String[] MenuResponsable={
            "ResponsableAfficherListePersonnePhique"
        };
        String[] MenuPublique={
            "",
            "null",
            "vide",
            "ResponsableAuthen", 
            "ResponsableConnexion", 
            "AffilieAuthen", 
            "AffilieConnexion", 
            "GestionnaireAuthen", 
            "GestionnaireConnexion", 
            "Deconnexion", 
            "CalculPrixDevis", 
            "CreationDevisInformations"
        };
        if(session!=null){
            System.out.println("Session est pas null");
            Gestionnaire sessiongestionnaire=(Gestionnaire)session.getAttribute("sessiongestionnaire");
            PersonnePhysique sessionaffilie=(PersonnePhysique)session.getAttribute("sessionaffilie");
            Responsable sessionresponsable=(Responsable)session.getAttribute("sessionresponsable");
            System.out.println((sessiongestionnaire==null?0:1)+" "+(sessionaffilie==null?0:1)+" "+(sessionresponsable==null?0:1));
            Response.add(session);
            Response.add(sessiongestionnaire);
            Response.add(sessionaffilie);
            Response.add(sessionresponsable);
            if(sessiongestionnaire!=null){
                if ((Arrays.asList(MenuGestionnaire).contains(act)||Arrays.asList(MenuPublique).contains(act))&&sessionaffilie==null&&sessionresponsable==null){
                    System.out.println("Mission Gestionnaire");
                    Response.add(true);
                    return Response;
                }
                else valide=false;
            }
            else if(sessionaffilie!=null){
                if ((Arrays.asList(MenuAffilier).contains(act)||Arrays.asList(MenuPublique).contains(act))&&sessionresponsable==null){
                    System.out.println("Mission Affilié");
                    Response.add(true);
                    return Response;
                }
                else valide=false;
            }
            else if(sessionresponsable!=null){
                if (Arrays.asList(MenuResponsable).contains(act)||Arrays.asList(MenuPublique).contains(act)){
                    System.out.println("Mission Responsable");
                    Response.add(true);
                    return Response;
                }
                else valide=false;
            }
        }
        if(valide){
            if (Arrays.asList(MenuPublique).contains(act)){
                System.out.println("Mission Publique");
                if(session==null){
                    Response.add(null);
                    Response.add(null);
                    Response.add(null);
                    Response.add(null);
                }
                Response.add(true);
                return Response;
            }
        }
        
        //if(count>1||(count==0&&act!=null&&!act.equals("")&&!act.equals("vide")&&!act.equals("ResponsableAuthen")&&!act.equals("AffilieAuthen")&&!act.equals("GestionnaireAuthen")&&!act.equals("GestionnaireConnexion")&&!act.equals("ResponsableConnexion")&&!act.equals("AffilieConnexion")&&!act.equals("CalculPrixDevis")&&!act.equals("Deconnexion")&&!act.equals("AffilieConnexion")&&!act.equals("CreationDevisInformations"))){
            
        if(act.substring(0, 5).equals("Affil")) request.setAttribute("typeConnexion","AffilieConnexion");
        else if(act.substring(0, 5).equals("Respo")) request.setAttribute("typeConnexion","ResponsableConnexion");
        else request.setAttribute("typeConnexion","GestionnaireConnexion");
        System.out.println("Session erreur");
        Response.add(false);
        return Response;
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
