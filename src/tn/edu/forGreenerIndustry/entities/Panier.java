/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forgreenerindustry.entities;

/**
 *
 * @author ACHREF
 */
public class Panier {
    private int panierId;
    private int clientId;
    private int produitId;
    private int quantite;
    private double prix;
    private double total;
    private String Nomproduit;

    public int getPanierId() {
        return panierId;
    }

    public int getClientId() {
        return clientId;
    }

    public int getProduitId() {
        return produitId;
    }

    public int getQuantite() {
        return quantite;
    }

    public double getPrix() {
        return prix;
    }

    public double getTotal() {
        return total;
    }

    

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setProduitId(int produitId) {
        this.produitId = produitId;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setTotal(double total) {
        this.total = total;
    }

  

    public Panier() {
    }

    public Panier(int panierId) {
        this.panierId = panierId;
    }

    public String getNomproduit() {
        return Nomproduit;
    }

    public void setNomproduit(String Nomproduit) {
        this.Nomproduit = Nomproduit;
    }

    @Override
    public String toString() {
        return "Panier{" + "panierId=" + panierId + ", clientId=" + clientId + ", produitId=" + produitId + ", quantite=" + quantite + ", prix=" + prix + ", total=" + total + ", Nomproduit=" + Nomproduit + '}';
    }

    public Panier(int panierId, int clientId, int produitId, int quantite, double prix, double total, String Nomproduit) {
        this.panierId = panierId;
        this.clientId = clientId;
        this.produitId = produitId;
        this.quantite = quantite;
        this.prix = prix;
        this.total = total;
        this.Nomproduit = Nomproduit;
    }

    public Panier(int clientId, int produitId, int quantite, double prix, double total, String Nomproduit) {
        this.clientId = clientId;
        this.produitId = produitId;
        this.quantite = quantite;
        this.prix = prix;
        this.total = total;
        this.Nomproduit = Nomproduit;
    }

    public Panier(int quantite, double prix, double total, String Nomproduit) {
        this.quantite = quantite;
        this.prix = prix;
        this.total = total;
        this.Nomproduit = Nomproduit;
    }

   

   
   
    

    public void setPanierId(int panierId) {
        this.panierId = panierId;
    }
    
    
    
}
