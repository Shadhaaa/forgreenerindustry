/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.gui;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.edu.forGreenerIndustry.entities.Reclamation;
import tn.edu.forGreenerIndustry.services.ServiceReclamation;
import tn.edu.forGreenerIndustry.tools.DataSource;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;


/**
 * FXML Controller class
 *
 * @author dell
 */
public class GestionReclamationController implements Initializable {

    Connection cnx = DataSource.getInstance().getConnection();
    ServiceReclamation sr = new ServiceReclamation(cnx);

    @FXML
    private TableView<Reclamation> tabReclamation;
   
    @FXML
    private TableColumn<Reclamation, String> nomRec;
    @FXML
    private TableColumn<Reclamation, String> prenomRec;
    @FXML
    private TableColumn<Reclamation, String> emailRec;
    @FXML
    private TableColumn<Reclamation, String> numeroRec;
    @FXML
    private TableColumn<Reclamation, Date> Date_creRec;
    @FXML
    private TableColumn<Reclamation, Date> date_traRec;
    @FXML
    private TableColumn<Reclamation, String> descRec;
    @FXML
    private TableColumn<Reclamation, String> statusRec;
    @FXML
    private TableColumn<Reclamation, String> servRec;

    @FXML
    private TextField searchRec;
    private Label ErrSel;

    private ServiceReclamation serviceReclamation;
    @FXML
    private TableColumn<String, String> priorityRec1;
    @FXML
    private Button repondrec;
    @FXML
    private Button DetailsP;

    public GestionReclamationController() {
        Connection cnx = DataSource.getInstance().getConnection();
        serviceReclamation = new ServiceReclamation(cnx);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomRec.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("nom"));
        prenomRec.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("prenom"));
        emailRec.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("email"));
        numeroRec.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("numero_mobile"));
        Date_creRec.setCellValueFactory(new PropertyValueFactory<Reclamation, Date>("date_creation"));
        date_traRec.setCellValueFactory(new PropertyValueFactory<Reclamation, Date>("date_traitement"));
        servRec.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("nomServcie"));
        descRec.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("description"));
        statusRec.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("status"));
       priorityRec1.setCellValueFactory(new PropertyValueFactory<String, String>("priority"));

        ObservableList<Reclamation> reclamationList = FXCollections.observableArrayList();
        reclamationList.addAll(serviceReclamation.getAll());
        tabReclamation.setItems(reclamationList);

        FilteredList<Reclamation> filteredData = new FilteredList<>(reclamationList, p -> true);

        searchRec.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(reclamation -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                if (reclamation.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Reclamation> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tabReclamation.comparatorProperty());
        tabReclamation.setItems(sortedData);

    }

    @FXML
    private void goAjouter(ActionEvent event) throws IOException {
        Main.role = "admin";
        searchRec.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("AjouterREclamation.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void goModifeir(ActionEvent event) throws IOException {
        try {
            Main.rec = tabReclamation.getSelectionModel().getSelectedItem();
            if (Main.rec != null) {
                System.out.println("Reclamation sélectionnée : " + Main.rec.getIdReclamation);

                searchRec.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("ModifierReclamation.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
            } else {
                System.out.println("Aucune Réclamation sélectionnée");
                ErrSel.setText("Aucune Réclamation Sélectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goSupprimer(ActionEvent event) throws IOException {
        try {
            Main.rec = tabReclamation.getSelectionModel().getSelectedItem();
            if (Main.rec != null) {
                searchRec.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("SupprimerReclamation.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
            } else {
                ErrSel.setText("Aucune Réclamation Sélectionnée");
            }
        } catch (Exception e) {
        }
    }

    @FXML
    private void ChangerStatus(ActionEvent event) {
        try {
            Main.rec = tabReclamation.getSelectionModel().getSelectedItem();
            if (Main.rec != null) {
                searchRec.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("StatusReclamation.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
            } else {
                ErrSel.setText("Aucune Réclamation Sélectionnée");
            }
        } catch (Exception e) {
        }
    }

    @FXML
    private void goavis(ActionEvent event) throws IOException {
        searchRec.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("GestionAvis.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    private void goReponse(ActionEvent event) throws IOException {
        searchRec.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("GestionReponse.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void goUser(ActionEvent event) throws IOException {
        searchRec.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("EspaceUser.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void genererPDF(ActionEvent event) throws BadElementException, IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers PDF", "*.pdf"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            String outputPath = file.getAbsolutePath();

            ObservableList<Reclamation> reclamationList = tabReclamation.getItems();
            serviceReclamation.genererPDF(reclamationList, outputPath);

            System.out.println("Le PDF a été généré avec succès à l'emplacement : " + outputPath);
        }
    }

    @FXML
    private void goDetails(ActionEvent event) throws IOException {
         DetailsP.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("Visualisation.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }


}