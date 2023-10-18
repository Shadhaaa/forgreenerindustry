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
import edu.esprit.for_greener_industry.enteties.Véhicule;
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
    private Button FXVéhicile_modifier;
    @FXML
    private Button FXVéhicile_supprimer;
    @FXML
    private Button FXVéhicile_valider;
    @FXML
    private Button FXVéhicile_annuler;
    @FXML
    private TextField FXVéhicile_id;
    @FXML
    private TextField FXVéhicile_state;
    @FXML
    private TextField FXVéhicile_action;
    @FXML
    private TextField FXVéhicile_lib;
    @FXML
    private TextField FXVéhicile_mark;
    @FXML
    private TextField FXVéhicile_pol;
    @FXML
    private Label FXVéhicile_conf;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Alert alert = new Alert(AlertType.INFORMATION);
        FXVéhicile_colid.setCellValueFactory(cell -> cell.getValue().getIdProperty().asObject());
        FXVéhicile_collib.setCellValueFactory(cell -> cell.getValue().getLibelletProperty());
        FXVéhicile_colmark.setCellValueFactory(cell -> cell.getValue().getMarqueProperty());
        FXVéhicile_colpol.setCellValueFactory(cell -> cell.getValue().getPollution_par_kmProperty().asObject());
        FXVéhicile_table.getColumns().addAll( FXVéhicile_colid,FXVéhicile_colmark, FXVéhicile_collib,FXVéhicile_colpol);
        DataSource.getInstance();
        Servicevéhicule service = new Servicevéhicule();
        Véhicule v1 = new Véhicule();
        FXVéhicile_table.getItems().addAll(service.getAll(v1));
        
        TextFormatter<String> formatter = new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("(?!0\\d*)\\d*\\.?\\d*") ) {
                return change;
            }
            return null;
        });

        FXVéhicile_pol.setTextFormatter(formatter);
        
        FXVéhicile_valider.setDisable(true);
        FXVéhicile_annuler.setDisable(true);
        FXVéhicile_mark.setDisable(true);
        FXVéhicile_pol.setDisable(true);
        FXVéhicile_lib.setDisable(true);
        FXVéhicile_id.setVisible(false);
        FXVéhicile_state.setVisible(false);
        FXVéhicile_action.setVisible(false);
        FXVéhicile_state.setText("inactive");
        
        FXVéhicile_table.setRowFactory( tv -> {
        TableRow<Véhicule> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && (! row.isEmpty()) &&  FXVéhicile_state.getText().equals("inactive")) {
                Véhicule rowData = row.getItem();
                FXVéhicile_lib.setText(rowData.getLibellet());
                FXVéhicile_id.setText(String.valueOf(rowData.getId()));
                FXVéhicile_mark.setText(rowData.getMarque());
                FXVéhicile_pol.setText(String.valueOf(rowData.getPollution_par_km()));
                }
            });
        return row ;
        });
        
        
        FXVéhicile_ajouter.setOnAction(e ->{
            FXVéhicile_action.setText("add");
            FXVéhicile_lib.setDisable(false);
            FXVéhicile_mark.setDisable(false);
            FXVéhicile_pol.setDisable(false);
            FXVéhicile_valider.setDisable(false);
            FXVéhicile_annuler.setDisable(false);
            FXVéhicile_state.setText("active");
        });
        
        FXVéhicile_modifier.setOnAction(e ->{
            if(FXVéhicile_lib.getText().equals("")||FXVéhicile_mark.getText().equals("")||FXVéhicile_pol.getText().equals("")){
                alert.setContentText("Choisir une véhicule pour étre modifieé");
                alert.show();
            }
            else{
                FXVéhicile_action.setText("mod");
                FXVéhicile_lib.setDisable(false);
                FXVéhicile_mark.setDisable(false);
                FXVéhicile_pol.setDisable(false);
                FXVéhicile_valider.setDisable(false);
                FXVéhicile_annuler.setDisable(false);
                FXVéhicile_state.setText("active");
            }
        });
        
        FXVéhicile_supprimer.setOnAction(e ->{
            if(FXVéhicile_lib.getText().equals("")||FXVéhicile_mark.getText().equals("")||FXVéhicile_pol.getText().equals("")){
                alert.setContentText("Choisir une véhicule pour étre suppriméé");
                alert.show();
            }
            else{
                FXVéhicile_action.setText("del");
                FXVéhicile_valider.setDisable(false);
                FXVéhicile_annuler.setDisable(false);
                FXVéhicile_state.setText("active");
                FXVéhicile_conf.setText("voulez vous vraiment supprimer cette Véhicule?");
            }
        });
        
        FXVéhicile_valider.setOnAction(e ->{
            Véhicule v = new Véhicule();
            
            if(FXVéhicile_lib.getText().equals("")||FXVéhicile_mark.getText().equals("")||FXVéhicile_pol.getText().equals("")){
                alert.setContentText("Tout champs sont obligatoires");
                
            }
            else{
                v.setLibellet(FXVéhicile_lib.getText());
                v.setMarque(FXVéhicile_mark.getText());
                v.setPollution_par_km(Float.parseFloat(FXVéhicile_pol.getText()));
                if (!service.Véhiculeunique(v)){
                alert.setContentText("echec cette Véhicule existe déja");
                }
                else{
                    switch(FXVéhicile_action.getText()){
                    case "add":                     
                        service.ajouter(v);
                        alert.setContentText("Véhicule ajouter avec success");
                        FXVéhicile_action.setText("");
                        FXVéhicile_lib.setText("");
                        FXVéhicile_table.getItems().setAll(service.getAll(v));
                        break;
                    case "mod":
                        v.setId(Integer.parseInt(FXVéhicile_id.getText()));
                        FXVéhicile_action.setText("");
                        service.modifier(v);
                        alert.setContentText("Véhicule modifieé avec success");
                        FXVéhicile_action.setText("");
                        FXVéhicile_lib.setText("");
                        FXVéhicile_id.setText("");
                        FXVéhicile_mark.setText("");
                        FXVéhicile_id.setText("");
                        FXVéhicile_table.getItems().setAll(service.getAll(v));
                        break;       
                    }
                }
                }
            if ( FXVéhicile_action.getText().equals("del")){
                 FXVéhicile_action.setText("");
                v.setId(Integer.parseInt (FXVéhicile_id.getText()));
                service.supprimer(v.getId());
                FXVéhicile_action.setText("");
                FXVéhicile_lib.setText("");
                FXVéhicile_id.setText("");
                FXVéhicile_pol.setText("");
                FXVéhicile_mark.setText("");
                FXVéhicile_table.getItems().setAll(service.getAll(v));
                alert.setContentText("Véhicule supprimeé avec success");
                FXVéhicile_conf.setText("");
            }
            alert.show();
            FXVéhicile_lib.setDisable(true);
            FXVéhicile_mark.setDisable(true);
            FXVéhicile_pol.setDisable(true);
            FXVéhicile_valider.setDisable(true);
            FXVéhicile_annuler.setDisable(true);
            FXVéhicile_state.setText("inactive");
            FXVéhicile_lib.setText("");
            FXVéhicile_id.setText("");
            FXVéhicile_pol.setText("");
            FXVéhicile_mark.setText("");
            FXVéhicile_conf.setText("");
            });
       
        FXVéhicile_annuler.setOnAction(e ->{
            FXVéhicile_lib.setDisable(true);
            FXVéhicile_mark.setDisable(true);
            FXVéhicile_pol.setDisable(true);
            FXVéhicile_valider.setDisable(true);
            FXVéhicile_annuler.setDisable(true);
            FXVéhicile_state.setText("inactive");
             FXVéhicile_lib.setText("");
            FXVéhicile_id.setText("");
            FXVéhicile_pol.setText("");
            FXVéhicile_mark.setText("");
            FXVéhicile_id.setText("");
            FXVéhicile_conf.setText("");
        });
       
        
    }    
    
}
