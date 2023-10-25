/*

* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forgreenerindustry;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import tn.edu.forGreenerIndustry.services.ServiceEntreprise;
import tn.edu.forgreenerindustry.entities.Commande;
import tn.edu.forgreenerindustry.entities.Reclamation;
import tn.edu.forgreenerindustry.tools.DataSource;
import tn.edu.forgreenerindustry.entities.User;
import tn.edu.forgreenerindustry.entities.Entreprise;

import tn.edu.forgreenerindustry.services.IService;
import tn.edu.forgreenerindustry.services.ServiceUser;
/**
 *
 * @author shadha
 */

public class ForGreenerIndustry { 
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DataSource.getInstance();
         ServiceUser service = new ServiceUser();
    
         
        /* Entreprise entreprise = new Entreprise(
    2, // Remplacez par l'ID de l'entreprise
    "shad ",
    "Prénom",
    "URL de la photo de profil",
    12345, // Remplacez par le numéro de téléphone de l'entreprise
    "email@entreprise.com",
    "Mot",
    "AGENT_ENTREPRISE", // Remplacez par le rôle de l'entreprise
    "Adres",
    "FEMME",
    "URL du logo de entr",
    "Nom d",
    "Secteur",
    "Description"
);

ServiceEntreprise service1 = new ServiceEntreprise(); // Remplacez par le nom correct de votre service d'entreprise
service1.modifier(entreprise);*/

// User instance
User c1 = new User( "nanana89898na",  "nanana89898na",  "na98na",
    12348888,    "jahahahahe@example.com",
    "jahahah",
    "CLIENT",

        
    "jahahahxample.com",
        "aaaamail.jpg"
   
);
service.ajouter(c1);
int a = c1.getId_user();
String r = c1.getRole();
        System.out.println("forgreenerindustry.ForGreenerIndustry.main()" + a + r );
         //service.modifier(c1);
         //getone 
         
         User user = service.getOne(c1);
/*if (user != null) {
    System.out.println(user); // Assuming User class has a proper toString() implementation
} else {
    System.out.println("User not found.");
}
*/
//get all 

  /*  User user1 = new User();

    List<User> userList = service.getAll(user1);
        System.out.println("");

    userList.forEach((u) -> {
        System.out.println(u);
       });
}*/
            
        }}



        
    
    
