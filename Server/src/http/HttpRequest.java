package http;

import java.util.Hashtable;
import java.util.Map.Entry;

/**
 * HttpRequest extends {@link HttpMessage} with additional data needed by http
 * requirements.
 * 
 * @version 1.0
 */
public class HttpRequest extends HttpMessage {
	protected RequestLine _initLine;
	private Hashtable<String, String> _cookies = null;
	private Hashtable<String, String> _arguments = null;

	public RequestLine get_initLine() {
		return _initLine;
	}

	public void set_initLine(RequestLine _initLine) {
		this._initLine = _initLine;
	}

	public String toString() {
		String ans = "";

		ans += this._initLine;

		for (Entry<String, String> e : this._headers.entrySet()) {
			ans += "\n" + e.getKey() + ": " + e.getValue();
		}

		if (this._body != null)
			ans += "\n" + this._body.toString();

		return ans;
	}

	public Hashtable<String, String> get_cookies() {
		// Init _cookies field
		if(this._cookies == null){
			this._cookies = new Hashtable<String, String>();
	
			String cookies = get_headers().get("cookie");
			// If Cookie header found
			if (cookies != null) {
				for (String cookie : cookies.split(";")) {
					String[] c = cookie.split("=");
					this._cookies.put(c[0], c[1]);
				}
			}
		}

		return this._cookies;
	}

	/**
	 * Try to parse {@link HttpMessage} parametrs
	 * 
	 * @return {@link HttpRequest} parsed parametrs
	 */
	public Hashtable<String, String> get_arguments(){
		// Init _arguments field
		if(this._arguments == null){
			this._arguments = new Hashtable<String, String>();
			String uri = this.get_initLine()._uri;
			String toparse = "";
			String[] split_equal;
	
			// Add body arguments
			if (this.get_body() != null)
				toparse = new String(this.get_body());
	
			// Add uri arguments
			if (uri.length() > 2 && uri.indexOf('?') > 0)
				toparse = uri.substring(uri.indexOf('?') + 1) + "&" + toparse;
	
			if (toparse.length() > 0) {
				for (String couple : toparse.split("&")) {
					split_equal = couple.split("=");
	
					/* if empty param */
					if (split_equal.length == 1)
						this._arguments.put(split_equal[0], null);
					else
						this._arguments.put(split_equal[0], split_equal[1]);
				}
			}
		}
		
		return this._arguments;
	}
}
