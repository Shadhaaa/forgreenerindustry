/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.for_greener_industry.enteties;

import java.util.Objects;

/**
 *
 * @author ritha
 */
public class Véhicule {
    private int id ;
    private String libellet ;
    private String marque ;
    private float pollution_par_km ;

    public Véhicule() {
    }
    public Véhicule(int id, String libellet, String marque, float pollution_par_km) {
        this.id = id;
        this.libellet = libellet;
        this.marque = marque;
        this.pollution_par_km = pollution_par_km;
    }

    public int getId() {
        return id;
    }

    public String getLibellet() {
        return libellet;
    }

    public String getMarque() {
        return marque;
    }

    public float getPollution_par_km() {
        return pollution_par_km;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLibellet(String libellet) {
        this.libellet = libellet;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setPollution_par_km(float pollution_par_km) {
        this.pollution_par_km = pollution_par_km;
    }

    @Override
    public String toString() {
        return "V\u00e9hicule{" + "libellet=" + libellet + ", marque=" + marque + ", pollution_par_km=" + pollution_par_km + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.id;
        hash = 61 * hash + Objects.hashCode(this.libellet);
        hash = 61 * hash + Objects.hashCode(this.marque);
        hash = 61 * hash + Float.floatToIntBits(this.pollution_par_km);
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
        final Véhicule other = (Véhicule) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Float.floatToIntBits(this.pollution_par_km) != Float.floatToIntBits(other.pollution_par_km)) {
            return false;
        }
        if (!Objects.equals(this.libellet, other.libellet)) {
            return false;
        }
        if (!Objects.equals(this.marque, other.marque)) {
            return false;
        }
        return true;
    }
    
    
}
