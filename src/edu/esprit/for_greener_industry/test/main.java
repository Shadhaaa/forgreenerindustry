/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.for_greener_industry.test;
import edu.esprit.for_greener_industry.tools.DataSource;
import edu.esprit.for_greener_industry.enteties.Produit;
import edu.esprit.for_greener_industry.services.ServiceProduit;
import edu.esprit.for_greener_industry.enteties.Catégorie;
import edu.esprit.for_greener_industry.enteties.Energie;
import edu.esprit.for_greener_industry.enteties.Véhicule;
import edu.esprit.for_greener_industry.services.ServiceCatégorie;
import edu.esprit.for_greener_industry.services.ServiceEnergie;
import edu.esprit.for_greener_industry.services.Servicevéhicule;
import java.util.ArrayList;
/**
 *
 * @author ritha
 */
public class main {
    public static void main(String[] args) {
        DataSource.getInstance();
        ServiceProduit Service =new ServiceProduit();
        ServiceCatégorie servicecat = new ServiceCatégorie();
        ServiceEnergie serviceener = new ServiceEnergie();
        Servicevéhicule servicevehi = new Servicevéhicule(); 
        Catégorie c = new Catégorie();
        c.setId(5);
        Energie e = new Energie();
        e.setId(3);
        Véhicule v = new Véhicule();
        v.setId(4);
        Catégorie cat = servicecat.getOne(c);
        Energie ener = serviceener.getOne(e);
        Véhicule veh = servicevehi.getOne(v);
        
        ArrayList<Energie> liste = new ArrayList();
        liste.add(e);
        
        ArrayList<Véhicule> listv = new ArrayList();
        listv.add(v);
        
        ArrayList<Float> consener = new ArrayList();
        consener.add(Float.parseFloat("2.3"));
        
        ArrayList<Float> distv = new ArrayList();
        distv.add(Float.parseFloat("8.3"));
        
        //Produit p = new Produit(1, 1, 55, 55, 77, c, "7777", "58", "kkk", liste, consener, listv, distv);
        Service.supprimer(13);
    }
}
