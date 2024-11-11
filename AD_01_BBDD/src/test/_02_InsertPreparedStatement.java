package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class _02_InsertPreparedStatement {

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
					+ "(?,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, "DaniGay87");
			ps.setInt(2, 30);
			ps.setDouble(3, 100);
			
			int rows = ps.executeUpdate();
			if(rows !=0) {
				System.out.println("Se ha dado de alta");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
