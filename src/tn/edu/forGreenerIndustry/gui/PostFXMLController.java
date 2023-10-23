/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.edu.forGreenerIndustry.entities.Post;
import tn.edu.forGreenerIndustry.services.ServicePost;

/**
 * FXML Controller class
 *
 * @author mila
 */
public class PostFXMLController implements Initializable {

    @FXML
    private Label tfPost;
    @FXML
    private TextField tfEntreprise;
    @FXML
    private TextField tfTitre;
    @FXML
    private TextField tfContenu;
    @FXML
    private ComboBox<String> comboBoxType;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField tfImage;
    
    private AllPostsFXMLController allPostsController;
    @FXML
    private Button home;
    @FXML
    private Button btnAjouter;
    @FXML
    private Label testID;
    @FXML
    private Button btnShow;
    @FXML
    private ImageView imageView;
    
    
    
    public void setAllPostsController(AllPostsFXMLController allPostsController) {
        this.allPostsController = allPostsController;
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        comboBoxType.getItems().addAll("Event", "Product", "News");

        
        comboBoxType.setValue("Product");
    }    

    @FXML
    private void btnAjouter(ActionEvent event) {
            try {
        int idEntreprise = Integer.parseInt(tfEntreprise.getText());
        String titre = tfTitre.getText();
        String selectedValue = comboBoxType.getValue();
        String contenu = tfContenu.getText();
        LocalDate localDate = datePicker.getValue();
        String imageUrl = tfImage.getText();

        // Check if required fields are empty
        if (titre.isEmpty() || contenu.isEmpty()) {
            testID.setText("Please fill in all the required fields.");
        } else {
            Date date = null;
            if (localDate != null) {
                date = Date.valueOf(localDate);
            } else {
                testID.setText("Please fill in all the required fields.");
            }

            ServicePost service = new ServicePost();

            Post newPost = new Post(titre, selectedValue, contenu, date, imageUrl);

            service.ajouter(newPost);

            testID.setText("Post added successfully");

            if (allPostsController != null) {
                allPostsController.loadPostData();
            }
            System.out.println("Successfully added post");
        }
    } catch (NumberFormatException e) {
        testID.setText("Invalid input. Please enter valid numbers.");
    }
        
    }

    
    @FXML
    private void btnShow(ActionEvent event) {
        try {
            // AllPosts 
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AllPostsFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) tfPost.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
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
    private void btnAddImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg")
        );

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            imageView.setImage(image);
        
        
            tfImage.setText(selectedFile.toURI().toString());
    }
    }

   
}

/* @FXML
    private void file(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pick a banner file!");
        fileChooser.setInitialDirectory(new File("\\"));
        Stage stage = new Stage();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image img = SwingFXUtils.toFXImage(bufferedImage, null);
                System.out.println(file.getAbsolutePath());
                imageeget = file.toURI().toURL().toString();
                System.out.println(imageeget);

                imageV.setImage(img);
                imageText.setText(file.getAbsolutePath().toString());
            } catch (IOException ex) {
                System.out.println("Could not get the image.");
            }
        } else {
            // L'utilisateur a annulé la sélection de fichier, vous pouvez gérer ce cas ici.
            System.out.println("Sélection de fichier annulée.");
        }
    }*/