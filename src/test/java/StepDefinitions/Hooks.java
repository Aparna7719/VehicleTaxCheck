package StepDefinitions;

import Utils.CommonUtils;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import java.io.IOException;

public class Hooks
{
        CommonUtils commonUtilsObj = new CommonUtils();

        @Before
        public void beforeTest() throws IOException
        {
            commonUtilsObj.openBrowser();
        }

        @After
        public void afterTest()

        {
            commonUtilsObj.closeBrowser();
        }

}


