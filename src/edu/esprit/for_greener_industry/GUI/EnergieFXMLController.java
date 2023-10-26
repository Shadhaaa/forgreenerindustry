/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.for_greener_industry.GUI;

import static edu.esprit.for_greener_industry.GUI.Mod_or_addEnergieFXMLController.Mod_or_addenErFXMLController;
import edu.esprit.for_greener_industry.enteties.Catégorie;
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
import edu.esprit.for_greener_industry.enteties.Energie;
import edu.esprit.for_greener_industry.services.ServiceEnergie;
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
public class EnergieFXMLController implements Initializable {

    @FXML
    private Button FXEnergie_ajouter;
    @FXML
    private TableView<Energie> FXEnergie_table;
    @FXML
    private TableColumn<Energie,Integer> FXEnergie_colid;
    @FXML
    private TableColumn<Energie,String> FXEnergie_coltype;
    @FXML
    private TableColumn<Energie,Float> FXEnergie_clopol;
    @FXML
    private TableColumn<Energie,Void>  FXEnergie_clomod;
    @FXML
    private TableColumn<Energie,Void>  FXEnergie_clodel;
   
    /**
     * Initializes the controller class.
     */
    @Override
   public void initialize(URL url, ResourceBundle rb) {
        
        Alert alert = new Alert(AlertType.INFORMATION);
        DataSource.getInstance();
        ServiceEnergie Service =new ServiceEnergie();
        Energie e1 =new Energie();
        ObservableList<Energie> observableList = FXCollections.observableList(Service.getAll(e1)); 
        FXEnergie_clomod.setCellFactory(param -> new TableCellWithButton(observableList));
        FXEnergie_clomod.setMinWidth(60);
        
        
        FXEnergie_clodel.setCellFactory(param -> new TableCellWithDelButton(observableList));
        FXEnergie_clodel.setMinWidth(60);
        
        
        FXEnergie_colid.setCellValueFactory(cell -> cell.getValue().getIdProperty().asObject());
        FXEnergie_coltype.setCellValueFactory(cell -> cell.getValue().getLibelletProperty());
        FXEnergie_clopol.setCellValueFactory(cell -> cell.getValue().getPollution_par_kwProperty().asObject());
        FXEnergie_table.setItems(observableList);
        FXEnergie_ajouter.setOnAction(event ->{
              Stage primaryStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
              Energie e = new Energie();
              e.setId(-1);
              Mod_or_addenErFXMLController(e,primaryStage1,observableList);
        });
        
    }   
    
     private static class TableCellWithButton extends TableCell<Energie, Void> {
        private final Button button;

        public TableCellWithButton(ObservableList<Energie> observableList) {
            this.button = new Button("Modifier");
            this.button.setOnAction(event -> {
               
                
                Stage primaryStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Energie e = getTableView().getItems().get(getIndex());
                Mod_or_addenErFXMLController(e,primaryStage1,observableList);
                
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
      
     private static class TableCellWithDelButton extends TableCell<Energie, Void> {
        private final Button button;

        public TableCellWithDelButton(ObservableList<Energie> observableList) {
            this.button = new Button("Supprimer");
            this.button.setOnAction(event -> {
                Energie e = getTableView().getItems().get(getIndex());
                Alert alert1 = new Alert(AlertType.INFORMATION);
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Delete Confirmation");
                alert.setContentText("Are you sure you want to delete this item?");
                alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                     DataSource.getInstance();
                     ServiceEnergie Service =new ServiceEnergie();
                     Service.supprimer(e.getId());
                     observableList.clear();               
                     observableList.addAll(Service.getAll(e));
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
    
    
    
            