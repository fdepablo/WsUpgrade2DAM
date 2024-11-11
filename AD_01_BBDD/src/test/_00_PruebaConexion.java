package test;

import java.sql.Connection;
import java.sql.DriverManager;

public class _00_PruebaConexion {

	public static void main(String[] args) throws Exception{
		
		//protocolo:subprotocolo://IP:PUERTO/ESQUEMA
		String url = "jdbc:mysql://localhost:3306/bbdd";
		String user = "root";
		String pass = "";
		
		System.out.println("Probando conexión a BBDD");
		Connection conn = DriverManager.getConnection(url, user, pass);
		System.out.println("La conexión ha sido establecida");
		conn.close();
	}

}
