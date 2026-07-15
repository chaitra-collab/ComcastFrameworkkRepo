package practice.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.generic.basetest.BaseClass;



//@Listeners(com.comcast.crm.listenerutility.ListImpClass.class)
public class InvoiceTest extends BaseClass {
	@Test(retryAnalyzer=com.comcast.crm.listenerutility.RetryListenerImp.class)
	//toretryonlyfsiledtestcript
	public void activateSim() {
		System.out.println("execute createInvoiceTest");
		String actTitle=driver.getTitle();
		Assert.assertEquals(actTitle, "Title");
		//refershprojetst-ouptuttestnf-failed.xml
		//toexecutefailedtestscriptsuite
		//execute3000testscripts//1000testscriptgotfailed//newbuildexecuteonlyfailedtetscriptsafterexceutiontakebackuptestng-outputfolderandexecureonlyfailedetestscript
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
		
}

@Test
public void createInvoiceWithContactTest()
{
	System.out.println("execute createInvoiceWithContactTest");
	System.out.println("step-1");
	System.out.println("step-2");
	System.out.println("step-3");
	System.out.println("step-4");
}
}