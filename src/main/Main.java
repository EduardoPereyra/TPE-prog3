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

public class Main {

	private static Scanner scanner;

	public static void main(String[] args) {
		String path = "C:\\Users\\tutip\\Desktop\\Proyectos Java\\TPE-Prog3";
		Sistema_aeropuertos trivago = new Sistema_aeropuertos(); 
		readerAeropuertos(path,trivago);
		readerRutas(path,trivago);
		readerReservas(path,trivago);

		int opcion = menu();
		elegirOpcion(opcion,trivago);

		
	}
	
	public static int menu() {
		System.out.println("===================================");
		System.out.println("Ingrese una opcion: ");
		System.out.println("1- Listar todos los aeropuertos.");
		System.out.println("2- Listar todas las reservas realizadas.");
		System.out.println("3- Servicio 1: Verificar vuelo directo.");
		System.out.println("4- Servicio 2: Obtener vuelos sin aerolínea.");
		System.out.println("5- Servicio 3: Vuelos disponibles.");
		System.out.println("0- Salir");
		System.out.println("===================================");
        scanner = new Scanner(System.in);
		int opcion;
		opcion = scanner.nextInt();
		return opcion;
	}
	
	public static void elegirOpcion(int opcion, Sistema_aeropuertos trivago) {
		System.out.println(opcion);
		switch (opcion) {
		case 1:
			listarAeropuertos(trivago);
			break;
		case 2:
			System.out.println("No podemos resolver su consulta en este momento, intente nuevamente mas tarde");
			break;
		case 3:
			System.out.println("No podemos resolver su consulta en este momento, intente nuevamente mas tarde");
			break;
		case 4:
			System.out.println("No podemos resolver su consulta en este momento, intente nuevamente mas tarde");
			break;
		case 5:
			System.out.println("No podemos resolver su consulta en este momento, intente nuevamente mas tarde");
			break;
		case 0:
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
	
	    public static void readerAeropuertos(String path, Sistema_aeropuertos trivago){
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
	    
	    public static void readerRutas(String path, Sistema_aeropuertos trivago){ 
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

	            }
	            System.out.println("Cargado con exito.");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public static void readerReservas(String path, Sistema_aeropuertos trivago){ 
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
	
		public static void write(String path) {
			BufferedWriter bw = null;
			try {
				File file = new File( path+ "/salida.csv");
				if (!file.exists()) {
					file.createNewFile();
				}

				FileWriter fw = new FileWriter(file);
				bw = new BufferedWriter(fw);

				// Escribo la primer linea del archivo
				String contenidoLinea1 = "Usuario1;Tiempo1";
				bw.write(contenidoLinea1);
				bw.newLine();

				// Escribo la segunda linea del archivo
				String contenidoLinea2 = "Usuario2;Tiempo2";
				bw.write(contenidoLinea2);
				bw.newLine();				
				/*
				 *
				 * ... 
				 * 
				*/

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
		
		public static void listarAeropuertos(Sistema_aeropuertos trivago) {
			ArrayList<Aeropuerto> aux = trivago.listarAeropuertos();
			for(int i = 0; i < aux.size(); i++) {
				System.out.println((i + 1) + "-" + aux.get(i).toString());
			}
		}
}
