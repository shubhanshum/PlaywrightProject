package com.parabank.pages;

import com.microsoft.playwright.Page;
import com.parabank.core.CoreFunctions;

public class HomePage {

	private Page page;
	private CoreFunctions functions;
	
	public HomePage(Page page) {
		this.page=page;
		this.functions=new CoreFunctions(page);
	}
	
	private String totalBalance="//td[@align='right']//following-sibling::td[1]";
	
	public boolean isTotalBalanceDisplayed() {
		return functions.isDisplayed(totalBalance);
	}
}
