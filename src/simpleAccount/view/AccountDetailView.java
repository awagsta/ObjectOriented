package simpleAccount.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import simpleAccount.controller.AccountController;
import simpleAccount.model.Model;
import simpleAccount.model.ModelEvent;

public class AccountDetailView extends JFrameView {
	
	private static final long serialVersionUID = -7436826659662915L;
	public static final String DEPOSIT = "Deposit";
	public static final String WITHDRAW = "Withdraw";
	private JTextField displayAmount;
	private JTextField enterAmount;
	private String currencyType;

	public AccountDetailView(Model model, AccountController controller, int viewId, String accountInfo, String currencyType, String amount) {
		super(model, controller);
		this.setViewId(viewId);
		JFrame f = new JFrame(accountInfo);
		f.addWindowListener(new WindowListener());
		this.currencyType = currencyType;
		
		// Create the fund display panel and associated widgets
		JPanel fundsPanel = new JPanel();
		JLabel fundAmount = new JLabel("Available funds in" + " " + currencyType);
		displayAmount = new JTextField(10);
		displayAmount.setText(amount);
		
		// Create the edit amount panel and associated widgets
		JPanel editAmtPanel = new JPanel();
		JLabel changeAmount = new JLabel("Enter amount in" + " " + currencyType);
		enterAmount = new JTextField(10);
		
		// Create the button panel and associated widgets
		JPanel buttonPanel = new JPanel();
		JButton depositButton = new JButton(DEPOSIT);
		JButton withdrawButton = new JButton(WITHDRAW);
		
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
	
	
	@Override
	public void ModelChanged(ModelEvent event) {
		if(getViewId() == event.getId()){
			displayAmount.setText(event.getAmount() + "$");
			// Add logic to do the conversion
		}
		
	}

	class ButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			((AccountController)getController()).operation(e.getActionCommand(), getViewId(), enterAmount.getText(), currencyType);
			
		}
		
	}
	
	class WindowListener extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			JFrame frame = (JFrame) e.getSource();
			frame.dispose();
			
		}
	}



}
