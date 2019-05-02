package grafo;
import java.util.ArrayList;

import servicios.ConsultaReservas;

public class Ruta {
	private Aeropuerto destino;
	private InformacionRuta info;
	
	public Ruta(Aeropuerto destino,InformacionRuta info) {
		this.destino = destino;
		this.info = info;
	}
	
	public Ruta() {
	}

	public Aeropuerto getDestino() {
		return destino;
	}
	
	public InformacionRuta getInfo() {
		return info;
	}

	public void setInfo(InformacionRuta info) {
		this.info = info;
	}
	public void setReserva(String aerolinea, int reservas) {
		this.info.setReservas(aerolinea, reservas);
	}
	
	public String toString() {
		return "Destino: " +this.destino.getNombre() + this.info;
	}
	
	public ArrayList<ConsultaReservas> getReservas(String aeropuerto_origen) {
		
		return info.getInfoReservas(aeropuerto_origen, destino.getNombre());
	}
	
}
