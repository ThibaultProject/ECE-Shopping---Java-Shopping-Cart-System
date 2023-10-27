package Vue;

import Controleur.InventaireTable;
import Modele.Categorie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Set;

public class Inventaire extends JPanel{
    private JTable jtProduits;
    private JPanel inventairePanel;
    private JComboBox cbCategorie;
    private JScrollPane jsPanel;
    public Inventaire(JPanel container){


        InventaireTable inv = new InventaireTable(jtProduits,0);
        jtProduits.getColumnModel().getColumn(3).setHeaderValue("Categorie");
        //jtProduits.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Recuperer les noms et id_categorie(Bd) et affecte a cbCategorie
        Categorie categorie = new Categorie();
        categorie.ajouter("Tout",0);
        categorie.recupCategoriedeBd();
        Map<String, Integer> mapCategorie = categorie.getMapCategorie();
        Set<String> cles = mapCategorie.keySet();
        for (String cle : cles) {
            cbCategorie.addItem(cle);
        }


        cbCategorie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupère l'élément sélectionné dans la première JComboBox (cbCategorie)
                String categorieSelectedItem = (String) cbCategorie.getSelectedItem();
                System.out.println(categorieSelectedItem);

                // Récupère l'idCategorie en fonction de la sélection
                int idCategorie = mapCategorie.get(categorieSelectedItem);

                // Mettre à jour la Table de produits en fonction de la sélection
                InventaireTable inv = new InventaireTable(jtProduits,idCategorie);
                jtProduits.getColumnModel().getColumn(3).setHeaderValue("Categorie");

            }
        });
        container.add(inventairePanel);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        jtProduits = new JTable();
        JScrollPane jsPanel = new JScrollPane(jtProduits);
        inventairePanel.add(jsPanel);

    }
}
