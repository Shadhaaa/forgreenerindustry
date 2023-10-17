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
public class Evenement {
     private int id_evenement; 
    private int id_entreprise;
    private int id_participant;
    private String titre_evenement;
    private Date date_evenement;
    private String QRcode;
    private String image_evenement;
    private String lieu_evenement;
    private String description_evenement;
    private String liste_participants;
    
    
    public Evenement(){
    }
    
    public Evenement(int id_entreprise, int id_participant, String titre_evenement, Date date_evenement, String QRcode, String image_evenement, String lieu_evenement, String description_evenement, String liste_participants) {
        this.id_entreprise = id_entreprise;
        this.id_participant = id_participant;
        this.titre_evenement = titre_evenement;
        this.date_evenement = date_evenement;
        this.QRcode = QRcode;
        this.image_evenement = image_evenement;
        this.lieu_evenement = lieu_evenement;
        this.description_evenement = description_evenement;
        this.liste_participants = liste_participants;
    }

    public Evenement(int id_evenement, int id_entreprise, int id_participant, String titre_evenement, Date date_evenement, String QRcode, String image_evenement,  String lieu_evenement, String description_evenement, String liste_participants) {
        this.id_evenement = id_evenement;
        this.id_entreprise = id_entreprise;
        this.id_participant = id_participant;
        this.titre_evenement = titre_evenement;
        this.date_evenement = date_evenement;
        this.QRcode = QRcode;
        this.image_evenement = image_evenement;
        this.lieu_evenement = lieu_evenement;
        this.description_evenement = description_evenement;
        this.liste_participants = liste_participants;
    }

    
    
    
    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public int getId_entreprise() {
        return id_entreprise;
    }

    public void setId_entreprise(int id_entreprise) {
        this.id_entreprise = id_entreprise;
    }

    public int getId_participant() {
        return id_participant;
    }

    public void setId_participant(int id_participant) {
        this.id_participant = id_participant;
    }

    public String getTitre_evenement() {
        return titre_evenement;
    }

    public void setTitre_evenement(String titre_evenement) {
        this.titre_evenement = titre_evenement;
    }
    
    public Date getDate_evenement() {
        return date_evenement;
    }

    public void setDate_evenement(Date date_evenement) {
        this.date_evenement = date_evenement;
    }

    public String getQRcode() {
        return QRcode;
    }

    public void setQRcode(String QRcode) {
        this.QRcode = QRcode;
    }

    public String getImage_evenement() {
        return image_evenement;
    }

    public void setImage_evenement(String image_evenement) {
        this.image_evenement = image_evenement;
    }

    public String getLieu_evenement() {
        return lieu_evenement;
    }

    public void setLieu_evenement(String lieu_evenement) {
        this.lieu_evenement = lieu_evenement;
    }

    public String getDescription_evenement() {
        return description_evenement;
    }

    public void setDescription_evenement(String description_evenement) {
        this.description_evenement = description_evenement;
    }

    public String getListe_participants() {
        return liste_participants;
    }

    public void setListe_participants(String liste_participants) {
        this.liste_participants = liste_participants;
    }

    @Override
    public String toString() {
        return "Evenement{" + 
                " id_entreprise=" + id_entreprise + 
                ", id_participant=" + id_participant + 
                ", titre_evenement=" + titre_evenement + 
                ", QRcode=" + QRcode + 
                ", image_evenement=" + image_evenement + 
                ", date_evenement=" + date_evenement + 
                ", lieu_evenement=" + lieu_evenement + 
                ", description_evenement=" + description_evenement + 
                ", liste_participants=" + liste_participants + 
                '}';
    } 

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.id_evenement;
        hash = 41 * hash + this.id_entreprise;
        hash = 41 * hash + this.id_participant;
        hash = 41 * hash + Objects.hashCode(this.titre_evenement);
        hash = 41 * hash + Objects.hashCode(this.date_evenement);
        hash = 41 * hash + Objects.hashCode(this.QRcode);
        hash = 41 * hash + Objects.hashCode(this.image_evenement);
        hash = 41 * hash + Objects.hashCode(this.lieu_evenement);
        hash = 41 * hash + Objects.hashCode(this.description_evenement);
        hash = 41 * hash + Objects.hashCode(this.liste_participants);
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
        final Evenement other = (Evenement) obj;
        if (this.id_evenement != other.id_evenement) {
            return false;
        }
        if (this.id_entreprise != other.id_entreprise) {
            return false;
        }
        if (this.id_participant != other.id_participant) {
            return false;
        }
        if (!Objects.equals(this.titre_evenement, other.titre_evenement)) {
            return false;
        }
        if (!Objects.equals(this.QRcode, other.QRcode)) {
            return false;
        }
        if (!Objects.equals(this.image_evenement, other.image_evenement)) {
            return false;
        }
        if (!Objects.equals(this.lieu_evenement, other.lieu_evenement)) {
            return false;
        }
        if (!Objects.equals(this.description_evenement, other.description_evenement)) {
            return false;
        }
        if (!Objects.equals(this.liste_participants, other.liste_participants)) {
            return false;
        }
        if (!Objects.equals(this.date_evenement, other.date_evenement)) {
            return false;
        }
        return true;
    }
    

    
}
