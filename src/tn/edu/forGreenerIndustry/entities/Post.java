/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.entities;

import java.sql.Date;

/**
 *
 * @author mila
 */
public class Post {
    private int id_post;
    private int id_entreprise;
    private String titre;
    private String typeDeContenu;
    private String contenu;
    private Date date;
    private String image;
    
    
    
    public Post() {
    // default
}
    
    public Post( String titre, String typeDeContenu, String contenu, Date date, String image) {
    
    this.titre = titre;
    this.typeDeContenu = typeDeContenu;
    this.contenu = contenu;
    this.date = date;
    this.image = image;
    
}
    
    public Post(int id_post, int id_entreprise, String titre, String typeDeContenu, String contenu, Date date, String image) {
    this.id_post = id_post;
    this.id_entreprise = id_entreprise;
    this.titre = titre;
    this.typeDeContenu = typeDeContenu;
    this.contenu = contenu;
    this.date = date;
    this.image = image;
    
}


  

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }

    public int getId_entreprise() {
        return id_entreprise;
    }

    public void setId_entreprise(int id_entreprise) {
        this.id_entreprise = id_entreprise;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getTypeDeContenu() {
        return typeDeContenu;
    }

    public void setTypeDeContenu(String typeDeContenu) {
        this.typeDeContenu = typeDeContenu;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    @Override
    public String toString() {
        return "Posts{" +
            "id_post=" + id_post +
            ", id_entreprise=" + id_entreprise +
            ", titre='" + titre + '\'' +
            ", typeDeContenu='" + typeDeContenu + '\'' +
            ", contenu='" + contenu + '\'' +
            ", date=" + date +
            ", image='" + image + '\'' +           
            '}';
}

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.id_post;
        hash = 71 * hash + this.id_entreprise;
        hash = 71 * hash + (this.titre != null ? this.titre.hashCode() : 0);
        hash = 71 * hash + (this.typeDeContenu != null ? this.typeDeContenu.hashCode() : 0);
        hash = 71 * hash + (this.contenu != null ? this.contenu.hashCode() : 0);
        hash = 71 * hash + (this.date != null ? this.date.hashCode() : 0);
        hash = 71 * hash + (this.image != null ? this.image.hashCode() : 0);
        
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
        final Post other = (Post) obj;
        if (this.id_post != other.id_post) {
            return false;
        }
        if (this.id_entreprise != other.id_entreprise) {
            return false;
        }
        
        if ((this.titre == null) ? (other.titre != null) : !this.titre.equals(other.titre)) {
            return false;
        }
        if ((this.typeDeContenu == null) ? (other.typeDeContenu != null) : !this.typeDeContenu.equals(other.typeDeContenu)) {
            return false;
        }
        if ((this.contenu == null) ? (other.contenu != null) : !this.contenu.equals(other.contenu)) {
            return false;
        }
        if ((this.date == null) ? (other.date != null) : !this.date.equals(other.date)) {
            return false;
        }
        if ((this.image == null) ? (other.image != null) : !this.image.equals(other.image)) {
            return false;
        }
        return true;
    }
    
    
    
}
