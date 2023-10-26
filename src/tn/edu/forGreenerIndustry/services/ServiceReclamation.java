/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.services;

import com.itextpdf.text.BadElementException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;

import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;

import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import tn.edu.forGreenerIndustry.entities.PDFGenerator; // Assurez-vous que le chemin est correct.

import tn.edu.forGreenerIndustry.entities.Reclamation;

/**
 *
 * @author SYRINE
 */
public class ServiceReclamation {

    private Connection cnx;
    private List<String> forbiddenWords=Arrays.asList("fuck", "mot2", "expression1", "expression2");;

  

    public ServiceReclamation(Connection cnx) {
        this.cnx = cnx;
    }

    public void ajouter(Reclamation m) {
        try {
          String req = "INSERT INTO `reclamation` (`idReclamation`, `nom`, `prenom`, `email`, `screenshot`, `numero_mobile`, `date_creation`, `date_traitement`, `description`, `status`, `nomServcie`, `priority`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";



            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, m.getIdReclamation());
            st.setString(2, m.getNom());
            st.setString(3, m.getPrenom());
            st.setString(4, m.getEmail());
            st.setString(5, m.getScreenshot());
            st.setString(6, m.getNumero_mobile());
            st.setDate(7, m.getDate_creation());
            st.setDate(8, m.getDate_traitement());
            st.setString(9, m.getDescription());
            st.setString(10, m.getStatus());
            st.setString(11, m.getNomServcie());
          
            st.setString(12, m.getPriority());


            st.executeUpdate();
            System.out.println("Reclamation ajoutée avec succès");
            MailService sem = new MailService();
            sem.sendEmail("Mail", "Ajouter Reclamation", "un nouvelle reclamation a ete Ajouter");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimer(int id) {
        System.out.println(id);
        try {
            String req = "DELETE FROM `reclamation` WHERE `idReclamation` =" + id;
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("recalamtion supprimer avec succés");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void modifier(Reclamation m) {

        try {
            String req = "UPDATE `reclamation` SET `reclamation` ( `nom`, `prenom`, `email`, `screenshot`, `numero_mobile`, `date_creation`, `date_traitement`, `description`, `status`, `nomServcie`, `priority`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = cnx.prepareStatement(req);

         
            st.setString(2, m.getNom());
            st.setString(3, m.getPrenom());
            st.setString(4, m.getEmail());
            st.setString(5, m.getScreenshot());
            st.setString(6, m.getNumero_mobile());
            st.setDate(7, m.getDate_creation());
            st.setDate(8, m.getDate_traitement());
            st.setString(9, m.getDescription());
            st.setString(10, m.getStatus());
            st.setString(11, m.getNomServcie());
            st.setString(12, m.getPriority());
            st.executeUpdate();
            System.out.println("recalamtion Modifié avec succés");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void modifierStatus(int id, String m) {

        try {
            String req = "UPDATE `reclamation` SET `status`=?  WHERE `idReclamation` =" + id;
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, m);

            st.executeUpdate();
            System.out.println("status Modifié avec succés");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public ObservableList<Reclamation> getAll() {
        String rep = "Select * from reclamation  ";
        ObservableList<Reclamation> l = FXCollections.observableArrayList();

        try {
            PreparedStatement statement = cnx.prepareStatement(rep);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Reclamation m = new Reclamation();

                m.setIdReclamation(rs.getInt(1));
                m.setNom(rs.getString(2));
                m.setPrenom(rs.getString(3));
                m.setEmail(rs.getString(4));
                m.setScreenshot(rs.getString(5));
                m.setNumero_mobile(rs.getString(6));
                m.setDate_creation(rs.getDate(7));
                m.setDate_traitement(rs.getDate(8));
                m.setDescription(rs.getString(9));
                m.setStatus(rs.getString(10));
                m.setNomServcie(rs.getString(11));
                m.setPriority(rs.getString(13));
                l.add(m);
            }

            statement.close(); // Close the statement
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return l;
    }

    // ...
    public void genererPDF(List<Reclamation> reclamationList, String outputPath) throws BadElementException, IOException {
        PDFGenerator.generatePDF(reclamationList, outputPath);
        System.out.println("Le PDF a été généré avec succès.");
    }

}
