/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.entities;

/**
 *
 * @author dell
 */
public class Avis {

    private int idAvis;
    private String detailAvisService;
    private int noteService;
    private String nomService;

    public Avis(int idAvis, String detailAvisService, int noteService, String nomService) {
        this.idAvis = idAvis;
        this.detailAvisService = detailAvisService;
        this.noteService = noteService;
        this.nomService = nomService;
    }

    public Avis(String detailAvisService, int noteService, String nomService) {
        this.detailAvisService = detailAvisService;
        this.noteService = noteService;
        this.nomService = nomService;
    }

    public Avis() {
    }

    public int getIdAvis() {
        return idAvis;
    }

    public void setIdAvis(int idAvis) {
        this.idAvis = idAvis;
    }

    public String getDetailAvisService() {
        return detailAvisService;
    }

    public void setDetailAvisService(String detailAvisService) {
        this.detailAvisService = detailAvisService;
    }

    public int getNoteService() {
        return noteService;
    }

    public void setNoteService(int noteService) {
        this.noteService = noteService;
    }

    public String getNomService() {
        return nomService;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }
    
}
