package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class _06_Delete {

	public static void main(String[] args) {
		//protocolo:subprotocolo://IP:PUERTO/ESQUEMA
		String url = "jdbc:mysql://localhost:3306/bbdd";
		String user = "root";
		String pass = "";
		
		try (Connection conn = DriverManager.getConnection(url, user, pass);){
			System.out.println("La conexión ha sido establecida");
			
			String query = "DELETE FROM personas WHERE ID=?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, 1);
			
			int filas = ps.executeUpdate();
			if(filas == 1) {
				System.out.println("Hemos borrado a id = 1");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
