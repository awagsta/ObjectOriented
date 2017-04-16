package simpleAccount.controller;

import simpleAccount.model.AccountModel;
import simpleAccount.view.AccountModelView;

/**
 * The ModelController class represents the primary controller for an account controlling system. 
 * It acts as an interface between the AccountModelView class and the AccountModel class. This allows
 * the AccountModelView to display the list of accounts, as well as perform account selection and save operations.
 * @author Alexander Wagstaff
 *
 */
public class ModelController extends AbstractController {
	
	/**
	 * Constructs a ModelController object responsible for initializing the account controlling system.
	 * @param fileName The file containing a list of accounts to populate the AccountModel
	 */
	public ModelController(String fileName){
		setModel(new AccountModel(fileName));
		setView(new AccountModelView((AccountModel)getModel(), this));
		
	}
	
	/**
	 * Executes desired operations selected from the AccountModelView on the AccountModel.
	 * @param option The desired operation selected in the AccountModelView
	 * @param viewId The id of the specified account the user selected in the AccountModelView
	 */
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
	
	/**
	 * Populates an Instance of AccountModelView with the list of accounts in the AccountModel.
	 * @return A string representation of the account list
	 */
	public String[] populateList(){
		return ((AccountModel)getModel()).getAccountList();
	}

}
