package Modele;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class  CamembertGraphModele extends JPanel{
    Map<String, Integer> mapCategorieQuantite = new LinkedHashMap<String, Integer>();
    private JPanel mainPanel;
    public CamembertGraphModele(int idcategorie,JPanel container) {

        // Créer les données du camembert graph
        DefaultPieDataset dataset = new DefaultPieDataset();
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
        for (Map.Entry<String, Integer> entry : mapCategorieQuantite.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

        // Créer le camembert graph
        JFreeChart chart = ChartFactory.createPieChart(
                "Stock Produits",
                dataset,
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
