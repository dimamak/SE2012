package structs;

import java.util.List;

public class Post {
    protected String _title;
    protected String _body;
    protected User _publisher;
    protected Long _publishDate;
    protected List<Post> _comments;
}
