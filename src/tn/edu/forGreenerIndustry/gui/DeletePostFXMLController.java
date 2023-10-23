/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
public class DeletePostFXMLController implements Initializable {

    @FXML
    private TableView<Post> postTable;
    @FXML
    private Button btnDelete;
    @FXML
    private Button home;
    
    private ServicePost service;
   
    @FXML
    private TableColumn<Post, Integer> tfEntreprise;
    @FXML
    private TableColumn<Post, String> tfTitre;
   
    @FXML
    private TableColumn<Post, String> tfContenu;
    @FXML
    private TableColumn<Post, String> tfImage;
    @FXML
    private TableColumn<Post, Date> tfDate;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        tfEntreprise.setCellValueFactory(new PropertyValueFactory<Post, Integer>("idEntreprise"));
        tfTitre.setCellValueFactory(new PropertyValueFactory<Post, String>("titre"));
        
        tfContenu.setCellValueFactory(new PropertyValueFactory<Post, String>("contenu"));
        tfImage.setCellValueFactory(new PropertyValueFactory<Post, String>("image"));
        tfDate.setCellValueFactory(new PropertyValueFactory<Post, Date>("date"));
    
    
        service = new ServicePost();
        populatePostTable();
    }    
    
    private void populatePostTable() {
        List<Post> posts = service.getAll(new Post());
        postTable.setItems(FXCollections.observableArrayList(posts));
    }

    @FXML
    private void btnDelete(ActionEvent event) {
        Post selectedPost = postTable.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText("Are you sure you want to delete this post?");
            alert.setContentText("This action cannot be undone.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                service.supprimer(selectedPost.getId_post());
                populatePostTable(); // Refresh list
            }
        }
    }

    @FXML
    private void btnHome(ActionEvent event) {
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
    
}
