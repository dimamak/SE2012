package http;

import java.util.Map.Entry;

/**
 * HttpRequest extends {@link HttpMessage} with additional data needed 
 * by http requirements. 
 * @version 1.0
 */
public class HttpRequest extends HttpMessage{
	protected RequestLine _initLine;

	public RequestLine get_initLine() {
		return _initLine;
	}

	public void set_initLine(RequestLine _initLine) {
		this._initLine = _initLine;
	}
	
	public String toString(){
		String ans = "";
		
		ans += this._initLine;
		
		for(Entry<String, String> e : this._headers.entrySet()){
			ans += "\n" + e.getKey() + ": " + e.getValue();
		}
		
		if(this._body != null)
			ans += "\n" + this._body.toString();
		
		return ans;
	}
}
