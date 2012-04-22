package testingClasses;

public class loginTest extends serverTest {

	public loginTest(){
		super();
	}
	public void  testLogin(){
		assertFalse(login("Roman","pass"));
	}
		

}
