/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import tn.edu.forGreenerIndustry.entities.Commentaires;
import tn.edu.forGreenerIndustry.services.ServiceCommentaires;

/**
 * FXML Controller class
 *
 * @author mila
 */
public class ShowCmntFXMLController implements Initializable {
    @FXML
    private TableView<Commentaires> tableView;
    @FXML
    private TableColumn<Commentaires, String> tfCmnt;
    
    private ServiceCommentaires commentairesService;
    private int postId;

    public void setPostId(int postId) {
        this.postId = postId;
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        commentairesService = new ServiceCommentaires();
        loadComments();
    }    
    
    private void loadComments() {
        List<Commentaires> comments = commentairesService.getCommentsByPostId(postId);
        ObservableList<Commentaires> observableComments = FXCollections.observableArrayList(comments);
        tableView.setItems(observableComments);
    }

    @FXML
    private void btnDelete(ActionEvent event) {
    }

    @FXML
    private void btnUpdate(ActionEvent event) {
    }

    @FXML
    private void btnReturn(ActionEvent event) {
    }
    
    
}
