/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.gui;

import java.awt.image.BufferedImage;
import java.io.File;

import java.io.IOException;
import static java.lang.Math.random;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import tn.edu.forGreenerIndustry.entities.Reclamation;
import tn.edu.forGreenerIndustry.services.MailService;
import tn.edu.forGreenerIndustry.services.ServiceReclamation;
import tn.edu.forGreenerIndustry.tools.DataSource;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AjouterREclamationController implements Initializable {

    Connection cnx = DataSource.getInstance().getConnection();
    ServiceReclamation sr = new ServiceReclamation(cnx);
    Random random = new Random();
    int rand = random.nextInt(100000);
    Reclamation Rcl = new Reclamation();

    @FXML
    private TextField NomRec;
    @FXML
    private TextField prenomRec;
    @FXML
    private TextField EmailRec;
    @FXML
    private TextField TelRec;
    @FXML
    private TextArea DescRec;
    @FXML
    private ComboBox<String> ServicereclameSel;

    private String[] a = {"Service evenement  ", "Service investissement", "Service Communication", "Service Produit"};

    @FXML
    private Label ErrNom;
    @FXML
    private Label ErrPrenom;
    @FXML
    private Label ErrEmail;

    @FXML
    private Label ErrTel;
    @FXML
    private Label ErrDesc;
    @FXML
    private Label ErrService;
    @FXML
    private ImageView imageV;

    /**
     * Initializes the controller class.
     */
    String imageeget = "pas d'image";
    @FXML
    private TextField imageText;
    @FXML
    private Label ErrImage;
    @FXML
    private TextArea descriptionTextArea;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServicereclameSel.getItems().setAll(a);
        init();

    }

    @FXML
    private void retourReclamation(ActionEvent event) throws IOException {

        if (Main.role == "admin") {
            DescRec.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("GestionReclamation.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } else {
            DescRec.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("EspaceUser.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();

        }
    }

    @FXML

    private void saveREclamation(ActionEvent event) throws IOException {
        init();

        int x = 0;
        try {
            Integer.parseInt(TelRec.getText());
        } catch (Exception e) {
            ErrTel.setVisible(true);
            ErrTel.setText(" numero invalide");
            x = 1;
        }

        if (NomRec.getText().isEmpty()) {
            ErrNom.setVisible(true);
            ErrNom.setText("le champ est obligatoire");
            x = 1;
        }
        if (prenomRec.getText().isEmpty()) {
            ErrPrenom.setVisible(true);
            ErrPrenom.setText("le champ est obligatoire");
            x = 1;
        }
        if (EmailRec.getText().isEmpty()) {
            ErrEmail.setVisible(true);
            ErrEmail.setText("le champ est obligatoire");
            x = 1;
        } else {
            // Validate email format
            String emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
            if (!EmailRec.getText().matches(emailPattern)) {
                ErrEmail.setVisible(true);
                ErrEmail.setText("Invalide adresse email ");
                x = 1;
            }
        }
        if (TelRec.getText().isEmpty()) {
            ErrTel.setVisible(true);
            ErrTel.setText("le champ est obligatoire");
            x = 1;
        }
        if (DescRec.getText().isEmpty()) {
            ErrDesc.setVisible(true);
            ErrDesc.setText("le champ est obligatoire");
            x = 1;
        }
        if (imageText.getText().isEmpty()) {
            ErrImage.setVisible(true);
            ErrImage.setText("le champ est obligatoire");
            x = 1;
        }
        if (ServicereclameSel.getValue() == null) {
            ErrService.setVisible(true);
            ErrService.setText("Select a service");
            x = 1;

        }

        if (x == 0) {
            // Continue with the save logic
            if (x == 0) {
                LocalDateTime now = LocalDateTime.now();
                Date d2 = new Date(2023, 1, 1);
                Date d1 = new Date(now.getYear() - 1901, now.getMonthValue() + 1, now.getDayOfMonth());

                Rcl.setIdReclamation(rand);
                Rcl.setNom(NomRec.getText());
                Rcl.setPrenom(prenomRec.getText());
                Rcl.setEmail(EmailRec.getText());
                Rcl.setScreenshot("image");
                Rcl.setNumero_mobile(TelRec.getText());
                Rcl.setDate_creation(d1);
                Rcl.setDate_traitement(d2);
                Rcl.setDescription(DescRec.getText());
                Rcl.setStatus("");
                Rcl.setNomServcie(ServicereclameSel.getValue());
                
                
                int descrlet = DescRec.getText().length();
                  
                    if(DescRec.getText()!=null){
                            
                            if (DescRec.getText().length() > 20) {
                        Rcl.setPriority("Blocker");
                         sr.ajouter(Rcl);

                    } else if (DescRec.getText().length() < 5) {
                        Rcl.setPriority("Cosmetic");
                         sr.ajouter(Rcl);
                    } else {
                        Rcl.setPriority("Major");
                         sr.ajouter(Rcl);
                    }
                }
                // Envoi de l'e-mail ici
                String to = EmailRec.getText(); // Adresse e-mail de l'utilisateur
                String subject = "Votre réclamation a été enregistrée avec succès";
                String body = "Nous avons bien reçu votre réclamation et nous travaillons à sa prise en charge.";

                MailService mailService = new MailService();
                mailService.sendEmail(to, subject, body);

                if (Main.role.equals("admin")) { // Change to .equals for string comparison
                    DescRec.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("GestionReclamation.fxml"));
                    Stage mainStage = new Stage();
                    Scene scene = new Scene(root);
                    mainStage.setScene(scene);
                    mainStage.show();
                } else {
                    DescRec.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("EspaceUser.fxml"));
                    Stage mainStage = new Stage();
                    Scene scene = new Scene(root);
                    mainStage.setScene(scene);
                    mainStage.show();
                }
            }
        }

    }

    public void init() {
        ErrNom.setVisible(false);
        ErrPrenom.setVisible(false);
        ErrEmail.setVisible(false);
        ErrTel.setVisible(false);
        ErrDesc.setVisible(false);
        ErrService.setVisible(false);
        ErrImage.setVisible(false);
    }

    @FXML
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
    }

    @FXML
    private void soumettreReclamation(ActionEvent event) {
        String description = descriptionTextArea.getText();

        if (isDescriptionClean(description)) {
            // La description est propre, enregistrez la réclamation
            ServiceReclamation serviceReclamation = new ServiceReclamation();

            // Effectuez d'autres actions ou affichez un message de réussite
            System.out.println("Réclamation enregistrée avec succès.");
        } else {
            // La description contient des mots interdits, réagissez en conséquence
            System.out.println("La description contient des mots interdits. Réclamation non enregistrée.");
        }
    }

    private boolean isDescriptionClean(String description) {
        List<String> forbiddenWords = Arrays.asList("fuck", "mot2", "expression1", "expression2");
        for (String word : forbiddenWords) {
            if (description.toLowerCase().contains(word.toLowerCase())) {
                return false; // La description contient un mot interdit
            }
        }
        return true; // La description est propre
    }

}
