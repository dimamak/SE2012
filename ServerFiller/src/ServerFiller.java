import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


public class ServerFiller {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws URISyntaxException 
	 */
	public static void main(String[] args) throws IOException, URISyntaxException {
		// Ensure number of arguments
		if (args.length != 1) {
			System.out.println("Usage: java -Xmx1024m -jar ServerFiller.jar <list of requests filename>");
			return;
		}

		File f = new File(args[0]);
		if (!f.exists()) {
			System.out.println("Couldn't find " + args[0]);
			return;
		}

		FileInputStream fstream = new FileInputStream(f);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		
		while ((strLine = br.readLine()) != null) {
			URL url = new URL(strLine);
			URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
			url = uri.toURL();

			JsoupWrapper.get(url.toString());	
		}
		
		System.out.println(JsoupWrapper.getCookie("SESSID"));
		
		in.close();

	}

}
