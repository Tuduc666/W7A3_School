package w7a3_School_JDBC.DAO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class OracleConnection{
	public static Connection getConnection() throws ClassNotFoundException, IOException, SQLException {
		Connection conn = null;
		final Properties prop = new Properties();
		final InputStream inputStream = OracleConnection.class.getClassLoader()
			.getResourceAsStream("w7a3_School_JDBC/resources/db.properties");
		prop.load(inputStream);
		Class.forName(prop.getProperty("driver"));
		conn = DriverManager.getConnection(prop.getProperty("url"), 
				prop.getProperty("username"), prop.getProperty("password"));
		return conn;
	}
}
