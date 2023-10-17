/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forgreenerindustry;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import tn.edu.forgreenerindustry.entities.Evenement;
import tn.edu.forgreenerindustry.entities.Investissement;
import tn.edu.forgreenerindustry.services.ServiceEvenement;
import tn.edu.forgreenerindustry.services.ServiceInvestissement;
import tn.edu.forgreenerindustry.tools.DataSource;

public class ForGreenerIndustry {
    public static void main(String[] args) {
        // Initialize the data source
        DataSource.getInstance();

        // Create an instance of the Post service
        ServiceEvenement service = new ServiceEvenement();
        ServiceInvestissement serviceInv = new ServiceInvestissement();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        //*********************** Ajouter Evenement ************************
        /*java.util.Date utilDate = df.parse("12/11/2012 15:30");
        Date sqlDate = new Date(utilDate.getTime());
        Evenement e1 = new Evenement(1, 2, "Welcome", sqlDate, "Event", "We have a new event","url_photo.jpg","url_photo.jpg","url_photo.jpg");
        service.ajouter(e1);
        */
        //*********************** Modifier Evenement ***********************
        /*
        java.util.Date utilDate = df.parse("17/07/2021 10:30");
        Date sqlDate = new Date(utilDate.getTime());
        int idEvenementAModifier =13;
        Evenement evenementAModifier = service.getOne(idEvenementAModifier);
        if (evenementAModifier != null) {
        evenementAModifier.setDate_evenement(sqlDate); // Remplacez "nouvelleDate" par la nouvelle date souhaitée.
        service.modifier(evenementAModifier);
        }
        
        
        /*
        //*********************** Supprimer Evenement ***********************
        int idEvenementASupprimer = 7;
        // Appelez la méthode supprimer pour supprimer l'événement.
        service.supprimer(idEvenementASupprimer);
        
        
        //*********************** getOne Evenement *************************
        // Obtenez un événement en utilisant son ID (remplacez 1 par l'ID de l'événement que vous souhaitez récupérer).
        int idEvenementARecuperer = 1;
        Evenement evenementRecupere = service.getOne(idEvenementARecuperer);
        if (evenementRecupere != null) {
        System.out.println("Événement récupéré : " + evenementRecupere);
        }
        
        //*********************** getAll Evenement *************************
        List<Evenement> listeEvenements = service.getAll(null);
        if (listeEvenements.isEmpty()) {
        System.out.println("Aucun événement trouvé.");
        } else {
        System.out.println("Liste des événements :");
        for (Evenement evenement : listeEvenements) {
        System.out.println(evenement); // Assurez-vous que la classe Evenement a une méthode toString appropriée pour l'affichage.
        }
        }
        
        
        //*********************** Ajouter Investissement ************************
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        java.util.Date utilDate = df.parse("15/09/2023 10:30");
        Date sqlDate = new Date(utilDate.getTime());
        // Créez un objet Investissement avec les informations nécessaires.
        Investissement investissement = new Investissement(4,44,4000.d,sqlDate,"30mois","details inv4",0);
        // Appelez la méthode ajouter pour insérer l'investissement dans la base de données.
        serviceInv.ajouter(investissement);
        
        */
        //*********************** Modifier Investissement ***********************
        /*java.util.Date utilDate = df.parse("11/05/2020 09:30");
        Date sqlDate = new Date(utilDate.getTime());
        int idInvestissementAModifier =10;
        Investissement investissementAModifier = serviceInv.getOne(idInvestissementAModifier);
        if (investissementAModifier != null) {
        investissementAModifier.setDate_debut_investissement(sqlDate); // Remplacez "nouvelleDate" par la nouvelle date souhaitée.
        serviceInv.modifier(investissementAModifier);
        }
        
        */
        //*********************** Supprimer Investissement ***********************
        /*int idInvestissementASupprimer = 10;
        serviceInv.supprimer(idInvestissementASupprimer);
        
        
        */
        
        
        //*********************** getOne Evenement *************************
       /* // Obtenez un événement en utilisant son ID (remplacez 1 par l'ID de l'événement que vous souhaitez récupérer).
        int idInvestissementARecuperer = 10;
        Investissement investissementRecupere = serviceInv.getOne(idInvestissementARecuperer);
        if (investissementRecupere != null) {
        System.out.println("Investissement récupéré : " + investissementRecupere);
        }
        
        */
        //*********************** getAll Evenement *************************
       /* List<Investissement> listeInvestissements = serviceInv.getAll(null);
        if (listeInvestissements.isEmpty()) {
        System.out.println("Aucun investissement trouvé.");
        } else {
        System.out.println("Liste des investissement :");
        for (Investissement investissement : listeInvestissements) {
        System.out.println(investissement); // Assurez-vous que la classe Evenement a une méthode toString appropriée pour l'affichage.
        }
        }
        
     */
    }

}
