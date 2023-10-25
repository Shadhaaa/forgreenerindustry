/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.edu.forGreenerIndustry.entities.Commande;
import tn.edu.forgreenerindustry.services.Commandecrud;

/**
 * FXML Controller class
 *
 * @author ACHREF
 */
public class Commande1Controller implements Initializable {

    @FXML
    private DatePicker Ddate_commande;
    @FXML
    private TextField txtMontatnt;
    @FXML
    private TextField txtLivraison;
    @FXML
    private DatePicker Ddate_livraison;
    @FXML
    private ComboBox<String> Combo_modepaiement;
    @FXML
    private TableView<Commande> tabcommande;
    @FXML
   private TableColumn<Commande, Date> Cdatec;
    @FXML
   private TableColumn<Commande, Double> Cmontant;
    @FXML
   private TableColumn<Commande, String> Cadressel;
    @FXML
   private TableColumn<Commande, Date> Cdateliv;
    @FXML
    private TableColumn<Commande, String> Cmodep;
    
    private double montant;
    private int panierId;
    
     public void initialize() {
        // Initialiser le champ de texte avec le montant
        txtMontatnt.setText(Double.toString(montant));
        

        // ...
    }
    
     /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

    ObservableList<String> modePaiementItems = (ObservableList<String>) Combo_modepaiement.getItems();
    modePaiementItems.addAll("Cash", "Carte de crédit", "Chèque");
    Combo_modepaiement.setItems(modePaiementItems);
       
    }    

    @FXML
    private void AjouterCommande(ActionEvent event) {
        int clientId = 1;
String adresseLivraison = txtLivraison.getText();
Date dateCommande = Ddate_commande.getValue() != null ? Date.valueOf(Ddate_commande.getValue()) : null;
String DC = dateCommande != null ? dateCommande.toString() : null;
String montantText = txtMontatnt.getText();
Date dateLivraison = Ddate_livraison.getValue() != null ? Date.valueOf(Ddate_livraison.getValue()) : null;
String DL = dateLivraison != null ? dateLivraison.toString() : null;
String modePaiement = Combo_modepaiement.getValue();

if (adresseLivraison.isEmpty() || (DC != null && DC.isEmpty()) || montantText.isEmpty() ||
    (DL != null && DL.isEmpty()) || modePaiement.isEmpty()) {
    afficherAlerte("Champs obligatoires non remplis", "Veuillez remplir tous les champs obligatoires.");
    return;
}


   
    Commandecrud ccd = new Commandecrud();
    Commande C = new Commande();
    C.setClientId(clientId);
    C.setAdresseLivraison(adresseLivraison);
    C.setDateCommande(dateCommande);
    C.setPanierId(panierId);
    C.setMontantTotal(montant);
    C.setDateLivraison(dateLivraison);
    C.setModePaiement(modePaiement);

    ccd.ajouterCommande2(C);
   
    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
    successAlert.setTitle("Succès");
    successAlert.setHeaderText(null);
    successAlert.setContentText("Commande ajoutée avec succès !");
    successAlert.showAndWait();
    System.out.println("Commande ajoutée");
    }

    @FXML
    private void AfficherCommande(ActionEvent event) {
        Commandecrud cm = new Commandecrud();
         List<Commande> myList = cm.readAll();
           ObservableList<Commande> commandeData = FXCollections.observableArrayList(myList);
         tabcommande.setItems(commandeData);
        Cdatec.setCellValueFactory(new PropertyValueFactory<Commande, Date>("dateCommande"));
        
        Cmontant.setCellValueFactory(new PropertyValueFactory<Commande, Double>("montantTotal"));
        Cadressel.setCellValueFactory(new PropertyValueFactory<Commande, String>("adresseLivraison"));
        Cdateliv.setCellValueFactory(new PropertyValueFactory<Commande, Date>("dateLivraison"));
        Cmodep.setCellValueFactory(new PropertyValueFactory<Commande, String>("modePaiement"));

        
    }
    private void afficherAlerte(String titre, String contenu) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(titre);
    alert.setHeaderText(null);
    alert.setContentText(contenu);
    alert.showAndWait();
        
    }

   

    @FXML
    private void ModifierCommande(ActionEvent event) {
        Commande selectedCommande = tabcommande.getSelectionModel().getSelectedItem();
    if (selectedCommande != null) {
        
       
        Date dateCommande = Ddate_commande.getValue()  != null ? Date.valueOf(Ddate_commande.getValue()) : null;
        String adresseLivraison = txtLivraison.getText();
        Date dateLivraison = Ddate_livraison.getValue() != null ? Date.valueOf(Ddate_livraison.getValue()) : null;
        String modePaiement = Combo_modepaiement.getValue();
        
         

        if (dateCommande == null ||  adresseLivraison.isEmpty() || dateLivraison == null || modePaiement.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs du formulaire.");
            alert.showAndWait();
            
        } else {
            // Vérification que le montant total est un nombre
            /*double montant = 0.0;
            try {
                montant = Double.parseDouble(montantText);
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Le montant total doit être un nombre.");
                alert.showAndWait();
                return; // Sortir de la méthode en cas d'erreur
            } */
          
           
            selectedCommande.setDateCommande(dateCommande);
           
            
            selectedCommande.setAdresseLivraison(adresseLivraison);
            selectedCommande.setDateLivraison(dateLivraison);
            selectedCommande.setModePaiement(modePaiement);

            Commandecrud cm = new Commandecrud();
            cm.modifierCommande(selectedCommande);

            
            tabcommande.refresh();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Commande modifiée avec succès !");
            alert.showAndWait();
        }
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une commande à modifier.");
        alert.showAndWait();
    }
    }

     public void setMontantAndPanierId(double total, int panierId) {
       this.montant = total;
       this.panierId = panierId;
       txtMontatnt.setText(Double.toString(total));
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
    private void Historiquedescommandes(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.close();

    
    HistoriqueDescommandewindow panierWindow = new HistoriqueDescommandewindow();
    Stage newStage = new Stage();
    panierWindow.start(newStage);
    }


    @FXML
    private void Passerpaiement(ActionEvent event) {
        Commande selectedCommande = tabcommande.getSelectionModel().getSelectedItem();

    if (selectedCommande != null) {
        String modePaiement = selectedCommande.getModePaiement();
        
        if ("Carte de crédit".equals(modePaiement)) {
            double total = selectedCommande.getMontantTotal();
            
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Paiement.fxml"));
                Parent root = loader.load();

                // Obtenez le contrôleur de la fenêtre de paiement
                PaiementController paiementController = loader.getController();

                // Passez le montant total au contrôleur de paiement
                paiementController.setMontant(total);

                Scene scene = new Scene(root);

                Stage stage = new Stage();
                stage.setScene(scene);

                // Fermez la fenêtre actuelle
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();

                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le paiement par carte de crédit est requis pour passer au paiement.");
            alert.showAndWait();
        }
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une commande dans le tableau avant de passer au paiement.");
        alert.showAndWait();
    }
    }
}
    
