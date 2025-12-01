package com.parabank.steps;

import org.testng.Assert;

import com.microsoft.playwright.Page;
import com.parabank.core.BaseFactory;
import com.parabank.pages.HomePage;
import com.parabank.pages.LandingPage;
import com.parabank.utils.Utilities;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
	
	Page page;
	LandingPage landingPage;
	HomePage homePage;
	
	@Given("user is on landing page")
	public void user_is_on_landing_page() {
		String browserName=Utilities.getConfigProperty("browser").toString();
		boolean isHeadlessMode=Boolean.parseBoolean(Utilities.getConfigProperty("headlessMode").toString());
		String url=Utilities.getConfigProperty("url").toString();
		page=BaseFactory.initBrowser(browserName, isHeadlessMode);
		page.navigate(url);
		landingPage=new LandingPage(page);
		homePage=new HomePage(page);
	}

	@When("user enters username")
	public void user_enters_username() {
		String userName=Utilities.getConfigProperty("username").toString();
		landingPage.enterUserName(userName);
	}
	
	@And("user enters password")
	public void user_enters_password() {
		String pwd=Utilities.getConfigProperty("password").toString();
		landingPage.enterPassword(pwd);
	}
	
	@And("user clicks on login button")
	public void user_clicks_on_login_button() {
		landingPage.clickLoginButton();
	}
	
	@Then("user can see total balance")
	public void user_can_see_total_balance() {
		boolean isvisible=homePage.isTotalBalanceDisplayed();
		Assert.assertTrue(isvisible);
	}
}
