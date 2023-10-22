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
import tn.edu.forGreenerIndustry.entities.Commentaires;
import tn.edu.forGreenerIndustry.entities.Post;
import tn.edu.forGreenerIndustry.services.ServiceCommentaires;
import tn.edu.forGreenerIndustry.services.ServicePost;
import tn.edu.forGreenerIndustry.tools.DataSource;

public class ForGreenerIndustry {
    public static void main(String[] args) {
        // Initialize the data source
        DataSource.getInstance();

        // Create an instance of the Post service
        ServicePost service = new ServicePost();
        ServiceCommentaires commentairesService = new ServiceCommentaires();

        try {
            
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date utilDate = df.parse("12/11/2012");
            Date sqlDate = new Date(utilDate.getTime());

            // Create a new Post object
            Post p1 = new Post(1, 2, "Welcome", "Event", "We have a new event", sqlDate, "url_photo.jpg");
            Post p2 = new Post(2, 5, "Radiant Youth Serum", "product", "premium skincare product developed by our trusted beauty brand", sqlDate, "url_photo.jpg");
            Post p3 = new Post(3, 3, "Special Event", "Event", "Exciting event happening next week!", sqlDate, "event_image.jpg");

            Commentaires c1 = new Commentaires(1, 1, 3, "This is a great post!", "Approved");
            Commentaires c2 = new Commentaires(2, 2, 4, "Nice work on this project!", "Approved");
            Commentaires c3 = new Commentaires(3, 6, 1, "I have a question about this post.", "Pending");



            //service.ajouter(p1);
            //service.ajouter(p2);
            // service.ajouter(p3);
            
            //commentairesService.ajouter(c1);
            //commentairesService.ajouter(c2);
            //commentairesService.ajouter(c3);
            
            //////////////////////modif///////////////////////
            
            //int postToModifyId = 1; // Replace with the ID of the post you want to modify
            //Post postToModify = service.getOne(postToModifyId);

            //if (postToModify != null) {
                // Modify the attributes of the Post
               // postToModify.setTitre("Updated Title");
               // postToModify.setContenu("Updated Content");

                // Call the modifier method to save the changes
                //service.modifier(postToModify);

                //System.out.println("Post has been modified: " + postToModify);
            //} else {
              //  System.out.println("Post not found.");
           // }
            
            ///////////////////supprim///////////////////////
                
            //int postToDeleteId = 10; 
            //service.supprimer(postToDeleteId);

            //////////////////get one/////////////////////////
            
            
            int postToRetrieveId = 1; // Replace with the ID of the post you want to retrieve
            Post retrievedPost = service.getOne(postToRetrieveId);

            if (retrievedPost != null) {
                System.out.println("Retrieved Post: " + retrievedPost);
            } else {
                System.out.println("Post not found.");
            }
            
            //////////////////////get all///////////////////////
            
            List<Post> allPosts = service.getAll(null);

            if (!allPosts.isEmpty()) {
                for (Post post : allPosts) {
                    System.out.println("Post: " + post);
                }
            } else {
                System.out.println("No posts found.");
            }
            
            //////////////////////////////////////////
            List<Commentaires> commentsList = commentairesService.getAll(c1);
            for (Commentaires comment : commentsList) {
                System.out.println(comment);
            }
            

        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

