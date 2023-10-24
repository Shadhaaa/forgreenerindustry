/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.edu.forgreenerindustry.entities.Panier;
import tn.edu.forgreenerindustry.services.Paniercrud;

/**
 * FXML Controller class
 *
 * @author ACHREF
 */
public class AjouterpanierController implements Initializable {

    @FXML
    private TextField txtProduit;
    @FXML
    private Spinner<Integer> Quantitespin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
    Quantitespin.setValueFactory(valueFactory);
    }    

    @FXML
    private void AfficherPanier(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.close();

    
    Panierwindow panierWindow = new Panierwindow();
    Stage newStage = new Stage();
    panierWindow.start(newStage);
        
    }

    @FXML
    private void AjouterPanier(ActionEvent event) {
        int quantite = Quantitespin.getValue();
    String nomProduit = txtProduit.getText();
    
    if (nomProduit.isEmpty()) {
        
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champ manquant");
        alert.setHeaderText("Le champ nom du produit est vide");
        alert.setContentText("Veuillez saisir un nom pour le produit avant d'ajouter au panier.");
        alert.showAndWait();
        return; 
    }

    int clientId = 15;
    int produitId = 23;
    double prix = 320.6;
    double total = quantite * prix; 

    Paniercrud paniercrud = new Paniercrud();
    List<Panier> paniersDuClient = paniercrud.readAllPaniers();

    // Vérifiez s'il existe un panier avec le même produitId
    boolean produitExiste = false;
    for (Panier panier : paniersDuClient) {
        if (panier.getProduitId() == produitId) {
           
            int nouvelleQuantite = panier.getQuantite() + quantite;
            double nouveauTotal = nouvelleQuantite * prix;
            panier.setQuantite(nouvelleQuantite);
            panier.setTotal(nouveauTotal);
            panier.setNomproduit(nomProduit);

            
            paniercrud.modifierPanier(panier);
            produitExiste = true;
            break;
        }
    }

    if (!produitExiste) {
        
        Panier panier = new Panier();
        panier.setClientId(clientId);
        panier.setProduitId(produitId);
        panier.setQuantite(quantite);
        panier.setPrix(prix);
        panier.setTotal(total);
        panier.setNomproduit(nomProduit);

       
        paniercrud.ajouterPanier(panier);
    }

    
    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
    successAlert.setTitle("Succès");
    successAlert.setHeaderText(null);
    successAlert.setContentText("Panier mis à jour avec succès !");
    successAlert.showAndWait();
    System.out.println("Panier ajouté ou mis à jour");
    }
    
}
