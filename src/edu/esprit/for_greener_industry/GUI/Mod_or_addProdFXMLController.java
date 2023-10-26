/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.for_greener_industry.GUI;

import com.sun.javafx.scene.control.skin.ComboBoxPopupControl;
import edu.esprit.for_greener_industry.enteties.Catégorie;
import edu.esprit.for_greener_industry.services.ServiceCatégorie;
import edu.esprit.for_greener_industry.enteties.Energie;
import edu.esprit.for_greener_industry.services.ServiceEnergie;
import edu.esprit.for_greener_industry.enteties.Véhicule;
import edu.esprit.for_greener_industry.services.Servicevéhicule;
import edu.esprit.for_greener_industry.enteties.Produit;
import edu.esprit.for_greener_industry.services.ServiceProduit;
import edu.esprit.for_greener_industry.enteties.Energie;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.List;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import edu.esprit.for_greener_industry.tools.DataSource;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import static jdk.nashorn.internal.objects.NativeString.substring;



/**
 * FXML Controller class
 *
 * @author ritha
 */
public class Mod_or_addProdFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public static void Mod_or_addProdFXMLController(Produit p, Stage primaryStage,ObservableList<Produit> tab) {
              UnaryOperator<TextFormatter.Change> filterint = change -> {
            String newText = change.getControlNewText();
            if (newText.isEmpty() || Pattern.matches("[1-9]\\d*", newText)) {
                return change;
            }
            return null;
        };
        
        TextFormatter<String> textFormatterint = new TextFormatter<>(filterint);
        
        
        
            UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (newText.isEmpty() || Pattern.matches("\\d*\\.?\\d*", newText)) {
                return change;
            }
            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        
            Map<String, TextField> textFieldMap = new HashMap<>();
            Map<Energie, Float> energieMap = new HashMap<>();
            Map<Véhicule, Float> véhiculeMap = new HashMap<>();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
            Stage newStage = new Stage();
            DataSource.getInstance();
            
            ServiceProduit serviceprod = new ServiceProduit();
            Servicevéhicule servicevéhi = new Servicevéhicule();
            ServiceEnergie serviceener = new ServiceEnergie();
            ServiceCatégorie servicecat = new ServiceCatégorie();
            
            Produit p1 = new Produit();
            Catégorie c = new Catégorie();
            Energie e = new Energie();
            Véhicule v = new Véhicule();
            
            newStage.setTitle("Ajouter Produit");
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.initOwner(primaryStage);
            
            TextField labelfield = new TextField();
            TextField stockfield = new TextField();
            stockfield.setTextFormatter(textFormatterint);
            TextField productionfield = new TextField();
          //  productionfield.setTextFormatter(textFormatterint);
            TextField Descriptionfield = new TextField();
            TextField prixfield = new TextField();
            prixfield.setTextFormatter(textFormatter);
            TextField pollutionfield = new TextField();
            pollutionfield.setDisable(true);
            TextField imgfield = new TextField();
            
            FileChooser chooseimg = new FileChooser();
            
            Label labellabel = new Label();
            labellabel.setText("Le libellet du produit :");
            Label prixlabel = new Label();
            prixlabel.setText("Le Prix du produit :");
            Label stocklabel = new Label();
            stocklabel.setText("le stock actuelle :");
            Label productionlabel = new Label();
            productionlabel.setText("La production mentuelle :");
            Label descriptionlabel = new Label();
            descriptionlabel.setText("Une description de votre produit");
            Label pollitionlabel = new Label();
            pollitionlabel.setText("La production en co2/piéce de votre produit :");
            Label imagesellabel = new Label();
            imagesellabel.setText("Choisir l'image de votre produit :");
            Label comcatlabel = new Label();
            comcatlabel.setText("choisir la catégorie de votre produit :");
            Label comenerlabel = new Label();
            comenerlabel.setText("Energies :");
            Label commarklabel = new Label();
            commarklabel.setText("Choisir marque :");
            Label comvéhilabel = new Label();
            comvéhilabel.setText("Ajouter véhicule :");
            
            ComboBox<Catégorie> catcombo = new ComboBox();
            ComboBox<Energie> enercombo = new ComboBox();
            ComboBox<Véhicule> véhicombo = new ComboBox();        
            ComboBox<String> markcombo = new ComboBox();
            
            ImageView prodimage = new ImageView();
            
            Button mod = new Button("Valider");
            Button annuler = new Button("Annuler");
            Button addener = new Button("+");
            Button addvéhi = new Button("+");
            Button chooseFile = new Button("Choisir fichier");
            
            HBox imgvbox = new HBox();
            imgvbox.getChildren().addAll(chooseFile,prodimage);
            
            VBox vbox_labels = new VBox(18);          
            vbox_labels.getChildren().addAll(labellabel,prixlabel,stocklabel,productionlabel,descriptionlabel,pollitionlabel,imagesellabel,comcatlabel,commarklabel,comvéhilabel,comenerlabel);
            
            VBox enerval = new VBox();
            enerval.getChildren().addAll(enercombo);
            
            HBox enerbox = new HBox();
            enerbox.getChildren().addAll(enerval,addener);
            
            VBox véhival = new VBox();
            véhival.getChildren().addAll(véhicombo);
            
            HBox véhibox = new HBox();
            véhibox.getChildren().addAll(véhival,addvéhi);
            
            chooseFile.setOnAction(event -> {
                Stage primaryStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                File selectedFile = chooseimg.showOpenDialog(primaryStage1);
                if (selectedFile != null) {
                    imgfield.setText(selectedFile.getAbsolutePath());
                    System.out.println(imgfield.getText());
                    Image image = new Image(selectedFile.toURI().toString());
                    prodimage.setImage(image);
                    prodimage.setFitWidth(200);
                    prodimage.setFitHeight(200);
                }
            });
            
            VBox vbox_Fields = new VBox(8);
            vbox_Fields.getChildren().addAll(labelfield,prixfield,stockfield,productionfield,Descriptionfield,pollutionfield,imgvbox,catcombo,markcombo,véhibox,enerbox);
            
            HBox buttonboxform = new HBox();
            buttonboxform.getChildren().addAll(mod,annuler);
            
            HBox boxform = new HBox(20);
            boxform.setAlignment(Pos.BOTTOM_LEFT);
            boxform.getChildren().addAll(vbox_labels,vbox_Fields);
            
            HBox buttonbox = new HBox(20);
            buttonbox.getChildren().addAll(mod,annuler);
            
            VBox boxfin = new VBox(50);
            boxfin.getChildren().addAll(boxform,buttonbox);
            
            Scene scene = new Scene(boxfin, 900, 900);
            
            ArrayList<String> listmark = new ArrayList();
            for(Véhicule v1 : servicevéhi.getAll(v)){
                if(!listmark.contains(v1.getMarque()))
                listmark.add(v1.getMarque());
            }
            markcombo.getItems().addAll(listmark);
            
            
            markcombo.valueProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable ,String oldvalue ,String newvalue){
                    List<Véhicule> listv = new ArrayList<>();
                    for(Véhicule v1 : servicevéhi.getAll(v)){
                        if(v1.getMarque().equals(newvalue))
                            listv.add(v1);
                            
                    }
                    véhicombo.getItems().clear();
                    véhicombo.getItems().addAll(listv);
                }
            });
            
            for(Catégorie c1 : servicecat.getAll(c)){
                catcombo.getItems().add(c1);
            }
            
            ArrayList<Energie> listener = new ArrayList();
            for(Energie e1 : serviceener.getAll(e)){
                enercombo.getItems().add(e1);
                listener.add(e1);
                String textFieldName = e1.getLibellet()+"Field" ;
                TextField textField = new TextField();
                textField.setId(textFieldName);
                
                
                textFieldMap.put(textFieldName, textField);
                
            }
            ArrayList<Véhicule> lisvéhi = new ArrayList();
            for(Véhicule v1 : servicevéhi.getAll(v)){
                lisvéhi.add(v1);
                String textFieldName = v1.getLibellet()+"Field" ;
                TextField textField = new TextField();
                textField.setId(textFieldName);
                textFieldMap.put(textFieldName, textField);
            }
            
            ArrayList<TextField> textfielslist = new ArrayList();
            
            addener.setOnAction(event ->{
                if (enercombo.getValue()==null){
                    alert.setContentText("choisir un type d'énergie");
                    alert.show();
                }
                else{
                    Energie  e2 = enercombo.getValue();
                    Véhicule v2 = new Véhicule();
                    TextField text = new TextField();
                    text.setId(enercombo.getValue().getLibellet()+"Field");
                    addTextField(enercombo.getValue().getLibellet()+"Field",textFieldMap,enerval,"ener",textfielslist,energieMap,véhiculeMap,e2,v2);                 
                }                
            });
            
            
            addvéhi.setOnAction(event ->{
                if (véhicombo.getValue()==null){
                    alert.setContentText("choisir une véhicule");
                    alert.show();
                }
                else{
                    Energie  e2 = new Energie();
                    Véhicule v2 = véhicombo.getValue();
                    TextField text = new TextField();
                    text.setId(véhicombo.getValue().getLibellet()+"Field");
                    addTextField(véhicombo.getValue().getLibellet()+"Field",textFieldMap,véhival,"véhi",textfielslist,energieMap,véhiculeMap,e2,v2);                 
                }                
            });
            
            mod.setOnAction(event -> {
                if(labelfield.getText().equals("")||catcombo.getValue().getLibellet().equals("")||Descriptionfield.getText().equals("")||prixfield.getText().equals("")||productionfield.getText().equals("")||stockfield.getText().equals("")||imgfield.getText().equals("")){
                    alert.setContentText("tout champs sont obligatoires");
                    alert.show();
                }
                else{
                    p.setLibellet(labelfield.getText());
                p.setC(catcombo.getValue());
                p.setDescription(Descriptionfield.getText());
                p.setPrix(Float.parseFloat(prixfield.getText()));
                p.setProduction_mentuelle(Integer.parseInt(productionfield.getText()));
                p.setStock_actuelle(Integer.parseInt(stockfield.getText()));
                
                p.setImg(imgfield.getText().replace("\\", "*"));
                
                for(TextField t : textFieldMap.values() ){
                    for(Energie me : energieMap.keySet()){
                        System.out.println( "this  "+t.getId());
                        if(t.getId().contains(me.getLibellet()))
                        energieMap.put(me,Float.parseFloat(t.getText()));
                    }
                }
                ArrayList<Energie> arrayListener = new ArrayList<>(energieMap.keySet());
                p.setEner(arrayListener);
                ArrayList<Float> arrayListenercons = new ArrayList<>(energieMap.values());
                p.setQte_ener(arrayListenercons);
                
                 for(TextField t : textFieldMap.values() ){
                    for(Véhicule mv : véhiculeMap.keySet()){
                        if(t.getId().contains(mv.getLibellet()))
                        véhiculeMap.put(mv,Float.parseFloat(t.getText()));
                    }
                }
                ArrayList<Véhicule> arrayListvéhi = new ArrayList<>(véhiculeMap.keySet());
                p.setVéhicule(arrayListvéhi);
                ArrayList<Float> arrayListvéhicons = new ArrayList<>(energieMap.values());
                p.setDistance_véhicule(arrayListvéhicons);
                Float poll = 0f;
                for(int i=0;i < arrayListener.size();i++){
                    poll+=arrayListener.get(i).getPollution_par_kw()*arrayListenercons.get(i);
                }
                for(int i=0;i < arrayListvéhi.size();i++){
                    poll+=arrayListvéhi.get(i).getPollution_par_km()*arrayListvéhicons.get(i);
                }
                poll=poll/p.getProduction_mentuelle();
                p.setPollution_par_piéce(poll);
                serviceprod.ajouter(p);
                alert.setContentText("produit ajouter avec success");
                tab.add(p);
                alert.show();
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
                }
                
            });
            
                       
            newStage.setScene(scene);
            newStage.showAndWait();
            
            
            
            
        }
    
        public static void addTextField(String textFieldName,Map<String, TextField> textFieldMap,VBox vbox,String t,ArrayList<TextField> listfields,Map<Energie, Float> energieMap,Map<Véhicule, Float> véhiculeMap,Energie e ,Véhicule v) {
            //TextField textField = new TextField();
            //textFieldMap.put(textFieldName, textField);
            TextField textField = textFieldMap.get(textFieldName);
            Label lab = new Label();
            String newtext=new String();
            newtext=textFieldName.substring(0,textFieldName.length()-5);
            if (t.equals("ener")){
                //textFieldMap
                lab.setText("Consommation "+newtext+" par mois :");
                energieMap.put(e,Float.parseFloat("0"));
            }
            else{
                lab.setText("Distance "+newtext+" par mois :");
                véhiculeMap.put(v,Float.parseFloat("0"));
            }
                
            Button rm = new Button("-");
            HBox bo = new HBox(10);
            if(listfields.contains(textField)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("ce field existe déja");
                alert.show();
            }
            else{
                bo.getChildren().addAll(lab,textField,rm);
                listfields.add(textField);
                rm.setOnAction(event -> {
                String text = textField.getText();
                listfields.remove(textField);
                onDeleteButtonClick(event);
            });
            }
           
            
            
            vbox.getChildren().add(bo);
            
        
        }
        
        private static void onDeleteButtonClick(ActionEvent event) {
        Button deleteButton = (Button) event.getSource();
        HBox hbox = (HBox) deleteButton.getParent();

        
        if (hbox != null && hbox.getParent() instanceof VBox) {
            VBox parentContainer = (VBox) hbox.getParent();
            parentContainer.getChildren().remove(hbox);
        }
    }
        
}
    


    



