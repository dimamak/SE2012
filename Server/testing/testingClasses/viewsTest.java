package testingClasses;

import org.jsoup.nodes.Document;

public class viewsTest extends serverTest {
	public viewsTest(){	
		super();
		
	}

	public void testViewForum(){
		this._servBridge.entry();
		Document d = (Document)viewForum();
		assertNull(d);
	}
}
