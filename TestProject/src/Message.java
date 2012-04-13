import java.util.Hashtable;


/**
 * Message class represents basic structure to be used 
 * as input/output by communication module.
 * @author Andrey
 * @version 1.0
 */
public class Message {
	private MessageType _type;
	private Hashtable<String, String> _parameters;
}
