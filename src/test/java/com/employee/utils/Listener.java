package com.employee.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class Listener implements ITestListener {
    public ExtentSparkReporter reporter;
    public ExtentReports repo;
    public ExtentTest test;
    ThreadLocal<ExtentTest> local=new ThreadLocal<>();
    @Override
    public void onTestStart(ITestResult result) {
        test=repo.createTest(result.getName());
        local.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
      local.get().log(Status.PASS,"Test Case Passed is"+result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        local.get().log(Status.FAIL,"Test Case Failed is"+result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        local.get().log(Status.SKIP,"Test Case Skipped is"+result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
    reporter=new ExtentSparkReporter(System.getProperty("user.dir")+ "/Reports/myReport.html");
    reporter.config().setDocumentTitle("Automation Report");
    reporter.config().setTheme(Theme.DARK);
    reporter.config().setReportName("Rest API Test Report");
    reporter.config().setTimelineEnabled(true);

    repo=new ExtentReports();
    repo.attachReporter(reporter);
        repo.setSystemInfo("Project Name","Employee Database API");
        repo.setSystemInfo("Host name","localhost");
        repo.setSystemInfo("Environemnt","QA");
        repo.setSystemInfo("user","pavan");

    }

    @Override
    public void onFinish(ITestContext context) {
    repo.flush();
    }
}
