package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Accueil extends JFrame implements ActionListener{
    private JPanel accueilPanel;
    private JButton clientButton;
    private JButton adminButton;

    public Accueil() {
        setTitle("Accueil");
        setContentPane(accueilPanel);
        setMinimumSize(new Dimension(550, 420));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Ajout des actions listeners sur les boutons
        clientButton.addActionListener(this);
        adminButton.addActionListener(this);

        // affichage de la fenêtre
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clientButton) {
            Login login = new Login("login_customer");
            login.setVisible(true);
            this.dispose();
            // Relier à la BDD Client ...

        } else if(e.getSource() == adminButton) {
            // Open sign up form
            Login login = new Login("login_admin");
            login.setVisible(true);
            this.dispose();
            // Relier à la BDD Admin ...
        }
    }
}
