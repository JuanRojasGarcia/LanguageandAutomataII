package Proyecto;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
public class Archivo {
    private static File F;
    private static FileReader fr;
    private static BufferedReader br;
    private static FileWriter fw;
    private static PrintWriter pw;
    public static void AbrirArchivo(File Nombre,char rw) throws IOException{
        F = Nombre;
        if(! F.exists())
        {
            F.createNewFile();
        }
        if(rw=='r'||rw == 'R')
        {
            fw = new FileWriter(F,true);
            pw = new PrintWriter(fw);
        }
        else 
        {
        fw = new FileWriter(F,true);
        pw = new PrintWriter(fw);
        }
    }
    public static void AbrirArchivo(String Nombre,char rw) throws IOException{
        F = new File(Nombre);
        if(! F.exists())
        {
            F.createNewFile();
        }
        if(rw=='r'||rw == 'R')
        {
            fr = new FileReader(F);
            br = new BufferedReader(fr);
        }
        else 
        {
        fw = new FileWriter(F,true);
        pw = new PrintWriter(fw);
        }
    }
    public static void CerrarArchivo()throws IOException{
        if(fr!= null)
            fr.close();
        if(fw!= null)
            fw.close();
    }
    public static void AgregaRegistro(String cadena)throws IOException{
        pw.println(cadena);
    }
    public static String LeeContenido()throws IOException{
    String exit = "";
    String Line=br.readLine();
    exit+=Line+"\n";
    while(Line!= null){
        //contenido.add(Line2Reg(Line));
        Line = br.readLine();
        if(Line!=null)
        exit+=Line+"\n";
    }    
    return exit;
    }
}
