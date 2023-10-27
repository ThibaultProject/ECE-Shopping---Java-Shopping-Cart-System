package Vue;

import Controleur.ClientTable;
import Modele.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DossierClient extends JPanel {
    private JPanel jpDossierClient;
    private JTextField jtEmail;
    private JButton btnRechercher;
    private JTable jtClients;
    public DossierClient(JPanel container){


        btnRechercher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Actualise le tableau client
                ActualiserDossier();

            }
        });
        container.add(jpDossierClient);
    }
    public void ActualiserDossier(){
        // Récupère l'email
        String email = jtEmail.getText();
        Client client1 = new Client();
        client1.setEmail(email);

        // Mettre à jour la Table client en fonction de la sélection
        ClientTable client = new ClientTable(jtClients,client1);
        jtClients.getColumnModel().getColumn(3).setHeaderValue("Categorie");
    }
}
