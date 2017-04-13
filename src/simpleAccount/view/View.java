package simpleAccount.view;

import simpleAccount.controller.Controller;
import simpleAccount.model.Model;

/**
 * This interface to be used by views who wish to implement MVC architectural style
 * @author Alexander Wagstaff
 *
 */
public interface View {
	
	/**
	 * Returns the controller associated with this view object
	 * @return  The controller
	 */
	Controller getController();
	
	/**
	 * Sets the controller for this view object
	 * @param controller The controller to be set
	 */
	void setController(Controller controller);
	
	/**
	 * Returns the model associated with this view object
	 * @return The model
	 */
	Model getModel();
	
	/**
	 * Sets the model for this view object.
	 * @param model The model to be set
	 */
	void setModel(Model model);
}
