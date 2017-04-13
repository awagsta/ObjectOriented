package simpleAccount.model;
import java.awt.event.ActionEvent;


/**
 * The ModelEvent class represents an event in the account system.
 * it is used along with notify changed to inform interested objects
 *  of a change to the model.
 * @author Alexander Wagstaff
 */
public class ModelEvent extends ActionEvent {
	
	private static final long serialVersionUID = 3284854567788980565L;
	private double amount;
	
	/**
	 * Constructor used to initialize the model event. Uses ActionEvent Parameters
	 * as well as an additional parameter amount.
	 * @param obj
	 * @param id
	 * @param message
	 * @param temp the amount changed in the corresponding account
	 */
	public ModelEvent(Object obj, int id, String message, double temp) {
		super(obj, id, message);
		this.amount = temp;
	}
	
	/**
	 * This function returns the changed amount in the account
	 * @return The amount currently in the account
	 */
	public double getAmount(){
		return amount;
	}
	
	/**
	 * This function returns the id of the account associated with the model event
	 * @return the id
	 */
	public int getId(){
		return id;
	}
}
