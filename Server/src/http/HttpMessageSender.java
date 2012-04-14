package http;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map.Entry;

/**
 * HttpMessageSender class is to provide tools for sending {@link HttpMessage}
 * @version 1.0
 */
public class HttpMessageSender {
	protected static final String _seperator = "\r\n";

	/**
	 * Send {@link HttpResponce}
	 * @param p
	 * @throws IOException
	 */
	public static void send(OutputStream os, String charsetName, HttpResponce p) throws IOException{
		OutputStreamWriter osw = new OutputStreamWriter(os, charsetName);
		
		// Write status line
		osw.write(p.get_statusLine().toString());
		
		// Write blank line
		osw.write(_seperator);
				
		// Write headers
		for(Entry<String, String> entry : p.get_headers().entrySet()){
			osw.write(entry.getKey() + ":" + entry.getValue() + _seperator);
		}
		
		// Write blank line
		osw.write(_seperator);
		osw.flush();
		
		// Write body
		if(p.get_body() != null)
			os.write(p.get_body());
		
		osw.flush();
	}
}
