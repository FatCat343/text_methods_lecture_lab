package org.nsu.fit.tests.ui.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.services.browser.Browser;
import org.nsu.fit.services.browser.BrowserService;
import org.nsu.fit.services.fixtures.PlanFixtureBuilder;
import org.nsu.fit.services.rest.data.PlanPojo;
import org.nsu.fit.tests.ui.screen.LoginScreen;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreatePlanTest {
    private Browser browser = null;

    @BeforeClass
    public void beforeClass() {
        browser = BrowserService.openNewBrowser();
    }

    @Test(description = "Create plan via UI.")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Create plan feature")
    public void createPlan() {
        PlanPojo planPojo = new PlanFixtureBuilder().build();
        new LoginScreen(browser)
                .loginAsAdmin()
                .createPlan()
                .fillName(planPojo.name)
                .fillDetails(planPojo.details)
                .fillFee(planPojo.fee)
                .clickSubmit();
    }

    @AfterClass
    public void afterClass() {
        if (browser != null) {
            browser.close();
        }
    }
}
