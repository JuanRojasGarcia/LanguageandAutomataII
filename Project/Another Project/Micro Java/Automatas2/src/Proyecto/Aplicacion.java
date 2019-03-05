/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;
public class Aplicacion extends JFrame implements ActionListener{
    private JPanel centro;
    JToolBar jtbMainP;
    JButton jbtnNuevo,jbtnGuardar,jbtnAbrir,JbtnSalir,Scan,parser,TreeMode,JTriple;
    JTextArea textarea1,weadetokens;
    JLabel jlerror;
    public Scanner scanner = new Scanner();
    public Parser pars;
    ArbolSintactico tree;
    private void bueno(){
        centro = new JPanel();
        centro.setLayout(null);
        textarea1=new JTextArea();
        textarea1.setFont(new Font("Consolas",Font.PLAIN,17));
        JScrollPane scrollpane1=new JScrollPane(textarea1);
        scrollpane1.setBounds(10,50,300,400);
        add(scrollpane1);
        centro.add(scrollpane1);
        JLabel eti = new JLabel("Programa");
        eti.setBounds(10,20,100,30);
        centro.add(eti);
        Scan = new JButton("SCAN");
        Scan.addActionListener(this);
        Scan.setBounds(320,50,100,30);
        centro.add(Scan);
        parser = new JButton("PARSER");
        parser.addActionListener(this);
        parser.setBounds(320,90,100,30);
        centro.add(parser);
        TreeMode = new JButton("TREE");
        TreeMode.setBounds(320,130,100,30);
        TreeMode.addActionListener(this);
        centro.add(TreeMode);
        JTriple = new JButton("TRIPLES");
        JTriple.setBounds(320,170,100,30);
        JTriple.addActionListener(this);
        centro.add(JTriple);
        jlerror = new JLabel("--");
        jlerror.setBounds(320,0,400,30);
        centro.add(jlerror);
        weadetokens=new JTextArea();
        weadetokens.setFont(new Font("Arial",Font.BOLD,12));
        
        JScrollPane scrollpane2=new JScrollPane(weadetokens);
        scrollpane2.setBounds(430,50,400,400);
        
        add(scrollpane2);
        centro.add(scrollpane2);
        JLabel ea = new JLabel("Tokens");
        ea.setBounds(430,20,100,30);
        centro.add(ea);
        add(centro,BorderLayout.CENTER);
    }
    private void BarraHerramientas() {
        //configuracion general
        jtbMainP = new JToolBar();
	jtbMainP.setFloatable(false);
	jtbMainP.setToolTipText("Barra De Herramientas Principal");//muestra un globo donde aparrese el nombre  cuando se pone el cursor
        //jtbMainP.setBackground(Color.DARK_GRAY);
        //contenido
        //nuevo
        jbtnNuevo = new JButton("Nuevo");
	jbtnNuevo.addActionListener(this);
	jbtnNuevo.setIcon( new ImageIcon("new2.jpg"));
	jbtnNuevo.setToolTipText("Crea Un Nuevo Archivo Laplace");
	jtbMainP.add(jbtnNuevo);
        //abrir
        jbtnAbrir = new JButton("Abrir");
	jbtnAbrir.addActionListener(this);
	jbtnAbrir.setIcon(new ImageIcon("open3.jpg"));
	jbtnAbrir.setToolTipText("Abre Un Archivo Valido");
	jtbMainP.add(jbtnAbrir);
        //guardar
        jbtnGuardar = new JButton("Guardar");
	jbtnGuardar.setIcon(new ImageIcon("save2.jpg"));
	jbtnGuardar.addActionListener(this);
	jbtnGuardar.setToolTipText("Guardar el Archivo");
	jtbMainP.add(jbtnGuardar);
        //salir
        JbtnSalir = new JButton("Salir");
	JbtnSalir.addActionListener(this);
	JbtnSalir.setIcon(new ImageIcon("exit2.jpg"));
	JbtnSalir.setToolTipText("Salimos De La Aplicacion");
	jtbMainP.add(JbtnSalir);
	//jimagestretch
	add(jtbMainP,BorderLayout.NORTH);	
	}
    public Aplicacion(){
        super();
	setLayout(new BorderLayout());
	setSize(new Dimension(850,530));
	setLocationRelativeTo(null);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(true);
	setTitle("Compilador Lenguaje Micro Java.............. Lenguajes Y Automatas 2");
        BarraHerramientas();
        bueno();
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       if(e.getSource()==Scan){
           if(textarea1.getText().equals("")){
               JOptionPane.showMessageDialog(null,"No puedes Compilar Codigo en Blanco");
           }else
           {    
               try{
               scanner.tokString.clear();
               scanner.variablesint.clear();
               scanner.variablesbool.clear();
               scanner.tokens.clear();
               scanner.symbolos.clear();
               scanner.Error = "No se Encontraron Errores con los tokens";
               scanner.Scanear(textarea1.getText());
               String tok = "";
               for(int j=0 ;j<scanner.symbolos.size();j++){
                  tok += scanner.symbolos.get(j) + "\n";
               }
               weadetokens.setText(tok);
               jlerror.setText(scanner.Error);
               }catch(Exception err){
                   //JOptionPane.showMessageDialog(null,"Erro por? :"+err.getMessage());
                   JOptionPane.showMessageDialog(null,"Error las clases deben de iniciar con la palabra class");
               }
           }
       }
       if(e.getSource()==parser){
           if(scanner.tokens.isEmpty()){
               JOptionPane.showMessageDialog(null,"No Has Realizado El proceso de Scanear aun");
               return;
           }
           try{
           scanner.tope = 0;
           pars = new Parser(scanner);
           pars.imprimePrueba();
           pars.program();
           String tok2 = "";
               for(int j=0 ;j<pars.estatutos.size();j++){
                  tok2 += pars.estatutos.get(j) + "\n";
               }
           weadetokens.setText(tok2);
           if(pars.eror)
               jlerror.setText("Proceso De Parsing Terminado con Errores");
           }catch(Exception er){
               //JOptionPane.showMessageDialog(null,er.getMessage());
               if(er.getMessage().contains("Index") && pars.eror==false){
               //JOptionPane.showMessageDialog(null,er.getMessage());
               String tok = "";
               for(int j=0 ;j<pars.estatutos.size();j++){
                  tok += pars.estatutos.get(j) + "\n";
               }
               weadetokens.setText(tok);
               JOptionPane.showMessageDialog(null,"Proceso de Parsing terminado con exito");
               jlerror.setText("Proceso de Parsing Terminado Con Exito");
               }
               else{
                   if(pars.eror){
                   JOptionPane.showMessageDialog(null,"Proceso Terminado Con Errores");
                   jlerror.setText("Proceso Terminado Con Errores");
                   }else{
                       JOptionPane.showMessageDialog(null,"Error en: " + er.getMessage());
                         jlerror.setText("Proceso Terminado Con Errores");
                   }
               }
           }
       }
       if(e.getSource()==TreeMode){
           if(scanner.tokens.isEmpty()){
               JOptionPane.showMessageDialog(null,"No Has Realizado El proceso de Scanear aun");
               return;
           }
           try{
           scanner.tope = 0;
           tree= new ArbolSintactico(scanner);
           //pars.imprimePrueba();
           tree.program();
           String tok2 = "*****Tabla de Simbolos******** \n";
                 tok2 += "Tipo           Nombre          \n";
           for(int j=0 ;j<tree.declaraciones.size();j++){
                  tok2 += tree.declaraciones.get(j).s2.getTypex() +"     "+ tree.declaraciones.get(j).s1 + "\n";
            }
           tok2 += "********************************** \n";
            for(int j=0 ;j<tree.estatutos.size();j++){
                  tok2 += tree.estatutos.get(j) + "\n";
               }
           weadetokens.setText(tok2);
           if(tree.eror){}
              // jlerror.setText("Proceso De Parsing Terminado con Errores");}
           }catch(Exception er){
               JOptionPane.showMessageDialog(null,"La wea Mostro El Siguiente Error uwu: "+er.getMessage());
               if(er.getMessage().contains("Index") && tree.eror==false){
               //JOptionPane.showMessageDialog(null,er.getMessage());
               String tok = "";
               for(int j=0 ;j<tree.estatutos.size();j++){
                  tok += tree.estatutos.get(j) + "\n";
               }
               weadetokens.setText(tok);
               JOptionPane.showMessageDialog(null,"Proceso de Arbol Sintactico terminado con exito");
               jlerror.setText("Se Creo El Arbol Sintactico Con Exito");
               }
               else{
                   if(pars.eror){
                   JOptionPane.showMessageDialog(null,"Ocurrio Un Error al Generar el arbol");
                   jlerror.setText("Proceso Terminado Con Errores");
                   }else{
                       JOptionPane.showMessageDialog(null,"Error en: " + er.getMessage());
                         jlerror.setText("Proceso Terminado Con Errores");
                   }
               }
           }
       }
       if(e.getSource()==JTriple){
           if(scanner.tokens.isEmpty()){
               JOptionPane.showMessageDialog(null,"No Has Realizado El proceso de Scanear aun");
               return;
           }
            try{
           scanner.tope = 0;
           tree= new ArbolSintactico(scanner);
           //pars.imprimePrueba();
           tree.program();
           String tok2 = "*****Tabla de Simbolos******** \n";
                 tok2 += "Tipo           Nombre          \n";
           for(int j=0 ;j<tree.declaraciones.size();j++){
                  tok2 += tree.declaraciones.get(j).s2.getTypex() +"     "+ tree.declaraciones.get(j).s1 + "\n";
            }
           tok2 += "********************************** \n";
            tok2 += "#\t Operacion\tValor 1\t Valor 2 \n";
            for(int j=0 ;j<tree.triples.size();j++){
                  tok2 += tree.triples.get(j) + "\n";
               }
           weadetokens.setText(tok2);
           jlerror.setText("Se Genero El Siguiente Codigo En Triples");
           if(tree.eror){}
              // jlerror.setText("Proceso De Parsing Terminado con Errores");}
           }catch(Exception er){
               JOptionPane.showMessageDialog(null,"La wea Mostro El Siguiente Error uwu: "+er.getMessage());
               if(er.getMessage().contains("Index") && tree.eror==false){
               //JOptionPane.showMessageDialog(null,er.getMessage());
               String tok = "";
               for(int j=0 ;j<tree.estatutos.size();j++){
                  tok += tree.estatutos.get(j) + "\n";
               }
               weadetokens.setText(tok);
               JOptionPane.showMessageDialog(null,"Proceso de Arbol Sintactico terminado con exito");
               jlerror.setText("Se Creo El Arbol Sintactico Con Exito");
               }
               else{
                   if(pars.eror){
                   JOptionPane.showMessageDialog(null,"Ocurrio Un Error al Generar el arbol");
                   jlerror.setText("Proceso Terminado Con Errores");
                   }else{
                       JOptionPane.showMessageDialog(null,"Error en: " + er.getMessage());
                         jlerror.setText("Proceso Terminado Con Errores");
                   }
               }
           }
       }
       if(e.getSource()==jbtnNuevo){
           textarea1.setText("");
           weadetokens.setText("");
           jlerror.setText("--");
           scanner.tokens.clear();
           scanner.variablesbool.clear();
           scanner.variablesint.clear();
       }
       if(e.getSource()==JbtnSalir){
           JOptionPane.showMessageDialog(this,"Saliendo de la aplicacion");
           System.exit(0);
       }
       if(e.getSource()==jbtnGuardar){
           if(textarea1.getText().equals("")){
               JOptionPane.showMessageDialog(this,"No se puede guardar codigo en blanco");
           }else{
               JFileChooser salvar = new JFileChooser();
               salvar.showSaveDialog(this);
               File NomArch = salvar.getSelectedFile();
               if(salvar.getSelectedFile().getName().contains(".lap")==false){
               //JOptionPane.showMessageDialog(null,"No puedes Guardar Un archivo sin extension .mpp", "Aviso del sistema" , JOptionPane.OK_OPTION);// + Carpeta , "Aviso del sistema" , JOptionPane.OK_OPTION);           
               //return;
               NomArch = new File(salvar.getSelectedFile().getAbsolutePath()+".lap");
                 }
               JOptionPane.showMessageDialog(null,"El archivo seleccionado es " + NomArch.getName(), "Aviso del sistema" , JOptionPane.OK_OPTION);// + Carpeta , "Aviso del sistema" , JOptionPane.OK_OPTION);           
               try{
                    Archivo.AbrirArchivo(NomArch,'w');
                    Archivo.AgregaRegistro(textarea1.getText()+"\n");
                    Archivo.CerrarArchivo();
                    }
               catch (IOException err){
                    JOptionPane.showMessageDialog(null,"Error al guardar el archivo " + NomArch.getName() + err.getMessage()); 
                    }
                }
           }
       if(e.getSource()==jbtnAbrir){
                    //JOptionPane.showMessageDialog(null,"algo debe de ");
                    JFileChooser chooser = new JFileChooser();
                    chooser.setDialogTitle("Seleccionar archivo");
                    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                     chooser.setCurrentDirectory(new File("C:"));
                     chooser.setAcceptAllFileFilterUsed(true);
                     FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagenes","lap");
                     chooser.setFileFilter(filter);
                     if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
                     String NomImg = chooser.getSelectedFile().getPath();
                    JOptionPane.showMessageDialog(null,NomImg);
                     try{
                         Archivo.AbrirArchivo(NomImg, 'r');
                         textarea1.setText(Archivo.LeeContenido()+"\n");
                         Archivo.CerrarArchivo();
                     }catch(IOException err){
                     JOptionPane.showMessageDialog(null,"Error" + err.getMessage() );
                     }
                     catch(NullPointerException err){
                      JOptionPane.showMessageDialog(null,"Erro null pointer:  " + err.getMessage() );
                     }
                     catch (Exception err ){
                     JOptionPane.showMessageDialog(null,"Error: Archivo No Valido " + err.getMessage() );
                     }
                     }
                }
       }
    public static void main(String[] args){
        new Aplicacion();
    }
}
