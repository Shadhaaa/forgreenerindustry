package tn.edu.forgreenerindustry.gui;
  
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import tn.edu.forGreenerIndustry.services.ServiceEntreprise;
import tn.edu.forgreenerindustry.entities.Entreprise;
import tn.edu.forgreenerindustry.entities.User;
import tn.edu.forgreenerindustry.services.ServiceUser;

public class AcceuiladminController implements Initializable {

    @FXML
    private BarChart<String, Number> barChart;


    @FXML
    private Label labelHommes;

    @FXML
    private Label labelFemmes;
    @FXML
    private Button stat;
  int hommes = 0;
        int femmes = 0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void stat(ActionEvent event) {
         List<String> listeDesGenres = new ArrayList<>();
        ServiceUser serv = new ServiceUser();
        ServiceEntreprise en = new ServiceEntreprise();
        Entreprise entr = new Entreprise();
          List<Entreprise> en1 = en.getAll(entr) ;


        // Récupérer les genres (sexe) des utilisateurs
        List<User> ss = serv.getAll(new User()) ;
        for (User u : ss ) {
            String genre = u.getGenre();
        
                listeDesGenres.add(genre);
        }
        // Récupérer les genres (sexe) des entreprises
        for (Entreprise e : en1) {
            String genre = e.getGenre();

                listeDesGenres.add(genre);
            
        }
                    System.out.println(listeDesGenres);

        // Récupérez les genres et calculez les statistiques
      

        for (String genre : listeDesGenres) {
            System.out.println( genre );

            if (genre == "Homme" || genre == "HOMME") {
                hommes = hommes +1;
                            System.out.println( " je sui le nombre"  + femmes );

                
            } else  {
                femmes++;
            }
        }
                       


        // Créez une série de données pour le graphique à barres
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Genres (Hommes/Femmes)");

        series.getData().add(new XYChart.Data<>("Hommes", 15));
        series.getData().add(new XYChart.Data<>("Femmes", femmes));

        // Ajoutez la série de données au graphique à barres
        barChart.getData().add(series);

        // Mettez à jour les libellés avec le nombre d'hommes et de femmes
        labelHommes.setText("Hommes : " + 15);
        labelFemmes.setText("Femmes : " + femmes);
    }

    // La méthode getGenres() récupère les genres (sexe) des utilisateurs et des entreprises
   
      
    
   
  
}
   
    