/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.for_greener_industry.GUI;

import static edu.esprit.for_greener_industry.GUI.Mod_or_addVéhiFXMLController.Mod_or_addvéhiFXMLController;
import edu.esprit.for_greener_industry.enteties.Catégorie;
import edu.esprit.for_greener_industry.enteties.Energie;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.List;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import edu.esprit.for_greener_industry.enteties.Véhicule;
import edu.esprit.for_greener_industry.services.ServiceEnergie;
import edu.esprit.for_greener_industry.services.Servicevéhicule;
import edu.esprit.for_greener_industry.tools.DataSource;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import edu.esprit.for_greener_industry.tools.DataSource;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextFormatter;

/**
 * FXML Controller class
 *
 * @author ritha
 */
public class VéhiculeFXMLController implements Initializable {
    @FXML
    private Button FXVéhicile_ajouter;
    @FXML
    private TableView<Véhicule> FXVéhicile_table;
    @FXML
    private TableColumn<Véhicule,Integer> FXVéhicile_colid;
    @FXML
    private TableColumn<Véhicule,Float> FXVéhicile_colpol;
    @FXML
    private TableColumn<Véhicule,String> FXVéhicile_collib;
    @FXML
    private TableColumn<Véhicule,String> FXVéhicile_colmark;
    @FXML
    private TableColumn<Véhicule,Void>  FXVéhicile_colmod;
    @FXML
    private TableColumn<Véhicule,Void>  FXVéhicile_coldel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Alert alert = new Alert(AlertType.INFORMATION);
        DataSource.getInstance();
        Servicevéhicule Service =new Servicevéhicule();
        Véhicule v =new Véhicule();
        ObservableList<Véhicule> observableList = FXCollections.observableList(Service.getAll(v)); 
        FXVéhicile_colmod.setCellFactory(param -> new TableCellWithButton(observableList));
        FXVéhicile_colmod.setMinWidth(60);
        
        
        FXVéhicile_coldel.setCellFactory(param -> new TableCellWithDelButton(observableList));
        FXVéhicile_coldel.setMinWidth(60);
        
        
        FXVéhicile_colid.setCellValueFactory(cell -> cell.getValue().getIdProperty().asObject());
        FXVéhicile_collib.setCellValueFactory(cell -> cell.getValue().getLibelletProperty());
        FXVéhicile_colpol.setCellValueFactory(cell -> cell.getValue().getPollution_par_kmProperty().asObject());
        FXVéhicile_colmark.setCellValueFactory(cell -> cell.getValue().getMarqueProperty());
        
        FXVéhicile_table.setItems(observableList);
        
        FXVéhicile_ajouter.setOnAction(event ->{
              Stage primaryStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
              Véhicule v1 = new Véhicule();
              v1.setId(-1);
              Mod_or_addvéhiFXMLController(v1,primaryStage1,observableList);
        });
        
    }   
    
     private static class TableCellWithButton extends TableCell<Véhicule, Void> {
        private final Button button;

        public TableCellWithButton(ObservableList<Véhicule> observableList) {
            this.button = new Button("Modifier");
            this.button.setOnAction(event -> {
               
                
                Stage primaryStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Véhicule v = getTableView().getItems().get(getIndex());
                Mod_or_addvéhiFXMLController(v,primaryStage1,observableList);
                
            });
            
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(button);
            }
        }
    }
      
     private static class TableCellWithDelButton extends TableCell<Véhicule, Void> {
        private final Button button;

        public TableCellWithDelButton(ObservableList<Véhicule> observableList) {
            this.button = new Button("Supprimer");
            this.button.setOnAction(event -> {
                Véhicule v = getTableView().getItems().get(getIndex());
                Alert alert1 = new Alert(AlertType.INFORMATION);
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Delete Confirmation");
                alert.setContentText("Are you sure you want to delete this item?");
                alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                     DataSource.getInstance();
                     Servicevéhicule Service =new Servicevéhicule();
                     Service.supprimer(v.getId());
                     observableList.clear();               
                     observableList.addAll(Service.getAll(v));
                     alert1.setContentText("Véhicule supprimé avec success");
                     alert1.show();
                } else {
                    alert.close();
                }
            });
            
        });
                    }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(button);
            }
        }
    }  
}
    
    
    
            