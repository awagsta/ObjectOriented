package simpleAccount.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AccountModelTest {
AccountModel modelTest;
String fileName;

	@Before
	public void initialize(){
		fileName = "/Users/alexanderwagstaff/Documents/workspace/simpleAccount/res/sampleAccountList.txt";
		modelTest = new AccountModel(fileName);
	}

	@Test
	public void testDeposit() {
		double initialAmount = 0.0;
		double postDeposit = 0.0;
		int accountId = 123456;
		try {
			initialAmount = modelTest.getAccount(accountId).getAmount();
			modelTest.deposit(100, accountId);
			postDeposit = modelTest.getAccount(accountId).getAmount();
		} catch (InvalidAmountException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		modelTest.save();
		assertFalse(initialAmount == postDeposit);
	}
	
	

}
