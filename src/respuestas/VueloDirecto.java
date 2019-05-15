package respuestas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class VueloDirecto {
	private HashMap<String,Integer> aerolineas;
	private double km;
	private String aeropuertoOrigen;
	private String aeropuertoDestino;
	
	public VueloDirecto(double km, String aeropuerto_origen, String aeropuerto_destino) { //constructor
		this.aerolineas = new HashMap<>();
		this.km = km;
		this.aeropuertoOrigen = aeropuerto_origen;
		this.aeropuertoDestino = aeropuerto_destino;
	}
	
	//funciones
	public double getKm() {
		return km;
	}
	public void setKm(double km) {
		this.km = km;
	}

	public int getCant_asientos(String aerolinea) {
		return this.aerolineas.get(aerolinea);
	}

	public void setAerolinea(String aerolinea, int cant_asientos) {
		this.aerolineas.put(aerolinea, cant_asientos);
	}
	
	public ArrayList<String> getAerolineas(){
		ArrayList<String> aerolineasSalida = new ArrayList<String>();

		Iterator<Entry<String, Integer>> it = aerolineas.entrySet().iterator();
			
		while(it.hasNext()) {
			
			Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it.next();
			
			aerolineasSalida.add(entry.getKey());
		}
		return aerolineasSalida;
	}

	public String getAeropuertoOrigen() {
		return aeropuertoOrigen;
	}

	public void setAeropuertoOrigen(String aeropuertoOrigen) {
		this.aeropuertoOrigen = aeropuertoOrigen;
	}

	public String getAeropuertoDestino() {
		return aeropuertoDestino;
	}

	public void setAeropuertoDestino(String aeropuertoDestino) {
		this.aeropuertoDestino = aeropuertoDestino;
	}
	
	public String toString() {
		ArrayList<String> aerolineas = getAerolineas();
		String aerolineasString = "Aerolineas: ";
		for(int i=0; i<aerolineas.size(); i++) {
			aerolineasString += "\n\t" + aerolineas.get(i);
		}
		return "\nAeropuerto Origen: "+ aeropuertoOrigen + "| Aeropuerto Destino: "+ aeropuertoDestino + "\n\t\t Cantidad de Kilometros: "+ km + "\n\t\t" + aerolineasString + "]";
	}
	
	
}
