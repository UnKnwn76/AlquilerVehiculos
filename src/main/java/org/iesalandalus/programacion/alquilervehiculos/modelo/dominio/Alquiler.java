/*************************************************
	 Ismail Hilmi
	 1ºDAW
	 Tarea online 05
*************************************************/
package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import javax.naming.OperationNotSupportedException;

public class Alquiler {

	/*************************************************
	 * ATRIBUTOS Y CONSTANTES
	 *************************************************/

	protected static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyy");
	private final static int PRECIO_DIA = 20;

	private LocalDate fechaAlquiler;
	private LocalDate fechaDevolucion;

	private Cliente cliente;
	private Turismo turismo;

	/**************************************************
	 * CONSTRUCTORES
	 **************************************************/

	public Alquiler(Cliente cliente, Turismo turismo, LocalDate fechaAlquiler) {

		setCliente(cliente);
		setTurismo(turismo);
		setFechaAlquiler(fechaAlquiler);

	}

	public Alquiler(Alquiler alquiler) {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No es posible copiar un alquiler nulo.");
		}
		cliente = new Cliente(alquiler.getCliente());
		turismo = new Turismo(alquiler.getTurismo());
		fechaAlquiler = alquiler.getFechaAlquiler();
		fechaDevolucion = alquiler.getFechaDevolucion();
	}

	/**************************************************
	 * GETTERS/SETTERS
	 **************************************************/

	public LocalDate getFechaAlquiler() {
		return fechaAlquiler;
	}

	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Turismo getTurismo() {
		return turismo;
	}

	private void setFechaAlquiler(LocalDate fechaAlquiler) {
		LocalDate hoy = LocalDate.now();
		if (fechaAlquiler == null) {
			throw new NullPointerException("ERROR: La fecha de alquiler no puede ser nula.");
		}
		if (fechaAlquiler.isAfter(hoy)) {
			throw new IllegalArgumentException("ERROR: La fecha de alquiler no puede ser futura.");
		}
		this.fechaAlquiler = fechaAlquiler;
	}

	private void setFechaDevolucion(LocalDate fechaDevolucion) {
		LocalDate hoy = LocalDate.now();

		if (fechaDevolucion == null) {
			throw new NullPointerException("ERROR: La fecha de devolución no puede ser nula.");
		}
		if (fechaDevolucion.isAfter(hoy)) {
			throw new IllegalArgumentException("ERROR: La fecha de devolución no puede ser futura.");
		}

		if (fechaDevolucion.isBefore(fechaAlquiler) || fechaDevolucion.isEqual(fechaAlquiler)) {
			throw new IllegalArgumentException(
					"ERROR: La fecha de devolución debe ser posterior a la fecha de alquiler.");
		}

		this.fechaDevolucion = fechaDevolucion;
	}

	private void setCliente(Cliente cliente) {
		if (cliente == null) {
			throw new NullPointerException("ERROR: El cliente no puede ser nulo.");
		}
		this.cliente = cliente;
	}

	private void setTurismo(Turismo turismo) {
		if (turismo == null) {
			throw new NullPointerException("ERROR: El turismo no puede ser nulo.");
		}
		this.turismo = turismo;
	}

	/***************************************************
	 * METODOS
	 ***************************************************/

	public void devolver(LocalDate fechaDevolucion) throws OperationNotSupportedException {

		// primero comprobamos que no hay ninguna fecha registrada en el atributo
		// fechadevolucion
		// por eso usamos "this"

		if (this.fechaDevolucion != null) {
			throw new OperationNotSupportedException("ERROR: La devolución ya estaba registrada.");
		}
		setFechaDevolucion(fechaDevolucion);
	}

	public int getPrecio() {

		int factorCilindrada = turismo.getCilindrada() / 10;
		int numeroDias;
		int precio = 0;

		// para calcular el precio, la fecha de devolucion debe ser registrada ya.
		// sino el precio sea 0
		if (fechaDevolucion != null) {
			// no se puede calcular "numeroDias" si la fecha de devolucion sea nula.
			numeroDias = (int) ChronoUnit.DAYS.between(fechaAlquiler, fechaDevolucion);
			precio = (PRECIO_DIA + factorCilindrada) * numeroDias;
		}

		return precio;
	}

	/***************************************************
	 * hashCode, equals, toString
	 ***************************************************/

	@Override
	public int hashCode() {
		return Objects.hash(cliente, fechaAlquiler, turismo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alquiler other = (Alquiler) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(fechaAlquiler, other.fechaAlquiler)
				&& Objects.equals(turismo, other.turismo);
	}

	@Override
	public String toString() {
		if (fechaDevolucion == null) {
			return String.format("%s <---> %s, %s - %s (%d€)", cliente, turismo,
					fechaAlquiler.format(Alquiler.FORMATO_FECHA), "Aún no devuelto", getPrecio());
		} else {
			return String.format("%s <---> %s, %s - %s (%d€)", cliente, turismo,
					fechaAlquiler.format(Alquiler.FORMATO_FECHA), fechaDevolucion.format(Alquiler.FORMATO_FECHA),
					getPrecio());
		}

	}

}
