package structs;

import java.util.ArrayList;
import java.util.List;

public class Forum extends ForumObject{
	protected User _admin;
	
	public List<SubForum> get_subforums(){
		List<SubForum> lst = new ArrayList<SubForum>();
		
		for(ForumObject fo : this.get_children()){
			lst.add((SubForum) fo);
		}
				
		return lst;
		//TODO TEMPORARY IMPLEMENTATION
		//TODO HOW TO CAST LIST?????????????
	}
}
