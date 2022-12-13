package pasaxeirosvosdelimitadorepaso;

import java.io.*;
import java.sql.*;

public class Main {

    public static int precio = 0;

    public static void main(String[] args) {


        File a = new File("/home/oracle/Escritorio/sql/a/reservas.txt");
        try {
            FileReader lectura = new FileReader(a);
            BufferedReader lecturaBuffed = new BufferedReader(lectura);
            String b;
            String dni = "";
            int codigo = 0;
            int ida = 0;
            int volta = 0;
            Connection conn = Conexion();
            while ((b = lecturaBuffed.readLine()) != null){
                String[] c = b.split(",");
                for (int i = 0; i < c.length; i++){
                    switch (i){
                        case 0:
                           codigo = Integer.parseInt(c[i]);
                           break;
                        case 1:
                            dni = c[i];
                            break;
                        case 2:
                            ida = Integer.parseInt(c[i]);
                            break;
                        case 3:
                            volta = Integer.parseInt(c[i]);
                            break;
                    }
                }
                listaProdutos(conn,dni, codigo, ida , volta);
                System.out.println("Linea leida");
            }

        } catch (IOException e) {
            e.printStackTrace();
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
