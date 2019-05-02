package grafo;

import java.util.ArrayList;
import java.util.List;

import servicios.ConsultaReservas;

public class Aeropuerto {
	private String nombre;
	private String pais;
	private String ciudad;
	private List<Ruta> rutas;
	
	public Aeropuerto(String nombre, String ciudad, String pais) {
		this.rutas = new ArrayList<Ruta>();
		this.nombre = nombre;
		this.pais = pais;
		this.ciudad = ciudad;
	}
	
	public void addArco(Ruta ruta) {
		this.rutas.add(ruta);
	}
	
	public List<Ruta> getArcos(){
		return this.rutas;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getPais() {
		return pais;
	}

	public String getCiudad() {
		return ciudad;
	}
	
	
	
	public void setReservaRuta(String aeropuerto_destino, String aerolinea, int cantReservas) {
		for(int i = 0; i < rutas.size() ; i++) {
			if(rutas.get(i).getDestino().getNombre().equals(aeropuerto_destino)) {
				rutas.get(i).setReserva(aerolinea, cantReservas);
			}
		}
	}
	
	public ArrayList<ConsultaReservas> getReservasDestino(){
		
		ArrayList<ConsultaReservas> reservasAeropuerto = new ArrayList<ConsultaReservas>();
		
		for(Ruta r : rutas) {
			reservasAeropuerto.addAll(r.getReservas(this.nombre));
		}
		
		return reservasAeropuerto;
	}
	
	public String toString() {
		return "\n Aeropuerto [Nombre: " + this.nombre + ", Pais: " + this.pais + ", Ciudad: " + this.ciudad + "]";
	}
}
