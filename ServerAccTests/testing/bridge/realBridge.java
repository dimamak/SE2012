package bridge;
import java.io.IOException;
import java.util.LinkedList;

import org.jsoup.nodes.Document;

import serviceCode.JsoupWrapper;


public class realBridge  implements ServerBridgeInt{
	
	String _serverUrl = "http://127.0.0.1:9091/?action=";
	
//	public Document getServerResponse(){
//		return JsoupWrapper.get(_serverUrl.concat()
//	}
	
	
	public realBridge(){		
//		request("entry","");
	}
	
	public Document entry(){
		return request("entry","");
	}
	
	private Document request(String reqName, String optArgs){
		Document d = null;
		try {
			
			 d  = JsoupWrapper.get(_serverUrl+reqName+optArgs);
			 System.out.println(Boolean.toString(d==null)+" "+reqName);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		System.out.println(Boolean.toString(d==null)+" "+reqName);
		return d;
	}
	
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
//		try {
//			JsoupWrapper.get(this._serverUrl.concat("login&username="+uName+"&password="+password));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
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
	public Object viewForum() {
//		Document d = null;
//		try {
//			 d = JsoupWrapper.get(_serverUrl.concat("view-list-of-subforums"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}		
//		return d;
		return request("view-list-of-subforums","");
	}

	@Override
	public Object viewSubForum(int subForumId) {
		// TODO Auto-generated method stub
		return null;
	}

}
