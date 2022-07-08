import java.util.ArrayList;

public class Employe extends Personne {
	float solde;
	String[] listProduit;

	public Employe(String nom, String prenom, int jourNaissance, int moisNaissance, int anneeNaissance,
			ArrayList<String> listProduit) {
		super(nom, prenom, jourNaissance, moisNaissance, anneeNaissance, listProduit);

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

}
