import java.util.ArrayList;

public class Directeur extends Personne {
	float solde;
	String[] listProduit;
	protected String password;

	public Directeur(String nom, String prenom, int jourNaissance, int moisNaissance, int anneeNaissance,
			ArrayList<String> listProduit, String password) {
		super(nom, prenom, jourNaissance, moisNaissance, anneeNaissance, listProduit);
		this.password = password;
	}

	public float getSolde() {
		return solde;
	}

	public void setSolde(float solde) {
		this.solde = solde;
	}

	public void ajoutSolde(float amount) {
		solde = solde + amount;
	}

	public void retireSolde(float amount) {
		solde = solde - amount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
