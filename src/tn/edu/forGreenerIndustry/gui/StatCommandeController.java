/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.gui;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.edu.forGreenerIndustry.entities.Commande;
import tn.edu.forgreenerindustry.services.Commandecrud;

/**
 * FXML Controller class
 *
 * @author ACHREF
 */
public class StatCommandeController implements Initializable {

    @FXML
    private StackedBarChart<String, Number> statcommandet;
    @FXML
    private AnchorPane main_form;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Afficherstat(ActionEvent event) {
      // Appelez la méthode pour récupérer toutes les commandes
        Commandecrud commandecrud = new Commandecrud();
        List<Commande> commandes = commandecrud.readAll();

        // Créez une structure pour stocker le nombre de commandes par adresse de livraison
        Map<String, Integer> commandesParAdresse = new HashMap<String, Integer>();

        // Parcourez les commandes et agrégez les données
        for (Commande commande : commandes) {
            String adresseLivraison = commande.getAdresseLivraison();
            // Vérifiez si l'adresse de livraison existe déjà dans la structure
            if (commandesParAdresse.containsKey(adresseLivraison)) {
                // Si oui, incrémente le nombre de commandes
                int nombreCommandes = commandesParAdresse.get(adresseLivraison);
                commandesParAdresse.put(adresseLivraison, nombreCommandes + 1);
            } else {
                // Si non, ajoutez l'adresse de livraison à la structure avec un compteur initial de 1
                commandesParAdresse.put(adresseLivraison, 1);
            }
        }

        // Créez une série de données pour le graphique
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        series.setName("Commandes par adresse de livraison");

        for (Map.Entry<String, Integer> entry : commandesParAdresse.entrySet()) {
            series.getData().add(new XYChart.Data<String, Number>(entry.getKey(), entry.getValue()));
        }

        // Ajoutez la série de données à l'élément statcommandet existant
        statcommandet.getData().clear(); 
        statcommandet.getData().add(series);
    }

    @FXML
    private void RetourCadmin(ActionEvent event) {
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.close();

    
    CommandeAdminwindow panierWindow = new  CommandeAdminwindow  ();
    Stage newStage = new Stage();
    panierWindow.start(newStage);
    }
}
