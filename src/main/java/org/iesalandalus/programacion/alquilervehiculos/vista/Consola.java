/*************************************************
	 Ismail Hilmi
	 1ºDAW
	 Tarea online 05
*************************************************/

package org.iesalandalus.programacion.alquilervehiculos.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	
	/*************************************************
	 * ATRIBUTOS Y CONSTANTES
	 *************************************************/
	
	private final static String PATRON_FECHA = "dd/MM/yyyy";
	private final static DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern(PATRON_FECHA);
	
	/**************************************************
	 * CONSTRUCTORES
	 **************************************************/ 
	
	private Consola() {

	}

	public static void mostrarCabecera(String mensaje) {
		System.out.println(); // para saltar linea
		System.out.println(mensaje);
		for (int i = 0; i < mensaje.length(); i++) {
			System.out.print("-");
		}
		System.out.println(); // saltar de linea para crear más espacio en la consola.
	}

	public static void mostrarMenu() {

		mostrarCabecera("Bienvenido a nuesta aplicación, aquí tiene el menú de opciones");
		System.out.println(); // saltar de linea para crear más espacio en la consola.
		for (Opcion opciones : Opcion.values()) {
			System.out.println(opciones);

		}
		System.out.println(); // saltar de linea para crear más espacio en la consola.
	}

	private static String leerCadena(String mensaje) {
		System.out.print(mensaje);
		String cadena = Entrada.cadena();
		return cadena;
	}

	private static int leerEntero(String mensaje) {
		System.out.print(mensaje);
		int entero = Entrada.entero();
		return entero;
	}

	private static LocalDate leerFecha(String mensaje) {
		LocalDate fecha = null;
		boolean error;
		do {
			error = false;
			try {
				System.out.print(mensaje);
				fecha = LocalDate.parse(Entrada.cadena(), FORMATO_FECHA);
			} catch (Exception e) {
				System.out.println("Debe introducir una fecha con formato " + PATRON_FECHA);
				error = true;
			}
		} while (error);

		return fecha;
	}

	public static Opcion elegirOpcion() {
		int opcion = 0;
		Opcion opcionElegida = null;
		do {
			try {
				opcion = leerEntero("Introduce una opcion válida: ");
				opcionElegida = Opcion.get(opcion);
				for (Opcion opcionesMenu : Opcion.values()) {
					if (opcionesMenu.ordinal() == opcion) {
						opcionElegida = opcionesMenu;
					}
				}
			} catch (Exception e) {
				mostrarCabecera(e.getMessage());
			}
		} while (opcion < 0 || opcion >= Opcion.values().length);

		return opcionElegida;

	}

	public static Cliente leerClienteDni() throws OperationNotSupportedException {
		String dni = leerCadena("Introduce el DNI del cliente: ");
		Cliente cliente = Cliente.getClienteConDni(dni);
		return cliente;
	}

	public static String leerNombre() throws OperationNotSupportedException {
		return leerCadena("Introduce el nombre del cliente: ");

	}

	public static String leerTelefono() throws OperationNotSupportedException {
		return leerCadena("Introduce el telefono del cliente : ");
	}

	public static Cliente leerCliente() throws OperationNotSupportedException {
		return new Cliente(leerNombre(), leerClienteDni().getDni(), leerTelefono());
	}

	public static Turismo leerTurismoMatricula() throws OperationNotSupportedException {
		String matricula = leerCadena("Introduzca la matricula del turismo : ");
		Turismo turismo = Turismo.getTurismoConMatricula(matricula);
		return turismo;
	}

	public static Turismo leerTurismo() throws OperationNotSupportedException {
		String marca = leerCadena("Introduce la marca: ");
		String modelo = leerCadena("Introduce el modelo: ");
		int cilindrada = leerEntero("Introduce la cilindrada: ");
		return new Turismo(marca, modelo, cilindrada, leerTurismoMatricula().getMatricula());
	}

	public static Alquiler leerAlquiler() throws OperationNotSupportedException {
		LocalDate fechaAlquiler = leerFecha("Introduzca la fecha de alquiler (dd/MM/yyyy): ");
		return new Alquiler(leerClienteDni(), leerTurismoMatricula(), fechaAlquiler);
	}

	public static LocalDate leerFechaDevolucion() throws OperationNotSupportedException {
		LocalDate fechaDevolucion = leerFecha("Introduzca la fecha de devolucion (dd/MM/yyyy): ");
		return fechaDevolucion;
	}

}
