package main;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		menu();
        Scanner scanner = new Scanner(System.in);
		int opcion;
		opcion = scanner.nextInt();
		elegirOpcion(opcion);

		
	}
	
	public static void menu() {
		System.out.println("===================================");
		System.out.println("Ingrese una opcion: ");
		System.out.println("1- Listar todos los aeropuertos.");
		System.out.println("2- Listar todas las reservas realizadas.");
		System.out.println("3- Servicio 1: Verificar vuelo directo.");
		System.out.println("4- Servicio 2: Obtener vuelos sin aerolínea.");
		System.out.println("5- Servicio 3: Vuelos disponibles.");
		System.out.println("0- Salir");
		System.out.println("===================================");
	}
	
	public static void elegirOpcion(int opcion) {
		System.out.println(opcion);
	}

}
