package Main;
import Server.*;
import Client.*;

public class Main {

	public static void main(String[] args) {
		
		Server dbServer = new Server();
		
		try {
			
			dbServer.DBConnect();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

}
