package simpleAccount.model;

/**
 * This exception indicates an account with the specified account ID already exists in the system.
 * @author Alexander Wagstaff
 *
 */
public class DuplicateAccountException extends Exception {

	private static final long serialVersionUID = 7848439502944351581L;
	private String message = null;
	
	/**
	 * Constructs a DuplicateAccountException object with no message.
	 */
	public DuplicateAccountException(){
		super();
	}
	
	/**
	 * Constructs a DuplicateAccountException object with the specified account information.
	 * @param DuplicateAccountInfo The information associated with an account recognized as
	 * a duplicate
	 */
	public DuplicateAccountException(String DuplicateAccountInfo){
		super(DuplicateAccountInfo);
		this.message = "Error in account insertion involving account: " + DuplicateAccountInfo
				+ "\nAccount Ids must be unique. Aborting insertion of account";
	}
	
	/**
	 * Constructs a DuplicateAccountException object with the specified cause.
	 * @param cause the cause specified
	 */
	public DuplicateAccountException(Throwable cause){
		super(cause);
	}
	
	/**
	 * Returns the message associated with this exception.
	 */
	public String getMessage(){
		return message;
	}
	
	/**
	 * Returns a string representation of this exception.
	 */
	public String toString(){
		return message;
	}

}
