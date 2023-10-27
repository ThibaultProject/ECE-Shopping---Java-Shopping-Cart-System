package Modele;

import java.sql.*;

public class Connexion {

        private final Connection conn;
        private final Statement stmt;
        private ResultSet rset;
        private ResultSetMetaData rsetMeta;

        public Connexion(String nameDatabase, String loginDatabase, String passwordDatabase) throws SQLException, ClassNotFoundException {
            // chargement driver "com.mysql.jdbc.Driver"
            Class.forName("com.mysql.jdbc.Driver");

            String urlDatabase = "jdbc:mysql://localhost:3306/" + nameDatabase;
            //création d'une connexion JDBC à la base
            conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);

            // création d'un ordre SQL (statement)
            stmt = conn.createStatement();
        }

        public void closeConnexion(){
            try {
                stmt.close();
                conn.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

    public Connection getConn() {
        return conn;
    }

}
