


public class serverTest extends junit.framework.TestCase{
	serverBridge _servBridge;
	
	public serverTest(){}
	
	public serverTest(String name){super(name);}
	
	public void setUp() throws Exception {
		super.setUp();
		this._servBridge = Driver.getBridge();
	}
	
	public void testDummy(){
		assertFalse(false);
	}
	
	public boolean login(String uName, String password){
		return this._servBridge.login(uName, password);
	}
}
