package http;

public class HttpVersion {
	protected Integer _versionMajor;
	protected Integer _versionMinor;
	
	/**
	 * HttpVersion constructor
	 */
	public HttpVersion(){
		this._versionMajor = 1;
		this._versionMinor = 1;
	}
	
	/**
	 * HttpVersion parser
	 * @throws HttpException 
	 */
	public HttpVersion(String line) throws HttpException{
		String[] parts;
		
		// Ensure version correctness
		if (line.indexOf("HTTP/") == 0 && line.indexOf('.') > 5) {
			parts = line.substring(5).split("\\.");
			try {
				this._versionMajor = Integer.parseInt(parts[0]);
				this._versionMinor = Integer.parseInt(parts[1]);
			} catch (NumberFormatException nfe) {
				throw new HttpException(400, "Problem while parsing http version.", line);
			}
		} else
			throw new HttpException(400, "Problem while parsing http version.", line);
	}
	
	/**
	 * Get major version of http message
	 * @return major version of http message
	 */
	public Integer get_versionMajor() {
		return _versionMajor;
	}
	
	/**
	 * Set major version of http message
	 * @return major version of http message
	 */
	public void set_versionMajor(Integer _versionMajor) {
		this._versionMajor = _versionMajor;
	}
	
	/**
	 * Get minor version of http message
	 * @return major version of http message
	 */
	public Integer get_versionMinor() {
		return _versionMinor;
	}
	
	/**
	 * Set minor version of http message
	 * @return major version of http message
	 */
	public void set_versionMinor(Integer _versionMinor) {
		this._versionMinor = _versionMinor;
	}
	
	@Override
	public String toString() {
		return "HTTP/" + _versionMajor + "." + _versionMinor;
	}
	
	
}
