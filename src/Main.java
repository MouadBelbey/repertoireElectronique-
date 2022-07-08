import java.util.ArrayList;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		
		ArrayList<Clients> listeClient = new ArrayList<Clients>();
		ArrayList<Employe> listeEmploye = new ArrayList<Employe>();
		ArrayList<Directeur> listeDirecteur = new ArrayList<Directeur>();
		ArrayList<Aliment> listeAliment = new ArrayList<Aliment>();
		ArrayList<Meuble> listeMeuble = new ArrayList<Meuble>();

		//interface principale
		InterfaceGraph1 interface1 = new InterfaceGraph1(listeClient,listeEmploye,
									listeDirecteur,listeAliment,listeMeuble);
		
	}
}
