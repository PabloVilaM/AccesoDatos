import javax.xml.stream.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try {
            FileReader archivo = new FileReader("/home/oracle/Escritorio/sql/autores.xml");
            XMLInputFactory a = XMLInputFactory.newInstance();
            XMLStreamReader b = a.createXMLStreamReader(archivo);
            while(b.hasNext()){
                b.next();
                int numero = b.getEventType();
                if (numero == XMLStreamConstants.END_DOCUMENT){
                    break;
                }
                System.out.println("Nombre elemento: " + b.getLocalName());
                if (b.getLocalName() == "nome" || b.getLocalName() == "titulo" ){
                    System.out.println("Content: " + b.getElementText());
                }


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }

    }
}
