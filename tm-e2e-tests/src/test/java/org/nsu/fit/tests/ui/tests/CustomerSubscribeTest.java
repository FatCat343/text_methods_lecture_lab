package org.nsu.fit.tests.ui.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.services.browser.Browser;
import org.nsu.fit.services.browser.BrowserService;
import org.nsu.fit.services.fixtures.ContactFixtureBuilder;
import org.nsu.fit.services.fixtures.PlanFixtureBuilder;
import org.nsu.fit.services.rest.RestClient;
import org.nsu.fit.services.rest.data.AccountTokenPojo;
import org.nsu.fit.services.rest.data.ContactPojo;
import org.nsu.fit.services.rest.data.CustomerPojo;
import org.nsu.fit.services.rest.data.PlanPojo;
import org.nsu.fit.tests.ui.screen.AdminScreen;
import org.nsu.fit.tests.ui.screen.CustomerScreen;
import org.nsu.fit.tests.ui.screen.LoginScreen;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class CustomerSubscribeTest {
    private Browser browser = null;
    private String login;
    private String pass;
    private RestClient restClient;
    private CustomerScreen customerScreen;
    private AccountTokenPojo customerAccountToken;

    @BeforeClass
    public void beforeClass() {
        browser = BrowserService.openNewBrowser();
        restClient = new RestClient();
//        AccountTokenPojo adminToken = restClient.authenticate("admin", "setup");
        ContactPojo contactPojo = new ContactFixtureBuilder().build();
        PlanPojo planPojo = new PlanFixtureBuilder().build();

        AdminScreen screen = new LoginScreen(browser)
                .loginAsAdmin()
                .createCustomer()
                .fillEmail(contactPojo.login)
                .fillPassword(contactPojo.pass)
                .fillFirstName(contactPojo.firstName)
                .fillLastName(contactPojo.lastName)
                .clickSubmit()
                .createPlan()
                .fillName(planPojo.name)
                .fillDetails(planPojo.details)
                .fillFee(planPojo.fee)
                .clickSubmit();
        screen.logout();

        login = contactPojo.login;
        pass = contactPojo.pass;
    }

    @Test(description = "Subscribe to plan via UI.")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Subscribe feature")
    public void subscribeToFirstPlanTest() {
        customerScreen = new LoginScreen(browser)
                .loginAsCustomer(login, pass)
                .subscribeToFirst();
        customerAccountToken = restClient.authenticate(login, pass);
        List<PlanPojo> subscriptions = restClient.getAvailablePlans(customerAccountToken);

        Assert.assertNotNull(subscriptions);
        Assert.assertNotEquals(0, subscriptions.size());
    }

    @Test(description = "Subscribe to plan via UI.", dependsOnMethods = {"subscribeToFirstPlanTest"})
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Subscribe feature")
    public void unsubscribe() {
        customerScreen.unsubscribeToFirst();
        List<PlanPojo> subscriptions = restClient.getAvailablePlans(customerAccountToken);
        Assert.assertNotNull(subscriptions);
        Assert.assertNotEquals(0, subscriptions.size());
    }

    @AfterClass
    public void afterClass() {
        if (browser != null) {
            browser.close();
        }
    }
}