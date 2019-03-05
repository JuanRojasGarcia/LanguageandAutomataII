/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto;
import ArbolSintactico.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Chuy
 */
public class ArbolSintactico {
    public ArrayList<Declarax> declaraciones = new ArrayList<Declarax>();
    public ArrayList<Statx> statements = new ArrayList<Statx>();
    public boolean eror = false;
    public Scanner scanner;
    public String Error = "Compilacion Exitosa!!";
    public int tkn=0;
    final int Class=1,DelIzq = 2,DelDer = 3,Boolean = 4,Int = 5,DL = 6,If = 7,Else=8,PD = 10;
    final int PI = 9,EQ = 11,mas=12,menos = 13,menor = 14,mayor = 15,multi = 16,True = 17;
    final int False = 18,SOP = 19 , ID = 20,INTEGER = 21,While = 22,VInteger = 23, VBoolean = 24;
    public String nomvar ;
    //DL es el punto y coma
    //DelIzq es { y DelDer es }
    //EQ ES =
    //PD es parentesis derecho y PI es parentesis izquierdo
    public Idx id1 = null;
    public Idx id2 = null;
    public String op = null;
    public ArrayList<String> estatutos = new ArrayList<String>();
    //Seccion de codigo Intermedio
    public ArrayList<Triple> triples = new ArrayList<Triple>();
    public int anterior;
    public int top = 1;
    public int retorno ;
    public ArbolSintactico(Scanner scanner){
        this.scanner = scanner;
        tkn = scanner.getToken();
        estatutos.add("Iniciando Creacion Del Arbol Sintactico");
    }
    public void imprimePrueba(){
        for(int j = 0 ; j < scanner.tokens.size();j++){
            System.out.println(scanner.tokens.get(j));
        }
    }
    public int tope = 1;
    public void advance() {
        tkn = scanner.tokens.get(tope);
        nomvar = scanner.tokString.get(tope);
        if(tope != scanner.tokens.size()-1){
            tope++;}
        
    }
       // tkn = scanner.getToken();
        /*
        if(scanner.tope == scanner.tokens.size()-1){
            JOptionPane.showMessageDialog(null,"Error, ya te comiste todo");
            
        }
    }*/
    
    public void eat(int t) {
       // int tokenEsperado = t;
       //System.out.println("Tope del Scanner = " + scanner.tope);
       //System.out.println("Token Esperado " + NomToken(t) + " Token Recibido " + NomToken(tkn));
       //estatutos.add("Token Esperado " + NomToken(t) + " Token Recibido " + NomToken(tkn));
        if(tkn == t) {
            //setLog("Token: " + token + "\n" + "Tipo:  "+ s.getTipoToken());
            advance();
        }
        else{
            //error(token, "token tipo:"+t);
            JOptionPane.showMessageDialog(null,"Error..... Se esperaba " + NomToken(t) + " no " + NomToken(tkn));
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
            default:
                return null;
        }
    }
    Programax p ;
    public void program(){
        try{
        switch(tkn){
            case Class:
                eat(Class);
                //System.out.println("Comi Class");
                eat(ID);
                //System.out.println("Comi Identificador");
                varDeclaration();
                //System.out.println("declare variables");
                eat(DelIzq);
                //System.out.println("{");
                //advance();
                System.out.println("Apunto de entrar a statement");
                while(tkn==While || tkn==SOP || tkn==VInteger || tkn == VBoolean)
        	{
        		statement();
        	}
                System.out.println("pase statement");
                eat(DelDer);
                System.out.println("ultimo }");
                p = new Programax(declaraciones,statements);
                estatutos.add("Se Genero Un Nuevo Programa");
                for(int k = 0; k <triples.size();k++){
                    System.out.println(triples.get(k).toString());
                }
                /*
                    System.out.println( triples.get(0).toString());
                    System.out.println( triples.get(1).toString());
                    System.out.println( triples.get(2).toString());
                    System.out.println( triples.get(3).toString());
                */
                JOptionPane.showMessageDialog(null,"Se Genero Un Nuevo Programa","Exito",JOptionPane.INFORMATION_MESSAGE);
                
                break;
            default :
                Error("Error en Program");
        }}catch(Exception g){
        System.out.println("ya termine...nos vemos " + g.getCause());
        }
    }
    public void varDeclaration(){
        String aux;
      switch (tkn){
          case Boolean:
              eat(Boolean);
              aux = nomvar;
              System.out.println(aux);
              eat(VBoolean);
              eat(DL);
              declaraciones.add(new Declarax(aux,new Typex("Boolean")));
              estatutos.add("Se Declaro una Variable Boolean");
              varDeclaration();
          break;
          case Int:
              eat(Int);
              aux = nomvar;
              eat(VInteger);
              eat(DL);
              declaraciones.add(new Declarax(aux,new Typex("Integer")));
              estatutos.add("Se Declaro una nueva Variable Integer");
              varDeclaration();
      }
    }
    public Statx statement(){
        String aux;
        String aux2;
        switch(tkn){
            case DelIzq:
            {
              Statx s1;
            	eat(DelIzq);
            	if(tkn!=0)
            	while(tkn==DelIzq  ||tkn==While || tkn==SOP || tkn==VInteger || tkn == VBoolean )
            	{
            		//System.out.println("Entro");
            		statement();
            	}  
                eat(DelDer);
            }return null;
            case While:
                Triple helper;
                boolean ismay = false,ismen= false ;
                int reve =0;
                int reve2 = 0 ;
                int revresta =0;
                System.out.println("Entre al while de statement");
                Expx ex;
                Statx s;
                eat(While);
                eat(PI);
                ex = expresion();
                Triple xc = new Triple();
                xc = triples.get(triples.size()-1);
                retorno = triples.size()-1;
                if("=".equals(xc.getOperacion())){
                    helper = new Triple(top,"-",xc.getV1(),xc.getV2());
                    top++;
                    triples.add(helper);
                    revresta = triples.size();
                    helper = new Triple(top,"JNZ","("+(top-1)+")","( )");
                    top++;
                    triples.add(helper);
                    reve = triples.size()-1;
                }
                 if("<".equals(xc.getOperacion())){
                    ismen = true ;
                    helper = new Triple(top,"-",xc.getV1(),xc.getV2());
                    top++;
                    triples.add(helper);
                    revresta = triples.size();
                    helper = new Triple(top,"JP","("+(top-1)+")","( )");
                    top++;
                    triples.add(helper);
                    reve = triples.size()-1;
                    helper = new Triple(top,"JZ","("+(top-2)+")","( )");
                    top++;
                    triples.add(helper);
                    reve2 = triples.size()-1;
                }
                 if(">".equals(xc.getOperacion())){
                    ismay = true ;
                    helper = new Triple(top,"-",xc.getV1(),xc.getV2());
                    top++;
                    triples.add(helper);
                    revresta = triples.size();
                    helper = new Triple(top,"JN","("+(top-1)+")","( )");
                    top++;
                    triples.add(helper);
                    reve = triples.size()-1;
                    helper = new Triple(top,"JZ","("+(top-2)+")","( )");
                    top++;
                    triples.add(helper);
                    reve2 = triples.size()-1;
                }
                eat(PD);
                //eat(DelIzq);
                s = statement();
                helper = new Triple(top,"JMP","("+(revresta)+")"," ");
                top++;
                triples.add(helper);
                Triple jm = new Triple();
                jm = triples.get(reve);
                jm.setV2("("+(top+1)+")");
                triples.set(reve, jm);
                
                if(ismen==true){
                    Triple jm2 = new Triple();
                    jm2 = triples.get(reve2);
                    jm2.setV2("("+(top+1)+")");
                    triples.set(reve2, jm2);
                }
                if(ismay==true){
                    Triple jm2 = new Triple();
                    jm2 = triples.get(reve2);
                    jm2.setV2("("+(top+1)+")");
                    triples.set(reve2, jm2);
                }
                statements.add(new Whilex(ex,s));
                estatutos.add("Se Genero un While ( E ) S");
                return new Whilex(ex,s);
            case SOP:
                Expx e;
                eat(SOP);
                eat(PI);
                e =expresion();
                eat(PD);
                eat(DL);
                statements.add(new Printx(e));
                Triple x1 = new Triple(top,"SOP","("+(top-1)+")"," ");
                top++;
                triples.add(x1);
                estatutos.add("Se Genero un nuevo System.out.println( E )");
                return new Printx(e);
            case VBoolean:
                Expx ebx;
                Idx a2 ;
                aux = nomvar;
                eat(VBoolean);
                eat(EQ);
                ebx = expresion();
                eat(DL);
                a2 =new Idx(aux,new Typex("Boolean"));
                statements.add(new Asignax(a2,ebx));
                Triple x3 = new Triple(top,"=",aux,"("+(top-1)+") ");
                top++;
                triples.add(x3);
                estatutos.add("Se Agino Un valor a una Variable Booleana");
                return new Asignax(a2,ebx);
            case VInteger:
                Expx eax;
                Idx a ;
                aux = nomvar;
                eat(VInteger);
                eat(EQ);
                eax = expresion();
                eat(DL);
                a = new Idx(aux,new Typex("Integer"));
                statements.add(new Asignax(a,eax));
                Triple x = new Triple(top,"=",aux,"("+(top-1)+") ");
                top++;
                triples.add(x);
                estatutos.add("Se Agino Un valor a una Variable Integer");
                return new Asignax(a,eax);
            default:
                return null;
        }
    }
    boolean primero = true;
    public Expx expresion(){
        Idx id1 = null,id2 = null;
        Expx e = null;
        String aux;
        String aux2;
        switch(tkn){
            case VInteger:
                aux = nomvar;
                eat(VInteger);
                id1 = new Idx(aux,new Typex("Integer"));
               // System.out.println("Valor del tkn" + tkn);
                switch (tkn) {
                    case EQ:
                        eat(EQ);
                        if(tkn==VInteger)
                        {
                            aux2 = nomvar;
                            eat(VInteger);
                            id2 = new Idx(aux2,new Typex("Integer"));
                            Triple x = new Triple(top,"=",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new IgualX(id1,id2);   
                        }
                        else
                        {
                            aux2 = nomvar;
                            eat(INTEGER);
                            id2 = new Idx(aux2,new Typex("Integer"));
                            Triple x = new Triple(top,"=",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new IgualX(id1,id2);
                        }
                        break;
                    case mas:
                        System.out.println("Entre al mas ");
                        eat(mas);
                        if(tkn==VInteger)
                        {                   
                            aux2 = nomvar;
                            eat(VInteger);
                            id2 = new Idx(aux2,new Typex("Integer"));
                            Triple x = new Triple(top,"+",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new Sumax(id1,id2);
                        }
                        else
                        {
                            //entro aqui porque el anterior era + y aqui se genera
                            //la suma 
                            aux2 = nomvar;
                            eat(INTEGER);
                            id2 = new Idx(aux,new Typex("Integer"));
                            Triple x = new Triple(top,"+",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new Sumax(id1,id2);
                        }
                        break;
                     case menos:
                        System.out.println("Entre al menos ");
                        eat(menos);
                        if(tkn==VInteger)
                        {                   
                            aux2 = nomvar;
                            eat(VInteger);
                            id2 = new Idx(aux2,new Typex("Integer"));
                            Triple x = new Triple(top,"-",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new Restax(id1,id2);
                        }
                        else
                        {
                            //entro aqui porque el anterior era + y aqui se genera
                            //la suma 
                            aux2 = nomvar;
                            eat(INTEGER);
                            id2 = new Idx(aux,new Typex("Integer"));
                            Triple x = new Triple(top,"-",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new Restax(id1,id2);
                        }
                        break;
                     case mayor:
                        System.out.println("Entre al mayor ");
                        eat(mayor);
                        if(tkn==VInteger)
                        {                   
                            aux2 = nomvar;
                            eat(VInteger);
                            id2 = new Idx(aux2,new Typex("Integer"));
                            Triple x = new Triple(top,">",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new Comparax(id1,id2);
                        }
                        else
                        {
                            //entro aqui porque el anterior era + y aqui se genera
                            //la suma 
                            aux2 = nomvar;
                            eat(INTEGER);
                            id2 = new Idx(aux,new Typex("Integer"));
                            Triple x = new Triple(top,">",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new Comparax(id1,id2);
                        }
                        break;
                    case menor:
                        System.out.println("Entre al menor ");
                        eat(menor);
                        if(tkn==VInteger)
                        {                   
                            aux2 = nomvar;
                            eat(VInteger);
                            id2 = new Idx(aux2,new Typex("Integer"));
                            Triple x = new Triple(top,"<",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new Comparax(id1,id2);
                        }
                        else
                        {
                            //entro aqui porque el anterior era + y aqui se genera
                            //la suma 
                            aux2 = nomvar;
                            eat(INTEGER);
                            id2 = new Idx(aux,new Typex("Integer"));
                            Triple x = new Triple(top,"<",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new Comparax(id1,id2);
                        }
                        break;
                    default:
                        //Entro aqui porque empezo con un num nomas
                        System.out.println("Expresion Sola ??");
                        if(tkn==VBoolean || tkn == True || tkn == False)
                        {
                                JOptionPane.showMessageDialog(null,"Error de tipo Boolean a Integer");
                        }
                        //aux2 = nomvar;
                        e = id1;
                        Triple x = new Triple(top,"=",aux," ");
                        top++;
                        triples.add(x);
                        break;
                        }
            break;
            case VBoolean:
                aux = nomvar;
                eat(VBoolean);
                id1 = new Idx(aux,new Typex("Boolean"));
               // System.out.println("Valor del tkn" + tkn);
                switch (tkn) {
                    case EQ:
                        eat(EQ);
                        if(tkn==VBoolean)
                        {
                            aux2 = nomvar;
                            eat(VBoolean);
                            id2 = new Idx(aux2,new Typex("Boolean"));
                            Triple x = new Triple(top,"=",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new IgualX(id1,id2);   
                        }
                        else
                        {
                            aux2 = nomvar;
                            eat(Boolean);
                            id2 = new Idx(aux2,new Typex("Boolean"));
                            Triple x = new Triple(top,"=",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new IgualX(id1,id2);
                        }
                        break;
                    case menor:
                        eat(menor);
                        if(tkn==VBoolean)
                        {
                            aux2 = nomvar;
                            eat(VBoolean);
                            id2 = new Idx(aux2,new Typex("Boolean"));
                            Triple x = new Triple(top,"<",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new Comparax(id1,id2);
                        }
                        else
                        {
                            aux2 = nomvar;
                            eat(Boolean);
                            id2 = new Idx(aux2,new Typex("Integer"));
                            Triple x = new Triple(top,"<",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new Comparax(id1,id2);
                        }
                        break;
                    case mayor:
                        eat(mayor);
                        if(tkn==VBoolean)
                        {
                            aux2 = nomvar;
                            eat(VBoolean);
                            id2 = new Idx(aux2,new Typex("Boolean"));
                            Triple x = new Triple(top,">",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new Comparax(id1,id2);
                        }
                        else
                        {
                            aux2 = nomvar;
                            eat(Boolean);
                            id2 = new Idx(aux2,new Typex("Integer"));
                            Triple x = new Triple(top,"<",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new Comparax(id1,id2);
                        }
                        break;
                    default:
                        //Entro aqui porque empezo con un num nomas
                        System.out.println("Expresion Booleana  Sola ??");
                        //aux2 = nomvar;
                        e = id1;
                        Triple x = new Triple(top,"=",aux," ");
                        top++;
                        triples.add(x);
                        break;
                        }
            break;
            //Caso de Ser Integer
             case INTEGER:
                aux = nomvar;
                eat(INTEGER);
                id1 = new Idx(aux,new Typex("Integer"));
               // System.out.println("Valor del tkn" + tkn);
                switch (tkn) {
                    case EQ:
                        eat(EQ);
                        if(tkn==VInteger)
                        {
                            aux2 = nomvar;
                            eat(VInteger);
                            id2 = new Idx(aux2,new Typex("Integer"));
                            Triple x = new Triple(top,"=",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new IgualX(id1,id2);   
                        }
                        else
                        {
                            aux2 = nomvar;
                            eat(INTEGER);
                            id2 = new Idx(aux2,new Typex("Integer"));
                            Triple x = new Triple(top,"=",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new IgualX(id1,id2);
                        }
                        break;
                    case mas:
                        System.out.println("Entre al mas ");
                        eat(mas);
                        if(tkn==VInteger)
                        {                   
                            aux2 = nomvar;
                            eat(VInteger);
                            id2 = new Idx(aux2,new Typex("Integer"));
                            Triple x = new Triple(top,"+",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new Sumax(id1,id2);
                        }
                        else
                        {
                            //entro aqui porque el anterior era + y aqui se genera
                            //la suma 
                            aux2 = nomvar;
                            eat(INTEGER);
                            id2 = new Idx(aux,new Typex("Integer"));
                            Triple x = new Triple(top,"+",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new Sumax(id1,id2);
                        }
                        break;
                     case menos:
                        System.out.println("Entre al menos ");
                        eat(menos);
                        if(tkn==VInteger)
                        {                   
                            aux2 = nomvar;
                            eat(VInteger);
                            id2 = new Idx(aux2,new Typex("Integer"));
                            Triple x = new Triple(top,"-",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new Restax(id1,id2);
                        }
                        else
                        {
                            //entro aqui porque el anterior era + y aqui se genera
                            //la suma 
                            aux2 = nomvar;
                            eat(INTEGER);
                            id2 = new Idx(aux,new Typex("Integer"));
                            Triple x = new Triple(top,"-",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new Restax(id1,id2);
                        }
                        break;
                    default:
                        //Entro aqui porque empezo con un num nomas
                        System.out.println("Expresion Sola ??");
                        //aux2 = nomvar;
                        e = id1;
                        Triple x = new Triple(top,"=",aux," ");
                        top++;
                        triples.add(x);
                        break;
                        }
            break;
            case True:
                aux = nomvar;
                eat(True);
                id1 = new Idx(aux,new Typex("Boolean"));
               // System.out.println("Valor del tkn" + tkn);
                switch (tkn) {
                    case EQ:
                        eat(EQ);
                        if(tkn==VBoolean)
                        {
                            aux2 = nomvar;
                            eat(VBoolean);
                            id2 = new Idx(aux2,new Typex("Boolean"));
                            Triple x = new Triple(top,"=",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new IgualX(id1,id2);   
                        }
                        else
                        {
                            aux2 = nomvar;
                            eat(Boolean);
                            id2 = new Idx(aux2,new Typex("Boolean"));
                            Triple x = new Triple(top,"=",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new IgualX(id1,id2);
                        }
                        break;
                    case menor:
                        eat(menor);
                        if(tkn==VBoolean)
                        {
                            aux2 = nomvar;
                            eat(VBoolean);
                            id2 = new Idx(aux2,new Typex("Boolean"));
                            Triple x = new Triple(top,"<",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new Comparax(id1,id2);
                        }
                        else
                        {
                            aux2 = nomvar;
                            eat(Boolean);
                            id2 = new Idx(aux2,new Typex("Integer"));
                            Triple x = new Triple(top,"<",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new Comparax(id1,id2);
                        }
                        break;
                    case mayor:
                        eat(mayor);
                        if(tkn==VBoolean)
                        {
                            aux2 = nomvar;
                            eat(VBoolean);
                            id2 = new Idx(aux2,new Typex("Boolean"));
                            Triple x = new Triple(top,">",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new Comparax(id1,id2);
                        }
                        else
                        {
                            aux2 = nomvar;
                            eat(Boolean);
                            id2 = new Idx(aux2,new Typex("Integer"));
                            Triple x = new Triple(top,"<",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new Comparax(id1,id2);
                        }
                        break;
                    default:
                        //Entro aqui porque empezo con un num nomas
                        System.out.println("Expresion Booleana  Sola ??");
                        //aux2 = nomvar;
                        e = id1;
                        Triple x = new Triple(top,"=",aux," ");
                        top++;
                        triples.add(x);
                        break;
                        }
            break;
              case False:
                aux = nomvar;
                eat(False);
                id1 = new Idx(aux,new Typex("Boolean"));
               // System.out.println("Valor del tkn" + tkn);
                switch (tkn) {
                    case EQ:
                        eat(EQ);
                        if(tkn==VBoolean)
                        {
                            aux2 = nomvar;
                            eat(VBoolean);
                            id2 = new Idx(aux2,new Typex("Boolean"));
                            Triple x = new Triple(top,"=",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new IgualX(id1,id2);   
                        }
                        else
                        {
                            aux2 = nomvar;
                            eat(Boolean);
                            id2 = new Idx(aux2,new Typex("Boolean"));
                            Triple x = new Triple(top,"=",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new IgualX(id1,id2);
                        }
                        break;
                    case menor:
                        eat(menor);
                        if(tkn==VBoolean)
                        {
                            aux2 = nomvar;
                            eat(VBoolean);
                            id2 = new Idx(aux2,new Typex("Boolean"));
                            Triple x = new Triple(top,"<",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new Comparax(id1,id2);
                        }
                        else
                        {
                            aux2 = nomvar;
                            eat(Boolean);
                            id2 = new Idx(aux2,new Typex("Integer"));
                            Triple x = new Triple(top,"<",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new Comparax(id1,id2);
                        }
                        break;
                    case mayor:
                        eat(mayor);
                        if(tkn==VBoolean)
                        {
                            aux2 = nomvar;
                            eat(VBoolean);
                            id2 = new Idx(aux2,new Typex("Boolean"));
                            Triple x = new Triple(top,">",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new Comparax(id1,id2);
                        }
                        else
                        {
                            aux2 = nomvar;
                            eat(Boolean);
                            id2 = new Idx(aux2,new Typex("Integer"));
                            Triple x = new Triple(top,"<",aux," "+aux2);
                            top++;
                            triples.add(x);
                            e = new Comparax(id1,id2);
                        }
                        break;
                    default:
                        //Entro aqui porque empezo con un num nomas
                        System.out.println("Expresion Booleana  Sola ??");
                        //aux2 = nomvar;
                        e = id1;
                        Triple x = new Triple(top,"=",aux," ");
                        top++;
                        triples.add(x);
                        break;
                        }
            break;
            default:
        }
        estatutos.add("Se Genero Una Nueva Expresion");
        return e;
    }
     public void Error(String tipo){
           JOptionPane.showMessageDialog(null,"Error");
    }
}
/*
*/
