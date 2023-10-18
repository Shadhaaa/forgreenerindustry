/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forgreenerindustry.gui;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.edu.forGreenerIndustry.entities.Commande;
import tn.edu.forgreenerindustry.services.Commandecrud;

/**
 * FXML Controller class
 *
 * @author ACHREF
 */
public class CommandeController implements Initializable {

    @FXML
    private DatePicker Ddate_commande;
    @FXML
    private ComboBox<String> ComboStatu_commande;
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
    private TableColumn<Commande, String> Cstatu;
    @FXML
    private TableColumn<Commande, Double> Cmontant;
    @FXML
    private TableColumn<Commande, String> Cadressel;
    @FXML
    private TableColumn<Commande, Date> Cdateliv;
    @FXML
    private TableColumn<Commande, String> Cmodep;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<String> items = (ObservableList<String>) ComboStatu_commande.getItems();
    items.addAll("En attente", "En cours", "Livré");
    ComboStatu_commande.setItems(items);

    ObservableList<String> modePaiementItems = (ObservableList<String>) Combo_modepaiement.getItems();
    modePaiementItems.addAll("Cash", "Carte de crédit", "Chèque");
    Combo_modepaiement.setItems(modePaiementItems);
       

    }    

   @FXML
private void AjouterCommande(ActionEvent event) {
    int clientId = 1;
    String adresseLivraison = txtLivraison.getText();
    Date dateCommande = Date.valueOf(Ddate_commande.getValue());
    String statutCommande = ComboStatu_commande.getValue();
    String montantText = txtMontatnt.getText();
    Date dateLivraison = Date.valueOf(Ddate_livraison.getValue());
    String modePaiement = Combo_modepaiement.getValue();
    
    // Vérification que tous les champs sont remplis
    if (adresseLivraison.isEmpty() || dateCommande == null || statutCommande == null || montantText.isEmpty() ||
        dateLivraison == null || modePaiement == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs du formulaire.");
        alert.showAndWait();
        return; 
    }

    // Vérification que le montant total est un nombre 
    double montant = 0.0;
    try {
        montant = Double.parseDouble(montantText);
    } catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Le montant total doit être un nombre.");
        alert.showAndWait();
        return; 
    }

    Commandecrud ccd = new Commandecrud();
    Commande C = new Commande();
    C.setClientId(clientId);
    C.setAdresseLivraison(adresseLivraison);
    C.setDateCommande(dateCommande);
    C.setStatutCommande(statutCommande);
    C.setMontantTotal(montant);
    C.setDateLivraison(dateLivraison);
    C.setModePaiement(modePaiement);

    ccd.ajouterCommande2(C);
    // Affichage de l'alerte de succès
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
        Cstatu.setCellValueFactory(new PropertyValueFactory<Commande, String>("statutCommande"));
        Cmontant.setCellValueFactory(new PropertyValueFactory<Commande, Double>("montantTotal"));
        Cadressel.setCellValueFactory(new PropertyValueFactory<Commande, String>("adresseLivraison"));
        Cdateliv.setCellValueFactory(new PropertyValueFactory<Commande, Date>("dateLivraison"));
        Cmodep.setCellValueFactory(new PropertyValueFactory<Commande, String>("modePaiement"));

        
    }

    @FXML
    private void SupprimerCommande(ActionEvent event) {
        if (tabcommande.getSelectionModel().getSelectedItem() != null) {
        
        Commande selectedCommande = tabcommande.getSelectionModel().getSelectedItem();
        
        Commandecrud cm = new Commandecrud();
        cm.supprimerCommande(selectedCommande.getCommandeId());
        
        // Rafraîchir la liste des commandes
        AfficherCommande(event);
        
        // Afficher une boîte de dialogue pour confirmer la suppression
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("Commande supprimée avec succès !");
        alert.showAndWait();
    }
    }

     @FXML
    private void ModifierCommande(ActionEvent event) {
        Commande selectedCommande = tabcommande.getSelectionModel().getSelectedItem();
    if (selectedCommande != null) {
        
        // Récupérer les données du formulaire
        Date dateCommande = Ddate_commande.getValue() != null ? Date.valueOf(Ddate_commande.getValue()) : null;
        String statutCommande = ComboStatu_commande.getValue();
        String montantText = txtMontatnt.getText();
        String adresseLivraison = txtLivraison.getText();
        Date dateLivraison = Ddate_livraison.getValue() != null ? Date.valueOf(Ddate_livraison.getValue()) : null;
        String modePaiement = Combo_modepaiement.getValue();

        if (dateCommande == null || statutCommande.isEmpty() || adresseLivraison.isEmpty() || dateLivraison == null || modePaiement.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs du formulaire.");
            alert.showAndWait();
            
        } else {
            // Vérification que le montant total est un nombre
            double montant = 0.0;
            try {
                montant = Double.parseDouble(montantText);
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Le montant total doit être un nombre.");
                alert.showAndWait();
                return; // Sortir de la méthode en cas d'erreur
            }
          
            // Mettre à jour les données de la commande sélectionnée
            selectedCommande.setDateCommande(dateCommande);
            selectedCommande.setStatutCommande(statutCommande);
            selectedCommande.setMontantTotal(montant);
            selectedCommande.setAdresseLivraison(adresseLivraison);
            selectedCommande.setDateLivraison(dateLivraison);
            selectedCommande.setModePaiement(modePaiement);

            Commandecrud cm = new Commandecrud();
            cm.modifierCommande(selectedCommande);

            // Mettre à jour la table visuellement
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

    }
    

