/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forgreenerindustry.gui;

import forgreenerindustry.ForGreenerIndustry;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
    private CheckBox CBdispo_aj;
    @FXML
    private TextField TFMdp1_aj;
    @FXML
    private TextField TFPrenom_aj;
    @FXML
    private TextField TFNum_aj;
    @FXML
    private TextField TFPdp_aj;
    @FXML
    private TextField TFMail_aj;
    @FXML
    private TextField TFAdresse_aj;
    @FXML
    private RadioButton RBFemme;
    @FXML
    private ToggleGroup RBGenre_aj;
    @FXML
    private RadioButton RBHomme;
    
    //role
    @FXML
    private ChoiceBox<String> CBRole_aj;
       private String [] role = {"CLIENT","INVESTISSEUR","AGENT_ENTREPRISE","LIVREUR"};
    //compagnie
    @FXML
    private ChoiceBox<String> CBComp_aj;
    private String [] comp1 = {"AliExpress" ,"Glovo" ,"Jumia" ,"Aucune" ,"Autre" };
    @FXML
    private TextField TFVehicule_aj;
    @FXML
    private TextField TFNom_aj;
    @FXML
    private TextField TFMdp2_aj;
    @FXML
    private TextField TFNom_entreprise_aj;
    @FXML
    private TextField TFLogo_aj;
    @FXML
    private TextField TFSecteur_aj;
    @FXML
    private TextField TFDesc_aj;
    @FXML
    private Button BTNmodifer_user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                CBRole_aj.getItems().addAll(role);
                CBComp_aj.getItems().addAll(comp1);

    }    

    private void BTNajouter_user(ActionEvent event) {
           ServiceUser sp = new ServiceUser();
      //genre 
        RadioButton selected_genre = (RadioButton) RBGenre_aj.getSelectedToggle();
    String genreValue = selected_genre.getText();
    
   //intialize homme selected
    ForGreenerIndustry.Genre g = ForGreenerIndustry.Genre.HOMME ;
    if (genreValue == "FEMME"){
        g = ForGreenerIndustry.Genre.FEMME ;}
   //dispo
    int dispo ;
     boolean dispoValue = CBdispo_aj.isSelected();
    if (dispoValue) {
       dispo = 1 ;// Valeur pour "disponible"
    } else {
        dispo = 0; // Valeur pour "non disponible"
    }
    ForGreenerIndustry.Role r = ForGreenerIndustry.Role.valueOf(CBRole_aj.getValue());
    ForGreenerIndustry.Comp cc = ForGreenerIndustry.Comp.valueOf(CBComp_aj.getValue().toString()) ;
    if (r == ForGreenerIndustry.Role.LIVREUR){
    sp.ajouter(new User(TFNom_aj.getText(), TFPrenom_aj.getText(), TFPdp_aj.getText(), Integer.parseInt(TFNum_aj.getText()),TFMail_aj.getText(),
           TFMdp1_aj.getText(),   r , TFAdresse_aj.getText(), g ,TFVehicule_aj.getText(),  cc, dispo     )); 
    }
    else if (r ==ForGreenerIndustry.Role.CLIENT || r ==ForGreenerIndustry.Role.INVESTISSEUR ){
    
        sp.ajouter(new User(TFNom_aj.getText(), TFPrenom_aj.getText(), TFPdp_aj.getText(), Integer.parseInt(TFNum_aj.getText()),TFMail_aj.getText(),
                TFMdp1_aj.getText(),   r , TFAdresse_aj.getText(), g ,null,  null, 0    ));
           }
    else{
            sp.ajouter (new Entreprise(TFNom_aj.getText(),TFPrenom_aj.getText(),
                    TFPdp_aj.getText(), Integer.parseInt(TFNum_aj.getText()),TFMail_aj.getText(), TFMdp1_aj.getText(),   r , TFAdresse_aj.getText(), "","",  "","" )) ;}
    
    }

    @FXML
    private void BTNmodifer_user(ActionEvent event) {
    }

    
}
