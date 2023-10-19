/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.for_greener_industry.GUI;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import edu.esprit.for_greener_industry.enteties.Produit;
import edu.esprit.for_greener_industry.services.ServiceProduit;
import edu.esprit.for_greener_industry.enteties.Catégorie;
import edu.esprit.for_greener_industry.services.ServiceCatégorie;
import edu.esprit.for_greener_industry.enteties.Energie;
import edu.esprit.for_greener_industry.services.ServiceEnergie;
import edu.esprit.for_greener_industry.enteties.Véhicule;
import edu.esprit.for_greener_industry.services.Servicevéhicule;
import edu.esprit.for_greener_industry.tools.DataSource;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import jdk.nashorn.internal.runtime.regexp.joni.constants.internal.AnchorType;



/**
 * FXML Controller class
 *
 * @author ritha
 */
public class ProduitFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private GridPane anchor;
    @FXML
    private Button FXProduit_ajouter;
    @FXML
    private Button FXProduit_modifier;
    @FXML
    private Button FXProduit_supprimer;
    @FXML
    private Button FXProduit_valider;
    @FXML
    private Button FXProduit_annuler;
    @FXML
    private Button FXProduit_addener;
    @FXML
    private Text FXProduit_conf;
    @FXML
    private TextField FXVéhicile_mark;
    @FXML
    private TextField FXProduit_id;
    @FXML
    private TextField FXProduit_state;
    @FXML
    private TextField FXProduit_action;
    @FXML
    private TextField FXProduit_libellet;
    @FXML
    private TextField FXProduit_prix;
    @FXML
    private TextField FXProduit_prod;
    @FXML
    private TextField FXProduit_stock;
    @FXML
    private TextField FXProduit_desc;
    @FXML
    private TextField FFXProduit_pollution;
    @FXML
    private TextField FXProduit_imglink;
    @FXML
    private TableView<Produit> FXProduit_table;
    @FXML
    private TableColumn<Produit,Integer> FXProduit_colid;
    @FXML
    private TableColumn<Produit,String> FXProduit_colimg;
    @FXML
    private TableColumn<Produit,String> FXProduit_collib;
    @FXML
    private TableColumn<Produit,Float> FXProduit_colprix;
    @FXML
    private TableColumn<Produit,Integer> FXProduit_colstock;
    @FXML
    private TableColumn<Produit,Integer> FXProduit_colprod;
    @FXML
    private TableColumn<Produit,String> FXProduit_colcat;
    @FXML
    private TableColumn<Produit,Integer> FXProduit_colener;
    @FXML
    private TableColumn<Produit,String> FXProduit_colcons;
    @FXML
    private TableColumn<Produit,String> FXProduit_colvéhicule;
    @FXML
    private TableColumn<Produit,Integer> FXProduit_coldist;
    @FXML
    private TableColumn<Produit,Float> FXProduit_colpol;
    @FXML
    private ComboBox<Catégorie> FXProduit_comcat;
    @FXML
    private ComboBox<Energie> FXProduit_comener;
    @FXML
    private ComboBox<Véhicule> FXProduit_comvéhicule;
    @FXML
    private ComboBox<String> FXProduit_commark;
    @FXML
    private ImageView FXProduit_imgview;
    @FXML
    private TextField FXProduit_inti;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DataSource.getInstance();
        ServiceCatégorie servicecat = new ServiceCatégorie();
        ServiceEnergie serviceener = new ServiceEnergie();
        Servicevéhicule servicevehi = new Servicevéhicule();
        ServiceProduit serviceprod = new ServiceProduit();
        Catégorie cat = new Catégorie();
        Energie ener = new Energie();
        Véhicule veh = new Véhicule();
        FXProduit_comcat.getItems().addAll(servicecat.getAll(cat));
        FXProduit_comener.getItems().addAll(serviceener.getAll(ener));
        FXProduit_inti.setText("0");
        
        
        ArrayList<String> listlark = new ArrayList();
        for(Véhicule v : servicevehi.getAll(veh)){
            if(!listlark.contains(v.getMarque()))
            listlark.add(v.getMarque());
        }
        FXProduit_commark.getItems().addAll(listlark);
        
        
        FXProduit_commark.valueProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable ,String oldvalue ,String newvalue){
                    List<Véhicule> listv = new ArrayList<>();
                    for(Véhicule v : servicevehi.getAll(veh)){
                        if(v.getMarque().equals(newvalue))
                            listv.add(v);
                            
                    }
                    FXProduit_comvéhicule.getItems().clear();
                    FXProduit_comvéhicule.getItems().addAll(listv);
                }
            });
        TextField textField[]=new TextField[15];
        FXProduit_addener.setOnAction(e ->{
            int i =Integer.parseInt(FXProduit_inti.getText());
                textField[i]=new TextField();
                anchor.add(textField[i], 0, i);
                FXProduit_inti.setText(String.valueOf(i+1));
                //String test=FXProduit_comener.getValue().getLibellet();
                //TextField test = new TextField();
                /*anchor.add(textField,0,0,1,1);
                textField.setVisible(true);
                textField.setText("here");*/
                
            
        });
    }    
    
}
