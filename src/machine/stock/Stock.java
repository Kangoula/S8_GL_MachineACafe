package machine.stock;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import machine.database.Connexion;

/**
 * Représente le stock des ingrédients de la machine
 * @author Guillaume Denis
 *
 */
public class Stock {
	
	/**
	 * Indique le type de stock (café, lait, ...)
	 */
	private String type;
	private int quantite;
	
	public Stock(String type, int quantite) {
		this.type = type.toLowerCase();
		this.quantite = quantite;
	}

	/**
	 * Augmente la quantité en stock d'un montant donné
	 * @param qte la quantité à augmenter
	 */
	public void augmenterStock(int qte){
		if(qte >= 0){
			this.quantite += qte;
		}
	}
	
	//GETTERS & SETTERS
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
        
        public void insertInDB() {
            String sql = "INSERT INTO stocks VALUES (?,?)";
            PreparedStatement ps;
            try {
                ps = Connexion.getConnection().prepareStatement(sql);
                ps.setString(1,this.type);
                ps.setInt(2,this.quantite);
            } catch (SQLException ex) {
                Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
	
}
