/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.entities;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SYRINE
 */
public class Reclamation {

    private int idReclamation;

    private String nom;
    private String prenom;
    private String email;
    private String screenshot;
    private String numero_mobile;
    private java.sql.Date date_creation;
    private java.sql.Date date_traitement;
    private String nomServcie;
    private String description;
    private String status;
    private String nomImage;
    private String priority ;

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getNomImage() {
        return nomImage;
    }

    public void setNomImage(String nomImage) {
        this.nomImage = nomImage;
    }

    public List<String> getForbiddenWords() {
        return forbiddenWords;
    }

    public void setForbiddenWords(List<String> forbiddenWords) {
        this.forbiddenWords = forbiddenWords;
    }

    public String getGetIdReclamation() {
        return getIdReclamation;
    }

    public void setGetIdReclamation(String getIdReclamation) {
        this.getIdReclamation = getIdReclamation;
    }

    private List<Reponse> reponse;

    private List<String> forbiddenWords;
    public String getIdReclamation;

    public Reclamation(String description) {
        this.forbiddenWords = Arrays.asList("fuck", "mot2", "expression1", "expression2");

        // Vous pouvez ajouter d'autres initialisations pour les autres attributs de la réclamation ici
    }

    public String getReclamationDescription() {
        return description;
    }

    public void setReclamationDescription(String reclamationDescription) {
        this.description = reclamationDescription;
    }

    public Reclamation(List<Reponse> reponse) {
        this.reponse = reponse;
    }

    public List<Reponse> getReponse() {
        return reponse;
    }

    public void setReponse(List<Reponse> reponse) {
        this.reponse = reponse;
    }

    public Reclamation(int idReclamation, String nom, String prenom, String email, String screenshot, String numero_mobile, java.sql.Date date_creation, java.sql.Date date_traitement, String description, String status, String nomServcie,String priority) {
        this.idReclamation = idReclamation;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.screenshot = screenshot;
        this.numero_mobile = numero_mobile;
        this.date_creation = date_creation;
        this.date_traitement = date_traitement;
        this.nomServcie = nomServcie;
        this.description = description;
        this.status = status;
        this.priority = priority;
    }

    public Reclamation() {
    }

    public int getIdReclamation() {
        return idReclamation;
    }

    public void setIdReclamation(int idReclamation) {
        this.idReclamation = idReclamation;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    public String getNumero_mobile() {
        return numero_mobile;
    }

    public void setNumero_mobile(String numero_mobile) {
        this.numero_mobile = numero_mobile;
    }

    public java.sql.Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(java.sql.Date date_creation) {
        this.date_creation = date_creation;
    }

    public java.sql.Date getDate_traitement() {
        return date_traitement;
    }

    public void setDate_traitement(java.sql.Date date_traitement) {
        this.date_traitement = date_traitement;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNomServcie() {
        return nomServcie;
    }

    public void setNomServcie(String nomServcie) {
        this.nomServcie = nomServcie;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
