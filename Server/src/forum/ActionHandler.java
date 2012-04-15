package forum;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import structs.SubForum;

import http.HttpException;
import http.HttpRequest;
import http.HttpResponse;

public class ActionHandler {

	public static HttpResponse processAction(Forum forum, HttpRequest inPkt) throws HttpException {
		HttpResponse ans = new HttpResponse();

		// If there are cookies, then site visited earlier, 
		// then there might be session
		if (inPkt.get_cookies().containsKey("SESSID") && inPkt.get_arguments().containsKey("action")) {
			try {
				String action = inPkt.get_arguments().get("action");
				Method mtd = ActionHandler.class.getMethod(action, Forum.class, HttpRequest.class);
				ans = (HttpResponse) mtd.invoke(null, forum, inPkt);
			} catch (NoSuchMethodException e) {
				throw new HttpException(501,"Method Not Implemented.");
			} catch (InvocationTargetException e){
				if(e.getCause() instanceof HttpException)
					throw (HttpException)e.getCause();
				else
					throw new HttpException(500,"Internal Server Error", e.getMessage());
			} catch (Exception e) {
				throw new HttpException(500,"Internal Server Error", e.getMessage());
			}
		} else {
			ans = entry(forum, inPkt);
		}

		return ans;

	}

	public static HttpResponse login(Forum forum, HttpRequest inPkt) {
		HttpResponse ans = new HttpResponse();
		System.out.println("login");
		return ans;
	}

	public static HttpResponse entry(Forum forum, HttpRequest inPkt) {
		HttpResponse ans = new HttpResponse();
		// Create new session
		Session s = new Session();
		forum.add_session(s.get_id(), s);

		// Create response
		ans.get_statusLine().set_statusCode(200);
		ans.get_statusLine().set_description("OK");
		ans.add_cookie("SESSID", s.get_id().toString());
		return ans;
	}
	
	public static HttpResponse register(Forum forum, HttpRequest inPkt){
		return null;
	}
	
	public static HttpResponse logout(Forum forum, HttpRequest inPkt){
		return null;
	}
	
	public static HttpResponse viewdiscussion(Forum forum, HttpRequest inPkt){
		return null;
	}
	
	public static HttpResponse viewsubforum(Forum forum, HttpRequest inPkt){
		return null;
	}
	
	public static HttpResponse addsubforum(Forum forum, HttpRequest inPkt) throws HttpException{
		String title = inPkt.get_arguments().get("title");
		if(title == null)
			throw new HttpException(400,"No title specified.");
		
		// Ensure there is no subforum with same title
		for(SubForum sf : forum.get_sforums()){
			if(sf.get_title().contentEquals(title))
				throw new HttpException(400,"Subforum with given title exists.");
		}
		
		SubForum s = new SubForum(title);
		forum.get_sforums().add(s);
		
		
		
		
		HttpResponse ans = new HttpResponse();
		
		String ttl = "Main page";
		
		String body = "<ul>";
		for(SubForum sf : forum.get_sforums()){
			body += "<li>" + sf.get_title() + "</li>";
		}
		body += "</ul>";
		
		ans.get_statusLine().set_statusCode(200);
		ans.get_statusLine().set_description("OK");
		ans.set_htmlbody(ttl, body);
		
		return ans;
	}
	
	/**
	 * 
	 * @param forum
	 * @param inPkt
	 * @return List of subforums
	 */
	public static HttpResponse viewforum(Forum forum, HttpRequest inPkt){
		HttpResponse ans = new HttpResponse();
		
		String title = "Main page";
		
		String body = "<ul>";
		for(SubForum sf : forum.get_sforums()){
			body += "<li>" + sf.get_title() + "</li>";
		}
		body += "</ul>";
		
		ans.get_statusLine().set_statusCode(200);
		ans.get_statusLine().set_description("OK");
		ans.set_htmlbody(title, body);
		
		return ans;
	}
	
	public static HttpResponse publish(Forum forum, HttpRequest inPkt){
		return null;
	}
	
	public static HttpResponse search(Forum forum, HttpRequest inPkt){
		return null;
	}
	
	public static HttpResponse addfriend(Forum forum, HttpRequest inPkt){
		return null;
	}
	
	public static HttpResponse ban(Forum forum, HttpRequest inPkt){
		return null;
	}
	
	public static HttpResponse setmoderator(Forum forum, HttpRequest inPkt){
		return null;
	}
	
	public static HttpResponse suspendmoder(Forum forum, HttpRequest inPkt){
		return null;
	}
	
	public static HttpResponse setadmin(Forum forum, HttpRequest inPkt){
		return null;
	}
}
