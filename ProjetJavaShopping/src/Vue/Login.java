package Vue;

import Modele.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class Login extends JFrame implements ActionListener {
    private JPanel loginPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signUpButton;
    private JCheckBox showPasswordCheckBox;
    private String tableNomBd;

    public void setTableNomBd(String tableNomBd) {
        this.tableNomBd = tableNomBd;
    }

    public String getTableNomBd() {
        return tableNomBd;
    }

    public Login(String tableNomBd) {
        setTableNomBd(tableNomBd);
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(600, 400));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Ajout des actions listeners aux boutons
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verifCompte();
            }
        });
        signUpButton.addActionListener(this);
        showPasswordCheckBox.addActionListener(this);

        setVisible(true);
    }

    // Vérification des entrées de l'utilsateurs
    public void verifCompte(){
        String email = usernameField.getText();
        String mdp = String.valueOf(passwordField.getPassword());

        if (email.isEmpty() || mdp.isEmpty() ) {
            JOptionPane.showMessageDialog(this,
                    "Veuillez renseigner tous les champs",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return;
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
            return;
        }

        Client client1 = new Client();
        boolean isValid = client1.verifCompteBd(email,mdp,getTableNomBd());
        if (isValid){
            dispose();
            if(getTableNomBd()=="login_customer"){
                if(client1.rechercheEmailversBd()){
                    MenuClient a = new MenuClient(client1.getIdClient());
                }
            }else if(getTableNomBd()=="login_admin"){
                MenuAdmin a = new MenuAdmin();

            }

        }else {
            JOptionPane.showMessageDialog(Login.this,
                    "Email ou Mot de passe incorrect",
                    "Réesayer",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    // Ajouts des actions performed aux boutons et checkbox
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            // Perform login action interface Client/Admin
            //...
            this.dispose();
        } else if (e.getSource() == signUpButton) {
            // Open sign up form
            Registration registerForm = new Registration(getTableNomBd());
            registerForm.setVisible(true);
            this.dispose();
        } else if (e.getSource() == showPasswordCheckBox) {
            // Toggle show password
            if (showPasswordCheckBox.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        }
    }
}
