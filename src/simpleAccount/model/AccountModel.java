package simpleAccount.model;

import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The AccountModel class represents the model for an account controlling system. It forms the
 * model portion of the MVC architecture
 * @author Alexander Wagstaff
 *
 */
public class AccountModel extends AbstractModel {
	private ArrayList<Account> accountList = new ArrayList<Account>();
	public final static double EXCHANGE_YUAN = 6.91;
	public final static double EXCHANGE_EURO = 0.94;
	String fileName;
	

	/**
	 * Constructor to initialize the account objects in the model 
	 * @param fileName the file containing account information to initialize account objects
	 */
	public AccountModel(String fileName){
		this.fileName = fileName;
		Scanner inFile = null;
		try{
			inFile = new Scanner(new FileReader(fileName));
		}catch(Exception e){
			System.out.println("Unable to find file. System will now exit");
			System.exit(1);
		}
		while(inFile.hasNext()){
			String line = inFile.nextLine();
			String[] words = line.split("\t");
			try{
				accountList.add(new Account(words[0], Integer.parseInt(words[1]), Double.parseDouble(words[2])));
			}catch(NumberFormatException e){
				System.out.println("Invalid entry in account ID or amount fields. Aborting insertion of affected account");
			}
		}
		inFile.close();
		
		
	}
	
	/**
	 * Adds the specified amount to the specified account
	 * @param amount the amount to be added
	 * @param account the account to which the amount is added
	 */
	public void deposit(double amount, int accountId) throws InvalidAmountException{
		Account searchedAccount = findAccount(accountId);
		if(searchedAccount.getId() == -1){
			System.out.println("Something went wrong.");
		}
		
		if(amount <= 1.0){
			throw new InvalidAmountException("Invalid amount entered. Amount must be greater than or equal to 1.0");
		}
		
		double temp = amount + searchedAccount.getAmount();
		searchedAccount.setAmount(temp);
		
		//TODO change the model event once sure how to implement it
		ModelEvent me = new ModelEvent(this, searchedAccount.getId(), "", temp );
		notifyChanged(me);
		
	}
	
	/**
	 * Withdraws the specified amount from the account if the amount is
	 * positive and not greater than the amount currently in the account.
	 * @param amount the amount to be withdrawn
	 * @param account the account from which the amount is deducted.
	 */
	public void withdraw(double amount, int accountId) throws InsufficientFundsException, InvalidAmountException{
		Account searchedAccount = findAccount(accountId);
		if(searchedAccount.getId() == -1){
			System.out.println("Something went wrong.");
		}
		
		if(amount > searchedAccount.getAmount()){
			throw new InsufficientFundsException("Insufficient funds in account to make withdrawal.");
		}
		
		if(amount <= 1.0){
			throw new InvalidAmountException("Invalid amount entered. Amount must be greater than or equal to 1.0");
		}
		
		double temp = searchedAccount.getAmount() - amount;
		searchedAccount.setAmount(temp);
		
		//TODO determine importance and role of model event parameters
		ModelEvent me = new ModelEvent(this, searchedAccount.getId(), "", temp);
		notifyChanged(me);
	}
	
	/**
	 * Saves the current state of the accounts. Writes the data to the file that was specified 
	 * in the constructor.
	 */
	public void save() {
		try{
			PrintWriter outFile = new PrintWriter(fileName);
			for(Account account: accountList){
				outFile.println(account + "\t" + account.getAmount());
			}
			outFile.close();
		}catch(Exception e){
			System.out.println("File not found and could not be created. Failed to save");
		}
		
	}
	
	/**
	 * Exits the system. If changes to accounts were made, Writes the data to the file that was 
	 * specified in the constructor.
	 */
	public void quit() {
		try{
			PrintWriter outFile = new PrintWriter(fileName);
			for(Account account: accountList){
				outFile.println(account + "\t" + account.getAmount());
			}
			outFile.close();
		}catch(Exception e){
			System.out.println("File not found and could not be created. Failed to save");
		}
		System.exit(0);
		
	}
	
	private Account findAccount(int searchId) {
		for(Account temp: accountList){
			if(temp.getId() == searchId)
				return temp;
		}
		return new Account();
	}
	
	public Account getAccount(int searchId){
		return findAccount(searchId);
		
	}
	
	/**
	 * Returns a string array representation of the list of accounts
	 * @return The string array representing the accounts
	 */
	public String[] getAccountList(){
		Account[] accountArray = accountList.toArray(new Account[0]);
		String[] accountList = new String[accountArray.length];
		
		for(int i = 0; i < accountList.length; i++){
			accountList[i] = accountArray[i].toString();
		}
		return accountList;
	}
	//TODO implement constructor functionality to read data in
	
}
