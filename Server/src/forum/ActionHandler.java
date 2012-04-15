package forum;

import java.util.Hashtable;

import http.HttpException;
import http.HttpRequest;
import http.HttpResponce;

public class ActionHandler {
	protected Hashtable<String, String> _arguments;
	protected Forum _forum;


	public HttpResponce processAction(Forum forum, HttpRequest inPkt) throws HttpException {
		HttpResponce ans = new HttpResponce();
		this._forum = forum;
		this._arguments = inPkt.get_arguments();
		String action = this._arguments.get("action");

		// If there are cookies, then site visited earlier, then there might be
		// session
		if (inPkt.get_cookies().containsKey("SESSID") && inPkt.get_headers().containsKey("action")) {
			try {
				getClass().getMethod(action).invoke(this);
			} catch (NoSuchMethodException e) {
				throw new HttpException(405,"Method Not Allowed.");
			} catch (Exception e) {
				if(e instanceof HttpException)
					throw (HttpException)e;
				else
					throw new HttpException(500,"Internal Server Error", e.getMessage());
			}
		} else {
			ans = entry();
		}

		return ans;

	}

	public HttpResponce login() {
		HttpResponce ans = new HttpResponce();
		System.out.println("login");
		return ans;
	}

	public HttpResponce entry() {
		HttpResponce ans = new HttpResponce();
		// Create new session
		Session s = new Session();
		this._forum.add_session(s);

		// Create response
		ans.get_statusLine().set_statusCode(200);
		ans.get_statusLine().set_description("OK");
		ans.add_cookie("SESSID", s.get_id().toString());
		return ans;
	}
	
	public HttpResponce register(){
		return null;
	}
	
	public HttpResponce logout(){
		return null;
	}
	
	public HttpResponce viewforum(){
		return null;
	}
	
	public HttpResponce viewmessage(){
		return null;
	}
	
	public HttpResponce home(){
		return null;
	}
	
	public HttpResponce publish(){
		return null;
	}
	
	public HttpResponce search(){
		return null;
	}
	
	public HttpResponce addfriend(){
		return null;
	}
	
	public HttpResponce ban(){
		return null;
	}
	
	public HttpResponce setmoderator(){
		return null;
	}
	
	public HttpResponce suspendmoder(){
		return null;
	}
	
	public HttpResponce setadmin(){
		return null;
	}
}
