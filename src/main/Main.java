package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	private static Scanner scanner;

	public static void main(String[] args) {
		int opcion = menu();
		elegirOpcion(opcion);

		
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
	
	public static void elegirOpcion(int opcion) {
		System.out.println(opcion);
		switch (opcion) {
		case 1:
			
			break;
		case 2:
			
			break;
		case 3:
			
			break;
		case 4:
			
			break;
		case 5:
			
			break;
		case 0:
			
			break;

		default:
			System.out.println("No es un valor valido");
			opcion = menu();
			elegirOpcion(opcion);
			break;
		}
	}
	

	    public static void reader(String path){ //diferentes readers para cada clase
	        String csvFile = path +"/dataset.csv";
	        String line = "";
	        String cvsSplitBy = ";";

	        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

	            while ((line = br.readLine()) != null) {

	                String[] items = line.split(cvsSplitBy);
/*	                items[0] --> nombre del aeropuerto
	                items[1] --> ciudad
	                items[2] --> pais
*/	                // ---------------------------------------------
	                // Poner el codigo para cargar los datos
	                // ---------------------------------------------

	            }
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
				bw.write(contenidoLinea2);
				bw.newLine();
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
}
