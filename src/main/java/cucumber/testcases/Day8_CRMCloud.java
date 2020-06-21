package cucumber.testcases;

import java.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Day8_CRMCloud {
	
	ChromeDriver driver;
	WebDriverWait wait;
	Actions action;
	JavascriptExecutor js;
	//gets current date and converts the date object to string and getting only the date portion using subString
	String thisDate = LocalDate.now().toString().substring(8,10);
	int nextDay = Integer.parseInt(thisDate)+1;
	
	@Given("Go to crmcould website")
	public void go_to_crmcould_website() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		driver = new ChromeDriver();
		System.out.println("Browser Launched");
		wait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		System.out.println("Browser Maximized");
		driver.get("https://demo.1crmcloud.com/");
		System.out.println("URL Loaded");
	}

	@And("Give username as admin and password as admin")
	public void give_username_as_admin_and_password_as_admin() {
		wait.until(ExpectedConditions.elementToBeClickable(By.id("login_user")));
		driver.findElementById("login_user").sendKeys("admin");
		System.out.println("User Name Entered");
		driver.findElementById("login_pass").sendKeys("admin");
		System.out.println("Password Entered");

	}

	@And("Choose theme as Claro Theme")
	public void choose_theme_as_Claro_Theme() {
		WebElement loginThemeEle = driver.findElementById("login_theme");
		Select loginThemeDD = new Select(loginThemeEle);
		loginThemeDD.selectByVisibleText("Claro Theme");
		System.out.println("Claro Theme selected");
		driver.findElementById("login_button").click();
		System.out.println("Login button clicked");

	}

	@And("Go to Sales and Marketting and click Sales Home")
	public void go_to_Sales_and_Marketting_and_click_Sales_Home() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Sales & Marketing']")));
		System.out.println("Login Success");
		driver.findElementByXPath("//div[text()='Sales & Marketing']").click();
		System.out.println("Sales & Marketing clicked");

	}

	@And("Click Create contact")
	public void click_Create_contact() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Create Contact']")));
		driver.findElementByXPath("//div[text()='Create Contact']").click();
		System.out.println("Create Contact clicked");

	}

	@And("Select (.*) and type (.*), (.*), (.*) and (.*)")
	public void enterNameDetails(String title,String firstName,String lastName,String email,String phoneNumber) throws InterruptedException {
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("DetailFormsalutation-input")));
		driver.findElementById("DetailFormsalutation-input").click();
		Thread.sleep(1000);
		//clicks the appropriate salutation
		driver.findElementByXPath("//div[text()='"+title+"']").click();
		driver.findElementById("DetailFormfirst_name-input").sendKeys(firstName);
		driver.findElementById("DetailFormlast_name-input").sendKeys(lastName);
		driver.findElementById("DetailFormemail1-input").sendKeys(email);
		driver.findElementById("DetailFormphone_work-input").sendKeys(phoneNumber);


	}

	@And("Select Lead Source as (.*) and Sales")
	public void select_Lead_Source_as_Public_Relations_and_Sales(String leadSource) {
		driver.findElementById("DetailFormlead_source-input").click();
		driver.findElementByXPath("//div[text()='"+leadSource+"']").click();

	}

	@And("Fill the (.*), (.*), (.*), (.*) and (.*) and click Save. Check if the (.*) (.*) is already available")
	public void fill_the_address(String address,String city, String state, String country, String pinCode, String firstName, String lastName) throws InterruptedException {
		driver.findElementById("DetailFormprimary_address_street-input").sendKeys(address);
		driver.findElementById("DetailFormprimary_address_city-input").sendKeys(city);
		driver.findElementById("DetailFormprimary_address_state-input").sendKeys(state);
		driver.findElementById("DetailFormprimary_address_country-input").sendKeys(country,Keys.TAB);
		driver.findElementById("DetailFormprimary_address_postalcode-input").sendKeys(pinCode);
		driver.findElementById("DetailForm_save2").click();
		Thread.sleep(2000);
		
		//searching if the contact is already available. if yes, contact creating will be cancelled and create meeting
				//will be processed
				if(driver.findElementsByXPath("(//span[text()='Save'])[1]").size()>0)
				{
					js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].click()",driver.findElementByXPath("//span[text()='Cancel']"));
					System.out.println("Contact with the name: "+firstName+" "+lastName+" and similar personal details already exists."+'\n'+"Creation could possibly a duplication"+'\n'+"Cancelling Contact Creation and proceeding further with the meeting");
				}
				else
				{
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'"+firstName+" "+lastName+"')]")));
					if(driver.findElementByXPath("//div[@class='summary-header']//h3").getText().contains(firstName+" "+lastName))
					{
						System.out.println("Contact  Creation successful");
					}
					else
					{
						System.out.println("Some issues with contact creation");
					}
				}
		

	}

	@And("Mouse over on Today's Activities and click Meetings")
	public void mouse_over_on_Today_s_Activities_and_click_Meetings() {
		action.moveToElement(driver.findElementByXPath("//div[contains(text(),'Today')]")).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Meetings']")));
		driver.findElementByXPath("//div[text()='Meetings']").click();
		System.out.println("Meetings Clicked");

	}

	@And("Type Subject as Project Status Status as Planned and Time as tomorrow 3 p.m")
	public void type_Subject_as_Project_Status_Status_as_Planned_and_Time_as_tomorrow_p_m() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Create']")));
		js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()",driver.findElementByXPath("//span[text()='Create']"));
		System.out.println("Create meeting clicked");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("DetailFormname-input")));
		driver.findElementById("DetailFormname-input").sendKeys("Project Status");
		driver.findElementById("DetailFormstatus-input").click();
		driver.findElementByXPath("//div[text()='Planned']");
		Thread.sleep(2000);
		driver.findElementByXPath("//div[@class='uii uii-lg uii-date']").click();
		if(driver.findElements(By.xpath("//div[text()='"+nextDay+"']")).size()>1)
		{
			driver.findElementByXPath("(//div[text()='"+nextDay+"'])[2]").click();
		}
		else
		{
			driver.findElementByXPath("//div[text()='"+nextDay+"']").click();
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='popup-search tools-row']//input[@class='input-text']")));
		driver.findElementByXPath("//div[@class='popup-search tools-row']//input[@class='input-text']").sendKeys("03:00", Keys.ENTER);
		driver.findElementById("DetailFormduration-time").clear();
		driver.findElementById("DetailFormduration-time").sendKeys("1",Keys.TAB);

	}

	@And("Click Add paricipants, add your created (.*) (.*) and click Save")
	public void click_Add_paricipants_add_your_created_Lead_name_and_click_Save(String firstName, String lastName) throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' Add Participants']")));
		driver.findElementByXPath("//span[text()=' Add Participants']").click();
		System.out.println("Add Participants clicked");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='app-search']//input[@class='input-text']")));
		driver.findElementByXPath("//div[@id='app-search']//input[@class='input-text']").sendKeys(firstName+" "+lastName, Keys.TAB);
		System.out.println("Contact Name entered in the search field");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='"+firstName+" "+lastName+"']")));
		driver.findElementByXPath("//div[text()='"+firstName+" "+lastName+"']").click();
		driver.findElementById("DetailForm_save2").click();

	}

	@When("Click contacts under Sales and Marketting, search the (.*) (.*) and click the name from the result")
	public void click_contacts_under_Sales_and_Marketting_search_the_lead_Name_and_click_the_name_from_the_result(String firstName, String lastName) throws InterruptedException {
		action.moveToElement(driver.findElementByXPath("//div[text()='Sales & Marketing']")).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Contacts']")));
		driver.findElementByXPath("//div[text()='Contacts']").click();
		System.out.println("Sales & Marketing -> Contacts clicked");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("filter_text")));
		driver.findElementById("filter_text").sendKeys(firstName+" "+lastName);
		System.out.println("Name entered in the search box");
		Thread.sleep(500);
		driver.findElementById("filter_text").sendKeys(Keys.ENTER);
		System.out.println("Search Initiated");

	}

	@Then("Check weather the Meeting is assigned to the (.*) (.*)")
	public void check_weather_the_Meeting_is_assigned_to_the_contact(String firstName, String lastName) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@class='listView']")));
		Thread.sleep(2000);
		if(driver.findElementsByXPath("//table[@class='listView']//a[contains(text(),'"+firstName+" "+lastName+"')]").size()>0)
		{
			driver.findElementByXPath("//table[@class='listView']//a[contains(text(),'"+firstName+" "+lastName+"')]").click();
			Thread.sleep(1500);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//table[@class='listView']//a)[6]")));
			String text = driver.findElementByXPath("(//table[@class='listView']//a)[6]").getText();
			if(driver.findElementByXPath("(//table[@class='listView']//a)[6]").getText().contains("Project Status"))
			{
				System.out.println("Activity check complete. Activity -> "+text);
			}
			else
			{
				System.out.println("Meeting is not found under Activities");
			}
		}
		driver.close();
	}


}
