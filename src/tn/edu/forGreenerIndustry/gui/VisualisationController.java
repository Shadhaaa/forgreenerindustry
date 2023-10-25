/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.gui;

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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
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
            .filter(r -> "en attend".equalsIgnoreCase(r.getStatus()))
            .filter(r -> "Blocker".equalsIgnoreCase(r.getPriority()))
            .count();
    }

    Long blockerTrait = null;
    if (allrec != null) {
        blockerTrait = allrec.stream()
            .filter(r -> "traiteé".equalsIgnoreCase(r.getStatus()))
            .filter(r -> "Blocker".equalsIgnoreCase(r.getPriority()))
            .count();
    }

    Long majorCosmeticNon = null;
    if (allrec != null) {
        majorCosmeticNon = allrec.stream()
            .filter(r -> "en attend".equalsIgnoreCase(r.getStatus()))
            .filter(r -> "Major Cosmetic".equalsIgnoreCase(r.getPriority()))
            .count();
    }

    Long majorCosmeticTrait = null;
    if (allrec != null) {
        majorCosmeticTrait = allrec.stream()
            .filter(r -> "traiteé".equalsIgnoreCase(r.getStatus()))
            .filter(r -> "Major Cosmetic".equalsIgnoreCase(r.getPriority()))
            .count();
    }

    // Create PieChart.Data objects for each combination only if counts are not null
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

    if (blockerNon != 0) {
        pieChartData.add(new PieChart.Data("Blocker Non-Traitée", blockerNon));
    }

    if (blockerTrait !=0) {
        pieChartData.add(new PieChart.Data("Blocker Traitée", blockerTrait));
    }

    if (majorCosmeticNon != 0) {
        pieChartData.add(new PieChart.Data("Major Cosmetic Non-Traitée", majorCosmeticNon));
    }

    if (majorCosmeticTrait != 0) {
        pieChartData.add(new PieChart.Data("Major Cosmetic Traitée", majorCosmeticTrait));
    }

    // Set the data for the ChartP PieChart
    ChartP.setData(pieChartData);

    // Adjust the size of the chart based on the number of data points
    double scaleFactor = Math.max(1.0, pieChartData.size() / 5.0); // Adjust as needed
   ChartP.setPrefSize(600, 600); 
}


}