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
import tn.edu.forgreenerindustry.tools.DataSource;
import tn.edu.forgreenerindustry.entities.User;

/**
 *
 * @author shadha
 */

public class ServiceUser implements IService<User>{
            private final Connection cnx ;
            public ServiceUser(){
         this.cnx = DataSource.getInstance().getConnection();

 }
    public void ajouter (User t) {
     try {
        
       String req = "INSERT INTO `user`(`id_user`, `nom`, `prenom`, `pdp`, `num`, `mail`, `mdp1`, `role`, `adresse`, `genre`) VALUES ('" + t.getId_user() + "','" + t.getNom() + "','" + t.getPrenom() + "','" + t.getPdp() + "','" + t.getNum() + "','" + t.getMail() + "','" + t.getMdp1() + "','" + t.getRole() + "','" + t.getAdresse() + "','" + t.getGenre() + "')";
Statement stm = cnx.createStatement();

            stm.executeUpdate(req);
            System.out.println("ajouter avec succee ! ");
           

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   
     } 
    
   public void modifier(User utilisateurModifie) {
    try {
        String req = "UPDATE `user` SET `nom`=?, `prenom`=?, `pdp`=?, `num`=?, `mail`=?, `mdp1`=?, `role`=?, `adresse`=?, `genre`=? WHERE `id_user` = ?";
        
        PreparedStatement statement = cnx.prepareStatement(req);
        statement.setString(1, utilisateurModifie.getNom());
        statement.setString(2, utilisateurModifie.getPrenom());
        statement.setString(3, utilisateurModifie.getPdp());
        statement.setInt(4, utilisateurModifie.getNum());
        statement.setString(5, utilisateurModifie.getMail());
        statement.setString(6, utilisateurModifie.getMdp1());
        statement.setString(7, utilisateurModifie.getRole());
        statement.setString(8, utilisateurModifie.getAdresse());
        statement.setString(9, utilisateurModifie.getGenre());
        statement.setInt(10, utilisateurModifie.getId_user());

        statement.executeUpdate();
        System.out.println("Modifié avec succès");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }



    }
    
    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `user` WHERE `id_user` ='"+ id +"';" ;
            PreparedStatement pst = cnx.prepareStatement(req);
            
            int rowCount = pst.executeUpdate();

            if (rowCount > 0) {
                System.out.println("Utilisateur supprimé avec succès");
            } else {
                System.out.println("Aucun utilisateur correspondant n'a été trouvé");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    
    @Override
    public User getOne(User t) {
    User user = null;

    try {
        String query = "SELECT * FROM user WHERE id_user = ?";
        PreparedStatement statement = cnx.prepareStatement(query);
        statement.setInt(1, t.getId_user());

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int idUser = resultSet.getInt("id_user");
            String nom = resultSet.getString("nom");
            String prenom = resultSet.getString("prenom");
            String pdp = resultSet.getString("pdp");
            int num = resultSet.getInt("num");
            String mail = resultSet.getString("mail");
            String mdp1 = resultSet.getString("mdp1");
            String role1 = resultSet.getString("role");
            String adresse = resultSet.getString("adresse");
            String genre1 = resultSet.getString("genre");
           
            
            user = new User(idUser, nom, prenom,pdp , num,mail , mdp1 , role1, adresse , genre1);
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
            return user;


    
}
    
     @Override
    public List<User> getAll(User t) {
        String req = "SELECT * FROM `user`";
    
        ArrayList<User> users = new ArrayList();
        Statement stm ;
    try {
        stm = this.cnx.createStatement();
        ResultSet resultSet=  stm.executeQuery(req);


        while (resultSet.next()) {


    User user = new User();
    user.setId_user(resultSet.getInt("id_user"));
    user.setNom(resultSet.getString("nom"));
    user.setPrenom(resultSet.getString("prenom"));
    user.setPdp(resultSet.getString("pdp"));
    user.setNum(resultSet.getInt("num"));
    user.setMail(resultSet.getString("mail"));
    user.setMdp1(resultSet.getString("mdp1"));
    user.setRole(resultSet.getString("role"));
    user.setAdresse(resultSet.getString("adresse"));
    user.setGenre(resultSet.getString("genre"));
   

    users.add(user);
}
        } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return users; 
            }
    
    public boolean isUniqueEmail(User user, List<User> getAllUsers) {
    for (User storedUser : getAllUsers) {
        if (storedUser != user && storedUser.getMail().equals(user.getMail())) {
            // L'utilisateur a été trouvé parmi les utilisateurs stockés et l'adresse e-mail n'est pas unique
            return false;
        }
    }
    // L'adresse e-mail est unique
    return true;
}
      public List<String> getAllMails(User t) {
        List<User> users = getAll(t);
        List<String> mails = new ArrayList<>();

        for (User user : users) {
            mails.add(user.getMail());
        }

        return mails;
    }
        public List<Integer> getAllid( ) {
        List<User> users = getAll(new User());
        List<Integer> id = new ArrayList<>();

        for (User user : users) {
            id.add(user.getId_user());
        }

        return id;
    }
           
            }
