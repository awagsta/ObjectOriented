package simpleAccount.model;

/**
 * The Account class is used to create account representations with 3 fields:
 * Name of the account holder, Id of the account holder, and the amount in the account.
 * @author Alexander Wagstaff
 *
 */
public class Account {
private String name;
private int id;
private double amount;

	/**
	 * Constructs a default account object
	 */
	public Account(){
		name = "";
		id = -1;
		amount = 0.0;
	}
	
	/**
	 * Constructs an account object with given parameters
	 * @param name The name of the account holder
	 * @param id The id of the account holder
	 * @param amount The amount in the account
	 */
	public Account(String name, int id, double amount){
		this.name = name;
		this.id = id;
		this.amount = amount;
	}
	
	/**
	 * Returns the name of the account holder
	 * @return the name of account holder
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Sets the name of the account holder
	 * @param name the name to be set
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * Returns the id of the account holder
	 * @return id of the account holder
	 */
	public int getId(){
		return id;
	}
	
	/**
	 * Sets the id of the account holder
	 * @param id the id to be set
	 */
	public void setId(int id){
		this.id = id;
	}
	
	/**
	 * Returns the amount in the account
	 * @return the amount
	 */
	public double getAmount(){
		return amount;
	}
	
	/**
	 * Set the amount in the account
	 * @param amount the amount to be set
	 */
	public void setAmount(Double amount){
		this.amount = amount;
	}
	
	public String toString(){
		String temp = name + "\t" + id;
		return temp;
	}
	
	@Override
	public boolean equals(Object o){
		if(o == null)
			return false;
		
		if(!(o instanceof Account))
			return false;
		
		Account oAccount = (Account) o;
		
		if(this.id == oAccount.id)
			return true;
		else
			return false;
		}
	
	@Override
	public int hashCode(){
		return id;
	}
		
}

