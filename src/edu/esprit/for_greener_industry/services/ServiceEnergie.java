/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.for_greener_industry.services;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.esprit.for_greener_industry.enteties.Energie;
import edu.esprit.for_greener_industry.tools.DataSource;
/**
 *
 * @author ritha
 */
public class ServiceEnergie implements IService<Energie>{
    Connection cnx ;
    public ServiceEnergie(){
        this.cnx= DataSource.getInstance().getConnection();
    }
    @Override
    public void ajouter(Energie e) {
        String req = "INSERT INTO energie(libellet, pollution_par_kwh) VALUES ('"+e.getLibellet()+"','"+e.getPollution_par_kw()+"')";
        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Energie e) {
        String req = "UPDATE `energie` SET `libellet`='"+e.getLibellet()+"',`pollution_par_kwh`='"+e.getPollution_par_kw()+"' WHERE `id`='"+e.getId()+"'";
        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
    }
    @Override
    public void supprimer(int id) {
        String req = "DELETE FROM `energie` WHERE id='"+id+"'";
        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
    }

    @Override
    public Energie getOne(Energie e) {
         String req = "Select * from `energie` where id = '"+e.getId()+"'";
        try {
            Statement stm=cnx.createStatement();
            stm.execute(req);
            ResultSet rs=  stm.executeQuery(req);
            e.setId(rs.getInt(1));
            e.setLibellet(rs.getString(2));
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        return e;
    }

    @Override
    public List<Energie> getAll(Energie t) {
        String req="select * from `energie`";
        ArrayList<Energie> energies=new ArrayList();
        Statement stm;
        try {
            stm=this.cnx.createStatement();
            ResultSet rs=stm.executeQuery(req);
            while (rs.next()) {
                Energie e = new Energie();
                e.setId(rs.getInt(1));
                e.setLibellet(rs.getString(2));
                e.setPollution_par_kw(rs.getFloat(3));
                energies.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return energies;
    }
   
    public boolean energieunique(Energie e){
        List<Energie> listener = new ArrayList();
        ServiceEnergie Service =new ServiceEnergie();
        listener = Service.getAll(e);
        for(Energie e1 : listener){
            if(e1.getLibellet().equals(e.getLibellet())){
            return false;
            }
        }
        return true;
    }

    
}
