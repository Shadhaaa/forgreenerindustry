/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.gui;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import tn.edu.forGreenerIndustry.entities.Reclamation;
import tn.edu.forGreenerIndustry.services.ServiceReclamation;
import tn.edu.forGreenerIndustry.tools.DataSource;

/**
 * FXML Controller class
 *
 * @author SYRINE
 */
public class VisualisationController implements Initializable {

    Connection cnx = DataSource.getInstance().getConnection();

    @FXML
    private PieChart ChartP;
    @FXML
    private Button retourRec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        visualizechart();
    }

    public void visualizechart() {
        ServiceReclamation service = new ServiceReclamation(cnx);
        List<Reclamation> allrec = service.getAll();

        // Define your combinations and use filter to count matching Reclamations
        Long blockerNon = null;
        if (allrec != null) {
            blockerNon = allrec.stream()
                    .filter(r ->r.getStatus().equalsIgnoreCase("En Attend"))
                    .filter(r -> r.getPriority().equalsIgnoreCase("Blocker"))
                    .count();
        }

        Long blockerTrait = null;
        if (allrec != null) {
            blockerTrait = allrec.stream()
                    .filter(r ->r.getStatus().equalsIgnoreCase("Traiteé"))
                    .filter(r -> r.getPriority().equalsIgnoreCase("Blocker"))
                    .count();
        }

        Long majorNon = null;
        if (allrec != null) {
            majorNon = allrec.stream()
                    .filter(r->r.getStatus().equalsIgnoreCase("En Attend"))
                    .filter(r ->r.getPriority().equalsIgnoreCase("Major"))
                    .count();
        }
           Long majorTrait = null;
        if (allrec != null) {
            majorTrait = allrec.stream()
                    .filter(r ->r.getStatus().equalsIgnoreCase("Traiteé"))
                    .filter(r ->r.getPriority().equalsIgnoreCase("Major"))
                    .count();
        }

        Long CosmeticTrait = null;
        if (allrec != null) {
            CosmeticTrait = allrec.stream()
                    .filter(r ->r.getStatus().equalsIgnoreCase("Traiteé"))
                    .filter(r ->r.getPriority().equalsIgnoreCase("Cosmetic"))
                    .count();
        }
        Long CosmeticNon = null;
        if (allrec != null) {
            CosmeticNon = allrec.stream()
                    .filter(r -> "Non Traiteé".equalsIgnoreCase(r.getStatus()))
                    .filter(r -> " Cosmetic".equalsIgnoreCase(r.getPriority()))
                    .count();
        }
      
        // Create PieChart.Data objects for each combination only if counts are not null
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        if (blockerNon != 0) {
            pieChartData.add(new PieChart.Data("Blocker Non-Traitée", blockerNon));
        }
        
        if (blockerTrait != 0) {
            pieChartData.add(new PieChart.Data("Blocker Traiteé", blockerTrait));
        }
        if(CosmeticTrait!=0) { 
            pieChartData.add(new PieChart.Data("Cosmetic Traitée", blockerNon));
        }


        if (CosmeticNon != 0) {
            pieChartData.add(new PieChart.Data("Cosmetic Non-Traitée", CosmeticNon));
        }
      
        if (majorNon != 0) {
            pieChartData.add(new PieChart.Data("Major Non-Traitée", majorNon));
        }
        if (majorTrait != 0) {
            pieChartData.add(new PieChart.Data("Major Traiteé", majorTrait));
        }

            // Set the data for the ChartP PieChart
            ChartP.setData(pieChartData);

            // Adjust the size of the chart based on the number of data points
            double scaleFactor = Math.max(1.0, pieChartData.size() / 5.0); // Adjust as needed
            ChartP.setPrefSize(400, 400);
        }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        retourRec.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("GestionReclamation.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    
    }

    
}
