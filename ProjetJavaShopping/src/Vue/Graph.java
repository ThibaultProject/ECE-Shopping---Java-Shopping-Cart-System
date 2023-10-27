package Vue;

import Controleur.InventaireTable;
import Modele.CamembertGraphModele;
import Modele.Categorie;
import Modele.HistogrammeModele;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Set;

public class Graph {
    private JPanel jpGraph;
    private JPanel jpCamembert;
    private JPanel jpHistogramme;
    private JComboBox cbCategorie;
    private JPanel jpContainer;

    public Graph(JPanel container){

        CamembertGraphModele camembert = new CamembertGraphModele(0,jpCamembert);
        HistogrammeModele histogramme = new HistogrammeModele(0,jpHistogramme);

        // Recuperer les noms et id_categorie(Bd) et affecte a cbCategorie
        Categorie categorie = new Categorie();
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

                // Récupère l'idCategorie en fonction de la sélection
                int idCategorie = mapCategorie.get(categorieSelectedItem);

                // Mettre à jour la Table de produits en fonction de la sélection
                // Suppression du contenu actuel du JPanel
                jpCamembert.removeAll();
                jpHistogramme.removeAll();

                // Modifie
                CamembertGraphModele camembert = new CamembertGraphModele(idCategorie,jpCamembert);
                HistogrammeModele histogramme = new HistogrammeModele(idCategorie,jpHistogramme);

                // Actualisation de l'interface graphique
                jpCamembert.revalidate();
                jpCamembert.repaint();
                jpHistogramme.revalidate();
                jpHistogramme.repaint();
            }
        });
        container.add(jpGraph);
    }
}
