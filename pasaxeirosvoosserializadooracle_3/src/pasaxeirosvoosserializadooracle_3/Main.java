package pasaxeirosvoosserializadooracle_3;

import java.io.*;
import java.sql.*;

public class Main {

    public static int precio = 0;

    public static <IOException> void main(String[] args) {
        try {
            Connection conn = Conexion();
            //FileOutputStream archivoEscribir = new FileOutputStream("/home/dam2a/Pruebas/Pruebas/objetos.txt");
            FileInputStream archivoLeer = new FileInputStream("/home/oracle/Escritorio/sql/a/reservas");

            //ObjectOutputStream objetoEscribir = new ObjectOutputStream(archivoEscribir);
            ObjectInputStream objetoLeer = new ObjectInputStream(archivoLeer);

            Reserva definitivo;

            while ((definitivo = (Reserva) objetoLeer.readObject()) != null){
                listaProdutos(conn, definitivo.getDni(), definitivo.getCodr(), definitivo.getIdvooida(), definitivo.getIdvoovolta());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
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

    public static void insireVoo(int code, String dni, String nombre, int precio, Connection conne){
        try {
            conne.prepareStatement("INSERT INTO RESERVASFEITAS VALUES("+ code +"," + "'"+dni +"','" + nombre +"'," + precio +")").executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void listaProdutos(Connection connection, String dni, int codigo, int ida, int volta){
        ResultSet rs = null;
        try {
            rs = connection.prepareStatement("SELECT voo,prezo FROM VOOS").executeQuery();
            while(rs.next()){
                if (ida == rs.getInt(1) || volta == rs.getInt(1)) {
                    System.out.println(precio);
                     precio =  precio + rs.getInt(2);
                    System.out.println(precio + "s");
                }
            }
            rs.close();
            String nombre = "";
            rs = connection.prepareStatement("SELECT dni,nome from pasaxeiros").executeQuery();
            while (rs.next()){
                if (dni.equals(rs.getString(1))){
                    nombre = rs.getString(2);
                }
            }
            insireVoo(codigo, dni, nombre , precio, connection);
           precio = 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*public static void actualizaVoos(Connection connection, String dni, int nreserva){
        try {
            connection.prepareStatement("UPDATE pasaxeiros SET nreservas = " + (nreserva+1) + "WHERE dni = '" + dni + "'").executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

    /*public static void actualizaPre(Connection connection, String dni, int nreserva){
        try {
            connection.prepareStatement("UPDATE pasaxeiros SET nreservas = " + (nreserva+1) + "WHERE dni = '" + dni + "'").executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

}
