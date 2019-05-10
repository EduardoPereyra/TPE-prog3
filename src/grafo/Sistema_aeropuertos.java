package grafo;

import java.util.ArrayList;
import java.util.Iterator;

import respuestas.ConsultaReservas;
import respuestas.ConsultaVueloDirecto;
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
		int i = 0;
		while(i < aeropuertos.size()-1 && !(aeropuertos.get(i).getNombre().equals(aeropuerto_origen))) {
			i++;
		}	
		int j = 0;
		while(j < aeropuertos.size()-1 && !(aeropuertos.get(j).getNombre().equals(aeropuerto_destino))) {
			j++;
		}	
		Ruta ruta = new Ruta(aeropuertos.get(j), info);
		aeropuertos.get(i).addRuta(ruta);	
	}
	
	public void setearReserva(String aeropuerto_origen, String aeropuerto_destino, String aerolinea, int reservas) { //setea las reservas en una ruta especifica
		int i = 0;
		while(i < aeropuertos.size()-1 && !(aeropuertos.get(i).getNombre().equals(aeropuerto_origen))) {
			i++;
		}
		aeropuertos.get(i).setReservaRuta(aeropuerto_destino, aerolinea, reservas);					
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
    
    public ConsultaVueloDirecto verificarVueloDirecto(String origen, String destino, String aerolinea) { //verifica si existe un vuelo directo entre un aeropuerto origen y destino con una aerolinea
    	int i = 0;
    	while((i < this.aeropuertos.size()-1)&&(!this.aeropuertos.get(i).getNombre().equals(origen))) {
    		i++;
    	}
    	return this.aeropuertos.get(i).verificarDestino(destino, aerolinea);
    }
	
	
	public ArrayList<VuelosSinAerolinea> listarVuelosSinAerolinea(String origen, String destino, String aerolineaX) { //lista todos los vuelos disponibles desde un origen a un destino sin utilizar una aerolinea
		ArrayList<VuelosSinAerolinea> resultado = new ArrayList<>();
		for (Aeropuerto a : aeropuertos) {
			a.setEstado("No Visitado");			
		}
    	int i = 0;
    	while((i < this.aeropuertos.size()-1)&&(!this.aeropuertos.get(i).getNombre().equals(origen))) {
    		i++;
    	}	
    		double km = 0;
    		int cant_escalas = 0;
			resultado.addAll(dfs_visit(destino,km,cant_escalas,this.aeropuertos.get(i), aerolineaX));
	
		return resultado;
	}
	
	private ArrayList<VuelosSinAerolinea> dfs_visit(String destino, double km, int cant_escalas, Aeropuerto puntero, String aerolineaX) { //dfs de todos las aeropuertos (conectado a listarVuelosSinAerolinea)
		ArrayList<VuelosSinAerolinea> resultado = new ArrayList<>();		
		puntero.setEstado("Visitado");		
		for (Ruta r : puntero.getRutas()) {		
			ArrayList<String> aerolineas = new ArrayList<>(r.getInfo().getAerolineas());			
			for(int i= 0; i<aerolineas.size(); i++) {
				if(!(aerolineas.get(i).equals(aerolineaX))&&(r.getDestino().getEstado().equals("No Visitado"))) {
					if(!(r.getDestino().getNombre().equals(destino))) {
						cant_escalas += 1;
						resultado.addAll(dfs_visit(destino, km, cant_escalas, r.getDestino(), aerolineaX));
					}	
					VuelosSinAerolinea vuelo = new VuelosSinAerolinea();
					km += r.getInfo().getKm();
					vuelo.setCant_km(km);
					vuelo.setEscalas(cant_escalas);
					vuelo.setAerolinea(aerolineas.get(i));
					resultado.add(vuelo);							
				}
			}			
		}
		puntero.setEstado("No Visitado");
		return resultado;
	}

}
