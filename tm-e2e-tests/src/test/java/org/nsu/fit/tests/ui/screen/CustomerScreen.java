package org.nsu.fit.tests.ui.screen;

import org.nsu.fit.services.browser.Browser;
import org.nsu.fit.shared.Screen;
import org.openqa.selenium.By;

public class CustomerScreen extends Screen {
    public CustomerScreen(Browser browser) {
        super(browser);
    }

    public CustomerScreen subscribeToFirst() {
        String rowXPath = "//*[@id='root']/div/div/div/div/div[2]/div[2]/div/div/div/table/tbody/tr[1]/td[1]/div/";
        browser.click(By.xpath(rowXPath + "button"));
        browser.waitForElement(By.xpath(rowXPath + "button[1]"));
        browser.click(By.xpath(rowXPath + "button[1]"));
        return this;
    }

    public CustomerScreen unsubscribeToFirst() {
        String rowXPath = "//*[@id='root']/div/div/div/div/div[1]/div[2]/div/div/div/table/tbody/tr[1]/td[1]/div/";
        browser.click(By.xpath(rowXPath + "button"));
        browser.waitForElement(By.xpath(rowXPath + "button[1]"));
        browser.click(By.xpath(rowXPath + "button[1]"));
        return this;
    }
    public TopUpBalanceScreen topUpBalance() {
        browser.click(By.xpath("//*[@id=\"root\"]/div/div/div/div/p[1]/a"));
        return new TopUpBalanceScreen(browser);
    }

    public int getBalance() {
        return Integer.parseInt(browser.getTextByElement("//*[@id=\"root\"]/div/div/div/div/h3").split(":")[1].trim());
    }
}
