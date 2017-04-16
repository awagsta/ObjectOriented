package simpleAccount.controller;

import java.text.DecimalFormat;

import simpleAccount.model.AbstractModel;
import simpleAccount.model.Account;
import simpleAccount.model.AccountModel;
import simpleAccount.model.InvalidAmountException;
import simpleAccount.view.AccountDetailView;

public class AccountController extends AbstractController {
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
	
	// TODO flesh out the catch blocks to be more specific and generate JDialogs
	public void operation(String option, int viewId, String enteredAmount, String currencyType){
			if(option.equals(AccountDetailView.DEPOSIT)){
				try{
					double formattedAmount = formatAmount(enteredAmount, currencyType);
					((AccountModel)getModel()).deposit(formattedAmount, viewId);
					
				}catch(NumberFormatException e){
					String errorMessage = "Invalid amount entered for deposit. Amount must be a number.";
					String errorTitle = "Deposit Error";
					((AccountDetailView)getView()).displayErrorMessage(errorMessage, errorTitle);
				}
				catch(InvalidAmountException e){
					String errorTitle = "Deposit Error";
					((AccountDetailView)getView()).displayErrorMessage(e.getMessage(), errorTitle);
					
				}
			}
			else{
				try{
					double formattedAmount = formatAmount(enteredAmount, currencyType);
					((AccountModel)getModel()).withdraw(formattedAmount, viewId);
				}
				catch(Exception e){
					e.printStackTrace();
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
	
	// Used by the view to get modified currency in Euros or Yuan
	public String getCurrency(double amount, String currencyType){
		DecimalFormat df = new DecimalFormat("#.00");
		if(currencyType.equals("Euro")){
			amount = amount * AccountModel.EXCHANGE_EURO;
		}
		else if(currencyType.equals("Yuan")){
			amount = amount * AccountModel.EXCHANGE_YUAN;
		}
		return df.format(amount);
		
	}

}
