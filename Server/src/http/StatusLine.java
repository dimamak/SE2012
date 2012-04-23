package http;

public class StatusLine {
	protected HttpVersion _version;
	protected Integer _statusCode;
	protected String _description;
	
	public StatusLine(){
		this._version = new HttpVersion();
		this._statusCode=200;
	}

	public HttpVersion get_version() {
		return _version;
	}

	public void set_version(HttpVersion _version) {
		this._version = _version;
	}

	public Integer get_statusCode() {
		return _statusCode;
	}

	public void set_statusCode(Integer _statusCode) {
		this._statusCode = _statusCode;
	}

	public String get_description() {
		return _description;
	}

	public void set_description(String _description) {
		this._description = _description;
	}
	
	public String toString(){
		return this._version + " " + this._statusCode + " " + this._description;
	}
}
