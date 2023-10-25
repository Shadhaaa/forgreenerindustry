/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forgreenerindustry.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tn.edu.forGreenerIndustry.services.ServiceEntreprise;
import tn.edu.forgreenerindustry.entities.Entreprise;
import tn.edu.forgreenerindustry.entities.User;
import tn.edu.forgreenerindustry.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author shadha
 */
public class UserModifierFXMLController implements Initializable {

    @FXML
    public TextField TFMdp1_aj;
    @FXML
    public TextField TFPrenom_aj;
    @FXML
    public TextField TFNum_aj;
    @FXML
    public TextField TFPdp_aj;
    @FXML
    public TextField TFMail_aj;
    @FXML
    public TextField TFAdresse_aj;
    @FXML
    public RadioButton RBFemme;
    @FXML
    public ToggleGroup RBGenre_aj;
    @FXML
    public RadioButton RBHomme;
    @FXML
    public ChoiceBox<String> CBRole_aj;
    @FXML
    public TextField TFNom_aj;
    @FXML
    public TextField TFMdp2_aj;
    @FXML
    public Button BTNajouter_user;
    @FXML
    public TextField TFNom_entreprise_aj;
    @FXML
    public TextField TFLogo_aj;
    @FXML
    public TextField TFSecteur_aj;
    @FXML
    public TextField TFDesc_aj;
    @FXML
    public Button btnrtr_home;
    @FXML
    public ImageView pdp;
    @FXML
    public ImageView logo;
    public Button TFlogo_aj;
    @FXML
    public Button btnpdp_aj;
    @FXML
    public TextField TFid_user;
        Alert alert = new Alert(Alert.AlertType.ERROR);
                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
    @FXML
    public TextField rolemdf;
     String r ;
       private String [] role = {"CLIENT","INVESTISSEUR","AGENT_ENTREPRISE"};

int id_use ; 
    @FXML
    public Pane Paneentreprise;
    @FXML
    private Button TFlogo_aj1;
    @FXML
    public TextField tfadmin_mod;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           CBRole_aj.getItems().addAll(role);
                    TFid_user.setVisible(false);
                    tfadmin_mod.setVisible(false);
                    rolemdf.setVisible(false);
                }
        
    
                 

   @FXML
private void BTNajouter_user(ActionEvent event) throws IOException {
    User utilisateurSelectionne = new User();
  Entreprise entrepriseSelectionnee = new Entreprise();
    ServiceUser sp = new ServiceUser();
    ServiceEntreprise en = new ServiceEntreprise();

    String utls = "HOMME";
    //genre
    RadioButton selected_genre = (RadioButton) RBGenre_aj.getSelectedToggle();
    String genreValue = selected_genre.getText();
    if (genreValue.equals("FEMME")) {
        utls = "FEMME";
    }

    boolean emailExiste1 = false;
    for (User utilisateur : sp.getAll(utilisateurSelectionne)) {
        if (utilisateur.getMail().equals(TFMail_aj.getText())) {
            emailExiste1 = true;
            id_use = utilisateur.getId_user();
            break; // Sortir de la boucle dès qu'une correspondance est trouvée
        }
    }

    boolean verf = (id_use == Integer.parseInt(TFid_user.getText()));
    if (!verf && emailExiste1) {
        alert.setContentText("Ce mail est déjà utilisé");
        alert.showAndWait();
    } else if (TFMail_aj.getText().isEmpty()) {
        alert.setContentText("Veuillez saisir votre mail");
        alert.showAndWait();
    } else if (!TFMdp1_aj.getText().equals(TFMdp2_aj.getText())) {
        alert1.setContentText("Mot de passe non conforme");
        alert1.showAndWait();
    } else if (!estEntier(TFNum_aj.getText())) {
        alert.setTitle("Erreur");
        alert.setHeaderText("Numéro invalide");
        alert.setContentText("Veuillez saisir un numéro valide.");
        alert.showAndWait();
    } else if (rolemdf.getText().equals("CLIENT") || rolemdf.getText().equals("INVESTISSEUR")) {
        // L'action est "modf" (modifier)
        // Appelez ServiceUser.modifier() pour mettre à jour l'utilisateur
        utilisateurSelectionne.setId_user(Integer.parseInt(TFid_user.getText()));
        utilisateurSelectionne.setNom(TFNom_aj.getText());
        utilisateurSelectionne.setPrenom(TFPrenom_aj.getText());
        utilisateurSelectionne.setAdresse(TFAdresse_aj.getText());
        utilisateurSelectionne.setNum(Integer.parseInt(TFNum_aj.getText()));
        utilisateurSelectionne.setMail(TFMail_aj.getText());
        utilisateurSelectionne.setMdp1(TFMdp1_aj.getText());
        utilisateurSelectionne.setPdp(TFPdp_aj.getText());
        utilisateurSelectionne.setRole(rolemdf.getText());
        utilisateurSelectionne.setGenre(selected_genre.getText());
        sp.modifier(utilisateurSelectionne);

        
            alert1.setTitle("Succès");
            alert1.setHeaderText("Modification réussie");
            alert1.setContentText("La modification a été effectuée avec succès.");
            alert1.showAndWait();

          



    } else if (rolemdf.getText().equals("AGENT_ENTREPRISE")) {
    entrepriseSelectionnee.setRole("AGENT_ENTREPRISE");
    entrepriseSelectionnee.setId_entreprise(Integer.parseInt(TFid_user.getText()));
    entrepriseSelectionnee.setNom(TFNom_aj.getText());
    entrepriseSelectionnee.setPrenom(TFPrenom_aj.getText());
    entrepriseSelectionnee.setAdresse(TFAdresse_aj.getText());
    entrepriseSelectionnee.setNum(Integer.parseInt(TFNum_aj.getText()));
    entrepriseSelectionnee.setMail(TFMail_aj.getText());
    entrepriseSelectionnee.setMdp1(TFMdp1_aj.getText());
    entrepriseSelectionnee.setPdp(TFPdp_aj.getText());
    entrepriseSelectionnee.setNom_entreprise(TFNom_entreprise_aj.getText());
    entrepriseSelectionnee.setLogo(TFLogo_aj.getText());
    entrepriseSelectionnee.setSecteur(TFSecteur_aj.getText());
    entrepriseSelectionnee.setDescription(TFDesc_aj.getText());

    // Assurez-vous de gérer le choix du genre (RBGenre_aj)
    if (RBGenre_aj.getSelectedToggle() == RBFemme) {
        entrepriseSelectionnee.setGenre("FEMME");
    } else {
        entrepriseSelectionnee.setGenre("HOMME");
    }

    // Vérifiez si l'e-mail existe déjà dans la base de données
    boolean emailExiste = false;
    for (Entreprise utilisateur : en.getAll(entrepriseSelectionnee)) {
        if (utilisateur.getMail().equals(TFMail_aj.getText())) {
            emailExiste = true;
            id_use = utilisateur.getId_entreprise();
            break; // Sortir de la boucle dès qu'une correspondance est trouvée
        }
    }

    boolean verf1 = (id_use == Integer.parseInt(TFid_user.getText()));
    if (!verf1 && emailExiste) {
        alert.setContentText("Ce mail est déjà utilisé");
        alert.showAndWait();
    } else if (TFMail_aj.getText().isEmpty()) {
        alert.setContentText("Veuillez saisir votre mail");
        alert.showAndWait();
    } else if (!TFMdp1_aj.getText().equals(TFMdp2_aj.getText())) {
        alert1.setContentText("Mot de passe non conforme");
        alert1.showAndWait();
    } else if (!estEntier(TFNum_aj.getText())) {
        alert.setTitle("Erreur");
        alert.setHeaderText("Numéro invalide");
        alert.setContentText("Veuillez saisir un numéro valide.");
        alert.showAndWait();
    } else {
        // L'action est "ajouter"
        // Appelez ServiceEntreprise.modifier() pour ajouter l'entreprise
        en.modifier(entrepriseSelectionnee);

             alert1.setTitle("Succès");
            alert1.setHeaderText("Modification réussie");
            alert1.setContentText("La modification a été effectuée avec succès.");
            alert1.showAndWait();
    

       
    }
    if (tfadmin_mod.getText() == "admin"){
      // Fermez l'interface courante
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();

            // Chargez l'interface AdminHomeFXML et affichez-la
            try {
                Parent root = FXMLLoader.load(getClass().getResource("AdminHomeFXML.fxml"));
                Scene scene = new Scene(root);
                Stage adminStage = new Stage();
                adminStage.setTitle("Accueil Administrateur");
                adminStage.setScene(scene);
                adminStage.show();
            } catch (IOException e) {
                e.printStackTrace();
                // Gérez les erreurs en cas de problème lors du chargement de l'interface AdminHomeFXML
            }
             
   

    }}}
    
        private boolean estEntier(String texte) {
    try {
        Integer.parseInt(texte);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}

    @FXML
    private void retour_home(ActionEvent event) {
   
         // Fermez l'interface courante
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.close();

    // Chargez l'interface AdminHomeFXML et affichez-la
    try {
        Parent root = FXMLLoader.load(getClass().getResource("AdminHomeFXML.fxml"));
        Scene scene = new Scene(root);
        Stage adminStage = new Stage();
        adminStage.setTitle("Accueil Administrateur");
        adminStage.setScene(scene);
        adminStage.show();
    } catch (IOException e) {
        e.printStackTrace();
        // Gérez les erreurs en cas de problème lors du chargement de l'interface AdminHomeFXML
    }}

    @FXML
    private void Uploadlogo(ActionEvent event) {
    }

    @FXML
    private void Uploadpdp(ActionEvent event) {
    }
    
}
