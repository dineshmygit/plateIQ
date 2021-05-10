package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import base.ProjectSpecificMethods;

public class SearchPage extends ProjectSpecificMethods{
	
	public SearchPage flightSeletion() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		System.out.println(driver.getTitle());
		JavascriptExecutor js=(JavascriptExecutor)driver;
		WebElement checkBox=driver.findElementByXPath("((//div[@class='listingCardWrap'])[1]//label)[2]/div");
		js.executeScript("arguments[0].click()", checkBox);
		Thread.sleep(3000);
		WebElement checkBoxtwo=driver.findElementByXPath("((//div[@class='listingCardWrap'])[2]//label)[2]");
		js.executeScript("arguments[0].click()", checkBoxtwo);
		System.out.println("Button slecteted");
		return this;
	}
}
