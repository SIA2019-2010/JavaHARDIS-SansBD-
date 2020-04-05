package servlet;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import entitee.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.Format;
import java.text.SimpleDateFormat;
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
        System.out.println("creation begins");
        //publiqueSession.RIB();
        //gestionnaireSession.creerActivite("act");
        //publiqueSession.creerDevis(null, null, 0, null, null);
        //List<Beneficiaire> lb=new ArrayList();
        //lb.add(Beneficiaire.Affilie);
        //lb.add(Beneficiaire.Concubin);
        //gestionnaireSession.creerProduit("1",lb , Beneficiaire.Affilie, null, null, null, null, null, null);
        //System.out.println("creation bon");
        //Session
        List<Object> ResultatSession=TraiterSession(request,act);
        System.out.println("qasw"+ResultatSession.size());
        HttpSession session=(HttpSession)(ResultatSession.get(0));
        Gestionnaire sessiongestionnaire=(Gestionnaire)(ResultatSession.get(1));
        System.out.println("1");
        PersonnePhysique sessionaffilie=(PersonnePhysique)(ResultatSession.get(2));
        System.out.println("2");
        Responsable sessionresponsable=(Responsable)(ResultatSession.get(3));
        request.setAttribute("typeConnexion", (String)(ResultatSession.get(5)));
        System.out.println(session==null);
        System.out.println(sessiongestionnaire==null);
        System.out.println(sessionaffilie==null);
        System.out.println(sessionresponsable==null);
        
        if(!(boolean)ResultatSession.get(4)){
            System.out.println("Erreur Session");
            jspClient="/ErreurSession.jsp";
            message="Erreur de session ! Veuillez vous reconnecter !";
        }
        else if(null==act){
            System.out.println("new missioncase null "+(new Date()).toLocaleString());
            jspClient="/Acceuil.jsp";
            message="Bienvenue";
        }
        else {System.out.println("new missioncase "+act+" "+(new Date()).toLocaleString());
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
                List<Beneficiaire> listben = publiqueSession.rechercheBeneficiaires(); //je vais faire la methode
                //Object[] perss;
                //perss=(Object[])session.getAttribute("pers");
                //listeinfos=(List<Object[]>)session.getAttribute("listeinfos");
                //if(listeinfos==null) listeinfos=new ArrayList();
                request.setAttribute("listben",listben);
                request.setAttribute("listepopulation",listpop);
                session.setAttribute("pers",null);
                session.setAttribute("listeinfos",new ArrayList());
                jspClient="/PageCreationDevis.jsp";
                break;
                
            case "RechercherAffilieDevis" :
                listpop = publiqueSession.recherchePopulations(); //je vais faire la methode
                listben = publiqueSession.rechercheBeneficiaires(); //je vais faire la methode
                request.setAttribute("listben",listben);
                request.setAttribute("listepopulation",listpop);
                String NumeroSeSo=request.getParameter("NumeroSS");
                Response = gestionnaireSession.DevisAvecRecherchePersonne(sessiongestionnaire,NumeroSeSo);
                System.out.println("meiti");
                Object[] perss=(Object[])Response.get(2);
                listeinfos=(List<Object[]>)Response.get(3);
                session.setAttribute("pers",perss);
                session.setAttribute("listeinfos",listeinfos);
                jspClient=(String)Response.get(1);
                System.out.println(jspClient);
                System.out.println((String)Response.get(0));
                break;
                
            case "CalculPrixDevis" :
                System.out.println("s");
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
                
                int number=0;
                try{number=NomAD.length;}catch(Exception e){}
                    for(int i=0; i<number; i++){
                        Object[] infos={NomAD[i],PrenomAD[i],DateNaiAD[i],NumeroSSAD[i]};
                        listeinfos.add(infos);
                        System.out.println("null");
                    }
                System.out.println("bp");
                
                
                Response=publiqueSession.calculPacks(pers, listeinfos);
                session.setAttribute("pers",pers);//supri
                session.setAttribute("listeinfos",listeinfos);//supri
                listpop = publiqueSession.recherchePopulations(); //je vais faire la methode
                request.setAttribute("listepopulation",listpop);
                System.out.println("ap");
                session.setAttribute("lesPacks",(List<Object[]>)Response.get(2));
                jspClient=(String)Response.get(1);
                System.out.println(jspClient+"jspCalculPacks");
                message=(String)Response.get(0);
                break;   
            
            case "ResponsableAfficherListePersonnePhique" :
                request.setAttribute("listestatut",responsableSession.rechercherStatutBeneficiaire(sessionresponsable.getLaPersonneMorale()));
                jspClient="/ResponsableAfficherListePersonnePhique.jsp";
                message="liste de personnes physiques";
                break;
                
            case "AffilieAfficherRempoursementPers" :
                request.setAttribute("listeremboursement",affilieSession.afficherRempoursementPers(sessionaffilie));
                jspClient="/AffilieAfficherRempoursementPers.jsp";
                message="liste de remboursements";
                break;
                
            case "GestionnaireValiderContrat" :
                request.setAttribute("listecontrat",gestionnaireSession.AfficherContratCree(sessiongestionnaire.getLeDomaine()));
                System.out.println(sessiongestionnaire.getLeDomaine().getId());
                jspClient="/GestionnaireValiderContrat.jsp";
                message="liste de contrats à valider";
                break;
                
            case "ValiderContrat" :
                String idc=request.getParameter("idc");
                System.out.println(sessiongestionnaire.getLeDomaine().getId());
                jspClient="/GestionnaireValiderContrat.jsp";
                Response=gestionnaireSession.validerContrat(idc);
                message=(String)Response.get(0);
                request.setAttribute("listecontrat",gestionnaireSession.AfficherContratCree(sessiongestionnaire.getLeDomaine()));
                break;
                
            case "GestionnaireCloturerContrat" :
                request.setAttribute("listecontrat",gestionnaireSession.AfficherContratValide(sessiongestionnaire.getLeDomaine()));
                System.out.println(sessiongestionnaire.getLeDomaine().getId());
                jspClient="/GestionnaireCloturerContrat.jsp";
                message="liste de contrats à cloturer";
                break;
                
            case "CloturerContrat" :
                System.out.println(sessiongestionnaire.getLeDomaine().getId());
                jspClient="/GestionnaireCloturerContrat.jsp";
                idc=request.getParameter("idc");
                Response=gestionnaireSession.cloturerContrat(idc);
                message=(String)Response.get(0);
                request.setAttribute("listecontrat",gestionnaireSession.AfficherContratValide(sessiongestionnaire.getLeDomaine()));
                break;
                
            case "AffilieAfficherContrat" :
                request.setAttribute("listecontrat",affilieSession.rechercherStatutBeneficiaire(sessionaffilie));
                jspClient="/AffilieAfficherContrat.jsp";
                break;  
                
            case "GestionnairePageModifierMdp" :
            case "AffiliePageModifierMdp" :
            case "ResponsablePageModifierMdp" :
                jspClient="/PageModifierMdp.jsp";
                message="Modification de mot de passe";
                break; 
            case "GestionnaireModifierMdp" :
                String OMDP=request.getParameter("OMDP");
                String NMDP=request.getParameter("NMDP");
                String RMDP=request.getParameter("RMDP");
                Response=gestionnaireSession.modifiermdp(sessiongestionnaire,OMDP,NMDP,RMDP);
                message=(String)(Response.get(0));
                jspClient=(String)(Response.get(1));
                break;
            case "AffilieModifierMdp" :
                OMDP=request.getParameter("OMDP");
                NMDP=request.getParameter("NMDP");
                RMDP=request.getParameter("RMDP");
                Response=affilieSession.modifiermdp(sessionaffilie,OMDP,NMDP,RMDP);
                message=(String)(Response.get(0));
                jspClient=(String)(Response.get(1));
                break;
            case "ResponsableModifierMdp" :
                OMDP=request.getParameter("OMDP");
                NMDP=request.getParameter("NMDP");
                RMDP=request.getParameter("RMDP");
                Response=responsableSession.modifiermdp(sessionresponsable,OMDP,NMDP,RMDP);
                message=(String)(Response.get(0));
                jspClient=(String)(Response.get(1));
                break;
                 
             /*
                case "ChangementMDP" :
                //PersonnePhysique affi= (PersonnePhysique) session.getAttribute("sessionaffilie");
                String NvMDP=request.getParameter("NvMDP");
                affilieSession.modifierMDP(NvMDP, sessionaffilie);
                jspClient="/AffilieMenu.jsp";
                message="Mot de passe modifié";
                
               
                String typeco = request.getParameter("TypeConnexion");
                
                if(typeco.substring(0, 5).equals("Affil"))  affilieSession.modifierMDP(NvMDP, sessionaffilie);
                else if(typeco.substring(0, 5).equals("Respo")) responsableSession.modifiermdp(sessionresponsable, NvMDP);
                else gestionnaireSession.modifiermdp(sessiongestionnaire, NvMDP);
               
                
                
                break;*/
                
                
            case "ChangementAdresse" :
                //PersonnePhysique affi= (PersonnePhysique) session.getAttribute("sessionaffilie");
                String NvAdresse=request.getParameter("NvAdresseAffilie");
                affilieSession.modifierAdresse(NvAdresse, sessionaffilie);
                jspClient="/AffilieMenu.jsp";
                message="Adresse modifiée";
                break;  
                
            case "ChangementMail" :
               // PersonnePhysique affi= (PersonnePhysique) session.getAttribute("sessionaffilie");
                String NvMail=request.getParameter("NvMailffilie");
                affilieSession.modifierMail(NvMail, sessionaffilie);
                jspClient="/AffilieMenu.jsp";
                message="eMail modifié";
                break;   
                
                
            case"CreationMoraleInformations" : 
                List<Activite> listacti = gestionnaireSession.recupererActivites();
                request.setAttribute("listeact",listacti);
                message="Créer personne morale";
                jspClient="/GestionnaireCreationMorale.jsp";
                break;
                
            case "InsererMorale" :
                //Objet pers : 1 SIRET, 2 raisonSociale, 3 adresse, 4 activite (String de ID)
                String siret=request.getParameter("Siret");
                String raiso=request.getParameter("RaisonSociale");
                String ad=request.getParameter("Adresse");
                String idactivi=request.getParameter("idact");
                
                List<String>listeinfosmorale=new ArrayList();
                /*Array.set(listeinfosmorale, 0, siret);
                Array.set(listeinfosmorale, 1, raiso);
                Array.set(listeinfosmorale, 2, ad);
                Array.set(listeinfosmorale, 3, idactivi);*/
                listeinfosmorale.add(siret);
                listeinfosmorale.add(raiso);
                listeinfosmorale.add(ad);
                listeinfosmorale.add(idactivi);
                
                System.out.println("testttt");
                Response=gestionnaireSession.creerMoraleComplet(listeinfosmorale);
                listacti = gestionnaireSession.recupererActivites();
                request.setAttribute("listeact",listacti);
                message=(String)(Response.get(0));
                jspClient=(String)(Response.get(1));
                break;
                
                //listepersmo;
            case "CreationResponsableInformations" : 
                List<PersonneMorale> listepersmo = gestionnaireSession.recupererPersonneMorale(); 
                
                request.setAttribute("listepersmo",listepersmo);
                    
                jspClient="/GestionnaireCreationResponsable.jsp";
                break;
                
                
            //case "GestionnaireCreationRemboursement" :
            //    System.out.println("entrer");
            //    listeacte = gestionnaireSession.rechercheListeActesNonRembourse();
            //    request.setAttribute("listeacte",listeacte);
            //    request.setAttribute("message",listeacte);
            //    jspClient="/GestionnaireCreationRemboursement.jsp";
            //    break;
                
            case "InsererResponsable" :
                // List<String> pers 1 nom, 2 prenom, 3 mail, 4 tel,5 PersonneMorale (String de ID)
                String nom=request.getParameter("Nom");
                String prenom=request.getParameter("Prenom");
                String mail=request.getParameter("Mail");
                String tel=request.getParameter("Telephone");
                String idpersmo=request.getParameter("idpers");
                System.out.println(nom);
                System.out.println(prenom);
                System.out.println(mail);
                System.out.println(tel);
                System.out.println("idperso"+idpersmo);
                System.out.println("asas");
                List<String>listeinfosresp=new ArrayList();
                listeinfosresp.add(nom);
                listeinfosresp.add(prenom);
                listeinfosresp.add(mail);
                listeinfosresp.add(tel);
                listeinfosresp.add(idpersmo);
                
                
                listepersmo = gestionnaireSession.recupererPersonneMorale();
                request.setAttribute("listepersmo",listepersmo);
                
                Response=gestionnaireSession.creerResponsableComplet(listeinfosresp);
                message=(String)(Response.get(0));
                jspClient=(String)(Response.get(1));
                break;
                
            case "PageDevisInformationsSupplementaire" :
                /*Object[] persc=(Object[])session.getAttribute("persc");
                List<Object[]>listeinfosc=(List<Object[]>)session.getAttribute("listeinfosc");
                //if(listeinfos==null) listeinfos=new ArrayList();
                try{//teste : si listeinfos récuperée est null
                    listeinfosc.size();
                }catch(Exception e){
                    listeinfosc=new ArrayList();
                }*/
                String iddevis=request.getParameter("iddevis");
                listben = publiqueSession.rechercheBeneficiaires(); //je vais faire la methode
                listpop = publiqueSession.recherchePopulations(); //je vais faire la methode
                Response=publiqueSession.VerifierDevisID(iddevis);
                message=(String)Response.get(0);
                jspClient=(String)Response.get(1);
                System.out.println((Devis)(Response.get(2))==null);
                request.setAttribute("devis", (Devis)(Response.get(2)));
                request.setAttribute("listben",listben);
                request.setAttribute("listpop",listpop);
                request.setAttribute("persa",null);
                request.setAttribute("listinfos",new ArrayList());
                break;
                
            case "CompleterInformations" :
                String Nom=request.getParameter("Nom");
                String Prenom=request.getParameter("Prenom");
                String NumeroSS=request.getParameter("NumeroSS");
                String Adresse=request.getParameter("Adresse");
                String idgen=request.getParameter("idgen");
                Login=request.getParameter("Login");
                MDP=request.getParameter("MDP");
                String RIB=request.getParameter("RIB");
                iddevis=request.getParameter("iddevis");
                Object[] persa={Nom, Prenom, NumeroSS, Adresse, idgen, Login, MDP, RIB};
                List<Object[]> listinfos=new ArrayList();
                NomAD=request.getParameterValues("NomAD");
                PrenomAD=request.getParameterValues("PrenomAD");
                NumeroSSAD=request.getParameterValues("NumeroSSAD");
                String[] AdresseAD=request.getParameterValues("AdresseAD");
                String[] idgenAD=request.getParameterValues("idgenAD");
                String[] idpopAD=request.getParameterValues("idpopAD");
                String[] idbenAD=request.getParameterValues("idbenAD");
                number=0;
                try{number=NomAD.length;}catch(Exception e){}
                for(int i=0; i<number; i++){
                    System.out.println(idbenAD[i]+"ben");
                    Object[] infos={NomAD[i],PrenomAD[i],NumeroSSAD[i],AdresseAD[i],idgenAD[i],idpopAD[i],idbenAD[i]};
                    listinfos.add(infos);
                }
                System.out.println("before validerDevis");
                Response=publiqueSession.validerDevis(iddevis,persa,listinfos);
                System.out.println("after validerDevis");
                jspClient=(String)(Response.get(1));
                System.out.println("jspClient "+jspClient);
                message=(String)(Response.get(0));
                listben = publiqueSession.rechercheBeneficiaires(); //je vais faire la methode
                listpop = publiqueSession.recherchePopulations(); //je vais faire la methode
                request.setAttribute("devis", (Devis)(Response.get(4)));
                request.setAttribute("persa", persa);
                request.setAttribute("listinfos", listinfos);
                request.setAttribute("listben",listben);
                request.setAttribute("listpop",listpop);
                break;
                
            case "GestionnaireAfficherAffilie" :
                List<PersonnePhysique>listepers=gestionnaireSession.AfficherPersonnesPhysiques();
                request.setAttribute("listepers", listepers);
                jspClient="/GestionnaireAfficherAffilie.jsp";
                message="Liste de personnes physiques";
                break;
                
            case "ContratListePersonnes" :
                String idp=request.getParameter("idp");
                Response=gestionnaireSession.DevisAvecRecherchePersonne(sessiongestionnaire, idp);
                message=(String)Response.get(0);
                jspClient=(String)Response.get(1);
                listpop = publiqueSession.recherchePopulations(); //je vais faire la methode
                listben = publiqueSession.rechercheBeneficiaires(); //je vais faire la methode
                listeinfos=(List<Object[]>)Response.get(3);
                request.setAttribute("listben",listben);
                request.setAttribute("listepopulation",listpop);
                session.setAttribute("pers",(Object[])Response.get(2));
                session.setAttribute("listeinfos",listeinfos);
                
                break;
                
             /*   
            case "genererPDFDevis" : 
                jspClient="/Acceuil";
                doActionCreerPdfDevis(request,response);
                
                break;
             */   
            
            case "genererPDFPriseEnCharge" : 
                jspClient="/Acceuil";
                String idgarst=request.getParameter("idgar");
                Long idgar = Long.valueOf(idgarst);
                
                Garantie g = affilieSession.recupererGarantieChoisie(idgar);
                
                doActionCreerPdfPriseEnCharge(request,response,sessionaffilie,g);
                
                break;
            
            case "GestionnaireProduitCollectif" :
                int page=1;
                try{page=Integer.parseInt(request.getParameter("SPage"));}catch(NumberFormatException e){}
                String RePr=(request.getParameter("RePr")==null?(session.getAttribute("RePr")==null?"":(String)session.getAttribute("RePr")):request.getParameter("RePr"));
                List<Produit> listepro = gestionnaireSession.rechercheProduitsCollectif(page,RePr);
                long taille=gestionnaireSession.CompterProduitCollectif(RePr);
                int total=(int)Math.ceil((double)(taille/50.0));
                request.setAttribute("taille", taille);
                request.setAttribute("Npage", page);
                request.setAttribute("total",total);
                request.setAttribute("listepro",listepro);
                request.setAttribute("idprod",request.getParameter("idprod"));
                session.setAttribute("RePr",RePr);
                message="listeproduit";
                jspClient="/GestionnaireProduitCollectif.jsp";
                break;
                
            case "GestionnaireChoixProduitCollectif" :
                String idprod=request.getParameter("idprod");
                Response=gestionnaireSession.rechercheProduitsCollectifID(idprod);
                page=1;
                try{page=Integer.parseInt(request.getParameter("SPage"));}catch(NumberFormatException e){}
                RePr=(request.getParameter("RePr")==null?(session.getAttribute("RePr")==null?"":(String)session.getAttribute("RePr")):request.getParameter("RePr"));
                listepro = gestionnaireSession.rechercheProduitsCollectif(page,RePr);
                taille=gestionnaireSession.CompterProduitCollectif(RePr);
                total=(int)Math.ceil((double)(taille/50.0));
                request.setAttribute("taille", taille);
                request.setAttribute("Npage", page);
                request.setAttribute("total",total);
                request.setAttribute("listepro",listepro);
                session.setAttribute("RePr",RePr);
                message=(String)Response.get(0);
                jspClient=(String)Response.get(1);
                request.setAttribute("idprod", (String)Response.get(2));
                request.setAttribute("persa", null);
                request.setAttribute("listinfos", new ArrayList());
                listben = publiqueSession.rechercheBeneficiaires(); //je vais faire la methode
                listpop = publiqueSession.recherchePopulations(); //je vais faire la methode
                request.setAttribute("listben",listben);
                request.setAttribute("listpop",listpop);
                request.setAttribute("persa",null);
                request.setAttribute("listinfos",new ArrayList());
                break;
                
            case "GestionnaireCreerContratCollectif" :
                idprod=request.getParameter("idprod");
                idgen=request.getParameter("idgen");
                Nom=request.getParameter("Nom");
                PrenomCreateur=request.getParameter("Prenom");
                DateNaiCreateur=request.getParameter("DateN");
                SSCreateur=request.getParameter("NumeroSS");
                Adresse=request.getParameter("Adresse");
                MailCreateur=request.getParameter("Mail");
                Papulation=request.getParameter("idpop");
                Object[] persb={idgen,Nom,PrenomCreateur,DateNaiCreateur,SSCreateur,Adresse,MailCreateur,Papulation};
                idgenAD=request.getParameterValues("idgenAD");
                NomAD=request.getParameterValues("NomAD");
                try{System.out.println(NomAD.length);}catch(Exception e){System.out.println("NomAD==null");}
                PrenomAD=request.getParameterValues("PrenomAD");
                DateNaiAD=request.getParameterValues("DateNaiAD");
                NumeroSSAD=request.getParameterValues("NumeroSSAD");
                AdresseAD=request.getParameterValues("AdresseAD");
                idpopAD=request.getParameterValues("idpopAD");
                idbenAD=request.getParameterValues("idbenAD");
                number=(NomAD==null?0:NomAD.length);
                listeinfos=new ArrayList();
                for(int i=0; i<number; i++){
                    Object[] infos={idgenAD[i],NomAD[i],PrenomAD[i],DateNaiAD[i],NumeroSSAD[i],AdresseAD[i],idpopAD[i],idbenAD[i]};
                    listeinfos.add(infos);
                }
                System.out.println("bp");
                
                
                Response=gestionnaireSession.ajouterPersonneProduitCollectif(idprod, persb, listeinfos);
                System.out.println("sortie");
                request.setAttribute("idprod",idprod);//supri
                request.setAttribute("persa",persb);//supri
                request.setAttribute("listinfos",listeinfos);//supri
                listben = publiqueSession.rechercheBeneficiaires(); //je vais faire la methode
                listpop = publiqueSession.recherchePopulations(); //je vais faire la methode
                request.setAttribute("listben",listben);
                request.setAttribute("listpop",listpop);
                message=(String)Response.get(0);
                jspClient=(String)Response.get(1);
                System.out.println(jspClient+"jspCalculPacks");
                break;
                
            case "GestionnaireActesNonRembourse" :
                page=1;
                try{page=Integer.parseInt(request.getParameter("SPage"));}catch(NumberFormatException e){}
                String ReSS=(request.getParameter("ReSS")==null?(session.getAttribute("ReSS")==null?"":(String)session.getAttribute("ReSS")):request.getParameter("ReSS"));
                List<Acte> listeacte = gestionnaireSession.rechercheListeActesNonRembourse(page,ReSS);
                taille=gestionnaireSession.CompterActesNonRembourse(ReSS);
                total=(int)Math.ceil((double)(taille/50.0));
                request.setAttribute("taille", taille);
                request.setAttribute("Npage", page);
                request.setAttribute("total",total);
                request.setAttribute("listeacte",listeacte);
                session.setAttribute("ReSS",ReSS);
                message="listeacte";
                jspClient="/GestionnaireActesNonRembourse.jsp";
                break;
                
            case "GestionnaireCreerRemboursement" :
                String idacte=request.getParameter("idacte");
                Response=gestionnaireSession.creerRemboursement(idacte);
                ReSS=(session.getAttribute("ReSS")==null?"":(String)session.getAttribute("ReSS"));
                listeacte = gestionnaireSession.rechercheListeActesNonRembourse(1,ReSS);
                taille=gestionnaireSession.CompterActesNonRembourse(ReSS);
                total=(int)Math.ceil((double)(taille/50.0));
                request.setAttribute("taille", taille);
                request.setAttribute("Npage", 1);
                request.setAttribute("total",total);
                request.setAttribute("listeacte",listeacte);
                session.setAttribute("ReSS",ReSS);
                message=(String)Response.get(0);
                jspClient="/GestionnaireActesNonRembourse.jsp";
                break;
                
            case "ChoixPack" :
                pers=(Object[])session.getAttribute("pers");
                System.out.println(pers==null);
                listeinfos=(List<Object[]>)session.getAttribute("listeinfos");
                //if(listeinfos==null) listeinfos=new ArrayList();
                try{//teste : si listeinfos récuperée est null
                    listeinfos.size();
                }catch(Exception e){
                    listeinfos=new ArrayList();
                }
                List<Object[]> lesPacks=(List<Object[]>)session.getAttribute("lesPacks");
                int numpack=Integer.parseInt(request.getParameter("numpack"));
                System.out.println(numpack+"numpack");
                Object[] pack=lesPacks.get(numpack);
                Response=publiqueSession.creerDevisComplet(pers, pack, listeinfos);
                listpop = publiqueSession.recherchePopulations(); //je vais faire la methode
                request.setAttribute("listepopulation",listpop);
                jspClient=(String)Response.get(1);
                message=(String)Response.get(0); 
                
                
                break;
                
            case "GestionnaireRempoursementEncours" :
                List<Remboursement> lister=gestionnaireSession.afficherRempoursementEncours();
                request.setAttribute("lister", lister);
                jspClient="/GestionnaireRempoursementEncours.jsp";
                message="Liste remboursement";
                break;
                
            case "GestionnaireValiderRemboursement" :
                jspClient="/GestionnaireRempoursementEncours.jsp";
                String idrv=request.getParameter("idrv");
                Response=gestionnaireSession.validerRemboursement(idrv);
                message=(String)Response.get(0);
                lister=gestionnaireSession.afficherRempoursementEncours();
                request.setAttribute("lister", lister);
                break;
                
            case "GestionnaireRefuserRemboursement" :
                jspClient="/GestionnaireRempoursementEncours.jsp";
                String idrr=request.getParameter("idrr");
                Response=gestionnaireSession.refuserRemboursement(idrr);
                message=(String)Response.get(0);
                lister=gestionnaireSession.afficherRempoursementEncours();
                request.setAttribute("lister", lister);
                break;
                
            case "PageCreationProduit" :
                listben = publiqueSession.rechercheBeneficiaires(); 
                listepersmo = gestionnaireSession.recupererPersonneMorale();
                listpop = publiqueSession.recherchePopulations();
                List<Domaine> listdo=gestionnaireSession.AfficherDomaine();
                List<TypeProduit> listtp=gestionnaireSession.AfficherTypeProduit();
                List<Fiscalite> listf=gestionnaireSession.AfficherFiscalite();
                List<TypeGarantie> listtg=gestionnaireSession.AfficherTypeGarantie();
                request.setAttribute("listben", listben);
                request.setAttribute("listepersmo", listepersmo);
                request.setAttribute("listpop", listpop);
                request.setAttribute("listdo", listdo);
                request.setAttribute("listtp", listtp);
                request.setAttribute("listf", listf);
                request.setAttribute("listtg", listtg);
                jspClient="/PageCreationProduit.jsp";
                message="Création Produit";
                break;
                
            case "GestionnaireCreationProduit" :
                message="";
                jspClient="/PageCreationProduit.jsp";
                listben = publiqueSession.rechercheBeneficiaires(); 
                listepersmo = gestionnaireSession.recupererPersonneMorale();
                listpop = publiqueSession.recherchePopulations();
                listdo=gestionnaireSession.AfficherDomaine();
                listtp=gestionnaireSession.AfficherTypeProduit();
                listf=gestionnaireSession.AfficherFiscalite();
                listtg=gestionnaireSession.AfficherTypeGarantie();
                request.setAttribute("listben", listben);
                request.setAttribute("listepersmo", listepersmo);
                request.setAttribute("listpop", listpop);
                request.setAttribute("listdo", listdo);
                request.setAttribute("listtp", listtp);
                request.setAttribute("listf", listf);
                request.setAttribute("listtg", listtg);
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
        HttpSession session=request.getSession(true);   
        System.out.println("A");
        boolean valide=true;
        act=(act==null?"null":act);
        String[] MenuGestionnaire={
            "GestionnairePageModifierMdp",
            "GestionnaireModifierMdp",
            "GestionnaireValiderContrat",
            "ValiderContrat",
            "GestionnaireCloturerContrat",
            "CloturerContrat",
            "GestionnaireCreerRemboursement",
            "GestionnaireActesNonRembourse",
            "CreationMoraleInformations",
            "CreationResponsableInformations",
            "InsererMorale",
            "InsererResponsable",
            "GestionnaireAfficherAffilie",
            "ContratListePersonnes",
            "GestionnaireRempoursementEncours",
            "GestionnaireValiderRemboursement",
            "GestionnaireRefuserRemboursement",
            "PageCreationProduit",
            "GestionnaireCreationProduit",
            "RechercherAffilieDevis",
            "GestionnaireProduitCollectif",
            "GestionnaireChoixProduitCollectif",
            "GestionnaireActesNonRembourse",
            "GestionnaireCreerContratCollectif"
        };
        String[] MenuAffilie={
            "AffilieAfficherRempoursementPers",
            "AffilieAfficherContrat",
            "AffiliePageModifierMdp",
            "AffilieModifierMdp",
            "ChangementMail",
            "ChangementAdresse",
            "ChangementMDP"
        
        };
        String[] MenuResponsable={
            "ResponsableAfficherListePersonnePhique",
            "ResponsablePageModifierMdp",
            "ResponsableModifierMdp"
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
            "CreationDevisInformations",
            "ValiderDevis",
            "PageDevisInformationsSupplementaire",
            "ChoixPack",
            "genererPDFDevis",
            "genererPDFPriseEnCharge",
            "CompleterInformations"
        };
        System.out.println("B");
        System.out.println("C");

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
                Response.add("GestionnaireConnexion");
                return Response;
            }
            else valide=false;
        }
        else if(sessionaffilie!=null){
            if ((Arrays.asList(MenuAffilie).contains(act)||Arrays.asList(MenuPublique).contains(act))&&sessionresponsable==null){
                System.out.println("Mission Affilié");
                Response.add(true);
                Response.add("AffilieConnexion");
                return Response;
            }
            else valide=false;
        }
        else if(sessionresponsable!=null){
            if (Arrays.asList(MenuResponsable).contains(act)||Arrays.asList(MenuPublique).contains(act)){
                System.out.println("Mission Responsable");
                Response.add(true);
                Response.add("ResponsableConnexion");
                return Response;
            }
            else valide=false;
        }
        if(valide){
            if (Arrays.asList(MenuPublique).contains(act)){
                System.out.println("Mission Publique");
                Response.add(true);
                Response.add("SansConnexion");
                return Response;
            }
        }
        System.out.println("Fin traitement");
        //if(count>1||(count==0&&act!=null&&!act.equals("")&&!act.equals("vide")&&!act.equals("ResponsableAuthen")&&!act.equals("AffilieAuthen")&&!act.equals("GestionnaireAuthen")&&!act.equals("GestionnaireConnexion")&&!act.equals("ResponsableConnexion")&&!act.equals("AffilieConnexion")&&!act.equals("CalculPrixDevis")&&!act.equals("Deconnexion")&&!act.equals("AffilieConnexion")&&!act.equals("CreationDevisInformations"))){
            
        /*if(act.substring(0, 5).equals("Affil")) request.setAttribute("typeConnexion","AffilieConnexion");
        else if(act.substring(0, 5).equals("Respo")) request.setAttribute("typeConnexion","ResponsableConnexion");
        else request.setAttribute("typeConnexion","GestionnaireConnexion");*/
        System.out.println("Session erreur");
        Response.add(false);
        String t=(String)request.getAttribute("typeConnexion");
        if (t==null) Response.add("GestionnaireConnexion");
        else if(t.equals("ResponsableAuthen")||t.equals("AffilieConnexion")||t.equals("ResponsableConnexion")) Response.add(t);
        else Response.add("GestionnaireConnexion");
        System.out.println("avant return case");
        return Response;
    }
    
 
    protected void doActionCreerPdfDevis(HttpServletRequest request, HttpServletResponse response,Devis dev) throws ServletException, IOException {
        String masterPath= request.getServletContext().getRealPath("/WEB-INF/DevisMaster.pdf");
        response.setContentType("application/pdf");
        
        try( PdfReader reader = new PdfReader(masterPath);
             PdfWriter writer = new PdfWriter(response.getOutputStream());
             PdfDocument document = new PdfDocument(reader, writer)) {
            
            PdfPage page = document.getPage(1);
            
            PdfCanvas canvas = new PdfCanvas(page);
            
            FontProgram fontProgram = FontProgramFactory.createFont();
            PdfFont font = PdfFontFactory.createFont(fontProgram, "utf-8", true);
            canvas.setFontAndSize(font, 12);
            
            canvas.beginText();
            
            
            //iddevis
            canvas.setTextMatrix(510, 760);
            canvas.showText(""+dev.getId());
            
            //info client
            PersonnePhysique pers = dev.getLaPersonne();
             
            canvas.setTextMatrix(110, 567);
            canvas.showText(""+pers.getNom());
            
            canvas.setTextMatrix(120, 551);
            canvas.showText(""+pers.getPrenom());
            
            canvas.setTextMatrix(210, 537);
            canvas.showText(""+pers.getNumeroSS());
            
            //info produit
            Produit pro = dev.getLeProduit();
            
            canvas.setTextMatrix(155, 464);
            canvas.showText(""+pro.getNomProduit());
            
            //liste garantie choisies dans le produit
             int top=410;
            
            for(TypeGarantie choisie : pro.getLesTypesGarantie()){
                 canvas.setTextMatrix(80, top);
                canvas.showText(""+choisie.getTypeGarantie());
                 
                top-=20;
            }
            
            //le prix
            canvas.setTextMatrix(300, 150);
            canvas.showText(""+Double.toString(dev.getPrix()));
            
            
             canvas.setFontAndSize(font, 10);
             
            //Le lien : 
            canvas.setTextMatrix(50, 60);
            canvas.showText("localhost:8080/Hardis_sApplication-war/Page?action=PageDevisInformationsSupplementaire&iddevis="+dev.getId());
            
            
            
            canvas.endText();
            
            
        }
    }
    
    protected void doActionCreerPdfPriseEnCharge(HttpServletRequest request, HttpServletResponse response,PersonnePhysique affi,Garantie gar) throws ServletException, IOException {
        String masterPath= request.getServletContext().getRealPath("/WEB-INF/PriseEnChargeMaster.pdf");
        response.setContentType("application/pdf");
        
        try( PdfReader reader = new PdfReader(masterPath);
             PdfWriter writer = new PdfWriter(response.getOutputStream());
             PdfDocument document = new PdfDocument(reader, writer)) {
            
            PdfPage page = document.getPage(1);
            
            PdfCanvas canvas = new PdfCanvas(page);
            
            FontProgram fontProgram = FontProgramFactory.createFont();
            PdfFont font = PdfFontFactory.createFont(fontProgram, "utf-8", true);
            canvas.setFontAndSize(font, 12);
            
            canvas.beginText();
            
            //info client
             
            canvas.setTextMatrix(110, 760);
            canvas.showText(""+affi.getNom());
            
            canvas.setTextMatrix(120, 745);
            canvas.showText(""+affi.getPrenom());
            
            canvas.setTextMatrix(213, 730);
            canvas.showText(""+affi.getNumeroSS());
            
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String datestr = formatter.format(affi.getDateNaiss());
            String datej = formatter.format(new Date());
            
            canvas.setTextMatrix(170, 715);
            canvas.showText(""+datestr);
            
            //Date : 
            canvas.setTextMatrix(115, 570);
            canvas.showText(""+datej);
            
            //texte
            canvas.setTextMatrix(340, 541);
            canvas.showText(""+affi.getNom()+" "+affi.getPrenom());
            
            //texte
            canvas.setTextMatrix(390, 527);
            canvas.showText(""+affi.getLesStatutsBeneficiaire().get(0).getLeContrat().getId());
            
           
            //Garantie choisie
            canvas.setTextMatrix(180, 472);
            canvas.showText(""+gar.getLibelleGarantie());
            
            
           
            //Signature
            canvas.setTextMatrix(385, 310);
            canvas.showText("..SIGNATURE/TAMPON..");
            
            
            
            canvas.endText();
            
            
        }
    }
    
    protected void doActionCreerPdfDemandePriseEnCharge(HttpServletRequest request, HttpServletResponse response,PersonnePhysique affi,Garantie gar) throws ServletException, IOException {
        String masterPath= request.getServletContext().getRealPath("/WEB-INF/PriseEnChargeMaster.pdf");
        response.setContentType("application/pdf");
        
        try( PdfReader reader = new PdfReader(masterPath);
             PdfWriter writer = new PdfWriter(response.getOutputStream());
             PdfDocument document = new PdfDocument(reader, writer)) {
            
            PdfPage page = document.getPage(1);
            
            PdfCanvas canvas = new PdfCanvas(page);
            
            FontProgram fontProgram = FontProgramFactory.createFont();
            PdfFont font = PdfFontFactory.createFont(fontProgram, "utf-8", true);
            canvas.setFontAndSize(font, 12);
            
            canvas.beginText();
            
            //info client
             
            canvas.setTextMatrix(110, 760);
            canvas.showText(""+affi.getNom());
            
            canvas.setTextMatrix(120, 745);
            canvas.showText(""+affi.getPrenom());
            
            canvas.setTextMatrix(213, 730);
            canvas.showText(""+affi.getNumeroSS());
            
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String datestr = formatter.format(affi.getDateNaiss());
            String datej = formatter.format(new Date());
            
            canvas.setTextMatrix(170, 715);
            canvas.showText(""+datestr);
            
            //Date : 
            canvas.setTextMatrix(115, 570);
            canvas.showText(""+datej);
            
            //texte
            canvas.setTextMatrix(340, 541);
            canvas.showText(""+affi.getNom()+" "+affi.getPrenom());
            
            //texte
            canvas.setTextMatrix(390, 527);
            canvas.showText(""+affi.getLesStatutsBeneficiaire().get(0).getLeContrat().getId());
            
           
            //Garantie choisie
            canvas.setTextMatrix(180, 472);
            canvas.showText(""+gar.getLibelleGarantie());
            
            
           
            //Signature
            canvas.setTextMatrix(385, 310);
            canvas.showText("..SIGNATURE/TAMPON..");
            
            
            
            canvas.endText();
            
            
        }
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
