package forum;

import java.util.UUID;

import structs.User;

public class Session {
	protected UUID _id; 
	protected User _user;
	protected Long _expires;
	
	public Session(){
		this._id = UUID.randomUUID();
	}
	
	public UUID get_id() {
		return _id;
	}
	public void set_id(UUID _id) {
		this._id = _id;
	}
	public User get_user() {
		return _user;
	}
	public void set_user(User _user) {
		this._user = _user;
	}
	public Long get_expires() {
		return _expires;
	}
	public void set_expires(Long _expires) {
		this._expires = _expires;
	}
	
	
}
