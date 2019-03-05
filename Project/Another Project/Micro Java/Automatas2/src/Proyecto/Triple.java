/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto;

/**
 *
 * @author Jesus Albert
 */
public class Triple {
    public int numero;
    public String operacion;
    public String v1;
    public String v2;
    public Triple(){
    
    }
    public Triple(int numero,String operacion,String v1,String v2){
        this.numero = numero;
        this.operacion = operacion;
        this.v1 = v1;
        this.v2 = v2;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getV1() {
        return v1;
    }

    public void setV1(String v1) {
        this.v1 = v1;
    }

    public String getV2() {
        return v2;
    }

    public void setV2(String v2) {
        this.v2 = v2;
    }

    @Override
    public String toString() {
        return "(" + numero + ")\t " + operacion + "\t" + v1 + "\t " + v2;
    }
    
    
}
