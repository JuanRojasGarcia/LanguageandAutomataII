/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Chuy
 */
public class Parser {
    public Scanner scanner;
    public String Error = "Compilacion Exitosa!!";
    public boolean eror = false;
    public int tkn=0;
    final int Class=1,DelIzq = 2,DelDer = 3,Boolean = 4,Int = 5,DL = 6,If = 7,Else=8,PD = 10;
    final int PI = 9,EQ = 11,mas=12,menos = 13,menor = 14,mayor = 15,multi = 16,True = 17;
    final int False = 18,SOP = 19 , ID = 20,INTEGER = 21,While = 22,VInteger = 23, VBoolean = 24;
    //DL es el punto y coma
    //DelIzq es { y DelDer es }
    //EQ ES =
    //PD es parentesis derecho y PI es parentesis izquierdo
    public ArrayList<String> estatutos = new ArrayList<String>();
    public Parser(Scanner scanner){
        this.scanner = scanner;
        tkn = scanner.getToken();
        estatutos.add("Iniciando Proceso de Parsing");
    }
    public void imprimePrueba(){
        for(int j = 0 ; j < scanner.tokens.size();j++){
            System.out.println(scanner.tokens.get(j));
        }
    }
    public int tope = 1;
    public void advance() {
        tkn = scanner.getToken();
        /*
        if(scanner.tope == scanner.tokens.size()-1){
            JOptionPane.showMessageDialog(null,"Error, ya te comiste todo");
            
        }*/
    }
    
    public void eat(int t) {
       // int tokenEsperado = t;
       //System.out.println("Tope del Scanner = " + scanner.tope);
       //System.out.println("Token Esperado " + NomToken(t) + " Token Recibido " + NomToken(tkn));
       estatutos.add("Token Esperado " + NomToken(t) + " Token Recibido " + NomToken(tkn));
        if(tkn == t) {
            //setLog("Token: " + token + "\n" + "Tipo:  "+ s.getTipoToken());
            advance();
        }
        else{
            //error(token, "token tipo:"+t);
            eror = true;
            JOptionPane.showMessageDialog(null,"Error..... Se esperaba " + NomToken(t) + " no " + NomToken(tkn));
            estatutos.add("Error..... Se esperaba " + NomToken(t) + " no " + NomToken(tkn));
        }
    }
    public String NomToken(int token){
        switch(token){
            case 1: 
            return "Class";
            case 2:
            return "Delimitador {";
            case 3:
            return "Delimitador }";
            case 4:
            return "boolean";
            case 5:
            return "int";
            case 6:
            return "Delimitador ;";
            case 7:
            return "If";
            case 8:
            return "Else";
            case 9:
            return "Delimitador (";
            case 10:
            return "Delimitador )";
            case 11:
            return "Operador =";
            case 12:
            return "Operador +";
            case 13:
            return "Operador -";
            case 14:
            return "Operador <";
            case 15:
            return "Operador >";
            case 16:
            return "Operador *";
            case 17:
            return "true";
            case 18:
            return "false";
            case 19:
            return "System.out.println";
            case 20:
            return "Identificador";
            case 21:
            return "Identificador Numerico";
            case 22:
            return "While";
            case 23:
            return "Variable Integer";
            case 24:
            return "Variable Boolean";
            default:
                return null;
        }
    }
    public void program(){
        switch(tkn){
            case Class:
                eat(Class);
                eat(ID);
                varDeclaration();
                eat(DelIzq);
                //advance();
                while(tkn==While || tkn==SOP || tkn==VInteger || tkn == VBoolean)
        	{
        		statement();
        	}
                eat(DelDer);
                break;
            default :
                Error("Error en Program");
        }
    }
    public void varDeclaration(){
      switch (tkn){
          case Boolean:
              eat(Boolean);
              eat(VBoolean);
              eat(DL);
              varDeclaration();
          break;
          case Int:
              eat(Int);
              eat(VInteger);
              eat(DL);
              varDeclaration();
      }
    }
    /*
    VInteger
    VBoolean
    */
    public void statement(){
        switch(tkn){
            /*
            case DelIzq:
                eat(DelIzq);
                statement();
                eat(DelDer);
            break;*/
            case DelIzq:
            {
            	eat(DelIzq);
            	if(tkn!=0)
            	while(tkn==DelIzq  ||tkn==While || tkn==SOP || tkn==VInteger || tkn == VBoolean )
            	{
            		//System.out.println("Entro");
            		statement();
            	}  
                eat(DelDer);
            }
            case While:
                eat(While);
                eat(PI);
                expresion();
                eat(PD);
                //eat(DelIzq);
                statement();
                //eat(DelDer);
                //statement();
            break;
            case SOP:
                eat(SOP);
                eat(PI);
                expresion();
                eat(PD);
                eat(DL);
                //statement();
            break;
            case VBoolean:
                eat(VBoolean);
                eat(EQ);
                expresion();
                eat(DL);
                //statement();
            break;
            case VInteger:
                eat(VInteger);
                eat(EQ);
                expresion();
                eat(DL);
                //statement();
            break;
            default:
            //Error("Error en Statement");
        }
    }
    /*
    VInteger
    VBoolean
    */
    public void expresion(){
        switch(tkn){
            case VInteger:
                eat(VInteger);
                switch (tkn) {
                    case EQ:
                        eat(EQ);
                        if(tkn==VInteger)
                        {
                            eat(VInteger);
                        }
                        else
                        {
                            eat(INTEGER);
                        }
                        break;
                    case menor:
                        eat(menor);
                        if(tkn==VInteger)
                        {
                            eat(VInteger);
                        }
                        else
                        {
                            eat(INTEGER);
                        }
                        break;
                    case mas:
                        eat(mas);
                        if(tkn==VInteger)
                        {
                            eat(VInteger);
                        }
                        else
                        {
                            eat(INTEGER);
                        }
                        break;
                    default:
                        break;
                        }
            break;
            case VBoolean:
                eat(VBoolean);
                if(tkn==EQ){
                 eat(EQ);
                        if(tkn==VBoolean)
                        {
                            eat(VBoolean);
                        }
                        else
                        {
                            if(tkn==True)
                            eat(True);
                            else
                            eat(False);
                        }
                }
            break;
            case INTEGER:
                eat(INTEGER);
                switch (tkn) {
                    case EQ:
                        eat(EQ);
                        if(tkn==VInteger)
                        {
                            eat(VInteger);
                        }
                        else
                        {
                            eat(INTEGER);
                        }
                        break;
                    case menor:
                        eat(menor);
                        if(tkn==VInteger)
                        {
                            eat(VInteger);
                        }
                        else
                        {
                            eat(INTEGER);
                        }
                        break;
                    case mas:
                        eat(mas);
                        if(tkn==VInteger)
                        {
                            eat(VInteger);
                        }
                        else
                        {
                            eat(INTEGER);
                        }
                        break;
                    default:
                        break;
                        }
            break;
            case True:
                eat(True);
                if(tkn==EQ){
                 eat(EQ);
                        if(tkn==VBoolean)
                        {
                            eat(VBoolean);
                        }
                        else
                        {
                            if(tkn==True)
                            eat(True);
                            else
                            eat(False);
                        }
                }
            break;
            case False:
                eat(False);
                if(tkn==EQ){
                 eat(EQ);
                        if(tkn==VBoolean)
                        {
                            eat(VBoolean);
                        }
                        else
                        {
                            if(tkn==True)
                            eat(True);
                            else
                            eat(False);
                        }
                }
            break;
            default:
            //Error("Error en Expresion");
        }
    }
     public void Error(String error){
           JOptionPane.showMessageDialog(null,error);
    }
}
