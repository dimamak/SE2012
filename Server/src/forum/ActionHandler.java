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
		Hashtable<String, String> _params = HttpMessageParser.parseRequestParams(inPkt);
		
		
		
		
		return ans;
		
	}
}
