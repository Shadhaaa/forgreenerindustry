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
public class Energie {
    private int id ;
    private String libellet ;
    private float pollution_par_kw ;

    public Energie() {
    }

    public Energie(int id, String libellet, float pollution_par_kw) {
        this.id = id;
        this.libellet = libellet;
        this.pollution_par_kw = pollution_par_kw;
    }

    public int getId() {
        return id;
    }

    public String getLibellet() {
        return libellet;
    }

    public float getPollution_par_kw() {
        return pollution_par_kw;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public void setLibellet(String libellet) {
        this.libellet = libellet;
    }

    public void setPollution_par_kw(float pollution_par_kw) {
        this.pollution_par_kw = pollution_par_kw;
    }

    @Override
    public String toString() {
        return "Energie{" + "libellet=" + libellet + ", pollution_par_kw=" + pollution_par_kw + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.libellet);
        hash = 59 * hash + Float.floatToIntBits(this.pollution_par_kw);
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
        final Energie other = (Energie) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Float.floatToIntBits(this.pollution_par_kw) != Float.floatToIntBits(other.pollution_par_kw)) {
            return false;
        }
        if (!Objects.equals(this.libellet, other.libellet)) {
            return false;
        }
        return true;
    }
    
}