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
import edu.ups.ec.siremo.preference.MisPreferences;
import edu.ups.ec.siremo.util.ErrorsController;

/**
 * Esta clase sirve para enlazar la vista del archivo xhtml con el controlador de la pagi recomendaciones 
 * @author Boris
 *
 */
@ManagedBean
@ViewScoped
public class PrincipalControlador {

	// Instanciamos la clase que controla los errores con su respectivo inject.
	ErrorsController error = new ErrorsController();
	@Inject
	private FacesContext facesContext;
	
	//variables necesarias
	private List<Vestimenta> vestimentasRecomen;
	private List<Vestimenta> vestimentasConjuntos;
	private List<Vestimenta> vestimentasMeGustaron;
	private List<Vestimenta> vestimentasBuscar;
	private List<Vestimenta> vestimentasPopulares;
	
	private int idUsuario;
	private Usuario usuario;
	private List<Votos> listVotos;
	private List<Vestimenta> filtVestimenta;
	
//	private Vestimenta ves;
	//instanciamos en objeto de acceso a datos para poder injectar los metodos crud correspondiente al Vestimenta
	@Inject
	private VestimentaDao vestimentaDAO;

	@Inject
	private PrediccionesDao prediccionesDAO;
	
	@Inject
	private VotosDao votosDAO;
	
	@Inject
	private UsuarioDao usuarioDao;
	
	@Inject
	private MisPreferences preferences;





	
	//este metodo se inicia cada vez que se construye la clase
	@PostConstruct
	public void init() {
		
		vestimentasRecomen = new ArrayList<>();
		vestimentasConjuntos = new ArrayList<>();
		vestimentasMeGustaron = new ArrayList<>();
		vestimentasBuscar = new ArrayList<>();
		vestimentasPopulares=new ArrayList<>();
		
		listVotos = new ArrayList<>();
	}

	
	public List<Vestimenta> getVestimentasRecomen() {
		
		idUsuario = preferences.getIdUsuario();
		loadUsuario(idUsuario);
		System.out.println("el id que llega al GET recomendacion="+idUsuario);
		loadUsuario(idUsuario);
		
		List<PrediccionesFCM> listaPrediccionesUsuario = prediccionesDAO.listPrediccionesUsua(idUsuario);
		System.out.println("mmmm "+listaPrediccionesUsuario.size());
		
		for (int i = 0; i < listaPrediccionesUsuario.size(); i++) {
			System.out.print("|"+listaPrediccionesUsuario.get(i).getItem()+" "+listaPrediccionesUsuario.get(i).getPrediccion());
		}
		
		
		if(listaPrediccionesUsuario.size()==0) { //si el usuario es nuevo se le muestra ropa al azar
			vestimentasAzar();
			vestimentasAzarConjuntos();
		}else { //si el usuario ya realizo votaciones le recomienda ropa
		recomendacionUsuario(listaPrediccionesUsuario);
		recomendacionConjuntos(listaPrediccionesUsuario);
		}
		
		
		
		vestimentasGustaron(idUsuario);
		
		return vestimentasRecomen;
	}

	public void setVestimentasRecomen(List<Vestimenta> vestimentasRecomen) {
		
		
		
		this.vestimentasRecomen = vestimentasRecomen;
	}

	public List<Vestimenta> getVestimentasConjuntos() {
		return vestimentasConjuntos;
	}

	public List<Vestimenta> getVestimentasBuscar() {
		vestimentasBuscar();
		return vestimentasBuscar;
	}


	public void setVestimentasBuscar(List<Vestimenta> vestimentasBuscar) {
		this.vestimentasBuscar = vestimentasBuscar;
	}

	public List<Vestimenta> getFiltVestimenta() {
		return filtVestimenta;
	}


	public void setFiltVestimenta(List<Vestimenta> filtVestimenta) {
		this.filtVestimenta = filtVestimenta;
	}


	public List<Vestimenta> getVestimentasPopulares() {
		vestimentasPopulares();
		return vestimentasPopulares;
	}


	public void setVestimentasPopulares(List<Vestimenta> vestimentasPopulares) {
		this.vestimentasPopulares = vestimentasPopulares;
	}


	public void setVestimentasConjuntos(List<Vestimenta> vestimentasConjuntos) {
		this.vestimentasConjuntos = vestimentasConjuntos;
	}

	public List<Vestimenta> getVestimentasMeGustaron() {
		return vestimentasMeGustaron;
	}

	public void setVestimentasMeGustaron(List<Vestimenta> vestimentasMeGustaron) {
		this.vestimentasMeGustaron = vestimentasMeGustaron;
	}

	public int getIdUsuario() {
		System.out.println("el id que retorna de GET="+idUsuario);
		return idUsuario;
	}

	public void setIdUsuario(int ic) {	
		this.idUsuario = idUsuario;
	}
	
	public String btnBuscar() {
		
		
		
		return "buscador_face.xhtml";
	}
	
	
	private void vestimentasBuscar() {
		vestimentasBuscar = vestimentaDAO.listadovestimentas();
	}
	
	///////Populares//////
private void vestimentasPopulares() {
		
		loadUsuario(idUsuario);
		
		vestimentasPopulares = new ArrayList<>();
		
		//List<Votos> votos= votosDAO.listadoVotos();
		List<Vestimenta> vestimentas= vestimentaDAO.listadovestimentas();
		List<Votos> listVotos= votosDAO.listadoVotos();

		
		//el bucle va de mayor a menor para sacar en primer lugar la ultima preda que le gusto al usuario.
			for (int i = vestimentas.size()-1; i >=0 ; i--) {
				//Vestimenta ves = votos.get(i).getVestimenta();
				Vestimenta ves = vestimentas.get(i);
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
				if(totalLikes>=3) {
						
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
						
				}else {
					ves.setRaiting("images/star1.png");
				}
				
				if(rat>=2.50 && ves.getGenero().equals(usuario.getGenero())) {
				ves.setLikes(totalLikes);
				vestimentasPopulares.add(ves);
				}
			}
			ArrayList<Vestimenta> aux = (ArrayList<Vestimenta>) vestimentasPopulares;
			vestimentasPopulares= new ArrayList<Vestimenta>();
			for (Vestimenta vestimenta : aux) {
				if (vestimenta.getRaiting().equals("images/star5.png")) {
					vestimentasPopulares.add(vestimenta);
				}	
			}
			for (Vestimenta vestimenta : aux) {
				if (vestimenta.getRaiting().equals("images/star4.png")) {
					vestimentasPopulares.add(vestimenta);
				}	
			}
			for (Vestimenta vestimenta : aux) {
				if (vestimenta.getRaiting().equals("images/star3.png")) {
					vestimentasPopulares.add(vestimenta);
				}	
			}
	}
	
	/////////////
	
	private void vestimentasGustaron(int idUsuario) {
		
		
		vestimentasMeGustaron = new ArrayList<>();
		
		List<Votos> votos= votosDAO.meGustaron(usuario);
		List<Votos> listVotos= votosDAO.listadoVotos();

		
		//el bucle va de mayor a menor para sacar en primer lugar la ultima preda que le gusto al usuario.
			for (int i = votos.size()-1; i >=0 ; i--) {
				Vestimenta ves = votos.get(i).getVestimenta();
						
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
				
				vestimentasMeGustaron.add(ves);    
			}
	}
	
	private void loadUsuario(int id) {
		usuario = usuarioDao.Leer(id);
	}
	
	private void vestimentasAzar() {
		vestimentasRecomen = vestimentaDAO.listadovestimentas();
		
		List<Vestimenta> auxVestimentas=new ArrayList();
		
		listVotos = votosDAO.listadoVotos();
		
	    for (int i = 0; i < vestimentasRecomen.size(); i++) {

					Vestimenta ves = vestimentasRecomen.get(i);
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
					if(ves.getGenero().equals(usuario.getGenero())) {
						auxVestimentas.add(ves);
					}
					//auxVestimentas.add(ves);
					

					
				}
		
	    vestimentasRecomen = auxVestimentas;
	    
	}
	
	private void vestimentasAzarConjuntos() {
		
		
		List<Vestimenta> auxVestimentas=new ArrayList();
		
		listVotos = votosDAO.listadoVotos();
		
	    for (int i = 0; i < vestimentasRecomen.size(); i++) {

					Vestimenta ves = vestimentasRecomen.get(i);
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
					if(ves.getGenero().equals(usuario.getGenero())) {
						auxVestimentas.add(ves);
					}
					
				}
	    
	    List<Vestimenta> torso=new ArrayList();
		List<Vestimenta> piernas=new ArrayList();
		List<Vestimenta> pies=new ArrayList();
		List<Vestimenta> Conjuntos=new ArrayList();
		
		for (Vestimenta vestimenta : auxVestimentas) {
			if(vestimenta.getTipo().equals("Torso")) {
				torso.add(vestimenta);
			}
			if(vestimenta.getTipo().equals("Piernas")) {
				piernas.add(vestimenta);
			}
			if(vestimenta.getTipo().equals("Pies")) {
				pies.add(vestimenta);
			}
		}
		
		for (int i = 0; i < torso.size(); i++) {
			Vestimenta vesTor = torso.get(i);
			
			for (int j = 0; j < piernas.size(); j++) {
				Vestimenta vesPier = piernas.get(j);
				
				if(vesTor.getColor().equals(vesPier.getColor()) && vesTor.getEstilo().equals(vesPier.getEstilo())) {
					
					for (int k = 0; k < pies.size(); k++) {
						Vestimenta vesPie = pies.get(k);
								
						if(vesTor.getColor().equals(vesPie.getColor()) && vesTor.getEstilo().equals(vesPie.getEstilo())){
							Conjuntos.add(torso.get(i));
							Conjuntos.add(piernas.get(j));
							Conjuntos.add(pies.get(k));
							
							piernas.remove(j);
							pies.remove(k);
							
							break;
						}
					}
					break;
					
				}
			}
			
		}
		vestimentasConjuntos=Conjuntos;
	    
	}
	

	private void recomendacionConjuntos(List<PrediccionesFCM> predicciones) {
		
		List<Recomendacion> listPredicciones = new ArrayList();

		System.out.println("entro a metodo "+predicciones.size());
	    for (int item = 0; item < predicciones.size(); item++){
	    	
	    		Recomendacion rec=new Recomendacion(predicciones.get(item).getItem(), predicciones.get(item).getPrediccion());
	    		
	    		listPredicciones.add(rec);

	    }
	    //obtiene los numeros mayores de la lista en base al voto de la prediicon
		Collections.sort(listPredicciones, new Comparator<Recomendacion>() {
			@Override
			public int compare(Recomendacion pre1, Recomendacion pre2) {			
				return new Integer(pre2.getPrediccion()).compareTo(new Integer(pre1.getPrediccion()));
			}
		});
	    
		List<Vestimenta> auxVestimentas=new ArrayList();
		
		listVotos = votosDAO.listadoVotos();
		
		System.out.println("elll taaa "+listPredicciones.size()+" total vest "+vestimentasRecomen.size());
		
	    for (int i = 0; i < listPredicciones.size(); i++) {
	    	
	    	Recomendacion rec = listPredicciones.get(i);
	    	
	    	for (int j = 0; j < vestimentasRecomen.size(); j++) {
				
				Vestimenta ves= vestimentasRecomen.get(j);
				
				if(ves.getId()==(rec.getItem())){

					int totalLikes=0;
					for (int k = 0; k < listVotos.size(); k++) {
						
						
						if(listVotos.get(k).getVestimenta().getId()==ves.getId()) {
							System.out.println("listVotoGET="+listVotos.get(k).getId()+" vesID="+ves.getId()+" voto="+listVotos.get(k).getVoto());

							if(listVotos.get(k).getVoto()==5) {
								totalLikes++;
							}
						}
					}
					double rat=rec.getPrediccion();

					
					System.out.println("EL ratingggg mmm "+rat);
					
					if(rat==1) {
						ves.setRaiting("images/star1.png");
					}else if(rat==2) {
						ves.setRaiting("images/star2.png");
					}else if(rat==3) {
						ves.setRaiting("images/star3.png");
					}else if(rat==4) {
						ves.setRaiting("images/star4.png");
					}else if(rat==5) {
						ves.setRaiting("images/star5.png");
					}
					
					ves.setLikes(totalLikes);
					if(ves.getGenero().equals(usuario.getGenero())) {
						auxVestimentas.add(ves);
					}
					//auxVestimentas.add(ves);
					

					
				}
		
	    	}
		}
		List<Vestimenta> torso=new ArrayList();
		List<Vestimenta> piernas=new ArrayList();
		List<Vestimenta> pies=new ArrayList();
		List<Vestimenta> Conjuntos=new ArrayList();
		
		for (Vestimenta vestimenta : auxVestimentas) {
			if(vestimenta.getTipo().equals("Torso")) {
				torso.add(vestimenta);
			}
			if(vestimenta.getTipo().equals("Piernas")) {
				piernas.add(vestimenta);
			}
			if(vestimenta.getTipo().equals("Pies")) {
				pies.add(vestimenta);
			}
		}
		
		for (int i = 0; i < torso.size(); i++) {
			Vestimenta vesTor = torso.get(i);
			
			for (int j = 0; j < piernas.size(); j++) {
				Vestimenta vesPier = piernas.get(j);
				
				if(vesTor.getColor().equals(vesPier.getColor()) && vesTor.getEstilo().equals(vesPier.getEstilo())) {
					
					for (int k = 0; k < pies.size(); k++) {
						Vestimenta vesPie = pies.get(k);
								
						if(vesTor.getColor().equals(vesPie.getColor()) && vesTor.getEstilo().equals(vesPie.getEstilo())){
							Conjuntos.add(torso.get(i));
							Conjuntos.add(piernas.get(j));
							Conjuntos.add(pies.get(k));
							
							piernas.remove(j);
							pies.remove(k);
							
							break;
						}
					}
					break;
					
				}
			}
			
		}
		
		/*int i=0;
		while (i<torso.size()||i<piernas.size()||i<pies.size()) {
			System.out.println(""+torso.size()+ "<<<>>> "+piernas.size()+"<<>>> "+pies.size() );
			Conjuntos.add(torso.get(i));
			Conjuntos.add(piernas.get(i));
			Conjuntos.add(pies.get(i));
			i++;
			if(i==torso.size()||i==piernas.size()||i==pies.size()) {
				break;
			}
			
		}*/
		vestimentasConjuntos=Conjuntos;
	    //vestimentasRecomen = auxVestimentas;

	}
	
	private void recomendacionUsuario(List<PrediccionesFCM> predicciones) {
		
		List<Recomendacion> listPredicciones = new ArrayList();

		System.out.println("entro a metodo "+predicciones.size());
	    for (int item = 0; item < predicciones.size(); item++){
	    	
	    		Recomendacion rec=new Recomendacion(predicciones.get(item).getItem(), predicciones.get(item).getPrediccion());
	    		
	    		listPredicciones.add(rec);

	    }
	    //obtiene los numeros mayores de la lista en base al voto de la prediicon
		Collections.sort(listPredicciones, new Comparator<Recomendacion>() {
			@Override
			public int compare(Recomendacion pre1, Recomendacion pre2) {			
				return new Integer(pre2.getPrediccion()).compareTo(new Integer(pre1.getPrediccion()));
			}
		});
	    
		vestimentasRecomen = vestimentaDAO.listadovestimentas();
		List<Vestimenta> auxVestimentas=new ArrayList();
		
		listVotos = votosDAO.listadoVotos();
		
		System.out.println("elll taaa "+listPredicciones.size()+" total vest "+vestimentasRecomen.size());
		
	    for (int i = 0; i < listPredicciones.size(); i++) {
	    	
	    	Recomendacion rec = listPredicciones.get(i);
	    	
	    	for (int j = 0; j < vestimentasRecomen.size(); j++) {
				
				Vestimenta ves= vestimentasRecomen.get(j);
				
				if(ves.getId()==(rec.getItem())){

					int totalLikes=0;
					for (int k = 0; k < listVotos.size(); k++) {
						
						
						if(listVotos.get(k).getVestimenta().getId()==ves.getId()) {
							System.out.println("listVotoGET="+listVotos.get(k).getId()+" vesID="+ves.getId()+" voto="+listVotos.get(k).getVoto());

							if(listVotos.get(k).getVoto()==5) {
								totalLikes++;
							}
						}
					}
					double rat=rec.getPrediccion();

					
					System.out.println("EL ratingggg "+rat);
					
					if(rat==1) {
						ves.setRaiting("images/star1.png");
					}else if(rat==2) {
						ves.setRaiting("images/star2.png");
					}else if(rat==3) {
						ves.setRaiting("images/star3.png");
						if(ves.getGenero().equals(usuario.getGenero())) {
							ves.setLikes(totalLikes);
							auxVestimentas.add(ves);
						}
					}else if(rat==4) {
						ves.setRaiting("images/star4.png");
						if(ves.getGenero().equals(usuario.getGenero())) {
							ves.setLikes(totalLikes);
							auxVestimentas.add(ves);
						}
					}else if(rat==5) {
						ves.setRaiting("images/star5.png");
						if(ves.getGenero().equals(usuario.getGenero())) {
							ves.setLikes(totalLikes);
							auxVestimentas.add(ves);
						}
					}
					
					
					
					
				}
		
	    	}
		}
	    vestimentasRecomen = auxVestimentas;

	}
	
}
