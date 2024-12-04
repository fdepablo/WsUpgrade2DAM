package modelo.persistencia.interfaz;

import java.util.List;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;

public interface DaoPasajero {
	Integer agregar(Pasajero pasajero);
	Integer borrar(int id);
	/**
	 * Método qe modifica los valores de un coche pasado por id
	 * @param id el id del coche que queremos borrar
	 * @param coche todos los nuevos datos del coche (menos el id)
	 * @return 1 en caso de que se haya mofificado el coche, 0 en 
	 * caso de que no haya modificado y null si ha ocurrido una
	 * excepcion (casi que mejor, arrojanzo excepciones)
	 */
	Integer modificar(int id, Pasajero pasajero);
	Pasajero getById(int id);
	List<Pasajero> listar();
	Integer agregerPasajeroCoche(int idCoche, int idPasajero);
	Integer borrarPasajeroCoche(int idPasajero);
	List<Pasajero> listarPasajeroPorCoche(int coche);
}
