package org.nsu.fit.tests.ui.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.services.browser.Browser;
import org.nsu.fit.services.browser.BrowserService;
import org.nsu.fit.services.fixtures.ContactFixtureBuilder;
import org.nsu.fit.services.log.Logger;
import org.nsu.fit.services.rest.data.ContactPojo;
import org.nsu.fit.tests.ui.screen.LoginScreen;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateCustomerFailedTest {
    private Browser browser = null;

    @BeforeClass
    public void beforeClass() {
        browser = BrowserService.openNewBrowser();
    }

    @Test(description = "Create customer via UI failed")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Create customer feature")
    public void createCustomer() throws Exception {
        ContactPojo contactPojo = new ContactFixtureBuilder().setPass("").build();

        Assert.assertThrows(Exception.class, () ->
                new LoginScreen(browser)
                        .loginAsAdmin()
                        .createCustomer()
                        .fillEmail(contactPojo.login)
                        .fillPassword(contactPojo.pass)
                        .fillFirstName(contactPojo.firstName)
                        .fillLastName(contactPojo.lastName)
                        .clickSubmit());

        Assert.assertEquals(browser.currentPage(), "http://localhost:8090/tm-frontend/add-customer");

        String text = browser.getText(By.xpath("/html/body/div[1]/div/div/div[1]"));
        Logger.debug(text);
    }

    @AfterClass
    public void afterClass() {
        if (browser != null) {
            browser.close();
        }
    }
}