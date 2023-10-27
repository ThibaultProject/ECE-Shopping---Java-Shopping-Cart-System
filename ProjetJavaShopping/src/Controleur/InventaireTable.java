package Controleur;

import Modele.InventaireTableModele;

import javax.swing.*;

public class InventaireTable {
    private JTable table;
    private InventaireTableModele modele;
    private int id_categorie;

    // Creer la table Inventaire
    public InventaireTable(JTable table,int id_categorie){
        this.table = table;
        this.id_categorie=id_categorie;
        modele = new InventaireTableModele(id_categorie);

        this.table.setModel(modele);
    }
}
