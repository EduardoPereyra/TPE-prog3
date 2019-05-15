package grafo;

import java.util.ArrayList;
import java.util.List;

import respuestas.ConsultaReservas;
import respuestas.VueloDirectoConAerolinea;

public class Aeropuerto {
	private String nombre;
	private String pais;
	private String ciudad;
	private String estado;
	private ArrayList<Ruta> rutas;
	
	public Aeropuerto(String nombre, String ciudad, String pais) { //constructor
		this.rutas = new ArrayList<Ruta>();
		this.nombre = nombre;
		this.pais = pais;
		this.ciudad = ciudad;
	}
	
	//funciones
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
	
	public void setReservaRuta(String aeropuerto_destino, String aerolinea, int cantReservas) { //setea las reservas en una aerolinea en una ruta valida
		for(int i = 0; i < rutas.size() ; i++) {
			if(rutas.get(i).getDestino().getNombre().equals(aeropuerto_destino)) {
				rutas.get(i).setReserva(aerolinea, cantReservas);
			}
		}
	}
	
	public ArrayList<ConsultaReservas> getReservasDestino(){ //devuelve un arreglo con todas las reservas
		ArrayList<ConsultaReservas> reservasAeropuerto = new ArrayList<ConsultaReservas>();		
		for(Ruta r : rutas) {
			reservasAeropuerto.addAll(r.getReservas(this.nombre));
		}	
		return reservasAeropuerto;
	}
	
	public VueloDirectoConAerolinea verificarDestinoAerolinea(String destino, String aerolinea) {//verifica que haya un vuelo directo hacia un destino con una aerolinea		
		int i = 0;
		
    	while((i < this.rutas.size()-1)&&(!(this.rutas.get(i).getDestino().getNombre().equals(destino)))) {
    		i++;
    	}
    	
    	if(this.rutas.get(i).getDestino().getNombre().equals(destino)) {
    		return new VueloDirectoConAerolinea(this.rutas.get(i).getInfo().getKm(),this.rutas.get(i).getInfo().getAsientosDisponibles(aerolinea));
    	}
    	return new VueloDirectoConAerolinea();	
	}
	
	public String toString() {
		return " Aeropuerto : [Nombre: " + this.nombre + ", Pais: " + this.pais + ", Ciudad: " + this.ciudad + "]";
	}
}
