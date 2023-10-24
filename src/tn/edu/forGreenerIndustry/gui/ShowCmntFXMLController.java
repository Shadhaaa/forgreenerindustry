/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.gui;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
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
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;

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
        //List<Commentaires> comments = commentairesService.getCommentsByPostId(postId);
        ObservableList<Commentaires> comments = FXCollections.observableArrayList(commentairesService.getCommentsByPostId(postId));
        
        tableView.setItems(comments);
        tfCmnt.setCellValueFactory(new PropertyValueFactory("contenu"));
    }



    public void setComments(List<Commentaires> comments) {
   
    ObservableList<Commentaires> observableComments = FXCollections.observableArrayList(comments);
    tableView.setItems(observableComments);
    tfCmnt.setCellValueFactory(new PropertyValueFactory("contenu"));
}

    

    @FXML
    private void editButton(ActionEvent event) {
        Commentaires selectedComment = tableView.getSelectionModel().getSelectedItem();
    if (selectedComment != null) {
        // Create a TextInputDialog to edit the comment
        TextInputDialog dialog = new TextInputDialog(selectedComment.getContenu());
        dialog.setTitle("Edit Comment");
        dialog.setHeaderText("Edit the selected comment:");
        dialog.setContentText("Comment:");

        // Show the dialog and wait for the user's response
        Optional<String> result = dialog.showAndWait();

        // If the user clicked OK, update the comment
        if (result.isPresent()) {
            String editedComment = result.get();
            selectedComment.setContenu(editedComment);

            // Call the editComment method from commentairesService
            commentairesService.modifier(selectedComment);

             // Refresh the comments
        }
    }
    }

    @FXML
    private void deleteButton(ActionEvent event) {
        Commentaires selectedComment = tableView.getSelectionModel().getSelectedItem();
    if (selectedComment != null) {
        // Create a confirmation dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("Are you sure you want to delete this comment?");
        alert.setContentText("This action cannot be undone.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            commentairesService.supprimer(selectedComment.getId_commentaire());
            tableView.getItems().remove(selectedComment); // Remove the selected comment from the table
        }
    }
    }

    
    
}
