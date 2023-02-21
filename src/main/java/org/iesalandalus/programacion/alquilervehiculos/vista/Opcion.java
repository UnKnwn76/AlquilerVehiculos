/*************************************************
	 Ismail Hilmi
	 1ºDAW
	 Tarea online 05
*************************************************/

package org.iesalandalus.programacion.alquilervehiculos.vista;

public enum Opcion {

	/*************************************************
	 * ATRIBUTOS Y CONSTANTES
	 *************************************************/
	
	SALIR("Salir."),

	INSERTAR_CLIENTE("Insertar un nuevo cliente."), INSERTAR_TURISMO("Insertar un nuevo turismo."),
	INSERTAR_ALQUILER("Insertar un nuevo alquiler."),

	BUSCAR_CLIENTE("Buscar un cliente."), BUSCAR_TURISMO("Buscar un turismo."), BUSCAR_ALQUILER("Buscar un alquiler."),

	MODIFICAR_CLIENTE("Modificar un cliente existente."),

	DEVOLVER_ALQUILER("Devolver un alquiler."),

	BORRAR_CLIENTE("Borrar un cliente."), BORRAR_TURISMO("Borrar un turismo."), BORRAR_ALQUILER("Borrar un alquiler."),

	LISTAR_CLIENTES("Listar todos los clientes."), LISTAR_TURISMOS("Listar todos los turismos."),
	LISTAR_ALQUILERES("Listar todos los alquileres."),
	LISTAR_ALQUILERES_CLIENTE("Listar los alquileres de un cliente."),
	LISTAR_ALQUILERES_TURISMO("Listar los alquileres de un turismo.");

	private String texto;

	/**************************************************
	 * CONSTRUCTORES
	 **************************************************/
	
	private Opcion(String texto) {
		this.texto = texto;
	}

	/***************************************************
	 * METODOS
	 ***************************************************/
	
	private static boolean esOrdinalValido(int ordinal) {
		return ordinal >= 0 && ordinal < Opcion.values().length;

	}

	public static Opcion get(int ordinal) {

		if (!esOrdinalValido(ordinal)) {
			throw new ArrayIndexOutOfBoundsException("ERROR: El cordinal no es correcto");
			//lanzamos la excepción "ArrayIndexOutOfBoundsException"
			//si el índice es negativo o por encima del tamaño del array
		}
		return values()[ordinal];
	}


	/***************************************************
	 * TOSTRING
	 ***************************************************/
	
	public String toString() {
		return String.format("%d : %s", ordinal(), texto);
	}
}