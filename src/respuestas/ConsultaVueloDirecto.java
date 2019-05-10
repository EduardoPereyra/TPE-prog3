package respuestas;

public class ConsultaVueloDirecto {
	private double km;
	private int cant_asientos;
	
	public ConsultaVueloDirecto( double km, int cant_asientos) { //constructor
		this.km = km;
		this.cant_asientos = cant_asientos;
	}
	
	//funciones
	public double getKm() {
		return km;
	}
	public void setKm(double km) {
		this.km = km;
	}
	public int getCant_asientos() {
		return cant_asientos;
	}
	public void setCant_asientos(int cant_asientos) {
		this.cant_asientos = cant_asientos;
	}
	
}
