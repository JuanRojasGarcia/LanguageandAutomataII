/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto;

import java.util.ArrayList;

/**
 *
 * @author Jesus Albert
 */
public class ListaTriples {
        public ArrayList<Triple> lista = new ArrayList<Triple>();
        public ListaTriples(){
        }
        public void inserta(Triple triple){
            lista.add(triple);
        }
        public void insertaOp(){
        
        }
        public void muestratodo(){
            for(int j = 0 ; j < lista.size(); j++)
            {
                System.out.println(lista.get(j).toString());
            }
        }
}
