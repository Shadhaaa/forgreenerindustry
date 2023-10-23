/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.entities;

import java.sql.Date;

/**
 *
 * @author ACHREF
 */
public class Commande {
    private int commandeId;
    private int clientId;
    private Date dateCommande;
    private int panierId;
    private double montantTotal;
    private String adresseLivraison;
    private Date dateLivraison;
    private String modePaiement;

    public int getCommandeId() {
        return commandeId;
    }

    public int getClientId() {
        return clientId;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    

    public double getMontantTotal() {
        return montantTotal;
    }

    public String getAdresseLivraison() {
        return adresseLivraison;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public String getModePaiement() {
        return modePaiement;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

   

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }

    public void setAdresseLivraison(String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }

   

    

    public Commande() {
    }

   

    

    public void setCommandeId(int commandeId) {
        this.commandeId = commandeId;
    }

    public int getPanierId() {
        return panierId;
    }

    public void setPanierId(int panierId) {
        this.panierId = panierId;
    }

    public Commande(int commandeId, int clientId, Date dateCommande, int panierId, double montantTotal, String adresseLivraison, Date dateLivraison, String modePaiement) {
        this.commandeId = commandeId;
        this.clientId = clientId;
        this.dateCommande = dateCommande;
        this.panierId = panierId;
        this.montantTotal = montantTotal;
        this.adresseLivraison = adresseLivraison;
        this.dateLivraison = dateLivraison;
        this.modePaiement = modePaiement;
    }

    public Commande(int clientId, Date dateCommande, int panierId, double montantTotal, String adresseLivraison, Date dateLivraison, String modePaiement) {
        this.clientId = clientId;
        this.dateCommande = dateCommande;
        this.panierId = panierId;
        this.montantTotal = montantTotal;
        this.adresseLivraison = adresseLivraison;
        this.dateLivraison = dateLivraison;
        this.modePaiement = modePaiement;
    }

    public Commande(Date dateCommande, double montantTotal, String adresseLivraison, Date dateLivraison, String modePaiement) {
        this.dateCommande = dateCommande;
        this.montantTotal = montantTotal;
        this.adresseLivraison = adresseLivraison;
        this.dateLivraison = dateLivraison;
        this.modePaiement = modePaiement;
    }

   
    

    
    
    
}
