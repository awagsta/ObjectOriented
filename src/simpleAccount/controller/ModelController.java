package simpleAccount.controller;

import simpleAccount.model.AccountModel;
import simpleAccount.view.AccountModelView;

public class ModelController extends AbstractController {
	public ModelController(String fileName){
		setModel(new AccountModel(fileName));
		setView(new AccountModelView((AccountModel)getModel(), this));
		
	}
	
	// Method for saving, quitting, and creating account detail views
	public void operation(String option, int viewId){
		if(option.equals(AccountModelView.SAVE)){
			((AccountModel)getModel()).save();
		}
		else if(option.equals(AccountModelView.EXIT)){
			((AccountModel)getModel()).quit();
		}
		else if(option.equals(AccountModelView.EDIT_EURO)){
			String currencyType = "Euro";
			new AccountController((AccountModel)getModel(), viewId, currencyType);
			
		}
		else if(option.equals(AccountModelView.EDIT_YUAN)){
			String currencyType = "Yuan";
			new AccountController((AccountModel)getModel(), viewId, currencyType);
		}
		else {
			String currencyType = "$";
			new AccountController((AccountModel)getModel(), viewId, currencyType);
		}
			
	}
	
	public String[] populateList(){
		return ((AccountModel)getModel()).getAccountList();
	}

}
