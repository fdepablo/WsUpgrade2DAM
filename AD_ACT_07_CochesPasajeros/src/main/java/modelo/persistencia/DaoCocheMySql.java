package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.Configuracion;
import modelo.entidad.Coche;
import modelo.persistencia.interfaz.DaoCoche;

public class DaoCocheMySql implements DaoCoche{

	public static DaoCocheMySql instance = null; 
	
	private String url;
	private String user;
	private String pass;
	
	private DaoCocheMySql() {
		super();
		url = Configuracion.getInstance().getProperty("bbdd.url");
		user = Configuracion.getInstance().getProperty("bbdd.user");
		pass = Configuracion.getInstance().getProperty("bbdd.pass");
	}
	
	public static DaoCocheMySql getInstance() {
		if(instance == null) {
			instance = new DaoCocheMySql();
		}
		return instance;
	}
		
	@Override
	public Integer agregar(Coche coche) {
		try(Connection conn = DriverManager.getConnection(url,user,pass)){
			String query = "insert into coches values (?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, coche.getMarca());
			ps.setString(2, coche.getModelo());
			ps.setInt(3, coche.getAnio());
			ps.setDouble(4,coche.getKm());
			int nf = ps.executeUpdate();
			return nf;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public Integer borrar(int id) {
		try(Connection conn = DriverManager.getConnection(url,user,pass)){
			String query = "delete from coches where id=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			int nf = ps.executeUpdate();
			return nf;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public Integer modificar(int id, Coche coche) {
		try(Connection conn = DriverManager.getConnection(url,user,pass)){
			String query = "update coches set marca=?, modelo=?, anio=?, km=? where id=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, coche.getMarca());
			ps.setString(2, coche.getModelo());
			ps.setInt(3, coche.getAnio());
			ps.setDouble(4,coche.getKm());
			ps.setInt(5, id);
			int nf = ps.executeUpdate();
			return nf;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public Coche getById(int id) {
		try(Connection conn = DriverManager.getConnection(url,user,pass)){
			String query = "select marca,modelo,anio,km from coches where id=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);;
			ResultSet rs = ps.executeQuery();
			Coche coche = null;
			while(rs.next()) {
				coche = new Coche();
				coche.setId(id);
				coche.setMarca(rs.getString(1));
				coche.setModelo(rs.getString(2));
				coche.setAnio(rs.getInt(3));
				coche.setKm(rs.getDouble(4));
			}
			return coche;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public List<Coche> listar() {
		try(Connection conn = DriverManager.getConnection(url,user,pass)){
			String query = "select id,marca,modelo,anio,km from coches";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			Coche coche = null;
			List<Coche> listaCoches = new ArrayList<Coche>();
			while(rs.next()) {
				coche = new Coche();
				coche.setId(rs.getInt(1));
				coche.setMarca(rs.getString(2));
				coche.setModelo(rs.getString(3));
				coche.setAnio(rs.getInt(4));
				coche.setKm(rs.getDouble(5));
				listaCoches.add(coche);
			}
			return listaCoches;
		}catch(Exception e) {
			return null;
		}
	}

}
