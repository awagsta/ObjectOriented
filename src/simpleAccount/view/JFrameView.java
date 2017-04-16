package simpleAccount.view;

import javax.swing.JFrame;

import simpleAccount.controller.Controller;
import simpleAccount.model.AbstractModel;
import simpleAccount.model.Model;
import simpleAccount.model.ModelListener;

/**
 * Abstract class JFrameView consists of the necessary methods to implement view functionality
 * in the MVC architecture. Its purpose is to be extended by any class that wishes
 * to implement such functionality.
 * @author Alexander Wagstaff
 *
 */
public abstract class JFrameView extends JFrame implements View, ModelListener {

	private static final long serialVersionUID = 1991725435796181195L;
	private Model model;
	private Controller controller;
	private int viewId;
	
	/**
	 * Constructs a JFrameView object with the specified model and controller objects.
	 * @param model The model to be set to
	 * @param controller The controller to be set to
	 */
	public JFrameView(Model model, Controller controller){
		setModel(model);
		setController(controller);
		viewId = 0;
	}
	
	/**
	 * This method sets the controller for this view.
	 */
	public void setController(Controller controller){
		this.controller = controller;
	}
	
	/**
	 * This method registers the view with the model to receive model updates.
	 */
	public void registerWithModel(){
		((AbstractModel)model).addModelListener(this);
	}
	
	/**
	 * This method sets the model for this view.
	 */
	public void setModel(Model model){
		this.model = model;
		registerWithModel();
	}
	
	/**
	 * This method gets the model for this view.
	 */
	public Model getModel(){
		return model;
	}
	
	/**
	 * This method gets the controller for this view.
	 */
	public Controller getController(){
		return controller;
	}

	/**
	 * This method gets the id of the view.
	 * @return The id
	 */
	public int getViewId() {
		return viewId;
	}

	/**
	 * This method sets the id of the view
	 * @param viewId The viewId to be set
	 */
	public void setViewId(int viewId) {
		this.viewId = viewId;
	}

}
