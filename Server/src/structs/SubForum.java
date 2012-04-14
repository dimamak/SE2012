package structs;

import java.util.ArrayList;
import java.util.List;

public class SubForum {
	protected List<User> _moderators;
	
	public SubForum(){
		this._moderators = new ArrayList<User>();
	}
}
