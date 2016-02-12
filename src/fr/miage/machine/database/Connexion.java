package fr.miage.machine.database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe connexion
 * @author Antoine NOSAL
 * @author Guillaume DENIS
 */
public class Connexion {
    
    private Connection c;
    
    /**
     * Construit une connexion à la base de données.
     */
    public Connexion() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.c = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (Exception e) {
            // Problème
        }
    }
    
    /**
     * Retroune la connexion à la base de données si elle existe,
     * sinon elle en crée une puis la retourne
     * @return 
     */
    public Connection getConnection() {
        if(c==null) {
            new Connexion();
        }
        return this.c;
    }
    
}
