import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

//interface pour les produits
public class InterfaceGraph3 extends JFrame {

	JMenuBar menuBar = new JMenuBar();
	JMenu menu;
	JMenu menu1;
	JMenu menu2;
	private JMenuItem affichage = new JMenuItem("Affichage");
	private JMenuItem ajoutAliment = new JMenuItem("Aliment");
	private JMenuItem ajoutMeuble = new JMenuItem("Meuble");
	private JMenuItem gestionDossier = new JMenuItem("Gestion des dossiers");

	JPanel panelTable = new JPanel(); // Panel parent contien les tableau
	JPanel panelAjoutAliment = new JPanel(); // Panel pour ajout aliment
	JPanel panelAjoutMeuble = new JPanel(); // Panel pour ajout aliment
	CardLayout c1 = new CardLayout();

	final JTextField message = new JTextField(); // message pour dialog
	String nomAlimentTemp = ""; // pour stocker le nom d'aliment temporaire
	String typeMeubleTemp = ""; // pour stocker le nom de meuble temporaire

	ArrayList<Clients> listClient = new ArrayList<Clients>();
	ArrayList<Employe> listEmploye = new ArrayList<Employe>();
	ArrayList<Directeur> listDirecteur = new ArrayList<Directeur>();
	ArrayList<Aliment> listAliment = new ArrayList<Aliment>(); // pour circuler info
	ArrayList<Meuble> listMeuble = new ArrayList<Meuble>(); // pour circuler info

	public InterfaceGraph3(ArrayList<Clients> listeClient, ArrayList<Employe> listeEmploye,
			ArrayList<Directeur> listeDirecteur, ArrayList<Aliment> listeAliment, ArrayList<Meuble> listeMeuble) {

		listClient = listeClient;
		listEmploye = listeEmploye;
		listDirecteur = listeDirecteur;
		listAliment = listeAliment;
		listMeuble = listeMeuble;

		// ------------------ Menu ---------------
		menu = new JMenu("Accueil");
		menu.add(affichage);

		menu1 = new JMenu("Personne");
		menu1.add(gestionDossier);

		menu2 = new JMenu("Produit");
		menu2.add(ajoutAliment);
		menu2.add(ajoutMeuble);
		menuBar.add(menu);
		menuBar.add(menu1);
		menuBar.add(menu2);
		menuBar.setSize(500, 300);

		add(menuBar, BorderLayout.PAGE_START);

		panelTable.setLayout(c1);
		add(panelTable, BorderLayout.CENTER);

		// ----------------------Data des produits ------------
		// Data pour les aliments
		String[] entetesAliment = { "Nom", "Couleur", "Poids (lb)", "Ajoute par le directeur" };

		String[][] dataAliment = new String[listAliment.size()][];

		for (int i = 0; i < listAliment.size(); i++) {
			String[] tab = { listAliment.get(i).getNom(), listAliment.get(i).getCouleur(),
					"" + listAliment.get(i).getPoids(), listAliment.get(i).getDirecteur() };
			dataAliment[i] = tab;
		}

		// Data pour les meubles
		String[] entetesMeuble = { "Type", "Prix ($)", "Hauteur maximale (cm)", "Ajoute par le directeur" };

		String[][] dataMeuble = new String[listMeuble.size()][];

		for (int i = 0; i < listMeuble.size(); i++) {
			String[] tab = { listMeuble.get(i).getType(), "" + listMeuble.get(i).getPrix(),
					"" + listMeuble.get(i).getHauteurMax(), listMeuble.get(i).getDirecteur() };
			dataMeuble[i] = tab;
		}

		// Data directeur pour assigner ajout d'un produit
		String[] directeurList = new String[listeDirecteur.size()];
		for (int i = 0; i < listeDirecteur.size(); i++) {
			directeurList[i] = listeDirecteur.get(i).getPrenom();
		}

		// pour switch a l'interface d'information
		affichage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfaceGraph1 interface1 = new InterfaceGraph1(listClient, listEmploye, listDirecteur, listAliment,
						listMeuble);
				dispose();
			}
		});

		// pour switch a l'interface de personne
		gestionDossier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfaceGraph2 interface2 = new InterfaceGraph2(listClient, listEmploye, listDirecteur, listAliment,
						listMeuble);
				dispose();
			}
		});

		// ------------------------ Partie ajout --------
		// --Aliment
		panelAjoutAliment.setLayout(null);

		JLabel titre = new JLabel("ALIMENT");
		titre.setBounds(10, -30, 120, 120);
		panelAjoutAliment.add(titre);

		JLabel nomAliment = new JLabel("Nom");
		nomAliment.setBounds(10, 40, 80, 25);
		panelAjoutAliment.add(nomAliment);

		JTextField nomAlimentText = new JTextField(20);
		nomAlimentText.setBounds(140, 40, 170, 25);
		panelAjoutAliment.add(nomAlimentText);

		JLabel couleur = new JLabel("Couleur");
		couleur.setBounds(10, 70, 80, 25);
		panelAjoutAliment.add(couleur);

		JTextField couleurText = new JTextField(20);
		couleurText.setBounds(140, 70, 170, 25);
		panelAjoutAliment.add(couleurText);

		JLabel poids = new JLabel("Poids");
		poids.setBounds(10, 100, 165, 25);
		panelAjoutAliment.add(poids);

		JTextField poidsText = new JTextField("0");
		poidsText.setBounds(140, 100, 170, 25);
		panelAjoutAliment.add(poidsText);

		JLabel directeur = new JLabel("Ajoute par directeur");
		directeur.setBounds(10, 130, 165, 25);
		panelAjoutAliment.add(directeur);

		JComboBox list = new JComboBox(directeurList);
		list.setBounds(140, 130, 170, 25);
		panelAjoutAliment.add(list);

		JButton buttonAjouterAliment = new JButton("Ajouter");
		buttonAjouterAliment.setBounds(140, 160, 80, 25);
		panelAjoutAliment.add(buttonAjouterAliment);

		JButton buttonModifierAliment = new JButton("Modifier");
		buttonModifierAliment.setBounds(230, 160, 80, 25);
		panelAjoutAliment.add(buttonModifierAliment);

		JButton buttonSupprimerAliment = new JButton("Supprimer");
		buttonSupprimerAliment.setBounds(320, 160, 100, 25);
		panelAjoutAliment.add(buttonSupprimerAliment);

		// Model de tableau pour aliment
		DefaultTableModel tableModelAliment;
		if (listeAliment.size() == 0) {
			tableModelAliment = new DefaultTableModel(entetesAliment, 0);
		} else {
			tableModelAliment = new DefaultTableModel(dataAliment, entetesAliment);
		}
		JTable tableauAliment = new JTable(tableModelAliment);
		JScrollPane spAliment = new JScrollPane(tableauAliment);
		spAliment.setBounds(10, 220, 600, 300);
		panelAjoutAliment.add(spAliment);

		panelTable.add(panelAjoutAliment, "1");

		// pour les messageDialog de mot de pass (pour Aliment et Meuble)
		JPanel panelPwd = new JPanel();
		JLabel labelPwd = new JLabel("Enter a password :");
		JPasswordField pass = new JPasswordField(10);
		panelPwd.add(labelPwd);
		panelPwd.add(pass);
		String[] options = new String[] { "OK", "Cancel" };

		// pour afficher le panel Aliment
		ajoutAliment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				titre.setText("ALIMENT");
				nomAlimentText.setText("");
				couleurText.setText("");
				poidsText.setText("0");
				c1.show(panelTable, "1");
			}
		});

		// --Les actions-----
		// pour ajouter un aliment et faire apparaire sur le tableau
		buttonAjouterAliment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nomAlimentText.getText().equals("") || couleurText.getText().equals("")
						|| poidsText.getText().equals("")) { // require remplir tous les champs

					JOptionPane.showMessageDialog(message, "Entrez tous les infos svp!");

				} else {

					int option = JOptionPane.showOptionDialog(null, panelPwd,
							"Autorisation de " + list.getSelectedItem().toString(), JOptionPane.NO_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, options, options[1]);

					if (option == 0) {// pressing OK button

						String nomAliment = nomAlimentText.getText();
						String couleurAliment = couleurText.getText();
						String poidsAlimentTexte = poidsText.getText(); // pour le tableau de l'interface
						float poidsAliment = Float.parseFloat(poidsText.getText()); // pour class Aliment
						String pwd = String.valueOf(pass.getPassword()); // pwd entre par utilisateur
						String directeurAliment = list.getSelectedItem().toString(); // prenom de Directeur qui autorise

						int indexDirecteur = moteurRechercheDirecteur(directeurAliment, listDirecteur);

						System.out.println("Mot de pass de " + listDirecteur.get(indexDirecteur).prenom + " : "
								+ listDirecteur.get(indexDirecteur).getPassword()); // juste au cas ou on oublie le mdp
																					// quand on teste :)

						if (poidsAliment >= 0) {
							if (pwd.equals(listDirecteur.get(indexDirecteur).getPassword())) {

								Aliment aliment = new Aliment(nomAliment, couleurAliment, poidsAliment,
										directeurAliment);
								listAliment.add(aliment);

								String[] data = { nomAliment, couleurAliment, poidsAlimentTexte, directeurAliment };
								tableModelAliment.addRow(data);

								JOptionPane.showMessageDialog(message, "Produit ajoute avec succes !");

								nomAlimentText.setText("");
								couleurText.setText("");
								poidsText.setText("0");

							} else {

								JOptionPane.showMessageDialog(message, "Mot de passe incorrect !");
							}
						} else { // si utilisateur ecrit un nombre de poids negatif

							JOptionPane.showMessageDialog(message, "Veuillez entrer un poids positif !");
						}
					}
				}
			}
		});

		// pour selectionner 1 ligne du tableau aliment
		tableauAliment.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				String nomAliment = tableModelAliment.getValueAt(tableauAliment.getSelectedRow(), 0).toString();
				String couleurAliment = tableModelAliment.getValueAt(tableauAliment.getSelectedRow(), 1).toString();
				String poidsAliment = tableModelAliment.getValueAt(tableauAliment.getSelectedRow(), 2).toString();

				nomAlimentText.setText(nomAliment);
				couleurText.setText(couleurAliment);
				poidsText.setText(poidsAliment);

				nomAlimentTemp = nomAliment;
			}
		});

		// pour modifier info d'un aliment et aussi modifier le tableau d'aliment
		buttonModifierAliment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableauAliment.getSelectedRowCount() == 1) {
					// if single row is selected

					int option = JOptionPane.showOptionDialog(null, panelPwd,
							"Autorisation de " + list.getSelectedItem().toString(), JOptionPane.NO_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, options, options[1]);

					if (option == 0) { // pressing OK button

						String pwd = String.valueOf(pass.getPassword());
						String nomAliment = nomAlimentText.getText();
						String couleurAliment = couleurText.getText();
						String poidsAlimentTexte = poidsText.getText(); // pour le tableau de l'interface
						float poidsAliment = Float.parseFloat(poidsText.getText()); // pour class Aliment
						String directeurAliment = list.getSelectedItem().toString();

						int indexDirecteur = moteurRechercheDirecteur(directeurAliment, listDirecteur);
						System.out.println("Mot de pass de " + listDirecteur.get(indexDirecteur).prenom + " : "
								+ listDirecteur.get(indexDirecteur).getPassword()); // sert print le mpd si on oublie
																					// quand on test l'interface :)

						if (poidsAliment >= 0) {

							if (pwd.equals(listDirecteur.get(indexDirecteur).getPassword())) { // pour verifier le mdp

								int indexAliment = moteurRechercheAliment(nomAliment, listAliment);

								if (indexAliment != -1) {

									listAliment.get(indexAliment).setNom(nomAliment);
									listAliment.get(indexAliment).setCouleur(couleurAliment);
									listAliment.get(indexAliment).setPoids(poidsAliment);
									listAliment.get(indexAliment).setDirecteur(directeurAliment);

								}

								tableauAliment.setValueAt(nomAliment, tableauAliment.getSelectedRow(), 0);
								tableauAliment.setValueAt(couleurAliment, tableauAliment.getSelectedRow(), 1);
								tableauAliment.setValueAt(poidsAlimentTexte, tableauAliment.getSelectedRow(), 2);
								tableauAliment.setValueAt(directeurAliment, tableauAliment.getSelectedRow(), 3);

								JOptionPane.showMessageDialog(message, "Update avec succes !");
							} else {
								JOptionPane.showMessageDialog(message, "Mot de passe incorrect !");
							}
						} else { // si utilisateur ecrit un nombre de poids negatif
							JOptionPane.showMessageDialog(message, "Veuillez entrer un poids positif !");
						}
					}
				} else if (tableauAliment.getRowCount() == 0) {

					JOptionPane.showMessageDialog(message, "Tableau est vide !");

				} else {

					JOptionPane.showMessageDialog(message, "Veuillez selectionner une seule ligne !");
				}
			}
		});

		// pour supprimer un aliment et enleve du tableau aliment
		buttonSupprimerAliment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableauAliment.getSelectedRowCount() == 1) {
					// if single row is selected

					int option = JOptionPane.showOptionDialog(null, panelPwd,
							"Autorisation de " + list.getSelectedItem().toString(), JOptionPane.NO_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, options, options[1]);

					if (option == 0) { // pressing OK button

						String pwd = String.valueOf(pass.getPassword());
						String directeurAliment = list.getSelectedItem().toString();
						int indexDirecteur = moteurRechercheDirecteur(directeurAliment, listDirecteur);

						System.out.println("Mot de pass de " + listDirecteur.get(indexDirecteur).prenom + " : "
								+ listDirecteur.get(indexDirecteur).getPassword()); // on laisse ce ligne au cas ou on
																					// oublie le mdp pendant le test

						if (pwd.equals(listDirecteur.get(indexDirecteur).getPassword())) {

							String nomAliment = nomAlimentText.getText();

							int indexAliment = moteurRechercheAliment(nomAliment, listAliment);

							if (indexAliment != -1) {
								listAliment.remove(indexAliment);

							}

							tableModelAliment.removeRow(tableauAliment.getSelectedRow());

							JOptionPane.showMessageDialog(message, "Supprime avec succes !");
						} else {
							JOptionPane.showMessageDialog(message, "Mot de pass incorrect !");
						}
					}
				} else if (tableauAliment.getRowCount() == 0) {

					JOptionPane.showMessageDialog(message, "Tableau est vide !");

				} else {
					JOptionPane.showMessageDialog(message, "Veuillez selectionner une seule ligne !");
				}
			}
		});

		// -----------Meuble-----------------------
		panelAjoutMeuble.setLayout(null);

		JLabel titre1 = new JLabel("MEUBLE");
		titre1.setBounds(10, -30, 120, 120);
		panelAjoutMeuble.add(titre1);

		JLabel typeMeuble = new JLabel("Type");
		typeMeuble.setBounds(10, 40, 80, 25);
		panelAjoutMeuble.add(typeMeuble);

		JTextField typeMeubleText = new JTextField(20);
		typeMeubleText.setBounds(140, 40, 170, 25);
		panelAjoutMeuble.add(typeMeubleText);

		JLabel prix = new JLabel("Prix");
		prix.setBounds(10, 70, 80, 25);
		panelAjoutMeuble.add(prix);

		JTextField prixText = new JTextField("0");
		prixText.setBounds(140, 70, 170, 25);
		panelAjoutMeuble.add(prixText);

		JLabel hauteur = new JLabel("Hauteur max");
		hauteur.setBounds(10, 100, 165, 25);
		panelAjoutMeuble.add(hauteur);

		JTextField hauteurText = new JTextField("0");
		hauteurText.setBounds(140, 100, 170, 25);
		panelAjoutMeuble.add(hauteurText);

		JLabel directeur1 = new JLabel("Ajoute par directeur");
		directeur1.setBounds(10, 130, 165, 25);
		panelAjoutMeuble.add(directeur1);

		JComboBox list1 = new JComboBox(directeurList);
		list1.setBounds(140, 130, 170, 25);
		panelAjoutMeuble.add(list1);

		JButton buttonAjouterMeuble = new JButton("Ajouter");
		buttonAjouterMeuble.setBounds(140, 160, 80, 25);
		panelAjoutMeuble.add(buttonAjouterMeuble);

		JButton buttonModifierMeuble = new JButton("Modifier");
		buttonModifierMeuble.setBounds(230, 160, 80, 25);
		panelAjoutMeuble.add(buttonModifierMeuble);

		JButton buttonSupprimerMeuble = new JButton("Supprimer");
		buttonSupprimerMeuble.setBounds(320, 160, 100, 25);
		panelAjoutMeuble.add(buttonSupprimerMeuble);

		DefaultTableModel tableModel2;
		if (listeMeuble.size() == 0) {
			tableModel2 = new DefaultTableModel(entetesMeuble, 0);
		} else {
			tableModel2 = new DefaultTableModel(dataMeuble, entetesMeuble);
		}

		JTable tableauMeuble = new JTable(tableModel2);
		JScrollPane sp4 = new JScrollPane(tableauMeuble);
		sp4.setBounds(10, 220, 600, 300);
		panelAjoutMeuble.add(sp4);
		panelTable.add(panelAjoutMeuble, "2");

		// pour afficher le panel de Meuble
		ajoutMeuble.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				typeMeubleText.setText("");
				prixText.setText("0");
				hauteurText.setText("0");
				c1.show(panelTable, "2");
			}
		});

		// pour creer un nouveau meuble et ajouter dans le tableau des meubles
		buttonAjouterMeuble.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (typeMeubleText.getText().equals("") || prixText.getText().equals("")
						|| hauteurText.getText().equals("")) {

					JOptionPane.showMessageDialog(message, "Entrez tous les infos svp!");

				} else {

					int option = JOptionPane.showOptionDialog(null, panelPwd,
							"Autorisation de " + list1.getSelectedItem().toString(), JOptionPane.NO_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, options, options[1]);

					if (option == 0) { // pressing OK button

						String type = typeMeubleText.getText();
						String prixTexte = prixText.getText();
						String hauteurTexte = hauteurText.getText();
						float prix = Float.parseFloat(prixText.getText());
						float hauteur = Float.parseFloat(hauteurText.getText());
						String directeur = list1.getSelectedItem().toString();
						String pwd = String.valueOf(pass.getPassword()); // le mdp entre par utilisateur
						int indexDirecteur = moteurRechercheDirecteur(directeur, listDirecteur);

						System.out.println("Mot de pass de " + listDirecteur.get(indexDirecteur).prenom + " : "
								+ listDirecteur.get(indexDirecteur).getPassword()); // on laisse ce ligne au cas ou on oublie le mdp pendant le test

						if (prix >= 0 && hauteur >= 0) {
							if (pwd.equals(listDirecteur.get(indexDirecteur).getPassword())) { // verifier le mdp

								Meuble meuble = new Meuble(type, prix, hauteur, directeur);
								listMeuble.add(meuble);

								String[] data = { type, prixTexte, hauteurTexte, directeur };
								tableModel2.addRow(data);

								JOptionPane.showMessageDialog(message, "Produit ajoute avec succes !");

								typeMeubleText.setText("");
								prixText.setText("0");
								hauteurText.setText("0");

							} else {
								JOptionPane.showMessageDialog(message, "Mot de passe incorrect !");
							}
						} else {
							JOptionPane.showMessageDialog(message, "Veuillez entrer le prix ou hauteur positif !");
						}

					}
				}
			}
		});

		// pour selectionner 1 ligne du tableau
		tableauMeuble.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				String nomMeuble = tableModel2.getValueAt(tableauMeuble.getSelectedRow(), 0).toString();
				String prix = tableModel2.getValueAt(tableauMeuble.getSelectedRow(), 1).toString();
				String hauteur = tableModel2.getValueAt(tableauMeuble.getSelectedRow(), 2).toString();

				typeMeubleText.setText(nomMeuble);
				prixText.setText(prix);
				hauteurText.setText(hauteur);

				typeMeubleTemp = nomMeuble;
			}
		});

		// pour modifier info d'un meuble et modifier ausis le tableau des meubles dans
		// interface
		buttonModifierMeuble.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tableauMeuble.getSelectedRowCount() == 1) {
					// if single row is selected

					int option = JOptionPane.showOptionDialog(null, panelPwd,
							"Autorisation de " + list1.getSelectedItem().toString(), JOptionPane.NO_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, options, options[1]);

					if (option == 0) { // pressing OK button

						String pwd = String.valueOf(pass.getPassword());
						String type = typeMeubleText.getText();
						String prixTexte = prixText.getText(); // pour tableau
						String hauteurTexte = hauteurText.getText(); // pour tableau
						float prix = Float.parseFloat(prixText.getText()); // pour class Meuble
						float hauteur = Float.parseFloat(hauteurText.getText()); // pour class Meuble
						String directeur = list1.getSelectedItem().toString();

						int indexDirecteur = moteurRechercheDirecteur(directeur, listDirecteur);
						System.out.println("Mot de pass de " + listDirecteur.get(indexDirecteur).prenom + " : "
								+ listDirecteur.get(indexDirecteur).getPassword()); // on laisse ce ligne au cas ou on
																					// oublie le mdp pendant le test

						if (prix >= 0 && hauteur >= 0) {
							if (pwd.equals(listDirecteur.get(indexDirecteur).getPassword())) {

								int indexMeuble = moteurRechercheMeuble(type, listMeuble);
								if (indexMeuble != -1) {

									listMeuble.get(indexMeuble).setType(type);
									listMeuble.get(indexMeuble).setPrix(prix);
									listMeuble.get(indexMeuble).setHauteurMax(hauteur);
									listMeuble.get(indexMeuble).setDirecteur(directeur);

								}

								// modifie le tableau des meubles de l'interface
								tableauMeuble.setValueAt(type, tableauMeuble.getSelectedRow(), 0);
								tableauMeuble.setValueAt(prixTexte, tableauMeuble.getSelectedRow(), 1);
								tableauMeuble.setValueAt(hauteurTexte, tableauMeuble.getSelectedRow(), 2);
								tableauMeuble.setValueAt(directeur, tableauMeuble.getSelectedRow(), 3);

								JOptionPane.showMessageDialog(message, "Update avec succes !");
							} else {
								JOptionPane.showMessageDialog(message, "Mot de pass errone !");
							}
						} else {
							JOptionPane.showMessageDialog(message, "Veuillez entrer un prix ou hauteur positif !");
						}
					}
				} else if (tableauMeuble.getRowCount() == 0) {
					JOptionPane.showMessageDialog(message, "Tableau est vide !");
				} else {
					JOptionPane.showMessageDialog(message, "Veuillez selectionner une seule ligne !");
				}
			}
		});

		// pour supprimer le meuble selectionne et l'enlever du tableau de l'interface
		buttonSupprimerMeuble.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (tableauMeuble.getSelectedRowCount() == 1) {
					// if single row is selected

					int option = JOptionPane.showOptionDialog(null, panelPwd,
							"Autorisation de " + list1.getSelectedItem().toString(), JOptionPane.NO_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, options, options[1]);

					if (option == 0) { // pressing OK button

						String pwd = String.valueOf(pass.getPassword());
						String directeur = list1.getSelectedItem().toString();
						int indexDirecteur = moteurRechercheDirecteur(directeur, listDirecteur);
						System.out.println("Mot de pass de " + listDirecteur.get(indexDirecteur).prenom + " : "
								+ listDirecteur.get(indexDirecteur).getPassword()); // on laisse ce ligne au cas ou on
																					// oublie le mdp pendant le test

						if (pwd.equals(listDirecteur.get(indexDirecteur).getPassword())) {

							String type = typeMeubleText.getText();
							int indexMeuble = moteurRechercheMeuble(type, listMeuble);

							if (indexMeuble != -1) {

								listMeuble.remove(indexMeuble);
							}

							tableModel2.removeRow(tableauMeuble.getSelectedRow());

							JOptionPane.showMessageDialog(message, "supprime avec succes !");

						} else {

							JOptionPane.showMessageDialog(message, "Mot de pass errone !");
						}
					}
				} else if (tableauMeuble.getRowCount() == 0) {

					JOptionPane.showMessageDialog(message, "Tableau est vide !");

				} else {

					JOptionPane.showMessageDialog(message, "Veuillez selectionner une seule ligne !");
				}
			}
		});
		// ----------

		pack();
		setTitle("Page Produits");
		setSize(1300, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ferme fenetre si clique x
		setVisible(true);

	}

	// pour chercher index de aliment
	public int moteurRechercheAliment(String aliment, ArrayList<Aliment> liste) {
		int index = -1;

		for (int i = 0; i < liste.size(); i++) {
			if (aliment.equals(liste.get(i).getNom())) {
				index = i;
				return index;
			}
		}
		return index;

	}

	// pour chercher index du meuble
	public int moteurRechercheMeuble(String meuble, ArrayList<Meuble> liste) {
		int index = -1;

		for (int i = 0; i < liste.size(); i++) {
			if (meuble.equals(liste.get(i).getType())) {
				index = i;
				return index;
			}
		}
		return index;

	}

	// pour chercher index du directeur
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
