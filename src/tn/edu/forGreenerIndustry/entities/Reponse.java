/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.entities;

/**
 *
 * @author SYRINE
 */
public class Reponse {

    private int idReponse;
    private String Text;
    private String status;
    private int idReclamation;
    private Reclamation reclamation;

    public Reponse(String text, String status, int reclamationId) {
        this.Text = text;
        this.status = status;
        this.idReclamation = reclamationId;
    }

    public Reponse(String Text, String status, int idReclamation, Reclamation reclamation) {
        this.Text = Text;
        this.status = status;
        this.idReclamation = idReclamation;
        this.reclamation = reclamation;
    }

    public Reponse() {
    }

    public int getIdReponse() {
        return idReponse;
    }

    public void setIdReponse(int idReponse) {
        this.idReponse = idReponse;
    }

    public String getText() {
        return Text;
    }

    public void setText(String Text) {
        this.Text = Text;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdReclamation() {
        return idReclamation;
    }

    public void setIdReclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }

    public Reclamation getReclamation() {
        return reclamation;
    }

    public void setReclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
    }

}
