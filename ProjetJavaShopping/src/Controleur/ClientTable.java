package Controleur;

import Modele.Client;
import Modele.DossierClientTableModele;

import javax.swing.*;

public class ClientTable {
    private JTable table;
    private DossierClientTableModele modele;
    // Creer la table Client

    public ClientTable(JTable table, Client client){
        this.table = table;
        // Verifie si email existe
        if (client.rechercheEmailversBd()){
            modele = new DossierClientTableModele(client.getIdClient());
        }else {
            modele = new DossierClientTableModele(0);
        }
        this.table.setModel(modele);
    }
    public ClientTable(JTable table, int idClient){
        this.table = table;
        modele = new DossierClientTableModele(idClient);
        this.table.setModel(modele);
    };
}
