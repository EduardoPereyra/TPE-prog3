package grafo;

import java.util.ArrayList;
import java.util.Iterator;

import respuestas.ConsultaReservas;
import respuestas.VueloDirecto;
import respuestas.VueloDirectoConAerolinea;
import respuestas.VuelosSinAerolinea;

public class Sistema_aeropuertos {
	private ArrayList<Aeropuerto> aeropuertos;
	
	public Sistema_aeropuertos() { //constructor
		aeropuertos = new ArrayList<Aeropuerto>();
	}
	
	//funciones
	public void add(Aeropuerto v) {
		this.aeropuertos.add(v);
	}
	
	public Iterator<Aeropuerto> getVertices() {
		return this.aeropuertos.iterator();
	}
	
	public void setearRuta_Aeropuerto(String aeropuerto_origen,String aeropuerto_destino, InformacionRuta info) { //setea la ruta de un aeropuerto hacia un aeropuerto destino
    	Aeropuerto aeropuertoOrigen = buscarAeropuerto(aeropuerto_origen);
    	Aeropuerto aeropuertoDestino = buscarAeropuerto(aeropuerto_destino);
		Ruta ruta = new Ruta(aeropuertoDestino, info);
		aeropuertoOrigen.addRuta(ruta);	
	}
	
	public void setearReserva(String aeropuerto_origen, String aeropuerto_destino, String aerolinea, int reservas) { //setea las reservas en una ruta especifica
		Aeropuerto aeropuertoOrigen = buscarAeropuerto(aeropuerto_origen);
		aeropuertoOrigen.setReservaRuta(aeropuerto_destino, aerolinea, reservas);					
	}
	
    public ArrayList<Aeropuerto> listarAeropuertos(){ //lista todos los aeropuertos
    	ArrayList<Aeropuerto> salida = new ArrayList<Aeropuerto>(aeropuertos);
    	return salida;
    }
    
    public ArrayList<ConsultaReservas> listarReservas(){ //lista todas las reservas de todos los aeropuertos	
    	ArrayList<ConsultaReservas> reservasSalida = new ArrayList<ConsultaReservas>(); 	
    	for(Aeropuerto a : aeropuertos) {
    		reservasSalida.addAll(a.getReservasDestino());
    	}
    	return reservasSalida;
    }
    
    public VueloDirectoConAerolinea verificarVueloDirectoAerolinea(String origen, String destino, String aerolinea) { //verifica si existe un vuelo directo entre un aeropuerto origen y destino con una aerolinea
	    	Aeropuerto aeropuertoOrigen = buscarAeropuerto(origen);
    	return aeropuertoOrigen.verificarDestinoAerolinea(destino, aerolinea);
    }
	
    public ArrayList<VueloDirecto> listarVuelosDirectos(String paisOrigen, String paisDestino){
    	ArrayList<VueloDirecto> vuelosDirectos = new ArrayList<>();
    	ArrayList<Aeropuerto> aeropuertosOrigen = buscarAeropuertosPais(paisOrigen); //encuentro aeropuertos del pais origen
    	
    	for(Aeropuerto a : aeropuertosOrigen) { // itero sobre los aeropuertos del pais origen
    		
    		for(Ruta r : a.getRutas()) { //itero en las rutas directas
    			if(r.getDestino().getPais().equals(paisDestino)) {  //pregunto si en la ruta directa tengo un aeropuerto del pais destino
    				VueloDirecto vueloDirecto = new VueloDirecto(r.getInfo().getKm(), a.getNombre(), r.getDestino().getNombre()); // si hay creo el vuelo directo seteo km y guardo el Aeropuerto Origen y Destino
    				
    				for(int i = 0; i < r.getInfo().getAerolineas().size(); i++) {  //itero sobre las aerolineas de la ruta directa
        				if(r.getInfo().getAsientosDisponibles(r.getInfo().getAerolineas().get(i)) > 0) {  //pregunto si dicha aerolinea tiene asientos disponibles
        					vueloDirecto.setAerolinea(r.getInfo().getAerolineas().get(i), r.getInfo().getAsientosDisponibles(r.getInfo().getAerolineas().get(i))); //si tiene agrego la aerolina al vuelo
        				}
    				}
    				vuelosDirectos.add(vueloDirecto); //agrego el vuelo al arreglo de vuelos directos
    			}
    		}
    	}
    	
    	return vuelosDirectos;
    }
 	
	public ArrayList<VuelosSinAerolinea> listarVuelosSinAerolinea(String origen, String destino, String aerolineaX) { //lista todos los vuelos disponibles desde un origen a un destino sin utilizar una aerolinea
		ArrayList<VuelosSinAerolinea> resultado = new ArrayList<>();
		ArrayList<ArrayList<String>> aerolineas = new ArrayList<>();
		for (Aeropuerto a : aeropuertos) {
			a.setEstado("No Visitado");			
		}
    	Aeropuerto aeropuertoDestino = buscarAeropuerto(origen);
    		double km = 0;
    		int cant_escalas = 0;
			resultado.addAll(dfs_visit(destino,km,cant_escalas,aeropuertoDestino, aerolineaX,aerolineas));	
		return resultado;
	}
	
	private ArrayList<VuelosSinAerolinea> dfs_visit(String destino, double km, int cant_escalas, Aeropuerto puntero, String aerolineaX,ArrayList<ArrayList<String>> aerolineas) { //dfs de todos las aeropuertos (conectado a listarVuelosSinAerolinea)
		ArrayList<VuelosSinAerolinea> resultado = new ArrayList<>();
		puntero.setEstado("Visitado");
		for (Ruta r : puntero.getRutas()) {				
				ArrayList<String> aerolineasDisponibles = aerolineasVisitables(aerolineaX, r);
				if(!aerolineasDisponibles.isEmpty()) {
					km += r.getInfo().getKm();
					aerolineas.add(aerolineasDisponibles);
					if(!(r.getDestino().getNombre().equals(destino))) {
						cant_escalas += 1;
						resultado.addAll(dfs_visit(destino, km, cant_escalas, r.getDestino(), aerolineaX,aerolineas));			
					}else {
						VuelosSinAerolinea vuelo = new VuelosSinAerolinea();
						vuelo.setCant_km(km);
						vuelo.setEscalas(cant_escalas);
						vuelo.setAerolineas(aerolineas);
						resultado.add(vuelo);
					}							
					cant_escalas -= 1;
					km -= r.getInfo().getKm();
					aerolineas.remove(aerolineasDisponibles);
				}
		}	
		puntero.setEstado("No Visitado");
		return resultado;
	}
	
	private ArrayList<String> aerolineasVisitables(String aerolineaX,Ruta r) {
		ArrayList<String> resultado = new ArrayList<>();
		if(r.getDestino().getEstado().equals("No Visitado")){
			for(int i = 0; i < r.getInfo().getAerolineas().size(); i++) {
				String aux =r.getInfo().getAerolineas().get(i);
				if(!(aux.equals(aerolineaX))) {
					if(r.getInfo().getAsientosDisponibles(aux) > 0) {
						resultado.add(aux);
					}
				}
			}
		}
		return resultado;
	}
	
	private Aeropuerto buscarAeropuerto(String aeropuerto) { //busca un aeropuerto por su nombre
    	int i = 0;
    	while((i < this.aeropuertos.size()-1)&&(!this.aeropuertos.get(i).getNombre().equals(aeropuerto))) {
    		i++;
    	}
    	return this.aeropuertos.get(i);
	}
	
	private ArrayList<Aeropuerto> buscarAeropuertosPais(String pais) { //busca un aeropuerto por su nombre
		ArrayList<Aeropuerto> salida = new ArrayList<>();
		
		for(int i=0; i<aeropuertos.size(); i++) {
			if(aeropuertos.get(i).getPais().equals(pais)) {
				salida.add(aeropuertos.get(i));
			}
		}		
    	return salida; 
	}

	private Ruta getRuta(Aeropuerto aeropuerto_origen, Aeropuerto aeropuerto_destino){
		Ruta aux;
		for(int i = 0; i < aeropuerto_origen.getRutas().size(); i++){
			aux = aeropuerto_origen.getRutas().get(i);
			if(aux.getDestino().equals(aeropuerto_destino)) {
				return aux;
			}
		}
		aux = null;
		return aux;
	}
	
	public ArrayList<Aeropuerto> visitAll(String origen){
		ArrayList<Aeropuerto> recorridoActual = new ArrayList<>();
		ArrayList<Aeropuerto> recorridoMenor = new ArrayList<>();
		double pesoActual = 0.0;
		double pesoMenor = 0.0;
		Aeropuerto aeropuerto_origen = this.buscarAeropuerto(origen);
		for (Aeropuerto a : aeropuertos) {
			a.setEstado("No Visitado");
		}
		visit_back(recorridoActual, recorridoMenor, pesoActual, pesoMenor, aeropuerto_origen, aeropuerto_origen);
		return recorridoMenor;
	}
	
	private void visit_back(ArrayList<Aeropuerto> recorridoActual, ArrayList<Aeropuerto> recorridoMenor, double pesoActual, double pesoMenor, Aeropuerto actual, Aeropuerto origen) {
		recorridoActual.add(actual);
		actual.setEstado("Visitado");
		
		for(Ruta r : actual.getRutas()) {
			pesoActual = pesoActual + r.getInfo().getKm();
			
			if((r.getDestino().getNombre().equals(origen.getNombre()))) {
				if(recorridoActual.size() == this.aeropuertos.size()){
					if((pesoActual < pesoMenor)||(pesoMenor == 0.0)){
						pesoMenor = pesoActual;
						recorridoMenor.clear();
						recorridoMenor.addAll(recorridoActual);
					}
				}
			}
			else {
				if(r.getDestino().getEstado().equals("No Visitado")) {
					visit_back(recorridoActual, recorridoMenor, pesoActual, pesoMenor, r.getDestino(), origen);
				}
			}
			
			pesoActual = pesoActual - r.getInfo().getKm();
		}
		
		recorridoActual.remove(actual);
		actual.setEstado("No Visitado");
	}
	
	public ArrayList<Aeropuerto> greedyCrazy(Aeropuerto origen){
		for (Aeropuerto a : aeropuertos) {
			a.setEstado("No Visitado");
		}
		ArrayList<Aeropuerto> sol = new ArrayList<>();
		sol.add(origen);
		Aeropuerto actual = origen;
		
		while(sol.size() < aeropuertos.size()) {
			actual.setEstado("Visitado");
			Aeropuerto masCercano = seleccionar(actual);
			sol.add(masCercano);
			actual = masCercano;
		}
		
		return sol;
	}
	
	private Aeropuerto seleccionar(Aeropuerto actual) {
		double costo = Double.POSITIVE_INFINITY;
		Aeropuerto masCercano = new Aeropuerto();
		for(Ruta r : actual.getRutas()) {
			if(r.getInfo().getKm() < costo && r.getDestino().getEstado().equals("No Visitado")) {
				costo = r.getInfo().getKm();
				masCercano = r.getDestino();
			}
		}
		return masCercano;
	}
/*
	public ArrayList<Aeropuerto> greedyCrazy(Aeropuerto origen){
		ArrayList<Aeropuerto> candidatos = new ArrayList<Aeropuerto>(aeropuertos);
		
		ArrayList<Aeropuerto> sol = new ArrayList<>();
		sol.add(origen);
		Aeropuerto actual = origen;
		
		while(sol.size() < candidatos.size() && !solucion(sol, origen)) {
			
			Aeropuerto masCercano = seleccionar(candidatos, actual);
			sol.add(masCercano);
			actual = masCercano;
		}
		
		return sol;
	}
*/
}
