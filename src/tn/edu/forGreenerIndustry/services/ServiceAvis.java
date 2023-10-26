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
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.edu.forGreenerIndustry.entities.Avis;

/**
 *
 * @author dell
 */
public class ServiceAvis {

    Connection cnx;

    public ServiceAvis(Connection cnx) {
        this.cnx = cnx;
    }

    public void ajouter(Avis c) {
        try {
            String req = "INSERT INTO avis(detailAvisService, noteService, nomService) VALUES (?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, c.getDetailAvisService());
            ps.setInt(2, c.getNoteService());
            ps.setString(3, c.getNomService());

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ObservableList<Avis> getAllAvis() {
        ObservableList<Avis> avisList = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM avis";
            Statement statement = cnx.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int idAvis = resultSet.getInt("idAvis");
                String detailAvisService = resultSet.getString("detailAvisService");
                int noteService = resultSet.getInt("noteService");
                String nomService = resultSet.getString("nomService");

                Avis avis = new Avis(idAvis, detailAvisService, noteService, nomService);
                avisList.add(avis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return avisList;
    }

    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `avis` WHERE `idAvis` = " + id;
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("Avis supprimer avec succés");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }


}
