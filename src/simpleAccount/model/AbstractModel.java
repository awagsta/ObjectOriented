package simpleAccount.model;
import java.util.ArrayList;

/**
 * AbstractModel class consists of the necessary event handling mechanisms for the model
 * in the MVC Architecture. Its purpose is to be extended by any class who wish
 * to implement event notification and listening functionality.
 * @author Alexander Wagstaff
 *
 */
public abstract class AbstractModel implements Model {
	private ArrayList<ModelListener> listeners = new ArrayList<ModelListener>();
	
	/**
	 * Add a model listener to the model
	 * @param listener the listener to be added to the model.
	 */
	public void addModelListener(ModelListener listener){
		listeners.add(listener);
	}
	
	/**
	 * Remove a model listener from the model.
	 * @param listener the listener to be removed from the model.
	 */
	public void removeModelListener(ModelListener listener){
		listeners.remove(listener);
	}
	
	public void notifyChanged(ModelEvent e)
	{
		for(ModelListener temp: listeners){
			temp.ModelChanged(e);
		}
	}

}
