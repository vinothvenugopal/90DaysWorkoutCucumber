package cucumber.testcases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Day4_BigBasket {
	
	ChromeDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	Actions action;
	
	@Given("Go to BigBasket website")
	public void go_to_BigBasket_website() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		driver = new ChromeDriver();
		System.out.println("Browser Launched");
		driver.manage().window().maximize();
		System.out.println("Browser Maximized");
		driver.get("https://www.bigbasket.com");
		System.out.println("URL Loaded");
	}

	@And("mouse over on  Shop by Category")
	public void mouse_over_on_Shop_by_Category() {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='dropdown full-wid hvr-drop']")));
		action = new Actions(driver);
		action.moveToElement(driver.findElementByXPath("//li[@class='dropdown full-wid hvr-drop']")).build().perform();

	}

	@And("Go to FOODGRAINS, OIL & MASALA and RICE & RICE PRODUCTS")
	public void go_to_FOODGRAINS_OIL_MASALA_and_RICE_RICE_PRODUCTS() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='Foodgrains, Oil & Masala'])[2]")));
		action.moveToElement(driver.findElementByXPath("(//a[text()='Foodgrains, Oil & Masala'])[2]")).build().perform();
		System.out.println("Foodgrains, Oil & Masala clicked");
	}

	@And("Click on BOILED & STEAM RICE")
	public void click_on_BOILED_STEAM_RICE() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@class='ng-scope']//a[text()='Rice & Rice Products'])[2]")));
		action.moveToElement(driver.findElementByXPath("(//li[@class='ng-scope']//a[text()='Rice & Rice Products'])[2]")).click().build().perform();
		System.out.println("Boiled and Steam Rice Clicked");
	}

	@And("Get the URL of the page and check with site navigation link")
	public void get_the_URL_of_the_page_and_check_with_site_navigation_link_HOME_FOODGRAINS_OIL_MASALA_RICE_RICE_PRODUCTS_BOILED_STEAM_RICE() {
		System.out.println(driver.getCurrentUrl());
	}

	@And("Choose the Brand as bb Royal")
	public void choose_the_Brand_as_bb_Royal() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='col-xs-12 checkbox ng-scope']//span[text()='bb Royal'])[1]")));
		driver.findElementByXPath("(//div[@class='col-xs-12 checkbox ng-scope']//span[text()='bb Royal'])[1]").click();
		System.out.println("Brand - BB Royal clicked");
	}

	@And("Go to Ponni Boiled Rice and select 10kg bag from Dropdown")
	public void go_to_Ponni_Boiled_Rice_and_select_kg_bag_from_Dropdown() throws InterruptedException {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 500)");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Ponni Boiled Rice - Super Premium']")));
		String productName = driver.findElementByXPath("//a[text()='Ponni Boiled Rice - Super Premium']").getText();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='Ponni Boiled Rice - Super Premium']/parent::div/following-sibling::div//i)[1]")));
		WebElement ele = driver.findElement(By.xpath("(//a[text()='Ponni Boiled Rice - Super Premium']/parent::div/following-sibling::div//i)[1]"));
		js.executeScript("arguments[0].click()", ele);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='Ponni Boiled Rice - Super Premium']/parent::div/following-sibling::div//span[text()='10 kg'])")));
		js.executeScript("arguments[0].click()",driver.findElementByXPath("(//a[text()='Ponni Boiled Rice - Super Premium']/parent::div/following-sibling::div//span[text()='10 kg'])"));
		System.out.println("Ponni Boiled Rice - Super Premium clicked");

	}

	@And("Click Add button")
	public void click_Add_button() {
		driver.findElementByXPath("(//a[text()='Ponni Boiled Rice - Super Premium']/ancestor::div[@class='ng-scope'][1]//div[@class='delivery-opt']//button)[1]").click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='Continue'])[1]")));
		driver.findElementByXPath("(//a[text()='Continue'])[1]").click();

	}

	@And("Go to search box and type Dal")
	public void go_to_search_box_and_type_Dal() {

		driver.findElementById("input").sendKeys("Dal", Keys.ENTER);
		System.out.println("Dal entered in search box and search initiated");
	}

	@And("Add Toor Arhar Dal 2kg and set Qty 2 from the list")
	public void add_Toor_Arhar_Dal_kg_and_set_Qty_from_the_list() {
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='toor dal']")));
		driver.findElementByXPath("//a[text()='toor dal']").click();
		System.out.println("Clicked Toor dal");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']")));
		String productName = driver.findElementByXPath("//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']").getText();
		driver.findElementByXPath("(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/parent::div/following-sibling::div//i)[1]").click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/parent::div/following-sibling::div//span[contains(text(),'2 kg')])")));
		driver.findElementByXPath("(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/parent::div/following-sibling::div//span[contains(text(),'2 kg')])").click();
		driver.findElementByXPath("(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/parent::div/following-sibling::div)[2]//button[@qa='add']").click();
		
		System.out.println("Toor Dal - 2kg pack selected successfully");

	}

	@And("click Address")
	public void click_Address() {
		js.executeScript("arguments[0].click()",driver.findElementByXPath("//span[@class='hvc']"));
	}

	@And("Select CHennai as City, Alandur-600016,Chennai as Area  and click Continue")
	public void select_CHennai_as_City_Alandur_Chennai_as_Area_and_click_Continue() {
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='form-group area-autocomplete city-select'])[1]")));
//		js.executeScript("arguments[0].click()",driver.findElementByXPath("(//div[@class='form-group area-autocomplete city-select'])[1]"));
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='ui-select-choices-group']//span[text()='Chennai']")));
//		driver.findElementByXPath("//li[@class='ui-select-choices-group']//span[text()='Chennai']").click();
//		System.out.println("City selected as Chennai");
		driver.findElementById("areaselect").sendKeys("600016");
		js.executeScript("arguments[0].click()", driver.findElementByXPath("(//button[text()='Continue'])[1]"));
		System.out.println("Continue button clicked");
	}

	@And("Mouse over on My Basket take a screen shot")
	public void mouse_over_on_My_Basket_take_a_screen_shot() throws InterruptedException, IOException {

		
		action.moveToElement(driver.findElementByXPath("//span[@class='basket-content']")).build().perform();
		Thread.sleep(1000);
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("./screenshots/MyBasket.png");
		FileUtils.copyFile(src, dest);
		System.out.println("Screenshot captured under screenshot folder successfully."+'\n'+"Screen shot File Name : MyBasket.png");

	}

	@When("Click View Basket and Checkout")
	public void click_View_Basket_and_Checkout() {
		action.moveToElement(driver.findElementByXPath("//span[@class='basket-content']")).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='View Basket & Checkout']")));
		driver.findElementByXPath("//button[text()='View Basket & Checkout']").click();
	}

	@Then("Click the close button and close the browser")
	public void click_the_close_button_and_close_the_browser() {
		driver.close();

	}

}
