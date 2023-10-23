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
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.edu.forgreenerindustry.entities.Panier;
import tn.edu.forgreenerindustry.utilities.Myconnection;

/**
 *
 * @author ACHREF
 */
public class Paniercrud {
     Connection cnx;
    
    public Paniercrud() {
        cnx = Myconnection.getInstance().getCnx();
    }

    public void ajouterPanier(Panier panier) {
        try {
            String requete = "INSERT INTO Panier (clientId, produitId, quantite, prix, total, Nomproduit) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, panier.getClientId());
            pst.setInt(2, panier.getProduitId());
            pst.setInt(3, panier.getQuantite());
            pst.setDouble(4, panier.getPrix());
            pst.setDouble(5, panier.getTotal());
            pst.setString(6, panier.getNomproduit());
            pst.executeUpdate();
            System.out.println("Panier ajouté avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public List<Panier> afficherPaniers() {
        List<Panier> paniers = new ArrayList<Panier>();
        try {
            String requete = "SELECT * FROM Panier";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Panier panier = new Panier();
                panier.setPanierId(rs.getInt("panierId"));
                panier.setClientId(rs.getInt("clientId"));
                panier.setProduitId(rs.getInt("produitId"));
                panier.setQuantite(rs.getInt("quantite"));
                panier.setPrix(rs.getDouble("prix"));
                panier.setTotal(rs.getDouble("total"));
                panier.setNomproduit(rs.getString("Nomproduit"));
                paniers.add(panier);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return paniers;
    }

    public void supprimerPanier(int panierId) {
        try {
            String requete = "DELETE FROM Panier WHERE panierId = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, panierId);
            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                System.out.println("Panier supprimé avec succès");
            } else {
                System.out.println("Aucun panier correspondant n'a été trouvé");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

        public void modifierPanier(Panier panier) {
        try {
            String requete = "UPDATE Panier SET clientId=?, produitId=?, quantite=?, prix=?, total=?, Nomproduit=? WHERE panierId=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, panier.getClientId());
            pst.setInt(2, panier.getProduitId());
            pst.setInt(3, panier.getQuantite());
            pst.setDouble(4, panier.getPrix());
            pst.setDouble(5, panier.getTotal());
            pst.setString(6, panier.getNomproduit());
            pst.setInt(7, panier.getPanierId());
            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                System.out.println("Panier modifié avec succès");
            } else {
                System.out.println("Aucun panier correspondant n'a été trouvé");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        

    
}
        public List<Panier> readAllPaniers() {
    String requete = "SELECT * FROM panier"; 
    List<Panier> list = new ArrayList<Panier>();

    try {
        Statement st = new Myconnection().getCnx().createStatement();
        ResultSet rs = st.executeQuery(requete);
        
        while (rs.next()) {
            Panier panier = new Panier();
            panier.setPanierId(rs.getInt("panierId"));
            panier.setClientId(rs.getInt("clientId"));
            panier.setProduitId(rs.getInt("produitId"));
            panier.setQuantite(rs.getInt("quantite"));
            panier.setPrix(rs.getDouble("prix"));
            panier.setTotal(rs.getDouble("total"));
            panier.setNomproduit(rs.getString("Nomproduit"));
            list.add(panier);
        }
    } catch (SQLException ex) {
        Logger.getLogger(Paniercrud.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return list;
}
       


}

