package grafo;

import java.util.ArrayList;
import java.util.List;

import respuestas.ConsultaReservas;
import respuestas.ConsultaVueloDirecto;

public class Aeropuerto {
	private String nombre;
	private String pais;
	private String ciudad;
	private String estado;
	private List<Ruta> rutas;
	
	public Aeropuerto(String nombre, String ciudad, String pais) {
		this.rutas = new ArrayList<Ruta>();
		this.nombre = nombre;
		this.pais = pais;
		this.ciudad = ciudad;
	}
	
	public void addRuta(Ruta ruta) {
		this.rutas.add(ruta);
	}
	
	public List<Ruta> getRutas(){
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
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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
	
	public ConsultaVueloDirecto verificarDestino(String destino, String aerolinea) {
		
		int i = 0;
    	while((i < this.rutas.size()-1)&&(!(this.rutas.get(i).getDestino().getNombre().equals(destino)))) {
    		i++;
    	}
    	ConsultaVueloDirecto resultado = new ConsultaVueloDirecto(this.rutas.get(i).getInfo().getKm(),this.rutas.get(i).getInfo().getAsientosDisponibles(aerolinea));
    	return resultado;	
	}
	
	public String toString() {
		return " Aeropuerto : [Nombre: " + this.nombre + ", Pais: " + this.pais + ", Ciudad: " + this.ciudad + "]";
	}
}
