package http;

public class RequestLine {
	protected String _method;
	protected String _uri;
	protected HttpVersion _version;
	
	public RequestLine(){
		this._version = new HttpVersion();
	}
	
	
	/**
	 * RequestLine parser
	 * @throws HttpException 
	 */
	public RequestLine(String initLine) throws HttpException{
		String cmd[];
		
		if (Character.isWhitespace(initLine.charAt(0)))
			throw new HttpException(400, "Initial line string starts with whitespace.", initLine);

		cmd = initLine.split("\\s");
		if (cmd.length != 3) {
			throw new HttpException(400, "Initial line parts count is not 3.", initLine);
		}

		// Ensure method correctness
		if (cmd[0].equals("GET") || cmd[0].equals("PUT") || cmd[0].equals("POST"))
			this._method = cmd[0];
		else
			throw new HttpException(501, "Requested method not supported.", cmd[0]);

		// Ensure identifier correctness
		this._uri = cmd[1];
		
		// Ensure version correctness
		this._version = new HttpVersion(cmd[2]);
	}
	
	public String get_method() {
		return _method;
	}
	public void set_method(String _method) {
		this._method = _method;
	}
	public String get_requestURI() {
		return _uri;
	}
	public void set_requestURI(String _requestURI) {
		this._uri = _requestURI;
	}
	public HttpVersion get_version() {
		return _version;
	}
	public void set_version(HttpVersion _version) {
		this._version = _version;
	}
	
	public String toString(){
		return this._method + " " + this._uri + " " + this._version;
	}
}
