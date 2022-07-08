import java.util.ArrayList;
import java.util.Calendar;

public abstract class Personne {

	Calendar c = Calendar.getInstance();
	int anneeCourant = c.get(Calendar.YEAR);

	String nom;
	String prenom;
	String email;
	int jourNaissance;
	int moisNaissance;
	int anneeNaissance;
	int age;
	ArrayList<String> listProduit;

	public Personne(String nom, String prenom, int jourNaissance, int moisNaissance, int anneeNaissance,
			ArrayList<String> listProduit) {
		this.nom = nom;
		this.prenom = prenom;
		this.jourNaissance = jourNaissance;
		this.moisNaissance = moisNaissance;
		this.anneeNaissance = anneeNaissance;
		age = anneeCourant - anneeNaissance;
		email = nom + prenom + "@magasin.ca";
		this.listProduit = listProduit;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public String setEmail(String email) {
		return this.email = email;
	}

	public int getJourNaissance() {
		return jourNaissance;
	}

	public void setJourNaissance(int jourNaissance) {
		this.jourNaissance = jourNaissance;
	}

	public int getMoisNaissance() {
		return moisNaissance;
	}

	public void setMoisNaissance(int moisNaissance) {
		this.moisNaissance = moisNaissance;
	}

	public int getAnneeNaissance() {
		return anneeNaissance;
	}

	public void setAnneeNaissance(int anneeNaissance) {
		this.anneeNaissance = anneeNaissance;
	}

	public ArrayList<String> getListProduit() {
		return listProduit;
	}

	public void setListProduit(ArrayList<String> listProduit) {
		this.listProduit = listProduit;
	}

	public String toStringListProduit() {
		String message = "";
		if (listProduit.size() > 0) {
			for (int i = 0; i < listProduit.size(); i++) {
				message += listProduit.get(i) + " ,";
			}
			return message;
		}
		return "vide";
	}

}
