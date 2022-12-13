package com.company;

import javax.xml.stream.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        try {

            FileInputStream archivoLeer = new FileInputStream("/home/dam2a/Pruebas/Pruebas/objetos.txt");
            ObjectInputStream objetoLeer = new ObjectInputStream(archivoLeer);
            Product algo;
            FileWriter archivo = new FileWriter("/home/dam2a/Pruebas/Pruebas/products.xml");
            XMLOutputFactory a = XMLOutputFactory.newInstance();
            XMLStreamWriter b = a.createXMLStreamWriter(archivo);

            b.writeStartDocument("1.0");
            b.writeStartElement("productos");
            while ((algo = (Product)objetoLeer.readObject()) != null){
                b.writeStartElement("producto");
                b.writeStartElement("codigo");
                b.writeCharacters(algo.getCodigo());
                b.writeEndElement();
                b.writeStartElement("descripcion");
                b.writeCharacters(algo.getDescripcion());
                b.writeEndElement();
                b.writeStartElement("precio");
                b.writeCharacters(Double.toString(algo.getPrecio()));
                b.writeEndElement();
                b.writeEndElement();

            }
            b.writeEndElement();
            b.writeEndDocument();
            b.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

    }
}
