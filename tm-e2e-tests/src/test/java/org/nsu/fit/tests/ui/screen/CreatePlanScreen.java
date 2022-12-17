package org.nsu.fit.tests.ui.screen;

import org.nsu.fit.services.browser.Browser;
import org.nsu.fit.shared.Screen;
import org.openqa.selenium.By;
import org.testng.Assert;

public class CreatePlanScreen extends Screen {
    public CreatePlanScreen(Browser browser) {
        super(browser);
    }

    public CreatePlanScreen fillName(String name) {
        browser.typeText(By.name("name"), name);
        return this;
    }

    public CreatePlanScreen fillDetails(String details) {
        browser.typeText(By.name("details"), details);
        return this;
    }
    public CreatePlanScreen fillFee(int fee) {
        browser.typeText(By.name("fee"), String.valueOf(fee));
        return this;
    }
    public AdminScreen clickSubmit(){
        browser.waitForElement(By.xpath("//button[@type = 'submit']"));
        Assert.assertEquals(browser.currentPage(), "http://localhost:8090/tm-frontend/add-plan");
        browser.click(By.xpath("//button[@type = 'submit']"));
        browser.waitForElement(By.xpath("//button[@title = 'Add Customer']"), 2);
        return new AdminScreen(browser);
    }

    public AdminScreen clickCancel() {
        browser.click(By.xpath("//button[@type = 'button']"));
        return new AdminScreen(browser);
    }
}