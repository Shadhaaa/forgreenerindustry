/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.edu.forgreenerindustry.entities.Panier;
import tn.edu.forgreenerindustry.services.Paniercrud;

/**
 * FXML Controller class
 *
 * @author ACHREF
 */
public class ModifierPanierController implements Initializable {

    private TableView<Panier> tabPanier;
    private TableColumn<Panier, String> columnProduit;
    private TableColumn<Panier, Integer> Columnquantite;
    private TableColumn<Panier, Double> columnprixu;
    private TableColumn<Panier, Double> columnprixtot;
    @FXML
    private TextField txtProduit;
    @FXML
    private Spinner<Integer> Quantitespin;
    private Panier selectedPanier ;
    public void setPanierData(Panier panier) {
        selectedPanier = panier;
        populateFields();
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
    Quantitespin.setValueFactory(valueFactory);
    }   
    private void populateFields() {
        Panier selectedPanier = tabPanier.getSelectionModel().getSelectedItem();
    // Assurez-vous que selectedPanier est correctement défini
    txtProduit.setText(selectedPanier.getNomproduit());
    Quantitespin.getValueFactory().setValue(selectedPanier.getQuantite());
}
  public void initData(Panier selectedPanier, TableView<Panier> tabPanier) {
        this.selectedPanier = selectedPanier;
        this.tabPanier = tabPanier;

        txtProduit.setText(selectedPanier.getNomproduit());
        // Assurez-vous que vous avez la valeur de quantité souhaitée dans l'objet Panier
        // Si la quantité est stockée dans l'objet Panier, vous pouvez l'obtenir et la définir ici.
        // Par exemple : Quantitespin.getValueFactory().setValue(selectedPanier.getQuantite());
    }

   

  

    @FXML
    private void AfficherPanier1(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.close();

    
    Panierwindow panierWindow = new Panierwindow();
    Stage newStage = new Stage();
    panierWindow.start(newStage);
    }



    @FXML
    private void Modifierpanier1(ActionEvent event) {
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
            return; // Ajoutez le return pour arrêter l'exécution en cas d'erreur
        }

        // Mettre à jour les données du panier
        selectedPanier.setQuantite(quantite);
        selectedPanier.setNomproduit(nomProduit);

        // Calculer le nouveau total si nécessaire (en fonction de la quantité, par exemple)
        double prixUnitaire = selectedPanier.getPrix();
        double nouveauTotal = quantite * prixUnitaire;
        selectedPanier.setTotal(nouveauTotal);

        // Mettre à jour le panier dans la base de données
        Paniercrud paniercrud = new Paniercrud();
        paniercrud.modifierPanier(selectedPanier);

        // Rafraîchir la table pour refléter les modifications
        tabPanier.refresh();

        // Afficher un message de confirmation
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Panier modifié avec succès !");
        alert.showAndWait();
    }
    }

        
       
    
}
