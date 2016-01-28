package machine.boisson;
/**
 * Représente la recette d'une boisson 
 * @author Guillaume Denis
 *
 */
public class Recette {
	
	private int qteCafe;
	private int qteLait;
	private int qteSucre;
	private int qteChocolat;
	
	//CONSTRUCTOR
	public Recette(int qteCafe, int qteLait, int qteSucre, int qteChocolat) {
		this.qteCafe = qteCafe;
		this.qteLait = qteLait;
		this.qteSucre = qteSucre;
		this.qteChocolat = qteChocolat;
	}
	
	
	// GETTERS and SETTERS
	public int getQteCafe() {
		return qteCafe;
	}
	
	public void setQteCafe(int qteCafe) {
		this.qteCafe = qteCafe;
	}
	
	public int getQteLait() {
		return qteLait;
	}
	
	public void setQteLait(int qteLait) {
		this.qteLait = qteLait;
	}
	
	public int getQteSucre() {
		return qteSucre;
	}
	
	public void setQteSucre(int qteSucre) {
		this.qteSucre = qteSucre;
	}
	
	public int getQteChocolat() {
		return qteChocolat;
	}
	
	public void setQteChocolat(int qteChocolat) {
		this.qteChocolat = qteChocolat;
	}
	
}
