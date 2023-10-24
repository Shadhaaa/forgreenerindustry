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
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.edu.forGreenerIndustry.entities.Post;
import tn.edu.forGreenerIndustry.tools.DataSource;
/**
 *
 * @author mila
 */
public class ServicePost implements IService<Post> {
        private final Connection cnx ;
    
    public ServicePost(){
         this.cnx = DataSource.getInstance().getConnection();
    
}

    @Override
    public void ajouter(Post t) {
        try {
            String req = "INSERT INTO `post` (`id_entreprise`, `titre`, `typeDeContenu`, `contenu`, `date`, `image`) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = cnx.prepareStatement(req);

        // Ensure that the parameters are in the correct order
            pst.setInt(1, t.getId_entreprise());
            pst.setString(2, t.getTitre());
            pst.setString(3, t.getTypeDeContenu());
            pst.setString(4, t.getContenu());
            pst.setDate(5, t.getDate());
            pst.setString(6, t.getImage());

            pst.executeUpdate();
            System.out.println("Post added successfully");
        } catch (SQLException ex) {
            System.err.println("Error while adding a post: " + ex.getMessage());
        }
    }



    @Override
    public void modifier(Post t) {
        if (t == null) {
            System.out.println("The Post cannot be modified.");
            return;
        }
        try {
            String req = "UPDATE post SET id_entreprise = ?, titre = ?, typeDeContenu = ?, contenu = ?, date = ?, image = ? WHERE id_post = ?";
            
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, t.getId_entreprise());
            pst.setString(2, t.getTitre());
            pst.setString(3, t.getTypeDeContenu());
            pst.setString(4, t.getContenu());
            pst.setDate(5, t.getDate());
            pst.setString(6, t.getImage());
            pst.setInt(7, t.getId_post());


            pst.executeUpdate();
        }   catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        
       
    }

    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `post` WHERE `id_post` = ?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            
            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                System.out.println("Post supprimé avec succès");
            } else {
                System.out.println("Aucun post correspondant n'a été trouvé");
            }
        }   catch (SQLException ex) {
            System.err.println(ex.getMessage());
                
 
            }
    }


    @Override
    public Post getOne(int id_post) {
        try {
            String req = "SELECT * FROM `post` WHERE `id_post` = ?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id_post);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                Post post = new Post();
                post.setId_post(rs.getInt("id_post"));
                post.setId_entreprise(rs.getInt("id_entreprise"));
                post.setTitre(rs.getString("titre"));
                post.setTypeDeContenu(rs.getString("typeDeContenu"));
                post.setContenu(rs.getString("contenu"));
                post.setDate(rs.getDate("date"));
                post.setImage(rs.getString("image"));
                
                return post;
            } else {
                System.out.println("No post found with id_post = " + id_post);
                return null;
            }
        }   catch (SQLException ex) {
                System.err.println(ex.getMessage());
            return null;
            }
    }

    @Override
    public List<Post> getAll(Post t){
        
        String req = "SELECT * FROM `post`";
        List<Post> postList = new ArrayList();


    try {
        Statement stm = this.cnx.createStatement();
        ResultSet rs = stm.executeQuery(req);

        while (rs.next()) {
            Post post = new Post();
            post.setId_post(rs.getInt("id_post"));
            post.setId_entreprise(rs.getInt("id_entreprise"));
            post.setTitre(rs.getString("titre"));
            post.setTypeDeContenu(rs.getString("typeDeContenu"));
            post.setContenu(rs.getString("contenu"));
            post.setDate(rs.getDate("date"));
            post.setImage(rs.getString("image"));
            
            postList.add(post);
        }
    }       catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
    
    return postList; 
     
}

    public List<Post> getPostByTitre(String titre) {
            List<Post> posts = new ArrayList();
        try {
            String req = "SELECT * FROM `post` WHERE `titre` = ?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, titre);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Post post = new Post();
                post.setId_post(rs.getInt("id_post"));
                post.setId_entreprise(rs.getInt("id_entreprise"));
                post.setTitre(rs.getString("titre"));
                post.setTypeDeContenu(rs.getString("typeDeContenu"));
                post.setContenu(rs.getString("contenu"));
                post.setDate(rs.getDate("date"));
                post.setImage(rs.getString("image"));
                posts.add(post);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return posts;
    }

        
    
}
