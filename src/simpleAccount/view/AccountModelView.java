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

public class AccountModelView extends JFrameView {


	private static final long serialVersionUID = -7411418821017564524L;
	// The currently selected account's id this view is associated with
	
	public static final String SAVE = "Save";
	public static final String EXIT = "Exit";
	public static final String EDIT_EURO = "Edit in Euros";
	public static final String EDIT_USD = "Edit in USD";
	public static final String EDIT_YUAN = "Edit in Yuan";

	private JComboBox<String> accountBox;
	
	public AccountModelView(AccountModel model, ModelController controller){
		super(model, controller);
		setViewId(0);
		JFrame f = new JFrame("Account Program");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel CurrencyPanel = new JPanel();
		JPanel accountListPanel = new JPanel();
		JPanel selectionPanel = new JPanel();
		ButtonHandler  bHandler = new ButtonHandler();
		ComboBoxHandler cHandler = new ComboBoxHandler();
		// TODO get account list from controller, not model
		String[] aList = controller.populateList();
		accountBox = new JComboBox<String>(aList);
		
		// Set the view ID to first account in the list
		String[] accountComponents = aList[0].split("\t");
		try{
			setViewId(Integer.parseInt(accountComponents[1]));
			}catch(NumberFormatException ex){
				System.out.println("Error: not a number for account ID");
				// Replace with JDialog later
			}
		
		JButton saveButton = new JButton(SAVE);
		JButton exitButton = new JButton(EXIT);
		JButton euroButton = new JButton(EDIT_EURO);
		JButton dollarButton = new JButton(EDIT_USD);
		JButton yenButton = new JButton(EDIT_YUAN);
		
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
	
	class ButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Implement Controller class and uncomment this code.
			((ModelController)getController()).operation(e.getActionCommand(), getViewId()); 	
		}	
	}
	
	class ComboBoxHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String accountInfo = accountBox.getSelectedItem().toString();
			String[] accountComponents = accountInfo.split("\t");
			try{
			setViewId(Integer.parseInt(accountComponents[1]));
			}catch(NumberFormatException ex){
				System.out.println("Error: not a number for account ID");
				// Replace with JDialog later
			}
			
		}	
	}

	public static void main(String[] args){
		String fileName = "/Users/alexanderwagstaff/Documents/workspace/simpleAccount/res/sampleAccountList.txt";
		new ModelController(fileName);
	}
}

	

