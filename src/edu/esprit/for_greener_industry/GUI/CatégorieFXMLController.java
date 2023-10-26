/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.for_greener_industry.GUI;

import static edu.esprit.for_greener_industry.GUI.Mod_or_addCatFXMLController.Mod_or_addCatFXMLController;
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
import edu.esprit.for_greener_industry.enteties.Catégorie;
import edu.esprit.for_greener_industry.services.ServiceCatégorie;
import edu.esprit.for_greener_industry.tools.DataSource;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import edu.esprit.for_greener_industry.tools.DataSource;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;


/**
 * FXML Controller class
 *
 * @author ritha
 */
public class CatégorieFXMLController implements Initializable {
    
    
    @FXML
    private Button FXCatégorie_ajouter;
    @FXML
    private TableView<Catégorie> FXCatégorie_table;
    @FXML
    private TableColumn<Catégorie,String> FXCatégorie_collib;
    @FXML
    private TableColumn<Catégorie,Integer> FXCatégorie_colid;
    @FXML
    private TableColumn<Catégorie,Void> FXCatégorie_colmod;
    @FXML
    private TableColumn<Catégorie,Void> FXCatégorie_coldel;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Alert alert = new Alert(AlertType.INFORMATION);
        DataSource.getInstance();
        ServiceCatégorie Service =new ServiceCatégorie();
        Catégorie c1 =new Catégorie();
        ObservableList<Catégorie> observableList = FXCollections.observableList(Service.getAll(c1)); 
        FXCatégorie_colmod.setCellFactory(param -> new TableCellWithButton(observableList));
        FXCatégorie_colmod.setMinWidth(60);
        
        
        FXCatégorie_coldel.setCellFactory(param -> new TableCellWithDelButton(observableList));
        FXCatégorie_coldel.setMinWidth(60);
        
        
        FXCatégorie_colid.setCellValueFactory(cell -> cell.getValue().getIdProperty().asObject());
        FXCatégorie_collib.setCellValueFactory(cell -> cell.getValue().getLibelletProperty());
        FXCatégorie_table.setItems(observableList);
        FXCatégorie_ajouter.setOnAction(event ->{
              Stage primaryStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
              Catégorie c = new Catégorie();
              c.setId(-1);
              Mod_or_addCatFXMLController(c,primaryStage1,observableList);
        });
        
    }   
    
     private static class TableCellWithButton extends TableCell<Catégorie, Void> {
        private final Button button;

        public TableCellWithButton(ObservableList<Catégorie> observableList) {
            this.button = new Button("Modifier");
            this.button.setOnAction(event -> {
               
                
                Stage primaryStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Catégorie c = getTableView().getItems().get(getIndex());
                Mod_or_addCatFXMLController(c,primaryStage1,observableList);
                
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
      
     private static class TableCellWithDelButton extends TableCell<Catégorie, Void> {
        private final Button button;

        public TableCellWithDelButton(ObservableList<Catégorie> observableList) {
            this.button = new Button("Supprimer");
            this.button.setOnAction(event -> {
                Catégorie c = getTableView().getItems().get(getIndex());
                Alert alert1 = new Alert(AlertType.INFORMATION);
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Delete Confirmation");
                alert.setContentText("Are you sure you want to delete this item?");
                alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                     DataSource.getInstance();
                     ServiceCatégorie Service =new ServiceCatégorie();
                     Service.supprimer(c.getId());
                     observableList.clear();               
                     observableList.addAll(Service.getAll(c));
                     alert1.setContentText("Catégorie supprimé avec success");
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
    
    
    
            