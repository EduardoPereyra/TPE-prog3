package grafo;

import java.util.HashMap;

public class Ruta {
	private double km;
	private boolean cabotaje;
	private Aeropuerto destino;
	private HashMap<String,Integer> aerolineas;
	
	public Ruta(double km, boolean cabotaje) {
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
	
	public int getCantPasajeros(String aerolinea) {
		return this.aerolineas.get(aerolinea);
	}
	
	public void setAerolineas(String aerolinea, int CantPasajeros) {
		this.aerolineas.put(aerolinea, CantPasajeros);
	}
	
	public Aeropuerto getDestino() {
		return destino;
	}
	
	public void setDestino(Aeropuerto destino) {
		this.destino = destino;
	}
	
	public String toString() {
/*		String aerolinea = null;
		aerolineas.forEach((k,v) -> aerolinea += ", Aerolinea: " + k + ", Cantidad Pasajeros: " + v));*/
		
		return "\n \tRuta [destino:" + destino.getNombre() + ", km:" + this.km + ", cabotaje:" + this.cabotaje +"]"
				+ "\tAerolinea " /*+ aerolinea*/;
	}
}
