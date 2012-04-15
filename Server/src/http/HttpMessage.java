package http;

import java.util.Hashtable;

/**
 * HttpMessage class is to provide basic parameters and functions for http
 * communication
 * 
 * @version 1.0
 */
public abstract class HttpMessage {
	protected Hashtable<String, String> _headers;
	protected byte[] _body;

	/**
	 * Class constructor
	 */
	public HttpMessage() {
		this._headers = new Hashtable<String, String>();
	}

	/**
	 * Get headers of http message
	 * 
	 * @return Header list of http message
	 */
	public Hashtable<String, String> get_headers() {
		return _headers;
	}

	/**
	 * Add new header to header list
	 * 
	 * @param key
	 *            Header key
	 * @param value
	 *            Header value
	 */
	public void add_header(String key, String value) {
		this._headers.put(key, value);
	}

	/**
	 * Get body of http message
	 * 
	 * @return Body of http message
	 */
	public byte[] get_body() {
		return _body;
	}

	/**
	 * Set body of http message
	 * 
	 * @param _body
	 *            of http message
	 */
	public void set_body(byte[] _body) {
		this._body = _body;
	}

	/**
	 * This function is to set body to be html page.
	 * 
	 * @param title
	 *            Title of html page
	 * @param body
	 *            Body of html page
	 */
	public void set_htmlbody(String title, String body) {
		String ans = "";
		ans += "<html  xmlns=\"http://www.w3.org/1999/xhtml\"  xml:lang=\"en\"  lang=\"en\">";
		ans += "<head>";
		ans += "<title>";
		ans += title;
		ans += "</title>";
		ans += "<body>";
		ans += body;
		ans += "</body>";
		ans += "</html>";
		this._body = ans.getBytes();
	}

}
