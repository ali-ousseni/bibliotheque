/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bibliotheque;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author balioussen
 */
public class AccesBdD {
    //private final String url;
    //private final String username;
    //private final String password;
    private Connection connexion;
    
    public AccesBdD(){
        Properties config = new Properties();
        try(FileInputStream fichier = new FileInputStream("config.properties")){
            //Lecture de la configuration
            config.load(fichier);
            
            String url = config.getProperty("db.url");
            String username = config.getProperty("db.username");
            String password = config.getProperty("db.password");
            
            //Ouverture de la connexion
            connexion = DriverManager.getConnection(url, username, password);
            
            System.out.println("Connexion réussie !");
        } catch (IOException e) {
            System.out.println("Erreur de lecture du fichier de congiguration" + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e.getMessage());
        }
    }    
    
    public Connection getConnection(){
        return this.connexion;
    }
    
    public ResultSet getLesLivres(){
        ResultSet result = null;
        try {
            String sql = "SELECT * FROM livre";
            Statement statement;
            
            statement = connexion.createStatement();
            result = statement.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AccesBdD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public ResultSet getLesCategories(){
        ResultSet result = null;
        try {
            
            String sql = "SELECT id, libelle FROM categorie";
            Statement statement;
            statement = connexion.createStatement();
            result = statement.executeQuery(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(AccesBdD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public ResultSet getLesLivresUnEditeur(String editeur){
        ResultSet result = null;
        try {
            String sql = "SELECT * FROM livre where editeur = ?";
            PreparedStatement statement;
            statement = connexion.prepareStatement(sql);
            statement.setString(1, editeur);
            result = statement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(AccesBdD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public ResultSet getLesLivresUneCategorie(String libelle){
        ResultSet result = null;
        try {
            String sql = "SELECT titre, libelle FROM livre l INNER JOIN categorie c ON l.categorie = c.id WHERE libelle = ?";
            PreparedStatement statement;
            statement = connexion.prepareStatement(sql);
            statement.setString(1, libelle);
            result = statement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(AccesBdD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int ajoutLivre(String titre, String auteur, String editeur, int annee, int categorie){
        int rowsInserted = 0;
        try {
            String sql = "INSERT INTO livre (titre, auteur, editeur, annee, categorie) VALUES (?,?,?,?,?)";        
            
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setString(1, titre);
            statement.setString(2, auteur);
            statement.setString(3, editeur);
            statement.setInt(4, annee);
            statement.setInt(5, categorie);
            rowsInserted = statement.executeUpdate();            
        } catch (SQLException ex) {
            Logger.getLogger(AccesBdD.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return rowsInserted;
    }
    
    public int supprimLivre(int id){
        int rowsInserted = 0;
        try {
            String sql = "DELETE FROM livre WHERE id = ?";        
            
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setInt(1, id);
            rowsInserted = statement.executeUpdate();   
            
        } catch (SQLException ex) {
            Logger.getLogger(AccesBdD.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return rowsInserted;
    }
    
    public int modifTitre(int id, String titre){
        int rowsInserted = 0;
        try {
            String sql = "UPDATE livre SET titre = ? WHERE id = ?";        
            
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setInt(2, id);
            statement.setString(1, titre);
            rowsInserted = statement.executeUpdate();   
            
        } catch (SQLException ex) {
            Logger.getLogger(AccesBdD.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return rowsInserted;
    }
    
    public int modifLivre(int id, String titre, String auteur, String editeur, int annee, int categorie){
        int rowsInserted = 0;
        try {
            String sql = "UPDATE livre SET titre = ?, auteur = ?, editeur = ?, annee = ?, categorie = ? WHERE id = ?";        
            
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setString(1, titre);
            statement.setString(2, auteur);
            statement.setString(3, editeur);
            statement.setInt(4, annee);
            statement.setInt(5, categorie);
            statement.setInt(6, id);
            rowsInserted = statement.executeUpdate();   
            
        } catch (SQLException ex) {
            Logger.getLogger(AccesBdD.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return rowsInserted;
    }
    
    
}
