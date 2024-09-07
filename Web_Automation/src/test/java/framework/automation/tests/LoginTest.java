package framework.automation.tests;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.io.IOException;
import java.security.PublicKey;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import framework.automation.drivers.PageDriver;
import framework.automation.pages.LoginPage;
import framework.automation.utilities.CommonMethods;
import framework.automation.utilities.ExtentFactory;

    public class LoginTest extends CommonMethods{ 	
    ExtentReports report;
    ExtentTest parentTest;
    ExtentTest childTest;
    @BeforeClass
    public void openUrl() throws InterruptedException {
	PageDriver.getCurrentDriver().get(url);
	timeout();
	report = ExtentFactory.getInstance();
	parentTest = report.createTest("<p style=\"color:#FF6000; font-size:13px\"><b>Orange HRM</b></p>").assignAuthor("QA Team").assignDevice("Windows");
	timeout();
	}
	
	@Test
	public void testLoginMethod() throws InterruptedException, IOException {
	childTest = parentTest.createNode("<p style=\"color:#3E96E7; font-size:13px\"><b>Login</b></p>");
	LoginPage loginpage=new LoginPage(childTest);
	loginpage.login();
	timeout();
	}
	@AfterClass
	public void report() {
		report.flush();
	}

}
