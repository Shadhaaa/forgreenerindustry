/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.edu.forGreenerIndustry.entities.Post;
import tn.edu.forGreenerIndustry.services.ServicePost;

/**
 * FXML Controller class
 *
 * @author mila
 */
public class AllPostsFXMLController implements Initializable {

    
    @FXML
    private TableColumn<Post, String> tfTitre;
    @FXML
    private TableColumn<Post, String> tfType;
    @FXML
    private TableColumn<Post, String> tfContenu;
    @FXML
    private TableColumn<Post, String> tfImage;
    @FXML
    private TableColumn<Post, Date> tfDate;
    @FXML
    private TableView<Post> tableView;
    
    private ServicePost service;
    @FXML
    private Button home;
    @FXML
    private Button btnCmment;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override 
    public void initialize(URL url, ResourceBundle rb) {
    
    tfTitre.setCellValueFactory(new PropertyValueFactory<Post, String>("titre"));
    tfType.setCellValueFactory(new PropertyValueFactory<Post, String>("type"));
    tfContenu.setCellValueFactory(new PropertyValueFactory<Post, String>("contenu"));
    tfImage.setCellValueFactory(new PropertyValueFactory<Post, String>("image"));
    tfDate.setCellValueFactory(new PropertyValueFactory<Post, Date>("date"));
    
    service = new ServicePost();
        loadPostData();
}
    
     @FXML
    private void btnReturn(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardFXML.fxml"));
            Parent root = loader.load();
            Scene dashboardScene = new Scene(root);
            Stage stage = (Stage) home.getScene().getWindow();
            stage.setScene(dashboardScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadPostData() {
        // Fetch posts 
        ObservableList<Post> posts = FXCollections.observableArrayList(service.getAll(null));
        tableView.setItems(posts);
    }

    @FXML
    private void btnComment(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CommentsFXML.fxml"));
            Parent root = loader.load();
            Scene dashboardScene = new Scene(root);
            Stage stage = (Stage) btnCmment.getScene().getWindow();
            stage.setScene(dashboardScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
