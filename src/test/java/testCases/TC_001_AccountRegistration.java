package testCases;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistration extends BaseClass{
	//Test case will have only test methods,
	//Always Run TestCase not BaseClass
	@Test (groups= {"regression","master"})

	//if we want run individually this method we will click Run
	public void test_account_Registration()
	{
		// the driver comes from baseclass, make it public there
		logger.info("Starting TC_001_AccountRegistration");

		try
		{
			driver.get(rd.getString("appURL"));
			logger.info("HomePage displayed");

			driver.manage().window().maximize();
			HomePage hp= new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on My Account");

			hp.clickRegister();
			logger.info("Clicked on Register");

			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
			regpage.setFirstName("john");
			logger.info("Provided FirstName");

			regpage.setLastName("Cadeny");
			logger.info("Provided LastName");

			regpage.setEmail(randomString()+"@gmail.com");
			logger.info("Set Email");

			regpage.setTelephone("1234567");
			logger.info("Set Telephone Number");

			regpage.setPassword("abc1234");
			logger.info("Set Password");

			regpage.setConfirmPassword("abc1234");
			logger.info("Set Confirm Password");

			regpage.clickCheckedPolicy();
			logger.info("Clicked checked policy");

			regpage.clickContinue();
			logger.info("Click continue");

			String confmsg = regpage.getConfirmationMsg();
			if(confmsg.equals("Your Account Has Been Created!"))
			{
				logger.info("Account Registration Success");

				AssertJUnit.assertTrue(true);
			}
			else
			{
				//make it failed to capture screen shot
				captureScreen(driver,"test_account_Registration");
				logger.info("Account Registration Failed");

				AssertJUnit.assertTrue(false);
			}
		}
		catch(Exception e)
		{
			logger.fatal("Account Registration Failed");
			AssertJUnit.fail();//Forcefully fail
		}

		logger.info("Finished Test Case");
	}



}
