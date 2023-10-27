package Modele;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreerCarte extends JPanel {
    public CreerCarte(int productId){
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));

        try {
            Connexion connexionBd = new Connexion("shop", "root", "");

            PreparedStatement stmt = connexionBd.getConn().prepareStatement("SELECT * FROM produits WHERE id = ?");
            stmt.setInt(1, productId);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String category = resultSet.getString("id_categorie");
                String description = resultSet.getString("description");
                String promo = resultSet.getString("promotion");
                int stock = resultSet.getInt("quantite");
                double price = resultSet.getDouble("prix");

                Color customColor = new Color(9, 89, 77);
                setBackground(customColor);

                // Première ligne: Promo et stock
                JPanel promoStockPanel = new JPanel(new BorderLayout());
                promoStockPanel.add(new JLabel("Promo : " + promo), BorderLayout.WEST);
                promoStockPanel.add(new JLabel("Stock : " + stock), BorderLayout.EAST);
                add(promoStockPanel);
                promoStockPanel.setBackground(customColor);

                // Deuxième ligne: Image du produit
                JLabel imageLabel = new JLabel(new ImageIcon("projetJava/src/Vue/icon/article.png"));
                JPanel imagePanel = new JPanel();
                imagePanel.add(imageLabel);
                imagePanel.setBackground(customColor);
                imagePanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Align the image panel to the center
                add(imagePanel);

                // Troisième ligne: Nom du produit
                JPanel productName = new JPanel(new BorderLayout());
                productName.add(new JLabel("Nom : " + nom), BorderLayout.WEST);
                add(productName);
                productName.setBackground(customColor);

                // Quatrième ligne: Catégorie du produit et prix
                JPanel categoryPricePanel = new JPanel(new BorderLayout());
                categoryPricePanel.add(new JLabel("Catégorie : " + category), BorderLayout.WEST);
                categoryPricePanel.add(new JLabel(String.format("Prix : %.2f €", price)), BorderLayout.EAST);
                add(categoryPricePanel);
                categoryPricePanel.setBackground(customColor);

                // Cinquième ligne : Description du produit
                JPanel descriptionPanel = new JPanel(new BorderLayout());
                descriptionPanel.add(new JLabel("Description : " + description), BorderLayout.WEST);
                add(descriptionPanel);
                descriptionPanel.setBackground(customColor);

                // Dernière ligne: Bouton Ajouter au panier et Spinner quantité
                JPanel bottomPanel = new JPanel(new BorderLayout());
                JButton addToCartButton = new JButton("Ajouter au Panier");
                addToCartButton.setPreferredSize(new Dimension(addToCartButton.getPreferredSize().width, 10));
                bottomPanel.add(addToCartButton, BorderLayout.WEST);

                JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
                quantitySpinner.setPreferredSize(new Dimension(40, 10));
                bottomPanel.add(quantitySpinner, BorderLayout.EAST);
                add(bottomPanel);
                bottomPanel.setBackground(customColor);

                // Définir la taille préférée et la taille maximale pour chaque ProductCard
                setPreferredSize(new Dimension(200, 300));
                setMaximumSize(new Dimension(200, 300));
            }
            resultSet.close();
            stmt.close();
            connexionBd.closeConnexion();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
}
