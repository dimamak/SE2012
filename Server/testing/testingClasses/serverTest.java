package testingClasses;
import serviceCode.Driver;
import bridge.ServerBridgeInt;


public class serverTest extends junit.framework.TestCase{
	protected ServerBridgeInt _servBridge = Driver.getBridge();
	
	public serverTest(){}
	
	public serverTest(String name){super(name);}
	
	public void setUp() throws Exception {
		
		this._servBridge = Driver.getBridge();
//		this._servBridge.entry();
	}
    
	public void testDummy(){
		assertTrue(true);
	}
	
	protected boolean login(String uName, String password){
		return this._servBridge.login(uName, password);
	}
	
	protected Object viewForum(){
		return this._servBridge.viewForum();
	}
}
