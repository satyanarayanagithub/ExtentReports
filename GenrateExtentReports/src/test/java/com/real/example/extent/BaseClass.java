package com.real.example.extent;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
		
		public static  WebDriver driver;
		public static ExtentReports extentReports;
		public static ExtentTest extenttest;
		public static String subScreenfolder;
		
		@Parameters("browserName")
		@BeforeTest
		public void launch(ITestContext context,  @Optional("chrome") String browser) {
			switch(browser.toLowerCase()){
			case "chrome":
				WebDriverManager.chromedriver().setup();
		    	driver = new ChromeDriver();
		    	break;
			case "ff":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			case "edge":
				 WebDriverManager.edgedriver().setup(); 
				  driver = new EdgeDriver();
				 break;
				 default :
					 System.out.println("select valid");
			}
			 
			driver.manage().window().maximize();
			Capabilities  capabilities = ((RemoteWebDriver)driver).getCapabilities();
			String device = capabilities.getBrowserName()+" "+capabilities.getVersion().substring(0,capabilities.getVersion().indexOf("."));
		    String author = context.getCurrentXmlTest().getParameter("author");
			extenttest =	extentReports.createTest(context.getName());
			extenttest.assignAuthor(author);
			extenttest.assignDevice(device);
		
		}

		@AfterTest
		public void teardown() {
			driver.quit();
		}
		
		
		@BeforeSuite
		public void initialiseExtentreport() {
			
			ExtentSparkReporter SpartRporter = new ExtentSparkReporter("AllTests.html");
			extentReports = new ExtentReports();
			extentReports.attachReporter(SpartRporter);
			extentReports.setSystemInfo("OS", System.getProperty("os.name"));
			extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
		}
		@AfterSuite
		public void generateExtentReport() throws Exception {
			
			extentReports.flush();
			Desktop.getDesktop().browse(new File("AllTests.html").toURI());
			
		}
		
		@AfterMethod
		public void checkSatus(Method m, ITestResult result) {
			
			if(result.getStatus() == ITestResult.FAILURE) {
				
				//takescreenshot(result.getTestContext().getName()+"_"+result.getName());
				extenttest.addScreenCaptureFromPath(takescreenshot(result.getTestContext().getName()+"_"+result.getName()));
				extenttest.fail(result.getThrowable());
				
				
			}else if(result.getStatus() == ITestResult.SUCCESS) {
				extenttest.pass(m.getName()+" is passed"); 
		}
			
		}
		
		public String takescreenshot(String filename) {
			
			if(subScreenfolder == null) {
				 LocalDateTime myDateObj = LocalDateTime.now();
				 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
				 subScreenfolder = myDateObj.format(myFormatObj);	    
			}
			
			File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String destfile = "./Screenshots/"+ subScreenfolder+"/"+filename+".jpg";
			try {
				FileUtils.copyFile(file, new File(destfile));
				System.out.println("Screen shot saved successfully");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return destfile;	
		}
	}


