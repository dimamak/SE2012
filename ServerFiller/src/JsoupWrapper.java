
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class JsoupWrapper{
	private static Map<String, String> _cookies = new HashMap<String, String>();
	private static final int _timeout = 15;

	public static Document get(String url, int timeout) throws IOException {
		// Create connection
	    Connection connection = Jsoup.connect(url);
	    connection.timeout(timeout * 1000);
	    
	    // Compose request
	    connection.request().header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	    connection.request().header("Accept-Encoding", "gzip, deflate");
	    connection.request().header("Accept-Language", "ru-ru,ru;q=0.8,en-us;q=0.5,en;q=0.3");
	    connection.request().header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:11.0) Gecko/20120313 Firefox/11.0");
	    connection.request().header("Connection", "keep-alive");
	    connection.request().header("Host", "www.d.co.il");
	    
	    // Add cookies to request
	    for (Entry<String, String> cookie : _cookies.entrySet()) {
	        connection.cookie(cookie.getKey(), cookie.getValue());
	    }
	    
	    // Request->Response
	    Response response = connection.execute();
	    
	    // Update cookie storage
	    _cookies.putAll(response.cookies());
	    
	    /*Elements meta = doc.select("html head meta");
	    while (meta.attr("http-equiv").contains("REFRESH")){
	        doc = Jsoup.connect(meta.attr("content").split("=")[1]).get();
	        meta = doc.select("html head meta");
	    }*/
	    
	    return response.parse();
	}
	
	public static Document get(String url) throws IOException {
		return get(url, _timeout);
	}
	
	public static void addCookie(String key, String value){
		_cookies.put(key, value);
	}
	
	public static String getCookie(String key){
		return _cookies.get(key);
	}
}
