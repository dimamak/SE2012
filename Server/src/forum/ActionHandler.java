package forum;

import java.lang.reflect.Method;

import http.HttpException;
import http.HttpRequest;
import http.HttpResponce;

public class ActionHandler {

	public static HttpResponce processAction(Forum forum, HttpRequest inPkt) throws HttpException {
		HttpResponce ans = new HttpResponce();

		// If there are cookies, then site visited earlier, 
		// then there might be session
		if (inPkt.get_cookies().containsKey("SESSID") && inPkt.get_arguments().containsKey("action")) {
			try {
				String action = inPkt.get_arguments().get("action");
				Method mtd = ActionHandler.class.getMethod(action, Forum.class, HttpRequest.class);
				ans = (HttpResponce) mtd.invoke(null, forum, inPkt);
			} catch (NoSuchMethodException e) {
				throw new HttpException(405,"Method Not Allowed.");
			} catch (Exception e) {
				if(e instanceof HttpException)
					throw (HttpException)e;
				else
					throw new HttpException(500,"Internal Server Error", e.getMessage());
			}
		} else {
			ans = entry(forum, inPkt);
		}

		return ans;

	}

	public static HttpResponce login(Forum forum, HttpRequest inPkt) {
		HttpResponce ans = new HttpResponce();
		System.out.println("login");
		return ans;
	}

	public static HttpResponce entry(Forum forum, HttpRequest inPkt) {
		HttpResponce ans = new HttpResponce();
		// Create new session
		Session s = new Session();
		forum.add_session(s);

		// Create response
		ans.get_statusLine().set_statusCode(200);
		ans.get_statusLine().set_description("OK");
		ans.add_cookie("SESSID", s.get_id().toString());
		return ans;
	}
	
	public static HttpResponce register(Forum forum, HttpRequest inPkt){
		return null;
	}
	
	public static HttpResponce logout(Forum forum, HttpRequest inPkt){
		return null;
	}
	
	public static HttpResponce viewforum(Forum forum, HttpRequest inPkt){
		return null;
	}
	
	public static HttpResponce viewmessage(Forum forum, HttpRequest inPkt){
		return null;
	}
	
	public static HttpResponce home(Forum forum, HttpRequest inPkt){
		return null;
	}
	
	public static HttpResponce publish(Forum forum, HttpRequest inPkt){
		return null;
	}
	
	public static HttpResponce search(Forum forum, HttpRequest inPkt){
		return null;
	}
	
	public static HttpResponce addfriend(Forum forum, HttpRequest inPkt){
		return null;
	}
	
	public static HttpResponce ban(Forum forum, HttpRequest inPkt){
		return null;
	}
	
	public static HttpResponce setmoderator(Forum forum, HttpRequest inPkt){
		return null;
	}
	
	public static HttpResponce suspendmoder(Forum forum, HttpRequest inPkt){
		return null;
	}
	
	public static HttpResponce setadmin(Forum forum, HttpRequest inPkt){
		return null;
	}
}
