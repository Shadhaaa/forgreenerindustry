/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.gui;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tn.edu.forGreenerIndustry.entities.Post;

/**
 * FXML Controller class
 *
 * @author mila
 */
public class DetailsFXMLController implements Initializable {

    @FXML
    private Label tfTitre;
    @FXML
    private Label tfContenu;
    @FXML
    private Label tfDate;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void setPostData(Post post) {
        tfTitre.setText(post.getTitre());
        tfContenu.setText(post.getContenu());
        
        
        Date date = post.getDate();
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Modify the date format as needed
            String formattedDate = dateFormat.format(date);
            tfDate.setText(formattedDate);
        } else {
            tfDate.setText("");
        }
    
        
    }
    
}
