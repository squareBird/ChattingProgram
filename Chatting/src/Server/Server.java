package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Server implements ServerInterface {

	Connection conn;
	Statement stmt;
	
	public void DBConnect() {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/project?characterEncoding=UTF-8&serverTimezone=UTC", "fails12", "sphere12#");
			stmt = conn.createStatement();
			System.out.println("Success!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void signIn() {
	
	}

	@Override
	public void signUp() {

	}

}
