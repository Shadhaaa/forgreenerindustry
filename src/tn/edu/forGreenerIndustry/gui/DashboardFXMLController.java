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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mila
 */
public class DashboardFXMLController implements Initializable {

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnShow;
    @FXML
    private Button Search;
    @FXML
    private Button Update;
    @FXML
    private Button Delete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    @FXML
    private void btnAdd(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PostFXML.fxml"));
            Parent root = loader.load();
            Scene postScene = new Scene(root);

            Stage stage = (Stage) btnAdd.getScene().getWindow();
            stage.setScene(postScene);
            stage.setTitle("Add Post");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void btnShowAll(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AllPostsFXML.fxml"));
        Parent root = loader.load();
        Scene allPostsScene = new Scene(root);

        Stage stage = (Stage) btnShow.getScene().getWindow();
        stage.setScene(allPostsScene);
        stage.setTitle("All Posts");
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    

    @FXML
    private void btnUpdate(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EnterIDFXML.fxml"));
        Parent root = loader.load();
        Scene enterIDScene = new Scene(root);

        Stage stage = (Stage) Update.getScene().getWindow();
        stage.setScene(enterIDScene);
        stage.setTitle("Enter Post ID");
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    @FXML
    private void btnDelete(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DeletePostFXML.fxml"));
        Parent root = loader.load();
        Scene deletePostScene = new Scene(root);

        Stage stage = (Stage) Delete.getScene().getWindow();
        stage.setScene(deletePostScene);
        stage.setTitle("Delete Post");
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    @FXML
    private void btnSearch(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchFXML.fxml"));
        Parent root = loader.load();
        Scene searchScene = new Scene(root);

        // Get the current stage (DashboardFXML) and switch to the Search scene
        Stage stage = (Stage) Search.getScene().getWindow();
        stage.setScene(searchScene);
        stage.setTitle("Search Posts");
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    
}
