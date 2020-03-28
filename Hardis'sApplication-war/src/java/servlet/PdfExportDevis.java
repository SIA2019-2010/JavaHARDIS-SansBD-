/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Sharon a dit : essayer de mettre dans la servlet de base avec protected void do action et appeler la methode doGet
@WebServlet(name = "PdfExportDevis", urlPatterns = {"/pdfexport"})
public class PdfExportDevis extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            
            canvas.setTextMatrix(0, 0);
            canvas.showText("Origine");
            
            //iddevis
            canvas.setTextMatrix(470, 790);
            canvas.showText("222222");
            
            //info client
             
            canvas.setTextMatrix(0, 550);
            canvas.showText("Alexis");
            
            canvas.setTextMatrix(0, 530);
            canvas.showText("Baillieu");
            
            canvas.setTextMatrix(0, 510);
            canvas.showText("3131342342213");
            
            //info produit
            
            canvas.setTextMatrix(0, 460);
            canvas.showText("Crystal Premium");
            
            //liste garantie choisies dans le produit
            int top=380;
            List<String> lestr=new ArrayList();
            lestr.add("zizi");
            lestr.add("pipi");
            lestr.add("caca");
            lestr.add("AssuranceSante");
            lestr.add("hospitalisation");
            lestr.add("dentition");
            lestr.add("joue de la guitare c'est vachement cool");
            
            for(String str : lestr){
                canvas.setTextMatrix(0, top);
                canvas.showText(""+str);
                 
                top-=20;
            }
            
            //le prix
            canvas.setTextMatrix(300, 200);
            canvas.showText("121 euros TTC");
            
            
            //Le lien : 
            canvas.setTextMatrix(0, 150);
            canvas.showText("localhost:8080/Hardis_sApplication-war/Page?action=PageDevisInformationsSupplementaire&iddevis=3");
            
            
            
            canvas.endText();
            
            
        }
                
        
        
        
        //super.doGet(request, response); //To change body of generated methods, choose Tools | Templates.
    }
    
 
    
}
