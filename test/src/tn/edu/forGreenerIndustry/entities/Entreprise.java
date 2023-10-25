/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forgreenerindustry.entities;

import forgreenerindustry.ForGreenerIndustry;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 *
 * @author shadha
 */
public class Entreprise  {
    public int id_entreprise;
    private String nom;
    private String prenom;
    private String pdp;
    private int num;

    private String mail;
    private String mdp1;
    public  String role ;
    private String adresse ; 
    private String genre;
    private String logo;
    private String nom_entreprise;
    private String secteur;
    private String description;
    private List<User> liste_inv;
    private List<Produit> produits;
    private List<Evenement> evenements;
    private List<Post> posts;
    private List<Investissement> invs ;



    public Entreprise() {
    }
//constructeur avec id  acec list

    public Entreprise(int id_entreprise, String nom, String prenom, String pdp, int num, String mail, String mdp1, String role, String adresse, String genre, String logo, String nom_entreprise, String secteur, String description) {
        this.id_entreprise = id_entreprise;
        this.nom = nom;
        this.prenom = prenom;
        this.pdp = pdp;
        this.num = num;
        this.mail = mail;
        this.mdp1 = mdp1;
        this.role = role;
        this.adresse = adresse;
        this.genre = genre;
        this.logo = logo;
        this.nom_entreprise = nom_entreprise;
        this.secteur = secteur;
        this.description = description;
    }

    public Entreprise(int id_entreprise, String nom, String prenom, String pdp, int num, String mail, String mdp1, String role, String adresse, String genre, String logo, String nom_entreprise, String secteur, String description, List<User> liste_inv, List<Produit> produits, List<Evenement> evenements, List<Post> posts, List<Investissement> invs) {
        this.id_entreprise = id_entreprise;
        this.nom = nom;
        this.prenom = prenom;
        this.pdp = pdp;
        this.num = num;
        this.mail = mail;
        this.mdp1 = mdp1;
        this.role = role;
        this.adresse = adresse;
        this.genre = genre;
        this.logo = logo;
        this.nom_entreprise = nom_entreprise;
        this.secteur = secteur;
        this.description = description;
        this.liste_inv = liste_inv;
        this.produits = produits;
        this.evenements = evenements;
        this.posts = posts;
        this.invs = invs;
    }

    public Entreprise(String nom, String prenom, String pdp, int num, String mail, String mdp1, String role, String adresse, String genre, String logo, String nom_entreprise, String secteur, String description, List<User> liste_inv, List<Produit> produits, List<Evenement> evenements, List<Post> posts, List<Investissement> invs) {
        this.nom = nom;
        this.prenom = prenom;
        this.pdp = pdp;
        this.num = num;
        this.mail = mail;
        this.mdp1 = mdp1;
        this.role = role;
        this.adresse = adresse;
        this.genre = genre;
        this.logo = logo;
        this.nom_entreprise = nom_entreprise;
        this.secteur = secteur;
        this.description = description;
        this.liste_inv = liste_inv;
        this.produits = produits;
        this.evenements = evenements;
        this.posts = posts;
        this.invs = invs;
    }

    public Entreprise(String nom, String prenom, String pdp, int num, String mail, String mdp1, String role, String adresse, String genre, String logo, String nom_entreprise, String secteur, String description) {
        this.nom = nom;
        this.prenom = prenom;
        this.pdp = pdp;
        this.num = num;
        this.mail = mail;
        this.mdp1 = mdp1;
        this.role = role;
        this.adresse = adresse;
        this.genre = genre;
        this.logo = logo;
        this.nom_entreprise = nom_entreprise;
        this.secteur = secteur;
        this.description = description;
    }

   

    public int getId_entreprise() {
        return id_entreprise;
    }

    public void setId_entreprise(int id_entreprise) {
        this.id_entreprise = id_entreprise;
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

    
    

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getNom_entreprise() {
        return nom_entreprise;
    }

    public void setNom_entreprise(String nom_entreprise) {
        this.nom_entreprise = nom_entreprise;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getListe_inv() {
        return liste_inv;
    }

    public void setListe_inv(List<User> liste_inv) {
        this.liste_inv = liste_inv;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public List<Evenement> getEvenements() {
        return evenements;
    }

    public void setEvenements(List<Evenement> evenements) {
        this.evenements = evenements;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
   public StringProperty getSecteurProperty() {
        return new SimpleStringProperty(secteur);
    }

   
     public StringProperty getDescriptionProperty() {
        return new SimpleStringProperty(description);
    }
     public StringProperty   getNom_entrepriseProperty(){
             return new SimpleStringProperty(nom_entreprise);
}
      public StringProperty getNomProperty() {
        return new SimpleStringProperty(nom);
    }

    public StringProperty getPrenomProperty() {
        return new SimpleStringProperty(prenom);
    }

     public IntegerProperty getIdProperty() {
        return new SimpleIntegerProperty(id_entreprise);
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
      public StringProperty getDesciprtionProperty() {
        return new SimpleStringProperty(description);
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
