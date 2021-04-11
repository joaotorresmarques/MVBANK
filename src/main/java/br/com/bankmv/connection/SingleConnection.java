package br.com.bankmv.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	private static String url = "jdbc:oracle:thin:@localhost:1521/xe";
	private static String password = "123456";
	private static String user = "system";
	private static Connection connection = null;
	
	static{
		conectar();
	}
	
	public SingleConnection(){
		conectar();
	}
	
	private static void conectar(){
		try{
			if(connection ==null){
				Class.forName("oracle.jdbc.driver.OracleDriver");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
				System.out.println("beleza joão");
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection(){
		return connection;
	}
}
