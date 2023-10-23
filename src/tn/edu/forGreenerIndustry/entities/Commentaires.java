/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.entities;

import java.util.Objects;
/**
 *
 * @author mila
 */
public class Commentaires {
    private int id_commentaire;
    private int id_user;
    private int id_post;
    private String contenu;
    private String statut;
    
    

    public Commentaires(int id_user,  String contenu, String statut) {
        this.id_user = id_user;
        this.id_post = id_post;
        this.contenu = contenu;
        this.statut = statut;
    }
    
    public Commentaires( int id_user, int id_post, String contenu, String statut) {
    this.id_commentaire = id_commentaire;
    this.id_user = id_user;
    this.id_post = id_post;
    this.contenu = contenu;
    this.statut = statut;
}
    
    public Commentaires(int id_commentaire, int id_user, int id_post, String contenu, String statut) {
    this.id_commentaire = id_commentaire;
    this.id_user = id_user;
    this.id_post = id_post;
    this.contenu = contenu;
    this.statut = statut;
}

    public int getId_commentaire() {
        return id_commentaire;
    }

    public void setId_commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
    
    @Override
    public String toString() {
        return "Commentaires{" +
                "id_commentaire=" + id_commentaire +
                ", id_user=" + id_user +
                ", id_post=" + id_post +
                ", contenu='" + contenu + '\'' +
                ", statut='" + statut + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id_commentaire;
        hash = 89 * hash + this.id_user;
        hash = 89 * hash + this.id_post;
        hash = 89 * hash + (this.contenu != null ? this.contenu.hashCode() : 0);
        hash = 89 * hash + (this.statut != null ? this.statut.hashCode() : 0);
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
        final Commentaires other = (Commentaires) obj;
        if (this.id_commentaire != other.id_commentaire) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        if (this.id_post != other.id_post) {
            return false;
        }
        if ((this.contenu == null) ? (other.contenu != null) : !this.contenu.equals(other.contenu)) {
            return false;
        }
        if ((this.statut == null) ? (other.statut != null) : !this.statut.equals(other.statut)) {
            return false;
        }
        return true;
    }
    
    

    
}
