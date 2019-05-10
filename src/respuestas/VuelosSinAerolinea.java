package respuestas;

import java.util.ArrayList;

public class VuelosSinAerolinea {
	private ArrayList<String> aerolineas;
	private int escalas;
	private double cant_km;
	
	public VuelosSinAerolinea() {//Constructor
		this.aerolineas = new ArrayList<>();
	}
	
	//funciones
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
	public String toString() {
		String aerolineasString = "Aerolineas: ";		
		for(int i=0; i< this.aerolineas.size(); i++) {
			aerolineasString += "\n\t" + this.aerolineas.get(i);		
		}			
		return "Cantidad de Escalas realizadas: " + this.escalas +"\n" + "Cantidad Kilometros: "+ this.cant_km + "\n" + aerolineasString; 			
	}
	


}
