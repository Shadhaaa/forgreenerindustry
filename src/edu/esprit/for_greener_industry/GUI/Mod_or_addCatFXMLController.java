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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import java.util.ArrayList;
/**
 * FXML Controller class
 *
 * @author ritha
 */
public class Mod_or_addCatFXMLController implements Initializable {
    public class NewInterface {
    
}

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public static void Mod_or_addCatFXMLController(Catégorie c, Stage primaryStage,ObservableList<Catégorie> tab) {
        if(c.getId()==-1){
            Stage newStage = new Stage();
            DataSource.getInstance();
            ServiceCatégorie Service =new ServiceCatégorie();
            newStage.setTitle("Ajouter Catégorie");
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.initOwner(primaryStage);

            TextField setlabel = new TextField();
            

            Label namelabel = new Label();
            namelabel.setText("Le libellet :");


            HBox hlayout = new HBox(20);
            hlayout.setAlignment(Pos.BOTTOM_LEFT);
            hlayout.getChildren().addAll(namelabel,setlabel);

            Button ann = new Button("annuler");
            ann.setOnAction(event -> {
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            });
            
            
            Button mod = new Button("Valider");
            mod.setOnAction(event -> {
                Alert alert = new Alert(AlertType.INFORMATION);
                c.setLibellet(setlabel.getText());
                if(setlabel.getText().equals("")){
                    alert.setContentText("Le champ doit étre rempli");
                    alert.show();
                }
                else if(!Service.catégorieunique(c)){
                    alert.setContentText("Cette catégorie existe déja");
                    alert.show();
                }
                else{
                    Service.ajouter(c);
                    tab.clear();               
                    tab.addAll(Service.getAll(c));
                    alert.setContentText("Catégorie ajoutr avec success!");
                    alert.show();
                }
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            });
            HBox layout1 = new HBox(20);
            layout1.getChildren().addAll(ann,mod);
            
            
            VBox layout = new VBox(100);
            layout.getChildren().addAll(hlayout,layout1);

            Scene scene = new Scene(layout, 600, 300);
            newStage.setScene(scene);
            newStage.showAndWait();
           }
        else{
            Stage newStage = new Stage();
            DataSource.getInstance();
            ServiceCatégorie Service =new ServiceCatégorie();
            newStage.setTitle("Modifier Catégorie");
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.initOwner(primaryStage);

            TextField setlabel = new TextField();
            setlabel.setText(c.getLibellet());

            Label namelabel = new Label();
            namelabel.setText("Modifier le libellet :");


            HBox hlayout = new HBox(20);
            hlayout.setAlignment(Pos.BOTTOM_LEFT);
            hlayout.getChildren().addAll(namelabel,setlabel);
            
            
            Button ann = new Button("annuler");
            ann.setOnAction(event -> {
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            });


            Button mod = new Button("Valider");
            mod.setOnAction(event -> {
                Alert alert = new Alert(AlertType.INFORMATION);
                c.setLibellet(setlabel.getText());
                if(setlabel.getText().equals("")){
                    alert.setContentText("Le champ doit étre rempli");
                    alert.show();
                }
                else if(!Service.catégorieunique(c)){
                    alert.setContentText("Cette catégorie existe déja");
                    alert.show();
                }
                else{
                    Service.modifier(c);
                    tab.clear();               
                    tab.addAll(Service.getAll(c));
                    alert.setContentText("Catégorie modifier avec success!");
                    alert.show();
                }
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            });
            
             HBox layout1 = new HBox(20);
            layout1.getChildren().addAll(ann,mod);
            
            
            VBox layout = new VBox(100);
            layout.getChildren().addAll(hlayout,layout1);

            Scene scene = new Scene(layout, 600, 300);
            newStage.setScene(scene);
            newStage.showAndWait();
        }
        
    }
    
}
