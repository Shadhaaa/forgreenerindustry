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
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.edu.forGreenerIndustry.entities.Commande;
import tn.edu.forgreenerindustry.services.Commandecrud;

/**
 * FXML Controller class
 *
 * @author ACHREF
 */
public class HistoriqueDescommandeController implements Initializable {

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
    private DatePicker datecommandeCH;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

    @FXML
    private void Cherchercommande(ActionEvent event) {
         LocalDate selectedDate = datecommandeCH.getValue();
    ObservableList<Commande> originalList = tabcommande.getItems(); // Conservez la liste originale
    
    if (selectedDate != null) {
        ObservableList<Commande> filteredList = FXCollections.observableArrayList();
        for (Commande commande : originalList) {
            if (commande.getDateCommande().toLocalDate().equals(selectedDate)) {
                filteredList.add(commande);
            }
        }
        if (filteredList.isEmpty()) {
            afficherAlerte("Aucun résultat trouvé", "Aucune commande trouvée pour la date sélectionnée.");
        } else {
            tabcommande.setItems(filteredList); // Affichez la liste filtrée
        }
    } else {
        // Si la date sélectionnée est null, affichez une alerte
        afficherAlerte("Recherche vide", "Veuillez sélectionner une date pour effectuer la recherche.");
    }
}

private void afficherAlerte(String titre, String contenu) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle(titre);
    alert.setHeaderText(null);
    alert.setContentText(contenu);
    alert.showAndWait();
    }

    @FXML
    private void Retouraucommande(ActionEvent event) {
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.close();

    
    Commandewindow1 panierWindow = new Commandewindow1();
    Stage newStage = new Stage();
    panierWindow.start(newStage);
    }

    @FXML
    private void Refreshtab(MouseEvent event) {
          Commandecrud cm = new Commandecrud();
    List<Commande> myList = cm.readAll();
    ObservableList<Commande> commandeData = FXCollections.observableArrayList(myList);
    tabcommande.setItems(commandeData);
        
    }
    
}
