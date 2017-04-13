package simpleAccount.controller;

import simpleAccount.model.Model;
import simpleAccount.view.View;

/**
 * AbstractController class consists of necessary methods to implement basic controller
 * functionality in the MVC architecture. Its purpose to be extended by any controller
 * that wishes to include such functionality
 * @author Alexander Wagstaff
 *
 */
public abstract class AbstractController implements Controller {
	private View view;
	private Model model;
	
	public void setModel(Model model){
		this.model = model;
	}
	
	public Model getModel(){
		return model;
	}
	
	public void setView(View view){
		this.view = view;
	}
	
	public View getView(){
		return view;
	}
}
