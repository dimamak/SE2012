package forum;


import http.HttpRequest;
import http.HttpException;
import http.HttpResponse;
import java.io.IOException;
import java.net.Socket;

/**
 * This class implements {@link Server} life cycle against Client
 * @version 1.0
 */
public class ClientRunnable implements Runnable {
	protected Socket _socket;
	protected ForumRunnable _server;

	/**
	 * Class constructor.
	 * @param sock {@link Socket} instance
	 * @param server {@link Server} instance
	 */
	public ClientRunnable(Socket sock, ForumRunnable server) {
		this._socket = sock;
		this._server = server;
	}

	@Override
	public void run() {
		try {
			HttpRequest inPkt = null;
			HttpResponse outPkt = null;
			try {
				// Try to parse incoming request
				inPkt = CommunicationHandler.receive(this._socket.getInputStream(), "UTF-8");
				while(inPkt == null)
					inPkt = CommunicationHandler.receive(this._socket.getInputStream(), "UTF-8");

				outPkt = ActionHandler.processAction(this._server, inPkt);

			} catch (HttpException e) {
				// Create error responce packet
				outPkt = new HttpResponse();

				outPkt.get_statusLine().set_statusCode(e.get_errNumber());

				String descr = "";
				if(e.get_errInfo() != ""){
					descr += e.get_errInfo();
					if(e.get_errSourceString() != "")
						descr += " - " + e.get_errSourceString();
				}
				outPkt.get_statusLine().set_description(descr);

				String title = "Error " + e.get_errNumber();
				String body = "Error number: " + e.get_errNumber() + "<br>";
				if(e.get_errInfo() != ""){
					body += "Description: " + e.get_errInfo() + "<br>";
					if(e.get_errSourceString() != "")
						body += e.get_errSourceString();
				}
				outPkt.set_htmlbody(title, body);

			}

			// Send packet
			CommunicationHandler.send(this._socket.getOutputStream(), "UTF-8", outPkt);
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
