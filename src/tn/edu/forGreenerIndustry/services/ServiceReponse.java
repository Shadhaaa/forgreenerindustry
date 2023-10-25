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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.edu.forGreenerIndustry.entities.Reponse;

/**
 *
 * @author SYRINE
 */
public class ServiceReponse {

    Connection cnx;

    public ServiceReponse(Connection cnx) {
        this.cnx = cnx;
    }

    public void ajouter(Reponse c) {
        
         String req = "INSERT INTO reponse (Text, status, idReclamation) VALUES (?, ?, ?)";

try (PreparedStatement ps = cnx.prepareStatement(req)) {
    // Remplacez les placeholders (?) par les valeurs réelles
    ps.setString(1, c.getText());
    ps.setString(2, c.getStatus());
    ps.setInt(3, c.getIdReclamation());

    ps.executeUpdate();
    System.out.println("Réponse ajoutée avec succès");
} catch (SQLException ex) {
    System.out.println(ex.getMessage());
}

    }

    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `reponse` WHERE `idReponse` = " + id;
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("Reponse supprimer avec succés");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public ObservableList<Reponse> getAll() {
        String rep = "SELECT * FROM `reponse`";
        ObservableList<Reponse> l = FXCollections.observableArrayList();
        Statement stm;
        try {
            stm = this.cnx.createStatement();
            ResultSet rs = stm.executeQuery(rep);

            while (rs.next()) {
                Reponse m = new Reponse();
                m.setIdReponse(rs.getInt(1));
                m.setText(rs.getString(2));
                m.setStatus(rs.getString(3));
                m.setIdReclamation(rs.getInt(4));

                l.add(m);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(l + "\n");
        return l;
    }

    public ObservableList<Reponse> getAllForrecl(int id) {
        String rep = "SELECT * FROM `reponse` WHERE `idReclamation` = " + id;
        ObservableList<Reponse> l = FXCollections.observableArrayList();
        Statement stm;
        try {
            stm = this.cnx.createStatement();
            ResultSet rs = stm.executeQuery(rep);

            while (rs.next()) {
                Reponse m = new Reponse();
                m.setIdReponse(rs.getInt(1));
                m.setText(rs.getString(2));
                m.setStatus(rs.getString(3));
                m.setIdReclamation(rs.getInt(4));

                l.add(m);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(l + "\n");
        return l;
    }

  public void modifier(Reponse m) {
    try {
        String req = "UPDATE `reponse` SET `Text`=?, `status`=?, `idReclamation`=? WHERE `idReponse`=?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setString(1, m.getText());
        st.setString(2, m.getStatus());
        st.setInt(3, m.getIdReclamation());
        st.setInt(4, m.getIdReponse()); // Ajoutez cet argument pour spécifier quelle réponse vous voulez mettre à jour

        st.executeUpdate();
        System.out.println("Reponse Modifié avec succès");
    } catch (SQLException ex) {
        System.out.println(ex);
    }
}

}
