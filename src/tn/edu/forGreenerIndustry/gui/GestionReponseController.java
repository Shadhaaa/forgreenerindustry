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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.edu.forGreenerIndustry.entities.Reclamation;
import tn.edu.forGreenerIndustry.entities.Reponse;
import tn.edu.forGreenerIndustry.services.ServiceReclamation;
import tn.edu.forGreenerIndustry.services.ServiceReponse;

import tn.edu.forGreenerIndustry.tools.DataSource;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class GestionReponseController implements Initializable {

    Connection cnx = DataSource.getInstance().getConnection();
    ServiceReponse sr = new ServiceReponse(cnx);
    ServiceReclamation st = new ServiceReclamation(cnx);
    @FXML
    private TableView<Reponse> tabreponse;

    @FXML
    private TableColumn<Reponse, String> textrep;
    @FXML
    private TableColumn<Reponse, String> statrep;
    @FXML
    private TableColumn<Reponse, Integer> recrrep;
   
   
    @FXML
    private TextField searchPonse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherReclamation(sr.getAll());
       
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        searchPonse.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("GestionReclamation.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

   

    @FXML
    private void actualiser(ActionEvent event) {
        AfficherReclamation(sr.getAll());
    }

    void AfficherReclamation(ObservableList<Reponse> list) {
        // ObservableList<Reponse>  list  = sa.getAll();
        
        textrep.setCellValueFactory(new PropertyValueFactory<Reponse, String>("text"));
        statrep.setCellValueFactory(new PropertyValueFactory<Reponse, String>("status"));
        recrrep.setCellValueFactory(new PropertyValueFactory<Reponse, Integer>("idReclamation"));

        tabreponse.setItems(list);

        FilteredList<Reponse> filteredData = new FilteredList<>(list, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        searchPonse.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(reponse -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (reponse.getText().toLowerCase().contains(lowerCaseFilter)) {
                    return true;

                }
                return false;
               
             });
        });
        SortedList<Reponse> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tabreponse.comparatorProperty());

        tabreponse.setItems(sortedData);

    }

}
