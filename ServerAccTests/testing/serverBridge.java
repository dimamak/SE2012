import java.util.LinkedList;


public interface serverBridge {
	boolean register(String userName,String password ,String fname,String sname,String email);
	
	boolean login(String uName, String password);
	
	boolean logout(); // addUname?
	
	LinkedList<?> viewForum(); // addUname?
	
	LinkedList<?> viewSubForum(int subForumId); // 
	
	Object viewDiscussion(int discussionId);
	
	boolean addSubForum(String title); 
	
	boolean publishNewThread(int subForumId);
	
	boolean publishReplyToThread(int subForumId, int parentMsgId);
	
	boolean editThread(int subForumId, int MessageId);
	
	LinkedList<?> search(String uName, String content);
	
	boolean addFriend(String uName, String friendUname);
	
	boolean banUser(String uName, int banDuration);
	
	boolean setSubForumModerator(String uName, int subForumId);
	
	boolean suspendSubForumModerator(String uName, int subForumId, int suspDuration);
	
	boolean setAdmin(String uName); 
	
}
