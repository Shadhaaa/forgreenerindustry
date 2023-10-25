/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.gui;

import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ACHREF
 */
public class MezedController implements Initializable {
    
    
    private String gagnant = "";
    private double montantDepart = 50.0;
    private int compteur = 30;
  

    @FXML
    private Label txtMontatnt;
    @FXML
    private Label txtmontantaPayer;
    @FXML
    private Label txtPrixdepart;
    @FXML
    private Label txtCompteur;
    @FXML
    private TextField Montantenchere;
    @FXML
    private TextField cinRES;
    @FXML
    private TextField CINEN;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtPrixdepart.setText(String.valueOf(montantDepart));
        txtCompteur.setText(String.valueOf(compteur));
        
        // Démarrez un thread pour décrémenter le compteur chaque seconde
        Thread decrementThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (compteur > 0) {
                    try {
                        Thread.sleep(1000); // Attendre une seconde
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    compteur--;
                    updateCompteurLabel();
                }
            }
        });

        decrementThread.setDaemon(true);
        decrementThread.start();
    }

    // Mettez à jour le label du compteur sur l'interface graphique
private void updateCompteurLabel() {
    Platform.runLater(new Runnable() {
        @Override
        public void run() {
            txtCompteur.setText(String.valueOf(compteur));
        }
    });
     
    }

 

    @FXML
    private void RetournerPanier(ActionEvent event) {
          Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.close();

    
    Panierwindow panierWindow = new Panierwindow();
    Stage newStage = new Stage();
    panierWindow.start(newStage);
    }

    @FXML
    private void Encherir(ActionEvent event) {
   // Récupérer le montant saisi
    String montantSaisi = Montantenchere.getText();
    String cinEncherisseur = CINEN.getText();

    // Vérifier si le champ CINEN est vide ou n'a pas 8 chiffres
    if (cinEncherisseur.isEmpty() || !estNombre(cinEncherisseur) || cinEncherisseur.length() != 8) {
        afficherAlerte("Erreur de CIN", "Le CIN doit contenir 8 chiffres.");
        return;
    }

    // Vérifier si le champ montant est vide ou n'est pas valide
    if (montantSaisi.isEmpty() || !estNombre(montantSaisi)) {
        afficherAlerte("Erreur de saisie", "Veuillez saisir un montant valide.");
        return;
    }

    // Convertir le montant saisi en double
    double montantEnchere = Double.parseDouble(montantSaisi);

    if (compteur == 0) {
        
            afficherAlerte("Fin d'enchère", "Fin d'enchère");
        
    } else if (montantEnchere > montantDepart) {
        // Mise à jour du gagnant et du montant
        gagnant = cinEncherisseur;
        txtPrixdepart.setText(String.valueOf(montantEnchere)); 
         afficherAlerte("ATTendre Fin d'enchère", "Attender la fin d'enchére pour la résultats");
    } else {
        afficherAlerte("Erreur d'enchère", "Le montant de l'enchère doit être supérieur au montant gagnant.");
    }

    
    }
    // Méthode pour vérifier si une chaîne peut être convertie en nombre
    private boolean estNombre(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Méthode pour afficher une alerte
    private void afficherAlerte(String titre, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    @FXML
    private void Resultat(ActionEvent event) {
        String cinResultat = cinRES.getText();

    if (compteur == 0 && gagnant != null && cinResultat.equals(gagnant)) {
        afficherAlerte("Félicitations", "Voici ton code 'FGTY'. Le produit est gratuit maintenant.");
    } else if (compteur > 0) {
        afficherAlerte("Fin d'enchère", "Attends la fin de l'enchère.");
    }
    else { afficherAlerte("Fin d'enchère", "Malheuresement tu n'as pas ganger ");
    }
    }
}

    

