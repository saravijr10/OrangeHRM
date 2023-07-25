package listenerClassPkg;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import mainClassPkg.MainClass;

public class ListenerClass implements ITestListener {
	public void onFinish(ITestContext Result)
	   {
			System.out.println("/****The test case Ends ****/");    
	   }

	   public void onStart(ITestContext Result)
	   {
		   System.out.println("/****The test case started ****/");
	   }

	   public void onTestFailedButWithinSuccessPercentage(ITestResult Result)
	   {
	   
	   }

	   // When Test case get failed, this method is called.
	   public void onTestFailure(ITestResult Result)
	   {
		   MainClass obj = new MainClass();
		   try {
				obj.WriteExcelData("Fail");
				//obj.CaptureScreen(obj.driver);
			} catch (Exception e) {
				e.printStackTrace();
			}
		   System.out.println("The name of the testcase failed is :"+Result.getName());
	   }

	   // When Test case get Skipped, this method is called.
	   public void onTestSkipped(ITestResult Result)
	   {
		   System.out.println("The name of the testcase Skipped is :"+Result.getName());
		   MainClass obj = new MainClass();
		   try {
				obj.WriteExcelData("Skipped");
			} catch (Exception e) {
				e.printStackTrace();
			}
	   }

	   // When Test case get Started, this method is called.
	   public void onTestStart(ITestResult Result)
	   {
		   System.out.println(Result.getName()+" test case started");
	   }

	   // When Test case get passed, this method is called.
	   public void onTestSuccess(ITestResult Result)
	   {
		   System.out.println("The name of the testcase passed is :"+Result.getName());
		   MainClass obj = new MainClass();
		   try {
				obj.WriteExcelData("Pass");
				//obj.CaptureScreen(obj.driver);
			} catch (Exception e) {
				e.printStackTrace();
			}
	   }
}

