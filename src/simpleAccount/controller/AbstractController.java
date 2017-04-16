package simpleAccount.controller;

import simpleAccount.model.Model;
import simpleAccount.view.View;

/**
 * AbstractController class consists of necessary methods to implement basic controller
 * functionality in the MVC architecture. Its purpose to be extended by any class
 * that wishes to include such functionality
 * @author Alexander Wagstaff
 *
 */
public abstract class AbstractController implements Controller {
	private View view;
	private Model model;
	
	/**
	 * This method sets the model for the controller.
	 */
	public void setModel(Model model){
		this.model = model;
	}
	
	/**
	 * This method returns the model associated with the controller.
	 */
	public Model getModel(){
		return model;
	}
	
	/**
	 * This method sets the view for the controller.
	 */
	public void setView(View view){
		this.view = view;
	}
	
	/**
	 *This method returns the view associated with the controller.
	 */
	public View getView(){
		return view;
	}
}
