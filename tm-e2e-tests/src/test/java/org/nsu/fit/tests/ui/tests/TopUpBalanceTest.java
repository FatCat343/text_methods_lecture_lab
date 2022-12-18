package org.nsu.fit.tests.ui.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.services.browser.Browser;
import org.nsu.fit.services.browser.BrowserService;
import org.nsu.fit.services.fixtures.ContactFixtureBuilder;
import org.nsu.fit.services.rest.RestClient;
import org.nsu.fit.services.rest.data.AccountTokenPojo;
import org.nsu.fit.services.rest.data.ContactPojo;
import org.nsu.fit.services.rest.data.CustomerPojo;
import org.nsu.fit.tests.ui.screen.AdminScreen;
import org.nsu.fit.tests.ui.screen.CustomerScreen;
import org.nsu.fit.tests.ui.screen.LoginScreen;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TopUpBalanceTest {
    private Browser browser = null;
    private RestClient restClient;

    @BeforeClass
    public void beforeClass() {
        restClient = new RestClient();
        browser = BrowserService.openNewBrowser();
    }

    @Test(description = "Top up balance via UI.")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Top up balance feature")
    public void topUpBalanceTest() {
        AccountTokenPojo adminToken = restClient.authenticate("admin", "setup");
        ContactPojo contactPojo = new ContactFixtureBuilder().build();

        AdminScreen screen = new LoginScreen(browser)
                .loginAsAdmin()
                .createCustomer()
                .fillEmail(contactPojo.login)
                .fillPassword(contactPojo.pass)
                .fillFirstName(contactPojo.firstName)
                .fillLastName(contactPojo.lastName)
                .clickSubmit();

        CustomerScreen customerScreen = screen.logout()
                .loginAsCustomer(contactPojo.login, contactPojo.pass);

        customerScreen
                .topUpBalance()
                .fillMoney(10)
                .clickSubmit();

        Assert.assertEquals(10, customerScreen.getBalance());

    }

    @AfterClass
    public void afterClass() {
        if (browser != null) {
            browser.close();
        }
    }
}