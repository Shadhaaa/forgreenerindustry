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
import tn.edu.forgreenerindustry.tools.DataSource;
import tn.edu.forgreenerindustry.entities.Entreprise;
/**
 *
 * @author shadha
 */
public class ServiceEntreprise  implements IService<Entreprise>{
                private final Connection cnx ;
         
    public ServiceEntreprise(){
         this.cnx = DataSource.getInstance().getConnection();
            }
    
    public void ajouter(Entreprise entreprise) {
    try {
        // Requête SQL d'insertion pour ajouter une entreprise
        String req = "INSERT INTO `entreprise`(`nom`, `prenom`, `pdp`, `num`, `mail`, `mdp1`, `role`, " +
                     "`adresse`, `genre`, `logo`, `nom_entreprise`, `secteur`, `description`) " +
                     "VALUES ('" + entreprise.getNom() + "','" + entreprise.getPrenom() + "','" +
                     entreprise.getPdp() + "','" + entreprise.getNum() + "','" + entreprise.getMail() + "','" +
                     entreprise.getMdp1() + "','" + entreprise.getRole() + "','" + entreprise.getAdresse() + "','" +
                     entreprise.getGenre() + "','" + entreprise.getLogo() + "','" + entreprise.getNom_entreprise() + "','" +
                     entreprise.getSecteur() + "','" + entreprise.getDescription() + "')";

        // Créez un objet Statement pour exécuter la requête SQL
        Statement stm = cnx.createStatement();

        // Exécutez la requête SQL pour ajouter l'entreprise à la base de données
        stm.executeUpdate(req);

        System.out.println("Ajout entreprise effectué avec succès !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
    
    public List<Entreprise> getAll(Entreprise t) {
    String req = "SELECT * FROM `entreprise`";
    
    ArrayList<Entreprise> entreprises = new ArrayList();
    Statement stm ;
    try {
        stm = this.cnx.createStatement();
        ResultSet resultSet = stm.executeQuery(req);

        while (resultSet.next()) {
            Entreprise entreprise = new Entreprise();
            entreprise.setId_entreprise(resultSet.getInt("id_entreprise"));
            entreprise.setNom(resultSet.getString("nom"));
            entreprise.setPrenom(resultSet.getString("prenom"));
            entreprise.setPdp(resultSet.getString("pdp"));
            entreprise.setNum(resultSet.getInt("num"));
            entreprise.setMail(resultSet.getString("mail"));
            entreprise.setMdp1(resultSet.getString("mdp1"));
            entreprise.setRole(resultSet.getString("role")); 
            entreprise.setAdresse(resultSet.getString("adresse"));
            entreprise.setGenre(resultSet.getString("genre"));
            entreprise.setLogo(resultSet.getString("logo"));
            entreprise.setNom_entreprise(resultSet.getString("nom_entreprise"));
            entreprise.setSecteur(resultSet.getString("secteur"));
            entreprise.setDescription(resultSet.getString("description"));

            entreprises.add(entreprise);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return entreprises;
}
public void modifier(Entreprise entreprise) {
    try {
        String req = "UPDATE `entreprise` SET `nom`=?, `prenom`=?, `pdp`=?, `num`=?, `mail`=?, `mdp1`=?, `role`=?, `adresse`=?, `genre`=?, `logo`=?, `nom_entreprise`=?, `secteur`=?, `description`=? WHERE `id_entreprise` = ?";

        PreparedStatement statement = cnx.prepareStatement(req);
        statement.setString(1, entreprise.getNom());
        statement.setString(2, entreprise.getPrenom());
        statement.setString(3, entreprise.getPdp());
        statement.setInt(4, entreprise.getNum());
        statement.setString(5, entreprise.getMail());
        statement.setString(6, entreprise.getMdp1());
        statement.setString(7, entreprise.getRole());
        statement.setString(8, entreprise.getAdresse());
        statement.setString(9, entreprise.getGenre());
        statement.setString(10, entreprise.getLogo());
        statement.setString(11, entreprise.getNom_entreprise());
        statement.setString(12, entreprise.getSecteur());
        statement.setString(13, entreprise.getDescription());
        statement.setInt(14, entreprise.getId_entreprise());

        statement.executeUpdate();
        System.out.println("Entreprise modifiée avec succès");

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
   public List<Integer> getAllid( ) {
        List<Entreprise> users = getAll(new Entreprise());
        List<Integer> id = new ArrayList<>();

        for (Entreprise user : users) {
            id.add(user.getId_entreprise()
            );
        }

        return id;
    }
   
public  Entreprise getOne(Entreprise Entreprise) {
        
        try {
            String query = "SELECT * FROM entreprise WHERE id_entreprise = ?";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setInt(1, Entreprise.id_entreprise);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String pdp = resultSet.getString("pdp");
                int num = resultSet.getInt("num");
                String mail = resultSet.getString("mail");
                String mdp1 = resultSet.getString("mdp1");
                String role = resultSet.getString("role");
                String adresse = resultSet.getString("adresse");
                String genre = resultSet.getString("genre");
                String logo = resultSet.getString("logo");
                String nom_entreprise = resultSet.getString("nom_entreprise");
                String secteur = resultSet.getString("secteur");
                String description = resultSet.getString("description");

                Entreprise = new Entreprise(nom, prenom, pdp, num, mail, mdp1, role, adresse, genre, logo, nom_entreprise, secteur, description);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return Entreprise;
    }
   public List<String> getAllMails(Entreprise t) {
        List<Entreprise> users = getAll(t);
        List<String> mails = new ArrayList<>();

        for (Entreprise user : users) {
            mails.add(user.getMail());
        }

        return mails;
    }
}
