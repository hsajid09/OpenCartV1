package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.HomePage;
import PageObject.LoginPage;
import PageObject.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
 * Data is valid -- login successful test pass --valid --logout
 * Data is valid -- login unsuccessful test fail  --invalid
 * 
 * Data is invalid -- login successful test fail --valid --logout
 * Data isin valid -- login unsuccessful test pass  --
 */


public class TC003_LoginDDT extends BaseClass {
	

	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class)
	public void verify_loginDDT(String email, String pwd, String exp) {
		
		logger.info("*************Started **TC003_ LoginDDT******************");
		//Homepage 
		try {
		logger.info("*************Started **TC003_ LoginDDT******************");
		
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//login
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		//MyAccount
		
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountExists();
		
		if (exp.equalsIgnoreCase("valid")) {
			if(targetPage==true) {
				Assert.assertTrue(true);
				macc.clickLogout();
			}else {
				Assert.assertTrue(false);
			}
		}
		if(exp.equalsIgnoreCase("invalid")) {
			if(targetPage==true) {
				macc.clickLogout();
				Assert.assertTrue(false);
			}else {
				Assert.assertTrue(true);
			}
		}
		}catch(Exception e) {
			Assert.fail();
		}
				
		logger.info("*************Finished **TC003_ LoginDDT******************");
	}

}
