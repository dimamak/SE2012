package structs;

import java.util.List;

public class Message {
	protected String _title;
    protected String _body;
    protected User _publisher;
    protected Long _publishDate;
    protected List<Message> _comments;
}
