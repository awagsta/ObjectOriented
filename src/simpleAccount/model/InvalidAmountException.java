package simpleAccount.model;

/**
 * The InvalidAmountException class is used to notify a user who attempts to deposit
 * or withdraw funds with an amount less than or equal to 1.0
 * @author Alexander Wagstaff
 *
 */
public class InvalidAmountException extends Exception {
	
	private static final long serialVersionUID = 1048288020732315389L;
	private String message;
	
	/**
	 * Construct a default exception object with no message
	 */
	public InvalidAmountException(){
		super();
	}
	
	/**
	 * construct an exception object with a message
	 * @param message the message to be stored in the exception object
	 */
	public InvalidAmountException(String message){
		super(message);
		this.message = message;
	}
	
	/**
	 * construct an exception object with specified cause
	 * @param cause the cause to be specified
	 */
	public InvalidAmountException(Throwable cause){
		super(cause);
	}
	
	/**
	 * returns the message associated with this exception object
	 */
	public String toString(){
		return message;
	}
	
	/**
	 * returns the message associated with this exception object
	 */
	public String getMessage(){
		return message;
	}

}
