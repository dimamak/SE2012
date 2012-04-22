package testingClasses;
import serviceCode.Driver;
import bridge.ServerBridgeInt;




public class serverTest extends junit.framework.TestCase{
	protected ServerBridgeInt _servBridge;
	
	public serverTest(){}
	
	public serverTest(String name){super(name);}
	
	public void setUp() throws Exception {
		super.setUp();
		this._servBridge = Driver.getBridge();
	}
	
	public void testDummy(){
		assertFalse(false);
	}
	
	
}
