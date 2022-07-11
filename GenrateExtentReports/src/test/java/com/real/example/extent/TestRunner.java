package com.real.example.extent;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestRunner extends BaseClass {


		@Test (testName = "loginOrangeHRM",groups= {"Smoke"})
		public void loginOrangeHRM() throws InterruptedException {
			driver.navigate().to("https://opensource-demo.orangehrmlive.com/");
			extenttest.info("Orange Hrm url is launched successfully");
			driver.findElement(By.id("txtUsername")).sendKeys("Admin");
			driver.findElement(By.id("txtPassword")).sendKeys("admin123");
			driver.findElement(By.id("btnLogin")).click();
			Thread.sleep(5000);
			Assert.assertTrue(driver.findElement(By.id("welcome")).isDisplayed());
			extenttest.info("Orange Hrm login method executed successfully");
		
			
		}
		
		@Test(testName = "Google",groups= {"Sanity"})
		public void Google() {

			driver.navigate().to("https://www.google.com");
			extenttest.info("google is launched successfully");
			driver.findElement(By.name("q")).sendKeys("testng"+Keys.ENTER);
			System.out.println(driver.getCurrentUrl());
		}
		@Test (testName = "FaceBook",groups= {"Regression"})
		public void FaceBook() {

			driver.navigate().to("https://www.facebook.com/");
			extenttest.info("FaceBook is launched successfully");
			driver.manage().window().maximize();
			driver.findElement(By.id("email")).sendKeys("BBK Learning");
			driver.findElement(By.name("login")).click();

			System.out.println(driver.getTitle());

			System.out.println(driver.getCurrentUrl());
			
			SoftAssert soft= new SoftAssert();

			String expected = "Log in to Facebook";
			String actual = driver.getTitle();
			soft.assertEquals(actual, expected,"Title is Mismatched");
			System.out.println(driver.getTitle());
			
			String expectedurl = "https://www.facebook.com/";
			String actualurl = driver.getCurrentUrl();
			soft.assertEquals(actualurl,expectedurl,"Url is mismatched");
		    
		    String actualtext = driver.findElement(By.id("email")).getAttribute("value");
		    String expectedtext = "";
		    soft.assertEquals(actualtext,expectedtext,"actual text is mismatched");
		    
		    String actualerror=driver.findElement(By.xpath("//div[@id='email_container']/div[2]")).getText();
		    String expectederror = "The email address or mobile number you entered isn't connected to an account. Find your account and log in.";
		    soft.assertEquals(actualerror,expectederror,"actual text is mismatched");
		    
		    String actualboarder = driver.findElement(By.id("email")).getCssValue("border");
		    String expectedboarder = "1px solid rgb(240, 40, 73)";
		    soft.assertEquals(actualboarder,expectedboarder,"actual boarder is mismatched");

			System.out.println();
			
			soft.assertAll();
			
		}
		

	}


