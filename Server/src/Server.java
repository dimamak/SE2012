import java.io.IOException;

/**
 * Server class represents entry point to run program
 * @author Andrey
 * @version 1.0
 */
public class Server {
	/**
	 * Main method
	 * @param args List of arguments passed from command line
	 */
	public static void main(String[] args) {
		// Set default port
		int port = 1234;
		
		// Consider arguments
		if (args.length == 1) {
			port = Integer.parseInt(args[0]);
		}
		else if (args.length > 1) {
			System.out.println("Usage: java  -jar  Server.jar [listener port]");
			return;
		}
		
		
		Forum f = null;
		try {
			f = new Forum(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		f.run();
	}

}
