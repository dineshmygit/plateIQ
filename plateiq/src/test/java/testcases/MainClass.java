package testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificMethods;
import pages.HomePage;

public class MainClass extends ProjectSpecificMethods{
	
	@BeforeTest
	public void seFileName() {
		excelFileName="DataCredentials";
	}

	@Test(dataProvider="fetchData")
	public void callAllFunctions(String uName, String pWord) throws InterruptedException {
		new HomePage().createAccount(uName, pWord).fromTo().dateSelection().travellerClass().clickSearch().flightSeletion();
	}
}
