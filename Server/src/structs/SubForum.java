package structs;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class SubForum {
	protected String _title;
	protected Hashtable<User, Integer> _moderators; // Integer - num of ban requests
	protected List<Message> _posts;

	public SubForum(String title) {
		this._moderators = new Hashtable<User, Integer>();
		this._posts = new ArrayList<Message>();
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

	public List<Message> get_posts() {
		return _posts;
	}

	public void set_posts(List<Message> _posts) {
		this._posts = _posts;
	}
}
