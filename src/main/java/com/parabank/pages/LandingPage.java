package com.parabank.pages;

import com.microsoft.playwright.Page;
import com.parabank.core.CoreFunctions;

public class LandingPage {

	private Page page;
	private CoreFunctions functions;
	
	public LandingPage(Page page) {
		this.page=page;
		this.functions=new CoreFunctions(page);
	}
	
	private String username="[name='username']";
	private String password="[name='password']";
	private String loginBtn="[value='Log In']";
	
	public void enterUserName(String uname) {
		functions.fill(username, uname);
	}
	
	public void enterPassword(String pwd) {
		functions.fill(password, pwd);
	}
	
	public void clickLoginButton() {
		functions.click(loginBtn);
	}
}
