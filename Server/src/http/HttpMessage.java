package http;
import java.util.Hashtable;
import java.util.Map.Entry;

/**
 * HttpMessage class is to provide basic parameters and functions for http communication
 * @version 1.0
 */
public abstract class HttpMessage {
	protected Hashtable<String, String> _headers;
	protected byte[] _body;
	
	/**
	 * Class constructor
	 */
	public HttpMessage(){
		this._headers = new Hashtable<String, String>();
	}
	
	/**
	 * Get headers of http message
	 * @return Header list of http message
	 */	
	public Hashtable<String, String> get_headers() {
		return _headers;
	}

	/**
	 * Add new header to header list
	 * @param key Header key
	 * @param value Header value
	 */
	public void add_header(String key, String value) {
		this._headers.put(key, value);
	}
	
	/**
	 * Get body of http message
	 * @return Body of http message
	 */
	public byte[] get_body() {
		return _body;
	}
	
	/**
	 * Set body of http message
	 * @param _body of http message
	 */
	public void set_body(byte[] _body) {
		this._body = _body;
	}
	
	
	public Hashtable<String, String> get_cookies(){
		Hashtable<String, String> ans = new Hashtable<String, String>();
		
		String cookies = get_headers().get("Cookie");
		// If Cookie header found
		if(cookies != null){
			for(String cookie : cookies.split(";")){
				String[] c = cookie.split("=");
				ans.put(c[0], c[1]);
			}
		}
		
		return ans;
	}
	
	public void add_cookie(String key, String value){
		Hashtable<String, String> cookies = get_cookies();
		cookies.put(key, value);
		
		String cookieHeaderValue = "";
		for(Entry<String, String> cookie: cookies.entrySet()){
			cookieHeaderValue += cookie.getKey() + "=" + cookie.getValue() + ";";
		}
		
		add_header("Cookie", cookieHeaderValue);
	}
}
