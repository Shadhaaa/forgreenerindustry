/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forgreenerindustry.entities;

import java.sql.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milou
 */
public class Investissement {
     private int id_investissement;
    private int id_investisseur;
    private int id_entreprise;
    private Double montant;
    private Date date_debut_investissement;
    private String duree_prevue;
    private String details;
    private int status;
    
    
    public Investissement() {
    }

    public Investissement(int id_investisseur, int id_entreprise, Double montant, Date date_debut_investissement, String duree_prevue, String details, int status) {
        this.id_investisseur = id_investisseur;
        this.id_entreprise = id_entreprise;
        this.montant = montant;
        this.date_debut_investissement = date_debut_investissement;
        this.duree_prevue = duree_prevue;
        this.details = details;
        this.status = status;
    }

    public Investissement(int id_investissement, int id_investisseur, int id_entreprise, Double montant, Date date_debut_investissement, String duree_prevue, String details, int status) {
        this.id_investissement = id_investissement;
        this.id_investisseur = id_investisseur;
        this.id_entreprise = id_entreprise;
        this.montant = montant;
        this.date_debut_investissement = date_debut_investissement;
        this.duree_prevue = duree_prevue;
        this.details = details;
        this.status = status;
    }

    public int getId_investissement() {
        return id_investissement;
    }

    public void setId_investissement(int id_investissement) {
        this.id_investissement = id_investissement;
    }

    public int getId_investisseur() {
        return id_investisseur;
    }

    public void setId_investisseur(int id_investisseur) {
        this.id_investisseur = id_investisseur;
    }

    public int getId_entreprise() {
        return id_entreprise;
    }

    public void setId_entreprise(int id_entreprise) {
        this.id_entreprise = id_entreprise;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Date getDate_debut_investissement() {
        return date_debut_investissement;
    }

    public void setDate_debut_investissement(Date date_debut_investissement) {
        this.date_debut_investissement = date_debut_investissement;
    }

    public String getDuree_prevue() {
        return duree_prevue;
    }

    public void setDuree_prevue(String duree_prevue) {
        this.duree_prevue = duree_prevue;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Investissement{" + "id_investisseur=" + id_investisseur + ", id_entreprise=" + id_entreprise + ", montant=" + montant + ", date_debut_investissement=" + date_debut_investissement + ", duree_prevue=" + duree_prevue + ", details=" + details + ", status=" + status + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.id_investissement;
        hash = 67 * hash + this.id_investisseur;
        hash = 67 * hash + this.id_entreprise;
        hash = 67 * hash + Objects.hashCode(this.montant);
        hash = 67 * hash + Objects.hashCode(this.date_debut_investissement);
        hash = 67 * hash + Objects.hashCode(this.duree_prevue);
        hash = 67 * hash + Objects.hashCode(this.details);
        hash = 67 * hash + this.status;
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
        final Investissement other = (Investissement) obj;
        if (this.id_investissement != other.id_investissement) {
            return false;
        }
        if (this.id_investisseur != other.id_investisseur) {
            return false;
        }
        if (this.id_entreprise != other.id_entreprise) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.duree_prevue, other.duree_prevue)) {
            return false;
        }
        if (!Objects.equals(this.details, other.details)) {
            return false;
        }
        if (!Objects.equals(this.montant, other.montant)) {
            return false;
        }
        if (!Objects.equals(this.date_debut_investissement, other.date_debut_investissement)) {
            return false;
        }
        return true;
    }

   
    
}
