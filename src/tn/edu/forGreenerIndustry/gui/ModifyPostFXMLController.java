/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.gui;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tn.edu.forGreenerIndustry.entities.Post;
import tn.edu.forGreenerIndustry.services.ServicePost;

/**
 * FXML Controller class
 *
 * @author mila
 */
public class ModifyPostFXMLController implements Initializable {

    @FXML
    private TextField tfTitre;
    @FXML
    private TextField tfContenu;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private DatePicker DatePicker;
    @FXML
    private TextField tfImage;
    @FXML
    private Label message;
    @FXML
    private Button btnUpdate;
    
    private Post selectedPost;

    public void setPostData(Post post) {
        selectedPost = post;
        populateFields();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBox.getItems().addAll("Event", "Product", "News");
    }    

    private void populateFields() {
        tfTitre.setText(selectedPost.getTitre());
        comboBox.setValue(selectedPost.getTypeDeContenu());
        tfContenu.setText(selectedPost.getContenu());
        tfImage.setText(selectedPost.getImage());
        if (selectedPost.getDate() != null) {
            LocalDate localDate = selectedPost.getDate().toLocalDate();
            DatePicker.setValue(localDate);
        }
    }
    
    @FXML
    private void btnUpdate(ActionEvent event) {
        String titre = tfTitre.getText();
        String selectedType = comboBox.getValue();
        String contenu = tfContenu.getText();
        LocalDate localDate = DatePicker.getValue();
        Date date = Date.valueOf(localDate);
        String imageUrl = tfImage.getText();

        // Check if any of the fields are empty
        if (titre.isEmpty() || selectedType == null || contenu.isEmpty() || date == null) {
            message.setText("Please fill in all fields.");
            return;
        }

        // Update the selected post
        selectedPost.setTitre(titre);
        selectedPost.setTypeDeContenu(selectedType);
        selectedPost.setContenu(contenu);
        selectedPost.setDate(date);
        selectedPost.setImage(imageUrl);

        // Save the updated post
        ServicePost service = new ServicePost();
        service.modifier(selectedPost);

        // Display a success message
        message.setText("Post updated successfully.");
    }
    
    

    
    
}
