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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.net.RequestOptions;
import com.stripe.param.ChargeCreateParams;
import java.util.Properties;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;








/**
 * FXML Controller class
 *
 * @author ACHREF
 */
public class PaiementController implements Initializable {

    @FXML
    private Label txtMontatnt;
    @FXML
    private TextField txtcodepromo;
    @FXML
    private Label txtmontantaPayer;
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
   
    return codePromo.equals("forgreener") || codePromo.equals("FGTH");
}


private double appliquerCodePromo(double montantTotal, String codePromo) {
   
    if (codePromo.equals("forgreener")) {
        return montantTotal * 0.9;
    } else if (codePromo.equals("FGTH")) {
        return montantTotal= 1; 
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
    
        String email = txtEmail.getText();
        String montantAPayer = txtmontantaPayer.getText();

        if (email.isEmpty() || !isValidEmail(email)) {
            afficherAlerte("Erreur de saisie", "Veuillez saisir une adresse e-mail valide.");
            return;
        }

        if (montantAPayer.isEmpty()) {
            afficherAlerte("Erreur de saisie", "Veuillez saisir le montant à payer.");
            return;
        }
    try {
            // Récupérer les informations du formulaire
            long montant = (long) (Double.parseDouble(txtmontantaPayer.getText()) * 100); // Convertir en centimes
            
            //String numeroCarte = txtNumCarte.getText();
           // String moisExpiration = String.valueOf(DateEX.getValue().getMonthValue()); // Convertir le mois en chaîne
           // String anneeExpiration = String.valueOf(DateEX.getValue().getYear()); // Convertir l'année en chaîne
           // String cvc = txtCVC.getText();
            //String email = txtEmail.getText();
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
                 // Envoyez un e-mail de confirmation
            //String recipientEmail = txtEmail.getText(); // Récupérez l'adresse e-mail du destinataire depuis le champ de texte

            // Appelez la méthode sendEmail pour envoyer l'e-mail
            sendEmail(email);
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
        private void sendEmail(String recipientEmail) {
        final String username = "achref.ghribi@esprit.tn";
        final String password = "223JMT4655";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); // Remplacez par le serveur SMTP approprié
        props.put("mail.smtp.port", "587"); // Port SMTP (587 est couramment utilisé)

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
        protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
        return new javax.mail.PasswordAuthentication(username, password);
        }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
           message.setSubject("Confirmation de paiement - ForGreener Industry");
        message.setText("Cher client,\n\n"
                + "Nous sommes ravis de vous informer que votre paiement a été traité avec succès. "
                + "Le montant de votre paiement a été confirmé et votre commande sera traitée dans les plus brefs délais.\n\n"
                + "Merci de faire confiance à ForGreener Industry.\n\n"
                + "Cordialement,\n"
                + "L'équipe ForGreener Industry");

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            // Lancer une exception pour signaler l'erreur
            throw new RuntimeException("Erreur lors de l'envoi de l'e-mail", e);
        }
        
    }
        private void afficherAlerte(String titre, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(regex);
    }
}
  


