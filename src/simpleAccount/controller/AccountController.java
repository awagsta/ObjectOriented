package simpleAccount.controller;

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
	
	// TODO add operation method for this class

}
