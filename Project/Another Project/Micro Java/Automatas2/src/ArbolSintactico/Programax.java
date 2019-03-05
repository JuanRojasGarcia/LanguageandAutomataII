package ArbolSintactico;
import java.util.ArrayList;
import java.util.Vector;

public class Programax {
    //private Declarax s1;
    /*
    private Vector s1;
    private Statx s2;
    
    public Programax(Vector st1, Statx st2) {
        s1 = st1;
        s2 = st2;
    }
    
    public Vector getDeclaration() {return s1;}
    
    public Statx getStatement() {return s2;}
*/
    private ArrayList<Declarax> declaraciones;
    private ArrayList<Statx> program = new ArrayList<Statx>();
    public Programax(ArrayList<Declarax> declaraciones,ArrayList<Statx> program){
            this.declaraciones = declaraciones;
            this.program = program;
    }
}
