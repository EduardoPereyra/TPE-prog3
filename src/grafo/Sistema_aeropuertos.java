package grafo;

import java.util.ArrayList;
import java.util.Iterator;

public class Sistema_aeropuertos {
	private ArrayList<Aeropuerto> aeropuertos;
	
	public Sistema_aeropuertos() {
		aeropuertos = new ArrayList<>();
	}
	
	public void add(Aeropuerto v) {
		this.aeropuertos.add(v);
	}
	
	public Iterator<Aeropuerto> getVertices() {
		return this.aeropuertos.iterator();
	}
	
	public String toString() {
		return "\n Grafo [ vertice: " + aeropuertos.toString() + "]";
	}
	
/*	public void dfs() {
		for (Aeropuerto v : aeropuertos) {
			v.setEstado("No visitado");			
		}
		
		for (Aeropuerto v : aeropuertos) {
			if(v.getEstado().equals("No visitado") ) {
				dfs_visit(v);
			}		
		}
	}
	
	private void dfs_visit(Aeropuerto v) {
		v.setEstado("Visitado");
		
		System.out.println(v.getInfo());
		
		for (ArcoDirigido a : v.getArcos()) {
			if(a.getDestino().getEstado().equals("No visitado")) {
				dfs_visit(a.getDestino());
			}
		}
		v.setEstado("Explorado");
	}
*/
}
