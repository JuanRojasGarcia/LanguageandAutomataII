public class Asignax extends Statx {
	private Idx s1;
	private Expx s2; 
	
	public Asignax(Idx st1, Expx st2)
	{
		s1=st1; s2=st2;  
	}
}


public class Comparax extends Expx {
	private Expx s1;
	private Expx s2; 
	
	public Comparax(Expx st1, Expx st2)
	{
		s1=st1; s2=st2;  
	}
}


class CreaAST{
    public static void main(String[] a){
	 //Declarax p = new Declarax(new Typex("Int"), "x"); 
	
	Programax p = new Programax(new Declarax(new Typex("Int"), "x"),
		new Ifx(new Comparax(new Idx("a"), new Idx("b")), 
			new Asignax(new Idx("c"), new Idx("d")),
			new Asignax(new Idx("e"), new Idx("f"))));
			
    }
}


class CreaAST2{
    public static void main(String[] a){
	 //Declarax p = new Declarax(new Typex("Int"), "x"); 
	
	Programax p = new Programax(new Declarax(new Typex("Int"), "x"),
		new Ifx(new Comparax(new Idx("a"), new Idx("b")), 
			new Asignax(new Idx("c"), new Idx("d")),
			new Asignax(new Idx("e"), new Idx("f"))));

	
	
	VisitaArbol(p);	
    }
    public static int VisitaArbol(Programax dx){
	return 0;
    }

}

public class Declarax {
	public Typex s1; 
	public String s2; 
	public Declarax(Typex st1, String st2)
	{
		s1=st1; s2=st2;  
	}
}


public abstract class Expx { }

public class Idx extends Expx {
	private String s1; 
	
	public Idx(String st1)
	{
		s1=st1;  
	}
}

public class Ifx extends Statx {
	private Expx s1; 
	private Statx s2;
 	private Statx s3;
	public Ifx(Expx st1, Statx st2, Statx st3)
	{
		s1=st1; s2=st2; s3=st3; 
	}
}

public class Programax {
	private Declarax s1; 
	private Statx s2; 
	public Programax(Declarax st1, Statx st2)
	{
		s1=st1; s2=st2;  
	}
}

public abstract class Statx { }

public class Typex  {
	private String s1; 
	
	public Typex(String st1)
	{
		s1=st1;  
	}
}


