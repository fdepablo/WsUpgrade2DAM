package modelo.negocio;

import java.util.List;

import modelo.entidad.Coche;
import modelo.persistencia.DaoCoche;

public class GestorCoche {

	private DaoCoche dc;
	
	public GestorCoche() {
		dc = new DaoCoche();
	}
	
	public List<Coche> listar(){
		try {
			return dc.listar();
		} catch (Exception e) {
			return null;
		}
	}
	
	public Coche getById(int id) {
		try {
			return dc.buscarPorId(id);
		} catch (Exception e) {
			return null;
		}
	}
	
	public boolean borrarById(int id) {
		try {
			return dc.borrarPorId(id);
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Metodo que persiste un coche
	 * @param coche
	 * @return 1 en caso de que la marca este vacio, 2 en caso de que el modelo este vacio
	 * 3 en caso de que la marca y el modelo este vacio, 4 en caso de que no se haya podido
	 * persistir en fichero, y 5 en caso de que se haya podido persitir.
	 */
	public int guardar(Coche coche) {
		
		if(coche.getMarca().isBlank() && coche.getModelo().isBlank()) {
			return 3;
		}
		
		if(coche.getMarca().isBlank()) {
			return 1;
		}
		
		if(coche.getModelo().isBlank()) {
			return 2;
		}
		
		boolean persistido = dc.persistir(coche);
		if(persistido) {
			return 4;
		}else {
			return 5;
		}
	}
	
}









