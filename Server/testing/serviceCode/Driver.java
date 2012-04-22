package serviceCode;
import bridge.ServerBridgeInt;
import bridge.proxyBridge;


public class Driver {
	
	public static ServerBridgeInt getBridge(){
		proxyBridge bridge = new proxyBridge();
		if (bridge.getRealBridge() != null)
			return bridge.getRealBridge();
		return bridge;
	}
}
