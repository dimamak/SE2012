package structs;

import java.util.Hashtable;

public class SubForum extends ForumObject{
	protected String _title;
	protected Hashtable<User, Integer> _moderators; // Integer - num of ban requests

	public SubForum(String title) {
		this._moderators = new Hashtable<User, Integer>();
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

	public void set_moderators(Hashtable<User, Integer> _moderators) {
		this._moderators = _moderators;
	}

}
