
public class loginTest extends serverTest {
	public loginTest() {
		super();
	}
	
	public void  testLogin(){
		assertFalse(login("uName1","password"));
	}
}
