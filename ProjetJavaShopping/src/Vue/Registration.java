package Vue;

import Modele.Client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class Registration extends JFrame implements ActionListener{
    private JPanel registerPanel;
    private JTextField lastNameField;
    private JTextField firstNameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton validerButton;
    private JButton annulerButton;
    private JCheckBox showPasswordCheckBox;
    private JCheckBox showPassword2CheckBox;
    //public Client client;
    private String tableNomBd;

    public void setTableNomBd(String tableNomBd) {
        this.tableNomBd = tableNomBd;
    }

    public String getTableNomBd() {
        return tableNomBd;
    }

    public Registration(String tableNomBd) {
        setTableNomBd(tableNomBd);
        setTitle("Sign Up");
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(700, 650));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Ajout des actions listener aux boutons et checkbox
        validerButton.addActionListener(this);
        annulerButton.addActionListener(this);
        showPasswordCheckBox.addActionListener( this);
        showPassword2CheckBox.addActionListener( this);

        // affiche la fenêtre
        setVisible(true);

    }
    private boolean creerClient() {
        String nom = lastNameField.getText();
        String prenom = firstNameField.getText();
        String email = emailField.getText();
        String mdp = String.valueOf(passwordField.getPassword());
        String cmdp = String.valueOf(confirmPasswordField.getPassword());

        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || mdp.isEmpty() || cmdp.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Veuillez renseigner tous les champs",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Valide le format de l'email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        if (!Pattern.compile(emailRegex).matcher(email).matches()) {
            JOptionPane.showMessageDialog(this,
                    "L'adresse e-mail n'est pas valide",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!mdp.equals(cmdp)) {
            JOptionPane.showMessageDialog(this,
                    "Erreur : mots de passes differents",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        Client client1 = new Client(nom, prenom, email, mdp);
        client1.ajouterClientversBd(getTableNomBd());
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == validerButton) {
            // Fermez la page RegisterForm et ouvrez la page LoginForm
            if (creerClient() == true) {
                this.dispose();
                new Login(getTableNomBd());
            }
        } else if (e.getSource() == annulerButton) { // clear les TextField de la fenêtre
            Component[] components = getContentPane().getComponents();

            // Boucle sur tous les composants
            for (Component component : components) {
                // Effacement des champs JTextField uniquement
                if (component instanceof JTextField) {
                    ((JTextField) component).setText("");
                }
            }
        } else if (e.getSource() == showPassword2CheckBox) {
            // Toggle show password
            if (showPassword2CheckBox.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        } else if (e.getSource() == showPasswordCheckBox) {
            // Toggle show password
            if (showPasswordCheckBox.isSelected()) {
                confirmPasswordField.setEchoChar((char) 0);
            } else {
                confirmPasswordField.setEchoChar('*');
            }
        }
    }
}
