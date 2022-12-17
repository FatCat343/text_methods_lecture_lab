package org.nsu.fit.tests.ui.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.services.browser.Browser;
import org.nsu.fit.services.browser.BrowserService;
import org.nsu.fit.services.fixtures.ContactFixtureBuilder;
import org.nsu.fit.services.rest.data.ContactPojo;
import org.nsu.fit.tests.ui.screen.AdminScreen;
import org.nsu.fit.tests.ui.screen.LoginScreen;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AuthTest {
    private Browser browser = null;

    @BeforeClass
    public void beforeClass() {
        browser = BrowserService.openNewBrowser();
    }

    @Test(description = "Login as admin via UI.")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Login as admin feature")
    public void loginAsAdmin() {
        new LoginScreen(browser)
                .loginAsAdmin()
                .logout();
    }

    @Test(description = "Login as customer via UI.")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Login as customer feature")
    public void loginAsCustomer() {
        ContactPojo contactPojo = new ContactFixtureBuilder().build();

        AdminScreen screen = new LoginScreen(browser)
                .loginAsAdmin()
                .createCustomer()
                .fillEmail(contactPojo.login)
                .fillPassword(contactPojo.pass)
                .fillFirstName(contactPojo.firstName)
                .fillLastName(contactPojo.lastName)
                .clickSubmit();

        screen.logout().loginAsCustomer(contactPojo.login, contactPojo.pass);
    }
    @AfterClass
    public void afterClass() {
        if (browser != null) {
            browser.close();
        }
    }
}