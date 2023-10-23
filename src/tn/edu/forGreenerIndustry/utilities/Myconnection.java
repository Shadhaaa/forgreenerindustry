/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forgreenerindustry.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ACHREF
 */
public class Myconnection {
     String url= "jdbc:mysql://localhost:3306/for_greener_industry";
    String login = "root";
    String password= "";
    Connection cnx ;
    public static Myconnection instance ;
            
    public Myconnection(){ 
        try {
           cnx= DriverManager.getConnection(url, login, password);
           System.out.println("connexion etablie!");
        } catch (SQLException ex) {
           System.err.println(ex.getMessage());
        }
        
}
    public Connection getCnx(){
        return cnx;
    }
    public static Myconnection getInstance(){
        if(instance == null){
            instance = new Myconnection();
        }
        return instance ;
    }
     public static class getInstance extends Myconnection {

        public getInstance() {
        }
     }
}     
    

