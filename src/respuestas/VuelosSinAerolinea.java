package respuestas;

import java.util.ArrayList;

public class VuelosSinAerolinea {
	private ArrayList<String> aerolineas;
	private int escalas;
	private double cant_km;
	
	public VuelosSinAerolinea() {
		this.aerolineas = new ArrayList<>();
	}
	
	public ArrayList<String> getAerolinea() {
		return aerolineas;
	}
	public void setAerolinea(String aerolinea) {
		this.aerolineas.add(aerolinea);
	}
	public int getEscalas() {
		return escalas;
	}
	public void setEscalas(int escalas) {
		this.escalas = escalas;
	}
	public double getCant_km() {
		return cant_km;
	}
	public void setCant_km(double cant_km) {
		this.cant_km = cant_km;
	}
	


}
