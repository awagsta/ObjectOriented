package simpleAccount.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import simpleAccount.controller.ModelController;
import simpleAccount.model.AccountModel;
import simpleAccount.model.ModelEvent;

/**
 * The AccountModelView class represents the primary view for an account controlling system.
 * It is responsible for displaying the list of accounts in the associated AccountModel object,
 * as well as receiving user gestures to pass to the associated ModelController object.
 * 
 * @author Alexander Wagstaff
 *
 */
public class AccountModelView extends JFrameView {


	private static final long serialVersionUID = -7411418821017564524L;
	public static final String SAVE = "Save";
	public static final String EXIT = "Exit";
	public static final String EDIT_EURO = "Edit in Euros";
	public static final String EDIT_USD = "Edit in USD";
	public static final String EDIT_YUAN = "Edit in Yuan";

	// A combo box to store string representations of accounts in the AccountModel
	private JComboBox<String> accountBox;
	
	/**
	 * Constructs an AccountModelView object that acts as the main view for the associated AccountModel.
	 * @param model The AccountModel associated with this view
	 * @param controller The ModelController associated with this view
	 */
	public AccountModelView(AccountModel model, ModelController controller){
		super(model, controller);
		setViewId(0);
		JFrame f = new JFrame("Account Program");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel CurrencyPanel = new JPanel();
		JPanel accountListPanel = new JPanel();
		JPanel selectionPanel = new JPanel();
		
		// Create handler objects for the buttons
		ButtonHandler  bHandler = new ButtonHandler();
		ComboBoxHandler cHandler = new ComboBoxHandler();
		String[] aList = controller.populateList();
		accountBox = new JComboBox<String>(aList);
		
		// Set the view ID to first account in the list
		String[] accountComponents = aList[0].split("\t");
		try{
			setViewId(Integer.parseInt(accountComponents[1]));
			}catch(NumberFormatException ex){
				// This should never be reached.
				System.out.println("Error: not a number for account ID");
			}
		
		// Create the buttons the user can interact with
		JButton saveButton = new JButton(SAVE);
		JButton exitButton = new JButton(EXIT);
		JButton euroButton = new JButton(EDIT_EURO);
		JButton dollarButton = new JButton(EDIT_USD);
		JButton yenButton = new JButton(EDIT_YUAN);
		
		// add the panels to the frame and the listeners to the buttons
		accountListPanel.add(accountBox);
		accountBox.addActionListener(cHandler);
		
		CurrencyPanel.add(euroButton);
		euroButton.addActionListener(bHandler);
		
		CurrencyPanel.add(dollarButton);
		dollarButton.addActionListener(bHandler);
		
		CurrencyPanel.add(yenButton);
		yenButton.addActionListener(bHandler);
		
		selectionPanel.add(saveButton);
		saveButton.addActionListener(bHandler);
		
		selectionPanel.add(exitButton);
		exitButton.addActionListener(bHandler);
		
		f.getContentPane().add(CurrencyPanel, "Center");
		f.getContentPane().add(accountListPanel, "North");
		f.getContentPane().add(selectionPanel, "South");
		f.pack();
		f.setVisible(true);
	}
	
	@Override
	public void ModelChanged(ModelEvent event) {
		// This view does not need to update, as accounts cannot be created past initialization and added
		// to the combo box representation of accounts.	
	}
	
	/**
	 * Inner class responsible for handling button events in the AccountModelView object.
	 * @author Alexander Wagstaff
	 *
	 */
	class ButtonHandler implements ActionListener{

		/**
		 * Method responsible for delegating button events to associated Model Controller.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			((ModelController)getController()).operation(e.getActionCommand(), getViewId()); 	
		}	
	}
	
	/**
	 * Inner class responsible for handling ComboBox events in the AccountModelView object.
	 * @author Alexander Wagstaff
	 *
	 */
	class ComboBoxHandler implements ActionListener{

		/**
		 * Method responsible for setting the viewI parameter to the accountId
		 * of the selected account in the ComboBox.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String accountInfo = accountBox.getSelectedItem().toString();
			String[] accountComponents = accountInfo.split("\t");
			try{
			setViewId(Integer.parseInt(accountComponents[1]));
			}catch(NumberFormatException ex){
				// This exception should never be reached.
				System.out.println("Error: not a number for account ID");
			}
			
		}	
	}

	public static void main(String[] args){
		String fileName = "/Users/alexanderwagstaff/Documents/workspace/simpleAccount/res/sampleAccountList.txt";
		new ModelController(fileName);
	}
}

	

