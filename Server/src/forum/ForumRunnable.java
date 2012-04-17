package forum;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

import structs.Forum;
import structs.ForumObject;
import structs.User;

/**
 * Forum runnable
 * @author Andrey
 * @version 1.1
 */
public class ForumRunnable implements Runnable{
	protected ServerSocket _sockFactory;
	 
	protected Hashtable<UUID,Session> _sessions;
	protected Hashtable<Integer,ForumObject> _fobjects;
	protected List<User> _users;
	
	public ForumRunnable(int port) throws IOException{
		System.out.println("Creating forum listener on port " + port);
		this._sockFactory = new ServerSocket();
		this._sockFactory.bind(new InetSocketAddress(port));
		
		
		this._sessions = new Hashtable<UUID, Session>();
		this._fobjects = new Hashtable<Integer,ForumObject>();
		this._users = new ArrayList<User>();
		
		Forum f = new Forum();
		this._fobjects.put(f.get_id(), f);
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
	    User ans=null;
	    for(User single : this._users)
		if(single.get_username().compareTo(username)==0)
		    ans=single;
	    return ans;
	}
	
	public void add_user(User u){
		this._users.add(u);
	}
	
	public User get_user_byemail(String email){
	    User ans=null;
	    for(User single : this._users)
		if(single.get_email().compareTo(email)==0)
		    ans=single;
	    return ans;
	}

	public Hashtable<Integer, ForumObject> get_fobjects() {
		return _fobjects;
	}

	public Forum get_forum(){
		return (Forum) this._fobjects.get(0);
	}
	
	public void add_fobject(ForumObject obj, ForumObject parent){
		this._fobjects.put(obj.get_id(), obj);
		parent.get_children().add(obj);
		obj.set_parent(parent);
	}
	
	public void add_fobject(ForumObject obj, Integer parentId){
		add_fobject(obj, this._fobjects.get(parentId));
	}
	
	public void delete_fobject(ForumObject obj){
		// Delete children
		for(ForumObject child : obj.get_children())
			delete_fobject(child);
		
		// Delete parent link
		obj.get_parent().get_children().remove(obj);
		
		// Delete object itself
		this._fobjects.remove(obj.get_id());
	}
	
	public void delete_fobject(Integer objId){
		ForumObject obj = this._fobjects.get(objId);
		delete_fobject(obj);
	}
	
}
