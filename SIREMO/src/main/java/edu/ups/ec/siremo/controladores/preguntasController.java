package edu.ups.ec.siremo.controladores;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.jpl7.Query;

import edu.ups.ec.siremo.controladores.se.PrologTest;



@ManagedBean
@RequestScoped
public class preguntasController {

	private String respuesta1="";
	private String respuesta2="";
	private String respuesta3="";
	private String respuesta4="";
	private String respuesta5="";
	private String respuesta6="";
	private String respuesta7="";
	private String respuesta8="";
	private String respuesta9="";
	private String respuesta10="";
	private String respuesta11="";
	private String respuesta12="";
	private String respuesta13="";
	
	@Inject
    private FacesContext facesContext;
	
	
	@PostConstruct
	public void init() {
		
	}


	public String getRespuesta1() {
		System.out.println("entro GET res 1 ="+respuesta1);
		return respuesta1;
	}


	public void setRespuesta1(String respuesta1) {
		System.out.println("entro SET res 1 ="+respuesta1);
		if(respuesta1!=null) {
		this.respuesta1 = respuesta1;
		}
	}


	public String getRespuesta2() {
		System.out.println("entro GET res 2");
		return respuesta2;
	}


	public void setRespuesta2(String respuesta2) {
		System.out.println("entro SET res 2");
		if(respuesta2!=null) {
		this.respuesta2 = respuesta2;
		}
	}


	public String getRespuesta3() {
		System.out.println("entro GET res 3 ="+respuesta3);
		return respuesta3;
	}


	public void setRespuesta3(String respuesta3) {
		System.out.println("entro SET res 3 ="+respuesta3);
		if(respuesta3!=null) {
		this.respuesta3 = respuesta3;
		}
	}


	public String getRespuesta4() {
		System.out.println("entro GET res 4 ="+respuesta4);
		return respuesta4;
	}


	public void setRespuesta4(String respuesta4) {
		System.out.println("entro SET res 5 ="+respuesta5);
		if(respuesta4!=null) {
		this.respuesta4 = respuesta4;
		}
	}


	public String getRespuesta5() {
		return respuesta5;
	}


	public void setRespuesta5(String respuesta5) {
		if(respuesta5!=null) {
		this.respuesta5 = respuesta5;
		}
	}


	public String getRespuesta6() {
		return respuesta6;
	}


	public void setRespuesta6(String respuesta6) {
		if(respuesta6!=null) {
		this.respuesta6 = respuesta6;
		}
	}


	public String getRespuesta7() {
		return respuesta7;
	}


	public void setRespuesta7(String respuesta7) {
		if(respuesta7!=null) {
		this.respuesta7 = respuesta7;
		}
	}


	public String getRespuesta8() {
		return respuesta8;
	}


	public void setRespuesta8(String respuesta8) {
		if(respuesta8!=null) {
		this.respuesta8 = respuesta8;
		}
	}


	public String getRespuesta9() {
		return respuesta9;
	}


	public void setRespuesta9(String respuesta9) {
		if(respuesta9!=null) {
		this.respuesta9 = respuesta9;
		}
	}


	public String getRespuesta10() {
		return respuesta10;
	}


	public void setRespuesta10(String respuesta10) {
		if(respuesta10!=null) {
		this.respuesta10 = respuesta10;
		}
	}


	public String getRespuesta11() {
		return respuesta11;
	}


	public void setRespuesta11(String respuesta11) {
		if(respuesta11!=null) {
		this.respuesta11 = respuesta11;
		}
		}


	public String getRespuesta12() {
		return respuesta12;
	}


	public void setRespuesta12(String respuesta12) {
		System.out.println("entro SET res 12 ="+respuesta12);
		if(respuesta12!=null) {
		this.respuesta12 = respuesta12;
		}
	}


	public String getRespuesta13() {
		System.out.println("entro GET res 13 ="+respuesta13);
		return respuesta13;
	}


	public void setRespuesta13(String respuesta13) {
		
		if(respuesta13!=null) {
		System.out.println("entro SET res 13 ="+respuesta13);
		this.respuesta13 = respuesta13;
		}
		
	}
	
	


	public String calcularEstilo() {
		
		boolean resValidacion = validarRespuestasContes();
		System.out.println("ALV "+resValidacion);
		
		if(resValidacion) {
			sacarEstiloProlog();
		}
		
		try {

		}catch (Exception e) {
		String errorMessage = getRootErrorMessage(e);
	    FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
	    facesContext.addMessage(null, m);
	    
	    
		}
		
		return "";
	}
	
	public boolean validarRespuestasContes() {
		
		System.out.println("R1 ="+respuesta1+" R2="+respuesta2+" R3="+respuesta3);
		
		if(!respuesta1.equals("")&&!respuesta2.equals("")&&!respuesta3.equals("")&&!respuesta4.equals("")&&
				!respuesta5.equals("")&&!respuesta6.equals("")&&!respuesta7.equals("")&&!respuesta8.equals("")&&
				!respuesta9.equals("")&&!respuesta10.equals("")&&!respuesta11.equals("")&&!respuesta12.equals("")&&
				!respuesta13.equals("")) {
			return true;
		}else {
			return false;
		}
	}
	
	public void sacarEstiloProlog() {
		
		Query q = new Query("consult('basedeconocimiento.pl').");
		if(q.hasSolution()) {
			System.out.println("Cargada");
		}else {
			System.out.println("Error, no se pudo cargar la base.");
		}
		
		System.out.println("Ease.");
		
		String aux="0,0,0";
		
		for (int i = 0; i < 13; i++) {
			System.out.println("VA A SACAR EL PINCHE ESTILOS ");
			
			PrologTest test = new PrologTest();
			aux=test.preguntasTest("contadorVotos("+(i+1)+",1,"+aux+",AuxRe,AuxVa,AuxPu).");
			System.out.println("mm "+aux);
				
		}
		
	}
	
//	public String pregunta2() {
//		
//		System.out.println("ALV");
//		try {
//
//		}catch (Exception e) {
//		String errorMessage = getRootErrorMessage(e);
//	    FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
//	    facesContext.addMessage(null, m);
//	    
//	    
//		}
//		
//		return "#pregunta_3";
//	}
//	public String pregunta3() {
//		
//		System.out.println("ALV");
//		try {
//
//		}catch (Exception e) {
//		String errorMessage = getRootErrorMessage(e);
//	    FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
//	    facesContext.addMessage(null, m);
//	    
//	    
//		}
//		
//		return "#pregunta_3";
//	}
//	public String pregunta4() {
//		
//		System.out.println("ALV");
//		try {
//
//		}catch (Exception e) {
//		String errorMessage = getRootErrorMessage(e);
//	    FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
//	    facesContext.addMessage(null, m);
//	    
//	    
//		}
//		
//		return "#pregunta_5";
//	}
//	public String pregunta6() {
//		
//		System.out.println("ALV");
//		try {
//
//		}catch (Exception e) {
//		String errorMessage = getRootErrorMessage(e);
//	    FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
//	    facesContext.addMessage(null, m);
//	    
//	    
//		}
//		
//		return "#pregunta_7";
//	}
//	public String pregunta7() {
//		
//		System.out.println("ALV");
//		try {
//
//		}catch (Exception e) {
//		String errorMessage = getRootErrorMessage(e);
//	    FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
//	    facesContext.addMessage(null, m);
//	    
//	    
//		}
//		
//		return "#pregunta_8";
//	}
//	public String pregunta8() {
//		
//		System.out.println("ALV");
//		try {
//
//		}catch (Exception e) {
//		String errorMessage = getRootErrorMessage(e);
//	    FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
//	    facesContext.addMessage(null, m);
//	    
//	    
//		}
//		
//		return "#pregunta_9";
//	}
//	public String pregunta9() {
//		
//		System.out.println("ALV");
//		try {
//
//		}catch (Exception e) {
//		String errorMessage = getRootErrorMessage(e);
//	    FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
//	    facesContext.addMessage(null, m);
//	    
//	    
//		}
//		
//		return "#pregunta_10";
//	}
//	public String pregunta10() {
//		
//		System.out.println("ALV");
//		try {
//
//		}catch (Exception e) {
//		String errorMessage = getRootErrorMessage(e);
//	    FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
//	    facesContext.addMessage(null, m);
//	    
//	    
//		}
//		
//		return "#pregunta_11";
//	}
//	public String pregunta11() {
//		
//		System.out.println("ALV");
//		try {
//
//		}catch (Exception e) {
//		String errorMessage = getRootErrorMessage(e);
//	    FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
//	    facesContext.addMessage(null, m);
//	    
//	    
//		}
//		
//		return "#pregunta_12";
//	}
//	public String pregunta12() {
//		
//		System.out.println("ALV");
//		try {
//
//		}catch (Exception e) {
//		String errorMessage = getRootErrorMessage(e);
//	    FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
//	    facesContext.addMessage(null, m);
//	    
//	    
//		}
//		
//		return "#pregunta_13";
//	}
//	public String pregunta13() {
//		
//		System.out.println("ALV");
//		try {
//
//		}catch (Exception e) {
//		String errorMessage = getRootErrorMessage(e);
//	    FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
//	    facesContext.addMessage(null, m);
//	    
//	    
//		}
//		
//		return "#respuesta";
//	}

	private String getRootErrorMessage(Exception e) {
        // Default to general error message that registration failed.
        String errorMessage = "Registration failed. See server log for more information";
        if (e == null) {
            // This shouldn't happen, but return the default messages
            return errorMessage;
        }

        // Start with the exception and recurse to find the root cause
        Throwable t = e;
        while (t != null) {
            // Get the message from the Throwable class instance
            errorMessage = t.getLocalizedMessage();
            t = t.getCause();
        }
        // This is the root cause message
        return errorMessage;
    }
	
	
}
