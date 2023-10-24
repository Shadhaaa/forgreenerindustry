/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.gui;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.edu.forGreenerIndustry.entities.Commande;
import tn.edu.forgreenerindustry.entities.Panier;
import tn.edu.forgreenerindustry.services.Commandecrud;
import tn.edu.forgreenerindustry.services.Paniercrud;

/**
 * FXML Controller class
 *
 * @author ACHREF
 */
public class CommandeAdminController implements Initializable {

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
    @FXML
    private TableColumn<Commande, Integer> panier_id;
    @FXML
    private TableColumn<Commande, Integer> client_id;
    @FXML
    private Spinner<Integer> Panier_id;
    @FXML
    private Spinner<Integer> Client_id;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         // Initialiser les Spinners
    int minValue = 1;
    int maxValue = 100;
    int initialValue = 1;
    
    SpinnerValueFactory<Integer> panierValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(minValue, maxValue, initialValue);
    Panier_id.setValueFactory(panierValueFactory);
    
    SpinnerValueFactory<Integer> clientValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(minValue, maxValue, initialValue);
    Client_id.setValueFactory(clientValueFactory);
    ObservableList<String> modePaiementItems = (ObservableList<String>) Combo_modepaiement.getItems();
    modePaiementItems.addAll("Cash", "Carte de crédit", "Chèque");
    Combo_modepaiement.setItems(modePaiementItems);
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
        panier_id.setCellValueFactory(new  PropertyValueFactory<Commande, Integer>("panierId") );
        client_id.setCellValueFactory(new PropertyValueFactory<Commande, Integer>("clientId"));

    }

    @FXML
    private void SupprimerCommande(ActionEvent event) {
        if (tabcommande.getSelectionModel().getSelectedItem() != null) {
        
        Commande selectedCommande = tabcommande.getSelectionModel().getSelectedItem();
        
        Commandecrud cm = new Commandecrud();
        cm.supprimerCommande(selectedCommande.getCommandeId());
        
        
        AfficherCommande(event);
        
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("Commande supprimée avec succès !");
        alert.showAndWait();
    }
    }
    public boolean panierIdExistsInDatabase(int panierId) {
    List<Panier> paniers = new Paniercrud().readAllPaniers();

    for (Panier panier : paniers) {
        if (panier.getPanierId() == panierId) {
            return true;
        }
    }

    return false;
}
   public boolean clientIdExistsInDatabase(int clientId) {
   List<Panier> paniers = new Paniercrud().readAllPaniers();

    for (Panier panier : paniers) {
        if (panier.getClientId() ==  clientId ) {
            return true;
        }
    }

    return false;
}

    @FXML
    private void ModifierCommande(ActionEvent event) {
         Commande selectedCommande = tabcommande.getSelectionModel().getSelectedItem();
    
    if (selectedCommande != null) {
        // Récupérer les nouvelles valeurs depuis les champs de saisie
        int newPanierId = Panier_id.getValue();
        int newClientId = Client_id.getValue();
        LocalDate newDateCommande = Ddate_commande.getValue();
        double newMontantTotal = Double.parseDouble(txtMontatnt.getText());
        String newAdresseLivraison = txtLivraison.getText();
        LocalDate newDateLivraison = Ddate_livraison.getValue();
        String newModePaiement = Combo_modepaiement.getValue();
        
        // Check if any required field is empty
        if (newDateCommande == null || newMontantTotal <= 0 || newAdresseLivraison.isEmpty() || newDateLivraison == null || newModePaiement == null) {
            afficherAlerte("Champs vides", "Veuillez remplir tous les champs.");
            return;
        }

        // Check if PanierId and ClientId exist in the database (You need to implement this logic)
        if (!panierIdExistsInDatabase(newPanierId)) {
    afficherAlerte("Erreur", "PanierId n'existe pas dans la base de données.");
    return;
}

if (!clientIdExistsInDatabase(newClientId)) {
    afficherAlerte("Erreur", "ClientId n'existe pas dans la base de données.");
    return;
}

        
        // Mettre à jour les propriétés de la commande sélectionnée
        selectedCommande.setPanierId(newPanierId);
        selectedCommande.setClientId(newClientId);
        selectedCommande.setDateCommande(Date.valueOf(newDateCommande));
        selectedCommande.setMontantTotal(newMontantTotal);
        selectedCommande.setAdresseLivraison(newAdresseLivraison);
        selectedCommande.setDateLivraison(Date.valueOf(newDateLivraison));
        selectedCommande.setModePaiement(newModePaiement);
        
        // Appeler votre fonction de mise à jour (par exemple, updateCommande) pour enregistrer la modification dans la base de données
        Commandecrud cm = new Commandecrud();
        cm.modifierCommande(selectedCommande);

        // Actualiser le TableView après la modification
        AfficherCommande(event);

        // Afficher une alerte de succès
        afficherAlerte("Modification réussie", "La commande a été modifiée avec succès.");
    }
}

private void afficherAlerte(String titre, String contenu) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(titre);
    alert.setHeaderText(null);
    alert.setContentText(contenu);
    alert.showAndWait();
        
    }

    @FXML
    private void RetournerPanier(ActionEvent event) {
    }

  

    @FXML
    private void Remplirleschamps(ActionEvent event) {
         Commande selectedCommande = tabcommande.getSelectionModel().getSelectedItem();
    
    if (selectedCommande != null) {
        // Remplir les champs de saisie avec les valeurs de la commande sélectionnée
        Panier_id.getValueFactory().setValue(selectedCommande.getPanierId());
        Client_id.getValueFactory().setValue(selectedCommande.getClientId());
        Ddate_commande.setValue(selectedCommande.getDateCommande().toLocalDate());
        txtMontatnt.setText(String.valueOf(selectedCommande.getMontantTotal()));
        txtLivraison.setText(selectedCommande.getAdresseLivraison());
        Ddate_livraison.setValue(selectedCommande.getDateLivraison().toLocalDate());
        Combo_modepaiement.setValue(selectedCommande.getModePaiement());
    } else {
        // Aucune commande sélectionnée, afficher une alerte
        afficherAlerte("Aucune commande sélectionnée", "Veuillez sélectionner une commande à modifier.");
    }
    }
    
}
