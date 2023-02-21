/*************************************************
	 Ismail Hilmi
	 1ºDAW
	 Tarea online 05
*************************************************/

package org.iesalandalus.programacion.alquilervehiculos.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.Alquileres;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.Clientes;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.Turismos;

public class Modelo {

	private Alquileres alquileres;
	private Clientes clientes;
	private Turismos turismos;

	public Modelo() {

	}

	public void comenzar() {
		alquileres = new Alquileres();
		clientes = new Clientes();
		turismos = new Turismos();
	}

	public void terminar() {
		System.out.println("El modelo ha terminado!!!");
	}

	/***************************************************
	 * METODOS DE INSERTAR
	 ***************************************************/
	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		Cliente nuevoCliente = new Cliente(cliente);
		clientes.insertar(nuevoCliente);

	}

	public void insertar(Turismo turismo) throws OperationNotSupportedException {
		Turismo nuevoTurismo = new Turismo(turismo);
		turismos.insertar(nuevoTurismo);

	}

	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede realizar un alquiler nulo.");
		}

		if (clientes.buscar(alquiler.getCliente()) == null) {
			throw new OperationNotSupportedException("ERROR: No existe el cliente del alquiler.");
		}

		if (turismos.buscar(alquiler.getTurismo()) == null) {
			throw new OperationNotSupportedException("ERROR: No existe el turismo del alquiler.");
		}
		Alquiler alquilerInsertado = new Alquiler(clientes.buscar(alquiler.getCliente()),
				turismos.buscar(alquiler.getTurismo()), alquiler.getFechaAlquiler());
		alquileres.insertar(alquilerInsertado);

	}

	/***************************************************
	 * METODOS DE BUSCAR
	 ***************************************************/

	public Cliente buscar(Cliente cliente) {
		return new Cliente(clientes.buscar(cliente));
	}

	public Turismo buscar(Turismo turismo) {
		return new Turismo(turismos.buscar(turismo));
	}

	public Alquiler buscar(Alquiler alquiler) {
		return new Alquiler(alquileres.buscar(alquiler));
	}

	/***************************************************
	 * METODO DE MODIFICAR
	 ***************************************************/

	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
		clientes.modificar(cliente, nombre, telefono);
	}

	/***************************************************
	 * METODO DE DEVOLVER
	 ***************************************************/

	public void devolver(Alquiler alquiler, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		Alquiler alquilerDev = alquileres.buscar(alquiler);
		if (alquilerDev == null) {
			throw new OperationNotSupportedException("ERROR: No existe el alquiler a devolver.");
		}
		alquilerDev.devolver(fechaDevolucion);
	}

	/***************************************************
	 * METODOS DE BORRAR
	 ***************************************************/

	public void borrar(Cliente cliente) throws OperationNotSupportedException {

		for (Alquiler alquiler : alquileres.get(cliente)) {
			alquileres.borrar(alquiler);// buscamos en la lista las qluileres de dicho cliente y las borramos
		}
		clientes.borrar(cliente); // luego borramos el cliente
	}

	public void borrar(Turismo turismo) throws OperationNotSupportedException {

		for (Alquiler alquiler : alquileres.get(turismo)) {
			alquileres.borrar(alquiler);// buscamos en la lista las alquileres de dicho turismo y las borramos
		}
		turismos.borrar(turismo); // luego borramos el turismo
	}

	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		alquileres.borrar(alquiler);

	}

	/***************************************************
	 * METODOS DE GET
	 ***************************************************/

	public List<Cliente> getClientes() {

		List<Cliente> listaNueva = new ArrayList<>();

		for (Cliente cliente : clientes.get()) {
			listaNueva.add(new Cliente(cliente));
		}
		return listaNueva;
	}

	public List<Turismo> getTurismos() {

		List<Turismo> listaNueva = new ArrayList<>();

		for (Turismo turismo : turismos.get()) {
			listaNueva.add(new Turismo(turismo));
		}
		return listaNueva;
	}

	public List<Alquiler> getAlquileres() {

		List<Alquiler> nuevaListaAlquileres = new ArrayList<>();

		for (Alquiler alquiler : alquileres.get()) {
			nuevaListaAlquileres.add(new Alquiler(alquiler));

		}
		return nuevaListaAlquileres;
	}

	public List<Alquiler> getAlquileres(Cliente cliente) {

		List<Alquiler> ListaAlquileresCliente = new ArrayList<>();

		for (Alquiler alquiler : alquileres.get(cliente)) {
			ListaAlquileresCliente.add(new Alquiler(alquiler));
		}
		return ListaAlquileresCliente;
	}

	public List<Alquiler> getAlquileres(Turismo turismo) {

		List<Alquiler> ListaAlquileresTurismo = new ArrayList<>();

		for (Alquiler alquiler : alquileres.get(turismo)) {
			ListaAlquileresTurismo.add(new Alquiler(alquiler));
		}
		return ListaAlquileresTurismo;

	}

}
