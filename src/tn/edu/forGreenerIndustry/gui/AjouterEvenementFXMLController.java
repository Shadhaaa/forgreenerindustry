/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forgreenerindustry.gui;

import com.sun.istack.internal.logging.Logger;
import static java.awt.SystemColor.text;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import java.sql.*;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import tn.edu.forgreenerindustry.entities.Evenement;

/**
 * FXML Controller class
 *
 * @author milou
 */
public class AjouterEvenementFXMLController implements Initializable {

    @FXML
    private Label event;
    @FXML
    private Pane paneEvent;
    @FXML
    private Label titreE;
    @FXML
    private Label dateE;
    @FXML
    private Label qrE;
    @FXML
    private Label imgeE;
    @FXML
    private Label lieuE;
    @FXML
    private Label descriptionE;
    @FXML
    private TextField TFtitre;
    @FXML
    private TextField TFdate;
    @FXML
    private TextField TFqr;
    @FXML
    private TextField TFimage;
    @FXML
    private TextField TFlieu;
    @FXML
    private TextField TFdescription;
    @FXML
    private Button btnAjouter;
    @FXML
    private TableColumn<?, ?> idEventColumn;
    @FXML
    private TableColumn<?, ?> idEntrepriseColumn;
    @FXML
    private TableColumn<?, ?> titreEventColumn;
    @FXML
    private TableColumn<?, ?> dateEventColumn;
    @FXML
    private TableColumn<?, ?> qrEventColumn;
    @FXML
    private TableColumn<?, ?> imageEventColumn;
    @FXML
    private TableColumn<?, ?> lieuEventColumn;
    @FXML
    private TableColumn<?, ?> descriptionEventColumn;

       
    int myIndex;
    int id;
    
    private Connection cnx;
    PreparedStatement pst;   
    public void Connect(){
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/for_greener_industry","root","");
                    System.out.println("Connected to DB!"); // Message de succès

        }catch (SQLException ex){
                    System.err.println("Database Connection Error: " + ex.getMessage()); // Message d'erreur en cas de problème de connexion

        }

     }
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connect();
    }  

    @FXML
    private void Ajouter(ActionEvent event) {
        
    String titre = TFtitre.getText();
    String date = TFdate.getText();
    String qr = TFqr.getText();
    String image = TFimage.getText();
    String lieu = TFlieu.getText();
    String description = TFdescription.getText();
    Connect();
    try {
        String sql = "INSERT INTO table_evenement (titre, date, qr, image, lieu, description) VALUES (?, ?, ?, ?, ?, ?)";
        pst.setString(1, titre);
        pst.setString(2, date);
        pst.setString(3, qr);
        pst.setString(4, image);
        pst.setString(5, lieu);
        pst.setString(6, description);

        // Exécutez la requête
        int rowsAffected = pst.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Événement ajouté avec succès.");
            // Vous pouvez également effacer les champs de texte ou afficher un message de succès.
        } else {
            System.err.println("Échec de l'ajout de l'événement.");
            // Affichez un message d'erreur en cas d'échec.
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Test Connection");
            
            alert.setHeaderText("Espace Evenement");
            alert.setContentText("Ajouter!!");
            alert.showAndWait();
        }
    }catch (SQLException ex) {
        System.err.println("Erreur lors de l'ajout de l'événement : " + ex.getMessage());
        // Affichez un message d'erreur en cas d'échec.
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Espace Evenement");
        alert.setContentText("Erreur lors de l'ajout de l'événement : " + ex.getMessage());
        alert.showAndWait();
    }
            
            
         // Cette méthode met à jour votre TableView avec les données de la base de données
private void tableTableView() {
    try {
        // Suppose que vous avez déjà configuré les colonnes du TableView
        // Récupérez les données de la base de données
        String sql = "SELECT * FROM table_evenement";
        PreparedStatement pst = cnx.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        // Créez une liste observable pour stocker vos données
        ObservableList<Evenement> dataList = FXCollections.observableArrayList();

        while (rs.next()) {
            // Créez un objet Evenement avec les données de la base de données
            Evenement evenement = new Evenement();
            evenement.setTitre(rs.getString("titreEvent"));
            evenement.setDate(rs.getString("date"));
            evenement.setQR(rs.getString("qr"));
            evenement.setImage(rs.getString("image"));
            evenement.setLieu(rs.getString("lieu"));
            evenement.setDescription(rs.getString("description"));

            // Ajoutez l'objet Evenement à la liste observable
            dataList.add(evenement);
        }

        // Mettez à jour votre TableView avec les nouvelles données
        // Supposons que vous avez un TableView nommé "tableView" avec les colonnes appropriées
        tableView.setItems(dataList);

    } catch (SQLException ex) {
        System.err.println("Erreur lors de la mise à jour du TableView : " + ex.getMessage());
    }
}   
            
            
            
           tabletableView();
            TFtitre.setText("");
            TFdate.setText("");
            TFqr.setText("");
            TFimage.setText("");
            TFlieu.setText("");
            TFdescription.setText("");  
          
            public void tableView() throws SQLException {
            Connect();
            ObservableList<Evenement> Evenements = FXCollections.observableArrayList();
            try {
            pst =cnx.prepareStatement("Select id_evenement, id_entreprise , titre_evenement , date_evenement, QRcode,lieu_Evenement,description_evenement from evenement");
                    ResultSet rs = pst.executeQuery();
                    while (rs.next())
                    {
                    Evenement ev = new Evenement();
                    ev.setId_evenement(rs.getInt("id"));
                    ev.setId_entreprise(rs.getInt("id"));
                    ev.setTitre_evenement(rs.getString("id"));
                    ev.setDate_evenement(rs.getDate("id"));
                    ev.setQRcode(rs.getString("id"));
                    ev.setImage_evenement(rs.getString("id"));
                    ev.setDescription_evenement(rs.getString("id"));
                    Evenements.ajouter(ev);
                    }
                    catch(){
                            
                            }
                }
            } catch (SQLException ex) {
            Logger.getLogger(AjouterEvenementController.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Associez l'ObservableList à votre TableView
             tableView.setItems(Evenements);
            // Associez les propriétés de vos objets aux colonnes du TableView
            idEventColumn.setCellValueFactory(new PropertyValueFactory<>("id_evenement"));
            idEntrepriseColumn.setCellValueFactory(new PropertyValueFactory<>("id_entreprise"));
            titreEventColumn.setCellValueFactory(new PropertyValueFactory<>("titre_evenement"));
            dateEventColumn.setCellValueFactory(new PropertyValueFactory<>("date_evenement"));
            qrEventColumn.setCellValueFactory(new PropertyValueFactory<>("qr_code"));
            imageEventColumn.setCellValueFactory(new PropertyValueFactory<>("image_evenement"));
            lieuEventColumn.setCellValueFactory(new PropertyValueFactory<>("lieu_evenement"));
               descriptionEventColumn.setCellValueFactory(new PropertyValueFactory<>("description_evenement"));

    private void tabletableView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    
}    
    
    
    
    
   
    
    

