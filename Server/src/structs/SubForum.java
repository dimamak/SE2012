package structs;

import java.util.Hashtable;
import java.util.List;

public class SubForum {
	protected String _title;
	protected Hashtable<User, Integer> _moderators; // Integer - num of ban requests
	protected List<Post> _posts;

	public SubForum() {
		this._moderators = new Hashtable<User, Integer>();
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

	public List<Post> get_posts() {
		return _posts;
	}

	public void set_posts(List<Post> _posts) {
		this._posts = _posts;
	}
}
