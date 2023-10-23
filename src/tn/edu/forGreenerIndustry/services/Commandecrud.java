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
import tn.edu.forGreenerIndustry.entities.Commande;
import tn.edu.forgreenerindustry.utilities.Myconnection;

/**
 *
 * @author ACHREF
 */
public class Commandecrud {
    Connection cnx2;
    
    public Commandecrud(){
        cnx2 = Myconnection.getInstance().getCnx();  
    }
    public void ajouterCommande(){
        try {
            String requete = "INSERT INTO Commande (client_id,date_commande,statut_commande,montant_total,adresse_livraison,date_livraison,mode_paiement)"
                    + "VALUES ('19', '2023-11-04', 'en attente', '99' , 'Tunis' , '2023-12-04' , 'cash')";
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("Commande ajoutée avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}
    public void ajouterCommande2(Commande C){
         
        try {
            String requete2 = "INSERT INTO Commande (client_id,date_commande,panierId,montant_total,adresse_livraison,date_livraison,mode_paiement)"
                    + "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
           pst.setInt(1, C.getClientId()); 
           pst.setDate(2, new java.sql.Date(C.getDateCommande().getTime())); 
           pst.setInt(3, C.getPanierId()); 
           pst.setDouble(4, C.getMontantTotal()); 
           pst.setString(5, C.getAdresseLivraison()); 
           pst.setDate(6, new java.sql.Date(C.getDateLivraison().getTime())); 
           pst.setString(7, C.getModePaiement()); 
           pst.executeUpdate();
           System.out.println("Votre commande est ajoutée");

            
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        
        }

}
     public List<Commande> afficherCommandes(){
         List<Commande> myList = new ArrayList<Commande>();
        try {
            String requete3 = "SELECT * FROM commande " ;
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3); 
            while(rs.next()){
                Commande c = new Commande();
                c.setCommandeId(rs.getInt(1));
                c.setClientId(rs.getInt(2));
                c.setDateCommande(rs.getDate(3));
                c.setPanierId(rs.getInt("panierId"));
                c.setMontantTotal(rs.getDouble("montant_total"));
                c.setAdresseLivraison(rs.getString("adresse_livraison"));
                c.setDateLivraison(rs.getDate("date_livraison"));
                c.setModePaiement(rs.getString("mode_paiement"));
                myList.add(c);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList ;
}
     public void supprimerCommande(int commandeId) {
    try {
        String requete = "DELETE FROM Commande WHERE commande_id = ?";
        PreparedStatement pst = cnx2.prepareStatement(requete);
        pst.setInt(1, commandeId);
        int rowCount = pst.executeUpdate();

        if (rowCount > 0) {
            System.out.println("Commande supprimée avec succès");
        } else {
            System.out.println("Aucune commande correspondante n'a été trouvée");
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}

public void modifierCommande(Commande C) {
    try {
        // Vérification que la connexion n'est pas null
        if (cnx2 == null) {
            System.err.println("La connexion à la base de données est nulle.");
            return;
        }

        // Vérification que l'objet Commande C n'est pas null
        if (C == null) {
            System.err.println("L'objet Commande est nul.");
            return;
        }

        String requete = "UPDATE Commande SET client_id=?, date_commande=?, panierId=?, montant_total=?, adresse_livraison=?, date_livraison=?, mode_paiement=? WHERE commande_id=?";
        PreparedStatement pst = cnx2.prepareStatement(requete);
        pst.setInt(1, C.getClientId());
        if (C.getDateCommande() != null) {
    pst.setDate(2, new java.sql.Date(C.getDateCommande().getTime()));
} else {
    pst.setNull(2, java.sql.Types.DATE);
}

        pst.setInt(3, C.getPanierId());
        pst.setDouble(4, C.getMontantTotal());
        pst.setString(5, C.getAdresseLivraison());
        if (C.getDateLivraison() != null) {
    pst.setDate(6, new java.sql.Date(C.getDateLivraison().getTime()));
} else {
    pst.setNull(6, java.sql.Types.DATE);
}

        pst.setString(7, C.getModePaiement());
        pst.setInt(8, C.getCommandeId());

        int rowCount = pst.executeUpdate();

        if (rowCount > 0) {
            System.out.println("Commande modifiée avec succès");
        } else {
            System.out.println("Aucune commande correspondante n'a été trouvée");
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}

   public List<Commande> readAll() {
        String requete = "SELECT * FROM commande"; 
        List<Commande> list = new ArrayList<Commande>();

        try {
            Statement st = new Myconnection().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()) {
                Commande commande = new Commande();
                commande.setCommandeId(rs.getInt("commande_id"));
                commande.setClientId(rs.getInt("client_id"));
                commande.setDateCommande(rs.getDate("date_commande"));
                commande.setPanierId(rs.getInt("panierId"));
                commande.setMontantTotal(rs.getDouble("montant_total"));
                commande.setAdresseLivraison(rs.getString("adresse_livraison"));
                commande.setDateLivraison(rs.getDate("date_livraison"));
                commande.setModePaiement(rs.getString("mode_paiement"));
                list.add(commande);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Commandecrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    

}


    

