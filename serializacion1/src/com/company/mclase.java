package com.company;

import java.io.Serializable;

public class mclase implements Serializable {
    private String nome;
    private transient int numero1;
    private  double numero2;

    public mclase(){

    }

    public mclase(String nom, int num, int num2){
        nome = nom;
        numero1 = num;
        numero2 = num2;
    }

    @Override
    public String toString() {
        return "mclase{" +
                "nome='" + nome + '\'' +
                ", numero1=" + numero1 +
                ", numero2=" + numero2 +
                '}';
    }
}
