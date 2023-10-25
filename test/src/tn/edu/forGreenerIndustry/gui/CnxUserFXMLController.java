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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
public class CnxUserFXMLController implements Initializable {
    Alert alert1 = new Alert(Alert.AlertType.WARNING);
    Stage primaryStage = new Stage() ;
    User u1 = new User ();
    ServiceUser Service = new ServiceUser();
    User u = new User();
    Entreprise enter = new Entreprise();
    ServiceEntreprise en = new ServiceEntreprise();
  
  

    @FXML
    private TextField TFEmail_cnx;
    @FXML
    private TextField TFMdp_cnx;
    @FXML
    private AnchorPane InterfaceCnx;
  
    @FXML
    private BorderPane borderpanecnx;
    @FXML
    private Hyperlink Link_cree_compte;
    @FXML
    private Button BTN_cnx1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
 

    @FXML
    private void cree_compte(ActionEvent event) {
    
       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       stage.close();

       // Chargez l'interface UserAjouterFXML et affichez-la
       try {
           Parent root = FXMLLoader.load(getClass().getResource("UserAjouterFXML.fxml"));
           Scene scene = new Scene(root);
           Stage adminStage = new Stage();
           adminStage.setTitle("Nouveau compte");
           adminStage.setScene(scene);
           adminStage.show();
       } catch (IOException e) {
           e.printStackTrace();
           // Gérez les erreurs en cas de problème lors du chargement de l'interface UserAjouterFXML
       }
    }


    @FXML
    private void cnx1(ActionEvent event) throws IOException {
   
   String emailRecherche = TFEmail_cnx.getText();

    // Recherche parmi les utilisateurs
    User utilisateurSelectionne = null;
    for (User user : Service.getAll(u)) {
        if (user.getMail().equals(emailRecherche)) {
            utilisateurSelectionne = user;
            break;
        }
    }

    // Recherche parmi les entreprises
    Entreprise entrepriseSelectionnee = null;
    for (Entreprise entreprise : en.getAll(enter)) {
        if (entreprise.getMail().equals(emailRecherche)) {
            entrepriseSelectionnee = entreprise;
            break;
        }
    }

    Stage stage = (Stage) TFEmail_cnx.getScene().getWindow();
    String r = "";
    String email = TFEmail_cnx.getText();
    String mdp = TFMdp_cnx.getText();
    boolean emailTrouve = false;
    String mdp_corr = "";
    int ucid = -1;

    // Chercher le mail dans une liste
    List<User> allUsers = Service.getAll(u);
    for (User user : allUsers) {
        if (user.getMail().equals(email)) {
            emailTrouve = true;
            mdp_corr = user.getMdp1();
            r = user.getRole();
            ucid = user.getId_user();
            break;
        }
    }
      List<Entreprise> allUserss = en.getAll(entrepriseSelectionnee);
    for (Entreprise user : allUserss) {
        if (user.getMail().equals(email)) {
            emailTrouve = true;
            mdp_corr = user.getMdp1();
            r = user.getRole();
            ucid = user.getId_entreprise();
            break;
        }
    }
    System.out.println(r);
    System.out.println(ucid);

     if ("admin".equals(email) && "admin".equals(mdp)) {
        // Vous avez saisi les identifiants admin
        stage.close();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AdminHomeFXML.fxml"));
            Scene scene = new Scene(root);
            Stage adminStage = new Stage();
            adminStage.setTitle("Bienvenue Admin");
            adminStage.setScene(scene);
            adminStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } else if (email.isEmpty() || mdp.isEmpty()) {
        // Les champs sont vides
        alert1.setContentText("Veuillez saisir votre email et votre mot de passe.");
        alert1.showAndWait();
    } else if (emailTrouve) {
        // Le mail a été trouvé, vérifiez le mot de passe
        System.out.println("i m in email trouve");

        if (mdp_corr.equals(mdp)) {
            System.out.println("i m loged");

            if (r.equals("CLIENT")) {
                System.out.println("i m client");
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Clienthome.fxml"));
                    Parent root = loader.load();

                    ClienthomeController modificationController = loader.getController();
                    // ID de l'utilisateur
                    modificationController.TFid_corc.setText(String.valueOf(ucid));
                    modificationController.role_home.setText("CLIENT");

                    Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage2.close();

                    Scene scene = new Scene(root);
                    Stage adminStage = new Stage();
                    adminStage.setTitle("Accueil user");
                    adminStage.setScene(scene);
                    adminStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (r.equals("INVESTISSEUR")) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Invhome.fxml"));
                    Parent root = loader.load();

                    InvhomeController modificationController = loader.getController();
                    // ID de l'utilisateur
                    modificationController.TFid_cor.setText(String.valueOf(ucid));
                    modificationController.role_home.setText("INVESTISSEUR");

                    stage.close();

                    Scene scene = new Scene(root);
                    Stage adminStage = new Stage();
                    adminStage.setTitle("Bienvenue investisseur");
                    adminStage.setScene(scene);
                    adminStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else  {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Agenthome.fxml"));
                    Parent root = loader.load();

                    AgenthomeController modificationController = loader.getController();
                    // ID de l'utilisateur
                    modificationController.TFid_cor.setText(String.valueOf(ucid));
                    modificationController.role_home.setText("AGENT_ENTREPRISE");

                    stage.close();

                    Scene scene = new Scene(root);
                    Stage adminStage = new Stage();
                    adminStage.setTitle("Bienvenue agent d'entreprise");
                    adminStage.setScene(scene);
                    adminStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            // Mot de passe incorrect
            alert1.setContentText("Mot de passe incorrect.");
            alert1.showAndWait();
        }
    } else {
        // Pas d'email trouvé
        alert1.setContentText("Pas d'email trouvé.");
        alert1.showAndWait();
    }}}