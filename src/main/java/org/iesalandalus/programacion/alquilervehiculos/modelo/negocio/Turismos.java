/*************************************************
	 Ismail Hilmi
	 1ºDAW
	 Tarea online 05
*************************************************/
package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;

public class Turismos {

	/*************************************************
	 * ATRIBUTOS Y CONSTANTES
	 *************************************************/
	
	private List<Turismo> coleccionTurismos;

	/**************************************************
	 * CONSTRUCTORES
	 **************************************************/
	
	public Turismos() {
		coleccionTurismos = new ArrayList<Turismo>();
	}

	/***************************************************
	 * METODOS
	 ***************************************************/
	
	public List<Turismo> get() {
		return new ArrayList<Turismo>(coleccionTurismos);
	}

	public int getCantidad() {
		return coleccionTurismos.size();
	}

	public void insertar(Turismo turismo) throws OperationNotSupportedException {
			if (turismo==null) {
				throw new NullPointerException("ERROR: No se puede insertar un turismo nulo.");
			}
			
			if (coleccionTurismos.contains(turismo)) {
				throw new OperationNotSupportedException("ERROR: Ya existe un turismo con esa matrícula.");
			}
			
			coleccionTurismos.add(turismo);
	}

	public void borrar(Turismo turismo) throws OperationNotSupportedException {
		if (turismo==null) {
			throw new NullPointerException("ERROR: No se puede borrar un turismo nulo.");
		}
		
		int indice = coleccionTurismos.indexOf(turismo);
		if (indice==-1) {
			throw new OperationNotSupportedException("ERROR: No existe ningún turismo con esa matrícula.");
		}
		coleccionTurismos.remove(indice);
		
	}

	public Turismo buscar(Turismo turismo) {
		
		if (turismo==null) {
			throw new NullPointerException("ERROR: No se puede buscar un turismo nulo.");
		}
		
		int indice = coleccionTurismos.indexOf(turismo);
		if (indice==-1) {
			return null;
		}
		return coleccionTurismos.get(indice);
		
	}

}
