/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.for_greener_industry.services;

import edu.esprit.for_greener_industry.enteties.Catégorie;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.esprit.for_greener_industry.enteties.Véhicule;
import edu.esprit.for_greener_industry.enteties.Produit;
import edu.esprit.for_greener_industry.tools.DataSource;
/**
 *
 * @author ritha
 */
public class Servicevéhicule implements IService<Véhicule> {
    Connection cnx ;
    public Servicevéhicule(){
        this.cnx= DataSource.getInstance().getConnection();
    }

    @Override
    public void ajouter(Véhicule v) {
         String req = "insert into `véhicule` (`libellet`,`pollution/km`,`marque`)  values ('"+v.getLibellet()+"','"+v.getPollution_par_km()+"','"+v.getMarque()+"')";
        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Véhicule v) {
        String req = "update `véhicule` set libellet='"+v.getLibellet()+"',marque='"+v.getMarque()+"',pollution/km='"+v.getPollution_par_km()+"' where id = '"+v.getId()+"'";
        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
       String req = "delete from `véhicule`  where id = '"+id+"'";
        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
    }

    @Override
    public Véhicule getOne(Véhicule v) {
          String req = "Select * from `véhicule` where id = '"+v.getId()+"'";
        try {
            Statement stm=cnx.createStatement();
            stm.execute(req);
            ResultSet rs=  stm.executeQuery(req);
            v.setId(rs.getInt(1));
            v.setLibellet(rs.getString(2));
            v.setPollution_par_km(rs.getFloat(3));
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        return v;
    }

    @Override
    public List<Véhicule> getAll(Véhicule t) {
          String req="select * from `véhicule`";
        ArrayList<Véhicule> véhicules=new ArrayList();
        Statement stm;
        try {
            stm=this.cnx.createStatement();
            ResultSet rs=stm.executeQuery(req);
            while (rs.next()) {
                Véhicule v = new Véhicule();
                v.setId(rs.getInt(1));
                v.setLibellet(rs.getString(2));             
                véhicules.add(v);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return véhicules;
    }
}
    

