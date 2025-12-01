package com.parabank.core;

import com.microsoft.playwright.Page;

public class CoreFunctions {

	private Page page;

    public CoreFunctions(Page page) {
        this.page = page;
    }
    
    public void click(String locator) {
        page.locator(locator).click();
    }
    
    public void fill(String locator, String text) {
        page.locator(locator).fill(text);
    }
    
    public boolean isDisplayed(String locator) {
    	return page.locator(locator).isVisible();
    }
}
