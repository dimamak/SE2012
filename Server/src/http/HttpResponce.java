package http;

import java.util.Hashtable;
import java.util.Map.Entry;

/**
 * HttpResponce extends {@link HttpMessage} with additional data needed 
 * by http requirements. 
 * @version 1.0
 */
public class HttpResponce extends HttpMessage{
	protected StatusLine _statusLine;
	
	public HttpResponce(){
		this._statusLine = new StatusLine();
	}

	public StatusLine get_statusLine() {
		return _statusLine;
	}

	public void set_statusLine(StatusLine _statusLine) {
		this._statusLine = _statusLine;
	}
	
	public void add_cookie(String key, String value){
		Hashtable<String, String> cookies = new Hashtable<String, String>();
		
		String cookieHeaderValue = get_headers().get("Set-Cookie");
		// If Cookie header found
		if(cookieHeaderValue != null){
			for(String cookie : cookieHeaderValue.split(";")){
				String[] c = cookie.split("=");
				cookies.put(c[0], c[1]);
			}
		}
		
		cookies.put(key, value);
		
		cookieHeaderValue = "";
		for(Entry<String, String> cookie: cookies.entrySet()){
			cookieHeaderValue += cookie.getKey() + "=" + cookie.getValue() + ";";
		}
		
		add_header("Set-Cookie", cookieHeaderValue);
	}
}
