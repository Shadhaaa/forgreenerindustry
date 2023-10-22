/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.edu.forGreenerIndustry.entities.Commentaires;
import tn.edu.forGreenerIndustry.tools.DataSource;
/**
 *
 * @author mila
 */
public class ServiceCommentaires implements IService<Commentaires> {
     private final Connection cnx ;
    
    public ServiceCommentaires(){
         this.cnx = DataSource.getInstance().getConnection();
    
}

    @Override
public void ajouter(Commentaires t) {
    try {
        String req = "INSERT INTO `commentaires`(`id_user`, `id_post`, `contenu`, `statut`) "
                + "VALUES (?, ?, ?, ?)";

        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setInt(1, t.getId_user());
        pst.setInt(2, t.getId_post());
        pst.setString(3, t.getContenu());
        pst.setString(4, t.getStatut());

        pst.executeUpdate();
        System.out.println("Commentaire ajouté avec succès.");
    } catch (SQLException ex) {
        System.out.println("Erreur lors de l'ajout du commentaire : " + ex.getMessage());
    }
}



    @Override
    public void modifier(Commentaires t) {
        try {
            String req = "UPDATE `commentaires` SET  `id_user` = ?, `id_post` = ?, `contenu` = ?, `date` = ?, `statut` = ? "
                    + "WHERE `id_commentaire` = ?";

            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(2, t.getId_user());
            pst.setInt(3, t.getId_post());
            pst.setString(4, t.getContenu());       
            pst.setString(5, t.getStatut());
            

            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `commentaires` WHERE `id_commentaire` = ?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            int rowCount = pst.executeUpdate();

            if (rowCount > 0) {
                System.out.println("Commentaire supprimé avec succès");
            } else {
                System.out.println("Aucun commentaire correspondant n'a été trouvé");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public Commentaires getOne(int id_commentaire) {
        try {
            String req = "SELECT * FROM `commentaires` WHERE `id_commentaire` = ?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id_commentaire);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int id_user = rs.getInt("id_user");
                int id_post = rs.getInt("id_post");
                String contenu = rs.getString("contenu");
                String statut = rs.getString("statut");

                Commentaires comment = new Commentaires(id_commentaire, id_user, id_post, contenu, statut);
                return comment;
            } else {
                System.out.println("No comment found with id_commentaire = " + id_commentaire);
                return null;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }


    @Override
    public List<Commentaires> getAll(Commentaires t) {
        String req = "SELECT * FROM `commentaires`";
        ArrayList<Commentaires> commentairesList = new ArrayList();
        try {
        Statement stm = this.cnx.createStatement();
        ResultSet rs = stm.executeQuery(req);

        while (rs.next()) {
            int id_commentaire = rs.getInt("id_commentaire");
            int id_user = rs.getInt("id_user");
            int id_post = rs.getInt("id_post");
            String contenu = rs.getString("contenu");
            String statut = rs.getString("statut");

            Commentaires commentaire = new Commentaires(id_commentaire, id_user, id_post, contenu, statut);
            commentairesList.add(commentaire);
        }
    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return commentairesList;
    }

   
}
