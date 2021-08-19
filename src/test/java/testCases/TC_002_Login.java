package testCases;
//This test is created by me
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC_002_Login  extends BaseClass{
	@Test(groups={"sanity","master"})
	public void test_Login()
	{

		logger.info("Starting TC_002_Login");
		try
		{
			driver.get(rd.getString("appURL"));
			logger.info("HomePage displayed");

			driver.manage().window().maximize();
			HomePage hp= new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on My Account");
			hp.clickLogin();
			logger.info("Clicked Login");

			LoginPage lp = new LoginPage(driver);

			lp.setEmailAddress(rd.getString("email"));
			logger.info("Provided Email Address");

			lp.setPwd(rd.getString("password"));
			logger.info("Provided password");

			lp.clickBtnLogin();
			logger.info("Clicked Login Button");

			boolean targetpage = lp.isMyAccountPageExists();
			if(targetpage)
			{
				logger.info("Login Sucess");
				Assert.assertTrue(true);
			}
			else
			{
				logger.error("Login Failed");
				captureScreen(driver,"test_Login");//capturing Screenshot
				Assert.assertTrue(false);
			}
		}
		catch(Exception e)
		{
			logger.fatal("Login Failed");
			Assert.fail();
		}
		logger.info("Finished TC_002_Login");

	}

}
