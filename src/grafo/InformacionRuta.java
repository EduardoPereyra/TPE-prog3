package grafo;

import java.util.ArrayList;
import java.util.HashMap;

public class InformacionRuta {
	private double km;
	private boolean cabotaje;
	private HashMap<String,Integer> aerolineas;
	private HashMap<String,Integer> reservas;
	
	public InformacionRuta(double km, boolean cabotaje) {
		this.km = km;
		this.cabotaje = cabotaje;
	}
	
	public double getKm() {
		return km;
	}
	
	public void setKm(double km) {
		this.km = km;
	}
	
	public boolean isCabotaje() {
		return cabotaje;
	}
	
	public void setCabotaje(boolean cabotaje) {
		this.cabotaje = cabotaje;
	}
	
	public void setAerolineas(String aerolinea, Integer cant_asientos) {
		this.aerolineas.put(aerolinea, cant_asientos);
	}

	public void setReservas(String aerolinea, Integer cant_reservas) {
		this.reservas.put(aerolinea, cant_reservas);
	}
	
/*	public HashMap<String, Integer> getAerolineas() {
	return aerolineas;
}
*/	
	public void getReservas() {
		//hacer un arreglo de ConsultaReservas e ir guardando en una lista
}

}
