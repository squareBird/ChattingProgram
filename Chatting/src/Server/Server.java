package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
					"jdbc:mysql://localhost:3306/ChatDB?characterEncoding=UTF-8&serverTimezone=UTC", "root", "sphere12#");
			stmt = conn.createStatement();
			System.out.println("DB Connect Success!");

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
	public ResultSet signIn(String id, String pwd) throws SQLException {
		String query = "select id from account where id ='" + id + "' and pwd ='" + pwd +"';";
//		System.out.println("SendQuery : " + query);
		return sendExecuteQuery(query);
	}

	@Override
	public int signUp(String id, String pwd, String name, int age,  String phoneNum) throws SQLException {
		String query = "insert account values('" + id + "','" + pwd +"','" + name + "','" + age + "','" + phoneNum +"');";
		return sendUpdateQuery(query);
	}

	public ResultSet sendExecuteQuery(String query) throws SQLException {
		return stmt.executeQuery(query);
	}

	public int sendUpdateQuery(String query) throws SQLException {
		return stmt.executeUpdate(query);
	}

}
