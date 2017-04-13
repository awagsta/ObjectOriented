package simpleAccount.model;

/**
 * This interface is to be used by any objects interested in
 * listening for model change event notifications. 
 * @author Alexander Wagstaff
 *
 */
public interface ModelListener {
	
	/**
	 * Used by objects interested in model change notifications
	 * to affect some implementation specific change in those objects
	 * @param event The event representing a change in the model object.
	 */
	public void ModelChanged(ModelEvent event);
}
