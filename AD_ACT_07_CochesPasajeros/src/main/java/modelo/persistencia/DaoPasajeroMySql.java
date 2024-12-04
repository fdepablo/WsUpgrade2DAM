package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.Configuracion;
import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.persistencia.interfaz.DaoCoche;
import modelo.persistencia.interfaz.DaoPasajero;

public class DaoPasajeroMySql implements DaoPasajero{

	public static DaoPasajeroMySql instance = null; 
	
	private String url;
	private String user;
	private String pass;
	
	private DaoPasajeroMySql() {
		super();
		url = Configuracion.getInstance().getProperty("bbdd.url");
		user = Configuracion.getInstance().getProperty("bbdd.user");
		pass = Configuracion.getInstance().getProperty("bbdd.pass");
	}
	
	public static DaoPasajeroMySql getInstance() {
		if(instance == null) {
			instance = new DaoPasajeroMySql();
		}
		return instance;
	}
		
	@Override
	public Integer agregar(Pasajero p) {
		try(Connection conn = DriverManager.getConnection(url,user,pass)){
			String query = "insert into pasajeros values (?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, p.getNombre());
			ps.setInt(2, p.getEdad());
			ps.setDouble(3, p.getPeso());
			int nf = ps.executeUpdate();
			return nf;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public Integer borrar(int id) {
		try(Connection conn = DriverManager.getConnection(url,user,pass)){
			String query = "delete from pasajeros where id=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			int nf = ps.executeUpdate();
			return nf;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public Integer modificar(int id, Pasajero pasajero) {
		try(Connection conn = DriverManager.getConnection(url,user,pass)){
			String query = "update pasajeros set nombre=?, edad=?, peso=? where id=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, pasajero.getNombre());
			ps.setInt(2, pasajero.getEdad());
			ps.setDouble(3, pasajero.getPeso());
			ps.setInt(4, id);
			int nf = ps.executeUpdate();
			return nf;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public Pasajero getById(int id) {
		try(Connection conn = DriverManager.getConnection(url,user,pass)){
			String query = "select nombre,edad,peso from pasajeros where id=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);;
			ResultSet rs = ps.executeQuery();
			Pasajero pasajero = null;
			while(rs.next()) {
				pasajero = new Pasajero();
				pasajero.setId(id);
				pasajero.setNombre(rs.getString(1));
				pasajero.setEdad(rs.getInt(2));
				pasajero.setPeso(rs.getDouble(3));
			}
			return pasajero;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public List<Pasajero> listar() {
		try(Connection conn = DriverManager.getConnection(url,user,pass)){
			String query = "select id,nombre,edad,peso from pasajeros";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			Pasajero pasajero = null;
			List<Pasajero> listadoPasajeros = new ArrayList<Pasajero>();
			while(rs.next()) {
				pasajero = new Pasajero();
				pasajero.setId(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getDouble(4));
				listadoPasajeros.add(pasajero);
			}
			return listadoPasajeros;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public Integer agregerPasajeroCoche(int idCoche,int idPasajero) {
		try(Connection conn = DriverManager.getConnection(url,user,pass)){
			String query = "update pasajeros set fk_coche=? where id=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, idCoche);
			ps.setInt(2, idPasajero);
			int nf = ps.executeUpdate();
			return nf;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public Integer borrarPasajeroCoche(int idPasajero) {
		try(Connection conn = DriverManager.getConnection(url,user,pass)){
			String query = "update pasajeros set fk_coche=null where id=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, idPasajero);
			int nf = ps.executeUpdate();
			return nf;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public List<Pasajero> listarPasajeroPorCoche(int idCoche) {
		try(Connection conn = DriverManager.getConnection(url,user,pass)){
			String query = "select id,nombre,edad,peso from pasajeros where fk_coche=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, idCoche);
			ResultSet rs = ps.executeQuery();
			Pasajero pasajero = null;
			List<Pasajero> listadoPasajeros = new ArrayList<Pasajero>();
			while(rs.next()) {
				pasajero = new Pasajero();
				pasajero.setId(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getDouble(4));
				listadoPasajeros.add(pasajero);
			}
			return listadoPasajeros;
		}catch(Exception e) {
			return null;
		}
	}
	
	

}
