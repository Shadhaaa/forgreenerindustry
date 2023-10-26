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
import edu.esprit.for_greener_industry.enteties.Energie;
import edu.esprit.for_greener_industry.services.ServiceCatégorie;
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
public class Mod_or_addEnergieFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
     public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public static void Mod_or_addenErFXMLController(Energie e, Stage primaryStage,ObservableList<Energie> tab) {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (newText.isEmpty() || Pattern.matches("\\d*\\.?\\d*", newText)) {
                return change;
            }
            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        
        
        if(e.getId()==-1){
            Stage newStage = new Stage();
            DataSource.getInstance();
            ServiceEnergie Service =new ServiceEnergie();
            newStage.setTitle("Ajouter Energie");
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.initOwner(primaryStage);

            TextField setlabel = new TextField();
            TextField setpol = new TextField();
            setpol.setTextFormatter(textFormatter);

            Label namelabel = new Label();
            namelabel.setText("Le libellet :");
            Label polabel = new Label();
            polabel.setText("Pollution_par_kw :");


            HBox hbox1 = new HBox(20);
            hbox1.setAlignment(Pos.BOTTOM_LEFT);
            hbox1.getChildren().addAll(namelabel,setlabel);
            
            HBox hbox2 = new HBox(20);
            hbox2.setAlignment(Pos.BOTTOM_LEFT);
            hbox2.getChildren().addAll(polabel,setpol);
            
            Button ann = new Button("annuler");
            ann.setOnAction(event -> {
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            });
            
            
            Button mod = new Button("Valider");
            mod.setOnAction( event -> {
                Alert alert = new Alert(AlertType.INFORMATION);
                e.setLibellet(setlabel.getText());
                e.setPollution_par_kw(Float.parseFloat(setpol.getText()));
                if(setpol.getText().equals("")||polabel.getText().equals("")){
                    alert.setContentText("Tout champs doivent étre rempli");
                    alert.show();
                }
                else if(!Service.energieunique(e)){
                    alert.setContentText("Ce type d'énergie existe déja");
                    alert.show();
                }
                else{
                    
                    Service.ajouter(e);
                    tab.clear();
                    tab.addAll(Service.getAll(e));
                    alert.setContentText("Energie ajouter avec success!");
                    alert.show();
                }
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            });
            
            HBox hbox3 = new HBox(20);
            hbox3.getChildren().addAll(ann,mod);
            
            VBox vbox2 = new VBox(20);
            vbox2.getChildren().addAll(hbox1,hbox2);
            
            
            VBox vbox1 = new VBox(100);
            vbox1.getChildren().addAll(vbox2,hbox3);
            
            Scene scene = new Scene(vbox1, 600, 300);
            
            newStage.setScene(scene);
            newStage.showAndWait();
            
           }
        else{
             Stage newStage = new Stage();
            DataSource.getInstance();
            ServiceEnergie Service =new ServiceEnergie();
            newStage.setTitle("Ajouter Energie");
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.initOwner(primaryStage);

            TextField setlabel = new TextField();
            setlabel.setText(e.getLibellet());
            TextField setpol = new TextField();
            setpol.setText(String.valueOf(e.getPollution_par_kw()));

            Label namelabel = new Label();
            namelabel.setText("Le libellet :");
            Label polabel = new Label();
            polabel.setText("Pollution_par_kw :");


            HBox hbox1 = new HBox(20);
            hbox1.setAlignment(Pos.BOTTOM_LEFT);
            hbox1.getChildren().addAll(namelabel,setlabel);
            
            HBox hbox2 = new HBox(20);
            hbox2.setAlignment(Pos.BOTTOM_LEFT);
            hbox2.getChildren().addAll(polabel,setpol);

            Button ann = new Button("annuler");
            ann.setOnAction(event -> {
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            });
            
            
            Button mod = new Button("Valider");
            mod.setOnAction(event -> {
                Alert alert = new Alert(AlertType.INFORMATION);
                e.setLibellet(setlabel.getText());
                e.setPollution_par_kw(Float.parseFloat(setpol.getText()));
                if(setpol.getText().equals("")||polabel.getText().equals("")){
                    alert.setContentText("Tout champs doivent étre rempli");
                    alert.show();
                }
                else if(!Service.energieunique(e)){
                    alert.setContentText("Ce type d'énergie existe déja");
                    alert.show();
                }
                else{
                    Service.modifier(e);
                    tab.clear();               
                    tab.addAll(Service.getAll(e));
                    alert.setContentText("Energie modifieé avec success!");
                    alert.show();
                }
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            });
            HBox hbox3 = new HBox(20);
            hbox3.getChildren().addAll(ann,mod);
            
            VBox vbox2 = new VBox(20);
            vbox2.getChildren().addAll(hbox1,hbox2);
            
            
            VBox vbox1 = new VBox(100);
            vbox1.getChildren().addAll(vbox2,hbox3);
            
            Scene scene = new Scene(vbox1, 600, 300);
            
            newStage.setScene(scene);
            newStage.showAndWait();
        }
        
    }
    
}
