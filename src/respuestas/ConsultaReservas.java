package respuestas;

public class ConsultaReservas {
	//tener cual es le destino origen y cant reservas
	private String origen;
	private String destino;
	private String aerolinea;
	private int cantReservas;
	
	public ConsultaReservas(String origen, String destino, String aerolinea, int cantReservas){
		this.origen = origen;
		this.destino = destino;
		this.aerolinea = aerolinea;
		this.cantReservas = cantReservas;
	}
	
	public String toString() {
		return "[Aeropuerto Origen: " + this.origen + ", Aeropuerto Destino: " + this.destino + ", Aerolinea: " + this.aerolinea + ", Asientos Reservados: " + this.cantReservas + "]\n";
	}
}
