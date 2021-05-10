package pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificMethods;

public class HomePage extends ProjectSpecificMethods{
	
	public HomePage createAccount(String uName, String pWord) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		List<WebElement> l=driver.findElementsByXPath("//div[@class='autopop__wrap makeFlex column defaultCursor']");
		if(l.size()!=0) {
			driver.findElementByXPath("//span[@class='rightArrow pushRight']").click();
		}else {
			driver.findElementByXPath("//div[@class='makeFlex column flexOne whiteText latoBold']").click();
		}
        Thread.sleep(2000); 
		//user name
		WebElement mobileNo = driver.findElementById("username");
		mobileNo.sendKeys(uName);
		WebElement continueButton = driver.findElementByXPath("//button[@data-cy='continueBtn']");
		if(continueButton.isEnabled()) {
			continueButton.click();
		}
		Thread.sleep(3000);
		//password
		WebDriverWait passWait=new WebDriverWait(driver, 5);
		passWait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
		WebElement password = driver.findElementById("password");
		password.sendKeys(pWord);
		WebElement logIn = driver.findElementByXPath("//span[text()='Login']");
		if(logIn.isEnabled()) {
			logIn.click();
		}
		String otp = driver.findElementByXPath("//label[text()='OTP']").getText();
		System.out.println(otp);
		//Select flight tab
		driver.get("https://www.makemytrip.com/");
		WebDriverWait wait=new WebDriverWait(driver, 10);
		WebElement flight = driver.findElementByXPath("//li[@class='menu_Flights']/a");
		wait.until(ExpectedConditions.elementToBeClickable(flight));
		flight.click();
		System.out.println("success");	
		return this;

	}
	
	public HomePage fromTo() throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait=new WebDriverWait(driver, 30);
		WebElement flight = driver.findElementByXPath("//li[@class='menu_Flights']/a");
		wait.until(ExpectedConditions.elementToBeClickable(flight));
		flight.click();
		//From and To
		driver.findElementById("fromCity").click();
		WebElement from=driver.findElementByXPath("//input[@placeholder='From']");
		from.sendKeys("Goa,India");	
		Thread.sleep(4000);
		from.sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(2000);
		from.sendKeys(Keys.ENTER);
		WebElement to=driver.findElementByXPath("//input[@placeholder='To']");
		to.sendKeys("Mumbai");	
		Thread.sleep(2000);
		to.sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(2000);
		to.sendKeys(Keys.ENTER);
		return this;
	}

	public HomePage dateSelection() throws InterruptedException {

		// Date selection
		String expectedDate ="18-April-2021";
		String eMonth = expectedDate.split("-")[1];
		String eYear = expectedDate.split("-")[2];
		String edate = expectedDate.split("-")[0];
		System.out.println(eMonth+" "+eYear+" "+edate);
		String getMonth= driver.findElementByXPath("(//div[@class='DayPicker-Caption'])[1]/div").getText().trim();
		String aMonth=getMonth.split(" ")[0];
		String aYear=getMonth.split(" ")[1];

		//date selection logic for departure 
		String flagDep="False";
		while(flagDep=="False") {
			if(driver.findElements(By.xpath("//div[@class='DayPicker-Day'][contains(@aria-label,'Wed May 26 2021')]")).size()>0) {
				driver.findElement(By.xpath("//div[@class='DayPicker-Day'][contains(@aria-label,'Wed May 26 2021')]")).click();
				flagDep="True";
			}
			else {
				Thread.sleep(3000);
				driver.findElementByXPath("//span[@aria-label='Next Month']").click();
			}
		}

		//date selection logic for return 
		String flagReturn="False";
		Thread.sleep(3000);
		WebElement returnDate=driver.findElementByXPath("//span[text()='RETURN']");
		returnDate.click();
		while(flagReturn=="False") {
			if(driver.findElements(By.xpath("//div[@class='DayPicker-Day'][contains(@aria-label,'Sat May 29 2021')]")).size()>0) {
				driver.findElement(By.xpath("//div[@class='DayPicker-Day'][contains(@aria-label,'Sat May 29 2021')]")).click();
				flagReturn="True";
			}
			else {
				Thread.sleep(3000);
				driver.findElementByXPath("//span[@aria-label='Next Month']").click();
			}
		}
		System.out.println("Return date selected");
		return this;

	}

	public HomePage travellerClass() throws InterruptedException {
       //Selection of Traveler & class
		WebElement travellersClass=driver.findElementByXPath("//span[contains(text(),'Travellers')]");
		travellersClass.click();
		Thread.sleep(2000);
		driver.findElementByXPath("//li[@data-cy='adults-1']").click();
		driver.findElementByXPath("//li[@data-cy='children-1']").click();
		driver.findElementByXPath("//li[@data-cy='travelClass-0']").click();
		driver.findElementByXPath("//button[@data-cy='travellerApplyBtn']").click();
		System.out.println("Traveller selected");
		return this;
	}
	
	public SearchPage clickSearch() {
		System.out.println("Search button");
		driver.findElementByXPath("//a[text()='Search']").click();
		return new SearchPage();
	}
}
