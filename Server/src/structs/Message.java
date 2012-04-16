package structs;

public class Message extends ForumObject{
	protected String _title;
    protected String _body;
    protected User _publisher;
    protected Long _publishDate;
    
	public String get_title() {
		return _title;
	}
	public void set_title(String _title) {
		this._title = _title;
	}
	public String get_body() {
		return _body;
	}
	public void set_body(String _body) {
		this._body = _body;
	}
	public User get_publisher() {
		return _publisher;
	}
	public void set_publisher(User _publisher) {
		this._publisher = _publisher;
	}
	public Long get_publishDate() {
		return _publishDate;
	}
	public void set_publishDate(Long _publishDate) {
		this._publishDate = _publishDate;
	}
    
    
}
