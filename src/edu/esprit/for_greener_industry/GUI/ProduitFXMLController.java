/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.for_greener_industry.GUI;


import com.sun.scenario.effect.ImageData;
import static edu.esprit.for_greener_industry.GUI.Mod_or_addProdFXMLController.Mod_or_addProdFXMLController;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
    private Button FXProduit_ajouter;
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
    private TableColumn<Catégorie,String> FXProduit_colcat;
    @FXML
    private TableColumn<Energie,ArrayList<Energie>> FXProduit_colener;
    @FXML
    private TableColumn<Produit,ArrayList<Float>> FXProduit_colcons;
    @FXML
    private TableColumn<Véhicule,ArrayList<Véhicule>> FXProduit_colvéhicule;
    @FXML
    private TableColumn<Produit,ArrayList<Float>> FXProduit_coldist;
    @FXML
    private TableColumn<Produit,Float> FXProduit_colpol;
    @FXML
    private TableColumn<Produit,Integer> FXProduit_colent;
    @FXML
    private TableColumn<Produit,Void> FXProduit_coldel;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // TODO
        DataSource.getInstance();
        ServiceCatégorie servicecat = new ServiceCatégorie();
        ServiceEnergie serviceener = new ServiceEnergie();
        Servicevéhicule servicevehi = new Servicevéhicule();
        ServiceProduit serviceprod = new ServiceProduit();
        Produit p = new Produit(); 
        Catégorie cat = new Catégorie();
        Energie ener = new Energie();
        Véhicule veh = new Véhicule();
        ObservableList<Produit> observableList = FXCollections.observableList(serviceprod.getAll(p));
        
        FXProduit_coldel.setCellFactory(param -> new ProduitFXMLController.TableCellWithDelButtonprod(observableList));
        FXProduit_coldel.setMinWidth(60);
        
        FXProduit_colent.setCellValueFactory(cell -> cell.getValue().getIdProperty().asObject());;
        FXProduit_colid.setCellValueFactory(cell -> cell.getValue().getIdProperty().asObject());
        
        //FXProduit_colimg.setCellValueFactory(new PropertyValueFactory<>("image"));
        
        //FXProduit_colimg.setCellValueFactory(data -> data.getValue().getImgProperty());
        //FXProduit_colimg.setCellFactory(column -> new TableCellWithImage());
        
        FXProduit_collib.setCellValueFactory(cell -> cell.getValue().getLibelletProperty());
        FXProduit_colprix.setCellValueFactory(cell -> cell.getValue().getPrixProperty().asObject());
        FXProduit_colstock.setCellValueFactory(cell -> cell.getValue().getStock_actuelleProperty().asObject());
        FXProduit_colprod.setCellValueFactory(cell -> cell.getValue().getProduction_mentuelleProperty().asObject());
        
        //FXProduit_colcat.setCellValueFactory(cell -> cell.getValue().getLibelletProperty());
        
        ArrayList<Energie> arrener = new ArrayList<>();
        arrener.addAll(serviceener.getAll(ener));
     
        FXProduit_colener.setCellValueFactory(new PropertyValueFactory<>("lib"));
        FXProduit_colener.setCellFactory(column -> new TableCellWithListEner());
        
        
        FXProduit_colvéhicule.setCellValueFactory(new PropertyValueFactory<>("en"));
        FXProduit_colvéhicule.setCellFactory(column -> new TableCellWithListvéhi());
        
        FXProduit_colpol.setCellValueFactory(cell -> cell.getValue().getPollution_par_piéceProperty().asObject());
        
        FXProduit_colcons.setCellValueFactory(new PropertyValueFactory<>("floatList"));
        FXProduit_colcons.setCellFactory(column -> new FloatListCell());
        
        FXProduit_coldist.setCellValueFactory(new PropertyValueFactory<>("floatList"));
        FXProduit_coldist.setCellFactory(column -> new FloatListCell());
        
        FXProduit_table.setItems(observableList);
        
        FXProduit_ajouter.setOnAction(event ->{
            Stage primaryStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Mod_or_addProdFXMLController(p,primaryStage1,observableList);
        });
        
        
    }    
    
 public class TableCellWithImage extends TableCell<Produit, String> {
    private final ImageView imageView = new ImageView();

    @Override
    protected void updateItem(String imagePath, boolean empty) {
        super.updateItem(imagePath, empty);
        if (empty || imagePath == null) {
            setGraphic(null);
            
        } else {
            // Create an Image object from the file path
            System.out.println(imagePath);
            Image image = new Image("file:" + imagePath); 
            
            imageView.setImage(image);
            imageView.setFitWidth(50); // Set the width of the displayed image
            imageView.setPreserveRatio(true);
            setGraphic(imageView);
            
            }
        }
    }
    public static class TableCellWithListEner extends TableCell<Energie,ArrayList<Energie>> {
        @Override
        protected void updateItem(ArrayList<Energie> lib, boolean empty) {
            super.updateItem(lib, empty);
            if (empty || lib == null || lib.isEmpty()) {
                setText("");
            } else {
                for(Energie e : lib){
                    setText(String.join(", ", e.getLibellet()));
                }
                
            }
        }
    }
    public static class TableCellWithListvéhi extends TableCell<Véhicule,ArrayList<Véhicule>> {
        @Override
        protected void updateItem(ArrayList<Véhicule> en, boolean empty) {
            super.updateItem(en, empty);
            if (empty || en == null || en.isEmpty()) {
                setText("");
            } else {
                for(Véhicule e : en){
                    setText(String.join(", ", e.getLibellet()));
                }
                
            }
        }
    }
 public static class FloatListCell extends javafx.scene.control.cell.TextFieldTableCell<Produit, ArrayList<Float>> {
        @Override
        public void updateItem(ArrayList<Float> floatList, boolean empty) {
            super.updateItem(floatList, empty);
            if (empty || floatList == null || floatList.isEmpty()) {
                setText("");
            } else {
                // Convert the list of floats to a comma-separated string
                String floatListString = floatList.stream()
                        .map(Object::toString)
                        .reduce((acc, next) -> acc + ", " + next)
                        .orElse("");
                setText(floatListString);
            }
        }
    }
 
      private static class TableCellWithDelButtonprod extends TableCell<Produit, Void> {
        private final Button button;

        public TableCellWithDelButtonprod(ObservableList<Produit> observableList) {
            this.button = new Button("Supprimer");
            this.button.setOnAction(event -> {
                Produit p = getTableView().getItems().get(getIndex());
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Delete Confirmation");
                alert.setContentText("Are you sure you want to delete this item?");
                alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                     DataSource.getInstance();
                     ServiceProduit Service =new ServiceProduit();
                     Service.supprimer(p.getId());
                     observableList.clear();               
                     observableList.addAll(Service.getAll(p));
                     alert1.setContentText("Produit supprimé avec success");
                     alert1.show();
                } else {
                    alert.close();
                }
            });
            
        });
                    }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(button);
            }
        }
    } 
 
}




















