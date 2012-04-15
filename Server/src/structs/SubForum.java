package structs;

import java.util.Hashtable;
import java.util.List;

public class SubForum {
    	protected String _title;
	protected Hashtable<User, Integer> _moderators; // Integer - num of ban requests
	protected List<Post> _posts;
	
	public SubForum(){
		this._moderators = new Hashtable<User, Integer>();
	}
}
