package modelo.persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.entidad.Coche;

public class DaoCoche {
	
	private static final String NOMBRE_FICHERO = "coches.dat";
	private int id;

	/**
	 * Metodo que lee de un fichero "coches.dat" todos los coches y los 
	 * devuelve
	 * @return la lista de coches. Vacia en caso de que el fichero este vacia
	 * @throws Exception en caso de que el fichero no exista o algun error de I/O
	 */
	public List<Coche> listar() throws Exception{
		List<Coche> listaCoches = new ArrayList<Coche>();
		try(FileInputStream fis = new FileInputStream(NOMBRE_FICHERO);
				ObjectInputStream ois = new ObjectInputStream(fis)){
			int bytesInBuffer = fis.available();
			while(bytesInBuffer > 0) {
				Object o = ois.readObject();
				Coche c = (Coche)o;
				listaCoches.add(c);
				bytesInBuffer = fis.available();
			}
		}catch(Exception e) {
			throw e;
		}
		return listaCoches;
	}
	
	/**
	 * Metodo que persiste un coche en el fichero. Cada vez que persistamos
	 * un nuevo coche, se borrara el fichero y se creara de nuevo con todos
	 * los anteriores coches más el pasado por parametro
	 * @param c el coche a persistir
	 * @return true en caso de que se haya conseguido persistir, false en caso
	 * contario (incluso si hay algun problema con el fichero)
	 */
	public boolean persistir (Coche c){
		try(FileOutputStream fos = new FileOutputStream(NOMBRE_FICHERO);
				ObjectOutputStream oos = new ObjectOutputStream(fos)){
			List<Coche> lista = listar();
			c.setId(++id);
			lista.add(c);
			for(Coche coche : lista) {
				oos.writeObject(coche);
			}
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public Coche buscarPorId(int id) throws Exception {
		List<Coche> lista = listar();
		for(Coche c : lista) {
			if(c.getId() == id) {
				return c;
			}
		}
		return null;
	}
	
	public boolean borrarPorId(int id) throws Exception {
		Coche c = buscarPorId(id);
		List<Coche> lista = listar();
		//podemos usar el método remove() de la listas
		//pero en objetos debemos de sobreescibir su método
		//equals ya que remove usa el equals() para saber
		//si ese objeto esta en la lista
		boolean borrado = lista.remove(c);
		if(borrado) {
			try(FileOutputStream fos = new FileOutputStream(NOMBRE_FICHERO);
					ObjectOutputStream oos = new ObjectOutputStream(fos)){
				for(Coche coche : lista) {
					oos.writeObject(coche);
				}
				return true;
			}catch(Exception e) {
				return false;
			}
		}else {
			return false;
		}
	}
	
}








