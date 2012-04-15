package forum;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

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
	protected Hashtable<UUID,Session> _sessions;
	protected List<User> _users;
	
	public Forum(int port) throws IOException{
		System.out.println("Creating forum listener on port " + port);
		this._sockFactory = new ServerSocket();
		this._sockFactory.bind(new InetSocketAddress(port));
		
		
		this._sforums = new ArrayList<SubForum>();
		this._sessions = new Hashtable<UUID, Session>();
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

	public User get_admin() {
		return _admin;
	}

	public void set_admin(User _admin) {
		this._admin = _admin;
	}

	public List<SubForum> get_sforums() {
		return _sforums;
	}

	public void set_sforums(List<SubForum> _sforums) {
		this._sforums = _sforums;
	}

	public Hashtable<UUID, Session> get_sessions() {
		return _sessions;
	}

	public Session get_session(UUID id) {
		return _sessions.get(id);
	}

	public void add_session(UUID id, Session s) {
		this._sessions.put(id, s);
	}

	public User get_user(String username){
	    return null;
	}
}
