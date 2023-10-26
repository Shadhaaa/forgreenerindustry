/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.gui;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.edu.forGreenerIndustry.entities.Avis;
import tn.edu.forGreenerIndustry.services.ServiceAvis;
import tn.edu.forGreenerIndustry.tools.DataSource;
import tn.edu.forGreenerIndustry.tools.DataSource;

/**
 * FXML Controller class
 *
 * @author SYRINE
 */
public class GestionAvisController implements Initializable {

    @FXML
    private TableView<Avis> tabAvis;
    @FXML
    private TableColumn<Avis, String> detailsAvis;
    @FXML
    private TableColumn<Avis, Integer> noteAvis;
    @FXML
    private TableColumn<Avis, String> ServiceAvis;
    @FXML
    private TextField searchavis;

    private Label ErrSel;
    private ServiceAvis serviceAvis = new ServiceAvis(DataSource.getInstance().getConnection());

    // Create a FilteredList to store filtered items
    private FilteredList<Avis> filteredAvisList;
    @FXML
    private Label bestServiceLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize TableView columns
        detailsAvis.setCellValueFactory(new PropertyValueFactory<>("detailAvisService"));
        noteAvis.setCellValueFactory(new PropertyValueFactory<>("noteService"));
        ServiceAvis.setCellValueFactory(new PropertyValueFactory<>("nomService"));

        // Retrieve data and set it in the TableView
        ObservableList<Avis> avisList = serviceAvis.getAllAvis();
        filteredAvisList = new FilteredList<>(avisList, p -> true);
        tabAvis.setItems(filteredAvisList);

        // Bind the TextField to the FilteredList
        searchavis.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredAvisList.setPredicate(avis -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // Show all items when the TextField is empty
                }

                // Check if "nomService" contains the search query (case-insensitive)
                return avis.getNomService().toLowerCase().contains(newValue.toLowerCase());
            });
        });

        // Calculate the best service and set it in the label
        String bestService = calculateBestService(avisList);
        bestServiceLabel.setText("Meilleur Service: " + bestService);
    }

// Add this method to calculate the best service
    private String calculateBestService(ObservableList<Avis> avisList) {
        Map<String, Integer> serviceTotalNotes = new HashMap<>();

        for (Avis avis : avisList) {
            String nomService = avis.getNomService();
            int noteService = avis.getNoteService();

            serviceTotalNotes.put(nomService, serviceTotalNotes.getOrDefault(nomService, 0) + noteService);
        }

        String bestService = "";
        int highestNote = Integer.MIN_VALUE;
        for (Map.Entry<String, Integer> entry : serviceTotalNotes.entrySet()) {
            if (entry.getValue() > highestNote) {
                highestNote = entry.getValue();
                bestService = entry.getKey();
            }
        }

        return bestService;
    }

    @FXML
    private void retourAvvis(ActionEvent event) throws IOException {
        searchavis.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("GestionReclamation.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void acualiser(ActionEvent event) {
    }

    private void ajouterAvis(ActionEvent event) throws IOException {
        Main.role = "admin";
        searchavis.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("AjouterAvis.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void SuppAvis(ActionEvent event) {
        try {
            Main.avis = tabAvis.getSelectionModel().getSelectedItem();
            if (Main.avis != null) {
                searchavis.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("SupprimerAvis.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
            } else {
                ErrSel.setText("Aucun Rec Selectionnée");
            }

        } catch (Exception e) {
        }
    }

}
