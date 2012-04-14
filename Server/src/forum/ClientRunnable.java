package forum;


import http.HttpMessageParser;
import http.HttpMessageSender;
import http.HttpRequest;
import http.HttpException;
import http.HttpResponce;
import java.io.IOException;
import java.net.Socket;
import java.util.Hashtable;

/**
 * This class implements {@link Server} life cycle against Client
 * @version 1.0
 */
public class ClientRunnable implements Runnable {
	protected Socket _socket;
	protected Forum _server;
	protected Hashtable<String, String> _params;

	/**
	 * Class constructor.
	 * @param sock {@link Socket} instance
	 * @param server {@link Server} instance
	 */
	public ClientRunnable(Socket sock, Forum server) {
		this._socket = sock;
		this._server = server;
	}

	@Override
	public void run() {
		try {
			HttpRequest inPkt = null;
			HttpResponce outPkt = new HttpResponce();
			
			try {
				// Try to parse incoming request
				inPkt = HttpMessageParser.parseRequest(this._socket.getInputStream(), "UTF-8");
				while(inPkt == null)
					inPkt = HttpMessageParser.parseRequest(this._socket.getInputStream(), "UTF-8");
				
				this._params=HttpMessageParser.parseRequestParams(inPkt);
				//throw new HttpException(400,"No Host header found in incoming message.");

				


			} catch (HttpException e) {
				// Create responce packet


			}

			// Send packet
			HttpMessageSender.send(this._socket.getOutputStream(), "UTF-8", outPkt);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
		    this._socket.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}

}
