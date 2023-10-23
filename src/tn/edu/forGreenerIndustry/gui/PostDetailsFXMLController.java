/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import tn.edu.forGreenerIndustry.entities.Post;

/**
 * FXML Controller class
 *
 * @author mila
 */
public class PostDetailsFXMLController implements Initializable {

    @FXML
    private Label tfTitle;
    @FXML
    private Label tfContent;
    @FXML
    private Label tfDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    public void setPostData(Post post) {
        tfTitle.setText(post.getTitre());
        tfContent.setText(post.getContenu());
        tfDate.setText(post.getDate().toString());
    }
    
}
