/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.for_greener_industry.services;
import edu.esprit.for_greener_industry.enteties.Catégorie;
import edu.esprit.for_greener_industry.enteties.Energie;
import edu.esprit.for_greener_industry.enteties.Véhicule;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.esprit.for_greener_industry.enteties.Produit;
import edu.esprit.for_greener_industry.tools.DataSource;
import java.io.*;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author ritha
 */
public class ServiceProduit implements IService<Produit>{
    Connection cnx ;

    public ServiceProduit(){
        this.cnx= DataSource.getInstance().getConnection();
    }

    @Override
    public void ajouter(Produit p) {   
           try {
            
            String req1 = "INSERT INTO `produit`( `id_entreprise`, `libellet`, `id_categorie`, `prix`, `img`, `description` ,"
                    + " `production_mentuelle`, `stock_actuelle`, `pollution_par_piece`) VALUES ('" + p.getId_entreprise()
                    + "','" + p.getLibellet() +"','" + p.getCatégorie().getId()+ "','" + p.getPrix() + "','" + p.getImg() +"','" + p.getDescription()
                    + "','"+ p.getProduction_mentuelle() + "','" + p.getStock_actuelle() +"','" + p.getPollution_par_piéce() + "')";
            Statement stm=cnx.createStatement();
            stm.executeUpdate(req1);
            String req = "Select * from `produit`";
            ResultSet rs = stm.executeQuery(req);
            ArrayList<Integer> idlist = new ArrayList();
            while (rs.next()){
                   idlist.add(rs.getInt(1));
            }
            Integer maxid = Collections.max(idlist);
            for (Energie e : p.getEnergie()){
                int i=0;
                String req2 = "INSERT INTO `produit_consomme_energie` (`id_produit`,`id_energie`,`consommation_mentuelle`)"
                        + "VALUES ('"+maxid+"','"+e.getId()+"','"+p.getQte_ener().get(i)+"')";
                i++;
                 stm.executeUpdate(req2);
            }
            for (Véhicule v : p.getVéhicule()){
                int i  = 0;
                String req2 = "Insert into `transport` (`id_produit`,`id_véhicule`,`distance_tot`) VALUES ('"+maxid+"','"+v.getId()+"','"+p.getDistance_véhicule().get(i)+"')";
                i++;
                stm.executeUpdate(req2);
            }
        } catch (SQLException ex) {
               System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Produit p) {
         String req = "UPDATE `produit` SET `id_entreprise`='"+p.getId_entreprise()+"',`libellet`='"+p.getLibellet()+"',`id_categorie`='"+p.getCatégorie().getId()+"',`prix`="+p.getPrix()+",`img`='"+p.getImg()+"',`description`='"+p.getDescription()+"',`production_mentuelle`='"+p.getProduction_mentuelle()+"',`stock_actuelle`='"+p.getStock_actuelle()+"',`pollution_par_piece`='"+p.getPollution_par_piéce()+"' WHERE `id`='"+p.getId()+"'";                      
        try {
            Statement stm=cnx.createStatement();
            stm.executeUpdate(req);
            for (Energie e : p.getEnergie()){
            int i =0 ;
            String req1 = "UPDATE `produit_consomme_energie` SET = `id_energie`='"+e.getId()+"',`consommation_mentuelle`='"+p.getQte_ener().get(i)+"' WHERE `id_produit`='"+p.getId()+"'";
            i++;
            stm.executeUpdate(req1);
            }
            for (Véhicule v : p.getVéhicule()){
                int i  = 0;
                String req1 = "Update `transport` SET `id_véhicule`'"+v.getId()+"',`distance_tot`='"+p.getDistance_véhicule().get(i)+"' WHERE `id_produit`='"+p.getId()+"'";
                i++;
                stm.executeUpdate(req1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
          String req = "Delete from produit where `id` = '"+id+"'";
          String req1 = "Delete from produit_consomme_energie where `id_produit` = '"+id+"'";
          String req2 = "Delete from transport where `id_produit` = '"+id+"'";
        try {
            Statement stm=cnx.createStatement();
            stm.executeUpdate(req1);
            stm.executeUpdate(req2);
            stm.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Produit getOne(Produit p) {
         String req = "Select * from produit where id = '"+p.getId()+"'";
        try {
            Statement stm=cnx.createStatement();
            stm.execute(req);
            ResultSet rs=  stm.executeQuery(req);
            Object castcat = rs.getObject(7);
            Catégorie c = (Catégorie) castcat;
            p.setId_entreprise(rs.getInt(2));
            p.setPrix(rs.getInt(3));
            p.setStock_actuelle(rs.getInt(4));
            p.setProduction_mentuelle(rs.getInt(5));
            p.setPollution_par_piéce(rs.getInt(6));
            p.setC(c);
            p.setLibellet(rs.getString(8));
            p.setDescription(rs.getString(9));
            p.setImg(rs.getString(10));
            
            String req1="select * from energie where id_produit='"+p.getId()+"'";
            ResultSet rs1;
            rs1=stm.executeQuery(req1);
            ArrayList<Energie> listener = new ArrayList();
            while(rs1.next()){
            Object castener = rs1.getObject(11);
            Energie e = (Energie) castener;
            listener.add(e);
            }
            p.setEner(listener);
            
            String req2="select consommation_mentuelle from produit_consomme_energie where id_produit='"+p.getId()+"'";
            ResultSet rs2;
            rs2=stm.executeQuery(req2);
            ArrayList<Float> list_cons = new ArrayList();
            while(rs2.next()){
            Float val=rs2.getFloat(3);
            list_cons.add(val);
            }
            p.setQte_ener(list_cons);
            
            String req3="select * from véhicule where id_produit='"+p.getId()+"'";
            ResultSet rs3;
            rs3=stm.executeQuery(req3);
            ArrayList<Véhicule> list_véhi = new ArrayList();
            while(rs3.next()){
            Object cast_véhi = rs1.getObject(13);
            Véhicule v = (Véhicule) cast_véhi;
            list_véhi.add(v);
            }
            p.setVéhicule(list_véhi);
            
            String req4="select consommation_mentuelle from produit_consomme_energie where id_produit='"+p.getId()+"'";
            ResultSet rs4;
            rs4=stm.executeQuery(req4);
            ArrayList<Float> list_distance = new ArrayList();
            while(rs4.next()){
            Float val=rs4.getFloat(3);
            list_distance.add(val);
            }
            
            p.setDistance_véhicule(list_distance);
            
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        return p;
    }

    @Override
    public List<Produit> getAll(Produit t) {
        String req="select * from `produit`";
        ArrayList<Produit> Produits=new ArrayList();
        Statement stm;
        Statement stm1;
        Statement stm2;
        Statement stm3;
        try {
            stm=this.cnx.createStatement();
            stm2=this.cnx.createStatement();
            stm2.execute(req);
            ResultSet rs=  stm2.executeQuery(req);
            Produit p = new  Produit();
            while (rs.next()) {
            
            Integer id_cat = rs.getInt("id_categorie");
            ServiceCatégorie sercat = new ServiceCatégorie();
            Catégorie c1 = new Catégorie();
            for (Catégorie c : sercat.getAll(c1)){
                if(c.getId()==id_cat){
                   p.setC(c); 
                }
            }
            Object castcat = rs.getObject(7);
            p.setId(rs.getInt("id"));
            p.setId_entreprise(rs.getInt("id_entreprise"));
            p.setPrix(rs.getInt("prix"));
            p.setStock_actuelle(rs.getInt("stock_actuelle"));
            p.setProduction_mentuelle(rs.getInt("production_mentuelle"));
            p.setPollution_par_piéce(rs.getInt("pollution_par_piece"));
            
            p.setLibellet(rs.getString("libellet"));
            p.setDescription(rs.getString("description"));
            p.setImg(rs.getString("img"));
            
            String req1="select * from produit_consomme_energie where id_produit='"+p.getId()+"'"; 
            ResultSet rs1;
            rs1=stm.executeQuery(req1);
            ArrayList<Energie> listener = new ArrayList();
            while(rs1.next()){
                String req4="select * from energie where id='"+rs1.getInt("id_energie")+"'";
                stm1=this.cnx.createStatement();
                ResultSet rs4=stm1.executeQuery(req4);
                while(rs4.next()){
                    Energie e = new Energie();
                    e.setId(rs4.getInt("id"));
                    e.setLibellet(rs4.getString("libellet"));
                    e.setPollution_par_kw(rs4.getFloat("pollution_par_kwh"));
                    listener.add(e);
                }

            }

            p.setEner(listener);
            
            
            
            String req3="select * from transport where id_produit='"+p.getId()+"'"; 
            ResultSet rs3;
            rs3=stm.executeQuery(req3);
            ArrayList<Véhicule> listvéhi = new ArrayList();
            while(rs3.next()){
                String req5="select * from véhicule where id='"+rs3.getInt("id_véhicule")+"'";
                stm1=this.cnx.createStatement();
                ResultSet rs5=stm1.executeQuery(req5);
                while(rs5.next()){
                    Véhicule v = new Véhicule();
                    v.setId(rs5.getInt("id"));
                    v.setLibellet(rs5.getString("libellet"));
                    v.setPollution_par_km(rs5.getFloat("pollution/km"));
                    v.setMarque(rs5.getString("marque"));
                    listvéhi.add(v);
                }
                
            }
            
            p.setVéhicule(listvéhi);
            
            
            
            
            
            Produits.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Produits;
    }
    
}
