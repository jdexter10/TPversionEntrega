package tp1.p2.control.exceptions;

public class NotEnoughCoinsException extends GameException{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotEnoughCoinsException(String message)
	{
		super(message);
	}
	
	public NotEnoughCoinsException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public NotEnoughCoinsException(Throwable cause)
	{
		super(cause); 
	}
	
}