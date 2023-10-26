/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.for_greener_industry.enteties;


import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import java.util.Objects;
import java.io.Serializable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
/**
 *
 * @author ritha
 */
public class Catégorie implements Serializable{
    private int id ;
    private String libellet ;

    public Catégorie() {
    }

    public Catégorie(int id, String libellet) {
        this.id = id;
        this.libellet = libellet;
    }
    
    
    public Catégorie(String libellet) {
        this.libellet = libellet;
    }
    
    public int getId() {
        return id;
    }
    
    public IntegerProperty getIdProperty() {
        IntegerProperty idp = new SimpleIntegerProperty(id);
        return idp;
    }

    public String getLibellet() {
        return libellet;
    }
    
    public StringProperty getLibelletProperty() {
        StringProperty lib = new SimpleStringProperty(libellet);
        return lib;
    }

    public void setId(int id) {
        this.id = id;
    }
   

    public void setLibellet(String libellet) {
        this.libellet = libellet;
    }

    @Override
    public String toString() {
        return  libellet ;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.libellet);
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
        final Catégorie other = (Catégorie) obj;
        if (!Objects.equals(this.libellet, other.libellet)) {
            return false;
        }
        return true;
    }
    
    
}
