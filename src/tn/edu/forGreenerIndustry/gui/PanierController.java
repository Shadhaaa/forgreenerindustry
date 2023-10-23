/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.edu.forgreenerindustry.entities.Panier;
import tn.edu.forgreenerindustry.services.Paniercrud;

/**
 * FXML Controller class
 *
 * @author ACHREF
 */
public class PanierController implements Initializable {

    @FXML
    private Spinner<Integer> Quantitespin;
    @FXML
    private TableView<Panier> tabPanier;
    @FXML
    private TableColumn<Panier, String> columnProduit;
    @FXML
    private TableColumn<Panier, Integer> Columnquantite;
    @FXML
    private TableColumn<Panier, Double> columnprixu;
    @FXML
    private TableColumn<Panier, Double> columnprixtot;
    @FXML
    private TextField txtProduit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Paniercrud paniercrud = new Paniercrud();
    List<Panier> myList = paniercrud.readAllPaniers();
    ObservableList<Panier> panierData = FXCollections.observableArrayList(myList);
    tabPanier.setItems(panierData);
    columnProduit.setCellValueFactory(new PropertyValueFactory<Panier, String>("nomproduit"));
    Columnquantite.setCellValueFactory(new PropertyValueFactory<Panier, Integer>("quantite"));
    columnprixu.setCellValueFactory(new PropertyValueFactory<Panier, Double>("prix"));
    columnprixtot.setCellValueFactory(new PropertyValueFactory<Panier, Double>("total"));


        
    SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
    Quantitespin.setValueFactory(valueFactory);
    }    

   @FXML
private void AfficherPanier(ActionEvent event) {
    Paniercrud paniercrud = new Paniercrud();
    List<Panier> myList = paniercrud.readAllPaniers();
    ObservableList<Panier> panierData = FXCollections.observableArrayList(myList);
    tabPanier.setItems(panierData);
    columnProduit.setCellValueFactory(new PropertyValueFactory<Panier, String>("nomproduit"));
    Columnquantite.setCellValueFactory(new PropertyValueFactory<Panier, Integer>("quantite"));
    columnprixu.setCellValueFactory(new PropertyValueFactory<Panier, Double>("prix"));
    columnprixtot.setCellValueFactory(new PropertyValueFactory<Panier, Double>("total"));
}


   @FXML
private void ModifierPanier(ActionEvent event) {
    Panier selectedPanier = tabPanier.getSelectionModel().getSelectedItem();
    if (selectedPanier != null) {

       
        int quantite = Quantitespin.getValue();
        String nomProduit = txtProduit.getText();

        if (quantite <= 0 || nomProduit.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs du formulaire.");
            alert.showAndWait();
        } else {
           
            selectedPanier.setQuantite(quantite);
            selectedPanier.setNomproduit(nomProduit);
            
            
            double prixUnitaire = columnprixu.getCellData(selectedPanier);
            
           
           
            double nouveauTotal = quantite * prixUnitaire;
            selectedPanier.setTotal(nouveauTotal);

            Paniercrud paniercrud = new Paniercrud();
            paniercrud.modifierPanier(selectedPanier);

           
            tabPanier.refresh();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Panier modifié avec succès !");
            alert.showAndWait();
        }
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un panier à modifier.");
        alert.showAndWait();
    }
}


@FXML
private void AjouterPanier(ActionEvent event) {
    int quantite = Quantitespin.getValue();
    String nomProduit = txtProduit.getText();
    
    if (nomProduit.isEmpty()) {
        
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champ manquant");
        alert.setHeaderText("Le champ nom du produit est vide");
        alert.setContentText("Veuillez saisir un nom pour le produit avant d'ajouter au panier.");
        alert.showAndWait();
        return; 
    }

    int clientId = 15;
    int produitId = 23;
    double prix = 320.6;
    double total = quantite * prix; 

    Paniercrud paniercrud = new Paniercrud();
    List<Panier> paniersDuClient = paniercrud.readAllPaniers();

    // Vérifiez s'il existe un panier avec le même produitId
    boolean produitExiste = false;
    for (Panier panier : paniersDuClient) {
        if (panier.getProduitId() == produitId) {
           
            int nouvelleQuantite = panier.getQuantite() + quantite;
            double nouveauTotal = nouvelleQuantite * prix;
            panier.setQuantite(nouvelleQuantite);
            panier.setTotal(nouveauTotal);
            panier.setNomproduit(nomProduit);

            
            paniercrud.modifierPanier(panier);
            produitExiste = true;
            break;
        }
    }

    if (!produitExiste) {
        
        Panier panier = new Panier();
        panier.setClientId(clientId);
        panier.setProduitId(produitId);
        panier.setQuantite(quantite);
        panier.setPrix(prix);
        panier.setTotal(total);
        panier.setNomproduit(nomProduit);

       
        paniercrud.ajouterPanier(panier);
    }

    
    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
    successAlert.setTitle("Succès");
    successAlert.setHeaderText(null);
    successAlert.setContentText("Panier mis à jour avec succès !");
    successAlert.showAndWait();
    System.out.println("Panier ajouté ou mis à jour");
}



  @FXML
private void SupprimerPanier(ActionEvent event) {
    if (tabPanier.getSelectionModel().getSelectedItem() != null) {
        Panier selectedPanier = tabPanier.getSelectionModel().getSelectedItem();

        Paniercrud paniercrud = new Paniercrud();
        paniercrud.supprimerPanier(selectedPanier.getPanierId());

        
        AfficherPanier(event);

       
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("Panier supprimé avec succès !");
        alert.showAndWait();
    }
}

  
@FXML
private void Commander(ActionEvent event) {
    
    Node source = (Node) event.getSource();
    
    Scene sc = source.getScene();
    
    Stage stage = (Stage) sc.getWindow();
    Panier selectedPanier = tabPanier.getSelectionModel().getSelectedItem();
    
    if (selectedPanier != null) {
        double total = selectedPanier.getTotal();
        int panierId = selectedPanier.getPanierId();

        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Commande1.fxml"));
            Parent root = loader.load();
            
           
            Commande1Controller controller = loader.getController();
            controller.setMontantAndPanierId(total, panierId);

            Scene scene = new Scene(root);
            
            
            Stage commande1Stage = new Stage();
            commande1Stage.setScene(scene);
            
            
            stage.close();

            
            commande1Stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }else {
       
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aucune sélection");
        alert.setHeaderText("Aucune ligne n'est sélectionnée");
        alert.setContentText("Veuillez sélectionner une ligne dans le tableau avant de passer une commande.");
        alert.showAndWait();
    }
}


    
}

