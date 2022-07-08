import java.util.ArrayList;

public class Clients extends Personne {

	float solde;
	// ArrayList<String> listProduit; //list produits preferes

	public Clients(String nom, String prenom, int jourNaissance, int moisNaissance, int anneeNaissance,
			ArrayList<String> listProduit) {
		super(nom, prenom, jourNaissance, moisNaissance, anneeNaissance, listProduit);
		// this.listProduit = listProduit;
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

}
