package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import PageObject.AccountRegistrationPage;
import PageObject.HomePage;
import testBase.BaseClass;


public class TC001_AccountRegistrationTest extends BaseClass{

	

	@Test(groups= {"Regression", "Master"})
	public void verify_account_registration() throws InterruptedException {
		
		logger.info("***************Started **TC001_Accounting_Registration Test******************");

		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("************TC001_Accounting_Registration***Clicked on Account****************");
		hp.clickRegister();
		logger.info("**********TC001_Accounting_Registration***Clicked on Registration link***************");

		Thread.sleep(3000);

		AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
		logger.info("**********TC001_Accounting_Registration***Providing Registration Details****************");
		regPage.setFirstName(randomString().toUpperCase());
		regPage.setLastName(randomString().toUpperCase());
		regPage.setEmail(randomString() + "i@gmail.com");
		regPage.setTelephone(randomNumbers());

		String password=randomAlphaNumeric();
		regPage.setPassword(password);
		regPage.setConfirmPassword(password);
		regPage.setPrivacyPolicy();
		regPage.clickContinue();
		
		logger.info("*************************Validating epected message**************************");

		String confMsg = regPage.getConfirmationMsg();
		if(confMsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}else {
			logger.error("*******************************Test Failed**************************");
			logger.debug("*****************************Debug logs**************************");
			Assert.assertTrue(false);
			
			
		}

		//Assert.assertEquals(confMsg, "Your Account Has Been Created!");
		}catch(Exception e) {
			
			Assert.fail();
		}
		logger.info("***********TC001_Accounting_Registration***Finished********************");
	}	
}
