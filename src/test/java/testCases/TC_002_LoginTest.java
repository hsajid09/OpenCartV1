package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.HomePage;
import PageObject.LoginPage;
import PageObject.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {
	
	@Test(groups= {"Sanity", "Master"})
	public void verify_login() {
		
		logger.info("*************Started **TC001_Accounting_Registration Test******************");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("**********************click My account link******************************");
		hp.clickLogin();
		logger.info("************************click on Login link******************************");
		
		
		LoginPage lp=new LoginPage(driver);
		logger.info("************************set username****************************");
		lp.setEmail(prop.getProperty("email"));
		logger.info("************************set password****************************");
		lp.setPassword(prop.getProperty("password"));
		logger.info("************************Click Login btn****************************");
		lp.clickLogin();
		
		
		logger.info("************************Loging confirmation****************************");
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountExists();
		
		logger.info("************************Loging confirmation****************************");
		Assert.assertEquals(targetPage, true);
		}catch(Exception e) {
			Assert.fail();
			logger.error("****************************Test Failed************************");
			logger.debug("************************Debug logs***************************");
		}
		logger.info("************************TC002 LoginTest Finished****************************");
	}
	

}
