package org.runner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

@RunWith(Cucumber.class)
@CucumberOptions (features = "src\\test\\java\\FeatureFile\\Login.feature" ,glue = "org.stepdef",monochrome=true,

plugin = {
		
		"pretty",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
		"html:Report/htmlReport.html",
		"json:Report/jsonReport.json",
		"junit:Report/junitReport.xml"
		
}
//C:\Users\sivar\.m2\repository\com\aventstack\extentreports\5.0.8\extentreports-5.0.8.jar
)
 

public class RunnerClass {

	public static WebDriver driver = null;
	
	@BeforeClass
	public static void setup() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	@AfterClass  
	public static void tearDown() {
		
		driver.quit();

	}
	
}
