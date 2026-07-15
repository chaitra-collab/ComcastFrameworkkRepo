package practice.test;

/**
 * test class for contact module
 * @author Deepak
 */
import org.testng.annotations.Test;

import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.crm.generic.basetest.BaseClass;

public class SearchContactTest extends BaseClass {
	/**
	 * Scenario : login()==>navigateToContact===>createContact()===verify
	 */
    @Test
    public void searchContactTest() {
    	/*step1 login to app*/
    	  	LoginPage lp=new LoginPage(driver);
    	  	lp.loginToapp("url", "username", "password");
    }
}
