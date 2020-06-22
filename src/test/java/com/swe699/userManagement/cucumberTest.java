package com.swe699.userManagement;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/resources", plugin = "json:target/cucumber-report.json")
public class cucumberTest {
}
