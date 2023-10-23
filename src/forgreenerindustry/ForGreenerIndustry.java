/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forgreenerindustry;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import tn.edu.forGreenerIndustry.entities.Commande;

import tn.edu.forGreenerIndustry.tools.DataSource;

/**
 *
 * @author shadha
 */
public class ForGreenerIndustry { 
   public enum Role {
    CLIENT,
    INVESTISSEUR,
    AGENT_ENTREPRISE,
    LIVREUR;
}
    public enum Genre {
    HOMME ,FEMME
}
    public enum Comp{
        AliExpress ,
        Glovo ,
        Jumia ,
        Aucune ,
        Autre ;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DataSource.getInstance();
         ServiceUser service = new ServiceUser();
         
// User instance
User c1 = new User(7,
    "shahd2",
    "nanana89898na",
    "profile5.jpg",
    12348888,
    "jahahahahe@example.com",
    "psw",
    Role.CLIENT,
    "123 Main St",
    Genre.FEMME,
    "voiture",
    Comp.Glovo,
   0
);
//service.ajouter(c1);
         //service.modifier(c1);
         //getone 
         
        /*  User user = service.getOne(c1);
if (user != null) {
    System.out.println(user); // Assuming User class has a proper toString() implementation
} else {
    System.out.println("User not found.");
}
*/
//get all 

  
    }
}
            
        



        
    
    
