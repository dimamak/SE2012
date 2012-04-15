package forum;

import http.HttpException;
import http.HttpMessage;
import http.HttpRequest;
import http.HttpResponse;
import http.RequestLine;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Map.Entry;

public class CommunicationHandler {
	protected static final String _seperator = "\r\n";

	/**
	 * Send {@link HttpResponse}
	 * 
	 * @param p
	 * @throws IOException
	 */
	public static void send(OutputStream os, String charsetName, HttpResponse p)
			throws IOException {
		OutputStreamWriter osw = new OutputStreamWriter(os, charsetName);

		// Write status line
		osw.write(p.get_statusLine().toString());

		// Write blank line
		osw.write(_seperator);

		// Write headers
		for (Entry<String, String> entry : p.get_headers().entrySet()) {
			osw.write(entry.getKey() + ":" + entry.getValue() + _seperator);
		}

		// Write blank line
		osw.write(_seperator);
		osw.flush();

		// Write body
		if (p.get_body() != null)
			os.write(p.get_body());

		osw.flush();
	}

	/**
	 * Try to parse {@link HttpMessage} from InputStream
	 * 
	 * @return {@link HttpRequest} parsed by the function
	 * @throws IOException
	 * @throws HttpException
	 */
	public static HttpRequest receive(InputStream is, String charsetName)
			throws IOException, HttpException {
		Charset charset = Charset.forName(charsetName);
		HttpRequest request = new HttpRequest();
		String line = null, headers = "";

		// Get initial line
		line = readLine(is, charset);
		if (line == null || line.length() == 0)
			return null;

		// Parse initial line
		RequestLine initLine = new RequestLine(line);
		request.set_initLine(initLine);

		// Get headers
		line = readLine(is, charset);
		while (!line.equals("")) {
			headers += "\n" + line;
			line = readLine(is, charset);
		}

		// Parse headers
		if (headers.startsWith("\n")) {
			headers = headers.substring(1);
		}
		parseHeaders(request, headers);

		// Parse body
		parseBody(request, is);

		return request;
	}

	/**
	 * Parse headers of {@link HttpRequest}
	 * 
	 * @param headers
	 * @throws HttpException
	 */
	private static void parseHeaders(HttpRequest request, String headers)
			throws HttpException {
		int splitter;
		String goodHeaders = headers.replace("\n ", "").replace("\n\t", "");

		for (String header : goodHeaders.split("\n")) {
			splitter = header.indexOf(':');
			request.add_header(header.substring(0, splitter).toLowerCase(),
					header.substring(splitter + 1).trim());
		}
	}

	/**
	 * Parse body of {@link HttpRequest}
	 * 
	 * @throws HttpException
	 */
	private static void parseBody(HttpRequest request, InputStream is)
			throws HttpException {
		String length = request.get_headers().get("content-length");
		if (length != null) {
			int bytesToRead = Integer.parseInt(length);
			byte[] body = new byte[bytesToRead];

			int bytesRead = 0;
			try {
				while (bytesRead < bytesToRead) {
					int result = is.read(body, bytesRead, bytesToRead
							- bytesRead);
					if (result == -1)
						throw new HttpException(400, "Ubnormal body length.",
								"Needed: " + bytesToRead + ", Got: "
										+ bytesRead);
					bytesRead += result;
				}
			} catch (IOException e) {
				throw new HttpException(400, "Problem while parsing body.",
						e.getMessage());
			}

			request.set_body(body);
		}
	}

	/**
	 * Read line from given source stream
	 * 
	 * @param is
	 * @param charset
	 * @return String line read from given {@link InputStream}
	 * @throws IOException
	 */
	private static String readLine(InputStream is, Charset charset)
			throws IOException {
		// Test whether the end of file has been reached. If so, return null.
		int readChar = readChar(is, charset);
		if (readChar == -1) {
			return null;
		}
		StringBuffer string = new StringBuffer("");
		// Read until end of file or new line
		while (readChar != -1 && readChar != '\n') {
			if (readChar != '\r') {
				string.append((char) readChar);
			}
			// Read the next character
			readChar = readChar(is, charset);
		}
		return string.toString();
	}

	/**
	 * Read char from given source stream
	 * 
	 * @param is
	 * @param charset
	 * @return Char read from given {@link InputStream}
	 * @throws IOException
	 */
	private static char readChar(InputStream is, Charset charset)
			throws IOException {
		int bpc = (int) charset.newEncoder().averageBytesPerChar();
		byte[] b = new byte[bpc];
		is.read(b);
		ByteBuffer bb = ByteBuffer.wrap(b);
		CharBuffer cb = charset.decode(bb);
		return cb.charAt(0);
	}
}
