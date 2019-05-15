package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import grafo.Aeropuerto;
import grafo.InformacionRuta;
import grafo.Sistema_aeropuertos;
import respuestas.ConsultaReservas;
import respuestas.VueloDirecto;
import respuestas.VueloDirectoConAerolinea;
import respuestas.VuelosSinAerolinea;

public class Main {

	private static Scanner scanner;

	public static void main(String[] args) {
		String path = "C:\\Users\\tutip\\Desktop\\Proyectos Java\\TPE-Prog3"; //path de los archivos
		//String path = "C:\\Users\\ezequiel\\eclipse-workspace\\TPE-prog3";
		Sistema_aeropuertos trivago = new Sistema_aeropuertos(); //creacion del sistema de aeropuerto
		readerAeropuertos(path,trivago); //carga de aeropuertos
		readerRutas(path,trivago); //carga de rutas
		readerReservas(path,trivago); //carga de reservas

		int opcion = menu();
		elegirOpcion(opcion,trivago); //menu

		
	}
	
	public static int menu() { //imprime el menu y se queda esperando a que el usuario ingrese una opcion
		System.out.println("\n\n===================================");
		System.out.println("Ingrese una opcion: ");
		System.out.println("1- Listar todos los aeropuertos.");
		System.out.println("2- Listar todas las reservas realizadas.");
		System.out.println("3- Servicio 1: Verificar vuelo directo.");
		System.out.println("4- Servicio 2: Obtener vuelos sin aerolínea.");
		System.out.println("5- Servicio 3: Vuelos disponibles directos.");
		System.out.println("0- Salir");
		System.out.println("===================================\n\n");
        scanner = new Scanner(System.in);
		int opcion;
		opcion = scanner.nextInt();
		return opcion;
	}
	
	public static void elegirOpcion(int opcion, Sistema_aeropuertos trivago) { //mediante la opcion ingresada imprime y llama a la funcion correspondiente
		System.out.println(opcion);
		switch (opcion) {
		case 1:
			System.out.println("---Listar todos los aeropuertos---");
			listarAeropuertos(trivago);
			break;
		case 2:
			System.out.println("---Listar todas las reservas realizadas---");
			listarReservas(trivago);
			break;
		case 3:
			System.out.println("---Servicio 1: Verificar vuelo directo---");
			System.out.println("Ingrese Aeropuerto Origen");
			String origen = insertarString();
			System.out.println("Ingrese Aeropuerto Destino");
			String destino = insertarString();
			System.out.println("Ingrese Aerolinea");
			String aerolinea = insertarString();
			verificarVueloDirectoAerolinea(trivago,origen,destino,aerolinea);
			break;
		case 4:
			System.out.println("---Servicio 2: Obtener vuelos sin aerolínea---");
			System.out.println("Ingrese Aeropuerto Origen");
			String origen1 = insertarString();
			System.out.println("Ingrese Aeropuerto Destino");
			String destino1 = insertarString();
			System.out.println("Ingrese Aerolinea que no quiere utilizar");
			String aerolinea1 = insertarString();
			listarVuelosSinAerolinea(trivago,origen1,destino1,aerolinea1);
			break;
		case 5:
			System.out.println("---Servicio 3: Vuelos disponibles directos---");
			System.out.println("Ingrese Aeropuerto Origen");
			String origen2 = insertarString();
			System.out.println("Ingrese Aeropuerto Destino");
			String destino2 = insertarString();
			listarVuelosDirecto(trivago,origen2,destino2);
			break;
		case 0:
			System.out.println("---Salir---");
			System.out.println("Gracias por utilizar nuestro sistema de aeropuertos. Vuelva pronto!");
			break;

		default:
			System.out.println("No es un valor valido");
			break;
		}
		if(opcion != 0) {
			opcion = menu();
			elegirOpcion(opcion, trivago);
		}
	}
	
	    public static void readerAeropuertos(String path, Sistema_aeropuertos trivago){ //carga de aeropuertos
	        String csvFile = path +"\\Aeropuertos.csv";
	        System.out.println(csvFile);
	        String line = "";
	        String cvsSplitBy = ";";
	        Aeropuerto aux;

	        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

	            while ((line = br.readLine()) != null) {

	                String[] items = line.split(cvsSplitBy);
	                aux = new Aeropuerto(items[0], items[1], items[2]);
	                trivago.add(aux);
	            }
	            System.out.println("Cargado con exito.");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public static void readerRutas(String path, Sistema_aeropuertos trivago){ //carga de rutas
	        String csvFile = path +"\\Rutas.csv";
	        System.out.println(csvFile);
	        String line = "";
	        String cvsSplitBy = ";";
	        String guion = "-";
	        String coma = ",";
	        InformacionRuta info;

	        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

	            while ((line = br.readLine()) != null) {

	                String[] items = line.split(cvsSplitBy);
	                info = new InformacionRuta(Double.parseDouble(items[2]),Boolean.parseBoolean(items[3]));
	                items[4] = items[4].replaceAll("\\{","");
	                items[4] = items[4].replaceAll("\\}","");
	                String subitems[] = items[4].split(coma);
	                for(int i = 0; i<subitems.length; i++) {
	                	String aerolinea[] = subitems[i].split(guion);
		                info.setAerolineas(aerolinea[0], Integer.parseInt(aerolinea[1]));
	                }
	                trivago.setearRuta_Aeropuerto(items[0],items[1],info);
	                trivago.setearRuta_Aeropuerto(items[1], items[0], info);  //seteo la misma ruta pero de "vuelta"

	            }
	            System.out.println("Cargado con exito.");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public static void readerReservas(String path, Sistema_aeropuertos trivago){ //carga de reservas
	        String csvFile = path +"\\Reservas.csv";
	        System.out.println(csvFile);
	        String line = "";
	        String cvsSplitBy = ";";

	        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

	            while ((line = br.readLine()) != null) {

	                String[] items = line.split(cvsSplitBy);
	                trivago.setearReserva(items[0], items[1], items[2], Integer.parseInt(items[3]));

	            }
	            System.out.println("Cargado con exito.");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
		public static void write(String path, String opcionEjecutada, String mensajeSalida) { //imprime en un archivo salida.txt el mensaje 
			BufferedWriter bw = null;
			try {
				File file = new File( path+ "/salida.txt");
				if (!file.exists()) {
					file.createNewFile();
				}

				FileWriter fw = new FileWriter(file);
				bw = new BufferedWriter(fw);

				// Escribo la primer linea del archivo
				String contenidoLinea1 = opcionEjecutada;
				bw.write(contenidoLinea1);
				bw.newLine();

				// Escribo la segunda linea del archivo
				String contenidoLinea2 = mensajeSalida;
				bw.write(contenidoLinea2);
				bw.newLine();				
				
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				try {
					if (bw != null)
						bw.close();
				} catch (Exception ex) {
					System.out.println("Error cerrando el BufferedWriter" + ex);
				}
			}
		}
		
		public static void listarAeropuertos(Sistema_aeropuertos trivago) { //muestra una lista de todos los aeropuertos
			ArrayList<Aeropuerto> aux = trivago.listarAeropuertos();
			for(int i = 0; i < aux.size(); i++) {
				System.out.println((i + 1) + "-" + aux.get(i).toString());
			}
		}
		
		public static void listarReservas(Sistema_aeropuertos trivago) { //lista todas las reservas de todos los aeropuertos de todas las aerolineas
			ArrayList<ConsultaReservas> aux = trivago.listarReservas();
			for(int i = 0; i < aux.size(); i++) {
				System.out.println(" Reserva : "+ (i + 1) +"\t"+ aux.get(i).toString());
			}
		}
		
		public static void verificarVueloDirectoAerolinea(Sistema_aeropuertos trivago, String origen, String destino, String aerolinea) { //muestra si hay vuelo directo 
			VueloDirectoConAerolinea resultado = trivago.verificarVueloDirectoAerolinea(origen, destino, aerolinea);	 						//desde un origen hacia un destino con una aerolinea
			if(resultado != null) {
			System.out.println("Desde el Aeropuerto : "+ origen +"\nHasta : " + destino + "\nCon la aerolinea : " 
					+ aerolinea + "\nHay : " + resultado.getKm() + " km \nTeniendo disponibles : " + resultado.getCant_asientos() + " asientos" );
			}else {
				System.out.println("No hay vuelo directo.");	
			}
		}
		
		public static void listarVuelosSinAerolinea(Sistema_aeropuertos trivago, String origen, String destino, String aerolinea) { //lista todos los vuelos posibles sin utilizar una aerolinea
			ArrayList<VuelosSinAerolinea> vuelos = trivago.listarVuelosSinAerolinea(origen, destino, aerolinea);
			for(int i=0; i<vuelos.size(); i++) {
				if(vuelos.get(i).getEscalas() < 5) {
					System.out.println("Desde el Aeropuerto : "+ origen +"\nHasta : " + destino + "\nSin utilizar la Aerolinea : " + aerolinea+ "\n" + vuelos.get(i).toString());	
				}
			}
			
			if(vuelos.isEmpty()) {
				System.out.println("\nNo existe caminos posibles sin esa aerolinea");
			}
			
		}
		
		public static void listarVuelosDirecto(Sistema_aeropuertos trivago, String paisOrigen, String paisDestino) { //lista vuelos directos entre dos paises 
			ArrayList<VueloDirecto> resultado = trivago.listarVuelosDirectos(paisOrigen, paisDestino);
			if(!resultado.isEmpty()) {
				
				System.out.println("Desde: "+ paisOrigen +" | Hasta : " + paisDestino);
				for(int i = 0; i< resultado.size(); i++) {
					System.out.println(resultado.get(i));
				}
			}
			else {
				System.out.println("No hay vuelo directo.");	
			}
		}
		
		private static String insertarString() { //devuelve el string ingresado por el usuario
	        scanner = new Scanner(System.in);
	        return scanner.nextLine();	
		}
}
