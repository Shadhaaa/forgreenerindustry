/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.edu.forGreenerIndustry.entities.Post;
import tn.edu.forGreenerIndustry.services.ServicePost;

/**
 * FXML Controller class
 *
 * @author mila
 */
public class EnterIDFXMLController implements Initializable {

    @FXML
    private Label test;
    @FXML
    private TextField tfEnteredID;
    @FXML
    private Button home;
    @FXML
    private Button btnSearchID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnSearchID(ActionEvent event) {
        try {
        int postId = Integer.parseInt(tfEnteredID.getText());

        if (isValidPostID(postId)) {
            // Valid ID, proceed to the UpdatePostFXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdatePostFXML.fxml"));
            Parent root = loader.load();
            Scene updatePostScene = new Scene(root);

            // Pass the valid post ID to the UpdatePostFXMLController
            UpdatePostFXMLController updatePostController = loader.getController();
            updatePostController.setPostID(postId);

            Stage stage = (Stage) test.getScene().getWindow();
            stage.setScene(updatePostScene);
            stage.setTitle("Update Post");
            stage.show();
        } else {
            // Invalid ID, show an error message in the label
            test.setText("Invalid Post ID");
        }
    } catch (NumberFormatException e) {
        // Handle invalid input (non-numeric input)
        test.setText("Please enter a valid post ID.");
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    
    private boolean isValidPostID(int postId) {
        ServicePost service = new ServicePost();
        Post post = service.getOne(postId);

        return post != null;
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
