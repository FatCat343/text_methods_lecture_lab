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
import org.nsu.fit.tests.ui.screen.AdminScreen;
import org.nsu.fit.tests.ui.screen.LoginScreen;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DeleteCustomerTest {
    private Browser browser = null;
    private RestClient restClient;

    @BeforeClass
    public void beforeClass() {
        restClient = new RestClient();
        browser = BrowserService.openNewBrowser();
    }

    @Test(description = "Delete customer via UI.")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Delete customer feature")
    public void deleteCustomer() {
        AccountTokenPojo adminToken = restClient.authenticate("admin", "setup");
        ContactPojo contactPojo = new ContactFixtureBuilder().build();

        AdminScreen adminScreen = new LoginScreen(browser)
                .loginAsAdmin()
                .createCustomer()
                .fillEmail(contactPojo.login)
                .fillPassword(contactPojo.pass)
                .fillFirstName(contactPojo.firstName)
                .fillLastName(contactPojo.lastName)
                .clickSubmit();
        int customerIndex = browser.getContactIndex(contactPojo);
        Assert.assertNotEquals(-1, customerIndex);
        adminScreen.deleteCustomer(customerIndex);
    }

    @AfterClass
    public void afterClass() {
        if (browser != null) {
            browser.close();
        }
    }
}
