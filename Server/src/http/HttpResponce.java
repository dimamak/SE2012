package http;

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
	
	
	
}
