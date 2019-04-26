package grafo;

import java.util.ArrayList;
import java.util.List;

public class Aeropuerto {
	private String nombre;
	private String pais;
	private String ciudad;
	private List<Ruta> rutas;
	
	public Aeropuerto(String nombre, String ciudad, String pais) {
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
	
	public Ruta getRuta(String destino) {
		Ruta aux = new Ruta();
		for(int i = 0; i < this.rutas.size() ; i++) {
			if(this.rutas.get(i).getDestino().getNombre() == destino) {
				aux = this.rutas.get(i);
			}
		}
		return aux;
	}
	
	public String toString() {
		return "\n Aeropuerto [Nombre: " + this.nombre + ", Pais: " + this.pais + ", Ciudad: " + this.ciudad + "]";
	}
}
