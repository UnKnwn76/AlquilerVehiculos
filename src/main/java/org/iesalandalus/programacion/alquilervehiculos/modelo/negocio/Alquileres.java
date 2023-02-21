/*************************************************
	 Ismail Hilmi
	 1ºDAW
	 Tarea online 05
*************************************************/

package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;

public class Alquileres {

	/*************************************************
	 * ATRIBUTOS Y CONSTANTES
	 *************************************************/
	
	List<Alquiler> coleccionAlquileres;

	/**************************************************
	 * CONSTRUCTORES
	 **************************************************/
	
	public Alquileres() {
		coleccionAlquileres = new ArrayList<>();
	}

	/***************************************************
	 * METODOS
	 ***************************************************/
	
	public List<Alquiler> get() {
		return new ArrayList<Alquiler>(coleccionAlquileres);
	}

	public List<Alquiler> get(Cliente cliente) {

		List<Alquiler> alquileresCliente = new ArrayList<>();

		// usamos el for each para recorrer la lista de alquileres
		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getCliente().equals(cliente)) {
				// si existe algun alquiler para dicho cliente lo añademos
				// a nuesta lista con los alquileres de dicho cliente (alquileresCliente)
				alquileresCliente.add(alquiler);
			}
		}
		return alquileresCliente;
	}

	public List<Alquiler> get(Turismo turismo) {

		List<Alquiler> alquileresTurimo = new ArrayList<>();

		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getTurismo().equals(turismo)) {
				alquileresTurimo.add(alquiler);
			}
		}
		return alquileresTurimo;
	}

	public int getCantidad() {
		return coleccionAlquileres.size();
	}

	private void comprobarAlquiler(Cliente cliente, Turismo turismo, LocalDate fechaAlquiler)
			throws OperationNotSupportedException {

		for (Alquiler alquiler : get(cliente)) {

			// Comprobar si el cliente tiene otro alquiler sin devolver
			if (alquiler.getFechaDevolucion() == null) {
				throw new OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver.");
			}

			// Comprobar si el cliente tiene un alquiler posterior
			if (alquiler.getFechaDevolucion().isAfter(fechaAlquiler)
					|| alquiler.getFechaDevolucion().isEqual(fechaAlquiler)) {
				throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");
			}
		}

		for (Alquiler alquiler : get(turismo)) {

			// Comprobar si el turismo está actualmente alquilado
			if (alquiler.getFechaDevolucion() == null) {
				throw new OperationNotSupportedException("ERROR: El turismo está actualmente alquilado.");
			}

			// Comprobar si el turismo tiene un alquiler posterior
			if ((alquiler.getFechaDevolucion().isAfter(fechaAlquiler)
					|| alquiler.getFechaDevolucion().isEqual(fechaAlquiler))) {
				throw new OperationNotSupportedException("ERROR: El turismo tiene un alquiler posterior.");
			}
		}

	}

	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		}
		comprobarAlquiler(alquiler.getCliente(), alquiler.getTurismo(), alquiler.getFechaAlquiler());
		coleccionAlquileres.add(alquiler);
	}

	public void devolver(Alquiler alquiler, LocalDate fechaDevolucion) throws OperationNotSupportedException {

		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler nulo.");
		}

		if (!coleccionAlquileres.contains(alquiler)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}
		// buscamos la posicion de alquiler en la lista
		// y luego llamamos al metodo devolver que asignará la fecha de devolución

		int posicionDeAlquiler = coleccionAlquileres.indexOf(alquiler);
		Alquiler alquilerDev = coleccionAlquileres.get(posicionDeAlquiler);
		alquilerDev.devolver(fechaDevolucion);

	}

	public Alquiler buscar(Alquiler alquiler) {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");
		}

		int posicionDeAlquiler = coleccionAlquileres.indexOf(alquiler);

		if (posicionDeAlquiler == -1) {
			return null;
		}
		return coleccionAlquileres.get(posicionDeAlquiler);

	}

	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {

		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");
		}

		int posicionDeAlquiler = coleccionAlquileres.indexOf(alquiler);
		if (posicionDeAlquiler == -1) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}
		coleccionAlquileres.remove(posicionDeAlquiler);
	}
}
