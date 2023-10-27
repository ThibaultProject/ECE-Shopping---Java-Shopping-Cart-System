package Modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Produits {
    private String nom;
    private double prix;
    private int quantite;
    private String description;
    private double promotion; // en %
    private int idcategorie;
    List<String> listeProduits = new ArrayList<String>(); // liste produits en fonction attribut idcategorie
    public Produits(String nom, double prix, int quantite, String description, int idcategorie){
        this.nom=nom;
        this.prix=prix;
        this.quantite=quantite;
        this.description=description;
        this.idcategorie=idcategorie;
    }
    public Produits(){}


    public void recupListeProduitsdeCategorieBd(int idCategorie){
        try {
            Connexion connexionBd = new Connexion("shop", "root", "");

            Statement stmt = connexionBd.getConn().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT nom FROM produits WHERE id_categorie = "+idCategorie);
            while (rs.next()) {
                String nom = rs.getString("nom");
                listeProduits.add(nom);
            }

            rs.close();
            stmt.close();
            connexionBd.closeConnexion();

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    public void addProduitversBd(){
        try {
            Connexion connexionBd = new Connexion("shop", "root", "");

            PreparedStatement stmt = connexionBd.getConn().prepareStatement("INSERT INTO produits(nom,prix,quantite,description,promotion,id_categorie) VALUES(?,?,?,?,?,?)");
            stmt.setString(1, this.nom);
            stmt.setDouble(2, this.prix);
            stmt.setInt(3, this.quantite);
            stmt.setString(4, this.description);
            stmt.setDouble(5, this.promotion);
            stmt.setInt(6,this.idcategorie);
            stmt.executeUpdate();

            stmt.close();
            connexionBd.closeConnexion();

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
    public void updateProduitversBd(){
        try {
            Connexion connexionBd = new Connexion("shop", "root", "");

            PreparedStatement stmt = connexionBd.getConn().prepareStatement("UPDATE produits SET prix = ?, quantite = ?, description = ?, promotion = ?  WHERE nom = ?AND id_categorie = ?");
            stmt.setDouble(1, prix);
            stmt.setInt(2, quantite);
            stmt.setString(3, description);

            stmt.setDouble(4,promotion);

            stmt.setString(5,nom);
            stmt.setInt(6,idcategorie);

            stmt.executeUpdate();

            stmt.close();
            connexionBd.closeConnexion();

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void deleteProduitversBd(String nom, int idcategorie){
        try {
            Connexion connexionBd = new Connexion("shop", "root", "");

            PreparedStatement stmt = connexionBd.getConn().prepareStatement("DELETE FROM produits WHERE nom = ? AND id_categorie = ?");
            stmt.setString(1, nom);
            stmt.setInt(2, idcategorie);
            stmt.executeUpdate();

            stmt.close();
            connexionBd.closeConnexion();

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
    // Recupere les elements du produit avec nom et id_categorie
    public void recupProduitdeBd(){
        try {
            Connexion connexionBd = new Connexion("shop", "root", "");

            PreparedStatement stmt = connexionBd.getConn().prepareStatement("SELECT * FROM produits WHERE nom = ? AND id_categorie = ?");
            stmt.setString(1, nom);
            stmt.setInt(2, idcategorie);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Double prix = rs.getDouble("prix");
                int quantite = rs.getInt("quantite");
                String description = rs.getString("description");
                Double promotion = rs.getDouble("promotion");
                setPrix(prix);
                setQuantite(quantite);
                setDescription(description);
                setPromotion(promotion);
            }

            rs.close();
            stmt.close();
            connexionBd.closeConnexion();

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
    // Recupere les elements du produit avec id
    public void recupProduitdeBdavecId(int idProduit){
        try {
            Connexion connexionBd = new Connexion("shop", "root", "");

            PreparedStatement stmt = connexionBd.getConn().prepareStatement("SELECT * FROM produits WHERE id = ?");
            stmt.setInt(1, idProduit);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String nom = rs.getString("nom");
                Double prix = rs.getDouble("prix");
                int quantite = rs.getInt("quantite");
                String description = rs.getString("description");
                Double promotion = rs.getDouble("promotion");
                int idCateogire = rs.getInt("id_categorie");
                setPrix(prix);
                setQuantite(quantite);
                setDescription(description);
                setPromotion(promotion);
                setIdcategorie(idCateogire);
                setNom(nom);
            }

            rs.close();
            stmt.close();
            connexionBd.closeConnexion();

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
    /*
    -------- GETTERS et SETTERS -------
    */

    public List<String> getListeProduits() {
        return listeProduits;
    }

    public String getNom() {return nom;}
    public double getPrix() {
        return prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public String getDescription() {
        return description;
    }

    public int getIdcategorie() {return idcategorie;}

    public double getPromotion() {
        return promotion;
    }



    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIdcategorie(int idcategorie) {
        this.idcategorie = idcategorie;
    }

    public void setPromotion(double promotion) {
        this.promotion = promotion;
    }
}
