package grafo;

import java.util.ArrayList;
import java.util.List;

public class Aeropuerto {
	private String nombre;
	private String pais;
	private String ciudad;
	private List<Ruta> rutas;
	
	public Aeropuerto(String nombre, String pais, String ciudad) {
		this.rutas = new ArrayList<>();
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
	
	public String toString() {
		return "\n Aeropuerto [nombre: " + this.nombre + ", pais: " + this.pais + ", ciudad: " + this.ciudad + ", rutas: " + rutas + "]";
	}
}
