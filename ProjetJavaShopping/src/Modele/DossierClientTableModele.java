package Modele;

import javax.swing.table.AbstractTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DossierClientTableModele extends AbstractTableModel {
    private List<Object[]> data;
    private String[] colonneNom;

    public DossierClientTableModele(int idClient){

        colonneNom = new String[]{ "nom", "prix", "quantiteAchete","id_categorie","prix_total"};
        data = new ArrayList<>();

        // Recuperer les noms et id_categorie dans la Bd et affecte a mapCategorie
        Categorie categorie = new Categorie();
        categorie.recupCategoriedeBd();
        Map<String, Integer> mapCategorie = categorie.getMapCategorie();

        // Requete SQL , Mise a jour table client
        try {
            Connexion connexionBd = new Connexion("shop", "root", "");

            String sql = "SELECT * FROM `ticket` JOIN produits ON ticket.id_produit = produits.id WHERE id_customer = ?";
            PreparedStatement stmt = connexionBd.getConn().prepareStatement(sql);
            stmt.setInt(1,idClient);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {


                Object[] row = new Object[colonneNom.length];
                for (int i = 0; i < colonneNom.length; i++) {
                    // Change l'idCategorie par son nom
                    if (colonneNom[i].equals("id_categorie")) {

                        for (Map.Entry<String, Integer> entry : mapCategorie.entrySet()) {
                            if (entry.getValue().equals(rs.getInt("id_categorie"))) {
                                row[i] = entry.getKey();
                                break;
                            }
                        }
                    } else {
                        row[i] = rs.getObject(colonneNom[i]);
                    }
                }
                data.add(row);
            }

            rs.close();
            stmt.close();
            connexionBd.closeConnexion();

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return colonneNom.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return colonneNom[column];
    }
}
