package forum;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Hashtable;

import http.HttpException;
import http.HttpMessageParser;
import http.HttpRequest;
import http.HttpResponce;

public class ActionHandler {
    protected Hashtable<String, String> _params;
    protected Forum _forum;
    protected final String[] _allowedMethods = {"login","entry","register","logout","viewforum","viewmessage","home","publish","search","addfriend","ban","setmoderator","suspendmoder","setadmin"}; 
    
    public HttpResponce processAction(Forum forum, HttpRequest inPkt) throws IOException, HttpException {
	HttpResponce ans = new HttpResponce();
	this._forum = forum;
	this._params = HttpMessageParser.parseRequestParams(inPkt);
	String action=this._params.get("action");
	
	// If there are cookies, then site visited earlier, then there might be
	// session
	if (inPkt.get_cookies().containsKey("SESSID") && Arrays.asList(this._allowedMethods).contains(action)) {
	    Method method;
	    try {
		method = getClass().getMethod(action);
		method.invoke(this);
	    } catch (NoSuchMethodException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (IllegalArgumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (InvocationTargetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	} else {
	    ans=entry();
	}

	return ans;

    }

    public HttpResponce login() {
	HttpResponce ans = new HttpResponce();
	System.out.println("login");
	return ans;
    }

    public HttpResponce entry() {
	HttpResponce ans = new HttpResponce();
	// Create new session
	Session s = new Session();
	this._forum.add_session(s);

	// Create response
	ans.get_statusLine().set_statusCode(200);
	ans.get_statusLine().set_description("OK");
	ans.add_cookie("SESSID", s.get_id().toString());
	return ans;
    }
}
