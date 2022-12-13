package exa15brevep;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static int graxaTotal = 0;
    public static int graxaParcial = 0;
    public static int graxaParcial2 = 0;

    public static void main(String[] args) {

        Connection conn = Conexion();


        try {
            FileInputStream archivoLeer = new FileInputStream("/home/oracle/Escritorio/sql/platoss");
            ObjectInputStream objetoLeer = new ObjectInputStream(archivoLeer);

            Platos definitivo;

            while ((definitivo = (Platos)objetoLeer.readObject()) != null){
                listaComposicion(conn, definitivo.getCodigop());
                System.out.println(definitivo);
                System.out.println("Graxa total: " + graxaTotal);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {

        }
    }

    public static Connection Conexion(){
        Connection conn;
        String driver = "jdbc:postgresql:";
        String host = "//localhost:"; // tamen poderia ser una ip como "192.168.1.14"
        String porto = "5432";
        String sid = "postgres";
        String usuario = "oracle";
        String password = "oracle";
        String url = driver + host+ porto + "/" + sid;
        try {
            conn = DriverManager.getConnection(url,usuario,password);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public static void listaComposicion(Connection connection, String codigo){
        ResultSet rs = null;

        try {
            rs = connection.prepareStatement("SELECT * FROM COMPOSICION WHERE codp= '" + codigo +"'").executeQuery();
            while(rs.next()){
                listaComponentes(connection, rs.getString(2),  rs.getInt(3));
            }
            rs.close();
            graxaParcial = 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void listaComponentes(Connection connection, String piezas, int peso){
        ResultSet rs = null;

        try {
            rs = connection.prepareStatement("SELECT * FROM COMPONENTES where codc = '" + piezas + "'").executeQuery();
            while(rs.next()){
               graxaParcial2 = peso/100* rs.getInt(3);
               graxaParcial = graxaParcial + graxaParcial2;
            }
            rs.close();
            graxaTotal = graxaParcial;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
