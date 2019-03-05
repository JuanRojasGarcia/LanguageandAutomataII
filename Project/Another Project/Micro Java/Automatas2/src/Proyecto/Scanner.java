/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author Chuy
 */
public class Scanner {
    //para los identificadores se usara 
    //numeric = 19
    public ArrayList<String> tabtok = new ArrayList<String>();
    public ArrayList<Integer> tokens =  new ArrayList<Integer>();
    public ArrayList<String> variablesint = new ArrayList<String>();
    public ArrayList<String> variablesbool = new ArrayList<String>();
    public ArrayList<String> symbolos = new ArrayList<String>();
    public ArrayList<String> tokString = new ArrayList<String>();
    public String Error= "No se Encontraron Errores con los tokens";
    public void llenaTabtok(){
        tabtok.add("Class");     //1
        tabtok.add("{");         //2
        tabtok.add("}");         //3
        tabtok.add("boolean");   //4
        tabtok.add("int");       //5
        tabtok.add(";");         //6
        tabtok.add("if");        //7
        tabtok.add("else");      //8
        tabtok.add("(");         //9
        tabtok.add(")");         //10
        tabtok.add("=");         //11
        tabtok.add("+");         //12
        tabtok.add("-");         //13
        tabtok.add("<");         //14
        tabtok.add(">");         //15
        tabtok.add("*");         //16
        tabtok.add("true");      //17
        tabtok.add("false");     //18
        tabtok.add("sop");//19
        tabtok.add("identifier");//20
        tabtok.add("numeric identifier");//21
        tabtok.add("while");//22
        tabtok.add("vint");     //23Variable Integer
        tabtok.add("vbool");    //24Variable Booleana 
    }
    public Scanner(){
        llenaTabtok();
    }
    //Metodo Con el que se va a escanear todo el codigo
    public void Scanear(String codigo){
        StringTokenizer t = new StringTokenizer(codigo);
        while(t.hasMoreElements()){
            String tk = t.nextToken();
            //System.out.println(tk);
            analizar(tk);
            tokString.add(tk);
        }  
        //imprimeTokens();
        /*
        System.out.println("Termine proceso de Scanneo....Tokens encontrados");
        System.out.println(tokString.size());
        for(int j = 0 ; j < tokString.size(); j++)
        {
            System.out.println(tokString.get(j));
        }*/
        System.out.println(tokString.size() + " " + tokens.size());
    }
    public void analizar(String token){
        if(token.equals("class")){
            tokens.add(1);
            symbolos.add("Token Reconocido........Palabra Reserada Class");
        }else if(token.equals("{"))
        {
            tokens.add(2);
            symbolos.add("Token Reconocido........Delimitador {");
        }else if(token.equals("}"))
        {
            tokens.add(3);
            symbolos.add("Token Reconocido........Delimitador }");
        }else if(token.equals("boolean"))
        {
            tokens.add(4);
            symbolos.add("Token Reconocido........Palabra Reservada boolean");
        }else if(token.equals("int"))
        {
            tokens.add(5);
            symbolos.add("Token Reconocido........Palabra Reservada int");
        }else if(token.equals(";"))
        {
            tokens.add(6);
            symbolos.add("Token Reconocido........Delimitador ;");
        }else if(token.equals("if"))
        {
            tokens.add(7);
            symbolos.add("Token Reconocido........Palabra Reservada if");
        }else if(token.equals("else"))
        {
            tokens.add(8);
            symbolos.add("Token Reconocido........Palabra Reservada else");
        }else if(token.equals("("))
        {
            tokens.add(9);
            symbolos.add("Token Reconocido........Delimitador (");
        }else if(token.equals(")"))
        {
            tokens.add(10);
            symbolos.add("Token Reconocido........Delimitador )");
        }else if(token.equals("="))
        {
            tokens.add(11);
            symbolos.add("Token Reconocido........Operador =");
        }else if(token.equals("+"))
        {
            tokens.add(12);
            symbolos.add("Token Reconocido........Operador +");
        }else if(token.equals("-"))
        {
            tokens.add(13);
            symbolos.add("Token Reconocido........Operador -");
        }else if(token.equals("<"))
        {
            tokens.add(14);
            symbolos.add("Token Reconocido........Operador <");
        }else if(token.equals(">"))
        {
            tokens.add(15);
            symbolos.add("Token Reconocido........Operador >");
        }else if(token.equals("*"))
        {
            tokens.add(16);
            symbolos.add("Token Reconocido........Operador *");
        }else if(token.equals("true"))
        {
            tokens.add(17);
            symbolos.add("Token Reconocido........Palabra Reservada true");
        }else if(token.equals("false"))
        {
            tokens.add(18);
            symbolos.add("Token Reconocido........Palabra Reservada false");
        }else if(token.equals("System.out.println"))
        {
            tokens.add(19);
            symbolos.add("Token Reconocido........Palabra Reservada System.out.println");
        }else if(token.equals("while")){
            tokens.add(22);
            symbolos.add("Token Reconocido........Palabra Reservada while");
        }
        else 
        {
            //En esta parte del codigo checare que el token es un numero o no 
            try{
                int esnumero = Integer.parseInt(token);
                tokens.add(21);
                symbolos.add("Token Reconocido........Identificador Numerico");
            }
            catch(Exception err){
                //Cacho una excepcion en caso de que no 
                //comprobare si es una variable o se declarara como tal
                //JOptionPane.showConfirmDialog(null,"Error el token: " + token + " no es valido ","Error", JOptionPane.QUESTION_MESSAGE, JOptionPane.ERROR_MESSAGE);     
                //Agrega un identificador tipo cadena
                if(variablesint.contains(token)){
                    tokens.add(23);
                    symbolos.add("Token Reconocido........Identificador Variable Integer " + token);
                   
                }else if(variablesbool.contains(token)){
                    tokens.add(24);
                    symbolos.add("Token Reconocido........Identificador Variable Boolean " + token);
                }
                //4 y 5 ya que 4 significa int y 5 boolean
                else if(tokens.get(tokens.size()-1)== 5 )
                {
                    tokens.add(23);
                    symbolos.add("Token Reconocido........Identificador Variable Integer " + token);
                    variablesint.add(token);
                    
                }else if(tokens.get(tokens.size()-1)== 4 )
                {
                    tokens.add(24);
                    symbolos.add("Token Reconocido........Identificador Variable Boolean " + token);
                    variablesbool.add(token);
                    
                }else if(tokens.get(tokens.size()-1)== 1)
                {
                    tokens.add(20);
                    symbolos.add("Token Reconocido........Identificador Nombre del Programa " + token);
                }
                else
                {
                    JOptionPane.showConfirmDialog(null,"Error el token: " + token + " no es valido en esta gramatica ","Error", JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE);  
                    Error = "Error........El token " + token + " no es valido en esta gramatica";
                }
            }
        }
    }
    public void imprimeTokens(){
        for(int j = 0 ; j<tokens.size(); j++){
            System.out.println(tokens.get(j));
        }
    }
    public int tope = 0;
    public int getToken(){
        int aux = tope;
        tope++;
        return tokens.get(aux);
    }
}   
