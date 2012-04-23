package structs;

import java.util.Hashtable;

public class SubForum extends ForumObject{
	protected String _title;
	protected Hashtable<User, Integer> _moderators; // Integer - num of ban requests
	protected Hashtable<User,Integer> _suspendedModers; // Integer - till time
	
	public SubForum(String title) {
		this._moderators = new Hashtable<User, Integer>();
		this._suspendedModers = new Hashtable<User, Integer>();
		this._title = title;
	}

	public String get_title() {
		return _title;
	}

	public void set_title(String _title) {
		this._title = _title;
	}

	public Hashtable<User, Integer> get_moderators() {
		return _moderators;
	}

	public void add_moderator(User moderator) {
		this._moderators.put(moderator, 0);
	}

	public void suspend_moderator(User moderator,Integer till){
		this._moderators.remove(moderator);
		if(till!=null && till!=0)
			this._suspendedModers.put(moderator,till);
	}
	
	public void unsuspend_moderator(User moderator){
		this._moderators.put(moderator,0);
		this._suspendedModers.remove(moderator);
	}
	
	public void del_moderator(User moderator) {
		this._moderators.remove(moderator);
	}

}
