package main;

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

}
