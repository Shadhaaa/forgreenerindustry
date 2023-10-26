/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.edu.forGreenerIndustry.entities.Reclamation;
import tn.edu.forGreenerIndustry.services.ServiceReclamation;
import tn.edu.forGreenerIndustry.tools.DataSource;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ModifierReclamationController implements Initializable {

    Connection cnx = DataSource.getInstance().getConnection();
    ServiceReclamation sr = new ServiceReclamation(cnx);
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

    private Label ErrNom;

    private Label ErrPrenom;
    private Label ErrEmail;
    private Label ErrTel;
    private Label ErrDesc;
    private Label ErrService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();
        Reclamation selectedReclamation = Main.rec;

        if (selectedReclamation != null) {
            NomRec.setText(selectedReclamation.getNom());
            prenomRec.setText(selectedReclamation.getPrenom());
            EmailRec.setText(selectedReclamation.getEmail());
            TelRec.setText(selectedReclamation.getNumero_mobile());
            DescRec.setText(selectedReclamation.getDescription());
            ServicereclameSel.setValue(selectedReclamation.getNomServcie());
        }
    }

    @FXML
    private void retourReclamation(ActionEvent event) throws IOException {
        DescRec.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("GestionReclamation.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void saveReclamation(ActionEvent event) throws IOException {
        init();

        // Récupérez la réclamation sélectionnée depuis Main.rec
        Reclamation selectedReclamation = Main.rec;

        if (selectedReclamation != null) {
            int idReclamation = selectedReclamation.getIdReclamation();
            String newNom = NomRec.getText();
            String newPrenom = prenomRec.getText();
            String newEmail = EmailRec.getText();
            String newNumeroMobile = TelRec.getText();
            String newDescription = DescRec.getText();
            String newService = ServicereclameSel.getValue();
            if (newNom.isEmpty() || newPrenom.isEmpty() || newEmail.isEmpty() || newNumeroMobile.isEmpty() || newDescription.isEmpty() || newService == null) {
                // Affichez un message d'erreur approprié pour chaque champ manquant
                if (newNom.isEmpty()) {
                    ErrNom.setVisible(true);
                }
                if (newPrenom.isEmpty()) {
                    ErrPrenom.setVisible(true);
                }
                if (newEmail.isEmpty()) {
                    ErrEmail.setVisible(true);
                }
                if (newNumeroMobile.isEmpty()) {
                    ErrTel.setVisible(true);
                }
                if (newDescription.isEmpty()) {
                    ErrDesc.setVisible(true);
                }
                if (newService == null) {
                    ErrService.setVisible(true);
                }
                return; // Arrêtez le traitement si des champs sont manquants
            }

            Date dateCreation = selectedReclamation.getDate_creation();

            // Récupérez la date de traitement actuelle de la réclamation
            Date dateTraitement = selectedReclamation.getDate_traitement();

            // Créez la réclamation modifiée
            Reclamation modifiedReclamation = new Reclamation(
                    selectedReclamation.getIdReclamation(),
                    newNom,
                    newPrenom,
                    newEmail,
                    selectedReclamation.getNomImage(), // Vous pouvez mettre à jour l'image ici si nécessaire
                    newNumeroMobile,
                    dateCreation,
                    dateTraitement,
                    newDescription,
                    selectedReclamation.getStatus(),
                    newService, // Utilisez newService ici, car c'est la variable que vous avez déclarée
                    selectedReclamation.getPriority() // Si vous avez besoin de conserver la priorité, récupérez-la de la réclamation sélectionnée
            );

            // Appelez votre service de réclamation pour effectuer la mise à jour
            sr.modifier(modifiedReclamation);

            // Redirigez l'utilisateur vers la page de gestion des réclamations
            DescRec.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("GestionReclamation.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
            // Redirigez l'utilisateur vers la page de gestion des réclamations
            retourReclamation(event);

        }
    }

    public void init() {
        ErrNom.setVisible(false);
        ErrPrenom.setVisible(false);
        ErrEmail.setVisible(false);
        ErrTel.setVisible(false);
        ErrDesc.setVisible(false);
        ErrService.setVisible(false);
    }

   

}
