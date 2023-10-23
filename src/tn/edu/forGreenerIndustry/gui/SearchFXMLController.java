/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.gui;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import tn.edu.forGreenerIndustry.entities.Post;
import tn.edu.forGreenerIndustry.services.ServicePost;

/**
 * FXML Controller class
 *
 * @author mila
 */
public class SearchFXMLController implements Initializable {

    @FXML
    private Label lblResult;
    @FXML
    private TextField tfSearch;
    @FXML
    private Button home;
    @FXML
    private Button btnSearch;
   
   
    @FXML
    private TableView<Post> TableView2;
    @FXML
    private TableColumn<Post, String> tfTitle;
    @FXML
    private TableColumn<Post, String> tfContenu;
    @FXML
    private TableColumn<Post, String> tfImage;
    @FXML
    private TableColumn<Post, Date> date;
    
    private ObservableList<Post> searchResults;
    @FXML
    private Button btnShowDetails;
    
    
    

    /**
     * Initializes the controller class.
     */
    
    

    
        @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        tfTitle.setCellValueFactory(new PropertyValueFactory("titre"));
        tfContenu.setCellValueFactory(new PropertyValueFactory("contenu"));
        tfImage.setCellValueFactory(new PropertyValueFactory("image"));
        date.setCellValueFactory(new PropertyValueFactory("date"));

        searchResults = FXCollections.observableArrayList();
        TableView2.setItems(searchResults);
        
        

    } 

    @FXML
    private void btnSearchPost(ActionEvent event) {
        String titre = tfSearch.getText();

    
        ServicePost service = new ServicePost();
        List<Post> foundPosts = service.getPostByTitre(titre); // Implement this method in your ServicePost class

        searchResults.clear();
        searchResults.addAll(foundPosts);

        if (foundPosts.isEmpty()) {
            lblResult.setText("No posts found with the title: " + titre);
        } else {
            lblResult.setText("Found " + foundPosts.size() + " post(s) with the title: " + titre);
        }
    }
    

    @FXML
    private void btnModify(ActionEvent event) {
        Post selectedPost = TableView2.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            openModifyInterface(selectedPost);
        } else {
            lblResult.setText("Please select a post to modify.");
        }
    
}
    
    private void openModifyInterface(Post selectedPost) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyPostFXML.fxml"));
            Parent root = loader.load();
            ModifyPostFXMLController modifyController = loader.getController();
            modifyController.setPostData(selectedPost);

            Stage stage = new Stage();
            stage.setTitle("Modify Post");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
   // lezem Tools | Templates.
   

    @FXML
    private void btnShowDetails(ActionEvent event) {
        Post selectedPost = TableView2.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            openDetailsInterface(selectedPost);
        } else {
            lblResult.setText("Please select a post ");
        }
    }
    
    private void openDetailsInterface(Post selectedPost) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsFXML.fxml"));
            Parent root = loader.load();
            DetailsFXMLController detailsController = loader.getController();
            detailsController.setPostData(selectedPost);

            Stage stage = new Stage();
            stage.setTitle("Post Details");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
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
