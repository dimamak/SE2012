package forum;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

import structs.ForumObject;
import structs.Message;
import structs.SubForum;
import structs.User;

import http.HttpException;
import http.HttpRequest;
import http.HttpResponse;

public class ActionHandler {

	public static HttpResponse processAction(ForumRunnable fr, HttpRequest inPkt)
			throws HttpException {
		HttpResponse ans = new HttpResponse();

		// If there are cookies, then site visited earlier,
		// then there might be session
		if (inPkt.get_cookies().containsKey("SESSID")
				&& inPkt.get_arguments().containsKey("action")) {
			try {
				String action = inPkt.get_arguments().get("action");
				Method mtd = ActionHandler.class.getMethod(action,
						ForumRunnable.class, HttpRequest.class);
				ans = (HttpResponse) mtd.invoke(null, fr, inPkt);
			} catch (NoSuchMethodException e) {
				throw new HttpException(501, "Method Not Implemented.");
			} catch (InvocationTargetException e) {
				if (e.getCause() instanceof HttpException)
					throw (HttpException) e.getCause();
				else
					throw new HttpException(500, "Internal Server Error",
							e.getMessage());
			} catch (Exception e) {
				throw new HttpException(500, "Internal Server Error",
						e.getMessage());
			}
		} else {
			ans = entry(fr, inPkt);
		}

		return ans;

	}

	private static Integer getIntArgument(HttpRequest inPkt, String argName) {
		Integer ans = null;

		try {
			String argNameValue = inPkt.get_arguments().get(argName);
			ans = Integer.parseInt(argNameValue);
		} catch (Exception e) {
		}

		return ans;
	}

	public static HttpResponse login(ForumRunnable forum, HttpRequest inPkt)
			throws HttpException {
		HttpResponse ans = new HttpResponse();
		User member;
		Session curSession = forum.get_session(UUID.fromString(inPkt
				.get_cookies().get("SESSID")));
		String username = inPkt.get_arguments().get("username");
		String password = inPkt.get_arguments().get("password");
		password = SecurityHandler.generateMd5(password);
		if (username == null || password == null) {
			throw new HttpException(400, "Parameter miss");
		} else {
			member = forum.get_user(username);

			if (member == null) {
				throw new HttpException(401, "Username or password error");
			} else if (password == null
					|| member.get_password().compareTo(password) != 0) {
				throw new HttpException(401, "Username or password error");
			} else {
				curSession.set_user(member);
				ans.set_htmlbody("Logged in", "<h1>Successfully logged in</h1>");
			}
		}
		return ans;
	}

	public static HttpResponse entry(ForumRunnable fr, HttpRequest inPkt) {
		HttpResponse ans = new HttpResponse();
		// Create new session
		Session s = new Session();
		fr.add_session(s.get_id(), s);

		// Create response
		ans.get_statusLine().set_statusCode(200);
		ans.get_statusLine().set_description("OK");
		ans.add_cookie("SESSID", s.get_id().toString());
		return ans;
	}

	public static HttpResponse register(ForumRunnable forum, HttpRequest inPkt)
			throws HttpException {
		HttpResponse ans = new HttpResponse();
		User member;
		Session curSession = forum.get_session(UUID.fromString(inPkt
				.get_cookies().get("SESSID")));
		String fname = inPkt.get_arguments().get("fname");
		String sname = inPkt.get_arguments().get("sname");
		String email = inPkt.get_arguments().get("email");
		String username = inPkt.get_arguments().get("username");
		String password = inPkt.get_arguments().get("password");
		if (username == null || password == null || email == null
				|| sname == null || fname == null) {
			throw new HttpException(400, "Parameter miss");
		} else {
			member = forum.get_user(username);
			if (member != null) {
				throw new HttpException(400, "Username already exist");
			} else {
				member = forum.get_user_byemail(email);
				if (member != null) {
					throw new HttpException(400, "Email already exist");
				} else {
					member = new User(fname, sname, username, password, email);
					forum.add_user(member);
					curSession.set_user(member);
					ans.set_htmlbody("Registered",
							"<h1>Successfully registered</h1>");
				}
			}
		}
		return ans;
	}

	public static HttpResponse logout(ForumRunnable forum, HttpRequest inPkt) {
		HttpResponse ans = new HttpResponse();
		Session curSession = forum.get_session(UUID.fromString(inPkt
				.get_cookies().get("SESSID")));
		curSession.set_user(null);
		ans.set_htmlbody("Logged out", "<h1>Successfully logged out</h1>");
		return ans;
	}

	public static HttpResponse viewdiscussion(ForumRunnable fr,
			HttpRequest inPkt) {
		return null;
	}

	public static HttpResponse viewsubforum(ForumRunnable fr, HttpRequest inPkt)
			throws HttpException {
		HttpResponse ans = new HttpResponse();

		// Ensure subforumid argument specified
		Integer subforumid = getIntArgument(inPkt, "subforumid");
		if (subforumid == null)
			throw new HttpException(400, "No subforumid argument specified.");

		// Ensure subforum exists and it is really subforum (not forum, not
		// message)
		ForumObject fo = fr.get_fobjects().get(subforumid);
		if (fo == null || fo.get_parent() == null
				|| fo.get_parent().get_id() != 0)
			throw new HttpException(400, "No subforum with given id found.");

		String title = "List of discussions in subforum "
				+ ((SubForum) fo).get_title();

		String body = "<ul>";
		for (ForumObject msg : fo.get_children()) {
			body += "<li>" + ((Message) msg).get_title() + "</li>";
		}
		body += "</ul>";

		ans.get_statusLine().set_statusCode(200);
		ans.get_statusLine().set_description("OK");
		ans.set_htmlbody(title, body);

		return ans;
	}

	/**
	 * 
	 * @param fr
	 * @param inPkt
	 * @return List of subforums
	 */
	public static HttpResponse viewforum(ForumRunnable fr, HttpRequest inPkt) {
		HttpResponse ans = new HttpResponse();

		String title = "List of subforums";

		String body = "<ul>";
		for (SubForum sf : fr.get_forum().get_subforums()) {
			body += "<li>" + sf.get_title() + "</li>";
		}
		body += "</ul>";

		ans.get_statusLine().set_statusCode(200);
		ans.get_statusLine().set_description("OK");
		ans.set_htmlbody(title, body);

		return ans;
	}

	public static HttpResponse publish(ForumRunnable fr, HttpRequest inPkt)
			throws HttpException {
		String title = inPkt.get_arguments().get("title");
		String content = inPkt.get_arguments().get("content");
		Integer parentmsgid = getIntArgument(inPkt, "parentmsgid");
		Integer messageid = getIntArgument(inPkt, "messageid");

		// If creating subforum/message
		if (parentmsgid != null) {
			ForumObject fo = fr.get_fobjects().get(parentmsgid);
			if (fo == null)
				throw new HttpException(400, "No parent message found.");

			// If creating subforum
			if (parentmsgid == 0) {
				if (title == null)
					throw new HttpException(400, "No title argument specified.");

				SubForum s = new SubForum(title);
				fr.add_fobject(s, fo);
			}
			// If creating message
			else {
				if (title == null && content == null)
					throw new HttpException(400,
							"No title/content arguments specified.");

				Message m = new Message();

				if (title == null)
					title = "No title";
				m.set_title(title);

				if (content != null)
					content = "";
				m.set_body(content);

				fr.add_fobject(m, fo);
			}
		}
		// If editing/deleting subforum/message
		else if (messageid != null) {
			ForumObject fo = fr.get_fobjects().get(messageid);
			if (fo == null || fo.get_id() == 0)
				throw new HttpException(400, "No message found.");

			// If editing/deleting subforum
			if (fo.get_parent().get_id() == 0) {
				if (title == null)
					fr.delete_fobject(fo);
				else
					((SubForum) fo).set_title(title);
			}
			// If editing/deleting message
			else {
				if (title == null && content == null)
					fr.delete_fobject(fo);
				else {
					if (title == null)
						title = "No title";
					((Message) fo).set_title(title);

					if (content != null)
						content = "";
					((Message) fo).set_body(content);
				}

			}

		} else
			throw new HttpException(400,
					"No parentmsgid/messageid argument specified.");

		return viewforum(fr, inPkt);
	}

	public static HttpResponse search(ForumRunnable fr, HttpRequest inPkt) {
		return null;
	}

	public static HttpResponse addfriend(ForumRunnable fr, HttpRequest inPkt) {
		return null;
	}

	public static HttpResponse ban(ForumRunnable fr, HttpRequest inPkt) {
		return null;
	}

	public static HttpResponse setmoderator(ForumRunnable fr, HttpRequest inPkt) {
		return null;
	}

	public static HttpResponse suspendmoder(ForumRunnable fr, HttpRequest inPkt) {
		return null;
	}

	public static HttpResponse setadmin(ForumRunnable fr, HttpRequest inPkt) {
		return null;
	}
}
