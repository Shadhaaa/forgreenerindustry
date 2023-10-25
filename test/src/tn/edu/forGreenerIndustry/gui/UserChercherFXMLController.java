/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forgreenerindustry.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
public class UserChercherFXMLController implements Initializable {
Parent root = null ;
User u = new User();
Entreprise entr = new Entreprise();
    ServiceEntreprise en = new  ServiceEntreprise();
ServiceUser service = new ServiceUser();
    @FXML
    private Button chercher_id;
    @FXML
    private Button btn_retour_home_chercher;
    @FXML
    private TextField TF_id_chercher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void chercher_user(ActionEvent event) throws IOException {
         // Récupérer l'e-mail recherché
    String emailRecherche = TF_id_chercher.getText();

    // Recherche parmi les utilisateurs
    User utilisateurSelectionne = null;
    for (User user : service.getAll(u)) {
        if (user.getMail().equals(emailRecherche)) {
            utilisateurSelectionne = user;
            break;
        }
    }

    // Recherche parmi les entreprises
    Entreprise entrepriseSelectionnee = null;
    for (Entreprise entreprise : en.getAll(entr)) {
        if (entreprise.getMail().equals(emailRecherche)) {
            entrepriseSelectionnee = entreprise;
            break;
        }
    }

    // Vérifier le type d'objet trouvé
    if (utilisateurSelectionne != null) {
        // Un utilisateur correspondant a été trouvé
        try {
            // Chargez l'interface de modification (UserModifierFXML)
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserModifierFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("affichage d'un  utilisateur");
            stage.show();

            // Accédez au contrôleur de l'interface de modification
            UserModifierFXMLController modificationController = loader.getController();

            // Remplissez les champs de l'interface de modification avec les valeurs de l'utilisateur sélectionné
            modificationController.rolemdf.setText(String.valueOf(utilisateurSelectionne.getRole()));
            modificationController.TFid_user.setText(String.valueOf(utilisateurSelectionne.getId_user()));
            modificationController.TFNom_aj.setText(utilisateurSelectionne.getNom());
            modificationController.TFPrenom_aj.setText(utilisateurSelectionne.getPrenom());
            modificationController.TFAdresse_aj.setText(utilisateurSelectionne.getAdresse());
            modificationController.TFNum_aj.setText(String.valueOf(utilisateurSelectionne.getNum()));
            modificationController.TFMail_aj.setText(utilisateurSelectionne.getMail());
            modificationController.TFMdp1_aj.setText(utilisateurSelectionne.getMdp1());
            modificationController.TFPdp_aj.setText(utilisateurSelectionne.getPdp());

            // Assurez-vous de gérer le choix du genre (RBGenre_aj)
            if (utilisateurSelectionne.getGenre().equals("FEMME")) {
                modificationController.RBGenre_aj.selectToggle(modificationController.RBFemme);
            } else {
                modificationController.RBGenre_aj.selectToggle(modificationController.RBHomme);
            }

            // Assurez-vous de gérer le choix du rôle (CBRole_aj)
            modificationController.CBRole_aj.setValue(utilisateurSelectionne.getRole());
            modificationController.CBRole_aj.setDisable(true);
        } catch (IOException e) {
            e.printStackTrace();
            // Gérez les erreurs en cas de problème lors du chargement de l'interface de modification
        }
    } else if (entrepriseSelectionnee != null) {
        // Une entreprise correspondante a été trouvée
   
            // Chargez l'interface de modification (UserModifierFXML)
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserModifierFXML.fxml"));
            Parent root1 = loader.load();
            Scene scene = new Scene(root1);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Affichage  d agent ");
            stage.show();

            // Accédez au contrôleur de l'interface de modification
            UserModifierFXMLController modificationController = loader.getController();

            // Remplissez les champs de l'interface de modification avec les valeurs de l'entreprise sélectionnée
            modificationController.rolemdf.setText("AGENT_ENTREPRISE");
            modificationController.TFid_user.setText(String.valueOf(entrepriseSelectionnee.getId_entreprise()));
            modificationController.TFNom_aj.setText(entrepriseSelectionnee.getNom());
            modificationController.TFPrenom_aj.setText(entrepriseSelectionnee.getPrenom());
            modificationController.TFAdresse_aj.setText(entrepriseSelectionnee.getAdresse());
            modificationController.TFNum_aj.setText(String.valueOf(entrepriseSelectionnee.getNum()));
            modificationController.TFMail_aj.setText(entrepriseSelectionnee.getMail());
            modificationController.TFMdp1_aj.setText(entrepriseSelectionnee.getMdp1());
            modificationController.TFPdp_aj.setText(entrepriseSelectionnee.getPdp());
            modificationController.TFNom_entreprise_aj.setText(entrepriseSelectionnee.getNom_entreprise());
            modificationController.TFLogo_aj.setText(entrepriseSelectionnee.getLogo());
            
        }
    }

    @FXML
    private void btnhome_chercher(ActionEvent event) {
       
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


    }
 private void loaderpage (String page){
    
        try {
            root = FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
}   

    @FXML
    private void chercher(ActionEvent event) throws IOException {
     }


    

public User getOneByEmail(String email) {
    // Code pour rechercher l'utilisateur par e-mail dans votre source de données
    // Remplacez cette partie avec votre propre logique de recherche

    // Supposons que vous avez une liste d'utilisateurs appelée "usersList"
    List<User> usersList = service.getAll(u); // Méthode factice pour obtenir la liste des utilisateurs

    // Parcourez la liste des utilisateurs pour trouver celui correspondant à l'e-mail spécifié
    for (User user : usersList) {
        if (user.getMail().equalsIgnoreCase(email)) {
            return user; // Retourne l'utilisateur correspondant à l'e-mail
        }
    }

    return null; // Aucun utilisateur correspondant trouvé
}
public Entreprise getOneByEmailentr(String email) {
    // Code pour rechercher l'utilisateur par e-mail dans votre source de données
    // Remplacez cette partie avec votre propre logique de recherche

    // Supposons que vous avez une liste d'utilisateurs appelée "usersList"
    List<Entreprise> usersList = en.getAll(entr); // Méthode factice pour obtenir la liste des utilisateurs

    // Parcourez la liste des utilisateurs pour trouver celui correspondant à l'e-mail spécifié
    for (Entreprise user : usersList) {
        if (user.getMail().equalsIgnoreCase(email)) {
            return user; // Retourne l'utilisateur correspondant à l'e-mail
        }
    }

    return null; // Aucun utilisateur correspondant trouvé
}}
