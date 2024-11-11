package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class _01_Insert {

	public static void main(String[] args) {
		
		//protocolo:subprotocolo://IP:PUERTO/ESQUEMA
		String url = "jdbc:mysql://localhost:3306/bbdd";
		String user = "root";
		String pass = "";
		
		System.out.println("Probando conexión a BBDD");
		try (Connection conn = DriverManager.getConnection(url, user, pass);){
			System.out.println("La conexión ha sido establecida");
			
			double peso = 97;
			String query = "INSERT INTO personas (NOMBRE,EDAD,PESO) VALUES "
					+ "('Antoñanzas',123,"  + peso + ")";
			
			Statement stmt = conn.createStatement();
			int rows = stmt.executeUpdate(query);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
