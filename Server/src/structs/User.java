package structs;

import java.util.Hashtable;

import forum.SecurityHandler;

public class User {
	protected String _fname;
	protected String _sname;
	protected String _username;
	protected String _password;
	protected String _email;
	protected Hashtable<User, FriendshipStatus> _friends;

	public User(String _fname, String _sname, String _username,
			String _password, String _email) {
		this._fname = _fname;
		this._sname = _sname;
		this._username = _username;
		this._password = SecurityHandler.generateMd5(_password);
		this._email = _email;
	}

	public void add_friend(User friend,FriendshipStatus status){	
		this._friends.put(friend, status);
	}
	
	public FriendshipStatus get_friendStatus(User friend){
		return this._friends.get(friend);
	}
	
	public String get_fname() {
		return _fname;
	}

	public void set_fname(String _fname) {
		this._fname = _fname;
	}

	public String get_sname() {
		return _sname;
	}

	public void set_sname(String _sname) {
		this._sname = _sname;
	}

	public String get_username() {
		return _username;
	}

	public void set_username(String _username) {
		this._username = _username;
	}

	public String get_password() {
		return _password;
	}

	public void set_password(String _password) {
		this._password = SecurityHandler.generateMd5(_password);
	}

	public String get_email() {
		return _email;
	}

	public void set_email(String _email) {
		this._email = _email;
	}
}
