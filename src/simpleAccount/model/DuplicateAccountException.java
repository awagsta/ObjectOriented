package simpleAccount.model;

public class DuplicateAccountException extends Exception {

	private static final long serialVersionUID = 7848439502944351581L;
	private String message = null;
	
	public DuplicateAccountException(){
		super();
	}
	
	public DuplicateAccountException(String DuplicateAccountInfo){
		super(DuplicateAccountInfo);
		this.message = "Error in account insertion involving account: " + DuplicateAccountInfo
				+ "\nAccount Ids must be unique. Aborting insertion of account";
	}
	
	public DuplicateAccountException(Throwable cause){
		super(cause);
	}
	
	public String getMessage(){
		return message;
	}
	
	public String toString(){
		return message;
	}

}
