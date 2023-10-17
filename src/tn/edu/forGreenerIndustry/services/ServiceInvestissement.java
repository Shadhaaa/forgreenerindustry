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
import tn.edu.forgreenerindustry.entities.Investissement;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.edu.forgreenerindustry.tools.DataSource;

/**
 *
 * @author milou
 */
public class ServiceInvestissement implements IService <Investissement> {

    private Connection cnx ;
    public ServiceInvestissement() {
    this.cnx = DataSource.getInstance().getConnection();
 }

@Override
    public void ajouter (Investissement t) {
        try {
            String req = "INSERT INTO `investissement`(`id_investisseur`,`id_entreprise`, `montant`, `date_debut_investissement`,`duree_prevue`,`details`, `status`) "
                    + "VALUES ('" + t.getId_investisseur() + "','" + t.getId_entreprise() + "','" + t.getMontant() + "','" + t.getDate_debut_investissement() + "','" + t.getDuree_prevue() + "','" + t.getDetails() + "','" + t.getStatus() + "')";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
@Override
    public void modifier (Investissement t) {
    if (t == null) {
            System.out.println("Aucun investissement de cet ID!");
            return;
        }
    try {
        String req = "UPDATE `investissement` SET `id_investisseur`=? ,`id_entreprise`= ?, `montant`=? , `date_debut_investissement` = ?,`duree_prevue` =? ,`details`= ?, `status` = ? WHERE id_investissement = ?";
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setInt(1, t.getId_investisseur());
        pst.setInt(2, t.getId_entreprise());
        pst.setDouble(3, t.getMontant());
        pst.setDate(4, t.getDate_debut_investissement());
        pst.setString(5, t.getDuree_prevue());
        pst.setString(6, t.getDetails());
        pst.setInt(7, t.getStatus());
        pst.setInt(8, t.getId_investissement());   
        pst.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    }

@Override
    public void supprimer (int id ) {
        try {
        String req = "DELETE FROM investissement WHERE id_investissement = ?";
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setInt(1, id);
        int rowCount = pst.executeUpdate();
        if (rowCount > 0)
            {
                System.out.println("Investissement supprimé avec succès");
            } 
        else 
            {
                System.out.println("Aucun investissement n'a été trouvé");
            }
        } 
        catch (SQLException ex) {
        System.out.println(ex.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
   

    @Override
    public Investissement getOne (int id_inv){
    try {
        String req = "SELECT * FROM `investissement` WHERE id_investissement = ?";
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setInt(1, id_inv);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            Investissement investissement = new Investissement();
            investissement.setId_investissement(rs.getInt("id_investissement"));
            investissement.setId_investisseur(rs.getInt("id_investisseur"));
            investissement.setId_entreprise(rs.getInt("id_entreprise"));
            investissement.setMontant(rs.getDouble("montant"));
            investissement.setDate_debut_investissement(rs.getDate("date_debut_investissement"));
            investissement.setDuree_prevue(rs.getString("duree_prevue"));
            investissement.setDetails(rs.getString("details"));
            investissement.setStatus(rs.getInt("status"));
            return investissement; }
        else {
            System.out.println("Aucun investissement se trouve avec cet id = " +id_inv);
                return null;
            }
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
          //To change body of generated methods, choose Tools | Templates.
    }   
    
@Override
    public List<Investissement> getAll(Investissement t) {
      String req = "SELECT * FROM `investissement`";
      ArrayList<Investissement> investissements = new ArrayList();
    Statement stm;
    try {
        stm = this.cnx.createStatement();
    
    
        ResultSet rs=  stm.executeQuery(req);
    while (rs.next()) 
    {
            Investissement i = new Investissement();
            i.setId_investissement(rs.getInt("id_investissement"));
            i.setId_investisseur(rs.getInt("id_investisseur"));
            i.setId_entreprise(rs.getInt("id_entreprise"));
            i.setMontant(rs.getDouble("montant"));
            i.setDate_debut_investissement(rs.getDate("date_debut_investissement"));
            i.setDuree_prevue(rs.getString("duree_prevue"));
            i.setDetails(rs.getString("details"));
            i.setStatus(rs.getInt("status"));

            investissements.add(i);
    }
    } catch (SQLException ex) 
    {
        System.out.println(ex.getMessage());
    }
    return investissements;
    }
   
    
}
