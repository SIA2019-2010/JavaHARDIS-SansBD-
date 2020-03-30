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
@WebServlet(name = "PdfExportGarantie", urlPatterns = {"/pdfexportpech"})
public class PdfExportGarantie extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            canvas.showText("Alexis");
            
            canvas.setTextMatrix(120, 745);
            canvas.showText("Baillieu");
            
            canvas.setTextMatrix(213, 730);
            canvas.showText("3131342342213");
            
            canvas.setTextMatrix(170, 715);
            canvas.showText("26/12/1996");
            
            //Date : 
            canvas.setTextMatrix(115, 570);
            canvas.showText("30 Mars 2020    /03/2020");
            
            //texte
            canvas.setTextMatrix(340, 541);
            canvas.showText("Baillieu Alexis");
            
            //texte
            canvas.setTextMatrix(390, 527);
            canvas.showText("131455");
            
           
            //Garantie choisie
            canvas.setTextMatrix(180, 472);
            canvas.showText("Hospitalisation des yeux verts uniquement");
            
            
           
            //Signature
            canvas.setTextMatrix(385, 310);
            canvas.showText("..SIGNATURE/TAMPON..");
            
            
            
            canvas.endText();
            
            
        }
                
        
        
        
        //super.doGet(request, response); //To change body of generated methods, choose Tools | Templates.
    }
    
 
    
}
