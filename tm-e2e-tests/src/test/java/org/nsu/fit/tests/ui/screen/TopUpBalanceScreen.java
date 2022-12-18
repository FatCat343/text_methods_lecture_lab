package org.nsu.fit.tests.ui.screen;

import org.nsu.fit.services.browser.Browser;
import org.nsu.fit.shared.Screen;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

public class TopUpBalanceScreen extends Screen {
    public TopUpBalanceScreen(Browser browser) {
        super(browser);
    }

    public TopUpBalanceScreen fillMoney(int money){
        browser.typeText(By.name("money"), String.valueOf(money));
        return this;
    }

    public CustomerScreen clickSubmit(){
        browser.waitForElement(By.xpath("//button[@type = 'submit']"));
        browser.click(By.xpath("//button[@type = 'submit']"));
        try
        {
            browser.waitForElement(By.linkText("Top up balance"), 2);
            return new CustomerScreen(browser);
        }
        catch (TimeoutException e)
        {
            String message = browser.getText(By.xpath("/html/body/div[1]/div/div/div[1]"));
            throw new TimeoutException(message);
        }
    }
}
