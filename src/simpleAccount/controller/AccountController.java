package simpleAccount.controller;

import java.text.DecimalFormat;

import simpleAccount.model.AbstractModel;
import simpleAccount.model.Account;
import simpleAccount.model.AccountModel;
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
	
	// TODO flesh out the catch blocks to be more specific and generate Jdialogs
	public void operation(String option, int viewId, String enteredAmount, String currencyType){
			if(option.equals(AccountDetailView.DEPOSIT)){
				try{
					double formattedAmount = formatAmount(enteredAmount, currencyType);
					((AccountModel)getModel()).deposit(formattedAmount, viewId);
				}
				catch(Exception e){
					e.printStackTrace();
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
	
	private double formatAmount(String amount, String currencyType) throws NumberFormatException{
		DecimalFormat df = new DecimalFormat("#.00");
		if(currencyType.equals("Euro")){
			double convertedAmount = Double.parseDouble(amount) / AccountModel.EXCHANGE_EURO;
			return Double.parseDouble(df.format(convertedAmount));
		}
		else if(currencyType.equals("Yuan")){
			double convertedAmount = Double.parseDouble(amount) / AccountModel.EXCHANGE_YUAN;
			return Double.parseDouble(df.format(convertedAmount));
		}
		else{
			double convertedAmount = Double.parseDouble(amount);
			return Double.parseDouble(df.format(convertedAmount));
		}
			
	}

}
