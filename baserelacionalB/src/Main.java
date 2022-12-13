import javax.swing.*;
import java.net.http.HttpResponse;
import java.sql.*;

public class Main {

    public static void main(String[] args) {
        Connection conn = Conexion();
        Statement statement;
        ResultSet rs = null;
        try {

            statement = conn.createStatement (rs.TYPE_SCROLL_SENSITIVE, rs.CONCUR_UPDATABLE);
            rs = statement.executeQuery("SELECT * FROM PRODUTOS");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //inxerirProdutoRS(rs);
        try {
            listaProdutos(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        actualizarRS(rs);
        eliminaProduto(rs,JOptionPane.showInputDialog(JOptionPane.showInputDialog("Codigo")));
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

    public static void inxerirProdutoRS(ResultSet resultSet){
        try {
            resultSet.moveToInsertRow();
            resultSet.updateString(1, "p14");
            resultSet.updateString(2, "altavoces");
            resultSet.updateInt(3, 14);
            resultSet.insertRow();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void listaProdutos(ResultSet rs) throws SQLException {
        try {
            rs.first();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while(rs.next()){
            System.out.println("Código: " + rs.getString(1) + " Descripción: " + rs.getString(2));
            System.out.println("Prezo: " + rs.getInt(3) + " Data: " + rs.getDate(4));
        }

    }

    public static void actualizarRS(ResultSet resultSet){
        try {
            resultSet.first();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String code = JOptionPane.showInputDialog("Código del que quieres actualizar");
        try {

            while(resultSet.next()){
               if (code.equals(resultSet.getString(1))){
                   resultSet.updateString(2, JOptionPane.showInputDialog("Nombre"));
                   resultSet.updateInt(3, Integer.parseInt(JOptionPane.showInputDialog("Precio")));
                   resultSet.updateRow();
               }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public static void eliminaProduto(ResultSet rs, String code){
        try {

            while(rs.next()){
                if (code.equals(rs.getString(1))){
                    rs.deleteRow();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
