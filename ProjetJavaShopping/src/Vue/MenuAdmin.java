package Vue;

import Modele.CamembertGraphModele;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuAdmin extends JFrame{
    private JPanel panelInventaire;
    private JPanel sidepanelDossierClient;
    private JPanel sidepanelDahboard;
    private JPanel sidepanelGestionProduit;
    private JPanel sidepanelLogout;
    private JPanel panelMenuAdmin;
    private JCheckBox checkBoxAjouter;
    private JPanel sideSupprimer;
    private JPanel sidepanelModifier;
    private JPanel sidepanelAjouter;
    private JPanel jpMainPanel;
    private JPanel jpSidePanel;
    private JCheckBox checkBoxModifier;
    private JCheckBox checkBoxSupprimer;


    public MenuAdmin() {

        setTitle("Menu Admin");
        setContentPane(panelMenuAdmin);
        setMinimumSize(new Dimension(1300, 650)); // parfait
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Création du jPanel Container (cardPanelContainer) et CardLayout
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanelContainer = new JPanel(cardLayout);

        // Création des panels à afficher dans jPanel Container (cardPanelContainer)

        // Inventaire
        JPanel jpInventaire = new JPanel(new BorderLayout());
        Inventaire inventaire = new Inventaire(jpInventaire);
        // DossierClient
        JPanel jpDossierClient = new JPanel(new BorderLayout());
        DossierClient client = new DossierClient(jpDossierClient);
        // Ajouter
        JPanel jpAjouter = new JPanel(new BorderLayout());
        AjouterProduit ajouter = new AjouterProduit(jpAjouter);
        // Modifier
        JPanel jpModifier = new JPanel(new BorderLayout());
        UpdateProduit update = new UpdateProduit(jpModifier);
        // Supprimer
        JPanel jpSupprimer = new JPanel(new BorderLayout());
        SupprimerProduit supprimer = new SupprimerProduit(jpSupprimer);
        // Graph
        JPanel jpGraph = new JPanel(new BorderLayout());
        //jpGraph.setPreferredSize(new Dimension(500, 500));
        Graph graph = new Graph(jpGraph);

        // Ajout des panels dans jPanel Container (cardPanelContainer)
        cardPanelContainer.add(jpInventaire, "jpInventaire");
        cardPanelContainer.add(jpDossierClient, "jpDossierClient");
        cardPanelContainer.add(jpAjouter, "jpAjouter");
        cardPanelContainer.add(jpModifier, "jpModifier");
        cardPanelContainer.add(jpSupprimer, "jpSupprimer");
        cardPanelContainer.add(jpGraph, "jpGraph");

        // Ajout des éléments dans mainPanel
        jpMainPanel.add(cardPanelContainer, BorderLayout.CENTER);
        cardLayout.show(cardPanelContainer, "jpInventaire");
        pack();
        setVisible(true);


        checkBoxAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBoxModifier.setSelected(false);
                checkBoxSupprimer.setSelected(false);
                cardLayout.show(cardPanelContainer, "jpAjouter");
            }
        });
        checkBoxModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBoxAjouter.setSelected(false);
                checkBoxSupprimer.setSelected(false);
                cardLayout.show(cardPanelContainer, "jpModifier");
            }
        });
        checkBoxSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBoxModifier.setSelected(false);
                checkBoxAjouter.setSelected(false);
                cardLayout.show(cardPanelContainer, "jpSupprimer");

            }
        });
        panelInventaire.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                checkBoxAjouter.setSelected(false);
                checkBoxModifier.setSelected(false);
                checkBoxSupprimer.setSelected(false);
                cardLayout.show(cardPanelContainer, "jpInventaire");
            }
        });
        sidepanelDahboard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                checkBoxAjouter.setSelected(false);
                checkBoxModifier.setSelected(false);
                checkBoxSupprimer.setSelected(false);
                cardLayout.show(cardPanelContainer, "jpGraph");
            }
        });
        sidepanelDossierClient.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                checkBoxAjouter.setSelected(false);
                checkBoxModifier.setSelected(false);
                checkBoxSupprimer.setSelected(false);
                cardLayout.show(cardPanelContainer, "jpDossierClient");
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


}
