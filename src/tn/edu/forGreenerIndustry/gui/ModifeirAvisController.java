/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.gui;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import tn.edu.forGreenerIndustry.entities.Avis;
import tn.edu.forGreenerIndustry.services.ServiceAvis;
import tn.edu.forGreenerIndustry.tools.DataSource;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ModifeirAvisController implements Initializable {
  Connection cnx = DataSource.getInstance().getConnection();
    ServiceAvis sa = new ServiceAvis(cnx);
    @FXML
    private Label TextAvis;
    @FXML
    private ComboBox<Integer> Selartcle;
    @FXML
    private Label ErrArticle;
    @FXML
    private Label ErrText;
    @FXML
    private RadioButton gr1;
    @FXML
    private ToggleGroup grating;
    @FXML
    private RadioButton gr3;
    @FXML
    private RadioButton gr2;
    @FXML
    private RadioButton gr4;
    @FXML
    private RadioButton gr5;
    @FXML
    private Label notreat;
        private Integer[] a = {1,2,3,4};
        String notte = "0";
    @FXML
    private TextArea texttttt;
    @FXML
    private ComboBox<?> Selcser;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Selartcle.getItems().addAll(a);
        notte = Main.avis.getNoteService()+"";
       texttttt.setText(Main.avis.getDetailAvisService());
       notreat.setText(Main.avis.getNoteService()+"");
      init();
    Selartcle.getItems().addAll(a);
          if(gr1.isSelected()){
            notte="1";
        }else if(gr2.isSelected()){
            notte="2";
        }else if(gr3.isSelected()){
            notte="3";
        }else if(gr4.isSelected()){
            notte="4";
        }else if(gr5.isSelected()){
            notte="5";
        }
          
        notreat.setText(notte); 
    }    

    @FXML
    private void AjouterAvis(ActionEvent event) throws IOException {
                init();
          if(gr1.isSelected()){
            notte="1";
        }else if(gr2.isSelected()){
            notte="2";
        }else if(gr3.isSelected()){
            notte="3";
        }else if(gr4.isSelected()){
            notte="4";
        }else if(gr5.isSelected()){
            notte="5";
        }
          
        notreat.setText(notte);
        int x = 0;
       if (texttttt.getText().equals("")){
              ErrText.setVisible(true);
              ErrText.setText("Champ Obligatoire");
        x=1;
        }
        if (Selartcle.getValue()== null){
              ErrArticle.setVisible(true);
              ErrArticle.setText("Champ Obligatoire");
        x=1;
        }
        if (x == 0){
            Avis a = new Avis(Main.avis.getIdAvis(),texttttt.getText(),Integer.parseInt(notte), 1);
            sa.modifier(a);
                     ErrText.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("GestionAvis.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
        }
    }

    @FXML
    private void AnuulerAvis(ActionEvent event) throws IOException {
                 ErrText.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("GestionAvis.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }
      void init(){
    ErrArticle.setVisible(false);
    ErrText.setVisible(false);
    }
    
}
