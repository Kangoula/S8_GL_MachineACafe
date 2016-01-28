package machine.boisson;
/**
 * Représente la recette d'une boisson 
 * @author Guillaume Denis
 *
 */
public abstract class Recette {
	
	private int qteCafe;
	private int qteLait;
	private int qteSucre;
	private int qteChocolat;
	
	//CONSTRUCTOR
	protected Recette(int qteCafe, int qteLait, int qteSucre, int qteChocolat) {
		super();
		this.qteCafe = qteCafe;
		this.qteLait = qteLait;
		this.qteSucre = qteSucre;
		this.qteChocolat = qteChocolat;
	}
	
	
	// GETTERS and SETTERS
	protected int getQteCafe() {
		return qteCafe;
	}
	
	protected void setQteCafe(int qteCafe) {
		this.qteCafe = qteCafe;
	}
	
	protected int getQteLait() {
		return qteLait;
	}
	
	protected void setQteLait(int qteLait) {
		this.qteLait = qteLait;
	}
	
	protected int getQteSucre() {
		return qteSucre;
	}
	
	protected void setQteSucre(int qteSucre) {
		this.qteSucre = qteSucre;
	}
	
	protected int getQteChocolat() {
		return qteChocolat;
	}
	
	protected void setQteChocolat(int qteChocolat) {
		this.qteChocolat = qteChocolat;
	}
	
}
