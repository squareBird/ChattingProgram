package Main;
import Server.*;
import Client.*;
import Gui.*;

public class Main {

	public static void main(String[] args) {
		
		Server dbServer = new Server();
		try {
			dbServer.DBConnect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		new InitialScreen(dbServer);
		
		
	}

}
