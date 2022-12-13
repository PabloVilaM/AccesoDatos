package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        String[] cod={"p1","p2","p3"};
        String[] desc ={"parafusos","cravos","tachas"};
        int[] prezo ={3,4,5};

        File a = new File("/home/dam2a/Pruebas/Pruebas/text8.txt");
        try {
            FileWriter escritura = new FileWriter(a);
            BufferedWriter escrituraBuffed = new BufferedWriter(escritura);
            PrintWriter escrituraPrint = new PrintWriter(escrituraBuffed);
            for (int i = 0; i < cod.length; i++){
                escrituraPrint.println(cod[i]+"\t"+desc[i]+"\t"+ prezo[i]);
            }
            escrituraPrint.close();
            FileReader lectura = new FileReader(a);
            BufferedReader lecturaBuffed = new BufferedReader(lectura);
            String b;
            while ((b = lecturaBuffed.readLine()) != null){
                String[] c = b.split("\t");
                for (int i = 0; i < c.length; i++){
                    System.out.println(c[i]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
