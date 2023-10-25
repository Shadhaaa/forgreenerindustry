/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.gui;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author mila
 */
public class StatPostFXMLController implements Initializable {

    @FXML
    private BorderPane borderPane;
    
    private BarChart barChart;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        barChart = buildBarChart();
        
        
    }    

    @FXML
    private void handleShowBar(ActionEvent event) {
        borderPane.setCenter(barChart);
    }
    
     private BarChart buildBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Most Popular Post");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("# comments");

        BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);

        XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<String, Number>();
        dataSeries1.setName("Popular Post by user interactions");

        dataSeries1.getData().add(new XYChart.Data<String, Number>("Product Launch", 25));
        dataSeries1.getData().add(new XYChart.Data<String, Number>("Event", 7));
        dataSeries1.getData().add(new XYChart.Data<String, Number>("New Cars", 12));
        dataSeries1.getData().add(new XYChart.Data<String, Number>("Makeup", 8));
        dataSeries1.getData().add(new XYChart.Data<String, Number>("Recruiting", 10));
        dataSeries1.getData().add(new XYChart.Data<String, Number>("Welcome", 3));
        barChart.getData().add(dataSeries1);

        return barChart;
    }
     
    @FXML
    private void handleClose(ActionEvent event) {
        System.exit(0);
    }
  
}
