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
	
	public void setearRuta_Aeropuerto(String aeropuerto_origen,String aeropuerto_destino, InformacionRuta info) {
		for(int i = 0; i < this.aeropuertos.size(); i++) {
			if(this.aeropuertos.get(i).getNombre() == aeropuerto_origen) {
				for(int j = 0; j < this.aeropuertos.size(); j++) {
					if(this.aeropuertos.get(j).getNombre() == aeropuerto_destino) {
						Ruta ruta = new Ruta(this.aeropuertos.get(j), info);
						this.aeropuertos.get(i).addArco(ruta);
					}	
				}
			}
		}
	}
	
	public void setearReserva(String aeropuerto_origen, String aeropuerto_destino, String aerolinea, int reservas) {
		for(int i = 0; i < this.aeropuertos.size(); i++) {
			if(this.aeropuertos.get(i).getNombre() == aeropuerto_origen) {
				if(this.aeropuertos.get(i).getRuta(aeropuerto_destino) != null) {
					this.aeropuertos.get(i).getRuta(aeropuerto_destino).setReserva(aerolinea, reservas);					
				}
			}	
		}
	}
	
    public ArrayList<Aeropuerto> listarAeropuertos(){
    	ArrayList<Aeropuerto> salida = new ArrayList<Aeropuerto>(aeropuertos);
    	
    	return salida;
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
