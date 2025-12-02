package com.parabank.core;

import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BaseFactory {

	private static ThreadLocal<Playwright> threadLocal = new ThreadLocal<>();
    private static ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static ThreadLocal<Page> page = new ThreadLocal<>();

    public static Page initBrowser(String browserName, boolean headless) {
    	threadLocal.set(Playwright.create());

        BrowserType browserType;
        switch (browserName.toLowerCase()) {
            case "firefox": browserType = threadLocal.get().firefox(); 
            break;
            case "webkit": browserType = threadLocal.get().webkit(); 
            break;
            default: browserType = threadLocal.get().chromium();
        }

        browser.set(browserType.launch(new BrowserType.LaunchOptions().setHeadless(headless)));
        
        
        context.set(browser.get().newContext(new Browser.NewContextOptions()
                .setRecordVideoDir(Paths.get("target/videos")) 
                .setRecordVideoSize(1280, 720)
            ));
        
        page.set(context.get().newPage());
        return page.get();
    }

    public static Page getPage() { 
    	return page.get(); 
    	}
    
    public static BrowserContext getContext() {
        return context.get();
    }

    public static void close() {
        browser.get().close();
        threadLocal.get().close();
    }
}
