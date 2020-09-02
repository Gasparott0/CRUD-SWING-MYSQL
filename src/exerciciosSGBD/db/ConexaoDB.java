package exerciciosSGBD.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {

	public static Connection getConnection() {
		try {
			String url = "jdbc:mysql://localhost/exerciciosSGBD?useSSL=true&verifyServerCertificate=false";
			String user = "root";
			String password = "root";
			
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void closeConnection(Connection con) {
		try {
			if(con != null) con.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
