/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.gui;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.model.Token;
import com.stripe.net.RequestOptions;
import com.stripe.param.ChargeCreateParams;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentMethodCreateParams;
import com.stripe.param.TokenCreateParams;
import javafx.scene.Node;
import javafx.stage.Stage;








/**
 * FXML Controller class
 *
 * @author ACHREF
 */
public class PaiementController implements Initializable {

    @FXML
    private TextField txtMontatnt;
    @FXML
    private TextField txtcodepromo;
    @FXML
    private TextField txtmontantaPayer;
    @FXML
    private TextField txtNumCarte;
    @FXML
    private TextField txtCVC;
    @FXML
    private DatePicker DateEX;
    @FXML
    private TextField txtEmail;
    
    private double montant;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configurez Stripe avec votre clé secrète
        Stripe.apiKey = "sk_test_51O4X5XCZicFmO9OlQe9jkt0X8JF0xPTV2zzNSCWDbl4uUMOPeQIjPbw2HGR9vO19nszhq47MUrxRexNtQNQTiu4900NQpdYyUm";
       
    }
 public void setMontant(double total) {
        montant = total;
         txtMontatnt.setText(Double.toString(montant));
    }    

    @FXML
    private void AjouterCodepromo(ActionEvent event) {
        
    String codePromo = txtcodepromo.getText();

    
    double montantTotal = Double.parseDouble(txtMontatnt.getText());

   
    double montantReduit = montantTotal;

    if (estCodePromoValide(codePromo)) {
        montantReduit = appliquerCodePromo(montantTotal, codePromo);
    }

  
    txtmontantaPayer.setText(String.valueOf(montantReduit));

    
    if (montantReduit < montantTotal) {
        afficherMessage("Code promo appliqué avec succès. Nouveau montant : $" + montantReduit);
    } else {
        afficherMessage("Code promo invalide. Montant inchangé : $" + montantTotal);
    }
}


private boolean estCodePromoValide(String codePromo) {
   
    return codePromo.equals("forgreener"); 
}


private double appliquerCodePromo(double montantTotal, String codePromo) {
   
    if (codePromo.equals("forgreener")) {
        return montantTotal * 0.9; 
    } else {
        return montantTotal; 
    }
}


private void afficherMessage(String message) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Message");
    alert.setHeaderText(message);
    alert.showAndWait();
    }

    @FXML
    private void RetournerPanier(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.close();

    
    Commandewindow1 panierWindow = new Commandewindow1();
    Stage newStage = new Stage();
    panierWindow.start(newStage);
    }

 @FXML
private void Payer(ActionEvent event) throws StripeException {  
    try {
            // Récupérer les informations du formulaire
            long montant = (long) (Double.parseDouble(txtmontantaPayer.getText()) * 100); // Convertir en centimes
            
            //String numeroCarte = txtNumCarte.getText();
           // String moisExpiration = String.valueOf(DateEX.getValue().getMonthValue()); // Convertir le mois en chaîne
           // String anneeExpiration = String.valueOf(DateEX.getValue().getYear()); // Convertir l'année en chaîne
           // String cvc = txtCVC.getText();
            String email = txtEmail.getText();
            /*Créez un token de carte
            TokenCreateParams cardParams = TokenCreateParams.builder()
            .setCard(TokenCreateParams.Card.builder()
            .setNumber(numeroCarte)
            .setExpMonth(moisExpiration)
            .setExpYear(anneeExpiration)
            .setCvc(cvc)
            .build())
            .build();

            Token token = Token.create(cardParams);

// Utilisez l'ID du token pour effectuer le paiement
            //String tokenID = token.getId();
*/

            // Utilisez l'ID du token de test fourni par Stripe
            String tokenID = "tok_visa"; 

            // Créez une charge
            RequestOptions requestOptions = RequestOptions.builder().setApiKey(Stripe.apiKey).build();

            ChargeCreateParams params = ChargeCreateParams.builder()
                    .setAmount(montant)
                    .setCurrency("usd")
                    .setSource(tokenID)
                    .setDescription("Charge for " + email)
                    .build();

            Charge charge = Charge.create(params, requestOptions);

            if (charge.getPaid()) {
                // Le paiement a réussi
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Paiement réussi");
                alert.setHeaderText("Le paiement a été effectué avec succès.");
                alert.showAndWait();
            } else {
                // Le paiement a échoué
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur de paiement");
                alert.setHeaderText("Le paiement a échoué. Veuillez réessayer.");
                alert.showAndWait();
            }
        } catch (StripeException e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur Stripe");
            alert.setHeaderText("Une erreur Stripe est survenue. Veuillez vérifier vos informations.");
            alert.showAndWait();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de format");
            alert.setHeaderText("Veuillez saisir un montant valide.");
            alert.showAndWait();
        }
    }

  
}

