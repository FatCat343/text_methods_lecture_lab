package org.nsu.fit.tests.ui.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.services.browser.Browser;
import org.nsu.fit.services.browser.BrowserService;
import org.nsu.fit.services.fixtures.PlanFixtureBuilder;
import org.nsu.fit.services.rest.data.PlanPojo;
import org.nsu.fit.tests.ui.screen.AdminScreen;
import org.nsu.fit.tests.ui.screen.LoginScreen;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DeletePlanTest {
    private Browser browser = null;

    @BeforeClass
    public void beforeClass() {
        browser = BrowserService.openNewBrowser();
    }

    @Test(description = "Delete plan via UI.")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Delete plan feature")
    public void deletePlan() {
        PlanPojo planPojo = new PlanFixtureBuilder().build();

        AdminScreen adminScreen = new LoginScreen(browser)
                .loginAsAdmin()
                .createPlan()
                .fillName(planPojo.name)
                .fillDetails(planPojo.details)
                .fillFee(planPojo.fee)
                .clickSubmit();

        int planIndex = browser.getPlanIndex(planPojo);
        Assert.assertNotEquals(-1, planIndex);
        adminScreen.deletePlan(planIndex);
    }

    @AfterClass
    public void afterClass() {
        if (browser != null) {
            browser.close();
        }
    }
}
