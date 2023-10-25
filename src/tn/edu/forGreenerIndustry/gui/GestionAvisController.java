/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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

/**
 * FXML Controller class
 *
 * @author dell
 */
public class GestionAvisController implements Initializable {

    Connection cnx = DataSource.getInstance().getConnection();
    ServiceAvis sa = new ServiceAvis(cnx);
    @FXML
    private TableView<Avis> tabAvis;

    @FXML
    private TableColumn<Avis, String> detailsAvis;
    @FXML
    private TableColumn<Avis, Integer> noteAvis;
    @FXML
    private TableColumn<Avis, Integer> serviceAvis;
    @FXML
    private TableColumn<Avis, Integer> userAvis;
    
    @FXML
    private ComboBox<String> selAvis;

    private String[] a = {"Service evenement  ", "Service investissement", "Service Communication", "Service Produit"};
    @FXML
    private TextField searchavis;
    private Label ErrSel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selAvis.getItems().setAll(a);
        AfficherAvis(sa.getAll());
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
    private void ModifierAvis(ActionEvent event) {
        try {
            Main.avis = tabAvis.getSelectionModel().getSelectedItem();
            if (Main.avis != null) {
                searchavis.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("ModifeirAvis.fxml"));
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

    void AfficherAvis(ObservableList<Avis> list) {
        // ObservableList<Article>  list  = sa.getAll();
        detailsAvis.setCellValueFactory(new PropertyValueFactory<Avis, String>("detailAvisService"));
        noteAvis.setCellValueFactory(new PropertyValueFactory<Avis, Integer>("noteService"));
        userAvis.setCellValueFactory(new PropertyValueFactory<Avis, Integer>("idUser"));
        serviceAvis.setCellValueFactory(new PropertyValueFactory<Avis, Integer>("nomService"));

        tabAvis.setItems(list);
        FilteredList<Avis> filteredData = new FilteredList<>(list, b -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        searchavis.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(m -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (m.getDetailAvisService().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return String.valueOf(m.getNoteService()).contains(lowerCaseFilter); // Does not match.           
                }
            });
        });
        SortedList<Avis> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tabAvis.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tabAvis.setItems(sortedData);

    }

    @FXML
    private void acualiser(ActionEvent event) {
        AfficherAvis(sa.getAll());
    }

    @FXML
    private void AvisArt(ActionEvent event) {
    }

}
