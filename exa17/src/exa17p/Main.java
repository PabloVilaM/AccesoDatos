package exa17p;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {
            Connection con = Conexion();
            FileReader archivo = new FileReader("/home/oracle/Escritorio/sql/pedidos.xml");
            XMLInputFactory a = XMLInputFactory.newInstance();
            XMLStreamReader b = a.createXMLStreamReader(archivo);
            String cantidad = "";
            String cantidad2 = "";
            boolean validator = false;
            while(b.hasNext()){
                b.next();
                switch(b.getEventType()){
                    case XMLStreamConstants.END_DOCUMENT:
                        System.out.println("Finalizado");
                        break;
                    /*case XMLStreamConstants.ATTRIBUTE:
                        System.out.println("Atributo: " + b.getAttributeName(1));
                        System.out.println("Atributo: " + b.getAttributeName(2));
                        System.out.println("a");
                        break;*/
                    case XMLStreamConstants.CHARACTERS:
                        if (!b.isWhiteSpace()) {
                            System.out.println("Contenido: " + b.getText().toString());

                            if (validator = true){
                                cantidad2 = b.getText().toString();
                                validator = false;
                            }
                            else{
                                cantidad = b.getText().toString();
                            }
                            validator = true;

                        }
                        break;
                    case XMLStreamConstants.START_ELEMENT:
                        System.out.println("Elemento: " + b.getLocalName());
                        if (!(b.getAttributeValue(0) == null)){
                            System.out.println(b.getAttributeValue(0));
                            System.out.println(b.getAttributeValue(1));
                        }
                        break;
                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (XMLStreamException e) {
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
            rs = connection.prepareStatement("SELECT * FROM PRODUTOS").executeQuery();
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
            connection.prepareStatement("UPDATE produtos SET descricion = " + "'" + descripcion + "', prezo = " + precio + ", datac = '" + fecha + "' WHERE " +
                    "codigo = '" + code +"'").executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*public static void eliminaProduto(String code, Connection connection){
        try {
            connection.createStatement().executeUpdate("DELETE FROM produtos WHERE codigo = '" + code + "'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/
}

