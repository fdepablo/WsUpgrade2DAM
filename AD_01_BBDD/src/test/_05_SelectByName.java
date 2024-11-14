package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class _05_SelectByName {

	public static void main(String[] args) {
		//protocolo:subprotocolo://IP:PUERTO/ESQUEMA
		String url = "jdbc:mysql://localhost:3306/bbdd";
		String user = "root";
		String pass = "";
		
		try (Connection conn = DriverManager.getConnection(url, user, pass);){
			System.out.println("La conexión ha sido establecida");
			
			String query = "SELECT * FROM personas WHERE nombre=?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, "Antoñanzas");
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(1);
				String nombre = rs.getString(2);
				int edad = rs.getInt(3);
				double peso = rs.getDouble(4);
				System.out.println("Registro: " + id + " " + nombre + " " + edad + " " + peso);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
