
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

//Interface pour consulter les informations des personnes
public class InterfaceGraph1 extends JFrame {

	JTextArea champRecherche;
	JTextArea champText;
	JButton buttonRecherche;
	final JTextField messageDialog = new JTextField(); // message pour dialog
	
	JMenuBar menuBar;
	JMenu menu1;
	JMenu menu2;
	private JMenuItem listClient = new JMenuItem("Liste des clients");
	private JMenuItem listEmploye = new JMenuItem("Liste des employes");
	private JMenuItem listDirecteur = new JMenuItem("Liste des directeur");
	private JMenuItem ajoutPersonne = new JMenuItem("Personne");
	private JMenuItem ajoutProduit = new JMenuItem("Produit");
	
	JPanel pane1;
	JPanel panelTable = new JPanel();
	JPanel panelRecherche = new JPanel();
	
	CardLayout c1 = new CardLayout();
	
	ArrayList<Clients> listeClient = new ArrayList<Clients>();
	ArrayList<Employe> listeEmploye = new ArrayList<Employe>();
	ArrayList<Directeur> listeDirecteur = new ArrayList<Directeur>();
	ArrayList<Aliment> listeAliment = new ArrayList<Aliment>(); // pour circuler info
	ArrayList<Meuble> listeMeuble = new ArrayList<Meuble>(); // pour circuler info

	public InterfaceGraph1(ArrayList<Clients> listeClient, ArrayList<Employe> listeEmploye,
			ArrayList<Directeur> listeDirecteur, ArrayList<Aliment> listeAliment, ArrayList<Meuble> listeMeuble) {

		this.listeClient = listeClient;
		this.listeEmploye = listeEmploye;
		this.listeDirecteur = listeDirecteur;
		this.listeAliment = listeAliment;
		this.listeMeuble = listeMeuble;
		
		//----------- Partie pour le menu ------
		menuBar = new JMenuBar();

		menu1 = new JMenu("Affichage");
		menu1.add(listClient);
		menu1.add(listEmploye);
		menu1.add(listDirecteur);
		menu2 = new JMenu("Service");
		menu2.add(ajoutPersonne);
		menu2.add(ajoutProduit);
		menuBar.add(menu1);
		menuBar.add(menu2);
		menuBar.setSize(500, 300);
		add(menuBar, BorderLayout.PAGE_START);
		
		// ------------------Pour les tableaux de donnees----------------
		String[] entetes = { "Nom", "Prenom", "Type", "Date de naissance", "Courriel", "Fond", "Liste Prefere" };

		// Data pour clients
		String[][] dataClient = new String[listeClient.size()][];

		for (int i = 0; i < listeClient.size(); i++) {
			String[] tab = { listeClient.get(i).getNom(), listeClient.get(i).getPrenom(), "Client",
					"" + listeClient.get(i).getJourNaissance() + "/" + listeClient.get(i).getMoisNaissance() + "/"
							+ listeClient.get(i).getAnneeNaissance(),
					listeClient.get(i).getEmail(), "" + listeClient.get(i).getSolde(),
					listeClient.get(i).toStringListProduit() };
			dataClient[i] = tab;
		}

		// Data pour employe
		String[][] dataEmploye = new String[listeEmploye.size()][];

		for (int i = 0; i < listeEmploye.size(); i++) {
			String[] tab = { listeEmploye.get(i).nom, listeEmploye.get(i).prenom, "Employe",
					"" + listeEmploye.get(i).jourNaissance + "/" + listeEmploye.get(i).moisNaissance + "/"
							+ listeEmploye.get(i).anneeNaissance,
					listeEmploye.get(i).email, "" + listeEmploye.get(i).solde,
					listeEmploye.get(i).toStringListProduit() };
			dataEmploye[i] = tab;
		}

		// Data pour directeur
		String[][] dataDirecteur = new String[listeDirecteur.size()][];

		for (int i = 0; i < listeDirecteur.size(); i++) {
			String[] tab = { listeDirecteur.get(i).getNom(), listeDirecteur.get(i).prenom, "Directeur",
					"" + listeDirecteur.get(i).jourNaissance + "/" + listeDirecteur.get(i).moisNaissance + "/"
							+ listeDirecteur.get(i).anneeNaissance,
					listeDirecteur.get(i).email, "" + listeDirecteur.get(i).solde,
					listeDirecteur.get(i).toStringListProduit() };
			dataDirecteur[i] = tab;
		}

		JTable tableauClient = new JTable(dataClient, entetes);
		tableauClient.setBounds(30, 40, 200, 300);
		JScrollPane sp1 = new JScrollPane(tableauClient);

		JTable tableauEmploye = new JTable(dataEmploye, entetes);
		tableauEmploye.setBounds(30, 40, 200, 300);
		JScrollPane sp2 = new JScrollPane(tableauEmploye);

		JTable tableauDirecteur = new JTable(dataDirecteur, entetes);
		tableauDirecteur.setBounds(30, 40, 200, 300);
		JScrollPane sp3 = new JScrollPane(tableauDirecteur);
		
		//Tableau pour la recherche par lettre
		DefaultTableModel tableModel;
		tableModel = new DefaultTableModel(entetes, 0);

		JTable tableauPersonne = new JTable(tableModel);
		JScrollPane sp4 = new JScrollPane(tableauPersonne);
		
		//-------------- Management des panels -----
		
		panelTable.setLayout(c1);

		panelTable.add(sp1, "1");
		panelTable.add(sp2, "2");
		panelTable.add(sp3, "3");
		panelTable.add(sp4, "4");
		panelTable.add(panelRecherche, "5");
		add(panelTable, BorderLayout.CENTER);
		//--------------

		//afficher la liste des clients
		listClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c1.show(panelTable, "1");
			}
		});

		//afficher la liste des employes
		listEmploye.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c1.show(panelTable, "2");
			}
		});

		//afficher la liste des directeurs
		listDirecteur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c1.show(panelTable, "3");
			}
		});

		// -------------------------- Pour ouvrir l'interface 2 et l'interface 3---------
		
		ajoutPersonne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfaceGraph2 interface2 = new InterfaceGraph2(listeClient, listeEmploye, listeDirecteur,
						listeAliment, listeMeuble);
				dispose();
			}
		}

		);

		ajoutProduit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfaceGraph3 interface3 = new InterfaceGraph3(listeClient, listeEmploye, listeDirecteur,
						listeAliment, listeMeuble);
				dispose();
			}
		}

		);

		// ----------------------------- Partie recherche ---------------
		pane1 = new JPanel();

		champRecherche = new JTextArea("Nom ou Prenom");
		champRecherche.setBounds(10, 10, 300, 300);

		buttonRecherche = new JButton("Recherche");
		buttonRecherche.setBounds(10, 30, 80, 25);

		pane1.add(champRecherche);
		pane1.add(buttonRecherche);
		
		add(pane1, BorderLayout.LINE_START);
		
		champText = new JTextArea();
		champText.setEditable(false);
		champText.setSize(500, 500);
		panelRecherche.add(champText);

		buttonRecherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String prenom = champRecherche.getText();
				String message = "La personne n'existe pas";
				tableModel.setRowCount(0); // initialiser le tableau de recherche par 1er lettre de Nom

				if (prenom.length() == 1) { //si recherche par une lettre

					for (int i = 0; i < listeClient.size(); i++) {
						String premierLettre = String.valueOf(listeClient.get(i).getNom().charAt(0));

						if (prenom.equals(premierLettre)) {
							String[] tab = { listeClient.get(i).getNom(), listeClient.get(i).getPrenom(), "Client",
									"" + listeClient.get(i).getJourNaissance() + "/"
											+ listeClient.get(i).getMoisNaissance() + "/"
											+ listeClient.get(i).getAnneeNaissance(),
									listeClient.get(i).getEmail(), "" + listeClient.get(i).getSolde(),
									listeClient.get(i).toStringListProduit() };
							tableModel.addRow(tab);
						}
					}

					for (int i = 0; i < listeEmploye.size(); i++) {
						String premierLettre = String.valueOf(listeEmploye.get(i).getNom().charAt(0));

						if (prenom.equals(premierLettre)) {
							String[] tab = { listeEmploye.get(i).nom, listeEmploye.get(i).prenom, "Employe",
									"" + listeEmploye.get(i).jourNaissance + "/" + listeEmploye.get(i).moisNaissance
											+ "/" + listeEmploye.get(i).anneeNaissance,
									listeEmploye.get(i).email, "" + listeEmploye.get(i).solde,
									listeEmploye.get(i).toStringListProduit() };
							tableModel.addRow(tab);
						}
					}

					for (int i = 0; i < listeDirecteur.size(); i++) {
						String premierLettre = String.valueOf(listeDirecteur.get(i).getNom().charAt(0));

						if (prenom.equals(premierLettre)) {
							String[] tab = { listeDirecteur.get(i).getNom(), listeDirecteur.get(i).prenom, "Directeur",
									"" + listeDirecteur.get(i).jourNaissance + "/" + listeDirecteur.get(i).moisNaissance
											+ "/" + listeDirecteur.get(i).anneeNaissance,
									listeDirecteur.get(i).email, "" + listeDirecteur.get(i).solde,
									listeDirecteur.get(i).toStringListProduit() };
							tableModel.addRow(tab);
						}
					}

					c1.show(panelTable, "4");
					
				} else { //si recherche par nom ou prenom
 
					for (int i = 0; i < listeClient.size(); i++) {
						if (prenom.equals(listeClient.get(i).getPrenom())
								|| prenom.equals(listeClient.get(i).getNom())) {
							message = "CLIENT\n" + "Nom : " + listeClient.get(i).getNom() + "\n" + "Prenom : "
									+ listeClient.get(i).getPrenom() + "\n" + "Date de naissance : "
									+ listeClient.get(i).getJourNaissance() + "/"
									+ listeClient.get(i).getMoisNaissance() + "/"
									+ listeClient.get(i).getAnneeNaissance() + "\n" + "Email : "
									+ listeClient.get(i).getEmail() + "\n" + "Fond disponible : "
									+ listeClient.get(i).solde + "\n" + "Liste produit prefere : "
									+ listeClient.get(i).toStringListProduit();
							break;
						}
					}

					for (int i = 0; i < listeEmploye.size(); i++) {
						if (prenom.equals(listeEmploye.get(i).getPrenom())
								|| prenom.equals(listeEmploye.get(i).getNom())) {
							message = "EMPLOYE\n" + "Nom : " + listeEmploye.get(i).getNom() + "\n" + "Prenom : "
									+ listeEmploye.get(i).getPrenom() + "\n" + "Date de naissance : "
									+ listeEmploye.get(i).getJourNaissance() + "/"
									+ listeEmploye.get(i).getMoisNaissance() + "/"
									+ listeEmploye.get(i).getAnneeNaissance() + "\n" + "Email : "
									+ listeEmploye.get(i).getEmail() + "\n" + "Fond disponible :"
									+ listeEmploye.get(i).solde + "\n" + "Liste produit prefere : "
									+ listeEmploye.get(i).toStringListProduit();
							break;
						}
					}

					for (int i = 0; i < listeDirecteur.size(); i++) {
						if (prenom.equals(listeDirecteur.get(i).getPrenom())
								|| prenom.equals(listeDirecteur.get(i).getNom())) {
							message = "DIRECTEUR\n" + "Nom : " + listeDirecteur.get(i).getNom() + "\n" + "Prenom : "
									+ listeDirecteur.get(i).getPrenom() + "\n" + "Date de naissance : "
									+ listeDirecteur.get(i).getJourNaissance() + "/"
									+ listeDirecteur.get(i).getMoisNaissance() + "/"
									+ listeDirecteur.get(i).getAnneeNaissance() + "\n" + "Email : "
									+ listeDirecteur.get(i).getEmail() + "\n" + "Fond disponible :"
									+ listeDirecteur.get(i).solde + "\n" + "Liste produit prefere : "
									+ listeDirecteur.get(i).toStringListProduit();
							break;
						}
					}
					champText.setText(message);
					c1.show(panelTable, "5");
				}
			}
		});
		
		pack();
		setTitle("Page Repertoire");
		setSize(1300, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ferme fenetre si clique x
		setVisible(true);

	}
}
