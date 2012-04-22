package bridge;
import java.util.LinkedList;


public class realBridge implements ServerBridgeInt {
	
	
	@Override
	public boolean addFriend(String uName, String friendUname) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addSubForum(String title) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean banUser(String uName, int banDuration) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editThread(int subForumId, int MessageId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean login(String uName, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean logout() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean publishNewThread(int subForumId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean publishReplyToThread(int subForumId, int parentMsgId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean register(String userName, String password, String fname,
			String sname, String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LinkedList<?> search(String uName, String content) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setAdmin(String uName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setSubForumModerator(String uName, int subForumId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean suspendSubForumModerator(String uName, int subForumId,
			int suspDuration) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object viewDiscussion(int discussionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<?> viewForum() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<?> viewSubForum(int subForumId) {
		// TODO Auto-generated method stub
		return null;
	}

}
