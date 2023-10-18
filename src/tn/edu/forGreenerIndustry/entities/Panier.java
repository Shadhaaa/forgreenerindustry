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
    private String CIN;

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

    public String getCIN() {
        return CIN;
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

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public Panier() {
    }

    public Panier(int panierId) {
        this.panierId = panierId;
    }

    public Panier(int panierId, int clientId, int produitId, int quantite, double prix, double total, String CIN) {
        this.panierId = panierId;
        this.clientId = clientId;
        this.produitId = produitId;
        this.quantite = quantite;
        this.prix = prix;
        this.total = total;
        this.CIN = CIN;
    }

    public Panier(int clientId, int produitId, int quantite, double prix, double total, String CIN) {
        this.clientId = clientId;
        this.produitId = produitId;
        this.quantite = quantite;
        this.prix = prix;
        this.total = total;
        this.CIN = CIN;
    }

    public Panier(int quantite, double prix, double total, String CIN) {
        this.quantite = quantite;
        this.prix = prix;
        this.total = total;
        this.CIN = CIN;
    }

    @Override
    public String toString() {
        return "Panier{" + "panierId=" + panierId + ", clientId=" + clientId + ", produitId=" + produitId + ", quantite=" + quantite + ", prix=" + prix + ", total=" + total + ", CIN=" + CIN + '}';
    }

    public void setPanierId(int panierId) {
        this.panierId = panierId;
    }
    
    
    
}
