package CommonUtils;

import org.testng.ITestContext;


import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

@Listeners(ListenerImplementation.class)

public class ListenerImplementation implements ITestListener {
	
	ExtentReports report;

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
	
		//System.out.println("Testscript Execution is started");
		String methodName = result.getMethod().getMethodName();

		Reporter.log(methodName+ "Testscript is started", true);  //Reporter.log(Strings)
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
		//System.out.println("Testscript execution is passed.");
		String methodName = result.getMethod().getMethodName();
		Reporter.log(methodName+ "Testscript execution is Passed", true);
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
		 String message = result.getThrowable().toString();
		String methodName = result.getMethod().getMethodName();

		Reporter.log(methodName+ "Testscript is failed"+message, true);
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();

		Reporter.log(methodName+ "Testscript is skipped", true);

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();

		Reporter.log(methodName+ "Testscript is failed with", true);
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();

		Reporter.log(methodName+ "To fail the testscript", true);

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		JavaUtil jutil = new JavaUtil();
		Reporter.log("To start the testscript",true);
		ExtentSparkReporter reporter = new ExtentSparkReporter("./extentreport/report"+jutil.getRandomNumber()+".html");  //create object pass the path like we do in takesscreenshot
		reporter.config().setDocumentTitle("vtigercrm");  //to set the title 
		reporter.config().setTheme(Theme.STANDARD); //to set theme
		reporter.config().setReportName("Pune");  //to give report name
		
		 report = new ExtentReports();  //use ExtentReports  to generate extentreport
		report.attachReporter(reporter);                 // to add configuration to report attchreporter() is used
        report.setSystemInfo("Windows11", "Chrome");  // enter this report.setSystemInfo("OS", "Browser");
        report.setSystemInfo("Chromeversion", "121");
        report.setSystemInfo("Author","Pune");
        
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
		Reporter.log("To finish the testscript",true);
		report.flush();

	}

	
}
