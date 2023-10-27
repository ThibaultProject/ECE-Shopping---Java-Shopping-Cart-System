package Vue;

import Modele.Categorie;
import Modele.Produits;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UpdateProduit extends JPanel{
    private JPanel updateProduitPanel;
    private JComboBox cbCategorie;
    private JComboBox cbProduits;
    private JTextField tfPrix;
    private JTextArea taDescription;
    private JTextField tfQuantite;
    private JButton updateButton;
    private JTextField tfPromotion;
    private Map<String, List<String>> mapListeProduits;

    public UpdateProduit(JPanel container){


        // Recuperer les noms et id de categorie(Bd) et affecte a cbCategorie
        Categorie categorie = new Categorie();
        categorie.recupCategoriedeBd();
        Map<String, Integer> mapCategorie = categorie.getMapCategorie();
        Set<String> cles = mapCategorie.keySet();
        for (String cle : cles) {
            cbCategorie.addItem(cle);
        }

        // Initialiser la map (liste) de correspondance entre les categories et les produits
        mapListeProduits = new HashMap<>();
        for (Map.Entry<String, Integer> entry : mapCategorie.entrySet()) {
            String nomCategorie = entry.getKey();
            Integer idCategorie = entry.getValue();
            Produits produitslist = new Produits();
            produitslist.recupListeProduitsdeCategorieBd(idCategorie);
            mapListeProduits.put(nomCategorie,produitslist.getListeProduits());
            System.out.println(produitslist.getListeProduits());
        }


        cbCategorie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupère l'élément sélectionné dans la première JComboBox (cbCatgorie)
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
        cbProduits.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupèrer l'élément sélectionné dans la JComboBox (cbProduits)
                String categorieSelectedItem = (String) cbCategorie.getSelectedItem();
                Categorie categorie = new Categorie();
                categorie.recupCategoriedeBd();
                Integer id_categorie = categorie.getMapCategorie().get(categorieSelectedItem);

                String produitSelectedItem = (String) cbProduits.getSelectedItem();
                Produits produits1 = new Produits();
                produits1.setNom(produitSelectedItem);
                produits1.setIdcategorie(id_categorie);
                produits1.recupProduitdeBd();

                // Mise à jour des élements du produits
                tfPrix.setText(Double.toString(produits1.getPrix()));
                tfQuantite.setText(Integer.toString(produits1.getQuantite()));
                taDescription.setText(produits1.getDescription());
                tfPromotion.setText(Double.toString(produits1.getPromotion()));
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                updateProduit();
            }
        });
        container.add(updateProduitPanel);
        //setVisible(true);
    }
    public void updateProduit(){

        String categorie = (String) cbCategorie.getSelectedItem();
        String produit = (String) cbProduits.getSelectedItem();
        String prix = tfPrix.getText();
        String quantite = tfQuantite.getText();
        String description = taDescription.getText();
        String promotion = tfPromotion.getText();
        Categorie tablecategorie = new Categorie();
        tablecategorie.recupCategoriedeBd();
        Map<String, Integer> mapCategorie = tablecategorie.getMapCategorie();
        int idcategorie = mapCategorie.get(categorie);

        if (categorie.isEmpty() || produit==null || produit.isEmpty() || prix.isEmpty() || quantite.isEmpty() || promotion.isEmpty() || description.isEmpty() ) {
            JOptionPane.showMessageDialog(this,
                    "Veuillez renseigner tous les champs",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        double doubleprix=0;
        double doublepromotion=0;
        int intquantite = Integer.parseInt(quantite);
        try {
            doubleprix = Double.parseDouble(prix);
            doublepromotion = Double.parseDouble(promotion);
        } catch (NumberFormatException e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        Produits produits1 = new Produits(produit,doubleprix,intquantite,description,idcategorie);
        produits1.setPromotion(doublepromotion);
        produits1.updateProduitversBd();
        JOptionPane.showMessageDialog(this,
                "Opération effectuée avec succès",
                "Information",
                JOptionPane.INFORMATION_MESSAGE);
    }

}
