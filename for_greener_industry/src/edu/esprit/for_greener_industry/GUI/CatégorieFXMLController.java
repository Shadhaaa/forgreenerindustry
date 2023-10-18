/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.for_greener_industry.GUI;

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


/**
 * FXML Controller class
 *
 * @author ritha
 */
public class CatégorieFXMLController implements Initializable {
    
    
    @FXML
    private Button FXCatégorie_ajouter;
    @FXML
    private Button FXCatégorie_modifier;
    @FXML
    private Button FXCatégorie_supprimer;
    @FXML
    private Button FXCatégorie_valider;
    @FXML
    private Button FXCatégorie_annuler;
    @FXML
    private TableView<Catégorie> FXCatégorie_table;
    @FXML
    private TextField FXCatégorie_libellet;
    @FXML
    private TextField FXCatégorie_id;
    @FXML
    private TextField FXCatégorie_action;
    @FXML
    private TableColumn<Catégorie,String> FXCatégorie_collib;
    @FXML
    private TableColumn<Catégorie,Integer> FXCatégorie_colid;
    @FXML
    private Label FXCatégorie_conf;
    @FXML
    private TextField FXCatégorie_state;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Alert alert = new Alert(AlertType.INFORMATION);
        
        
        FXCatégorie_colid.setCellValueFactory(cell -> cell.getValue().getIdProperty().asObject());
        FXCatégorie_collib.setCellValueFactory(cell -> cell.getValue().getLibelletProperty());
        FXCatégorie_table.getColumns().addAll( FXCatégorie_colid, FXCatégorie_collib);
        DataSource.getInstance();
        ServiceCatégorie Service =new ServiceCatégorie();
        Catégorie c1 =new Catégorie();
        FXCatégorie_table.getItems().addAll(Service.getAll(c1));
        
        
        FXCatégorie_state.setText("inactive");
        FXCatégorie_action.setVisible(false);
        FXCatégorie_libellet.setDisable(true);
        FXCatégorie_id.setVisible(false);
        FXCatégorie_valider.setDisable(true);
        FXCatégorie_annuler.setDisable(true);
        FXCatégorie_state.setVisible(false);
        
        
        FXCatégorie_table.setRowFactory( tv -> {
        TableRow<Catégorie> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && (! row.isEmpty()) &&  FXCatégorie_state.getText().equals("inactive")) {
                Catégorie rowData = row.getItem();
                FXCatégorie_libellet.setText(rowData.getLibellet());
                FXCatégorie_id.setText(String.valueOf(rowData.getId()));
                }
            });
        return row ;
        });
        
        
        
        FXCatégorie_ajouter.setOnAction(e ->{
            FXCatégorie_action.setText("add");
            FXCatégorie_libellet.setDisable(false);
            FXCatégorie_valider.setDisable(false);
            FXCatégorie_annuler.setDisable(false);
            FXCatégorie_state.setText("active");
        });
        
        FXCatégorie_modifier.setOnAction(e ->{
            if(FXCatégorie_libellet.getText().equals("")){
                alert.setContentText("choisir une Catégorie pour étre modifié");
                alert.show();
            }
            else{
                FXCatégorie_action.setText("mod");
                FXCatégorie_libellet.setDisable(false);
                FXCatégorie_valider.setDisable(false);
                FXCatégorie_annuler.setDisable(false);
                FXCatégorie_state.setText("active");
            }
            
        });
        
        FXCatégorie_supprimer.setOnAction(e ->{
            if(FXCatégorie_libellet.getText().equals("")){
                alert.setContentText("choisir une Catégorie pour étre supprimé");
                alert.show();
            }
            else{
                FXCatégorie_action.setText("del");
                FXCatégorie_valider.setDisable(false);
                FXCatégorie_annuler.setDisable(false);
                FXCatégorie_conf.setText("voulez vous vraiment supprimer cette catégorie?");
                FXCatégorie_state.setText("active");
            }
            
        });
        
        
        
        FXCatégorie_valider.setOnAction(e ->{
            
            if(FXCatégorie_libellet.getText().equals("")){
                alert.setContentText("Le libellet est obligatoire");
            }
            else{
                Catégorie c =new Catégorie();
            c.setLibellet(FXCatégorie_libellet.getText());
            
            if (!Service.catégorieunique(c)){
                alert.setContentText("echec cette catégorie existe déja");
            }
            else{
                switch(FXCatégorie_action.getText()){
                case "add":
                    Service.ajouter(c);
                    alert.setContentText("Catégorie ajouter avec success");
                    FXCatégorie_action.setText("");
                    FXCatégorie_libellet.setText("");
                    FXCatégorie_table.getItems().setAll(Service.getAll(c));
                    break;
                case "mod":
                    c.setId(Integer.parseInt(FXCatégorie_id.getText()));
                    FXCatégorie_action.setText("");
                    Service.modifier(c);
                    alert.setContentText("Catégorie modifieé avec success");
                    FXCatégorie_action.setText("");
                    FXCatégorie_libellet.setText("");
                    FXCatégorie_table.getItems().setAll(Service.getAll(c));
                    break;       
                }
            }
            if (FXCatégorie_action.getText().equals("del")){
                FXCatégorie_action.setText("");
                c.setId(Integer.parseInt(FXCatégorie_id.getText()));
                Service.supprimer(c.getId());
                FXCatégorie_action.setText("");
                FXCatégorie_libellet.setText("");
                FXCatégorie_table.getItems().setAll(Service.getAll(c));
                alert.setContentText("Catégorie supprimeé avec success");
                FXCatégorie_conf.setText("");
            }
            alert.show();
            FXCatégorie_libellet.setDisable(true);
            FXCatégorie_valider.setDisable(true);
            FXCatégorie_annuler.setDisable(true);
            FXCatégorie_state.setText("inactive");
            FXCatégorie_id.setText("");
            }
            
            
        });
        
        FXCatégorie_annuler.setOnAction(e ->{
            FXCatégorie_libellet.setDisable(true);
            FXCatégorie_id.setVisible(false);
            FXCatégorie_valider.setDisable(true);
            FXCatégorie_annuler.setDisable(true);
            FXCatégorie_state.setText("inactive");
            FXCatégorie_action.setText("");
        });
    }   
    private void save_cat(){       
       
    }
    
}
