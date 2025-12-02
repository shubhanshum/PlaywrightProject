package com.parabank.tests;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.BrowserContext;
import com.parabank.core.BaseFactory;
import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BaseTest {

    @After
    public void tearDown(Scenario scenario) {
        Page page = BaseFactory.getPage();
        BrowserContext context = BaseFactory.getContext();

        Path screenshotPath = Paths.get("target/screenshots", scenario.getName() + ".png");
        try {
			Files.createDirectories(screenshotPath.getParent());
			byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
	        Files.write(screenshotPath, screenshot);
	        Allure.addAttachment("Screenshot", Files.newInputStream(screenshotPath));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        
        Path videoPath = null;
        if (scenario.isFailed()) {
            if (page.video() != null) {
                videoPath = page.video().path();
            }
        }
        page.close();
        context.close();
        if (videoPath != null && Files.exists(videoPath)) {
            try {
                Path targetPath = Paths.get("target/videos", scenario.getName() + ".webm");
                Files.createDirectories(targetPath.getParent());
                Files.move(videoPath, targetPath);
                scenario.attach(Files.readAllBytes(targetPath), "video/webm", "video");
                Allure.addAttachment("Video", new ByteArrayInputStream(Files.readAllBytes(targetPath)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BaseFactory.close();
    }
}
