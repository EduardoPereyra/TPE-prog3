package grafo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import respuestas.ConsultaReservas;

public class InformacionRuta {
	private double km;
	private boolean cabotaje;
	private HashMap<String,Integer> aerolineas;
	private HashMap<String,Integer> reservas;
	
	public InformacionRuta(double km, boolean cabotaje) {
		this.km = km;
		this.cabotaje = cabotaje;
		this.aerolineas = new HashMap<>();
		this.reservas = new HashMap<>();
	}
	
	public double getKm() {
		return km;
	}
	
	public void setKm(double km) {
		this.km = km;
	}
	
	public int getAsientosDisponibles(String aerolinea) {
		if(this.reservas.get(aerolinea) != null) {
		return this.aerolineas.get(aerolinea) - this.reservas.get(aerolinea);
		}else {
			return this.aerolineas.get(aerolinea);
		}
		
	}
	
	public boolean isCabotaje() {
		return cabotaje;
	}
	
	public void setCabotaje(boolean cabotaje) {
		this.cabotaje = cabotaje;
	}
	
	public void setAerolineas(String aerolinea, int cant_asientos) {
		this.aerolineas.put(aerolinea,cant_asientos);
	}

	public void setReservas(String aerolinea, Integer cant_reservas) {
		this.reservas.put(aerolinea, cant_reservas);
	}
	
	public String toString() {
		return "Kilometros : " + this.km + " Cabotaje : " + this.cabotaje;
	}
	
/*	public HashMap<String, Integer> getAerolineas() {
	return aerolineas;
}
*/	
	public ArrayList<ConsultaReservas> getInfoReservas(String aeropuerto_origen, String aeropuerto_destino) {
		//hacer un arreglo de ConsultaReservas e ir guardando en una lista
		ArrayList<ConsultaReservas> reservasSalida = new ArrayList<ConsultaReservas>();

		Iterator<Entry<String, Integer>> it = reservas.entrySet().iterator();
			
		while(it.hasNext()) {
			
			Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it.next();
			ConsultaReservas vuelo = new ConsultaReservas(aeropuerto_origen, aeropuerto_destino, entry.getKey(), entry.getValue());
		
			reservasSalida.add(vuelo);
			
		}
		return reservasSalida;
		
	}

}
