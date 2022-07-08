
public class Aliment {
	String nom;
	String couleur;
	float poids;
	String directeur;

	public Aliment(String nom, String couleur, float poids, String directeur) {
		this.nom = nom;
		this.couleur = couleur;
		this.poids = poids;
		this.directeur = directeur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public float getPoids() {
		return poids;
	}

	public void setPoids(float poids) {
		this.poids = poids;
	}

	public String getDirecteur() {
		return directeur;
	}

	public void setDirecteur(String directeur) {
		this.directeur = directeur;
	}

}
