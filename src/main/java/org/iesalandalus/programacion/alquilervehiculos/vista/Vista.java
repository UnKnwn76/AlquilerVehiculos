/*************************************************
	 Ismail Hilmi
	 1ºDAW
	 Tarea online 05
*************************************************/

package org.iesalandalus.programacion.alquilervehiculos.vista;

import java.time.LocalDate;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;

public class Vista {
	
	/*************************************************
	 * ATRIBUTOS Y CONSTANTES
	 *************************************************/
	
	private Controlador controlador;

	/***************************************************
	 * METODOS
	 ***************************************************/
	
	public void setControlador(Controlador controlador) {
		if (controlador == null) {
			throw new NullPointerException("ERROR: El controldor no puede ser nulo.");
		}
		this.controlador = controlador;
	}

	public void comenzar() throws OperationNotSupportedException {
		Opcion opcion;
		do {
			Consola.mostrarMenu();
			opcion = Consola.elegirOpcion();
			ejecutar(opcion);
		} while (opcion != Opcion.SALIR);
	}

	public void terminar() {
		System.out.println("Gracias por utilizar nuestra aplicación. ¡Hasta pronto!");
	}

	private void ejecutar(Opcion opcion) throws OperationNotSupportedException {

		switch (opcion) {
		case INSERTAR_CLIENTE:
			insertarCliente();
			break;

		case INSERTAR_TURISMO:
			insertarTurismo();
			break;

		case INSERTAR_ALQUILER:
			insertarAlquiler();
			break;

		case BUSCAR_CLIENTE:
			buscarCliente();
			break;

		case BUSCAR_TURISMO:
			buscarTurismo();
			break;

		case BUSCAR_ALQUILER:
			buscarAlquiler();
			break;

		case MODIFICAR_CLIENTE:
			modificarCliente();
			break;

		case DEVOLVER_ALQUILER:
			devolverAlquiler();
			break;

		case BORRAR_CLIENTE:
			borrarCliente();
			break;

		case BORRAR_TURISMO:
			borrarTurismo();
			break;

		case BORRAR_ALQUILER:
			borrarAlquiler();
			break;

		case LISTAR_CLIENTES:
			listarClientes();
			break;

		case LISTAR_TURISMOS:
			listarTurismos();
			break;

		case LISTAR_ALQUILERES:
			listarAlquileres();
			break;

		case LISTAR_ALQUILERES_CLIENTE:
			listarAlquileresCliente();
			break;

		case LISTAR_ALQUILERES_TURISMO:
			listarAlquileresTurismo();
			break;

		case SALIR:
			terminar();
			break;

		default:
			break;
		}
	}

	/***************************************************
	 * METODOS DE INSERTAR 
	 ***************************************************/
	
	private void insertarCliente() throws OperationNotSupportedException {
		Consola.mostrarCabecera("La opcion de insertar cliente nuevo: ");
		try {
			Cliente cliente = Consola.leerCliente();
			controlador.insertar(cliente);
			Consola.mostrarCabecera("Cliente insertado correctamente."); // si el cliente ha insertado correctamente nos devuelve este mensaje
		} catch (Exception e) {
			Consola.mostrarCabecera(e.getMessage());
		}
	}

	private void insertarTurismo() throws OperationNotSupportedException {
		Consola.mostrarCabecera("La opcion de insertar turismo nuevo: ");
		try {
			Turismo turismo = Consola.leerTurismo();
			controlador.insertar(turismo);
			Consola.mostrarCabecera("Turismo insertado correctamente."); // si el turismo ha insertado correctamente nos devuelve este mensaje
		} catch (Exception e) {
			Consola.mostrarCabecera(e.getMessage());
		}

	}

	private void insertarAlquiler() throws OperationNotSupportedException {
		Consola.mostrarCabecera("La opcion de insertar alquiler nuevo: ");
		try {
			Alquiler alquiler = Consola.leerAlquiler();
			controlador.insertar(alquiler);
			Consola.mostrarCabecera("Alquiler insertado correctamente."); // si el alquiler ha insertado correctamente nos devuelve este mensaje
		} catch (Exception e) {
			Consola.mostrarCabecera(e.getMessage());
		}

	}


	/***************************************************
	 * METODOS DE BUSCAR
	 ***************************************************/
	
	private void buscarCliente() {
		Consola.mostrarCabecera("La opcion de buscar un cliente: ");
		try {
			Cliente cliente = Consola.leerClienteDni();
			Consola.mostrarCabecera("Cliente encontrado: " + controlador.buscar(cliente));
		} catch (Exception e) {
			Consola.mostrarCabecera(e.getMessage());
		}
	}

	private void buscarTurismo() throws OperationNotSupportedException {
		Consola.mostrarCabecera("La opcion de buscar un turismo: ");
		try {
			Turismo turismo = Consola.leerTurismoMatricula();
			Consola.mostrarCabecera("Turismo encontrado: " + controlador.buscar(turismo));
		} catch (Exception e) {
			Consola.mostrarCabecera(e.getMessage());
		}
	}

	private void buscarAlquiler() throws OperationNotSupportedException {
		Consola.mostrarCabecera("La opcion de buscar un alquiler: ");
		try {
			Alquiler alquiler = Consola.leerAlquiler();
			Consola.mostrarCabecera("Alquiler encontrado: " + controlador.buscar(alquiler));
		} catch (Exception e) {
			Consola.mostrarCabecera(e.getMessage());
		}
	}

	/***************************************************
	 * METODO DE MODIFICAR
	 ***************************************************/
	
	private void modificarCliente() throws OperationNotSupportedException {
		Consola.mostrarCabecera("La opcion de modificar un cliente: ");
		try {
			Cliente cliente = Consola.leerClienteDni();
			String nombre = Consola.leerNombre();
			String telefono = Consola.leerTelefono();
			controlador.modificar(cliente, nombre, telefono);
			Consola.mostrarCabecera("La modificación ha sido correcta."); // si la modificacion ha sido correctamente nos devuelve este mensaje
		} catch (Exception e) {
			Consola.mostrarCabecera(e.getMessage());
		}
	}

	/***************************************************
	 * METODO DE DEVOLVER
	 ***************************************************/
	
	private void devolverAlquiler() throws OperationNotSupportedException {
		Consola.mostrarCabecera("La opcion de devolver un alquiler: ");
		try {
			Alquiler alquiler = Consola.leerAlquiler();
			LocalDate fechaDevolucion = Consola.leerFechaDevolucion();
			controlador.devolver(alquiler, fechaDevolucion);
			Consola.mostrarCabecera("La devolución ha sido correctamente."); // si la devolucion ha sido correctamente nos devuelve este mensaje
		} catch (Exception e) {
			Consola.mostrarCabecera(e.getMessage());
		}

	}

	/***************************************************
	 * METODOS DE BORRAR
	 ***************************************************/
	
	private void borrarCliente() throws OperationNotSupportedException {
		Consola.mostrarCabecera("La opcion de borrar un cliente: ");
		try {
			Cliente cliente = Consola.leerClienteDni();
			controlador.borrar(cliente);
			Consola.mostrarCabecera("El cliente ha sido borrado correctamente."); // si el cliente ha sido borrado correctamente nos devuelve este mensaje
		} catch (Exception e) {
			Consola.mostrarCabecera(e.getMessage());
		}

	}

	private void borrarTurismo() throws OperationNotSupportedException {
		Consola.mostrarCabecera("La opcion de borrar un turismo: ");
		try {
			Turismo turismo = Consola.leerTurismoMatricula();
			controlador.borrar(turismo);
			Consola.mostrarCabecera("El Turismo ha sido borrado correctamente."); // si el turismo ha sido borrado correctamente nos devuelve este mensaje
		} catch (Exception e) {
			Consola.mostrarCabecera(e.getMessage());
		}

	}

	private void borrarAlquiler() throws OperationNotSupportedException {
		Consola.mostrarCabecera("La opcion de borrar un alquiler: ");
		try {
			Alquiler alquiler = Consola.leerAlquiler();
			controlador.borrar(alquiler);
			Consola.mostrarCabecera("El Alquiler ha sido borrado correctamente."); // si el alquiler ha sido borrado correctamente nos devuelve este mensaje
		} catch (Exception e) {
			Consola.mostrarCabecera(e.getMessage());
		}

	}

	/***************************************************
	 * METODOS DE LISTAR
	 ***************************************************/
	
	private void listarClientes() {
		Consola.mostrarCabecera("La opcion de Listar Clientes");
		try {
			List<Cliente> clientes = controlador.getClientes();
			Consola.mostrarCabecera(clientes.toString());
		} catch (Exception e) {
			Consola.mostrarCabecera(e.getMessage());
		}
	}

	private void listarTurismos() {
		Consola.mostrarCabecera("La opcion de listar turismos: ");
		try {
			List<Turismo> turismos = controlador.getTurismos();
			Consola.mostrarCabecera(turismos.toString());
		} catch (Exception e) {
			Consola.mostrarCabecera(e.getMessage());
		}
	}

	private void listarAlquileres() {
		Consola.mostrarCabecera("La opcion de listar alquileres: ");
		try {
			List<Alquiler> alquileres = controlador.getAlquileres();
			Consola.mostrarCabecera(alquileres.toString());
		} catch (Exception e) {
			Consola.mostrarCabecera(e.getMessage());
		}
	}

	private void listarAlquileresCliente() throws OperationNotSupportedException {
		Consola.mostrarCabecera("La opcion de listar alquileres de cliente: ");
		try {
			Cliente cliente = Consola.leerClienteDni();
			List<Alquiler> alquileresCliente = controlador.getAlquileres(cliente);
			Consola.mostrarCabecera(alquileresCliente.toString());
		} catch (Exception e) {
			Consola.mostrarCabecera(e.getMessage());
		}
	}

	private void listarAlquileresTurismo() throws OperationNotSupportedException {
		Consola.mostrarCabecera("La opcion de listar alquileres de turismo: ");
		try {
			Turismo turismo = Consola.leerTurismoMatricula();
			List<Alquiler> alquileresTurismo = controlador.getAlquileres(turismo);
			Consola.mostrarCabecera(alquileresTurismo.toString());
		} catch (Exception e) {
			Consola.mostrarCabecera(e.getMessage());
		}
	}

}