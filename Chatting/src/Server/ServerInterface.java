package Server;

import java.sql.SQLException;

interface ServerInterface {

	void signIn();
	int signUp(String id, String pwd, String name, int Age,  String phoneNum)  throws SQLException;	
}
