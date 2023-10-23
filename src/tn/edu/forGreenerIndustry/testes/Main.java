/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forgreenerindustry.testes;

import java.sql.Date;
import java.util.List;
import tn.edu.forGreenerIndustry.entities.Commande;
import tn.edu.forgreenerindustry.entities.Panier;
import tn.edu.forgreenerindustry.services.Commandecrud;
import tn.edu.forgreenerindustry.services.Paniercrud;

/**
 *
 * @author ACHREF
 */
public class Main {
     public static void main(String[] args) {
        /*Myconnection mc = Myconnection.getInstance();
        Myconnection mc2 = Myconnection.getInstance();
        System.out.println(mc.hashCode() + "--" + mc2.hashCode());*/

        Commandecrud ccd = new Commandecrud();
        
        // Créez des valeurs
       /*
        Date dateCommande = java.sql.Date.valueOf("2022-01-01");
        String statutCommande = "confirmer";
        double montantTotal = 999.0;
        String adresseLivraison = "ben arouss";
        Date dateLivraison = java.sql.Date.valueOf("2024-01-01");
        String modePaiement = "chéque"; 

        // Créez une instance de la classe Commande avec les valeurs appropriées
        Commande c2 = new Commande( dateCommande, statutCommande,  montantTotal, adresseLivraison, dateLivraison,  modePaiement);

        // Appelez la méthode pour ajouter la commande
        ccd.ajouterCommande2(c2);
        
        /* Test de la fonction supprimerCommande
        int commandeIdASupprimer = 1; 
        ccd.supprimerCommande(commandeIdASupprimer);*/

        /* Test de la fonction modifierCommande
        Commande commandeAModifier = new Commande();
        commandeAModifier.setCommandeId(2); 
        commandeAModifier.setClientId(20); 
        commandeAModifier.setMontantTotal(150.0); 
        ccd.modifierCommande(commandeAModifier);
    }*/
         
/* Test de la méthode afficherCommandes
        List<Commande> commandes = ccd.afficherCommandes();

         // Parcourir la liste de commandes et afficher les détails
         commandes.stream().map((commande) -> {
             System.out.println("Commande ID: " + commande.getCommandeId());
             return commande;
         }).map((commande) -> {
             System.out.println("Client ID: " + commande.getClientId());
             return commande;
         }).map((commande) -> {
             System.out.println("Date de commande: " + commande.getDateCommande());
             return commande;
         }).map((commande) -> {
             System.out.println("Statut de commande: " + commande.getStatutCommande());
             return commande;
         }).map((commande) -> {
             System.out.println("Montant total: " + commande.getMontantTotal());
             return commande;
         }).map((commande) -> {
             System.out.println("Adresse de livraison: " + commande.getAdresseLivraison());
             return commande;
         }).map((commande) -> {
             System.out.println("Date de livraison: " + commande.getDateLivraison());
             return commande;
         }).map((commande) -> {
             System.out.println("Mode de paiement: " + commande.getModePaiement());
             return commande;
         }).forEachOrdered((_item) -> {
             System.out.println("----------------------------");
         }); 
     */

Paniercrud panierc = new Paniercrud() ;

        // Créez des valeurs pour un nouveau panier
        int clientId = 2;
        int produitId = 2;
        int quantite = 5;
        double prix = 10.0;
        double total = 50.0;
        String CIN = "ABCD1234";

        // Créez une instance de la classe Panier avec les valeurs appropriées
        Panier nvp = new Panier(clientId, produitId, quantite, prix, total, CIN);

        // Appelez la méthode pour ajouter le panier
         panierc.ajouterPanier(nvp);
    

// Test de la méthode afficherPaniers

        List<Panier> paniers = panierc.afficherPaniers();
        for (Panier panier : paniers) {
            
            System.out.println("Client ID: " + panier.getClientId());
            System.out.println("Produit ID: " + panier.getProduitId());
            System.out.println("Quantité: " + panier.getQuantite());
            System.out.println("Prix: " + panier.getPrix());
            System.out.println("Total: " + panier.getTotal());
            
            System.out.println("----------------------------");
        }


        // Test de la méthode modifierPanier
        if (!paniers.isEmpty()) 
  {
  
            Panier panierAModifier = paniers.get(0);
      // Supposons que nous modifions le premier panier de la liste
            panierAModifier.setQuantite(7);
            panierc.modifierPanier(panierAModifier);
  
         

        // Test de la méthode supprimerPanier
        int panierIdASupprimer = 2; 
        panierc.supprimerPanier(panierIdASupprimer);

        
    }
       
     
     
        
       
}
     }
     


    

       







      
