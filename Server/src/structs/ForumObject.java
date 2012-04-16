package structs;

import java.util.ArrayList;
import java.util.List;

public abstract class ForumObject {
	protected static Integer _nextId = 0;
	protected Integer _id;
	protected ForumObject _parent;
	protected List<ForumObject> _children;
	
	public ForumObject(){
		this._id = _nextId;
		this._children = new ArrayList<ForumObject>();
		_nextId++;		
	}

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}

	public List<ForumObject> get_children() {
		return _children;
	}

	public void set_children(List<ForumObject> _children) {
		this._children = _children;
	}

	public ForumObject get_parent() {
		return _parent;
	}

	public void set_parent(ForumObject _parent) {
		this._parent = _parent;
	}
	
	
}
