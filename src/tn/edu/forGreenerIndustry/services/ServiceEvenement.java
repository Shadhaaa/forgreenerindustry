/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forgreenerindustry.services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.edu.forgreenerindustry.entities.Evenement;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.edu.forgreenerindustry.entities.Investissement;
import tn.edu.forgreenerindustry.tools.DataSource;

/**
 *
 * @author milou
 * 
 */

public class ServiceEvenement implements IService <Evenement> {
    private final Connection cnx ;
    
    public ServiceEvenement() {
    this.cnx = DataSource.getInstance().getConnection();
 }
    private ServiceEvenement serviceEvenement;

    
    @Override
    public void ajouter (Evenement t) {
        try {
            String req = "INSERT INTO `evenement`(`id_entreprise`, `id_participant`, `titre_evenement`,`date_evenement`,`QRcode`,`image_evenement`, `lieu_evenement`, `description_evenement`, `liste_participants` ) "
                    + "VALUES ('" + t.getId_entreprise() + "','" + t.getId_participant() + "','" + t.getTitre_evenement() + "','" + t.getDate_evenement() + "','" + t.getQRcode() + "','" + t.getImage_evenement() + "','" + t.getLieu_evenement() + "','" + t.getDescription_evenement() + "','" + t.getListe_participants() + "')";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
     
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    
    
    @Override
    public void modifier (Evenement t) {
        if (t == null) {
            System.out.println("Aucun evenement de cet ID!");
            return;
        }
       try {
        String req = "UPDATE evenement SET id_entreprise = ?, id_participant = ?, titre_evenement = ?, date_evenement = ?, QRcode = ?, image_evenement = ?, lieu_evenement = ?, description_evenement = ?, liste_participants = ? WHERE id_evenement = ?";
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setInt(1, t.getId_entreprise());
        pst.setInt(2, t.getId_participant());
        pst.setString(3, t.getTitre_evenement());
        pst.setDate (4, t.getDate_evenement());
        pst.setString(5, t.getQRcode());
        pst.setString(6, t.getImage_evenement());
        pst.setString(7, t.getLieu_evenement());
        pst.setString(8, t.getDescription_evenement());
        pst.setString(9, t.getListe_participants());
        pst.setInt(10, t.getId_evenement());
        pst.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }//To change body of generated methods, choose Tools | Templates.
    }
   
    
    @Override
    public void supprimer (int id ) {
        try {
        String req = "DELETE FROM evenement WHERE id_evenement = ?";
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setInt(1, id);
        int rowCount = pst.executeUpdate();
        if (rowCount > 0)
            {
                System.out.println("Evenement supprimé avec succès");
            } 
        else 
            {
                System.out.println("Aucun évenement n'a été supprimé");
            }
        } 
        catch (SQLException ex) {
        System.out.println(ex.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    @Override
    public Evenement getOne (int id_event ){
    try {
        String req = "SELECT * FROM evenement WHERE id_evenement = ?";
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setInt(1, id_event);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            Evenement evenement = new Evenement();
            evenement.setId_evenement(rs.getInt("id_evenement"));
            evenement.setId_entreprise(rs.getInt("id_entreprise"));
            evenement.setId_participant(rs.getInt("id_participant"));
            evenement.setTitre_evenement(rs.getString("titre_evenement"));
            evenement.setDate_evenement(rs.getDate("date_evenement"));
            evenement.setQRcode(rs.getString("QRcode"));
            evenement.setImage_evenement(rs.getString("image_evenement"));
            evenement.setLieu_evenement(rs.getString("lieu_evenement"));
            evenement.setDescription_evenement(rs.getString("description_evenement"));
            evenement.setListe_participants(rs.getString("liste_participants"));
            return evenement;
            }
         else {
                System.out.println("Aucun événement trouvé avec l'ID = " +id_event);
                return null;
            }
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
          //To change body of generated methods, choose Tools | Templates.
    }   
    
    
    
   
    @Override
    public List<Evenement> getAll (Evenement t) {
      String req = "SELECT * FROM `evenement`";
      ArrayList<Evenement> evenements = new ArrayList();
    Statement stm;
    try {
        stm = this.cnx.createStatement();
        ResultSet rs=  stm.executeQuery(req);
    while (rs.next()) 
    {
        Evenement e = new Evenement();
            e.setId_evenement(rs.getInt("id_evenement"));
            e.setId_entreprise(rs.getInt("id_entreprise"));
            e.setId_participant(rs.getInt("id_participant"));
            e.setTitre_evenement(rs.getString("titre_evenement"));
            e.setDate_evenement(rs.getDate("date_evenement"));
            e.setQRcode(rs.getString("QRcode"));
            e.setImage_evenement(rs.getString("image_evenement"));
            e.setLieu_evenement(rs.getString("lieu_evenement"));
            e.setDescription_evenement(rs.getString("description_evenement"));
            e.setListe_participants(rs.getString("liste_participants"));

            evenements.add(e);
    }
    } catch (SQLException ex) 
    {
        System.out.println(ex.getMessage());
    }
    return evenements;
    }

    public void ajouter(Investissement i1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

