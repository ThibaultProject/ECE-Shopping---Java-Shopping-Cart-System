package Modele;

import Vue.Carte;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class MenuModele extends JPanel{
    private JPanel modele;

    public MenuModele(JPanel container){
        this.modele=container;
    }
    public void displayAllProducts() {
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
                Carte a = new Carte(nouveau,productId);
            }
            //jp.add(nouveau);
            //JScrollPane scrollPane = new JScrollPane(nouveau);
            scrollPane.revalidate();
            scrollPane.repaint();
            modele.add(scrollPane);

            resultSet.close();
            stmt.close();
            connexionBd.closeConnexion();

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        modele.revalidate();
        modele.repaint();
    }
    public void displaySearchProducts(String searchTerm){

        try {
            Connexion connexionBd = new Connexion("shop", "root", "");

            PreparedStatement stmt = connexionBd.getConn().prepareStatement("SELECT * FROM produits WHERE nom LIKE ?");
            stmt.setString(1, "%" + searchTerm + "%");

            ResultSet resultSet = stmt.executeQuery();


            JPanel nouveau = new JPanel();
            nouveau.setLayout(new FlowLayout(FlowLayout.LEFT));
            nouveau.setPreferredSize(new Dimension(450, 400));
            JScrollPane scrollPane = new JScrollPane(nouveau);

            while (resultSet.next()) {
                int productId = resultSet.getInt("id");
                Carte a = new Carte(nouveau,productId);
            }
            scrollPane.revalidate();
            scrollPane.repaint();
            modele.add(scrollPane);

            resultSet.close();
            stmt.close();
            connexionBd.closeConnexion();

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        modele.revalidate();
        modele.repaint();

    }
    public void displayCategorieProducts(String nomCategorie){

        // Recuperer les noms et id_categorie dans la Bd et affecte a mapCategorie
        Categorie categorie = new Categorie();
        categorie.recupCategoriedeBd();
        Map<String, Integer> mapCategorie = categorie.getMapCategorie();
        int idCategorie=0;
        for (Map.Entry<String, Integer> entry : mapCategorie.entrySet()) {
            if (entry.getKey().equals(nomCategorie)) {
                idCategorie= entry.getValue();
                break;
            }
        }
        try {
            Connexion connexionBd = new Connexion("shop", "root", "");

            PreparedStatement stmt = connexionBd.getConn().prepareStatement("SELECT * FROM produits WHERE id_categorie= ?");
            stmt.setInt(1, idCategorie);

            ResultSet resultSet = stmt.executeQuery();


            JPanel nouveau = new JPanel();
            nouveau.setLayout(new FlowLayout(FlowLayout.LEFT));
            nouveau.setPreferredSize(new Dimension(450, 400));
            JScrollPane scrollPane = new JScrollPane(nouveau);

            while (resultSet.next()) {
                int productId = resultSet.getInt("id");
                Carte a = new Carte(nouveau,productId);
            }
            scrollPane.revalidate();
            scrollPane.repaint();
            modele.add(scrollPane);

            resultSet.close();
            stmt.close();
            connexionBd.closeConnexion();

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        modele.revalidate();
        modele.repaint();
    }
}
