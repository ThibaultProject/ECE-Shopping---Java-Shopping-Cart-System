package Modele;
import java.sql.*;

public class Client {
    String nom;
    String prenom;
    String email;
    String mdp;

    int idClient;

    public Client(String nom, String prenom, String email, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
    }

    public Client() {
    }

    public void ajouterClientversBd(String tableNomBd) {
        try {
            Connexion connexionBd = new Connexion("shop", "root", "");

            PreparedStatement stmt = connexionBd.getConn().prepareStatement("INSERT INTO "+tableNomBd+"(nom,prenom,passwords,email) VALUES(?,?,?,?)");
            stmt.setString(1, this.nom);
            stmt.setString(2, this.prenom);
            stmt.setString(3, this.mdp);
            stmt.setString(4, this.email);
            stmt.executeUpdate();

            stmt.close();
            connexionBd.closeConnexion();

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    public boolean verifCompteBd(String email,String mdp,String tableNomBd) {
        boolean isValid = false;

        try {
            Connexion connexionBd = new Connexion("shop", "root", "");

            PreparedStatement stmt = connexionBd.getConn().prepareStatement("SELECT * FROM "+tableNomBd+" WHERE email=? AND passwords=?");
            stmt.setString(1, email);
            stmt.setString(2, mdp);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                setNom(resultSet.getString("nom"));
                setPrenom(resultSet.getString("prenom"));
                setEmail(resultSet.getString("email"));
                setMdp(resultSet.getString("passwords"));
                setIdClient(resultSet.getInt("id"));
                System.out.println("nom: " + getNom() + ", prenom: " + getPrenom() + ", email: " + getEmail() + ", passwords: " + getMdp());
                isValid = true;
            }
            resultSet.close();
            stmt.close();
            connexionBd.closeConnexion();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return isValid;

    }
    public boolean rechercheEmailversBd(){
        boolean isValid = false;
        try {
            Connexion connexionBd = new Connexion("shop", "root", "");

            PreparedStatement stmt = connexionBd.getConn().prepareStatement("SELECT * FROM login_customer WHERE email=?");
            stmt.setString(1, email);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                setIdClient(resultSet.getInt("id"));
                isValid = true;
            }
            stmt.close();
            resultSet.close();
            connexionBd.closeConnexion();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return isValid;

    }

    /*
    GETTERS - SETTERS
     */

    public int getIdClient() {
        return idClient;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

}
