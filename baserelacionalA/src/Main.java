import javax.swing.*;
import java.sql.*;

public class Main {

    public static void main(String[] args) {
       Connection conn = Conexion();
        insireProduto(JOptionPane.showInputDialog("Código"), JOptionPane.showInputDialog("Descripcion"), Float.parseFloat(JOptionPane.showInputDialog("Precio")), Date.valueOf(JOptionPane.showInputDialog("Fecha")) ,conn);
        listaProdutos(conn);
        actualizaPre(JOptionPane.showInputDialog("Código del registro a modificar"), JOptionPane.showInputDialog("Descripcion"), Float.parseFloat(JOptionPane.showInputDialog("Precio")),Date.valueOf(JOptionPane.showInputDialog("Fecha")),conn);
        eliminaProduto(JOptionPane.showInputDialog("Código del registro a eliminar"), conn);
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

    public static void insireProduto(String code, String descripcion, float precio, Date fecha, Connection conne){
        try {
            conne.prepareStatement("INSERT INTO PRODUTOS VALUES(" + "'"+ code +"'," + "'"+descripcion +"'," + precio +"," + "'"+fecha+"')").executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void listaProdutos(Connection connection){
        ResultSet rs = null;
        try {
            rs = connection.createStatement().executeQuery("SELECT * FROM PRODUTOS");
            while(rs.next()){
                System.out.println("Código: " + rs.getString(1) + " Descripción: " + rs.getString(2));
                System.out.println("Prezo: " + rs.getInt(3) + " Data: " + rs.getDate(4));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void actualizaPre(String code, String descripcion, float precio, Date fecha, Connection connection){
        try {
            connection.createStatement().executeUpdate("UPDATE produtos SET descricion = " + "'" + descripcion + "', prezo = " + precio + ", datac = '" + fecha + "' WHERE " +
                    "codigo = '" + code +"'" );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void eliminaProduto(String code, Connection connection){
        try {
            connection.createStatement().executeUpdate("DELETE FROM produtos WHERE codigo = '" + code + "'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
