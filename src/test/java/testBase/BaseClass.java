package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public static WebDriver driver;
	public  Logger logger;
	public Properties prop;

	@BeforeClass(groups= {"Sanity", "Regression", "Master", "DataDriven"})
	@Parameters({"os", "browser"})
	public void setup(String os, String br) throws IOException, InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		FileReader file=new FileReader("C:\\Users\\hsaji\\Eclipse_Selenium\\OpenCartV1\\src\\test\\resources\\config.properties");
		prop=new Properties();
		prop.load(file);
		
		logger=LogManager.getLogger(this.getClass());
		
		if(prop.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities dc=new DesiredCapabilities(); 
			//os
			if(os.equalsIgnoreCase("windows")) {
				dc.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("linux")) {
				dc.setPlatform(Platform.LINUX);
			}
			else if(os.equalsIgnoreCase("mac")) {
				dc.setPlatform(Platform.MAC);
			}else {
				System.out.println("No matching found...");
				return;
			}
			//browser
			switch(br.toLowerCase()) {
			case "chrome":dc.setBrowserName("chrome");break;
			case "edge":dc.setBrowserName("MicrosoftEdge");break;
			case "firefox":dc.setBrowserName("firefox");break;
			default:System.out.println("Invalid browser name");return;
			}
			driver=new RemoteWebDriver(new URL("http://10.0.0.181:4444/wd/hub"), dc);
		}
		
		if(prop.getProperty("execution_env").equalsIgnoreCase("local")) {
			
			switch(br.toLowerCase()) {
			case"chrome":driver = new ChromeDriver();break;
			case"edge":driver = new EdgeDriver();break;
			case"firefox":driver = new FirefoxDriver();break;
			default: System.out.println("Invalid Browser Selection...");return;
			}
			
		}
		
		// driver=new FirefoxDriver();
		// driver=new EdgeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(prop.getProperty("appURL"));
		driver.manage().window().maximize();

	}

	@AfterClass(groups= {"Sanity", "Regression", "Master", "DataDriven"})
	public void tearDown() {

		driver.quit();

	}
	
	public String randomAlphaNumeric() {

		String generatedString = RandomStringUtils.randomAlphabetic(3);
		String generatedNumber = RandomStringUtils.randomNumeric(3);

		return (generatedString+generatedNumber);
	}

	public String randomString() {

		String generatedString = RandomStringUtils.randomAlphabetic(5);

		return generatedString;
	}

	public String randomNumbers(){

		String generatedNumber = RandomStringUtils.randomNumeric(10);

		return generatedNumber;
	}

	public String captureScreen(String tname) {
		
		String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takeScreenshot=(TakesScreenshot)driver;
		File sourceFile=takeScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"-"+timeStamp+".png";
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
		
	}
	

}


