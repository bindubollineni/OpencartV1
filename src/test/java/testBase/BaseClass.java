package testBase;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

//not a testng class , it normal class
public class BaseClass {


	public WebDriver driver;
	public Logger logger; //For Logging
	//Resource bundle for adding config properties file

	public ResourceBundle rd;// to read config.properties file

	@BeforeClass(groups= {"master","sanity","regression"})
	@Parameters({"browser"})
	public void setUp(String br)
	{
		//Load config.properties file
		rd=ResourceBundle.getBundle("config");

		//Logging
		logger = LogManager.getLogger(this.getClass());
		//No "BaseClass", get the class dynamically

		if(br.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			logger.info("Launched Chrome Browser");
		}
		else if(br.equals("edge"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			logger.info("Launched Chrome Browser");
		}
		else if(br.equals("firefox"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			logger.info("Launched Chrome Browser");


		}


	}
	@AfterClass(groups= {"master","sanity","regression"})
	public void tearDown()
	{
		driver.quit();
	}


	public void captureScreen(WebDriver driver, String tname) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty
				("user.dir")+"/screenshots/"+tname+".png");
		FileUtils.copyFile(source, target);
	}
	public String randomString()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return (generatedString);
	}

	//To generate random number for OTP or mobile numbers
	public int randomNumber()
	{

		String generatedString2= RandomStringUtils.randomNumeric(7);
		//generate in String format , convert it into integer Parse int
		return(Integer.parseInt(generatedString2));
	}




}
