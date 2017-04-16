package simpleAccount.controller;

import simpleAccount.model.Model;
import simpleAccount.view.View;

/**
 * This interface to be used by any controllers who wish to implement the MVC
 * architectural style.
 * @author Alexander Wagstaff
 *
 */
public interface Controller {
	
	/**
	 * Sets the model for this controller object.
	 * @param model The model to be set
	 */
	void setModel(Model model);
	
	/**
	 * Gets the model for this controller object.
	 * @return The model
	 */
	Model getModel();
	
	/**
	 * Sets the view for this controller object.
	 * @param view The view to be set
	 */
	void setView(View view);
	
	/**
	 * Gets the view for this controller object.
	 * @return The view
	 */
	View getView();

}
