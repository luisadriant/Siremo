package edu.ups.ec.siremo.controladores;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;



@ManagedBean
@RequestScoped
public class preguntasController {

	private String respuestas[];
	
	@Inject
    private FacesContext facesContext;
	
	
	
	@PostConstruct
	public void init() {

		
	}
	
	



	public String[] getRespuestas() {
		
		return respuestas;
	}


	public void setRespuestas(String[] respuestas) {

		this.respuestas = respuestas;
	}


	public String Guardar() {
		
		
		
		try {

		}catch (Exception e) {
		String errorMessage = getRootErrorMessage(e);
	    FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
	    facesContext.addMessage(null, m);
	    return null;
			
		}
		
		return "preguntaTest_2";
	}
	
	
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
