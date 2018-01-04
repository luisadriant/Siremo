package edu.ups.ec.siremo.controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import edu.ups.ec.siremo.algoritmo.FuzzyCMeans;
import edu.ups.ec.siremo.algoritmo.Recomendacion;
import edu.ups.ec.siremo.dao.EmpresaDao;
import edu.ups.ec.siremo.dao.MarcaDao;
import edu.ups.ec.siremo.dao.PrediccionesDao;
import edu.ups.ec.siremo.dao.UsuarioDao;
import edu.ups.ec.siremo.dao.VestimentaDao;
import edu.ups.ec.siremo.dao.VotosDao;
import edu.ups.ec.siremo.modelo.Empresa;
import edu.ups.ec.siremo.modelo.Marca;
import edu.ups.ec.siremo.modelo.Persona;
import edu.ups.ec.siremo.modelo.PrediccionesFCM;
import edu.ups.ec.siremo.modelo.Usuario;
import edu.ups.ec.siremo.modelo.Vestimenta;
import edu.ups.ec.siremo.modelo.Votos;
import edu.ups.ec.siremo.util.ErrorsController;

/**
 * Esta clase sirve para enlazar la vista del archivo xhtml con el controlador de la pagi recomendaciones 
 * @author Boris
 *
 */
@ManagedBean
@ViewScoped
public class PrincipalController {

	// Instanciamos la clase que controla los errores con su respectivo inject.
	ErrorsController error = new ErrorsController();
	@Inject
	private FacesContext facesContext;
	
	//variables necesarias
	private List<Vestimenta> vestimentas;
	private int idUsuario=5;
	 
//	private Vestimenta ves;
	//instanciamos en objeto de acceso a datos para poder injectar los metodos crud correspondiente al Vestimenta
	@Inject
	private VestimentaDao vestimentaDAO;

	
	@Inject
	private PrediccionesDao prediccionesDAO;
	
	@Inject
	private VotosDao votosDAO;
	
	
	
	private Empresa emp;
	
	
	private List<Votos> listVotos;



	
	//este metodo se inicia cada vez que se construye la clase
	@PostConstruct
	public void init() {

		listVotos = new ArrayList<>();
	}
	
	public Empresa getEmp() {
		return emp;
	}

	public void setEmp(Empresa emp) {
		this.emp = emp;
	}

	public List<Vestimenta> getVestimentas() {
		return vestimentas;
	}

	public void setVestimentas(List<Vestimenta> vestimentas) {
		this.vestimentas = vestimentas;
	}
	
	public int getIdUsuario() {
		System.out.println("el id que retorna de GET="+idUsuario);
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		System.out.println("el id que llega al SET recomendacion="+idUsuario);

		int ultimoIDtabla=prediccionesDAO.listadoPredicciones().size();
		
		PrediccionesFCM pre1 =prediccionesDAO.Leer(ultimoIDtabla);
		
		int predicciones[][]=pre1.getPredicciones();
		
		System.out.println("El tamaño es= "+predicciones.length+" "+predicciones[0].length+" el ultimoID="+ultimoIDtabla);
		
		if(idUsuario>predicciones.length) { //si el usuario es nuevo se le muestra ropa al azar
			vestimentasAzar();
		}else { //si el usuario ya realizo votaciones le recomienda ropa
		recomendacionUsuario(predicciones,(idUsuario-1));
		}
		this.idUsuario = idUsuario;
	}
	
	public void vestimentasAzar() {
		vestimentas = vestimentaDAO.listadovestimentas();
	}
	
	public void recomendacionUsuario(int predicciones[][], int numUsuario) {
		
		List<Recomendacion> listPredicciones = new ArrayList();

	    for (int item = 0; item < predicciones[0].length; item++){
	    	
	    	if(predicciones[numUsuario][item] !=0) {
	    		int prediccion=predicciones[numUsuario][item];
	    		Recomendacion rec=new Recomendacion(item, prediccion);
	    		
	    		listPredicciones.add(rec);
	    	}

	    }
	    
	    //obtiene los numeros mayores de la lista en base al voto de la prediicon
		Collections.sort(listPredicciones, new Comparator<Recomendacion>() {
			@Override
			public int compare(Recomendacion pre1, Recomendacion pre2) {			
				
				 
				
				return new Integer(pre2.getPrediccion()).compareTo(new Integer(pre1.getPrediccion()));
			}
		});
	    
		vestimentas = vestimentaDAO.listadovestimentas();
		List<Vestimenta> auxVestimentas=new ArrayList();
		
		listVotos = votosDAO.listadoVotos();
		
	    for (int i = 0; i < listPredicciones.size(); i++) {
	    	
	    	Recomendacion rec = listPredicciones.get(i);
	    	
	    	for (int j = 0; j < vestimentas.size(); j++) {
				
				Vestimenta ves= vestimentas.get(j);
				
			
				if(ves.getId()==(rec.getItem()+1)){
					
					//realizamos el calculo del rating y le asiganmos una imagen
					int sumVotacion=0;
					int totalVotos=0;
					int totalLikes=0;
					for (int k = 0; k < listVotos.size(); k++) {
						
						
						if(listVotos.get(k).getVestimenta().getId()==ves.getId()) {
							System.out.println("listVotoGET="+listVotos.get(k).getId()+" vesID="+ves.getId()+" voto="+listVotos.get(k).getVoto());
							sumVotacion = sumVotacion+listVotos.get(k).getVoto();
							totalVotos++;
							if(listVotos.get(k).getVoto()==5) {
								totalLikes++;
							}
						}
					}
					double rat=0.00;
					
					if(totalVotos!=0) {
					rat=Double.parseDouble(""+sumVotacion)/Double.parseDouble(""+totalVotos);
					
					}
					
					System.out.println("EL ratingggg "+rat);
					
					if(rat<=1.49) {
						ves.setRaiting("images/star1.png");
					}else if(rat>=1.50 && rat<=2.49) {
						ves.setRaiting("images/star2.png");
					}else if(rat>=2.50 && rat<=3.49) {
						ves.setRaiting("images/star3.png");
					}else if(rat>=3.50 && rat<=4.49) {
						ves.setRaiting("images/star4.png");
					}else if(rat>=4.50) {
						ves.setRaiting("images/star5.png");
					}
					
					ves.setLikes(totalLikes);
					
					auxVestimentas.add(ves);
				}
		
	    	}
		}
		vestimentas = auxVestimentas;
		
		
		for (int i = 0; i < auxVestimentas.size(); i++) {
			System.out.println("la vesti ="+vestimentas.get(i).getDescripcion());
		}

	}
	
}
