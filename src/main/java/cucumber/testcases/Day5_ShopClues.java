package cucumber.testcases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.collections4.list.TreeList;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Day5_ShopClues {

	ChromeDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	Actions action;
	List<WebElement> blackShoeList = new ArrayList<WebElement>();
	Map<String, Integer> shoeMap = new TreeMap<String, Integer>();
	List<Integer> shoePriceMoreThan = new ArrayList<Integer>();
	List<Integer> allShoePrice = new ArrayList<Integer>();

	@Given("Go to shopclues website")
	public void go_to_shopclues_website() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
		System.out.println("Browser Launched");
		driver.manage().window().maximize();
		System.out.println("Browser Maximized");
		driver.get("https://www.shopclues.com");
		System.out.println("URL launched");

	}

	@And("Mouseover on women and click Casual Shoes")
	public void mouseover_on_women_and_click_Casual_Shoes() {
		action = new Actions(driver);
		action.moveToElement(driver.findElementByXPath("//a[text()='WOMEN']")).build().perform();
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@id='column_1']//a[text()='Casual Shoes'])[1]")));
		driver.findElementByXPath("(//div[@id='column_1']//a[text()='Casual Shoes'])[1]").click();
		System.out.println("WOMEN -> Casual Shoes clicked");

	}

	@And("Select Color as Black")
	public void select_Color_as_Black() {
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowHandlesList.get(1));
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", driver.findElementByXPath("//label[contains(text(),'Black')]"));
		js.executeScript("arguments[0].click()",driver.findElementByXPath("//label[contains(text(),'Black')]"));
		System.out.println("Color Selected as Black");

	}

	@And("Check whether the product name contains the word black")
	public void check_whether_the_product_name_contains_the_word_black() {
		blackShoeList = driver.findElementsByXPath("//div[@class='row']//span[contains(text(),'Black')]");		
	}

	@And("If so, add the product name and price in to Map")
	public void if_so_add_the_product_name_and_price_in_to_Map() throws InterruptedException {
		js = (JavascriptExecutor) driver;
		if(blackShoeList.size()>0)
		{
			System.out.println("There are products with names that contains the Word Black");
			js.executeScript("arguments[0].scrollIntoView(true)", driver.findElementByXPath("//div[@class='seo_description']"));
			js.executeScript("arguments[0].scrollIntoView(true)",driver.findElementById("moreProduct"));
			js.executeScript("arguments[0].click()",driver.findElementById("moreProduct"));
			Thread.sleep(1000);
			blackShoeList = driver.findElementsByXPath("//div[@class='row']//span[contains(text(),'Black')]");
			System.out.println("There are "+blackShoeList.size()+" number of shoes displayed that contains the word Black");
			for (int i = 1; i <= blackShoeList.size(); i++) 
			{
				String shoeName = driver.findElementByXPath("(//div[@class='row']//span[contains(text(),'Black')])["+i+"]").getText();
				int shoePrice = Integer.parseInt(driver.findElementByXPath("(//div[@class='row']//span[contains(text(),'Black')])["+i+"]/parent::a/div[@class='prd_p_section']//span[@class='p_price']").getText().replaceAll("\\D", ""));
				shoeMap.put(shoeName, shoePrice);
				allShoePrice.add(shoePrice);
				if(shoePrice>500)
				{
					shoePriceMoreThan.add(shoePrice);
				}
			}
		}
		else
		{
			System.out.println("There are NO products with names that contains the Word Black");
		}
	}

	@And("Check Display the count of shoes which are more than 500 rupees")
	public void check_Display_the_count_of_shoes_which_are_more_than_rupees() {
		
		System.out.println("There are "+shoePriceMoreThan.size()+" shoes with price more than 500");

	}

	@And("Click the highest price of the shoe")
	public void click_the_highest_price_of_the_shoe() {

		Collections.sort(allShoePrice);
		String highestPrice = "Rs."+allShoePrice.get(allShoePrice.size()-1).toString();
		js.executeScript("arguments[0].scrollIntoView(true)",driver.findElementByXPath("//span[text()='"+highestPrice+"']"));
		js.executeScript("arguments[0].click()",driver.findElementByXPath("//span[text()='"+highestPrice+"']"));
		System.out.println("Highest price "+highestPrice+" is clicked");
		
	}

	@And("Get the current page URL and check with the product ID")
	public void get_the_current_page_URL_and_check_with_the_product_ID() {
		
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowHandlesList.get(windowHandlesList.size()-1));
		System.out.println("Current URL "+ driver.getCurrentUrl());
		System.out.println(driver.findElementByXPath("//span[@class='pID']").getText());

	}

	@And("Copy the offercode")
	public void copy_the_offercode() {

		String couponCode = driver.findElementByXPath("(//div[@class='coupons_code']/span)[1]").getText();
	}

	@And("Select size, color and click ADD TO CART")
	public void select_size_color_and_click_ADD_TO_CART() {

		driver.findElementByXPath("//span[@class='variant var ' and @value='Black']").click();
		System.out.println("Color Selected");
		driver.findElementByXPath("//li[@scname='Size - EUR or IND']//span").click();
		System.out.println("Size Selected");
		driver.findElementById("add_cart").click();
		System.out.println("Add To Cart Clicked");
	}

	@And("Mouse over on Shopping cart and click View Cart")
	public void mouse_over_on_Shopping_cart_and_click_View_Cart() {

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='cart_ic']")));
		action.moveToElement(driver.findElementByXPath("//a[@class='cart_ic']")).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='View Cart']")));
		driver.findElementByXPath("//a[text()='View Cart']").click();
		System.out.println("View Cart clicked");
		
	}

	@When("Type Pincode as 600016 click Submit and Place Order")
	public void type_Pincode_as_click_Submit_and_Place_Order() {

		wait.until(ExpectedConditions.elementToBeClickable(By.id("pin_code_popup")));
		driver.findElementById("pin_code_popup").sendKeys("600016");
		driver.findElementById("get_pincode_popup").click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Place Order']")));
		driver.findElementByXPath("//div[text()='Place Order']").click();
		System.out.println("Place Order button clicked");
	}

	@Then("Close the Browser")
	public void close_the_Browser() {

		driver.quit();
		System.out.println("Closing all browsers");
	}


}
