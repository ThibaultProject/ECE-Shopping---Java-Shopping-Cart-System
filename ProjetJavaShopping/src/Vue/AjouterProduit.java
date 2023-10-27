package Vue;

import Controleur.DoubleFiltre;
import Controleur.IntFiltre;
import Modele.Categorie;
import Modele.Produits;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Set;

public class AjouterProduit extends JPanel{
    private JPanel ajouterProduitPanel;
    private JTextField tfNom;
    private JTextArea taDescription;
    private JTextField tfQuantite;
    private JTextField tfPrix;
    private JComboBox cbCategorie;
    private JButton ajouterButton;
    private JTextField tfPromotion;


    public AjouterProduit(JPanel container) {



        // Recuperer les noms et id de categorie(Bd) et affecte a cbCategorie
        Categorie categorie = new Categorie();
        categorie.recupCategoriedeBd();
        Map<String, Integer> mapCategorie = categorie.getMapCategorie();
        Set<String> cles = mapCategorie.keySet();
        for (String cle : cles) {
            cbCategorie.addItem(cle);
        }
        // Filtre les entrees dans le textfield Prix et Quantite et Promotion
        AbstractDocument docPrix = (AbstractDocument) tfPrix.getDocument();
        docPrix.setDocumentFilter(new DoubleFiltre());
        AbstractDocument docQuantite = (AbstractDocument) tfQuantite.getDocument();
        docQuantite.setDocumentFilter(new IntFiltre());
        AbstractDocument docPromo = (AbstractDocument) tfPromotion.getDocument();
        docPromo.setDocumentFilter(new DoubleFiltre());


        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creerProduit();
            }
        });

        container.add(ajouterProduitPanel);
    }
    public void creerProduit(){
        String nom = tfNom.getText();
        String prix = tfPrix.getText();
        String quantite = tfQuantite.getText();
        String description = taDescription.getText();
        String categorie = (String) cbCategorie.getSelectedItem();
        String promotion = tfPromotion.getText();

        if (nom.isEmpty() || prix.isEmpty() || quantite.isEmpty() || description.isEmpty() || promotion.isEmpty() || categorie.isEmpty()) {
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
        Categorie tablecategorie = new Categorie();
        tablecategorie.recupCategoriedeBd();
        Map<String, Integer> mapCategorie = tablecategorie.getMapCategorie();
        int idcategorie = mapCategorie.get(categorie);


        Produits produits1 = new Produits(nom,doubleprix,intquantite,description,idcategorie);
        produits1.setPromotion(doublepromotion);
        produits1.addProduitversBd();

        JOptionPane.showMessageDialog(this,
                "Opération effectuée avec succès",
                "Information",
                JOptionPane.INFORMATION_MESSAGE);
    }


}
