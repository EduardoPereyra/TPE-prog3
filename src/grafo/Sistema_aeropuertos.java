package grafo;

import java.util.ArrayList;
import java.util.Iterator;

import respuestas.ConsultaReservas;
import respuestas.ConsultaVueloDirecto;
import respuestas.VuelosSinAerolinea;

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
		aeropuertos.get(i).addRuta(ruta);
		
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
		int i = 0;
		while(i < aeropuertos.size()-1 && !(aeropuertos.get(i).getNombre().equals(aeropuerto_origen))) {
			i++;
		}
		aeropuertos.get(i).setReservaRuta(aeropuerto_destino, aerolinea, reservas);					
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
    
    public ConsultaVueloDirecto verificarVueloDirecto(String origen, String destino, String aerolinea) {
    	int i = 0;
    	while((i < this.aeropuertos.size()-1)&&(!this.aeropuertos.get(i).getNombre().equals(origen))) {
    		i++;
    	}
    	return this.aeropuertos.get(i).verificarDestino(destino, aerolinea);
    }
	
	
	public ArrayList<VuelosSinAerolinea> listarVuelosSinAerolinea(String origen, String destino, String aerolinea) {
		ArrayList<VuelosSinAerolinea> resultado = new ArrayList<>();
		for (Aeropuerto a : aeropuertos) {
			a.setEstado("No visitado");			
		}
    	int i = 0;
    	while((i < this.aeropuertos.size()-1)&&(!this.aeropuertos.get(i).getNombre().equals(origen))) {
    		i++;
    	}	
    		double km = 0;
    		int cant_escalas = 0;
			resultado.addAll(dfs_visit(destino,km,cant_escalas,this.aeropuertos.get(i)));
	
		return resultado;
	}
	
	private ArrayList<VuelosSinAerolinea> dfs_visit(String destino, double km, int cant_escalas, Aeropuerto a) {
		ArrayList<VuelosSinAerolinea> resultado = new ArrayList<>();
		a.setEstado("Visitado");
		for (Ruta r : a.getRutas()) {
			if((r.getDestino().getEstado().equals("No visitado"))&&(!(r.getDestino().getNombre().equals(destino)))) {
				km += r.getInfo().getKm();
				cant_escalas += 1;
				/*falta aerolinea*/
				dfs_visit(destino, km, cant_escalas, r.getDestino());
			}else {
				VuelosSinAerolinea vuelo = new VuelosSinAerolinea();
				vuelo.setCant_km(km);
				vuelo.setEscalas(cant_escalas);
				/*agregar Aerolineas*/
				resultado.add(vuelo);			
			}
		}
		a.setEstado("Explorado");
		return resultado;
	}

}
