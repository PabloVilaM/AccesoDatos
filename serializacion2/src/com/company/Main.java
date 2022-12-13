package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        try {
            FileOutputStream archivoEscribir = new FileOutputStream("/home/dam2a/Pruebas/Pruebas/objetos.txt");
            FileInputStream archivoLeer = new FileInputStream("/home/dam2a/Pruebas/Pruebas/objetos.txt");

            ObjectOutputStream objetoEscribir = new ObjectOutputStream(archivoEscribir);
            ObjectInputStream objetoLeer = new ObjectInputStream(archivoLeer);

            Product productoA = new Product("a", "una a", 1);
            Product productoB = new Product("b", "una b", 2);
            Product productoC = new Product("c", "una c", 3);
            Product definitivo;
            objetoEscribir.writeObject(productoA);
            objetoEscribir.writeObject(productoB);
            objetoEscribir.writeObject(productoC);
            objetoEscribir.writeObject(null);
            objetoEscribir.close();

            while ((definitivo = (Product)objetoLeer.readObject()) != null){
                System.out.println(definitivo.toString());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
