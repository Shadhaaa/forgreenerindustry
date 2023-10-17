/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forgreenerindustry.entities;

import forgreenerindustry.ForGreenerIndustry;
import forgreenerindustry.ForGreenerIndustry.Role;
import java.util.List;


/**
 *
 * @author shadha
 */
public class Entreprise extends User {
     
    private String logo;
    private String nom_entreprise;
    private String secteur;
    private String description;
    private List<User> liste_inv;
    private List<Produit> produits;
    private List<Evenement> evenements;
    private List<Post> posts;


//constructeur avec id 

    public Entreprise() {
    }

    public Entreprise(int id_user,String nom, String prenom, String pdp, int num, String mail,
            String mdp1,  
            Role role ,String adresse ,String logo, String nom_entreprise,
            String secteur, String description, List<User> liste_inv, List<Produit> produits,
            List<Evenement> evenements, List<Post> posts) {
        super(id_user, nom, prenom, pdp, num, mail, mdp1,  role, adresse);
        this.logo = logo;
        this.nom_entreprise = nom_entreprise;
        this.secteur = secteur;
        this.description = description;
        this.liste_inv = liste_inv;
        this.produits = produits;
        this.evenements = evenements;
        this.posts = posts;
    }
//constructeur sans id
    public Entreprise( String nom, String prenom, String pdp, int num, String mail, String mdp1, String mdp2, Role role, String adresse,  String logo,  String nom_entreprise, String secteur, String description, List<User> liste_inv, List<Produit> produits, List<Evenement> evenements, List<Post> posts) {
        super(nom, prenom, pdp, num, mail, mdp1,  role, adresse);
        this.logo = logo;
        this.nom_entreprise = nom_entreprise;
        this.secteur = secteur;
        this.description = description;
        this.liste_inv = liste_inv;
        this.produits = produits;
        this.evenements = evenements;
        this.posts = posts;
    }
    //constructeur sans liste sans id 
    //constructeur sans id
    public Entreprise( String nom, String prenom, String pdp, int num, String mail, String mdp1, Role role, String adresse,  String logo,  String nom_entreprise, String secteur, String description) {
        super(nom, prenom, pdp, num, mail, mdp1, role, adresse);
        this.logo = logo;
        this.nom_entreprise = nom_entreprise;
        this.secteur = secteur;
        this.description = description;
        
    }
    
}
