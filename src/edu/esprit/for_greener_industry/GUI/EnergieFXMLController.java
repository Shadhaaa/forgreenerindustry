/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.for_greener_industry.GUI;

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
    private Button FXEnergie_modifier;
    @FXML
    private Button FXEnergie_supprimer;
    @FXML
    private Button FXEnergie_valider;
    @FXML
    private Button FXEnergie_annuler;
    @FXML
    private TableView<Energie> FXEnergie_table;
    @FXML
    private TableColumn<Energie,Integer> FXEnergie_colid;
    @FXML
    private TableColumn<Energie,String> FXEnergie_coltype;
    @FXML
    private TableColumn<Energie,Float> FXEnergie_clopol;
    @FXML
    private TextField FXEnergie_id;
    @FXML
    private TextField FXEnergie_action;
    @FXML
    private TextField FXEnergie_state;
    @FXML
    private Label FXEnergie_conf;
    @FXML
    private TextField FXEnergie_typeener;
    @FXML
    private TextField FXEnergie_pollution;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Alert alert = new Alert(AlertType.INFORMATION);
        FXEnergie_colid.setCellValueFactory(cell -> cell.getValue().getIdProperty().asObject());
        FXEnergie_coltype.setCellValueFactory(cell -> cell.getValue().getLibelletProperty());
        FXEnergie_clopol.setCellValueFactory(cell -> cell.getValue().getPollution_par_kwProperty().asObject());
        FXEnergie_table.getColumns().addAll( FXEnergie_colid, FXEnergie_coltype, FXEnergie_clopol );
        DataSource.getInstance();
        ServiceEnergie Service =new ServiceEnergie();
        Energie e1 =new Energie();
        FXEnergie_table.getItems().addAll(Service.getAll(e1));
        
        TextFormatter<String> formatter = new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("(?!0\\d*)\\d*\\.?\\d*") ) {
                return change;
            }
            return null;
        });

        FXEnergie_pollution.setTextFormatter(formatter);
        
        
        FXEnergie_state.setText("inactive");
        FXEnergie_action.setVisible(false);
        FXEnergie_typeener.setDisable(true);
        FXEnergie_id.setVisible(false);       
        FXEnergie_valider.setDisable(true);        
        FXEnergie_annuler.setDisable(true);
        FXEnergie_state.setVisible(false);
        FXEnergie_pollution.setDisable(true);
        
        
        FXEnergie_table.setRowFactory( tv -> {
        TableRow<Energie> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && (! row.isEmpty()) &&  FXEnergie_state.getText().equals("inactive")) {
                Energie rowData = row.getItem();
                FXEnergie_typeener.setText(rowData.getLibellet());
                FXEnergie_id.setText(String.valueOf(rowData.getId()));
                FXEnergie_pollution.setText(String.valueOf(rowData.getPollution_par_kw()));
                }
            });
        return row ;
        });
        
        
        FXEnergie_ajouter.setOnAction(e ->{
            FXEnergie_action.setText("add");
            FXEnergie_typeener.setDisable(false);
            FXEnergie_valider.setDisable(false);
            FXEnergie_annuler.setDisable(false);
            FXEnergie_state.setText("active");
            FXEnergie_pollution.setDisable(false);
        });
        
        FXEnergie_modifier.setOnAction(e ->{ 
            if(FXEnergie_typeener.getText().equals("")||FXEnergie_pollution.getText().equals("")){
                alert.setContentText("choisir un type d'énergie pour étre modifiée");
                alert.show();
            }
            else{
                FXEnergie_action.setText("mod");
                FXEnergie_typeener.setDisable(false);
                FXEnergie_valider.setDisable(false);
                FXEnergie_annuler.setDisable(false);
                FXEnergie_state.setText("active");
                FXEnergie_pollution.setDisable(false);
            }
        });
        
        FXEnergie_supprimer.setOnAction(e ->{
            if(FXEnergie_typeener.getText().equals("")||FXEnergie_pollution.getText().equals("")){
                alert.setContentText("choisir un type d'énergie pour étre supprimé");
                alert.show();
            }
            else{
                FXEnergie_action.setText("del");
                FXEnergie_typeener.setDisable(false);
                FXEnergie_valider.setDisable(false);
                FXEnergie_annuler.setDisable(false);
                FXEnergie_conf.setText("Voulez vous vraiment supprimer ce type d'energie??");
                FXEnergie_state.setText("active");
            }
        });
        
        
        FXEnergie_valider.setOnAction(t ->{
           
            if(FXEnergie_typeener.getText().equals("")||FXEnergie_pollution.getText().equals("")){
                alert.setContentText("Tout champs sont obligatoires");
                alert.show();
            }
            else{
                Energie e =new Energie();
            e.setLibellet(FXEnergie_typeener.getText());
            e.setPollution_par_kw(Float.parseFloat(FXEnergie_pollution.getText()));
            if (!Service.energieunique(e)){
                alert.setContentText("Echec ce type d'energie existe déja");
                FXEnergie_typeener.setText("");
                FXEnergie_pollution.setText("");
            }
            else{
                switch(FXEnergie_action.getText()){
                case "add":
                    Service.ajouter(e);
                    alert.setContentText("Type d'energie ajouter avec success");
                    FXEnergie_action.setText("");
                    FXEnergie_typeener.setText("");
                    FXEnergie_table.getItems().setAll(Service.getAll(e));
                    break;
                case "mod":
                    e.setId(Integer.parseInt(FXEnergie_id.getText()));
                    FXEnergie_action.setText("");
                    if (!Service.energieunique(e)){
                        alert.setContentText("Echec ce type d'energie existe déja");
                    }
                    else{
                        Service.modifier(e);
                    alert.setContentText("Type d'energie modifieé avec success");
                    FXEnergie_action.setText("");
                    FXEnergie_typeener.setText("");
                    FXEnergie_table.getItems().setAll(Service.getAll(e));
                    }
                    break;       
                }
            }
            if (FXEnergie_action.getText().equals("del")){
                FXEnergie_action.setText("");
                e.setId(Integer.parseInt(FXEnergie_id.getText()));
                System.out.println("haha");
                Service.supprimer(e.getId());
                FXEnergie_action.setText("");
                FXEnergie_typeener.setText("");
                FXEnergie_pollution.setText("");
                FXEnergie_conf.setText("");
                FXEnergie_id.setText("");
                FXEnergie_table.getItems().setAll(Service.getAll(e));
                alert.setContentText("Catégorie supprimeé avec success");
                
            }
            alert.show();
            FXEnergie_typeener.setDisable(true);
            FXEnergie_valider.setDisable(true);
            FXEnergie_annuler.setDisable(true);
            FXEnergie_pollution.setDisable(true);
            FXEnergie_state.setText("inactive");
            FXEnergie_id.setText("");
            FXEnergie_typeener.setText("");
            FXEnergie_pollution.setText("");
            }
            
            
            
            
        });
        
         FXEnergie_annuler.setOnAction(e ->{
            FXEnergie_typeener.setDisable(true);
            FXEnergie_valider.setDisable(true);
            FXEnergie_annuler.setDisable(true);
            FXEnergie_pollution.setDisable(true);
            FXEnergie_state.setText("inactive");
            FXEnergie_id.setText("");
            FXEnergie_conf.setText("");
            FXEnergie_typeener.setText("");
            FXEnergie_pollution.setText("");
        });
    
    }

}  
  
    
    