package http;

/**
 * HttpException class implements exception thrown on http communication problems,
 * like message parsing, connection problems, etc.
 * @version 1.0
 */
public class HttpException extends Exception{
	private static final long serialVersionUID = 1L;

	private int _errNumber;
	private String _errInfo = "";
	private String _errSourceString = "";
	
	/**
	 * HttpException constructor
	 * @param errNumber Http error number
	 */
	public HttpException(int errNumber){
		this._errNumber = errNumber;
	}
	
	/**
	 * HttpException constructor
	 * @param errNumber Http error number
	 * @param errInfo Http error information
	 */
	public HttpException(int errNumber, String errInfo){
		this._errNumber = errNumber;
		this._errInfo = errInfo;
	}
	
	/**
	 * HttpException constructor
	 * @param errNumber Http error number
	 * @param errInfo Http error information
	 * @param errSourceString Source of the problem
	 */
	public HttpException(int errNumber, String errInfo, String errSourceString){
		this._errNumber = errNumber;
		this._errInfo = errInfo;
		this._errSourceString = errSourceString;
	}
	
	/**
	 * Get number of http exception
	 * @return Http error code
	 */
	public int get_errNumber() {
		return _errNumber;
	}

	/**
	 * Get information about http exception
	 * @return Http error information
	 */
	public String get_errInfo() {
		return _errInfo;
	}

	/**
	 * Get source of http exception
	 * @return Source of the thrown exception
	 */
	public String get_errSourceString() {
		return _errSourceString;
	}

	public String toString(){
		String ans = "Error number: " + ((Integer)this._errNumber).toString();
		
		if(this._errInfo != "")
			ans += "\nDescription: " + this._errInfo;
		
		if(this._errSourceString != "")
			ans += "\nSource: [" + this._errSourceString + "]";
		
		return ans;
	}
}
