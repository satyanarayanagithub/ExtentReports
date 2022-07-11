package com.extent.reports;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class SatyaExtentReportsTest {
	ExtentReports extent;
	ExtentSparkReporter spark;
	ExtentTest test;
  @Test
  public void Test1() {
	  test = extent.createTest("verify user able to launch")
			  .assignAuthor("Satish")
			  .assignCategory("smoky")
			  .assignDevice("chrome");
	  test.log(Status.PASS, "browser launched by user");
	  test.info("User able to lauch the browser");
	  test.pass("Test1 Completed Successfully");
	  
	  
  }
  @Test
  public void Test2() {
	  test = extent.createTest("verify user able login application")
			        .assignAuthor("satya")
	                .assignCategory("sanity")
	                .assignDevice("ff");
	  test.info("User able to login Application");
	  test.pass("Test1 Completed Successfully");
	  test.warning("validate if any found");
	  
			  
  }
  @Test
  public void Test3() {
	 test = extent.createTest("verify user able to see the home screen")
			       .assignAuthor("sai")
			       .assignCategory("regression")
			       .assignDevice("edge");
	 test.info("user unable to see  home the screen");
	 test.fail("Test3 failed");
	 test.warning("validate if any found");
	              
  }
  @Test
  public void Test4() {
	  test = extent.createTest("verify user able to see logout button" )
			       .assignAuthor("govindappa")
			       .assignCategory("regression")
			       .assignDevice("chrome");
	  test.info("user unable to see logout button");
	  test.skip("Test 4 skipped");
			  
  }
  @Test
  public void Test5() {
	  
	 test = extent.createTest("verify reports generated or not")
			      .assignAuthor("durga")
			      .assignCategory("regression")
			      .assignDevice("chrome");
	 test.info("user able to generate the report");
	 test.pass("Reports Generate Sucessfully");
			 
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
