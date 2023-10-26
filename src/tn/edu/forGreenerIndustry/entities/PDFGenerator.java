/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.entities;

/**
 *
 * @author SYRINE
 */
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;

import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class PDFGenerator {

    public static void generatePDF(List<Reclamation> reclamationList, String outputPath) throws BadElementException, IOException {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(outputPath));
            document.open();

            // Ajoutez un titre au document
            Paragraph title = new Paragraph("Liste des Réclamations");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Chargez l'image de logo depuis un fichier (assurez-vous que l'image existe dans votre projet)
            Image logoImage = Image.getInstance("C:\\Users\\SYRINE\\OneDrive\\Documents\\esprit.png");
            logoImage.scaleAbsolute(100, 100); // Réglez la taille de l'image

// Obtenez les dimensions de la page PDF
            Rectangle pageSize = document.getPageSize();

// Réglez la position de l'image dans le coin supérieur droit
            float x = pageSize.getRight() - 120; // 120 est la largeur de l'image
            float y = pageSize.getTop() - 120; // 120 est la hauteur de l'image
            logoImage.setAbsolutePosition(x, y);
// Ajoutez l'image au document
            document.add(logoImage);
            // Ajoutez un espace entre le titre et la liste
            document.add(Chunk.NEWLINE);

            // Ajouter les réclamations au document
            for (Reclamation reclamation : reclamationList) {
                document.add(new Paragraph("Nom : " + reclamation.getNom()));
                document.add(new Paragraph("Prénom : " + reclamation.getPrenom()));
                document.add(new Paragraph("Email : " + reclamation.getEmail()));
                document.add(new Paragraph("Screenshot : " + reclamation.getScreenshot()));
                document.add(new Paragraph("Numéro de mobile : " + reclamation.getNumero_mobile()));
                document.add(new Paragraph("Date de création : " + reclamation.getDate_creation()));
                document.add(new Paragraph("Date de traitement : " + reclamation.getDate_traitement()));
                document.add(new Paragraph("Nom du Service : " + reclamation.getNomServcie()));
                document.add(new Paragraph("Description : " + reclamation.getDescription()));
                document.add(new Paragraph("Status : " + reclamation.getStatus()));
                document.add(new Paragraph("Priority: " + reclamation.getPriority()));
                // Vous pouvez ajouter plus d'informations de réclamation ici si nécessaire
                document.add(new Paragraph("------------------------------------------------------"));
            }

            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
