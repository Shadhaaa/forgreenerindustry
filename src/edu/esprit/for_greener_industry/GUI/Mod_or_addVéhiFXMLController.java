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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import java.util.ArrayList;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.event.EventHandler;
import javafx.scene.control.TextFormatter;


/**
 * FXML Controller class
 *
 * @author ritha
 */
public class Mod_or_addVéhiFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     public static void Mod_or_addvéhiFXMLController(Véhicule v, Stage primaryStage,ObservableList<Véhicule> tab) {
         UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (newText.isEmpty() || Pattern.matches("\\d*\\.?\\d*", newText)) {
                return change;
            }
            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        
        if(v.getId()==-1){
            Stage newStage = new Stage();
            DataSource.getInstance();
            Servicevéhicule Service =new Servicevéhicule();
            newStage.setTitle("Ajouter Véhicule");
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.initOwner(primaryStage);

            TextField setlabel = new TextField();
            TextField setpol = new TextField();
            setpol.setTextFormatter(textFormatter);
            TextField setmark = new TextField();

            Label namelabel = new Label();
            namelabel.setText("Le libellet :");
            Label polabel = new Label();
            polabel.setText("Pollution_par_kw :");
            Label marklabel = new Label();
            marklabel.setText("Le nom de la véhicule :");


            HBox hbox1 = new HBox(20);
            hbox1.setAlignment(Pos.BOTTOM_LEFT);
            hbox1.getChildren().addAll(namelabel,setlabel);
            
            HBox hbox2 = new HBox(20);
            hbox2.setAlignment(Pos.BOTTOM_LEFT);
            hbox2.getChildren().addAll(polabel,setpol);
            
            HBox hbox3 = new HBox(20);
            hbox3.setAlignment(Pos.BOTTOM_LEFT);
            hbox3.getChildren().addAll(marklabel,setmark);
            
            Button ann = new Button("annuler");
            ann.setOnAction(event -> {
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            });
            
            
            Button mod = new Button("Valider");
            mod.setOnAction( event -> {
                Alert alert = new Alert(AlertType.INFORMATION);
                System.out.println("test1");
                v.setLibellet(setlabel.getText());
                v.setPollution_par_km(Float.parseFloat(setpol.getText()));
                v.setMarque(setmark.getText());
                System.out.println("test2");
                if(setpol.getText().equals("")||polabel.getText().equals("")||setmark.getText().equals("")){
                    alert.setContentText("Tout champs doivent étre rempli");
                    alert.show();
                }
                else if(!Service.Véhiculeunique(v)){
                    alert.setContentText("Cette véhicule existe déja");
                    alert.show();
                }
                else{
                    
                    Service.ajouter(v);
                    tab.clear();
                    tab.addAll(Service.getAll(v));
                    alert.setContentText("véhicule ajouter avec success!");
                    alert.show();
                }
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            });
            
            HBox hbox4 = new HBox(20);
            hbox4.getChildren().addAll(ann,mod);
            
            VBox vbox2 = new VBox(20);
            vbox2.getChildren().addAll(hbox1,hbox3,hbox2);
            
            
            VBox vbox1 = new VBox(100);
            vbox1.getChildren().addAll(vbox2,hbox4);
            
            Scene scene = new Scene(vbox1, 600, 300);
            
            newStage.setScene(scene);
            newStage.showAndWait();
            
           }
        else{
            Stage newStage = new Stage();
            DataSource.getInstance();
            Servicevéhicule Service =new Servicevéhicule();
            newStage.setTitle("Modifier Véhicule");
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.initOwner(primaryStage);

            TextField setlabel = new TextField();
            setlabel.setText(v.getLibellet());
            TextField setpol = new TextField();
            setpol.setText(String.valueOf(v.getPollution_par_km()));
            TextField setmark = new TextField();
            setmark.setText(v.getMarque());

            Label namelabel = new Label();
            namelabel.setText("Le libellet :");
            Label polabel = new Label();
            polabel.setText("Pollution_par_kw :");
            Label marklabel = new Label();
            marklabel.setText("Le nom de la véhicule :");


            HBox hbox1 = new HBox(20);
            hbox1.setAlignment(Pos.BOTTOM_LEFT);
            hbox1.getChildren().addAll(namelabel,setlabel);
            
            HBox hbox2 = new HBox(20);
            hbox2.setAlignment(Pos.BOTTOM_LEFT);
            hbox2.getChildren().addAll(polabel,setpol);
            
            HBox hbox3 = new HBox(20);
            hbox3.setAlignment(Pos.BOTTOM_LEFT);
            hbox3.getChildren().addAll(marklabel,setmark);
            
            Button ann = new Button("annuler");
            ann.setOnAction(event -> {
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            });
            
            
            Button mod = new Button("Valider");
            mod.setOnAction( event -> {
                Alert alert = new Alert(AlertType.INFORMATION);
                v.setLibellet(setlabel.getText());
                v.setPollution_par_km(Float.parseFloat(setpol.getText()));
                v.setMarque(setmark.getText());
                if(setpol.getText().equals("")||polabel.getText().equals("")||setmark.getText().equals("")){
                    alert.setContentText("Tout champs doivent étre rempli");
                    alert.show();
                }
                else if(!Service.Véhiculeunique(v)){
                    alert.setContentText("Cette véhicule existe déja");
                    alert.show();
                }
                else{
                    
                    Service.modifier(v);
                    tab.clear();
                    tab.addAll(Service.getAll(v));
                    alert.setContentText("véhicule modifier avec success!");
                    alert.show();
                }
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            });
            
            HBox hbox4 = new HBox(20);
            hbox4.getChildren().addAll(ann,mod);
            
            VBox vbox2 = new VBox(20);
            vbox2.getChildren().addAll(hbox1,hbox3,hbox2);
            
            
            VBox vbox1 = new VBox(100);
            vbox1.getChildren().addAll(vbox2,hbox4);
            
            Scene scene = new Scene(vbox1, 600, 300);
            
            newStage.setScene(scene);
            newStage.showAndWait();
        }
        
    }
    
}

    

