/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forgreenerindustry.gui;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Provider.Service;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.mail.internet.AddressException;
import tn.edu.forGreenerIndustry.services.ServiceEntreprise;
import tn.edu.forGreenerIndustry.services.Sms;
import tn.edu.forGreenerIndustry.tools.Myemail1;
import tn.edu.forgreenerindustry.entities.Entreprise;
import tn.edu.forgreenerindustry.entities.User;
import tn.edu.forgreenerindustry.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author shadha
 */
public class UserAjouterFXMLController implements Initializable {
   
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
    
    //role
    @FXML
    public ChoiceBox<String> CBRole_aj;
       private String [] role = {"CLIENT","INVESTISSEUR","AGENT_ENTREPRISE"};
    @FXML
    public TextField TFNom_aj;
    @FXML
    public TextField TFMdp2_aj;
    @FXML
    public TextField TFNom_entreprise_aj;
    @FXML
    public TextField TFLogo_aj;
    @FXML
    public TextField TFSecteur_aj;
    @FXML
    public TextField TFDesc_aj;
    @FXML
    public Button BTNajouter_user;
    @FXML
    public ImageView logo;
    @FXML
    public Button btnrtr_home;
    @FXML
    public ImageView pdp;
    public Button TFlogo_aj;
    @FXML
    public Button btnpdp_aj;
   Parent root  = null ; 
    @FXML
    public TextField TFid_user;
     Alert alert1 = new Alert(Alert.AlertType.WARNING);
     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
ServiceUser service = new ServiceUser();

ServiceEntreprise serv = new ServiceEntreprise();
User u = new User();
UserAfficherFXMLController autreI = new UserAfficherFXMLController(); // Créez une nouvelle instance de l'interface
String action1 = autreI.action ;
String act ;
Entreprise entr = new Entreprise();

Entreprise zz = new Entreprise();

    /**
     * Initializes the controller class.
     */
String r ;

    Stage primaryStage = new Stage();
    @FXML
    public TextField TFAdmin;
    @FXML
    private TextField tfgetrole;
    @FXML
    private Pane pane_entr;
    @FXML
    private Button TFlogo_aj1;
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                CBRole_aj.getItems().addAll(role);
                    TFid_user.setVisible(false);
                     pane_entr.setVisible(false);
 CBRole_aj.setOnAction(event -> {
        // Get the selected role
        String selectedRole = CBRole_aj.getValue();

        // Update the text field with the selected role
       // r =selectedRole ;
        tfgetrole.setText(selectedRole);
     
       
    });
  TFAdmin.setVisible(false);
        tfgetrole.setVisible(false);
        btnrtr_home.setVisible(false);
        TFAdmin.setVisible(false);
        tfgetrole.setVisible(true);
    CBRole_aj.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue.equals("AGENT_ENTREPRISE")) {
            pane_entr.setVisible(true);
            r = newValue ;
        } else {
            pane_entr.setVisible(false);
                        r = newValue ;

        }
    });
    }
    
  
    private boolean estEntier(String texte) {
    try {
        Integer.parseInt(texte);
        return true;
    } catch (NumberFormatException e) {
        return false;
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
private void BTNajouter_user(ActionEvent event) throws AddressException, IOException {
    String mdp_corr = null;
    User utilisateur = new User();
    ServiceUser serviceUser = new ServiceUser();
    ServiceEntreprise serviceEntreprise = new ServiceEntreprise();
    String genre = "HOMME";

    // Genre
    RadioButton selected_genre = (RadioButton) RBGenre_aj.getSelectedToggle();
    String genreValue = selected_genre.getText();
    if (genreValue.equals("FEMME")) {
        genre = "FEMME";
    }

    boolean emailExiste = false;

    List<String> mails = serviceUser.getAllMails(u);
    List<String> mailsEntreprise = serviceEntreprise.getAllMails(entr);

    for (User u : serviceUser.getAll(u)) {
        if (u.getMail().equals(TFMail_aj.getText())) {
            emailExiste = true;
            mdp_corr = u.getMdp1();
            break;
        }
    }
    for (Entreprise u : serviceEntreprise.getAll(entr)) {
        if (u.getMail().equals(TFMail_aj.getText())) {
            emailExiste = true;
            mdp_corr = u.getMdp1();
            break;
        }
    }

    if (TFAdmin.getText().equals("admin")) {
        if (emailExiste) {
            alert1.setContentText("Cette adresse e-mail est déjà utilisée");
            alert1.showAndWait();
        } else if (TFMail_aj.getText().isEmpty()) {
            alert1.setContentText("Veuillez saisir votre adresse e-mail");
            alert1.showAndWait();
        } else if (!TFMdp1_aj.getText().equals(TFMdp2_aj.getText())) {
            alert1.setContentText("Les mots de passe ne correspondent pas");
            alert1.showAndWait();
        } else if ( TFMail_aj.getText().matches(".*@.*\\.com")){
            alert1.setContentText("le mail doit contenir @ et finir par '.com' ");
            alert1.showAndWait();
            }
else if (!estEntier(TFNum_aj.getText())) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Numéro invalide");
            alert.setContentText("Veuillez saisir un numéro valide");
            alert.showAndWait();
        } else {
            // Déterminer quel rôle a été sélectionné
            String selectedRole = CBRole_aj.getValue();

            if ("CLIENT".equals(selectedRole) || "INVESTISSEUR".equals(selectedRole)) {
                serviceUser.ajouter(new User(TFNom_aj.getText(), TFPrenom_aj.getText(), TFPdp_aj.getText(), Integer.parseInt(TFNum_aj.getText()), TFMail_aj.getText(), TFMdp1_aj.getText(), selectedRole, TFAdresse_aj.getText(), genre));
            } else if ("AGENT_ENTREPRISE".equals(selectedRole)) {
                serviceEntreprise.ajouter(new Entreprise(TFNom_aj.getText(), TFPrenom_aj.getText(), TFPdp_aj.getText(), Integer.parseInt(TFNum_aj.getText()), TFMail_aj.getText(), TFMdp1_aj.getText(), selectedRole, TFAdresse_aj.getText(), genre, TFLogo_aj.getText(), TFNom_entreprise_aj.getText(), TFSecteur_aj.getText(), TFDesc_aj.getText()));
            }

            alert.setTitle("Succès");
            alert.setHeaderText("Création de compte réussie");
            alert.setContentText("Votre compte a été créé avec succès.");
            alert.showAndWait();

            // Fermer la fenêtre actuelle
            Stage stage = (Stage) BTNajouter_user.getScene().getWindow();
            stage.close();

            // Charger l'interface AdminHomeFXML
            chargerPage("AdminHomeFXML");

        }}
     
       
    


else if (TFAdmin.getText().isEmpty()) {
    System.out.println("zaghret rany user ");
      if (emailExiste) {
            alert1.setContentText("Cette adresse e-mail est déjà utilisée");
            alert1.showAndWait();
        } else if (TFMail_aj.getText().isEmpty()) {
            alert1.setContentText("Veuillez saisir votre adresse e-mail");
            alert1.showAndWait();
        } else if (!TFMdp1_aj.getText().equals(TFMdp2_aj.getText())) {
            alert1.setContentText("Les mots de passe ne correspondent pas");
            alert1.showAndWait();
        } else if (!estEntier(TFNum_aj.getText())) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Numéro invalide");
            alert.setContentText("Veuillez saisir un numéro valide");
            alert.showAndWait();
        } else {
            // Déterminer quel rôle a été sélectionné
            String selectedRole = CBRole_aj.getValue();

    
    if (r.equals("CLIENT")) {
        System.out.println("userr ya 3ini");

        User user = new User(TFNom_aj.getText(), TFPrenom_aj.getText(), TFPdp_aj.getText(), Integer.parseInt(TFNum_aj.getText()), TFMail_aj.getText(),
                TFMdp1_aj.getText(), tfgetrole.getText(), TFAdresse_aj.getText(), genre);
        service.ajouter(user);

        alert1.setTitle("Succès");
        alert1.setHeaderText("Création de compte réussie");
        alert1.setContentText("Votre compte est bien créé.");
        alert1.showAndWait();
        
          //read welcome.html file
                String html = new String(Files.readAllBytes(Paths.get("src/Images/welcome.html")));
                html = html.replace("{{user_email}}", user.getMail());
                html = html.replace("{{user_password}}", user.getMdp1());
                //send welcome email
                new Myemail1().sendMailAsync(user.getMail(), "Welcome to our website", html);

        Stage stage = (Stage) BTNajouter_user.getScene().getWindow();
        stage.close();

        // Charger l'interface ClientHome et l'afficher
          chargerPage("CnxUserFXML");
    } else if (r.equals("INVESTISSEUR")) {
        System.out.println("inv ya 3ini");

        User user = new User(TFNom_aj.getText(), TFPrenom_aj.getText(), TFPdp_aj.getText(), Integer.parseInt(TFNum_aj.getText()), TFMail_aj.getText(),
                TFMdp1_aj.getText(), "INVESTISSEUR", TFAdresse_aj.getText(), genre);
        service.ajouter(user);
        
          //read welcome.html file
                String html = new String(Files.readAllBytes(Paths.get("src/Images/welcome.html")));
                html = html.replace("{{user_email}}", user.getMail());
                html = html.replace("{{user_password}}", user.getMdp1());
                //send welcome email
                new Myemail1().sendMailAsync(user.getMail(), "Welcome to our website", html);

        alert1.setTitle("Succès");
        alert1.setHeaderText("Création de compte réussie");
        alert1.setContentText("Votre compte est bien créé.");
        alert1.showAndWait();

        Stage stage = (Stage) BTNajouter_user.getScene().getWindow();
        stage.close();

        // Charger l'interface InvHome et l'afficher
       chargerPage("CnxUserFXML");
    } else if ("AGENT_ENTREPRISE".equals(tfgetrole.getText())) {
        System.out.println("rany dkhalt lel agent  w ana user");
        zz = new Entreprise(TFNom_aj.getText(), TFPrenom_aj.getText(),
                TFPdp_aj.getText(), Integer.parseInt(TFNum_aj.getText()), TFMail_aj.getText(),
                TFMdp1_aj.getText(), "AGENT_ENTREPRISE", TFAdresse_aj.getText(), genre, TFLogo_aj.getText(),
                TFNom_entreprise_aj.getText(), TFSecteur_aj.getText(), TFDesc_aj.getText());
        serviceEntreprise.ajouter(zz);
        
//mail        
  //read welcome.html file
                String html = new String(Files.readAllBytes(Paths.get("src/Images/welcome.html")));
                html = html.replace("{{user_email}}", zz.getMail());
                html = html.replace("{{user_password}}", zz.getMdp1());
                //send welcome email
                new Myemail1().sendMailAsync(zz.getMail(), "Welcome to our Platform", html);
//sms
//Sms o = new Sms();
//Sms.sendSms("hey jo7i");
        Stage stage = (Stage) BTNajouter_user.getScene().getWindow();
        stage.close();

        // Charger l'interface AgentHome et l'afficher
        chargerPage("CnxUserFXML");
    }
}}

   }
 
 private void chargerPage(String page) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(page + ".fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle(page);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    
    @FXML
    public void retour_home(ActionEvent event) {
        
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

    @FXML
    private void Uploadlogo(ActionEvent event) {
   
    
  FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", ".jpg", ".png", "*.jpeg")
        );

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            logo.setImage(image);
        
        
            TFLogo_aj.setText(selectedFile.toURI().toString());
    
    }
    }
       
    
    

    @FXML
    private void Uploadpdp(ActionEvent event) {
        
         FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", ".jpg", ".png", "*.jpeg")
        );

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            pdp.setImage(image);
        
        
            TFPdp_aj.setText(selectedFile.toURI().toString());
    

        }
    
}}
