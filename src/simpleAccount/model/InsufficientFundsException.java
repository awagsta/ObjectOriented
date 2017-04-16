package simpleAccount.model;

/**
 * This exception indicates there are insufficient funds in an account to proceed with a withdrawal.
 * @author Alexander Wagstaff
 *
 */
public class InsufficientFundsException extends Exception {

	private static final long serialVersionUID = 8011100635221108625L;
	private String message = null;

	/**
	 * Constructs an InsufficientFundsException object with no message.
	 */
	public InsufficientFundsException(){
		super();
	}
	
	/**
	 * Constructs an InsufficientFundsException object with a message.
	 * @param message the message to be stored in the exception object
	 */
	public InsufficientFundsException(String message){
		super(message);
		this.message = message;
	}
	
	/**
	 * Constructs an InsufficientFundsException object with specified cause.
	 * @param cause the cause to be specified
	 */
	public InsufficientFundsException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * Returns the message associated with this exception object.
	 */
	public String toString(){
		return message;
	}
	
	/**
	 * Returns String representation of this exception object.
	 */
	public String getMessage(){
		return message;
	}
		
}
