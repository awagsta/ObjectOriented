package simpleAccount.view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import simpleAccount.model.AccountModel;

public class ViewTest {
	public static final String SAVE = "Save";
	public static final String EXIT = "Exit";
	public static final String EDIT_EURO = "Edit in Euros";
	public static final String EDIT_USD = "Edit in USD";
	public static final String EDIT_YEN = "Edit in Yen";
	public static final String DEPOSIT = "Deposit";
	public static final String WITHDRAW = "Withdraw";
	public static final String DISMISS  = "Dismiss";

	public static void main(String[] args) {

		JFrame f = new JFrame("Account Program");
		String fileName = "/Users/alexanderwagstaff/Documents/workspace/simpleAccount/res/sampleAccountList.txt";
		AccountModel model = new AccountModel(fileName);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel CurrencyPanel = new JPanel();
		JPanel accountListPanel = new JPanel();
		JPanel selectionPanel = new JPanel();
		String[] aList = model.getAccountList();
		JComboBox<String> accountBox = new JComboBox<String>(aList);
		JButton saveButton = new JButton(SAVE);
		JButton exitButton = new JButton(EXIT);
		JButton euroButton = new JButton(EDIT_EURO);
		JButton dollarButton = new JButton(EDIT_USD);
		JButton yenButton = new JButton(EDIT_YEN);
		// Add the listeners later
		
		accountListPanel.add(accountBox);
		CurrencyPanel.add(euroButton);
		CurrencyPanel.add(dollarButton);
		CurrencyPanel.add(yenButton);
		selectionPanel.add(saveButton);
		selectionPanel.add(exitButton);
		
		f.getContentPane().add(CurrencyPanel, "Center");
		f.getContentPane().add(accountListPanel, "North");
		f.getContentPane().add(selectionPanel, "South");
		f.pack();
		f.setVisible(true);
	}

}
