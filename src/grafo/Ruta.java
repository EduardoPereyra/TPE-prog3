package grafo;

public class Ruta {
	private Aeropuerto destino;
	private InformacionRuta info;
	
	public Ruta(Aeropuerto destino,InformacionRuta info) {
		this.destino = destino;
		this.info = info;
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
}
