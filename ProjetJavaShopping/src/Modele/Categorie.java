package Modele;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

public class Categorie {
    Map<String, Integer> mapCategorie = new LinkedHashMap<String, Integer>();


    public void recupCategoriedeBd(){
        try {
            Connexion connexionBd = new Connexion("shop", "root", "");

            Statement stmt = connexionBd.getConn().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM produits_categorie");
            while (rs.next()) {
                int id = rs.getInt("id_categorie");
                String categorie = rs.getString("nom");
                mapCategorie.put(categorie, id);
            }

            rs.close();
            stmt.close();
            connexionBd.closeConnexion();

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
    public void ajouter(String key, int value){
        mapCategorie.put(key,value);
    }
    public Map<String, Integer> getMapCategorie() {
        return mapCategorie;
    }
}
