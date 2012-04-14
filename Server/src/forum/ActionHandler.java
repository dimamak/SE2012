package forum;

import java.io.IOException;
import java.util.Hashtable;

import http.HttpException;
import http.HttpMessageParser;
import http.HttpRequest;
import http.HttpResponce;

public class ActionHandler {
	
	public static HttpResponce processAction(Forum forum, HttpRequest inPkt) throws IOException, HttpException{
		HttpResponce ans = new HttpResponce();
		Hashtable<String, String> params = HttpMessageParser.parseRequestParams(inPkt);
		
		// If there are cookies, then site visited earlier, then there might be session
		if(inPkt.get_cookies().containsKey("SESSID")){
			
		}
		else{
			// Create new session
			Session s = new Session();
			forum.add_session(s);
			
			
		}
		
		
		return ans;
		
	}
}
