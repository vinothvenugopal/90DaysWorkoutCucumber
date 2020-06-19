package cucumber.testcases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Day7_BigBasket {
	
	ChromeDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	Actions action;
	List<String> productName = new ArrayList<String>();
	List<WebElement> productList = new ArrayList<WebElement>();

	@Given("Go to BigBasket website for this test case")
	public void go_to_BigBasket_website()
	{
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		driver = new ChromeDriver();
		System.out.println("Browser Launched");
		driver.manage().window().maximize();
		System.out.println("Browser Maximized");
		driver.get("https://www.bigbasket.com");
		System.out.println("URL Loaded");
	}
	
	@Given("mouse over on Shop by Category for this test case")
	public void mouse_hover_on_shop_by_category()
	{
		action = new Actions(driver);
		action.moveToElement(driver.findElementByXPath("//li[@class='dropdown full-wid hvr-drop']")).build().perform();
	}
	
	@Given("Go to Beverages and Fruit juices & Drinks")
	public void go_to_Beverages_and_Fruit_juices_Drinks() {

		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id='navBarMegaNav']//a[text()='Beverages']")));
		action.moveToElement(driver.findElementByXPath("//ul[@id='navBarMegaNav']//a[text()='Beverages']")).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='Fruit Juices & Drinks'])[2]")));
		action.moveToElement(driver.findElementByXPath("(//a[text()='Fruit Juices & Drinks'])[2]")).build().perform();
	}

	@And("Click on JUICES")
	public void click_on_JUICES() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='Juices'])[2]")));
		driver.findElementByXPath("(//a[text()='Juices'])[2]").click();
		System.out.println("Clicked on Beverages -> Fruit Juice & Drinks -> Juices");
	}

	@And("click Tropicana and Real under Brand")
	public void click_Tropicana_and_Real_under_Brand() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Brand']/parent::h4/parent::div//span[text()='Tropicana'])[1]")));
		driver.findElementByXPath("(//span[text()='Brand']/parent::h4/parent::div//span[text()='Tropicana'])[1]").click();
		System.out.println("Tropicana Clicked");
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Brand']/parent::h4/parent::div//span[text()='Real'])[1]")));
		driver.findElementByXPath("(//span[text()='Brand']/parent::h4/parent::div//span[text()='Real'])[1]").click();
		System.out.println("Real Clicked");
	}

	@And("Check count of the products from each Brands and total count")
	public void check_count_of_the_products_from_each_Brands_and_total_count() throws InterruptedException {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", driver.findElementByXPath("//h4[text()='PAYMENT OPTIONS:']"));
		js.executeScript("arguments[0].scrollIntoView(true)",driver.findElementByXPath("//h2[@qa='pageName']//span[text()='Juices']"));
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@qa='product_name']/h6")));
		Thread.sleep(3000);
		productList = driver.findElementsByXPath("//div[@qa='product_name']/h6");
		for (int i = 1; i <= productList.size(); i++) {
			productName.add(driver.findElementByXPath("(//div[@qa='product_name']/h6)["+i+"]").getText());
		}
		int realCount = Collections.frequency(productName, "Real");
		int tropicanaCount = Collections.frequency(productName, "Tropicana");
		System.out.println("Juice with Real Brand : "+realCount);
		System.out.println("Juice with Tropicana Brand : "+tropicanaCount);
		int totalCountDisplayed = Integer.parseInt(driver.findElementByXPath("//h2[@qa='pageName']").getText().replaceAll("\\D", ""));
		if(totalCountDisplayed == (realCount+tropicanaCount))
		{
			System.out.println("Total number of juices displayed matches with the sum of Real and Tropicana Count");
		}
		else
		{
			System.out.println("Total number of juices displayed doesn't match with the sum of Real and Tropicana Count");			
		}
	}

	@And("Check whether the products is availabe with Add button.")
	public void check_whether_the_products_is_availabe_with_Add_button() {
		
		List<WebElement> numberOfAddButton = driver.findElementsByXPath("//button[contains(text(),'Add')]");
		if(numberOfAddButton.size() == productList.size())
		{
			System.out.println("All displayed products has the Add button associated with it");
		}
		else
		{
			int missingAddButton = productList.size()-numberOfAddButton.size();
			System.out.println(missingAddButton+" of the displayed products do not have Add button associated to it");
		}
	}

	@And("Add the First listed available product")
	public void add_the_First_listed_available_product() {
		driver.findElementByXPath("(//button[contains(text(),'Add')])[1]").click();
		System.out.println("First listed product added to cart");
//		driver.findElementByXPath("(//button[text()='×'])[5]").click();
	}

	@And("click on Change Address")
	public void click_on_Change_Address() {
//		driver.findElementByXPath("//span[@class='hvc']").click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='Change Location'])[1]")));
		driver.findElementByXPath("(//a[text()='Change Location'])[1]").click();
		
		

	}
	@And("Select CHennai as City, Alandur-600016,Chennai as Area  and click Continue for this test case")
	public void select_City()
	{
		driver.findElementByXPath("(//div[@placeholder='Select your city']//span[@aria-label='Select box activate'])[1]").click();
		driver.findElementByXPath("//input[@aria-label='Select box'][1]").sendKeys("Chennai");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='ui-select-choices-row-inner']//span[text()='Chennai']")));
		driver.findElementByXPath("//a[@class='ui-select-choices-row-inner']//span[text()='Chennai']").click();
		driver.findElementByXPath("//input[@id='areaselect']").sendKeys("600016",Keys.TAB);
		driver.findElementByXPath("//div[@class='skp-exp']//button[text()='Continue']").click();
		System.out.println("Address changed");
	}
	
	@And("Mouse over on My Basket print the product name. count and price.")
	public void mouse_over_on_My_Basket_print_the_product_name_count_and_price() {
		action.moveToElement(driver.findElementByXPath("//span[@class='basket-content']")).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='product-name']")));
		System.out.println("Product Name: "+driver.findElementByXPath("//div[@class='product-name']").getText());
		String[] split = driver.findElementByXPath("//div[@class='product-qty ng-binding']").getText().split(" ");
		System.out.println("Product Quantity :"+split[0]);
		System.out.println("Product Price : "+driver.findElementByXPath("//div[@class='row mrp']").getText());

	}

	@When("Click View Basket and Checkout for this test case")
	public void click_View_Basket_and_Checkout() {

		driver.findElementByXPath("//button[text()='View Basket & Checkout']").click();
		System.out.println("View Basket & Checkout button clicked");
	}
	
	@Then("Click the close button and close the browser for this test case")
	public void close_Browser() {

		js.executeScript("arguments[0].click()",driver.findElementByXPath("//span[@class='login-icon login-icon-close']"));
		System.out.println("pop up closed");
		System.out.println("Closing Browser");
		driver.close();
	}
}
