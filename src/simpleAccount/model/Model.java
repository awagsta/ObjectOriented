package simpleAccount.model;

/**
 * This Interface to be used by models to implement change notifications
 * @author Alexander Wagstaff
 *
 */
public interface Model {
	/**
	 * Notifies interested objects of changes to the model.
	 * @param e The event representing a change
	 */
	void notifyChanged(ModelEvent e);
}
