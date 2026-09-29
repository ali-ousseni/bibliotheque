/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bibliotheque;
import java.sql.*;
import java.util.Optional;

/**
 *
 * @author balioussen
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        AccesBdD db = new AccesBdD();
        
        /*//Test de la modification des informations d'un livre
        if(db.getConnection() != null){
            System.out.println("Connexion réussie");
            System.out.println("Liste des livres");
            db.modifLivre(17, "Book", "S.Belington", "NewBook", 2026, 2);
        }*/
        
        /*//Test de la modification du titre d'un livre
        if(db.getConnection() != null){
            System.out.println("Connexion réussie");
            System.out.println("Liste des livres");
            db.modifTitre(17, "World");
        }*/
        
        /*//Test de la suppression d'un livre
        if(db.getConnection() != null){
            System.out.println("Connexion réussie");
            System.out.println("Liste des livres");
            db.supprimLivre(29);
        }*/
        
        /*//Test de l'ajout d'un livre
        if(db.getConnection() != null){
            System.out.println("Connexion réussie");
            System.out.println("Liste des livres");
            db.ajoutLivre("Sauvage", " Jamey Bradbury", "Gallmeister", 2019 , 2);
        }*/
         
        /*//Test de l'affichage des livres par la catégorie associé
        if(db.getConnection() != null){
            System.out.println("Connexion réussie");
            System.out.println("Liste des livres");
            ResultSet result = db.getLesLivresUneCategorie("Essai");
            while(result.next()){
                String titre = result.getString(1);
                String categorie = result.getString(2);
                
                System.out.println(titre + " - " +  categorie);
            }
        }*/
        
        /*//Test de l'affichage des livres par l'éditeur associé
        if(db.getConnection() != null){
            System.out.println("Connexion réussie !");
            System.out.println("Liste des livres");
            ResultSet result = db.getLesLivresUnEditeur("Gallimard");
            if(result.next()){                
                while(result.next()){
                    String titre = result.getString(2);
                    String editeur = result.getString(4);

                    System.out.println(titre + " - " +  editeur);
                }
            }
            else {
                System.out.println("Aucun livre trouvé pour cet éditeur.");
            }
        }*/
        
        //Test de la connexion à la base de données et de la récupération des enregistrements
        if(db.getConnection() != null){
            System.out.println("Connexion réussie");
            System.out.println("Liste des livres");
            ResultSet result = db.getLesLivres();
            while(result.next()){
                String titre = result.getString(2);
                String auteur = result.getString(3);
                String editeur = result.getString(4);
                int annee = result.getInt(5);
                
                System.out.println(titre + " - " + auteur + " - " + editeur + " - " + annee );
            }
        }
        
    }
    
}
