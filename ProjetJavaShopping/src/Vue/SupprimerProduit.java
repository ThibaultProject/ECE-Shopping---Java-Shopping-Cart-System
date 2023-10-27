package Vue;

import Modele.Categorie;
import Modele.Produits;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SupprimerProduit extends JPanel{
    private JPanel supprimeProduitPanel;
    private JComboBox cbCategorie;
    private JComboBox cbProduits;
    private JButton supprimerButton;
    private Map<String, List<String>> mapListeProduits;



    public SupprimerProduit(JPanel container) {


        // Recuperer les noms et id de categorie(Bd) et affecte a cbCategorie
        Categorie categorie = new Categorie();
        categorie.recupCategoriedeBd();
        Map<String, Integer> mapCategorie = categorie.getMapCategorie();
        Set<String> cles = mapCategorie.keySet();
        for (String cle : cles) {
            cbCategorie.addItem(cle);
        }

        // Initialiser la map(liste) de correspondance entre les categories et les produits
        mapListeProduits = new HashMap<>();
        for (Map.Entry<String, Integer> entry : mapCategorie.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            Produits produitslist = new Produits();
            produitslist.recupListeProduitsdeCategorieBd(value);
            mapListeProduits.put(key,produitslist.getListeProduits());
            System.out.println(produitslist.getListeProduits());
        }

        cbCategorie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer l'élément sélectionné dans la première JComboBox (cbCategorie)
                String categorieSelectedItem = (String) cbCategorie.getSelectedItem();

                // Mettre à jour la liste d'éléments de la deuxième JComboBox (cbProduits) en fonction de la sélection (cbCategorie)
                List<String> produitslist = mapListeProduits.get(categorieSelectedItem);
                DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
                for (String produit : produitslist) {
                    model.addElement(produit);
                }
                cbProduits.setModel(model);
            }
        });

        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                supprimerProduit();
            }
        });
        container.add(supprimeProduitPanel);
    }
    public void supprimerProduit(){
        Produits produits1 = new Produits();
        String categorie = (String) cbCategorie.getSelectedItem();
        String produit = (String) cbProduits.getSelectedItem();
        Categorie tablecategorie = new Categorie();
        tablecategorie.recupCategoriedeBd();
        Map<String, Integer> mapCategorie = tablecategorie.getMapCategorie();
        int idcategorie = mapCategorie.get(categorie);

        if (categorie.isEmpty() || produit==null || produit.isEmpty()  ) {
            JOptionPane.showMessageDialog(this,
                    "Veuillez renseigner tous les champs",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        produits1.deleteProduitversBd(produit,idcategorie);
        JOptionPane.showMessageDialog(this,
                "Opération effectuée avec succès",
                "Information",
                JOptionPane.INFORMATION_MESSAGE);

    }
}
