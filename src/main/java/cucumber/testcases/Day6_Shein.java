package cucumber.testcases;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Day6_Shein {
	
	ChromeDriver driver;
	Actions action;
	JavascriptExecutor js;
	WebDriverWait wait;
	
	@Given("open shein website")
	public void open_shein_website() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions option = new ChromeOptions();
		option.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(option);
		System.out.println("Browser launched");
		driver.manage().window().maximize();
		System.out.println("Browser Maximized");
		driver.get("https://www.shein.in");
		System.out.println("URL Loaded");
	}

	@And("Mouseover on Clothing and click Jeans")
	public void mouseover_on_Clothing_and_click_Jeans() {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='c-coupon-box']//i[@class='iconfont icon-close she-close']")));
		driver.findElementByXPath("//div[@class='c-coupon-box']//i[@class='iconfont icon-close she-close']").click();
		action = new Actions(driver);
		action.moveToElement(driver.findElementByXPath("//span[text()='CLOTHING']")).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'Denim')]/parent::h6[@class='j-nav2-third']/following-sibling::div/a[contains(text(),'Jeans')])[1]")));
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()",driver.findElementByXPath("(//a[contains(text(),'Denim')]/parent::h6[@class='j-nav2-third']/following-sibling::div/a[contains(text(),'Jeans')])[1]"));
		System.out.println("Clothing -> Jeans clicked");
	}

	@And("Choose Black under Jeans product count")
	public void choose_Black_under_Jeans_product_count() {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", driver.findElementByXPath("//span[@class='attr-item-pic j-attr-item']//span[text()='Black']/following-sibling::i"));
		driver.findElementByXPath("//span[@class='attr-item-pic j-attr-item']//span[text()='Black']/following-sibling::i").click();
		System.out.println("Black selected under Product Color");
	}

	@And("check size as medium")
	public void check_size_as_medium() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Size']/parent::li//i")));
		js.executeScript("arguments[0].scrollIntoView(true)",driver.findElementByXPath("//span[text()='Size']/parent::li//i"));
		driver.findElementByXPath("//span[text()='Size']/parent::li//i").click();
		System.out.println("Size expanded");
		Thread.sleep(500);
		driver.findElementByXPath("//a[@data-attr-value_id='417']").click();
		System.out.println("Size Medium selected");
	}

	@And("check whether the color is black")
	public void check_whether_the_color_is_black() {

		String attribute = driver.findElementByXPath("//span[text()='Black']/parent::a").getAttribute("class");
		if(attribute.equals("j-auto-attrlink attr-item-selected"))
		{
			System.out.println("Color Black is selected");
		}
		else
		{
			System.out.println("Color Black is not selected");
		}
	}

	@And("Click first item to Add to Bag")
	public void click_first_item_to_Add_to_Bag() {
		action.moveToElement(driver.findElementByXPath("//div[@class='c-goodsitem__absolute j-sc-goodsitem']")).build().perform();
		driver.findElementByXPath("(//button[contains(text(),'+ Add to Bag')])[1]").click();
		System.out.println("Add to Bag clicked");

	}

	@And("Click the size as M abd click Submit")
	public void click_the_size_as_M_abd_click_Submit() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@class='opt-size j-quick-add-opt']/span[contains(text(),'M')]")));
		action.moveToElement(driver.findElementByXPath("//label[@class='opt-size j-quick-add-opt']/span[contains(text(),'M')]")).click().build().perform();
		System.out.println("Size M selected");
		driver.findElementByXPath("(//button[contains(text(),'Submit')])[1]").click();
		System.out.println("Submit button clicked");
	}

	@And("Click view Bag")
	public void click_view_Bag() {
		action.moveToElement(driver.findElementByXPath("//a[@class='j-ipad-prevent-a j-sa-cart-1']")).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='view bag']")));
		driver.findElementByXPath("//a[text()='view bag']").click();
		System.out.println("View Bag clicked");
	}

	@When("Check the size is Medium or not")
	public void check_the_size_is_Medium_or_not() {
		String size = driver.findElementByXPath("//span[@class='gd-size']/em").getText();
		if(size.equals("M"))
		{
			System.out.println("Size M is selected");
		}
		else
		{
			System.out.println("Size M is not selected");
		}

	}

	@Then("Close the browser")
	public void close_the_browser() {
		System.out.println("Closing browser");
		driver.close();
	}


}
