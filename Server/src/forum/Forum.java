package forum;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import structs.SubForum;
import structs.User;

/**
 * Forum runnable
 * @author Andrey
 * @version 1.1
 */
public class Forum implements Runnable{
	protected ServerSocket _sockFactory;
	protected User _admin;
	protected List<SubForum> _sforums;
	protected List<Session> _sessions;
	
	public Forum(int port) throws IOException{
		System.out.println("Creating forum listener on port " + port);
		this._sockFactory = new ServerSocket();
		this._sockFactory.bind(new InetSocketAddress(port));
		
		
		this._sforums = new ArrayList<SubForum>();
		this._sessions = new ArrayList<Session>();
	}
	
	@Override
	public void run() {
		System.out.println("Forum started on port " + this._sockFactory.getLocalPort());
		try {
		    while (true) {
		    	ClientRunnable clnt = new ClientRunnable(this._sockFactory.accept(), this);
		    	Thread t = new Thread(clnt);
		    	t.start();
		    }
		} catch (SocketException e) {
		    System.out.print("SocketFactory closed.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
