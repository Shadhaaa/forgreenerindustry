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
    private TableColumn<Post, Integer> tfPost;
    @FXML
    private TableColumn<Post, Integer> tfEntreprise;
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

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override 
    public void initialize(URL url, ResourceBundle rb) {
    tfPost.setCellValueFactory(new PropertyValueFactory<Post, Integer>("idPost"));
    tfEntreprise.setCellValueFactory(new PropertyValueFactory<Post, Integer>("idEntreprise"));
    tfTitre.setCellValueFactory(new PropertyValueFactory<Post, String>("titre"));
    tfType.setCellValueFactory(new PropertyValueFactory<Post, String>("type"));
    tfContenu.setCellValueFactory(new PropertyValueFactory<Post, String>("contenu"));
    tfImage.setCellValueFactory(new PropertyValueFactory<Post, String>("image"));
    tfDate.setCellValueFactory(new PropertyValueFactory<Post, Date>("date"));
    
    service = new ServicePost();
        loadPostData();
}
    
    public void loadPostData() {
        // Fetch all posts and add them to the TableView
        ObservableList<Post> posts = FXCollections.observableArrayList(service.getAll(null));
        tableView.setItems(posts);
    }
 
}
