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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.edu.forGreenerIndustry.entities.Commentaires;
import tn.edu.forGreenerIndustry.entities.Post;
import tn.edu.forGreenerIndustry.services.ServiceCommentaires;
import tn.edu.forGreenerIndustry.services.ServicePost;
import tn.edu.forGreenerIndustry.tools.QrCode;
import tn.edu.forGreenerIndustry.gui.mailing;


/**
 * FXML Controller class
 *
 * @author mila
 */
public class CommentsFXMLController implements Initializable {

    
    @FXML
    private TableView<Post> TableView;
    
    
    @FXML
    private Button home;
    @FXML
    private TableColumn<Post, String> tfTitre;
    @FXML
    private TableColumn<Post, String> tfContenu;
    @FXML
    private TableColumn<Post, String> tfImage;
    @FXML
    private TableColumn<Post, Date> tfDate;
    @FXML
    private Button btnAddCmnt;
    
    @FXML
    private Label msg;
    
    @FXML
    private Button btnShowCmnt;
    
    
    private ServicePost postService;
    private ServiceCommentaires commentairesService;
    
    @FXML
    private Button generateQRButton;
    @FXML
    private Button reportButton;
    @FXML
    private AnchorPane AnchorPane;
    
    
    

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfTitre.setCellValueFactory(new PropertyValueFactory<Post, String>("titre"));    
        tfContenu.setCellValueFactory(new PropertyValueFactory<Post, String>("contenu"));
        tfImage.setCellValueFactory(new PropertyValueFactory<Post, String>("image"));
        tfDate.setCellValueFactory(new PropertyValueFactory<Post, Date>("date"));
    
        postService = new ServicePost();
        commentairesService = new ServiceCommentaires();
        loadPostData();
    } 
    
    private void loadPostData() {
        ObservableList<Post> posts = FXCollections.observableArrayList(postService.getAll(null));
        TableView.setItems(posts);
    }

    @FXML
    private void btnAdd(ActionEvent event) {
        Post selectedPost = TableView.getSelectionModel().getSelectedItem();

        if (selectedPost != null) {
            int postId = selectedPost.getId_post();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AddCommentFXML.fxml"));
                Parent root = loader.load();
                AddCommentFXMLController addCommentController = loader.getController();
                addCommentController.setPostId(postId);

                Stage stage = new Stage();
                stage.setTitle("Add Comment");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
        // Show an alert if no row is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Post Selected");
            alert.setHeaderText("Please select a post to add a comment to.");
            alert.showAndWait();
        }  
    }


    @FXML
    private void btnShowCmnt(ActionEvent event) {
        Post selectedPost = TableView.getSelectionModel().getSelectedItem();

        if (selectedPost != null) {
            int postId = selectedPost.getId_post();

        
            List<Commentaires> comments = commentairesService.getCommentsByPostId(postId);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowCmntFXML.fxml"));
                Parent root = loader.load();
                ShowCmntFXMLController showCmntController = loader.getController();
                showCmntController.setComments(comments); // Pass the comments to the controller

            Scene showCmntScene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Comments for Post: " + selectedPost.getTitre());
            stage.setScene(showCmntScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } else {
        // Show an alert when no post is selected
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Post Selected");
        alert.setHeaderText(null);
        alert.setContentText("Please select a post to view comments.");
        alert.showAndWait();
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

    @FXML
    private void generateQRButton(ActionEvent event) {
        Post selectedPost = TableView.getSelectionModel().getSelectedItem(); // Get the selected post

    if (selectedPost != null) {
        int postId = selectedPost.getId_post(); // Get the id_post of the selected post

        // Create a new instance of the QR code generator form and pass the postId
        QrCode qrCodeForm = new QrCode(postId);
        qrCodeForm.setVisible(true);
    } else {
        // No post is selected, show an alert
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("No Post Selected");
        alert.setHeaderText(null);
        alert.setContentText("Please select a post before generating a QR code.");
        alert.showAndWait();
    }
    }

    @FXML
    private void reportButton(ActionEvent event) {
        Post selectedPost = TableView.getSelectionModel().getSelectedItem();

    if (selectedPost != null) {
        // Create a new mailing frame
        mailing mailingFrame = new mailing();

        // Set the subject textfield to the selected post's title
        

        // Set the mailing frame visible
        mailingFrame.setVisible(true);
    } else {
        // No post selected, show an alert
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Post Selected");
        alert.setHeaderText(null);
        alert.setContentText("Please select a post to report.");
        alert.showAndWait();
    }
    }

    @FXML
    private void btnStat(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StatPostFXML.fxml"));
        Parent root = loader.load();
        Scene statisticsScene = new Scene(root);

        // Create a new stage for the statistics interface
        Stage statisticsStage = new Stage();
        statisticsStage.setScene(statisticsScene);
        statisticsStage.setTitle("Statistics");
        statisticsStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    
    
}
