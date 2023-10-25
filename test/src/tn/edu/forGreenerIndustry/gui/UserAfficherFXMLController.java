/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forgreenerindustry.gui;

import javafx.scene.control.Alert;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.edu.forGreenerIndustry.services.ServiceEntreprise;

import tn.edu.forgreenerindustry.entities.User;
import tn.edu.forgreenerindustry.entities.Entreprise;
import tn.edu.forgreenerindustry.services.ServiceUser;
import tn.edu.forgreenerindustry.tools.DataSource;


/**
 * FXML Controller class
 *
 * @author shadha
 */

 
public class UserAfficherFXMLController implements Initializable {
Parent root = null ;
Alert alert = new Alert(Alert.AlertType.INFORMATION);
Alert alert1 = new Alert(Alert.AlertType.WARNING);
private String [] role = {"CLIENT","INVESTISSEUR","AGENT_ENTREPRISE"};
public String action1 = "modf" ;
public String action = "aj" ;


String stat = "inactive";
ServiceUser Service =new ServiceUser();
ServiceEntreprise serviceentr = new ServiceEntreprise();
ServiceUser serviceusr = new ServiceUser();
User u1 = new User();
Entreprise e1 = new Entreprise();
List<User> n1 = serviceusr.getAll(u1); 
List<Entreprise> entr = serviceentr.getAll(e1);
Entreprise    Selectionne1 = new Entreprise();
User Selectionne = new User();
 String tab_selc = "entreprise" ;
 private User selectedUser; // Pour stocker l'utilisateur sélectionné
private Entreprise selectedEntreprise; // Pour stocker l'entreprise sélectionnée
       public Entreprise entrepriseSelectionnee1 = new Entreprise();
        User utilisateurSelectionne1 = new User();
        int id_sup ;

@FXML
    public Button btnmodifier;
    @FXML
    public Button btnsupprimer;
    @FXML
    public Button btnajouter;
    @FXML
    public TableView<User> tabuser; 
    @FXML
    public Button btnchercher;
    @FXML
    public TableColumn<User, Integer> colid_user;
    @FXML
    public TableColumn<User, String> colnom_user;
    @FXML
    private TableColumn<User, String> colprenom_user;
    @FXML
    public TableColumn<User, String> coladresse_user;
    @FXML
    public TableColumn<User, Integer> colnum_user;
    @FXML
    public TableColumn<User, String> email_user;
    @FXML
    public TableColumn<User, String> colgenre_user;
    @FXML
    public TableColumn<User, String> colmdp_user;
    @FXML
    public TableColumn<Entreprise, Integer> colid_entreprise;
    @FXML
    public TableColumn<Entreprise, String> colnom_entreprise;
    @FXML
    public TableColumn<Entreprise, String> colprenom_entreprise;
    @FXML
    private TableColumn<Entreprise, String> colmdp_entreprise;
    @FXML
    public TableColumn<Entreprise, String> colnom_entreprise_entreprise;
    @FXML
    public TableColumn<Entreprise, Integer> colnum_entreprise;
    @FXML
    public TableColumn<Entreprise, String> colmail_entreprise;
    @FXML
    public TableColumn<Entreprise, String> colsecteur_entreprise;
    @FXML
    public TableColumn<Entreprise, String> coldesc_entreprise;         
    @FXML
    public ChoiceBox<String> cbrole;
    @FXML
    public TableView<Entreprise> tabentreprise;
    /**
     * Initializes the controller class.
     */String role55 ;
@Override
public void initialize(URL url, ResourceBundle rb) {
    tabuser.refresh();
    tabentreprise.refresh();
    System.out.println("taaaaaaaaaaaaaaaaaaa");
    
    cbrole.getItems().addAll(role);
    colid_user.setVisible(false);
    colid_entreprise.setVisible(false);

    
    cbrole.setOnAction(event -> onRoleSelected()); // Définit l'action à exécuter lorsque le choix change

    DataSource.getInstance();
    
    //user
    colid_user.setCellValueFactory(cell -> cell.getValue().getIdProperty().asObject());
    colnom_user.setCellValueFactory(cell -> cell.getValue().getNomProperty());
    colprenom_user.setCellValueFactory(cell -> cell.getValue().getPrenomProperty());
    coladresse_user.setCellValueFactory(cell -> cell.getValue().getAdresseProperty());
    colnum_user.setCellValueFactory(cell -> cell.getValue().getNumProperty().asObject());
    email_user.setCellValueFactory(cell -> cell.getValue().getMailProperty());
    colgenre_user.setCellValueFactory(cell -> cell.getValue().getGenreProperty());
    colmdp_user.setCellValueFactory(cell -> cell.getValue().getMdpProperty());

    //entreprise
    colid_entreprise.setCellValueFactory(cell -> cell.getValue().getIdProperty().asObject());
    colnom_entreprise.setCellValueFactory(cell -> cell.getValue().getNomProperty());
    colprenom_entreprise.setCellValueFactory(cell -> cell.getValue().getPrenomProperty());
    colmdp_entreprise.setCellValueFactory(cell -> cell.getValue().getMdpProperty());
    colnom_entreprise_entreprise.setCellValueFactory(cell -> cell.getValue().getNom_entrepriseProperty());
    colnum_entreprise.setCellValueFactory(cell -> cell.getValue().getNumProperty().asObject());
    colmail_entreprise.setCellValueFactory(cell -> cell.getValue().getMailProperty());
    colsecteur_entreprise.setCellValueFactory(cell -> cell.getValue().getSecteurProperty());
    coldesc_entreprise.setCellValueFactory(cell -> cell.getValue().getDesciprtionProperty());

    tabuser.getItems().addAll(Service.getAll(u1));
    tabentreprise.getItems().addAll(serviceentr.getAll(e1));

    tabuser.setRowFactory(tv -> {
        TableRow<User> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !row.isEmpty()) {
                selectedUser = row.getItem();
                selectedEntreprise = null; // Réinitialisez l'entreprise sélectionnée
                
// Vous pouvez également effectuer d'autres actions ici, si nécessaire
            }
        });
        return row;
    });

    tabentreprise.setRowFactory(tv -> {
        TableRow<Entreprise> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !row.isEmpty()) {
                selectedEntreprise = row.getItem();
                selectedUser = null; // Réinitialisez l'utilisateur sélectionné
                role55 = selectedEntreprise.role;// Vous pouvez également effectuer d'autres actions ici, si nécessaire
            }
        });
        return row;
    });
}

private void onRoleSelected() {
    String choix = cbrole.getValue();
    tabuser.getItems().clear();
    tabentreprise.getItems().clear();
    
    if ("CLIENT".equals(choix) || "INVESTISSEUR".equals(choix)) {
        tabuser.setVisible(true);
        tabentreprise.setVisible(false);
        tab_selc = "user";
        
        if ("CLIENT".equals(choix)) {
            List<User> users = getUsersByRole("CLIENT"); // Récupérez les utilisateurs en fonction du rôle
            tabuser.getItems().addAll(users);
        } else {
            List<User> users = getUsersByRole("INVESTISSEUR"); // Récupérez les utilisateurs en fonction du rôle
            tabuser.getItems().addAll(users);
        }
    } else {
        tabentreprise.setVisible(true);
        tabuser.setVisible(false);
        tab_selc = "entreprise";
         List<Entreprise> entr = getUsersByRole1("AGENT_ENTREPRISE"); // Récupérez les utilisateurs en fonction du rôle
            tabentreprise.getItems().addAll(entr);
    }
}

private List<User> getUsersByRole(String role) {
    List<User> filteredUsers = new ArrayList<>();

    if ("CLIENT".equals(role)) {
        for (User user : n1) {
            if ("CLIENT".equals(user.getRole())) {
                filteredUsers.add(user);
            }
        }
    } else if ("INVESTISSEUR".equals(role)) {
        for (User user : n1) {
            if ("INVESTISSEUR".equals(user.getRole())) {
                filteredUsers.add(user);
            }
        }
    }

    return filteredUsers;
}
private List<Entreprise> getUsersByRole1(String role) {
    List<Entreprise> filteredUsers = new ArrayList<>();

    if ("AGENT_ENTREPRISE".equals(role)) {
        for (Entreprise user : entr) {
            if ("AGENT_ENTREPRISE".equals(user.getRole())) {
                filteredUsers.add(user);
            }
        }
    }
    

    return filteredUsers;
}

 
    private void loaderpage (String page){
    
        try {
            Parent root5 = FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UserAfficherFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    

 }
  



    @FXML
    private void btn_ajout_user(ActionEvent event ) {

    try {
                        

        Stage primaryStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
primaryStage1.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserAjouterFXML.fxml"));
        
        Parent root1 = loader.load();
        Scene scene = new Scene(root1);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Ajouter un utilisateur");
        stage.show();
          tabuser.getItems().addAll(Service.getAll(u1));
    tabentreprise.getItems().addAll(serviceentr.getAll(e1));
        UserAjouterFXMLController modificationController = loader.getController();

                                       modificationController.TFAdmin.setText("admin");
    } catch (IOException ex) {
        ex.printStackTrace(); 
    }
}

        
        
       

   @FXML
private void btn_supprimer_user(ActionEvent event) throws IOException {
    // Récupérez l'utilisateur ou l'entreprise sélectionné(e) depuis le TableView correspondant
    User utilisateurSelectionne = tabuser.getSelectionModel().getSelectedItem();
    Entreprise entrepriseSelectionnee = tabentreprise.getSelectionModel().getSelectedItem();

    if (utilisateurSelectionne != null) {
        int idUtilisateurSelectionne = utilisateurSelectionne.getId_user(); // Remplacez "getId()" par la méthode appropriée pour obtenir l'ID de l'utilisateur

        // Affichez une boîte de dialogue de confirmation
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Supprimer l'utilisateur ?");
        alert.setContentText("Êtes-vous sûr de vouloir supprimer cet utilisateur ?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            // L'utilisateur a confirmé la suppression
            // Appelez la méthode de suppression de l'utilisateur avec l'ID correspondant
            Service.supprimer(idUtilisateurSelectionne);

            // Supprimez l'utilisateur de tabuser
            tabuser.getItems().remove(utilisateurSelectionne);

            // Vérifiez si l'utilisateur supprimé est également l'utilisateur sélectionné dans tabentreprise
            if (utilisateurSelectionne.equals(selectedEntreprise)) {
                selectedEntreprise = null; // Réinitialisez l'entreprise sélectionnée
            }
        }
    } else if (entrepriseSelectionnee != null) {
        int idEntrepriseSelectionnee = entrepriseSelectionnee.getId_entreprise(); // Remplacez "getId()" par la méthode appropriée pour obtenir l'ID de l'entreprise

        // Affichez une boîte de dialogue de confirmation
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Supprimer l'entreprise ?");
        alert.setContentText("Êtes-vous sûr de vouloir supprimer cette entreprise ?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            // L'utilisateur a confirmé la suppression
            // Appelez la méthode de suppression de l'entreprise avec l'ID correspondant
            Service.supprimer(idEntrepriseSelectionnee);

            // Supprimez l'entreprise de tabentreprise
            tabentreprise.getItems().remove(entrepriseSelectionnee);

            // Vérifiez si l'entreprise supprimée est également l'utilisateur sélectionné dans tabuser
            if (entrepriseSelectionnee.equals(selectedUser)) {
                selectedUser = null; // Réinitialisez l'utilisateur sélectionné
            }
        }
    }
 // tabuser.getItems().addAll(Service.getAll(u1));
   // tabentreprise.getItems().addAll(serviceentr.getAll(e1));
    
    
    }


    

    @FXML
private void btn_modifier_user(ActionEvent event) {
    // Vérifiez quel onglet est actuellement sélectionné (user ou entreprise)
    if (tab_selc.equals("user")) {
        User utilisateurSelectionne = tabuser.getSelectionModel().getSelectedItem();

        // Assurez-vous qu'un utilisateur est sélectionné
        if (utilisateurSelectionne != null) {
            try {
                // Chargez l'interface de modification (UserModifierFXML)
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.close();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UserModifierFXML.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Modifier un utilisateur");
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
                modificationController.tfadmin_mod.setText("admin");

                // Assurez-vous de gérer le choix du genre (RBGenre_aj)
                if (utilisateurSelectionne.getGenre() == "FEMME" ) {
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
        } else {
            // Si aucun utilisateur n'est sélectionné, affichez un message d'erreur ou effectuez une action appropriée.
        }
    } else if (tab_selc.equals("entreprise")) {
        Entreprise entrepriseSelectionnee = tabentreprise.getSelectionModel().getSelectedItem();

        // Assurez-vous qu'une entreprise est sélectionnée
        if (entrepriseSelectionnee != null) {
            try {
                // Chargez l'interface de modification (UserModifierFXML)
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.close();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UserModifierFXML.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Modifier une entreprise");
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
                modificationController.TFSecteur_aj.setText(entrepriseSelectionnee.getSecteur());
                modificationController.TFDesc_aj.setText(entrepriseSelectionnee.getDescription());

                // Assurez-vous de gérer le choix du genre (RBGenre_aj)
                if (entrepriseSelectionnee.getGenre().equals("FEMME")) {
                    modificationController.RBGenre_aj.selectToggle(modificationController.RBFemme);
                }else {
                    modificationController.RBGenre_aj.selectToggle(modificationController.RBHomme);
                }

                // Assurez-vous de gérer le choix du rôle (CBRole_aj)
                modificationController.CBRole_aj.setValue("AGENT_ENTREPRISE");
                modificationController.CBRole_aj.setDisable(true);
            } catch (IOException e) {
                e.printStackTrace();
                // Gérez les erreurs en cas de problème lors du chargement de l'interface de modification
            }
        }
    }
  tabuser.getItems().addAll(Service.getAll(u1));
    tabentreprise.getItems().addAll(serviceentr.getAll(e1));
}




    @FXML
    private void btn_chercher_user(ActionEvent event) {
         // Fermez l'interface courante
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.close();

    // Chargez l'interface AdminHomeFXML et affichez-la
    try {
        Parent root = FXMLLoader.load(getClass().getResource("UserChercherFXML.fxml"));
        Scene scene = new Scene(root);
        Stage adminStage = new Stage();
        adminStage.setTitle("chercher user");
        adminStage.setScene(scene);
        adminStage.show();
    } catch (IOException e) {
        e.printStackTrace();
        // Gérez les erreurs en cas de problème lors du chargement de l'interface AdminHomeFXML
    }}
     

    
}
                 
      
    

