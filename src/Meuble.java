
public class Meuble {
	String type;
	float prix;
	float hauteurMax;
	String directeur;

	public Meuble(String type, float prix, float hauteurMax, String directeur) {
		this.type = type;
		this.prix = prix;
		this.hauteurMax = hauteurMax;
		this.directeur = directeur;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public float getHauteurMax() {
		return hauteurMax;
	}

	public void setHauteurMax(float hauteurMax) {
		this.hauteurMax = hauteurMax;
	}

	public String getDirecteur() {
		return directeur;
	}

	public void setDirecteur(String directeur) {
		this.directeur = directeur;
	}

}
