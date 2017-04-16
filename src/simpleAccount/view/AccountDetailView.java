package simpleAccount.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import simpleAccount.controller.AccountController;
import simpleAccount.model.Model;
import simpleAccount.model.ModelEvent;

/**
 * The AccountDetailView class represents a view associated with a specific account in the associated
 * AccountModel object. It is responsible for displaying information associated with that account,
 * and receiving user input to delegate to the associated AccountController object.
 * @author Alexander Wagstaff
 *
 */
public class AccountDetailView extends JFrameView {
	
	private static final long serialVersionUID = -7436826659662915L;
	public static final String DEPOSIT = "Deposit";
	public static final String WITHDRAW = "Withdraw";
	private JTextField displayAmount;
	private JTextField enterAmount;
	private String currencyType;

	/**
	 * Constructs an AccountDetailView object using the specified parameters.
	 * @param model The AccountModel associated with this view
	 * @param controller The AccountController associated with this view
	 * @param viewId The Id of the account this view will represent
	 * @param accountInfo The String Representation of the associated account
	 * @param currencyType The type of currency this view will display and allow operations in
	 * @param amount The amount in the associated account.
	 */
	public AccountDetailView(Model model, AccountController controller, int viewId, String accountInfo, String currencyType, String amount) {
		super(model, controller);
		this.setViewId(viewId);
		JFrame f = new JFrame(accountInfo);
		f.addWindowListener(new WindowListener());
		this.currencyType = currencyType;
		
		// Create the fund display panel and associated widgets
		JPanel fundsPanel = new JPanel();
		JLabel fundAmount = new JLabel("Available funds in" + " " + currencyType);
		displayAmount = new JTextField(amount, 10);
		displayAmount.setEditable(false);
		
		// Create the edit amount panel and associated widgets
		JPanel editAmtPanel = new JPanel();
		JLabel changeAmount = new JLabel("Enter amount in" + " " + currencyType);
		enterAmount = new JTextField(10);
		
		// Create the button panel and associated widgets
		JPanel buttonPanel = new JPanel();
		JButton depositButton = new JButton(DEPOSIT);
		JButton withdrawButton = new JButton(WITHDRAW);
		
		// Create a handler for button objects
		ButtonHandler bHandler = new ButtonHandler();
		
		fundsPanel.add(fundAmount);
		fundsPanel.add(displayAmount);
		
		editAmtPanel.add(changeAmount);
		editAmtPanel.add(enterAmount);
		
		buttonPanel.add(depositButton);
		depositButton.addActionListener(bHandler);
		
		buttonPanel.add(withdrawButton);
		withdrawButton.addActionListener(bHandler);
		
		f.add(fundsPanel, "North");
		f.add(editAmtPanel, "Center");
		f.add(buttonPanel, "South");
		f.pack();
		f.setVisible(true);

	}
	
	/**
	 * This method is used by the controller to create error messages for the user.
	 * @param errorMessage The Message associated with the error
	 * @param errorTitle The title of the Error Message
	 */
	public void displayErrorMessage(String errorMessage, String errorTitle){
		JOptionPane.showMessageDialog(this, errorMessage, errorTitle, JOptionPane.ERROR_MESSAGE);
	}
	
	
	/**
	 * This method updates the view when a change to the underlying account has been made.
	 */
	@Override
	public void ModelChanged(ModelEvent event) {
		if(getViewId() == event.getId()){
				String convertedAmount = ((AccountController)getController()).getCurrency(event.getAmount(), currencyType);
				displayAmount.setText(convertedAmount);
		}
		
	}

	/**
	 * Inner Class responsible for handling button events in the AccountDetailView object.
	 * @author Alexander Wagstaff
	 *
	 */
	class ButtonHandler implements ActionListener{

		/**
		 * Method responsible for delegating button events to the associated AccountController object.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			((AccountController)getController()).operation(e.getActionCommand(), getViewId(), enterAmount.getText(), currencyType);
			enterAmount.setText("");
		}
		
	}
	
	/**
	 * Inner class responsible for handling window closing events in the AccountDetailView object.
	 * @author Alexander Wagstaff
	 *
	 */
	class WindowListener extends WindowAdapter{
		
		/**
		 * Method responsible handling window closure events in the AccountDetailView object.
		 */
		public void windowClosing(WindowEvent e){
			JFrame frame = (JFrame) e.getSource();
			frame.dispose();
			
		}
	}



}
