package com.company;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {
            FileReader archivo = new FileReader("/home/dam2a/Pruebas/Pruebas/products.xml");
            XMLInputFactory a = XMLInputFactory.newInstance();
            XMLStreamReader b = a.createXMLStreamReader(archivo);
            ArrayList<Product> productos = new ArrayList<>();
            Product item = new Product();
            while(b.hasNext()){
                b.next();
                int numero = b.getEventType();
                if (numero == XMLStreamConstants.END_DOCUMENT){
                    break;
                }
                if (numero == XMLStreamConstants.END_ELEMENT && b.getLocalName() == "producto"){
                    productos.add(item);
                    System.out.println(item.toString());
                    System.out.println("añadiendo");
                    for (int i = 0; i < productos.size(); i++){
                        System.out.println(productos.get(i).toString());
                    }
                }

                System.out.println("Nombre elemento: " + b.getLocalName());
                if (b.getLocalName() == "descripcion"){

                    item.setDescripcion(b.getElementText());
                }
                else if (b.getLocalName() == "codigo") {
                    item.setCodigo(b.getElementText());
                }
                else if (b.getLocalName() == "precio"){
                    item.setPrecio(Double.parseDouble(b.getElementText()));
                }


            }

            for (int i = 0; i < productos.size(); i++){
                System.out.println(productos.get(i).toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }
}
