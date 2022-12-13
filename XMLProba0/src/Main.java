import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try {
            FileWriter archivo = new FileWriter("/home/oracle/Escritorio/sql/autores.xml");
            XMLOutputFactory a = XMLOutputFactory.newInstance();
            XMLStreamWriter  b = a.createXMLStreamWriter(archivo);
            b.writeStartDocument("1.0");
            b.writeStartElement("autores");
            b.writeStartElement("autor");
            b.writeAttribute("codigo", "a1");
            b.writeStartElement("nome");
            b.writeCharacters("Alexandre dumas");
            b.writeEndElement();
            b.writeStartElement("titulo");
            b.writeCharacters("El conde de montecristo");
            b.writeEndElement();
            b.writeStartElement("titulo");
            b.writeCharacters("los miserables");
            b.writeEndElement();
            //--Otro autor
            b.writeStartElement("autor");
            b.writeAttribute("codigo", "a2");
            b.writeStartElement("nome");
            b.writeCharacters("Fiodor Dostoyevski");
            b.writeEndElement();
            b.writeStartElement("titulo");
            b.writeCharacters("El conde de montecristo");
            b.writeEndElement();
            b.writeStartElement("titulo");
            b.writeCharacters("noches blancas");
            b.writeEndElement();
            b.writeEndElement();
            b.writeEndElement();
            b.writeEndDocument();
            b.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }

    }
}
