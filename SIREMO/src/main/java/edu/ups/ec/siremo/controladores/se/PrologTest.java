package edu.ups.ec.siremo.controladores.se;
import java.util.Map;

import org.jpl7.JPLException;
import org.jpl7.Query;
import org.jpl7.Term;

public class PrologTest {
	
	public String preguntasTest(String query) {
		String respuesta="";

  		Query q = new Query("consult('basedeconocimiento.pl')");
		if(q.hasSolution()) {
			System.out.println("Cargada");
		}else {
			System.out.println("Error, no se pudo cargar la base de conocimiento.");
		}
		try {
			Query q2 = new Query(query);
			System.out.println(q2.hasSolution()? "verdadero":"falso");
	        Map<String, Term>[] solutions = q2.allSolutions();
	 
//	        System.out.println(solutions[0].get("AuxRe"));
//	        System.out.println(solutions[0].get("AuxVa"));
//	        System.out.println(solutions[0].get("AuxPu"));
	        respuesta=""+solutions[0].get("AuxRe")+","+solutions[0].get("AuxVa")+","+solutions[0].get("AuxPu");
		}catch(JPLException e) {
			System.out.println(e.getMessage());
		}
		return respuesta;
		
        
	}
	public String[] solucion(String query) {
		String caracter[]=new String[2];
		Query q = new Query("consult('basedeconocimiento.pl')");
		if(q.hasSolution()) {
			System.out.println("Cargada");
		}else {
			System.out.println("Error, no se pudo cargar la base.");
		}
		try {
			Query q2 = new Query(query);
			System.out.println(q2.hasSolution()? "verdadero":"falso");
	        Map<String, Term>[] solutions = q2.allSolutions();
	        System.out.println(solutions[0].get("C"));
	        System.out.println(solutions[0].get("D"));
	        caracter[0]=""+solutions[0].get("C");
	        caracter[1]=""+solutions[0].get("D");
	       
		}catch(JPLException e) {
			System.out.println(e.getMessage());
		}
		
		return caracter;
	}
	
}
