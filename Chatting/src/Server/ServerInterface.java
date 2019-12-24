package Server;

import java.sql.ResultSet;
import java.sql.SQLException;

interface ServerInterface {

	ResultSet signIn(String id, String pwd) throws SQLException;
	int signUp(String id, String pwd, String name, int Age,  String phoneNum)  throws SQLException;	
}
