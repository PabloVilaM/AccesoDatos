package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) {

        try {
            FileOutputStream archivo = new FileOutputStream("/home/dam2a/Pruebas/Pruebas/serial.txt");
            FileInputStream archivo2 = new FileInputStream("/home/dam2a/Pruebas/Pruebas/serial.txt");

            ObjectOutputStream objeto = new ObjectOutputStream(archivo);
            ObjectInputStream objetoLeer = new ObjectInputStream(archivo2);

            mclase valores = new mclase("ola",-7,268743857);
            objeto.writeObject(valores);
            objeto.close();
            mclase valorVacio = new mclase();
            valorVacio = (mclase)objetoLeer.readObject();
            System.out.println(valorVacio.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
