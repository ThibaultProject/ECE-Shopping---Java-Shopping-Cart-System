package Vue;

import Modele.Categorie;
import Modele.Produits;

import javax.swing.*;
import java.util.Map;

public class Carte extends JPanel{
    private JPanel jpCartePanel;
    private JPanel jpContainer;
    private JLabel stockLabel;
    private JLabel promoLabel;
    private JLabel nomLabel;
    private JLabel categorieLabel;
    private JLabel prixLabel;
    private JLabel descriptionLabel;
    private JButton ajouterAuPanierButton;
    private JSpinner spinner1;

    public Carte(JPanel container,int idProduit){

        Produits produits1 = new Produits();
        produits1.recupProduitdeBdavecId(idProduit);
        // Recuperer les noms et id_categorie dans la Bd et affecte a mapCategorie
        Categorie categorie = new Categorie();
        categorie.recupCategoriedeBd();
        Map<String, Integer> mapCategorie = categorie.getMapCategorie();
        String nomCategorie="";
        for (Map.Entry<String, Integer> entry : mapCategorie.entrySet()) {
            if (entry.getValue().equals(produits1.getIdcategorie())) {
                nomCategorie = entry.getKey();
            }
        }

        // Mise à jour des élements de la carte
        stockLabel.setText(Integer.toString(produits1.getQuantite()));
        promoLabel.setText(Double.toString(produits1.getPromotion()));
        nomLabel.setText(produits1.getNom());
        categorieLabel.setText(nomCategorie);
        prixLabel.setText(Double.toString(produits1.getPrix()));
        descriptionLabel.setText(produits1.getDescription());


        container.add(jpCartePanel);
    }
}
