package simpleAccount.controller;

import java.text.DecimalFormat;

import simpleAccount.model.AbstractModel;
import simpleAccount.model.Account;
import simpleAccount.model.AccountModel;
import simpleAccount.model.InsufficientFundsException;
import simpleAccount.model.InvalidAmountException;
import simpleAccount.view.AccountDetailView;

/**
 * The AccountController class represents the account controller for the account system.
 * This class's primary role is to act as an interface between individual account instances
 * in the AccountModel and the AccountDetailView.
 * @author Alexander Wagstaff
 *
 */
public class AccountController extends AbstractController {
	private static final String WITHDRAWAL_ERROR = "Withdrawal Error";
	private static final String DEPOSIT_ERROR = "Deposit Error";
	
	/**
	 * Constructs an AccountController Object that acts as an interface between
	 * The desired AccountDetailView and the AccountModel.
	 * @param model The AccountModel to interface with
	 * @param viewId The id of the account associated with this view
	 * @param currencyType The type of currency the view is initialized in
	 */
	AccountController(AbstractModel model, int viewId, String currencyType){
		setModel(model);
		Account searchedAccount = ((AccountModel)getModel()).getAccount(viewId);
		String accountString = searchedAccount.toString();
		double accountAmount = searchedAccount.getAmount();
		
		if(currencyType.equals("Euro")){
			accountAmount = accountAmount * AccountModel.EXCHANGE_EURO;	
		}
		else if(currencyType.equals("Yuan")){
			accountAmount = accountAmount * AccountModel.EXCHANGE_YUAN;
		}
		setView(new AccountDetailView(model, this, viewId, accountString, currencyType, Double.toString(accountAmount)));
	}
	
	/**
	 * Receives input from the AccountDetailView and executes the desired operation on the AccountModel.
	 * @param option The desired operation
	 * @param viewId The id of the account associated with this view
	 * @param enteredAmount The amount specified to be deposited or withdrawn
	 * @param currencyType The type of currency specified to perform the operation with
	 */
	public void operation(String option, int viewId, String enteredAmount, String currencyType){
			if(option.equals(AccountDetailView.DEPOSIT)){
				try{
					double formattedAmount = formatAmount(enteredAmount, currencyType);
					((AccountModel)getModel()).deposit(formattedAmount, viewId);
					
				}catch(NumberFormatException e){
					String errorMessage = "Invalid amount entered for deposit. Amount must be a number.";
					((AccountDetailView)getView()).displayErrorMessage(errorMessage, DEPOSIT_ERROR);
				}
				catch(InvalidAmountException e){
					((AccountDetailView)getView()).displayErrorMessage(e.getMessage(), DEPOSIT_ERROR);
					
				}
			}
			else{
				try{
					double formattedAmount = formatAmount(enteredAmount, currencyType);
					((AccountModel)getModel()).withdraw(formattedAmount, viewId);
				}
				catch(NumberFormatException e){
					String errorMessage = "Invalid amount entered for withdrawal. Amount must be a number.";
					((AccountDetailView)getView()).displayErrorMessage(errorMessage, WITHDRAWAL_ERROR);
				}
				catch(InvalidAmountException e){
					((AccountDetailView)getView()).displayErrorMessage(e.getMessage(), WITHDRAWAL_ERROR);
				}
				catch(InsufficientFundsException e){
					((AccountDetailView)getView()).displayErrorMessage(e.getMessage(), WITHDRAWAL_ERROR);

				}
			}
				
	}
	
	// Converts entered Euros or Yuan into USD for deposits or withdrawals
	private double formatAmount(String amount, String currencyType) throws NumberFormatException{
		DecimalFormat df = new DecimalFormat("#.00");
		if(currencyType.equals("Euro")){
			// Convert entered Euros to USD
			double convertedAmount = Double.parseDouble(amount) / AccountModel.EXCHANGE_EURO;
			return Double.parseDouble(df.format(convertedAmount));
		}
		// Convert entered Yuan to USD
		else if(currencyType.equals("Yuan")){
			double convertedAmount = Double.parseDouble(amount) / AccountModel.EXCHANGE_YUAN;
			return Double.parseDouble(df.format(convertedAmount));
		}
		else{
			double convertedAmount = Double.parseDouble(amount);
			return Double.parseDouble(df.format(convertedAmount));
		}
			
	}
	
	/**
	 * This method returns the currency in the appropriate format for the view that requested it.
	 * @param amount The updated amount in the associated account
	 * @param currencyType The type of currency the view is associated with
	 * @return The formatted currency rounded to 2 decimal places
	 */
	public String getCurrency(double amount, String currencyType){
		DecimalFormat df = new DecimalFormat("#.00");
		// Convert the value in the account to the view's associated currency
		if(currencyType.equals("Euro")){
			amount = amount * AccountModel.EXCHANGE_EURO;
		}
		else if(currencyType.equals("Yuan")){
			amount = amount * AccountModel.EXCHANGE_YUAN;
		}
		return df.format(amount);
		
	}

}
