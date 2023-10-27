package Vue;

import Controleur.ClientTable;
import Modele.Client;
import Modele.Connexion;
import Modele.CreerCarte;
import Modele.MenuModele;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class MenuClient extends JFrame{
    private int idClient;
    private JPanel jpMenuClientPanel;
    private JPanel jpMainPanel;
    private JPanel jpContainerPanel;
    private JPanel jpSearchPanel;
    private JPanel jpSidePanel;
    private JPanel sidepanelHome;
    private JPanel sidepanelPromo;
    private JPanel sidepanelPanier;
    private JPanel sidepanelCommande;
    private JPanel sidepanelLogout;
    private JPanel sidepanelFilm;
    private JCheckBox checkBoxFilm;
    private JPanel sidepanelLivre;
    private JCheckBox checkBoxLivre;
    private JPanel sidePanelVetement;
    private JCheckBox checkBoxVetement;
    private JPanel sidepanelVinyle;
    private JCheckBox checkBoxVinyle;
    private JTextField tfSearch;



    public MenuClient(int idClient){
        this.idClient = idClient;

        setTitle("Menu Client");
        setContentPane(jpMenuClientPanel);
        setMinimumSize(new Dimension(1300, 650)); // parfait
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        tfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchProducts(tfSearch.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchProducts(tfSearch.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchProducts(tfSearch.getText());
            }
        });


        setVisible(true);
        sidepanelHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                checkBoxFilm.setSelected(false);
                checkBoxLivre.setSelected(false);
                checkBoxVetement.setSelected(false);
                checkBoxVinyle.setSelected(false);
                jpContainerPanel.removeAll();
                MenuModele modele = new MenuModele(jpContainerPanel);
                modele.displayAllProducts();
                jpContainerPanel.revalidate();
                jpContainerPanel.repaint();
            }
        });
        checkBoxFilm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchCategorieProducts("Film");
                checkBoxLivre.setSelected(false);
                checkBoxVetement.setSelected(false);
                checkBoxVinyle.setSelected(false);
            }
        });
        checkBoxLivre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchCategorieProducts("Livre");
                checkBoxFilm.setSelected(false);
                checkBoxVetement.setSelected(false);
                checkBoxVinyle.setSelected(false);
            }
        });
        checkBoxVetement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchCategorieProducts("Vetement");
                checkBoxLivre.setSelected(false);
                checkBoxFilm.setSelected(false);
                checkBoxVinyle.setSelected(false);
            }
        });
        checkBoxVinyle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchCategorieProducts("Vinyle");
                checkBoxLivre.setSelected(false);
                checkBoxVetement.setSelected(false);
                checkBoxFilm.setSelected(false);
            }
        });
        sidepanelCommande.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                checkBoxFilm.setSelected(false);
                checkBoxLivre.setSelected(false);
                checkBoxVetement.setSelected(false);
                checkBoxVinyle.setSelected(false);
                jpContainerPanel.removeAll();

                // Ajout de la table commande

                JTable tableCommande = new JTable();
                ClientTable client = new ClientTable(tableCommande,idClient);
                tableCommande.getColumnModel().getColumn(3).setHeaderValue("Categorie");
                JScrollPane scrollPane = new JScrollPane(tableCommande);
                jpContainerPanel.add(scrollPane);

                jpContainerPanel.revalidate();
                jpContainerPanel.repaint();
            }
        });
        sidepanelLogout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Accueil accueil = new Accueil();
                accueil.setVisible(true);
                dispose();
            }
        });
    }

    private void searchProducts(String searchTerm) {
        jpContainerPanel.removeAll();

        if (searchTerm.isEmpty()) {
            MenuModele modele = new MenuModele(jpContainerPanel);
            modele.displayAllProducts();
        } else {
            MenuModele modele = new MenuModele(jpContainerPanel);
            modele.displaySearchProducts(searchTerm);
        }
        jpContainerPanel.revalidate();
        jpContainerPanel.repaint();

    }
    public void searchCategorieProducts(String nomCategorie){
        jpContainerPanel.removeAll();

        MenuModele modele = new MenuModele(jpContainerPanel);
        modele.displayCategorieProducts(nomCategorie);

        jpContainerPanel.revalidate();
        jpContainerPanel.repaint();
    }
























    private void displayProducts() {
        try {
            Connexion connexionBd = new Connexion("shop", "root", "");

            PreparedStatement stmt = connexionBd.getConn().prepareStatement("SELECT * FROM produits");
            ResultSet resultSet = stmt.executeQuery();

            JPanel nouveau = new JPanel();
            nouveau.setLayout(new FlowLayout(FlowLayout.LEFT));
            nouveau.setPreferredSize(new Dimension(450, 400));
            JScrollPane scrollPane = new JScrollPane(nouveau);

            while (resultSet.next()) {
                int productId = resultSet.getInt("id");
                //nouveau.add(new CreerCarte(productId));

                Carte a = new Carte(nouveau,productId);
                //nouveau.add(new Carte());
            }
            //jp.add(nouveau);
            //JScrollPane scrollPane = new JScrollPane(nouveau);
            scrollPane.revalidate();
            scrollPane.repaint();
            jpContainerPanel.add(scrollPane);
            resultSet.close();
            stmt.close();
            connexionBd.closeConnexion();

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        jpContainerPanel.revalidate();
        jpContainerPanel.repaint();
    }
}
