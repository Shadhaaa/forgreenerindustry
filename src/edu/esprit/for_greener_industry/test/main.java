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
/**
 *
 * @author ritha
 */
public class main {
    public static void main(String[] args) {
        DataSource.getInstance();
        Servicevéhicule Service =new Servicevéhicule();
        Véhicule e = new Véhicule(1,"testvmod","testmmod",12);
        Service.supprimer(1);
    }
}
