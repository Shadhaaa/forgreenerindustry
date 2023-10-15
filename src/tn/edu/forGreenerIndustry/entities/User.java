/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.entities;

import forgreenerindustry.ForGreenerIndustry.Comp;
import forgreenerindustry.ForGreenerIndustry.Genre;
import forgreenerindustry.ForGreenerIndustry.Role;
import java.util.List;

/**
 *
 * @author shadha
 */
public class User {
    private int id_user;
    private String nom;
    private String prenom;
    private String pdp;
    private int num;

    private String mail;
    private String mdp1;
    private String mdp2;
    private Role role ;
    private String adresse ; 
    private Genre genre;
    private List<Commande> commandes ;
    private List<Reclamation> reclamations ;
    

//investisseur
    private List<Entreprise> investisseur_inv;

//livreur
    private String vehicule;
    private Comp compagnie_livraison;
    private int dispo;
    
        // Constructeur

    public User() {
    }
            // Constructeur avec id

    public User(int id_user, String nom, String prenom, String pdp, int num, String mail, String mdp1, Role role, String adresse, 
            Genre genre, List<Commande> commandes, List<Reclamation> reclamations, List<Entreprise> investisseur_inv, 
            String vehicule, Comp compagnie_livraison, int dispo) {
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
        this.vehicule = vehicule;
        this.compagnie_livraison = compagnie_livraison;
        this.dispo = dispo;
    }

   
    
            // Constructeur sans id 

    public User(String nom, String prenom, String pdp, int num, String mail, String mdp1,  Role role, String adresse,
            Genre genre, List<Commande> commandes, List<Reclamation> reclamations, List<Entreprise> investisseur_inv,
            String vehicule, Comp compagnie_livraison , int dispo) {
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
        this.vehicule = vehicule;
        this.compagnie_livraison = compagnie_livraison;
        this.dispo = dispo;
    }
    //constructeur entreprise 

    public User(int id_user, String nom, String prenom, String pdp, int num, String mail, String mdp1, String mdp2, Role role, String adresse) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.pdp = pdp;
        this.num = num;
        this.mail = mail;
        this.mdp1 = mdp1;
        this.mdp2 = mdp2;
        this.role = role;
        this.adresse = adresse;
    }
    //constructeur entreprise sans id
     public User( String nom, String prenom, String pdp, int num, String mail, String mdp1, String mdp2, Role role, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.pdp = pdp;
        this.num = num;
        this.mail = mail;
        this.mdp1 = mdp1;
        this.mdp2 = mdp2;
        this.role = role;
        this.adresse = adresse;
    }
     //constructeur sans liste

    public User(int id_user, String nom, String prenom, String pdp, 
            int num, String mail, String mdp1, Role role,
            String adresse, Genre genre, String vehicule, Comp compagnie_livraison, int dispo) {
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
        this.vehicule = vehicule;
        this.compagnie_livraison = compagnie_livraison;
        this.dispo = dispo;
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

    public String getMdp2() {
        return mdp2;
    }

    public void setMdp2(String mdp2) {
        this.mdp2 = mdp2;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getVehicule() {
        return vehicule;
    }

    public void setVehicule(String vehicule) {
        this.vehicule = vehicule;
    }

    public Comp getCompagnie_livraison() {
        return compagnie_livraison;
    }

    public void setCompagnie_livraison(Comp compagnie_livraison) {
        this.compagnie_livraison = compagnie_livraison;
    }

    public int getDispo() {
        return dispo;
    }

    public void setDispo(int dispo) {
        this.dispo = dispo;
    }

    public List<Entreprise> getInvestisseur_inv() {
        return investisseur_inv;
    }

    public void setInvestisseur_inv(List<Entreprise> investisseur_inv) {
        this.investisseur_inv = investisseur_inv;
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
        return "User{" + "nom=" + nom + ", prenom=" + prenom + ", pdp=" + pdp + ", num=" + num + ", mail=" + mail + ", mdp1=" + mdp1 + ",  role=" + role + ", adresse=" + adresse + ", genre=" + genre + ", commandes=" + commandes + ", reclamations=" + reclamations + ", investisseur_inv=" + investisseur_inv + ", vehicule=" + vehicule + ", compagnie_livraison=" + compagnie_livraison + ", dispo=" + dispo + '}';
    }
    


    

    
    
}
