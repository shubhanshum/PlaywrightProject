package com.parabank.tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "src/test/resources/features",
	    glue = {"com.parabank.steps", "hooks"},
	    plugin = {
	        "pretty",
	        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
	    },
	    monochrome = true
	)


public class RunnerTest extends AbstractTestNGCucumberTests{

}
