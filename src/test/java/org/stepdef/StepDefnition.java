package org.stepdef;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.runner.RunnerClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefnition {
	
	public static WebDriver driver = RunnerClass.driver;
	public static String a = "Music";
	public static String b = "cd";
	public static String firstproducttext = "";
	public static String selectproducttext = "";
	
	@Given("user launch the Url")
	public void user_launch_the_url() {
	    driver.get("https://www.amazon.in/");
	}

	@When("user select the {string} from dropodown")
	public void user_select_the__from_dropodown(String string) {
		WebElement dropdown = driver.findElement(By.id("searchDropdownBox"));
		Select s = new Select (dropdown); //Parameterized constructor
		List<WebElement> option =s.getOptions();
		ListIterator<WebElement> li = option.listIterator();

		while(li.hasNext()) {
			WebElement next = li.next();
			String actual = next.getText();
			
			if(string.equalsIgnoreCase(actual)) {
				s.selectByVisibleText(string);
				break;
			 }
		  }
	    
	}

	@When("user enter {string} in search box")
	public void user_enter__in_search_box(String string1) throws InterruptedException {
		
		WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
		search.sendKeys(string1);
		Thread.sleep(2000);
		
		List<WebElement> searchOptions =driver.findElements(By.xpath("//div[@class='autocomplete-results-container']"));
		
		for (int i = 1; i<= searchOptions.size(); i++) {
			WebElement each = driver.findElement(By.xpath("//div[@class='autocomplete-results-container']/div["+i+"]"));
	       String eachText = each.getText();
	       
	       if(string1.equalsIgnoreCase(eachText)) {
	    	   each.click();
	    	   break;
	          }   
	       }
	    
	}
	@When("select the first product")
	public void select_the_first_product() {
		WebElement firstproduct = driver.findElement(By.xpath("//span[text()='RESULTS']/ancestor::div[4]/following-sibling::div//h2"));
		firstproducttext = firstproduct.getText();
		System.out.println(firstproducttext);
		WebElement firstproduct1 = driver.findElement(By.xpath("//span[text()='RESULTS']/ancestor::div[4]/following-sibling::div//h2/a"));
        firstproduct1.click();
        
        //windowhandle
      String hometab = driver.getWindowHandle();
      Set<String>multipletab=driver.getWindowHandles();
      for (String nexttab:multipletab) {
    	  if(!hometab.equals(nexttab)) {
    		  driver.switchTo().window(nexttab);
    		  break;
    	  }
    	  System.out.println("selectproducttext");
      }
	}

	@When("check the selected product text with first product")
	public void check_the_selected_product_text_with_first_product() throws InterruptedException {
		
		if(firstproducttext.equals(selectproducttext)) {
			WebElement addtocart = driver.findElement(By.id("add-to-cart-button"));
			addtocart.click();
			Thread.sleep(5000);
		}
	  
	}

	@Then("add the product to the cart")
	public void add_the_product_to_the_cart() throws InterruptedException {
	
			WebElement selectproduct = driver.findElement(By.id("productTitle"));
			String selectproducttext = selectproduct.getText();
			System.out.println(selectproducttext);
			
			if(firstproducttext.equals(selectproducttext)) {
				WebElement addtocart = driver.findElement(By.id("add-to-cart-button"));
				addtocart.click();
				Thread.sleep(5000);
			}
		}
	

	@Then("Take Screenshot")
	public void take_screenshot() throws IOException {
		
		 TakesScreenshot amazon = ( TakesScreenshot)driver;
		 File source = amazon.getScreenshotAs(OutputType.FILE);
		 File target = new File("C:\\Users\\sivar\\eclipse-workspace\\MiniProject\\target\\screenshot.amazon.png");
		 org.openqa.selenium.io.FileHandler.copy(source, target);
		 
	   	}

	@Then("go to cart")
	public void go_to_cart() {
		
		 WebElement gotocart = driver.findElement(By.xpath("//span[@id='sw-gtc']/child::span//a"));
		  gotocart.click();
	}
	

	@When("check selectedproducttext with shoppingproductcarttext")
	public void check_selectedproducttext_with_shoppingproductcarttext() {
	     
		 WebElement shoppingcart = driver.findElement(By.xpath("//li[@class='a-spacing-mini']/child::span"));
		 
		  String shoppingcarttext = shoppingcart.getText();
		  System.out.println(shoppingcarttext);
		  if(selectproducttext.contains(shoppingcarttext)) {
			  driver.close();
		  }
	}


	
}
