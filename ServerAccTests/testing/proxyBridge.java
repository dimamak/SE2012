import java.util.LinkedList;


public class proxyBridge implements serverBridge {
	
	serverBridge _realBridge = null;
	@Override
	public boolean addFriend(String uName, String friendUname) {
		 
		return false;
	}

	@Override
	public boolean addSubForum(String title) {
		 
		return false;
	}

	@Override
	public boolean banUser(String uName, int banDuration) {
		 
		return false;
	}

	@Override
	public boolean editThread(int subForumId, int MessageId) {
		 
		return false;
	}

	@Override
	public boolean login(String uName, String password) {
		 
		return false;
	}

	@Override
	public boolean logout() {
		 
		return false;
	}

	@Override
	public boolean publishNewThread(int subForumId) {
		 
		return false;
	}

	@Override
	public boolean publishReplyToThread(int subForumId, int parentMsgId) {
		 
		return false;
	}

	@Override
	public boolean register(String userName, String password, String fname,
			String sname, String email) {
		 
		return false;
	}

	@Override
	public LinkedList<?> search(String uName, String content) {
		 
		return null;
	}

	@Override
	public boolean setAdmin(String uName) {
		 
		return false;
	}

	@Override
	public boolean setSubForumModerator(String uName, int subForumId) {
		 
		return false;
	}

	@Override
	public boolean suspendSubForumModerator(String uName, int subForumId,
			int suspDuration) {
		 
		return false;
	}

	@Override
	public Object viewDiscussion(int discussionId) {
		 
		return null;
	}

	@Override
	public LinkedList<?> viewForum() {
		 
		return null;
	}

	@Override
	public LinkedList<?> viewSubForum(int subForumId) {
		 
		return null;
	}

	public serverBridge getRealBridge() {
		 
		return null;
	}

}
