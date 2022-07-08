import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class InterfaceGraph2 extends JFrame {

	JTextArea champRecherche;
	JTextArea champText;
	JButton buttonRecherche;
	JButton buttonModifier;
	JButton buttonSupprimer;
	JButton buttonConfirmer;
	JButton buttonModifierSolde;
	JPasswordField passwordText;
	JLabel passwordLabel;
	final JTextField message = new JTextField(); // message pour dialog

	JMenuBar menuBar = new JMenuBar();
	JMenu menu;
	JMenu menu1;
	JMenu menu2;
	private JMenuItem affichage = new JMenuItem("Affichage");
	private JMenuItem ajoutClient = new JMenuItem("Client");
	private JMenuItem ajoutEmploye = new JMenuItem("Employe");
	private JMenuItem ajoutDirecteur = new JMenuItem("Directeur");
	private JMenuItem listeProduit = new JMenuItem("Liste de produits");

	JPanel pane1 = new JPanel(); //pour panel de recherche
	JPanel pane2 = new JPanel(); //panel vide par defaut
	JPanel panelParent = new JPanel(); //panel parent pour la gestion des panel
	JPanel panelAjout = new JPanel(); //panel pour ajouter
	JPanel panelRecherche = new JPanel(); //panel pour afficher la recherche
	JPanel panelSupprimer = new JPanel(); //panel pour supprimer
	JPanel panelModifier = new JPanel(); //panel pour modifier les infos
	JPanel panelModifierSolde = new JPanel(); //panel pour retirer ou ajouter solde
	CardLayout c1 = new CardLayout();

	ArrayList<Clients> listeClient = new ArrayList<Clients>();
	ArrayList<Employe> listeEmploye = new ArrayList<Employe>();
	ArrayList<Directeur> listeDirecteur = new ArrayList<Directeur>();
	ArrayList<Aliment> listeAliment = new ArrayList<Aliment>(); // pour circuler info
	ArrayList<Meuble> listeMeuble = new ArrayList<Meuble>(); // pour circuler info

	public InterfaceGraph2(ArrayList<Clients> listeClient, ArrayList<Employe> listeEmploye,
			ArrayList<Directeur> listeDirecteur, ArrayList<Aliment> listeAliment, ArrayList<Meuble> listeMeuble) {
		
		this.listeClient = listeClient;
		this.listeEmploye = listeEmploye;
		this.listeDirecteur = listeDirecteur;
		this.listeAliment = listeAliment;
		this.listeMeuble = listeMeuble;

		// --------- partie pour menu ------
		menu = new JMenu("Accueil");
		menu.add(affichage);

		menu1 = new JMenu("Ajouter");
		menu1.add(ajoutClient);
		menu1.add(ajoutEmploye);
		menu1.add(ajoutDirecteur);

		menu2 = new JMenu("Produits");
		menu2.add(listeProduit);

		menuBar.add(menu); // pour page affichage
		menuBar.add(menu1); // pour ajouter nouvelle personne
		menuBar.add(menu2); // pour page produits
		menuBar.setSize(500, 300);
		add(menuBar, BorderLayout.PAGE_START);

		// ----------- Management des panels de l'interface 2

		panelParent.setLayout(c1);
		
		panelRecherche.setLayout(null);
		panelAjout.setLayout(null);
		panelSupprimer.setLayout(null);
		panelModifier.setLayout(null);
		panelModifierSolde.setLayout(null);
		
		panelParent.add(pane2, "1"); //panel champ pour ecrire Nom ou Prenom a rechercher
		panelParent.add(panelAjout, "2"); 
		panelParent.add(panelRecherche, "3"); //panel affiche le resultat de la recherche
		panelParent.add(panelSupprimer, "4");
		panelParent.add(panelModifier, "5");
		panelParent.add(panelModifierSolde, "6");
		
		add(panelParent, BorderLayout.CENTER);

		//-------------
		
		//pour switch a l'interface d'informations
		affichage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfaceGraph1 interface1 = new InterfaceGraph1(listeClient, listeEmploye, listeDirecteur,
						listeAliment, listeMeuble);
				dispose();
			}
		});

		//pour switch a l'interface des produits
		listeProduit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfaceGraph3 interface3 = new InterfaceGraph3(listeClient, listeEmploye, listeDirecteur,
						listeAliment, listeMeuble);
				dispose();
			}
		});

		// ----------------------------- Partie recherche ---------------
		champRecherche = new JTextArea("Prenom ou Nom");
		champRecherche.setSize(300, 300);
		buttonRecherche = new JButton("Recherche");

		pane1.add(champRecherche);
		pane1.add(buttonRecherche);
		add(pane1, BorderLayout.LINE_START);

		champText = new JTextArea();
		champText.setEditable(false);
		champText.setSize(300, 150);
		panelRecherche.add(champText);

		buttonModifier = new JButton("Modifier");
		buttonModifier.setBounds(50, 150, 120, 25);
		panelRecherche.add(buttonModifier);

		buttonSupprimer = new JButton("Supprimer");
		buttonSupprimer.setBounds(200, 150, 120, 25);
		panelRecherche.add(buttonSupprimer);

		buttonModifierSolde = new JButton("Modifier Solde");
		buttonModifierSolde.setBounds(350, 150, 150, 25);
		panelRecherche.add(buttonModifierSolde);

		
		buttonRecherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String prenom = champRecherche.getText();
				String message = "La personne n'existe pas";
				boolean estTrouve = false;

				if (estTrouve == false) {
					
					int indexClient = moteurRechercheClient(prenom, listeClient);
					if (indexClient != -1) {
						
						estTrouve = true;
						message = "CLIENT\n" + "Nom : " + listeClient.get(indexClient).getNom() + "\n" + "Prenom : "
								+ listeClient.get(indexClient).getPrenom() + "\n" + "Date de naissance : "
								+ listeClient.get(indexClient).getJourNaissance() + "/"
								+ listeClient.get(indexClient).getMoisNaissance() + "/"
								+ listeClient.get(indexClient).getAnneeNaissance() + "\n" + "Email : "
								+ listeClient.get(indexClient).getEmail() + "\n" + "Fond disponible : "
								+ listeClient.get(indexClient).solde + "\n" + "Liste produit(s) prefere(s) : "
								+ listeClient.get(indexClient).toStringListProduit();
						;
					}
				}

				if (estTrouve == false) {
					
					int indexEmploye = moteurRechercheEmploye(prenom, listeEmploye);
					if (indexEmploye != -1) {
						
						estTrouve = true;
						message = "EMPLOYE\n" + "Nom : " + listeEmploye.get(indexEmploye).getNom() + "\n" + "Prenom : "
								+ listeEmploye.get(indexEmploye).getPrenom() + "\n" + "Date de naissance : "
								+ listeEmploye.get(indexEmploye).getJourNaissance() + "/"
								+ listeEmploye.get(indexEmploye).getMoisNaissance() + "/"
								+ listeEmploye.get(indexEmploye).getAnneeNaissance() + "\n" + "Email : "
								+ listeEmploye.get(indexEmploye).getEmail() + "\n" + "Fond disponible :"
								+ listeEmploye.get(indexEmploye).solde + "\n" + "Liste produit(s) prefere(s) : "
								+ listeEmploye.get(indexEmploye).toStringListProduit();
					}
				}

				if (estTrouve == false) {
					
					int indexDirecteur = moteurRechercheDirecteur(prenom, listeDirecteur);
					if (indexDirecteur != -1) {
						
						estTrouve = true;
						message = "DIRECTEUR\n" + "Nom : " + listeDirecteur.get(indexDirecteur).getNom() + "\n"
								+ "Prenom : " + listeDirecteur.get(indexDirecteur).getPrenom() + "\n"
								+ "Date de naissance : " + listeDirecteur.get(indexDirecteur).getJourNaissance() + "/"
								+ listeDirecteur.get(indexDirecteur).getMoisNaissance() + "/"
								+ listeDirecteur.get(indexDirecteur).getAnneeNaissance() + "\n" + "Email : "
								+ listeDirecteur.get(indexDirecteur).getEmail() + "\n" + "Fond disponible :"
								+ listeDirecteur.get(indexDirecteur).solde + "\n" + "Liste produit(s) prefere(s) : "
								+ listeDirecteur.get(indexDirecteur).toStringListProduit();
					}
				}
				champText.setText(message);
				c1.show(panelParent, "3");
			}
		});

		// ------------Formulaire pour Ajouter------------
		JLabel titre = new JLabel("");
		titre.setBounds(10, -30, 120, 120);
		panelAjout.add(titre);

		JLabel nom = new JLabel("Nom");
		nom.setBounds(10, 40, 80, 25); //
		panelAjout.add(nom);

		JTextField nomText = new JTextField(20);
		nomText.setBounds(140, 40, 170, 25);
		panelAjout.add(nomText);

		JLabel prenom = new JLabel("Prenom");
		prenom.setBounds(10, 70, 80, 25);
		panelAjout.add(prenom);

		JTextField prenomText = new JTextField(20);
		prenomText.setBounds(140, 70, 170, 25);
		panelAjout.add(prenomText);

		JLabel dateNaissance = new JLabel("Date de Naissance");
		dateNaissance.setBounds(10, 100, 165, 25);
		panelAjout.add(dateNaissance);

		JTextField JNaissanceText = new JTextField("jj");
		JNaissanceText.setBounds(140, 100, 30, 25);
		panelAjout.add(JNaissanceText);

		JTextField MNaissanceText = new JTextField("mm");
		MNaissanceText.setBounds(180, 100, 30, 25);
		panelAjout.add(MNaissanceText);

		JTextField ANaissanceText = new JTextField("aaaa");
		ANaissanceText.setBounds(230, 100, 80, 25);
		panelAjout.add(ANaissanceText);

		JLabel solde = new JLabel("Solde");
		solde.setBounds(10, 130, 80, 25);
		panelAjout.add(solde);

		JTextField soldeText = new JTextField(20);
		soldeText.setBounds(140, 130, 170, 25);
		panelAjout.add(soldeText);

		JLabel pwd = new JLabel("Mot de passe");
		pwd.setBounds(350, 130, 120, 25);
		panelAjout.add(pwd);

		JTextField pwdTexte = new JTextField(20);
		pwdTexte.setBounds(470, 130, 170, 25);
		panelAjout.add(pwdTexte);

		JLabel listP = new JLabel("Liste produit prefere");
		listP.setBounds(10, 160, 200, 25);
		panelAjout.add(listP);

		//tableau pour les produits disponibles
		String[] enteteListProduit = { "Produit disponible" };

		DefaultTableModel tableModelProduit;
		if (listeAliment.size() + listeMeuble.size() == 0) {
			tableModelProduit = new DefaultTableModel(enteteListProduit, 0);
		} else {
			String[] dataProduit = new String[listeAliment.size() + listeMeuble.size()];
			for (int i = 0; i < listeAliment.size(); i++) {
				dataProduit[i] = listeAliment.get(i).getNom();
			}
			for (int j = 0; j < listeMeuble.size(); j++) {
				dataProduit[j + listeAliment.size()] = listeMeuble.get(j).getType();
			}

			tableModelProduit = new DefaultTableModel();
			tableModelProduit.addColumn("Produit disponible", dataProduit);
		}

		JTable tableauProduit = new JTable(tableModelProduit);
		JScrollPane sp = new JScrollPane(tableauProduit);
		sp.setBounds(210, 160, 150, 200);
		panelAjout.add(sp);

		JButton buttonBucketList = new JButton("Ajouter dans la liste");
		buttonBucketList.setBounds(380, 200, 150, 25);
		panelAjout.add(buttonBucketList);

		//tableau affiche les produis preferes selectionnes
		String[] enteteListProduitPrefere = { "Liste prefere" };

		DefaultTableModel tableModelProduitPrefere = new DefaultTableModel(enteteListProduitPrefere, 0);

		JTable tableauProduitPrefere = new JTable(tableModelProduitPrefere);
		JScrollPane sp1 = new JScrollPane(tableauProduitPrefere);
		sp1.setBounds(550, 160, 100, 150);
		panelAjout.add(sp1);

		JButton buttonSupprimeProduit = new JButton("Supprimer"); //pour deselectionner un produit de la liste prefere
		buttonSupprimeProduit.setBounds(550, 330, 100, 25);
		panelAjout.add(buttonSupprimeProduit);

		JButton buttonAjouter = new JButton("Ajouter"); //pour ajouter personne
		buttonAjouter.setBounds(40, 200, 100, 25);
		panelAjout.add(buttonAjouter);

		// --------------Partie Ajouter-----------------------------------------
		//afficher formulaire pour ajouter client
		ajoutClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				titre.setText("CLIENT");
				nomText.setText("");
				prenomText.setText("");
				JNaissanceText.setText("jj");
				MNaissanceText.setText("mm");
				ANaissanceText.setText("aaaa");
				soldeText.setText("0");
				pwd.setVisible(false);
				pwdTexte.setVisible(false);
				c1.show(panelParent, "2");
			}
		});
		
		//afficher formulaire pour ajouter employe
		ajoutEmploye.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				titre.setText("EMPLOYE");
				nomText.setText("");
				prenomText.setText("");
				JNaissanceText.setText("jj");
				MNaissanceText.setText("mm");
				ANaissanceText.setText("aaaa");
				soldeText.setText("0");
				pwd.setVisible(false);
				pwdTexte.setVisible(false);
				c1.show(panelParent, "2");
			}
		});

		//afficher formulaire pour ajouter directeur
		ajoutDirecteur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				titre.setText("DIRECTEUR");
				nomText.setText("");
				prenomText.setText("");
				JNaissanceText.setText("jj");
				MNaissanceText.setText("mm");
				ANaissanceText.setText("aaaa");
				soldeText.setText("0");
				pwd.setVisible(true);
				pwdTexte.setVisible(true);

				c1.show(panelParent, "2");
			}
		});

		//button pour ajouter un produit selectionne dans la liste preferee
		buttonBucketList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (tableauProduit.getSelectedRowCount() == 1) {
					
					String nomProduit = tableModelProduit.getValueAt(tableauProduit.getSelectedRow(), 0).toString();
					String[] data = { nomProduit };
					tableModelProduitPrefere.addRow(data);
					
				} else if (tableauProduit.getRowCount() == 0) {
					
					JOptionPane.showMessageDialog(message, "Tableau est vide !");
				} else {
					
					JOptionPane.showMessageDialog(message, "Veuillez selectionne une seule ligne !");
				}
			}

		});
		
		//button pour deselectionner un produit de la liste preferee
		buttonSupprimeProduit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (tableauProduitPrefere.getSelectedRowCount() == 1) {

					tableModelProduitPrefere.removeRow(tableauProduitPrefere.getSelectedRow());

				} else if (tableauProduitPrefere.getRowCount() == 0) {
					
					JOptionPane.showMessageDialog(message, "Tableau est vide !");
				} else {
					
					JOptionPane.showMessageDialog(message, "Veuillez selectionne une seule ligne !");
				}
			}

		});

		//pour ajouter la personne
		buttonAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nom = nomText.getText();
				String prenom = prenomText.getText();
				String jourTexte = JNaissanceText.getText();
				String moisTexte = MNaissanceText.getText();
				String anneeTexte = ANaissanceText.getText();
				
				float solde = Float.parseFloat(soldeText.getText());
				ArrayList<String> listeProduit = new ArrayList<String>();
				String pwd = pwdTexte.getText();

				// pour extrait liste de produit prefere
				for (int row = 0; row < tableauProduitPrefere.getRowCount(); row++) {
					listeProduit.add(tableauProduitPrefere.getValueAt(row, 0).toString());
				}

				if (nom.equals("") || prenom.equals("") || jourTexte.equals("") || jourTexte.equals("jj") 
								   || moisTexte.equals("") || moisTexte.equals("mm") || anneeTexte.equals("") || anneeTexte.equals("aaaa")) { //require de remplir tous les infos

					JOptionPane.showMessageDialog(message, "Entrez tous les infos svp!");
					
				} else { //si tous les infos sont remplis
					int jour = Integer.parseInt(jourTexte);
					int mois = Integer.parseInt(moisTexte);
					int annee = Integer.parseInt(anneeTexte);
					
					if (jour >= 1 && jour <= 31 && mois >= 1 && mois <= 12 && annee >= 1800 && annee <= 2300) {
						if (solde >= 0) {
							if (titre.getText() == "CLIENT") { //si c'est un client

								Clients client = new Clients(nom, prenom, jour, mois, annee, listeProduit);
								client.setSolde(solde);
								listeClient.add(client);

								JOptionPane.showMessageDialog(message, "Le client a ete ajoute avec succes.");

							} else if (titre.getText() == "EMPLOYE") { //si c'est un employe

								Employe employe = new Employe(nom, prenom, jour, mois, annee, listeProduit);
								employe.setSolde(solde);
								listeEmploye.add(employe);

								JOptionPane.showMessageDialog(message, "L'employe a ete ajoute avec succes.");

							} else if (titre.getText() == "DIRECTEUR") { //si c'est un directeur

								Directeur directeur = new Directeur(nom, prenom, jour, mois, annee, listeProduit, pwd);
								directeur.setSolde(solde);
								listeDirecteur.add(directeur);
								System.out.println("Mot de passe de " + prenom + " : " + pwd);
								JOptionPane.showMessageDialog(message, "Le directeur a ete ajoute avec succes.");
							}

							nomText.setText("");
							prenomText.setText("");
							JNaissanceText.setText("jj");
							MNaissanceText.setText("mm");
							ANaissanceText.setText("aaaa");
							soldeText.setText("0");
							pwdTexte.setText("");
							tableModelProduitPrefere.setRowCount(0); // initialiser le tableau de list prefere

						} else { //solde negatif
							 
							JOptionPane.showMessageDialog(message, "Veuillez entrer un solde positif");
						}
					} else { //date de naissance bizarre
						
						JOptionPane.showMessageDialog(message, "Veuillez entrer une date appropriee");
					}
				}
			}
		});

		// ----------Formulaire pour Modifier----------------
		JLabel titreM = new JLabel("");
		titreM.setBounds(10, -30, 120, 120);
		panelModifier.add(titreM);

		JLabel nomM = new JLabel("Nom");
		nomM.setBounds(10, 40, 80, 25);
		panelModifier.add(nomM);

		JTextField nomTextM = new JTextField(20);
		nomTextM.setBounds(140, 40, 170, 25);
		panelModifier.add(nomTextM);

		JLabel prenomM = new JLabel("Prenom");
		prenomM.setBounds(10, 70, 80, 25);
		panelModifier.add(prenomM);

		JTextField prenomTextM = new JTextField(20);
		prenomTextM.setBounds(140, 70, 170, 25);
		panelModifier.add(prenomTextM);

		JLabel dateNaissanceM = new JLabel("Date de Naissance");
		dateNaissanceM.setBounds(10, 100, 165, 25);
		panelModifier.add(dateNaissanceM);

		JTextField JNaissanceTextM = new JTextField("jj");
		JNaissanceTextM.setBounds(140, 100, 30, 25);
		panelModifier.add(JNaissanceTextM);

		JTextField MNaissanceTextM = new JTextField("mm");
		MNaissanceTextM.setBounds(180, 100, 30, 25);
		panelModifier.add(MNaissanceTextM);

		JTextField ANaissanceTextM = new JTextField("aaaa");
		ANaissanceTextM.setBounds(230, 100, 80, 25);
		panelModifier.add(ANaissanceTextM);

		JLabel soldeM = new JLabel("Solde");
		soldeM.setBounds(10, 130, 80, 25);
		panelModifier.add(soldeM);

		JTextField soldeTextM = new JTextField(20);
		soldeTextM.setBounds(140, 130, 170, 25);
		soldeTextM.setEditable(false);
		panelModifier.add(soldeTextM);

		JLabel listP1 = new JLabel("Liste produit(s) prefere(s)");
		listP1.setBounds(10, 160, 200, 25);
		panelModifier.add(listP1);

		// model pour la list des produits disponibles
		DefaultTableModel tableModel2;
		
		if (listeAliment.size() + listeMeuble.size() == 0) {
			tableModel2 = new DefaultTableModel(enteteListProduit, 0);
		} else {
			String[] dataProduit = new String[listeAliment.size() + listeMeuble.size()];
			for (int i = 0; i < listeAliment.size(); i++) {
				dataProduit[i] = listeAliment.get(i).getNom();
			}
			for (int j = 0; j < listeMeuble.size(); j++) {
				dataProduit[j + listeAliment.size()] = listeMeuble.get(j).getType();
			}

			tableModel2 = new DefaultTableModel();
			tableModel2.addColumn("Produit(s) disponible(s)", dataProduit);
		}

		JTable tableauProduit1 = new JTable(tableModel2);
		JScrollPane sp2 = new JScrollPane(tableauProduit1);
		sp2.setBounds(210, 160, 150, 200);
		panelModifier.add(sp2);

		JButton buttonBucketList1 = new JButton("Ajouter dans la liste");
		buttonBucketList1.setBounds(380, 200, 150, 25);
		panelModifier.add(buttonBucketList1);

		//model pour tableau de list produits preferes
		DefaultTableModel tableModel3 = new DefaultTableModel(enteteListProduitPrefere, 0);

		JTable tableauProduitPrefere1 = new JTable(tableModel3);
		JScrollPane sp3 = new JScrollPane(tableauProduitPrefere1);
		sp3.setBounds(550, 160, 100, 150);
		panelModifier.add(sp3);

		JButton buttonSupprimeProduit1 = new JButton("Supprimer");
		buttonSupprimeProduit1.setBounds(550, 330, 100, 25);
		panelModifier.add(buttonSupprimeProduit1);

		JButton buttonConfirmerM = new JButton("Confirmer");
		buttonConfirmerM.setBounds(50, 200, 120, 25);
		panelModifier.add(buttonConfirmerM);

		// ----------------------------- Partie Modifier ---------------

		//pour afficher panel pour modifier les infos
		buttonModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String rech = champRecherche.getText();
				tableModel3.setRowCount(0);
				boolean estTrouve = false;

				if (estTrouve == false) { //si pas encore trouve la personne
					int indexClient = moteurRechercheClient(rech, listeClient);
					if (indexClient != -1) {

						titreM.setText("CLIENT");
						nomTextM.setText(listeClient.get(indexClient).getNom());
						prenomTextM.setText(listeClient.get(indexClient).getPrenom());
						JNaissanceTextM.setText("" + listeClient.get(indexClient).getJourNaissance());
						MNaissanceTextM.setText("" + listeClient.get(indexClient).getMoisNaissance());
						ANaissanceTextM.setText("" + listeClient.get(indexClient).getAnneeNaissance());
						soldeTextM.setText("" + listeClient.get(indexClient).getSolde());
						ArrayList<String> dataProduit = listeClient.get(indexClient).getListProduit();

						for (int k = 0; k < dataProduit.size(); k++) {
							String[] data = { dataProduit.get(k) };
							tableModel3.addRow(data);
						}

						c1.show(panelParent, "5");
						estTrouve = true;
					}
				}

				if (estTrouve == false) { //si pas encore trouve la personne
					int indexEmploye = moteurRechercheEmploye(rech, listeEmploye);
					if (indexEmploye != -1) {

						titreM.setText("EMPLOYE");
						nomTextM.setText(listeEmploye.get(indexEmploye).getNom());
						prenomTextM.setText(listeEmploye.get(indexEmploye).getPrenom());
						JNaissanceTextM.setText("" + listeEmploye.get(indexEmploye).getJourNaissance());
						MNaissanceTextM.setText("" + listeEmploye.get(indexEmploye).getMoisNaissance());
						ANaissanceTextM.setText("" + listeEmploye.get(indexEmploye).getAnneeNaissance());
						soldeTextM.setText("" + listeEmploye.get(indexEmploye).getSolde());
						ArrayList<String> dataProduit = listeEmploye.get(indexEmploye).getListProduit();

						for (int k = 0; k < dataProduit.size(); k++) {
							String[] data = { dataProduit.get(k) };
							tableModel3.addRow(data);
						}

						c1.show(panelParent, "5");
						estTrouve = true;
					}
				}

				if (estTrouve == false) { //si pas encore trouve la personne
					int indexDirecteur = moteurRechercheDirecteur(rech, listeDirecteur);
					if (indexDirecteur != -1) {

						titreM.setText("DIRECTEUR");
						nomTextM.setText(listeDirecteur.get(indexDirecteur).getNom());
						prenomTextM.setText(listeDirecteur.get(indexDirecteur).getPrenom());
						JNaissanceTextM.setText("" + listeDirecteur.get(indexDirecteur).getJourNaissance());
						MNaissanceTextM.setText("" + listeDirecteur.get(indexDirecteur).getMoisNaissance());
						ANaissanceTextM.setText("" + listeDirecteur.get(indexDirecteur).getAnneeNaissance());
						soldeTextM.setText("" + listeDirecteur.get(indexDirecteur).getSolde());
						ArrayList<String> dataProduit = listeDirecteur.get(indexDirecteur).getListProduit();

						for (int k = 0; k < dataProduit.size(); k++) {
							String[] data = { dataProduit.get(k) };
							tableModel3.addRow(data);
						}

						c1.show(panelParent, "5");
					}
				}
			}
		});

		// ----------------------------- Confirmer la
		// modification----------------------------

		//button pour ajouter un produit dans la liste des produits preferes
		buttonBucketList1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (tableauProduit1.getSelectedRowCount() == 1) {
					
					String nomProduit = tableModel2.getValueAt(tableauProduit1.getSelectedRow(), 0).toString();
					String[] data = { nomProduit };
					tableModel3.addRow(data);
					
				} else if (tableauProduit1.getRowCount() == 0) {
					
					JOptionPane.showMessageDialog(message, "Tableau est vide !");
				} else {
					
					JOptionPane.showMessageDialog(message, "Veuillez selectionner une seule ligne !");
				}
			}

		});

		//button pour deselectionner un produit de la liste des produits preferes
		buttonSupprimeProduit1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (tableauProduitPrefere1.getSelectedRowCount() == 1) {

					tableModel3.removeRow(tableauProduitPrefere1.getSelectedRow());

				} else if (tableauProduitPrefere1.getRowCount() == 0) {
					JOptionPane.showMessageDialog(message, "Tableau est vide !");
				} else {
					JOptionPane.showMessageDialog(message, "Veuillez selectionner une seule ligne !");
				}
			}

		});

		buttonConfirmerM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String rech = champRecherche.getText();
				String type = titreM.getText();
				String nom = nomTextM.getText();
				String prenom = prenomTextM.getText();
				int jour = Integer.parseInt(JNaissanceTextM.getText());
				int mois = Integer.parseInt(MNaissanceTextM.getText());
				int annee = Integer.parseInt(ANaissanceTextM.getText());
				ArrayList<String> listeProduit = new ArrayList<String>();

				// pour recuperer data de list prefere modifie
				for (int row = 0; row < tableauProduitPrefere1.getRowCount(); row++) {
					listeProduit.add(tableauProduitPrefere1.getValueAt(row, 0).toString());
				}

				if (jour >= 1 && jour <= 31 && mois >= 1 && mois <= 12 && annee >= 1900 && annee <= 2300) {
					if (type.equals("CLIENT")) {
						int indexClient = moteurRechercheClient(rech, listeClient);

						listeClient.get(indexClient).setNom(nom);
						listeClient.get(indexClient).setPrenom(prenom);
						listeClient.get(indexClient).setJourNaissance(jour);
						listeClient.get(indexClient).setMoisNaissance(mois);
						listeClient.get(indexClient).setAnneeNaissance(annee);
						listeClient.get(indexClient).setEmail(nom + prenom + "@magasin.ca");
						// listeClient.get(i).setSolde(Float.parseFloat(soldeTextM.getText()));
						listeClient.get(indexClient).setListProduit(listeProduit);
						JOptionPane.showMessageDialog(message, "Le Client a ete modifie avec succes.");

					} else if (type.equals("EMPLOYE")) {
						int indexEmploye = moteurRechercheEmploye(rech, listeEmploye);

						listeEmploye.get(indexEmploye).setNom(nom);
						listeEmploye.get(indexEmploye).setPrenom(prenom);
						listeEmploye.get(indexEmploye).setJourNaissance(jour);
						listeEmploye.get(indexEmploye).setMoisNaissance(mois);
						listeEmploye.get(indexEmploye).setAnneeNaissance(annee);
						listeEmploye.get(indexEmploye).setEmail(nom + prenom + "@magasin.ca");
						// listeEmploye.get(i).setSolde(Float.parseFloat(soldeTextM.getText()));
						listeEmploye.get(indexEmploye).setListProduit(listeProduit);
						JOptionPane.showMessageDialog(message, "L'employe a ete modifie avec succes.");

					} else {
						int indexDirecteur = moteurRechercheDirecteur(rech, listeDirecteur);

						listeDirecteur.get(indexDirecteur).setNom(nom);
						listeDirecteur.get(indexDirecteur).setPrenom(prenom);
						listeDirecteur.get(indexDirecteur).setJourNaissance(jour);
						listeDirecteur.get(indexDirecteur).setMoisNaissance(mois);
						listeDirecteur.get(indexDirecteur).setAnneeNaissance(annee);
						listeDirecteur.get(indexDirecteur).setEmail(nom + prenom + "@magasin.ca");
						// listeDirecteur.get(i).setSolde(Float.parseFloat(soldeTextM.getText()));
						listeDirecteur.get(indexDirecteur).setListProduit(listeProduit);
						JOptionPane.showMessageDialog(message, "Le directeur a ete modifie avec succes.");
					}
				} else {
					JOptionPane.showMessageDialog(message, "Veuillez entrer une date appropriee !");
				}
			}
		});

		// ------------Formulaire pour la modification du
		// solde------------------------------------------

		JLabel titreS = new JLabel("Modifier le solde");
		titreS.setBounds(10, -30, 120, 120);
		panelModifierSolde.add(titreS);

		JLabel ajouterSolde = new JLabel("Ajouter");
		ajouterSolde.setBounds(10, 40, 110, 25);
		panelModifierSolde.add(ajouterSolde);

		JTextField ajouterSoldeText = new JTextField(20);
		ajouterSoldeText.setText("0");
		ajouterSoldeText.setBounds(150, 40, 170, 25);
		panelModifierSolde.add(ajouterSoldeText);

		JLabel retirerSolde = new JLabel("Retirer");
		retirerSolde.setBounds(10, 70, 110, 25);
		retirerSolde.setVisible(true);
		panelModifierSolde.add(retirerSolde);

		JTextField retirerSoldeText = new JTextField(20);
		retirerSoldeText.setText("0");
		retirerSoldeText.setBounds(150, 70, 170, 25);
		retirerSoldeText.setVisible(false);
		panelModifierSolde.add(retirerSoldeText);

		JLabel prenomDS = new JLabel("Autorise par directeur");
		prenomDS.setBounds(10, 100, 150, 25);
		panelModifierSolde.add(prenomDS);

		JTextField prenomDStext = new JTextField(20);
		prenomDStext.setBounds(150, 100, 170, 25);
		panelModifierSolde.add(prenomDStext);

		JButton buttonConfirmerS = new JButton("Confirmer");
		buttonConfirmerS.setBounds(50, 130, 120, 25);
		panelModifierSolde.add(buttonConfirmerS);

		JPanel panelPwd = new JPanel();
		JLabel labelPwd = new JLabel("Enter a password :");
		JPasswordField pass = new JPasswordField(10);
		panelPwd.add(labelPwd);
		panelPwd.add(pass);
		String[] options = new String[] { "OK", "Cancel" };

		// ----------------------------- Partie modifier le solde---------------

		buttonModifierSolde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String rech = champRecherche.getText();
				int index = moteurRechercheClient(rech, listeClient);

				if (index != -1) {
					retirerSolde.setVisible(false);
					retirerSoldeText.setVisible(false);
					c1.show(panelParent, "6");

				} else {
					retirerSolde.setVisible(true);
					retirerSoldeText.setVisible(true);
					c1.show(panelParent, "6");
				}
			}
		});

		// ----------------------------- Confirmer le nouveau
		// solde----------------------------

		buttonConfirmerS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String rech = champRecherche.getText();
				float ajoutSolde = Float.parseFloat(ajouterSoldeText.getText());
				float retirerSolde = Float.parseFloat(retirerSoldeText.getText());
				String directeur = prenomDStext.getText();
				boolean estTrouve = false;

				if (ajoutSolde < 0 || retirerSolde < 0) {
					JOptionPane.showMessageDialog(message, "Veuillez entrer un montant positif !");
				} else {

					int option = JOptionPane.showOptionDialog(null, panelPwd, "Autorisation de " + directeur,
							JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
					if (option == 0) {

						String pwd = String.valueOf(pass.getPassword()); //mdp entre par utilisateur

						int index = moteurRechercheDirecteur(directeur, listeDirecteur);

						if (index == -1) {

							JOptionPane.showMessageDialog(message, "Veuillez entrer le prenom valide de directeur");

						} else if (pwd.equals(listeDirecteur.get(index).getPassword())) { //verifier le mdp

							if (estTrouve == false) { //si pas encore trouve la personne
								
								int indexClient = moteurRechercheClient(rech, listeClient);
								if (indexClient != -1) {
									
									estTrouve = true;

									listeClient.get(indexClient).ajoutSolde(ajoutSolde);
									JOptionPane.showMessageDialog(message, "Solde ajoute avec succes");

								}
							}

							if (estTrouve == false) { //si pas encore trouve la personne
								
								int indexEmploye = moteurRechercheEmploye(rech, listeEmploye);
								if (indexEmploye != -1) {
									
									estTrouve = true;
									
									if (ajoutSolde != 0) {
										
										listeEmploye.get(indexEmploye).ajoutSolde(ajoutSolde);
										JOptionPane.showMessageDialog(message, "Solde ajoute avec succes");

									} else if ((retirerSolde != 0)
											&& (retirerSolde <= listeEmploye.get(indexEmploye).getSolde())) {
										
										listeEmploye.get(indexEmploye).retireSolde(retirerSolde);
										JOptionPane.showMessageDialog(message, "Solde retire avec succes");
									}
								}
							}

							if (estTrouve == false) { //si pas encore trouve la personne
								
								int indexDirecteur = moteurRechercheDirecteur(rech, listeDirecteur);
								if (indexDirecteur != -1) {
									
									estTrouve = true;
									
									if (ajoutSolde != 0) {
										
										listeDirecteur.get(indexDirecteur).ajoutSolde(ajoutSolde);
										JOptionPane.showMessageDialog(message, "Solde ajoute avec succes");

									} else if (retirerSolde != 0
											&& (retirerSolde <= listeDirecteur.get(indexDirecteur).getSolde())) {
										
										listeDirecteur.get(indexDirecteur).retireSolde(retirerSolde);
										JOptionPane.showMessageDialog(message, "Solde retire avec succes");

									}
								}
							}
							ajouterSoldeText.setText("0");
							retirerSoldeText.setText("0");
							prenomDStext.setText("");
						}
					}
				}
			}
		});

		// ----------------------------- Partie Supprimer ---------------

		JLabel prenomD = new JLabel("Autorise par directeur");
		prenomD.setBounds(10, 20, 150, 25);
		panelSupprimer.add(prenomD);

		JTextField prenomDtext = new JTextField(20);
		prenomDtext.setBounds(150, 20, 170, 25);
		panelSupprimer.add(prenomDtext);

		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		panelSupprimer.add(passwordLabel);

		passwordText = new JPasswordField();
		passwordText.setBounds(150, 40, 170, 25);
		panelSupprimer.add(passwordText);

		buttonConfirmer = new JButton("Confirmer");
		buttonConfirmer.setBounds(170, 80, 100, 25);
		panelSupprimer.add(buttonConfirmer);

		buttonSupprimer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				c1.show(panelParent, "4");
			}
		});

		// ----------------------------- Confirmer la suppression ---------------

		buttonConfirmer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String rech = champRecherche.getText();
				String directeur = prenomDtext.getText();
				String pwd = String.valueOf(passwordText.getPassword());

				int index = moteurRechercheDirecteur(directeur, listeDirecteur);

				boolean estTrouve = false;

				if (pwd.equals(listeDirecteur.get(index).getPassword())) { //verifier le mdp

					if (estTrouve == false) { //si pas encore trouve la personne
						int indexClient = moteurRechercheClient(rech, listeClient);
						if (indexClient != -1) {
							estTrouve = true;
							listeClient.remove(indexClient);
						}
					}

					if (estTrouve == false) { //si pas encore trouve la personne
						int indexEmploye = moteurRechercheEmploye(rech, listeEmploye);
						if (indexEmploye != -1) {
							estTrouve = true;
							listeEmploye.remove(indexEmploye);
						}
					}

					if (estTrouve == false) { //si pas encore trouve la personne
						int indexDirecteur = moteurRechercheDirecteur(rech, listeDirecteur);
						if (indexDirecteur != -1) {
							estTrouve = true;
							listeDirecteur.remove(indexDirecteur);
						}
					}

					JOptionPane.showMessageDialog(message, rech + " a ete supprimee avec succes.");
				} else {
					JOptionPane.showMessageDialog(message, "Le mot de passe est errone.");
				}
			}
		});

		pack();
		setTitle("Page Personne");
		setSize(1300, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ferme fenetre si clique x
		setVisible(true);
	}

	// Methode pour chercher index de client
	public int moteurRechercheClient(String rech, ArrayList<Clients> liste) {
		int index = -1;

		for (int i = 0; i < liste.size(); i++) {
			if (rech.equals(liste.get(i).getPrenom()) || rech.equals(liste.get(i).getNom())) {
				index = i;
				return index;
			}
		}
		return index;

	}

	// Methode pour chercher index de employe
	public int moteurRechercheEmploye(String rech, ArrayList<Employe> liste) {
		int index = -1;

		for (int i = 0; i < liste.size(); i++) {
			if (rech.equals(liste.get(i).getPrenom()) || rech.equals(liste.get(i).getNom())) {
				index = i;
				return index;
			}
		}
		return index;

	}

	// Methode pour chercher index de directeur
	public int moteurRechercheDirecteur(String rech, ArrayList<Directeur> liste) {
		int index = -1;

		for (int i = 0; i < liste.size(); i++) {
			if (rech.equals(liste.get(i).getPrenom()) || rech.equals(liste.get(i).getNom())) {
				index = i;
				return index;
			}
		}
		return index;

	}
}
