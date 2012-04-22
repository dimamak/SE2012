package testingClasses;

public class loginTest extends serverTest {
	public loginTest() {
		super();
	}
	
	public void  testLogin(){
		assertFalse(this._servBridge.login("uName1","password"));
	}
}
