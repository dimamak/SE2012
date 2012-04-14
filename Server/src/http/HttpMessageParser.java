package http;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Hashtable;

/**
 * HttpMessageParser class is to provide tools for {@link HttpMessage} parsing.
 * 
 * @version 1.0
 */
public class HttpMessageParser {

    /**
     * Try to parse {@link HttpMessage} parametrs
     * 
     * @return {@link HttpRequest} parsed parametrs
     */
    public static Hashtable<String, String> parseRequestParams(HttpRequest inPkt) throws IOException, HttpException {
	Hashtable<String, String> params = new Hashtable<String, String>();
	String uri = inPkt.get_initLine()._uri;
	String toparse = "";
	String[] split_and, split_equal;

	if (inPkt.get_body() != null)
	    toparse = new String(inPkt.get_body());

	/* add get params */
	if (uri.length() > 2)
	    toparse = uri.substring(2) + "&" + toparse;

	if (toparse.length() > 0) {
	    split_equal = new String[2];
	    split_and = toparse.split("&");

	    /* for all couples */
	    for (String couple : split_and) {
		split_equal = couple.split("=");

		/* if empty param */
		if (split_equal.length == 1)
		    params.put(split_equal[0], "");
		else
		    params.put(split_equal[0], split_equal[1]);
	    }
	}
	return params;
    }

    /**
     * Try to parse {@link HttpMessage} from InputStream
     * 
     * @return {@link HttpRequest} parsed by the function
     * @throws IOException
     * @throws HttpException
     */
    public static HttpRequest parseRequest(InputStream is, String charsetName) throws IOException, HttpException {
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
    private static void parseHeaders(HttpRequest request, String headers) throws HttpException {
	int splitter;
	String goodHeaders = headers.replace("\n ", "").replace("\n\t", "");

	for (String header : goodHeaders.split("\n")) {
	    splitter = header.indexOf(':');
	    request._headers.put(header.substring(0, splitter).toLowerCase(), header.substring(splitter + 1).trim());
	}
    }

    /**
     * Parse body of {@link HttpRequest}
     * 
     * @throws HttpException
     */
    private static void parseBody(HttpRequest request, InputStream is) throws HttpException {
	String length = request.get_headers().get("content-length");
	if (length != null) {
	    int bytesToRead = Integer.parseInt(length);
	    byte[] body = new byte[bytesToRead];

	    int bytesRead = 0;
	    try {
		while (bytesRead < bytesToRead) {
		    int result = is.read(body, bytesRead, bytesToRead - bytesRead);
		    if (result == -1)
			throw new HttpException(400, "Ubnormal body length.", "Needed: " + bytesToRead + ", Got: " + bytesRead);
		    bytesRead += result;
		}
	    } catch (IOException e) {
		throw new HttpException(400, "Problem while parsing body.", e.getMessage());
	    }

	    request.set_body(body);
	}
    }

    /**
     * Read char from given source stream
     * 
     * @param is
     * @param charset
     * @return Char read from given {@link InputStream}
     * @throws IOException
     */
    private static char readChar(InputStream is, Charset charset) throws IOException {
	int bpc = (int) charset.newEncoder().averageBytesPerChar();
	byte[] b = new byte[bpc];
	is.read(b);
	ByteBuffer bb = ByteBuffer.wrap(b);
	CharBuffer cb = charset.decode(bb);
	return cb.charAt(0);
    }

    /**
     * Read line from given source stream
     * 
     * @param is
     * @param charset
     * @return String line read from given {@link InputStream}
     * @throws IOException
     */
    private static String readLine(InputStream is, Charset charset) throws IOException {
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

}
