package Modele;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class HistogrammeModele extends JPanel {
    Map<String, Integer> mapCategorieQuantite = new LinkedHashMap<String, Integer>();
    private JPanel mainPanel;
    public HistogrammeModele(int idcategorie,JPanel container){

        // Créer les données du camembert graph
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try {
            Connexion connexionBd = new Connexion("shop", "root", "");

            PreparedStatement stmt = connexionBd.getConn().prepareStatement("SELECT * FROM produits WHERE id_categorie = ?");
            stmt.setInt(1, idcategorie);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int quantite = resultSet.getInt("quantite");
                String categorie = resultSet.getString("nom");
                System.out.println(quantite+categorie);
                mapCategorieQuantite.put(categorie, quantite);
            }
            stmt.close();
            resultSet.close();
            connexionBd.closeConnexion();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        // Recuperer les noms et id_categorie dans la Bd et affecte a cbCategorie
        Categorie categorie = new Categorie();
        categorie.recupCategoriedeBd();
        Map<String, Integer> mapCategorie = categorie.getMapCategorie();

        String nomCategorie=null;
        for (Map.Entry<String, Integer> entry : mapCategorie.entrySet()) {
            if (entry.getValue().equals(idcategorie)) {
                nomCategorie = entry.getKey();
            }
        }
        for (Map.Entry<String, Integer> entry : mapCategorieQuantite.entrySet()) {
            dataset.setValue(entry.getValue(),nomCategorie,entry.getKey());
        }

        // Créer l'histogramme
        JFreeChart chart = ChartFactory.createBarChart(
                "Stock Produits", // Chart title
                "Category", // X-Axis Label
                "Quantite", // Y-Axis Label
                dataset, // Dataset
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Créer le ChartPanel et l'ajouter au JPanel
        ChartPanel chartPanel = new ChartPanel(chart);
        mainPanel = new JPanel();
        mainPanel.add(chartPanel);

        // Ajouter le JPanel à la fenêtre
        container.add(mainPanel);



    }
}
