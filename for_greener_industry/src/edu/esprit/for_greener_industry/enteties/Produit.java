/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.for_greener_industry.enteties;

import java.util.Objects;
import java.util.ArrayList;
/**
 *
 * @author ritha
 */
public class Produit {
    private int id;
    private int id_entreprise;//
    private int prix;
    private int stock_actuelle;
    private int production_mentuelle;
    private int pollution_par_piéce;
    private Catégorie c;//
    private String libellet;
    private String description;
    private String img;
    private ArrayList<Energie> ener;
    private ArrayList<Float> qte_ener;
    private ArrayList<Véhicule> véhicule;
    private ArrayList<Float> distance_véhicule;
    public Produit(){

    }

    public Produit(int id, int id_entreprise, int prix, int stock_actuelle, int production_mentuelle, int pollution_par_piéce, Catégorie c, String libellet, String description, String img, ArrayList<Energie> ener, ArrayList<Float> qte_ener, ArrayList<Véhicule> véhicule, ArrayList<Float> distance_véhicule) {
        this.id = id;
        this.id_entreprise = id_entreprise;
        this.prix = prix;
        this.stock_actuelle = stock_actuelle;
        this.production_mentuelle = production_mentuelle;
        this.pollution_par_piéce = pollution_par_piéce;
        this.c = c;
        this.libellet = libellet;
        this.description = description;
        this.img = img;
        this.ener = ener;
        this.qte_ener = qte_ener;
        this.véhicule = véhicule;
        this.distance_véhicule = distance_véhicule;
    }

    public Produit(int id_entreprise, int prix, int stock_actuelle, int production_mentuelle, int pollution_par_piéce, Catégorie c, String libellet, String description, String img, ArrayList<Energie> ener, ArrayList<Float> qte_ener, ArrayList<Véhicule> véhicule, ArrayList<Float> distance_véhicule) {
        this.id_entreprise = id_entreprise;
        this.prix = prix;
        this.stock_actuelle = stock_actuelle;
        this.production_mentuelle = production_mentuelle;
        this.pollution_par_piéce = pollution_par_piéce;
        this.c = c;
        this.libellet = libellet;
        this.description = description;
        this.img = img;
        this.ener = ener;
        this.qte_ener = qte_ener;
        this.véhicule = véhicule;
        this.distance_véhicule = distance_véhicule;
    }

    public int getId() {
        return id;
    }

    public int getId_entreprise() {
        return id_entreprise;
    }

    public int getPrix() {
        return prix;
    }

    public int getStock_actuelle() {
        return stock_actuelle;
    }

    public int getProduction_mentuelle() {
        return production_mentuelle;
    }

    public int getPollution_par_piéce() {
        return pollution_par_piéce;
    }

    public Catégorie getCatégorie() {
        return c;
    }

    public String getLibellet() {
        return libellet;
    }

    public String getDescription() {
        return description;
    }

    public String getImg() {
        return img;
    }

    public ArrayList<Energie> getEnergie() {
        return ener;
    }

    public ArrayList<Float> getQte_ener() {
        return qte_ener;
    }

    public ArrayList<Véhicule> getVéhicule() {
        return véhicule;
    }

    public ArrayList<Float> getDistance_véhicule() {
        return distance_véhicule;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_entreprise(int id_entreprise) {
        this.id_entreprise = id_entreprise;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setStock_actuelle(int stock_actuelle) {
        this.stock_actuelle = stock_actuelle;
    }

    public void setProduction_mentuelle(int production_mentuelle) {
        this.production_mentuelle = production_mentuelle;
    }

    public void setPollution_par_piéce(int pollution_par_piéce) {
        this.pollution_par_piéce = pollution_par_piéce;
    }

    public void setC(Catégorie c) {
        this.c = c;
    }

    public void setLibellet(String libellet) {
        this.libellet = libellet;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setEner(ArrayList<Energie> ener) {
        this.ener = ener;
    }

    public void setQte_ener(ArrayList<Float> qte_ener) {
        this.qte_ener = qte_ener;
    }

    public void setVéhicule(ArrayList<Véhicule> véhicule) {
        this.véhicule = véhicule;
    }

    public void setDistance_véhicule(ArrayList<Float> distance_véhicule) {
        this.distance_véhicule = distance_véhicule;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
        hash = 89 * hash + this.id_entreprise;
        hash = 89 * hash + this.prix;
        hash = 89 * hash + this.stock_actuelle;
        hash = 89 * hash + this.production_mentuelle;
        hash = 89 * hash + this.pollution_par_piéce;
        hash = 89 * hash + Objects.hashCode(this.c);
        hash = 89 * hash + Objects.hashCode(this.libellet);
        hash = 89 * hash + Objects.hashCode(this.description);
        hash = 89 * hash + Objects.hashCode(this.img);
        hash = 89 * hash + Objects.hashCode(this.ener);
        hash = 89 * hash + Objects.hashCode(this.qte_ener);
        hash = 89 * hash + Objects.hashCode(this.véhicule);
        hash = 89 * hash + Objects.hashCode(this.distance_véhicule);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produit other = (Produit) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.id_entreprise != other.id_entreprise) {
            return false;
        }
        if (this.prix != other.prix) {
            return false;
        }
        if (this.stock_actuelle != other.stock_actuelle) {
            return false;
        }
        if (this.production_mentuelle != other.production_mentuelle) {
            return false;
        }
        if (this.pollution_par_piéce != other.pollution_par_piéce) {
            return false;
        }
        if (!Objects.equals(this.libellet, other.libellet)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.img, other.img)) {
            return false;
        }
        if (!Objects.equals(this.c, other.c)) {
            return false;
        }
        if (!Objects.equals(this.ener, other.ener)) {
            return false;
        }
        if (!Objects.equals(this.qte_ener, other.qte_ener)) {
            return false;
        }
        if (!Objects.equals(this.véhicule, other.véhicule)) {
            return false;
        }
        if (!Objects.equals(this.distance_véhicule, other.distance_véhicule)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produit{" + "id_entreprise=" + id_entreprise + ", prix=" + prix + ", stock_actuelle=" + stock_actuelle + ", production_mentuelle=" + production_mentuelle + ", pollution_par_pi\u00e9ce=" + pollution_par_piéce + ", c=" + c + ", libellet=" + libellet + ", description=" + description + ", img=" + img + ", ener=" + ener + ", qte_ener=" + qte_ener + ", v\u00e9hicule=" + véhicule + ", distance_v\u00e9hicule=" + distance_véhicule + '}';
    }
    
}
    
