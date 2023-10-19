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
import edu.esprit.for_greener_industry.enteties.Catégorie;
import edu.esprit.for_greener_industry.enteties.Produit;
import edu.esprit.for_greener_industry.tools.DataSource;
/**
 *
 * @author ritha
 */
public class ServiceCatégorie implements IService<Catégorie>{
    Connection cnx ;
    public ServiceCatégorie(){
        this.cnx= DataSource.getInstance().getConnection();
    }

    @Override
    public void ajouter(Catégorie c) {
        String req = "insert into `catégorie` (`libellet`)  values ('"+c.getLibellet()+"')";
        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public void modifier(Catégorie c) {
       String req = "update `catégorie` set libellet='"+c.getLibellet()+"' where id = '"+c.getId()+"'";
        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        String req = "delete from `catégorie` where id = '"+id+"'";
        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
    }

    @Override
    public Catégorie getOne(Catégorie c) {
         String req = "Select * from `catégorie` where id = '"+c.getId()+"'";
        try {
            Statement stm=cnx.createStatement();
            stm.execute(req);
            ResultSet rs=  stm.executeQuery(req);
            c.setId(rs.getInt(1));
            c.setLibellet(rs.getString(2));
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        return c;
    }

    @Override
    public List<Catégorie> getAll(Catégorie t) {
        String req="select * from `catégorie`";
        ArrayList<Catégorie> catégories=new ArrayList();
        Statement stm;
        try {
            stm=this.cnx.createStatement();
            ResultSet rs=stm.executeQuery(req);
            while (rs.next()) {
                Catégorie c = new Catégorie();
                c.setId(rs.getInt(1));
                c.setLibellet(rs.getString(2));             
                catégories.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return catégories;
    }
    
    public boolean catégorieunique(Catégorie c){
        List<Catégorie> listcat = new ArrayList();
        ServiceCatégorie Service =new ServiceCatégorie();
        listcat = Service.getAll(c);
        for(Catégorie c1 : listcat){
            if(c.getLibellet().equals(c1.getLibellet())){
            return false;
            }
        }
        return true;
    }
}
    

