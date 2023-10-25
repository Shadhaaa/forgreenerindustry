/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forgreenerindustry.entities;


import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import javafx.beans.Observable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author shadha
 */
public class User  {
    public int id_user;
    private String nom;
    private String prenom;
    private String pdp;
    private int num;

    private String mail;
    private String mdp1;
    public  String role ;
    private String adresse ; 
    public String genre;
    private List<Commande> commandes ;
    private List<Reclamation> reclamations ;
//investisseur
    private List<Investissement> investisseur_inv;
   
    
        // Constructeur

    public User() {
    }
    
            // Constructeur avec id

    public User(int id_user, String nom, String prenom, String pdp, int num, String mail, String mdp1, String role, String adresse, String genre, List<Commande> commandes, List<Reclamation> reclamations, List<Investissement> investisseur_inv) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.pdp = pdp;
        this.num = num;
        this.mail = mail;
        this.mdp1 = mdp1;
        this.role = role;
        this.adresse = adresse;
        this.genre = genre;
        this.commandes = commandes;
        this.reclamations = reclamations;
        this.investisseur_inv = investisseur_inv;
    }
//sasn id + liste
    public User(String nom, String prenom, String pdp, int num, String mail, String mdp1, String role, String adresse, String genre, List<Commande> commandes, List<Reclamation> reclamations, List<Investissement> investisseur_inv) {
        this.nom = nom;
        this.prenom = prenom;
        this.pdp = pdp;
        this.num = num;
        this.mail = mail;
        this.mdp1 = mdp1;
        this.role = role;
        this.adresse = adresse;
        this.genre = genre;
        this.commandes = commandes;
        this.reclamations = reclamations;
        this.investisseur_inv = investisseur_inv;
    }
//sans liste
    public User(int id_user, String nom, String prenom, String pdp, int num, String mail, String mdp1, String role, String adresse, String genre) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.pdp = pdp;
        this.num = num;
        this.mail = mail;
        this.mdp1 = mdp1;
        this.role = role;
        this.adresse = adresse;
        this.genre = genre;
    }
//sans liste sans id
    public User(String nom, String prenom, String pdp, int num, String mail, String mdp1, String role, String adresse, String genre) {
        this.nom = nom;
        this.prenom = prenom;
        this.pdp = pdp;
        this.num = num;
        this.mail = mail;
        this.mdp1 = mdp1;
        this.role = role;
        this.adresse = adresse;
        this.genre = genre;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPdp() {
        return pdp;
    }

    public void setPdp(String pdp) {
        this.pdp = pdp;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp1() {
        return mdp1;
    }

    public void setMdp1(String mdp1) {
        this.mdp1 = mdp1;
    }

   
    

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }

    public List<Reclamation> getReclamations() {
        return reclamations;
    }

    public void setReclamations(List<Reclamation> reclamations) {
        this.reclamations = reclamations;
    }

    @Override
    public String toString() {
        return "User{" + "nom=" + nom + ", prenom=" + prenom + ", pdp=" + pdp + ", num=" + num + ", mail=" + mail + ", mdp1=" + mdp1 + ",  role=" + role + ", adresse=" + adresse + ", genre=" + genre + ", commandes=" + commandes + ", reclamations=" + reclamations + ", investisseur_inv=" + investisseur_inv +'}';
    }
    
 public StringProperty getNomProperty() {
        return new SimpleStringProperty(nom);
    }

    public StringProperty getPrenomProperty() {
        return new SimpleStringProperty(prenom);
    }

     public IntegerProperty getIdProperty() {
        return new SimpleIntegerProperty(id_user);
    }

    public StringProperty getAdresseProperty() {
        return new SimpleStringProperty(adresse);
    }

    public IntegerProperty getNumProperty() {
        return new SimpleIntegerProperty(num);
    }

    public StringProperty getGenreProperty() {
        return new SimpleStringProperty(genre);
    }

    public StringProperty getMdpProperty() {
        return new SimpleStringProperty(crypter(mdp1));
    }
    public StringProperty getMailProperty() {
        return new SimpleStringProperty(mail);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

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
        final User other = (User) obj;
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        return true;
    }
        public  String crypter(String mdp) {
        try {
            //instnce
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(mdp.getBytes());

            // Conversion des bytes en une représentation hexadécimale
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null; // Gestion de l'erreur
        }
    }
    
}
