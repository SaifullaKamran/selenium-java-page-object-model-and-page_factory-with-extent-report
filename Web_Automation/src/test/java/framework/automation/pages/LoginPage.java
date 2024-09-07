package framework.automation.pages;

import java.io.IOException;

import org.apache.hc.core5.http.Message;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import dev.failsafe.Timeout;
import framework.automation.drivers.PageDriver;
import framework.automation.utilities.CommonMethods;
import framework.automation.utilities.ExcelUtils;
import framework.automation.utilities.GetScreenShot;

public class LoginPage extends CommonMethods{
	ExtentTest test;
	ExcelUtils excelUtils = new ExcelUtils();
	public LoginPage(ExtentTest test) {
		PageFactory.initElements(PageDriver.getCurrentDriver(),this);
		this.test=test;
	}
	
	

	@FindBys({
		@FindBy(xpath="//*[@name='username']"),
		@FindBy(xpath="//body/div[@id='app']/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[1]/div[1]/div[2]/input[1]")
	})
	WebElement userName;
	@FindBys({
		@FindBy(xpath="//body/div[@id='app']/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[2]/div[1]/div[2]/input[1]")
	})
	WebElement password;
	@FindBys({
		@FindBy(xpath="//body/div[@id='app']/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[3]/button[1]")
	})
	WebElement login;
	    // PASS CASE
		public void passCase(String message) {
			test.pass("<p style=\"color:#85BC63; font-size:13px\"><b>" + message + "</b></p>");
		}

		// PASS CASE WITH SCREENSHOT
		public void passCaseWithSC(String message, String scName) throws IOException{
			test.pass("<p style=\"color:#85BC63; font-size:13px\"><b>" + message + "</b></p>");
			String screenShotPath = GetScreenShot.capture(PageDriver.getCurrentDriver(), "" + scName + "");
			String dest = System.getProperty("user.dir") + "\\screenshots\\" + "" + scName + ".png";
			test.pass(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
			
		}

		// FAIL CASE WITH SCREENSHOT
		
		public void failCase(String message, String scName) throws IOException {
			test.fail("<p style=\"color:#FF5353; font-size:13px\"><b>" + message + "</b></p>");
			Throwable t = new InterruptedException("Exception");
			test.fail(t);
			String screenShotPath = GetScreenShot.capture(PageDriver.getCurrentDriver(), "" + scName + "");
			String dest = System.getProperty("user.dir") + "\\screenshots\\" + "" + scName + ".png";
			test.fail(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
			PageDriver.getCurrentDriver().quit();
		}
	public void login() throws IOException {
		excelUtils.writeExcelData("Safulla", "Kamran");
		excelUtils.ReadExcel();
		try {
			test.info("Please enter your username");
			if (userName.isDisplayed()) {
				userName.sendKeys(excelUtils.username);
				passCase("you have entered your username");
				timeout();
				try {
					test.info("Please enter your password");
					if (password.isDisplayed()) {
						password.sendKeys(excelUtils.password);
						passCase("you have entered your password");
						timeout();
						try {
							test.info("Please click on the login button");
							if (login.isDisplayed()) {
								login.click();
								timeout(10000);
								passCaseWithSC("login successfull", "login_pass");
								//timeout();
							}
						} catch (Exception e) {
							System.out.println("Login cannot clikable");
							failCase("login button was not locatable", "login_failed");
						}
					}
				} catch (Exception e) {
					System.out.println("Passwod was not locatable");
					failCase("Passwod was not locatable", "password_failed");
				}
			}
		} catch (Exception e) {
			System.out.println("Username was not locatable");
			failCase("username was not locatable", "username_failed");
		}
	}

	

}
