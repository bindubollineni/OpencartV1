package testCases;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.XLUtility;

public class TC_003_LoginDDT extends BaseClass{

	@Test(dataProvider="LoginData")
	public void test_LoginDDT(String email,String pwd,String exp)
	{
		logger.info("Starting TC_003_DDT");
		try
		{
			driver.get(rd.getString("appURL"));
			logger.info("Home Page Displayed");
			driver.manage().window().maximize();

			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked My Account");

			hp.clickLogin();
			logger.info("Clicked Login Button");

			LoginPage lp = new LoginPage(driver);
			lp.setEmailAddress(email);
			logger.info("Provided email address");

			lp.setPwd(pwd);
			logger.info("Provided password");

			lp.clickBtnLogin();
			logger.info("Clicked Login Button");

			boolean targetpage=lp.isMyAccountPageExists();
			if(exp.equals("Valid"))
			{
				if(targetpage)
				{
					logger.info("Login Sucess..");
					hp.clickMyAccount();
					MyAccountPage myaccpage = new MyAccountPage(driver);
					myaccpage.clickLogout();
					logger.info("Clicked Logout Page");
					Assert.assertTrue(true);
				}
				else
				{
					logger.error("Login Failed");
					Assert.assertTrue(false);

				}
			}

			if(exp.equals("Invalid"))
			{
				//pass invalid data, target page is displayed
				// that is a bug. we need to click logout
				if(targetpage)
				{
					hp.clickMyAccount();
					MyAccountPage myaccpage= new MyAccountPage(driver);
					myaccpage.clickLogout();
					Assert.assertTrue(false);
				}
				else// else part covers negative test
				{
					logger.error("Login Failed");
					Assert.assertTrue(true);
					//when we pass invalid data the target page is not displayed so it is passed
				}
			}
		}
		catch(Exception e)
		{
			logger.fatal("Login Failed");
			Assert.fail();
		}


	}

@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{

		String path="./testData/Opencart_LoginData.xlsx";

		XLUtility xlutil = new XLUtility(path);

		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1",1);

		String logindata[][]= new String[totalrows][totalcols];

		for(int i=1;i<=totalrows;i++)
		{
			for(int j=0;j<totalcols;j++)
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);
			}
		}
		return logindata;


	}





}
