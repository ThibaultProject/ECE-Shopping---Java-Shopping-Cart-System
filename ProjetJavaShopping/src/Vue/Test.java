package Vue;

import Modele.Connexion;
import Modele.CreerCarte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test extends JFrame{
    private JPanel mainPanel;
    private JPanel sidePanel;
    private JPanel subPanel;
    private JButton button1;
    private JButton button2;

    public Test(){


        setTitle("Menu Admin");
        setContentPane(mainPanel);
        setMinimumSize(new Dimension(1300, 600)); // parfait
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel jpDossierClient = new JPanel();
        DossierClient client = new DossierClient(jpDossierClient);
        subPanel.add(jpDossierClient, BorderLayout.CENTER);
        subPanel.add(new CreerCarte(1));
/*
        // Création du CardLayout
        CardLayout cardLayout = new CardLayout();

        // Création des panels à afficher
        JPanel panel1 = new JPanel();
        Inventaire inv = new Inventaire(panel1);
        JPanel panel2 = new JPanel(new BorderLayout());
        AjouterProduit aj = new AjouterProduit(panel2);

        // Ajout des panels au panel principal
        JPanel cardPanel = new JPanel(cardLayout);
        cardPanel.add(panel1, "panel1");
        cardPanel.add(panel2, "panel2");

        // Ajout des éléments à la fenêtre
        subPanel.add(cardPanel, BorderLayout.CENTER);
 */

        //mainPanel.add(main,BorderLayout.CENTER);


        pack();
        setVisible(true);


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //cardLayout.show(cardPanel,"panel1");
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //cardLayout.show(cardPanel,"panel2");
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });
    }
}
