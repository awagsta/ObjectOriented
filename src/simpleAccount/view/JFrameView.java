package simpleAccount.view;

import javax.swing.JFrame;

import simpleAccount.controller.Controller;
import simpleAccount.model.AbstractModel;
import simpleAccount.model.Model;
import simpleAccount.model.ModelListener;

public abstract class JFrameView extends JFrame implements View, ModelListener {

	private static final long serialVersionUID = 1991725435796181195L;
	private Model model;
	private Controller controller;
	private int viewId;
	
	public JFrameView(Model model, Controller controller){
		setModel(model);
		setController(controller);
		viewId = 0;
	}
	
	public void setController(Controller controller){
		this.controller = controller;
	}
	
	public void registerWithModel(){
		((AbstractModel)model).addModelListener(this);
	}
	
	public void setModel(Model model){
		this.model = model;
		registerWithModel();
	}
	
	public Model getModel(){
		return model;
	}
	
	public Controller getController(){
		return controller;
	}

	public int getViewId() {
		return viewId;
	}

	public void setViewId(int viewId) {
		this.viewId = viewId;
	}

}
