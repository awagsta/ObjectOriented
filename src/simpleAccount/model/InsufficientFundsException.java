package simpleAccount.model;

/**
 * The InsufficientFundsException class is used to notify a user who attempts to withdraw
 * more funds from an account than are available that the action cannot proceed.
 * @author Alexander Wagstaff
 *
 */
public class InsufficientFundsException extends Exception {

	private static final long serialVersionUID = 8011100635221108625L;
	private String message = null;

	/**
	 * Construct a default exception object with no message.
	 */
	public InsufficientFundsException(){
		super();
	}
	
	/**
	 * Construct an exception object with a message
	 * @param message the message to be stored in the exception object
	 */
	public InsufficientFundsException(String message){
		super(message);
		this.message = message;
	}
	
	/**
	 * Construct new exception object with specified cause
	 * @param cause the cause to be specified
	 */
	public InsufficientFundsException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * Returns the message associated with this exception object
	 */
	public String toString(){
		return message;
	}
	
	/**
	 * Returns message associated with this exception object
	 */
	public String getMessage(){
		return message;
	}
		
}
