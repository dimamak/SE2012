
public class Driver {
	
	public static serverBridge getBridge(){
		proxyBridge bridge = new proxyBridge();
		if (bridge.getRealBridge() != null)
			return bridge.getRealBridge();
		return bridge;
	}
}
