package grafo;

import java.util.HashMap;

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
		return this.aerolineas.get(aerolinea) - this.reservas.get(aerolinea);
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
	public void getReservas(String aeropuerto_origen) {
		//hacer un arreglo de ConsultaReservas e ir guardando en una lista
		
	}

}
