package grafo;

import java.util.ArrayList;
import java.util.Iterator;
import servicios.ConsultaReservas;

public class Sistema_aeropuertos {
	private ArrayList<Aeropuerto> aeropuertos;
	
	public Sistema_aeropuertos() {
		aeropuertos = new ArrayList<Aeropuerto>();
	}
	
	public void add(Aeropuerto v) {
		this.aeropuertos.add(v);
	}
	
	public Iterator<Aeropuerto> getVertices() {
		return this.aeropuertos.iterator();
	}
	
	public void setearRuta_Aeropuerto(String aeropuerto_origen,String aeropuerto_destino, InformacionRuta info) {
		int i = 0;
		while(i < aeropuertos.size()-1 && !(aeropuertos.get(i).getNombre().equals(aeropuerto_origen))) {
			i++;
		}
		
		int j = 0;
		while(j < aeropuertos.size()-1 && !(aeropuertos.get(j).getNombre().equals(aeropuerto_destino))) {
			j++;
		}
		
		Ruta ruta = new Ruta(aeropuertos.get(j), info);
		aeropuertos.get(i).addArco(ruta);
		
		/*for(int i = 0; i < aeropuertos.size(); i++) {
			if(aeropuertos.get(i).getNombre() == aeropuerto_origen) {
				aeropuertos.get(i)
				
				for(int j = 0; j < aeropuertos.size(); j++) {
					if(aeropuertos.get(j).getNombre() == aeropuerto_destino) {
						Ruta ruta = new Ruta(this.aeropuertos.get(j), info);
						this.aeropuertos.get(i).addArco(ruta);
					}	
				}
			}
		}*/
	}
	
	public void setearReserva(String aeropuerto_origen, String aeropuerto_destino, String aerolinea, int reservas) {
		for(int i = 0; i < aeropuertos.size(); i++) {
			if(aeropuertos.get(i).getNombre().equals(aeropuerto_origen)) {
					aeropuertos.get(i).setReservaRuta(aeropuerto_destino, aerolinea, reservas);					
				}
			}	
	}
	
    public ArrayList<Aeropuerto> listarAeropuertos(){
    	ArrayList<Aeropuerto> salida = new ArrayList<Aeropuerto>(aeropuertos);
    	
    	return salida;
    }
    
    public ArrayList<ConsultaReservas> listarReservas(){
    	
    	ArrayList<ConsultaReservas> reservasSalida = new ArrayList<ConsultaReservas>();
    	
    	for(Aeropuerto a : aeropuertos) {
    		reservasSalida.addAll(a.getReservasDestino());
    	}
    	
    	return reservasSalida;
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
