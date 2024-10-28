package interfaz;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import modelo.entidad.Coche;
import modelo.enumerado.TipoMotor;
import modelo.negocio.GestorCoche;
import modelo.persistencia.DaoCoche;

public class InterfazUsuario {

	private GestorCoche gc;
	private Scanner scInt = new Scanner(System.in);
	private Scanner sc = new Scanner(System.in);

	public InterfazUsuario() {
		super();
		gc = new GestorCoche();
	}

	public void mostrarInterfaz() {

		System.out.println("-------------");
		System.out.println("APP DE COCHES");
		System.out.println("-------------");

		seleccionarOpcion();

	}

	public void seleccionarOpcion() {
		byte opcion = printMenu();

		switch (opcion) {
		case 0:
			salir();
			break;
		case 1:
			introducirCoche();
			break;
		case 2:
			mostrarCocheById();
			break;
		case 3:
			borrarCochePorId();
			break;
		case 4:
			listarTodosLosCoches();
			break;
		}
	}

	private void borrarCochePorId() {
		
		System.out.print("Escribe el ID del coche que deseas borrar: ");
		int idSeleccionado = scInt.nextInt();
		try {
			boolean resultado = gc.borrarById(idSeleccionado);
			if (resultado) {
				System.out.println("Coche borrado correctamente");
			} else {
				System.out.println("No se ha encontrado el coche indicado");
			}
			seleccionarOpcion();
		} catch (Exception e) {
			mensajeErrorArchivo(e);
		}
	}

	public byte printMenu() {

		byte opcion = 0;
		boolean flag = false;

		do {
			System.out.println("Seleccione una opción");
			System.out.println("");
			System.out.println("- 0. Salir");
			System.out.println("- 1. Introducir coche");
			System.out.println("- 2. Obtener coche por ID");
			System.out.println("- 3. Borrar coche por ID ");
			System.out.println("- 4. Listar todos los coches");
			System.out.println("");
			System.out.print("Opción: ");
			opcion = scInt.nextByte();
			try {
				flag = validarOpcion(opcion);
			} catch (InputMismatchException e) {
				System.out.println("Introduce una opción válida");
				System.out.println("");
			}
		} while (!flag);

		return opcion;
	}

	private boolean validarOpcion(byte opcion) throws InputMismatchException {

		if (opcion >= 0 && opcion <= 4) {
			return true;
		}
		return false;
	}

	private void listarTodosLosCoches() {
		List<Coche> lista = gc.listar();
		if(lista != null) {
			gc.listar().forEach(coche -> {
				System.out.println("=====================================");
				System.out.println("           Detalles del Coche        ");
				System.out.println("=====================================");
				System.out.println("ID Coche : " + coche.getId());
				System.out.println("Marca    : " + coche.getMarca());
				System.out.println("Modelo   : " + coche.getModelo());
				System.out.println("Motor    : " + coche.getTipoMotor());
				System.out.println("-------------------------------------");
			});
		}else {
			System.out.println("NO existe ningun coche");
		}
		seleccionarOpcion();
	}

	private void mostrarCocheById() {
		System.out.print("Escribe el ID del coche que deseas buscar: ");
		int idSeleccionado = scInt.nextInt();
		Coche c = null;
		try {
			c = gc.getById(idSeleccionado);
			System.out.println("=====================================");
			System.out.println("           Detalles del Coche        ");
			System.out.println("=====================================");
			System.out.println("ID Coche : " + c.getId());
			System.out.println("Marca    : " + c.getMarca());
			System.out.println("Modelo   : " + c.getModelo());
			System.out.println("Motor    : " + c.getTipoMotor());
			System.out.println("-------------------------------------");
			seleccionarOpcion();
		} catch (Exception e) {
			mensajeErrorArchivo(e);
		}
	}

	private void introducirCoche() {
		System.out.println("Rellena los datos del coche que quieres introducir en la base de datos: ");
		System.out.print("Introduce la marca: ");
		String marca = sc.nextLine();
		System.out.print("Introduce el modelo: ");
		String modelo = sc.nextLine();
		System.out.println("Seleccione un motor: ");
		System.out.println("- 1. Gasolina");
		System.out.println("- 2. Diésel");
		System.out.println("- 3. Hidrógeno");
		byte opcionMotor = scInt.nextByte();
		TipoMotor motor = null; // The local variable motor may not have been initialized

		switch (opcionMotor) {
		case 1:
			motor = TipoMotor.GASOLINA;
			break;
		case 2:
			motor = TipoMotor.DIESEL;
			break;
		case 3:
			motor = TipoMotor.HIDROGENO;
			break;
		}

		Coche c = new Coche(marca, modelo, motor);

		int resultadoValidacion = gc.guardar(c);

		while (resultadoValidacion != 4) {

			if (resultadoValidacion == 3) {
				System.out.println("Marca y modelo no válidos.");
				System.out.print("Por favor, escribe una marca: ");
				c.setMarca(sc.nextLine());
				System.out.print("Por favor, escribe un modelo: ");
				c.setModelo(sc.nextLine());
			}

			if (resultadoValidacion == 1) {
				System.out.println("No has escrito una marca. Por favor, escribe una marca: ");
				c.setMarca(sc.nextLine());
			}

			if (resultadoValidacion == 2) {
				System.out.println("No has escrito un modelo. Por favor, escribe un modelo: ");
				c.setModelo(sc.nextLine());
			}

			resultadoValidacion = gc.guardar(c);
		}

		try {
			gc.guardar(c);
			seleccionarOpcion();
		} catch (Exception e) {
			mensajeErrorArchivo(e);
		}

	}

	private void mensajeErrorArchivo(Exception e) {
		System.out.println("Ha ocurrido un error con el archivo.");
		System.out.println("Por favor, inténtelo más tarde");
		System.out.println("Error: " + e);
		seleccionarOpcion();
	}

	private void salir() {
		System.out.println("");
		System.out.println("-------------------");
		System.out.println("PROGRAMA FINALIZADO");
		System.out.println("-------------------");
	}
}
