package tp1.p2.control.exceptions;

public class CommandParseException extends GameException {

	private static final long serialVersionUID = 1L;

	public CommandParseException(String message)
	{
		super(message);
	}
	
	public CommandParseException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public CommandParseException(Throwable cause)
	{
		super(cause); 
	}
	
}
