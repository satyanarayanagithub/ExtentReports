package com.extent.reports;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class ExtentReportsTest {
	ExtentReports extent;
	ExtentSparkReporter spark;
	ExtentTest test;
	
  @Test
  public void Test1() {
	  test = extent.createTest("Verify user able to launch Application")
			  .assignAuthor("Bharath")
			  .assignCategory("Smoke")
			  .assignDevice("chrome");
	  test.log(Status.PASS, "browser launched by user");
	  test.info("User able to laucn the browser");
	  test.pass("Test1 Completed Successfully");
			  
	  
  }
  @Test
  public void Test2() {
	  test = extent.createTest("Verify user able to login into the Application")
			  .assignAuthor("Satya")
			  .assignCategory("Sanity")
			  .assignDevice("FF");
	  test.info("User able to login into the application");
	  test.pass("Test2 Completed Successfully");
	test.warning("validate if any warning found");	  
	  
	  
  }
  @Test
  public void Test3() {
	  test = extent.createTest("Verify user able to see home screen")
			  .assignAuthor("Satish")
			  .assignCategory("Regression")
			  .assignDevice("edge");
	  test.info("User unable to see home screen");
	  test.fail("Test3 Failed");
	test.warning("validate if any warning found");	  
	  
	
	  
  }
  @Test
  public void Test4() {
	  test = extent.createTest("Verify user able to see logout button")
			  .assignAuthor("Ravi")
			  .assignCategory("Regression")
			  .assignDevice("edge");
	  test.info("User unable to see logout screen");
	  test.skip("Test4 Skipped");
 
	  
	  
  }
  @Test
  public void Test5() {
	  test = extent.createTest("Verify reports generated or not")
			  .assignAuthor("BBK")
			  .assignCategory("Regression")
			  .assignDevice("safari");
	  test.info("User able to see generated reports");
	  test.pass("Reports generated successfully");

	  
  }
  @BeforeTest
  public void beforeTest() {
	  
	  extent = new ExtentReports();
	  spark = new ExtentSparkReporter("Results.html");
	  extent.attachReporter(spark);

  }

  @AfterTest
  public void afterTest() {
	  extent.flush();
  }

}
